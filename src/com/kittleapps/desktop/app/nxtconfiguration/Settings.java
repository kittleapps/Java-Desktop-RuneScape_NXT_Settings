package com.kittleapps.desktop.app.nxtconfiguration;

import static com.sun.jna.platform.win32.WinReg.HKEY_CURRENT_USER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;

import com.sun.jna.platform.win32.Advapi32Util;

public class Settings {

	public static String
		NXT_REGISTRY_LOCATION_BASE = "Software\\Jagex\\RuneScape",
		Cache_settings_location = "",
		configuration_location = "",
		launcher_client_position = "",
		current_line = "";
	private static int splash_x_position, splash_y_position;
	public static File preferences_config, Settings_db;
	private static boolean NXT_INSTALLED;
	private static BufferedReader reader;
	private static StringBuilder sb, messages;

	private static String graphicsSettingsFilter(final int FilterType, final String msg) {
		String value = msg.replace(" ", "_=_").replace("_", " ");
		if (FilterType == -1) {} else if (FilterType == 0) {
			value = value.replace("0", "Disabled")
						 .replace("1", "Enabled");
		} else if (FilterType == 1) {
			value = value.replace("0", "Low")
						 .replace("1", "Medium")
						 .replace("2", "High")
						 .replace("3", "Ultra");
		} else if (FilterType == 2) {
			value = value.replace("0", "Off")
						  .replace("1", "Low")
						  .replace("2", "Medium")
						  .replace("3", "High")
						  .replace("4", "Ultra");
		} else if (FilterType == 3) {
			value = value.replace("0", "Off")
						 .replace("1", "SSAO")
						 .replace("2", "HBAO");
		} else if (FilterType == 4) {
			value = value.replace("0", "Off")
						 .replace("1", "Compressed")
						 .replace("2", "Uncompressed");
		} else if (FilterType == 5) {
			value = value.replace("0", "Off")
						 .replace("1", "FXAA")
						 .replace("2", "MSAA")
						 .replace("3", "FXAA+MSAA");
		} else if (FilterType == 6) {
			value = value.replace("0", "Off")
						 .replace("1", "x2")
						 .replace("2", "x4")
						 .replace("3", "x8")
						 .replace("4", "x16");
		} else if (FilterType == 7) {
			value = value.replace("-1", "Adaptive")
						 .replace("0",  "Off")
						 .replace("1",  "On")
						 .replace("2",  "Half")
						 .replace("3",  "Quarter");
		} else if (FilterType == 8) {
			value = value.replace("0", "None")
						 .replace("1", "Selectively")
						 .replace("2", "Always")
						 .replace("3", "All");
		} else if (FilterType == 10) {
			value = value.replace("0", "Off (0)");
		} else if (FilterType == 11) {
			value = value.replace("0", "Lowest (Slider = 0%)")
						 .replace("1", "Low (Slider = 25%)")
						 .replace("2", "Medium (Slider = 50%)")
						 .replace("3", "High (Slider = 75%)")
						 .replace("4", "Highest (Slider = 100%)");
		} else {
			value = "Invalid FilterType Used.";
		}
		return "\t" + value;
	}

	private static void SendVerboseMessage(final String msg) {
		messages.append("<div class='test'><font color=\"green\">" + msg + "</font></div>\n");
		Document doc = NXTSettingsGUI.VerboseOutputArea.getDocument();
		try {
			NXTSettingsGUI.VerboseOutputAreaEditor.insertHTML((HTMLDocument) doc, doc.getLength(), "<div class='test'><font color=\"green\">" +
															 msg.replace("{{nl}}", "<br>").replace("\t", "&ensp;&ensp;&ensp;") +
															 "</font></div>", 0, 0, null);
		} catch(BadLocationException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void TestRead() {
		int OS_TYPE = Storage.OS_TYPE;
		NXTSettingsGUI.VerboseOutputArea.setText("");
		messages = new StringBuilder("<html>");

		if (OS_TYPE == 0) {
			NXT_REGISTRY_LOCATION_BASE = "Software\\Jagex\\RuneScape";
			NXT_INSTALLED = Advapi32Util.registryKeyExists(HKEY_CURRENT_USER, NXT_REGISTRY_LOCATION_BASE);
			SendVerboseMessage("NXT's installed: " + ("" + NXT_INSTALLED).replace("true", "Yes.")
																		 .replace("false", "No."));
		} else if (OS_TYPE == 1) {
			NXT_REGISTRY_LOCATION_BASE = "/usr/bin/runescape-launcher";
			NXT_INSTALLED = new File(NXT_REGISTRY_LOCATION_BASE).exists();
			SendVerboseMessage("NXT's installed: " + ("" + NXT_INSTALLED).replace("true", "Yes.")
																		 .replace("false", "No."));
		}
		if (NXT_INSTALLED) {
			if (OS_TYPE == 0) {
				configuration_location = Advapi32Util.registryGetStringValue(HKEY_CURRENT_USER, NXT_REGISTRY_LOCATION_BASE, "splash").replace("splash6.gif", "preferences.cfg").replace("\\", "/");
				splash_x_position = Advapi32Util.registryGetIntValue(HKEY_CURRENT_USER, NXT_REGISTRY_LOCATION_BASE + "\\Persistent_Options\\LauncherSplash\\Splash", "x");
				splash_y_position = Advapi32Util.registryGetIntValue(HKEY_CURRENT_USER, NXT_REGISTRY_LOCATION_BASE + "\\Persistent_Options\\LauncherSplash\\Splash", "y");
				launcher_client_position = Advapi32Util.registryGetStringValue(HKEY_CURRENT_USER, NXT_REGISTRY_LOCATION_BASE, "client_position");
				SendVerboseMessage("NXT's registry path: HKEY_CURRENT_USER\\" + NXT_REGISTRY_LOCATION_BASE);
				SendVerboseMessage("NXT's configuration file is located at: " + configuration_location);
				SendVerboseMessage("NXT's splash screen X-coordinate is set to: " + splash_x_position);
				SendVerboseMessage("NXT's splash screen Y-coordinate is set to: " + splash_y_position);
				SendVerboseMessage("NXT's client position is set to: " + launcher_client_position);
			} else if (OS_TYPE == 1) {
				configuration_location = new File(System.getProperty("user.home").replace("\\", "/") + "/Jagex/launcher/preferences.cfg").getAbsolutePath();
				try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home").replace("\\", "/") + "/.runescape"))) {
					String line;
					while ((line = br.readLine()) != null) {
						if (line.startsWith("client_position")) {
							launcher_client_position = line.trim().replace("client_position=", "");
						} else if (line.startsWith("x")) {
							splash_x_position = new Integer(line.trim().replace("x=", ""));
						} else if (line.startsWith("y")) {
							splash_y_position = new Integer(line.trim().replace("y=", ""));
						}
					}

				} catch(IOException e) {
					e.printStackTrace();
				}
			}


			Cache_settings_location = "";
			preferences_config = new File(configuration_location);


			if (preferences_config.exists()) {
				try {
					reader = new BufferedReader(new FileReader(preferences_config));
					current_line = "";
					while ((current_line = reader.readLine()) != null) {
						if (current_line.startsWith("graphics_device=")) {
							SendVerboseMessage("NXT's graphics device is currently set to: " + current_line.trim().replace("graphics_device=", ""));
						}
						if (current_line.startsWith("compatibility=")) {
							String CompatMode = current_line.trim()
															.replace("compatibility=", "")
															.replace("true", "On.")
															.replace("false", "Off.")
															.replace("default", "Automatic."
									);
							if (CompatMode.equals("On.")) {
								Storage.nxtClientSettings_CompatibilityMode = 0;
							} else if (CompatMode.equals("Off.")) {
								Storage.nxtClientSettings_CompatibilityMode = 1;
							} else if (CompatMode.equals("Automatic.")) {
								Storage.nxtClientSettings_CompatibilityMode = 2;
							} else {
								// Default "Automatic" on-error.
								Storage.nxtClientSettings_CompatibilityMode = 2;
							}
							SendVerboseMessage("NXT's compatibility mode is currently: " + CompatMode);
						}
						if (current_line.startsWith("dont_ask_graphics=")) {
							SendVerboseMessage("NXT will currently" + 
												current_line.trim().replace("dont_ask_graphics=", "")
																   .replace("1", " NOT ")
																   .replace("0", " ") +
											   "ask to switch to compatibility mode on-error."
									);
						}
						if (current_line.startsWith("confirm_quit=")) {
							SendVerboseMessage("NXT will currently" + 
												current_line.trim()
															.replace("confirm_quit=", "")
															.replace("0", " NOT ")
															.replace("1", " ") +
												"ask if it's okay to close the program while logged in."
									);
						}
						if (current_line.startsWith("graphics_version=")) {
							SendVerboseMessage("NXT's last read graphics driver version was: " + current_line.trim().replace("graphics_version=", ""));
						}
						if (current_line.startsWith("cache_folder=")) {
							if (OS_TYPE == 0) {
								SendVerboseMessage("NXT's cache is located at: " + current_line.trim().replace("cache_folder=", "").replace("\\", "/") + "/RuneScape");
							} else if (OS_TYPE == 1) {
								SendVerboseMessage("NXT's cache is located at: " + current_line.trim().replace("cache_folder=", "").replace("\\", "/") + "/RuneScape");
							}
						}
						if (current_line.startsWith("Language=")) {
							SendVerboseMessage("NXT's server language is currently set to: " +
									current_line.trim()
												.replace("Language=", "")
												.replace("0", "English (English/Standard Servers)")
												.replace("1", "Deutsch (Dutch Servers)")
												.replace("2", "Fran�ais (French Servers)")
												.replace("3", "portugu� (Portuguese/Brazilian Servers)")
									);
						}
						if (current_line.startsWith("user_folder=")) {
							Cache_settings_location = current_line.trim().replace("user_folder=", "").replace("\\", "/") + "/RuneScape/Settings.jcache";
							SendVerboseMessage("NXT's standard cache/graphics settings are located at: " + Cache_settings_location);
						}
					}
					reader.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			} else {
				SendVerboseMessage("The configuration file does NOT exist at: " + configuration_location);
			}

			Settings_db = new File(Cache_settings_location);
			sb = new StringBuilder("");

			if (Settings_db.isFile()) {
				try {
					Class.forName("org.sqlite.JDBC");
				} catch(ClassNotFoundException eString) {
					System.err.println("Could not init JDBC driver - driver not found");
				}
				try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Cache_settings_location);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM Config")) {
					while (rs.next()) {
						sb.append(rs.getString("KEY").trim() + " " + rs.getString("DATA").trim() + "\n");
					}
					conn.close();
					stmt.close();
					rs.close();
				} catch(SQLException e) {
					System.err.println(e.getMessage());
				}
				try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Cache_settings_location);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM \"player\"")) {
					while (rs.next()) {
						if (rs.getString("KEY").equals("uid")) {
							if (NXTSettingsGUI.ShowSensitiveInformation.isSelected()) {
								SendVerboseMessage("NXT's current uid is: " + rs.getString("DATA") + "{{nl}}");
							} else {
								SendVerboseMessage("NXT's current uid is: (CENSORED){{nl}}");
							}
						}
					}
					conn.close();
					stmt.close();
					rs.close();
				} catch(SQLException e) {
					SendVerboseMessage("\t" + "No settings found.{{nl}}");
				}

				SendVerboseMessage("NXT's current graphics settings are currently:");
				for (String Line: sb.toString().split("\n")) {
					if (Line.startsWith("CustomCursors") ||
							Line.startsWith("Shadows") ||
							Line.startsWith("FlickeringEffects") ||
							Line.startsWith("GroundDecor") ||
							Line.startsWith("GroundBlending")) {
						SendVerboseMessage(graphicsSettingsFilter(0, Line.replace("CustomCursors", "Custom_Cursors")
																		 .replace("FlickeringEffects", "Flickering_Effects")
																		 .replace("GroundDecor", "Ground_Decororations")
																		 .replace("GroundBlending", "Terrain_Blending")
								));
					} else if (Line.startsWith("DrawDistance") ||
							Line.startsWith("ShadowQuality") ||
							Line.startsWith("LightingQuality") ||
							Line.startsWith("AntialiasingQuality") ||
							Line.startsWith("Reflections")) {
						SendVerboseMessage(graphicsSettingsFilter(1, Line.replace("DrawDistance", "Draw_Distance")
																	 	 .replace("ShadowQuality", "Shadow_Quality")
																		 .replace("LightingQuality", "Lighting_Quality")
																		 .replace("AntialiasingQuality", "Anti-aliasing_Quality")
																		 .replace("Reflections", "Water_Detail")
								));
					} else if (Line.startsWith("VolumetricLighting") ||
							Line.startsWith("Bloom")) {
						SendVerboseMessage(graphicsSettingsFilter(2, Line.replace("VolumetricLighting", "Volumetric_Lighting_Quality")
								.replace("Bloom", "Bloom_Quality")));
					} else if (Line.startsWith("AmbientOcclusion")) {
						SendVerboseMessage(graphicsSettingsFilter(3, Line.replace("AmbientOcclusion", "Ambient_Occlusion")));
					} else if (Line.startsWith("Texturing")) {
						SendVerboseMessage(graphicsSettingsFilter(4, Line.replace("Texturing", "Texture_Quality")));
					} else if (Line.startsWith("AntialiasingMode")) {
						SendVerboseMessage(graphicsSettingsFilter(5, Line.replace("AntialiasingMode", "Anti-aliasing_Mode")));
					} else if (Line.startsWith("AnisotropicFiltering")) {
						SendVerboseMessage(graphicsSettingsFilter(6, Line.replace("AnisotropicFiltering", "Anisotropic_Filtering")));
					} else if (Line.startsWith("VSync")) {
						SendVerboseMessage(graphicsSettingsFilter(7, Line));
					} else if (Line.startsWith("RemoveRoof")) {
						SendVerboseMessage(graphicsSettingsFilter(8, Line.replace("RemoveRoof", "Remove_Roofs")));
					} else if (Line.startsWith("Version")) {
						NXTSettingsGUI.frame.setTitle("NXT Settings (Settings Version: "+Line.replace("Version ", "")+")");
					} else if (Line.startsWith("VolumeLoginMusic") ||
							Line.startsWith("VolumeMainEffects") ||
							Line.startsWith("VolumeBackgroundEffects") ||
							Line.startsWith("VolumeMainMusic") ||
							Line.startsWith("VolumeSpeech")) {
						SendVerboseMessage(graphicsSettingsFilter(10, Line.replace("VolumeLoginMusic", "Login_Music_Volume")
																		  .replace("VolumeMainMusic", "In-Game_Music_Volume")
																		  .replace("VolumeMainEffects", "Sound_Effects_Volume")
																		  .replace("VolumeBackgroundEffects", "Ambient_Sounds_Volume")
																		  .replace("VolumeSpeech", "Voice_Over_Volume")
								));
					} else if (Line.startsWith("Brightness")) {
						SendVerboseMessage(graphicsSettingsFilter(11, Line.replace("Brightness", "Brightness_Slider")));
					} else if (Line.startsWith("MaxForegroundFps") ||
							Line.startsWith("MaxBackgroundFps")) {
						SendVerboseMessage(graphicsSettingsFilter(-1, Line.replace("MaxForegroundFps", "Maximum_Foreground_FPS")
								.replace("MaxBackgroundFps", "Maximum_Background_FPS")));
					} else if (Line.startsWith("ConsoleKeyJavaStyle") ||
							Line.startsWith("ConsoleKeyPress") ||
							Line.startsWith("DiskCacheSize") ||
							Line.startsWith("AutoSetup") ||
							Line.startsWith("CameraOcclusion") ||
							Line.startsWith("ResizableResolution") ||
							Line.startsWith("AnimDetail") ||
							Line.startsWith("Water") ||
							Line.startsWith("TextureQuality")) {
						/* 
						 * These currently have no meaning for the intention of the program, so they will be ignored here.
						 */
					} else {
						SendVerboseMessage(graphicsSettingsFilter( - 1, Line.replace("DOF", "Depth_Of_Field")
																			.replace("HeatHaze", "Heat_Haze")
																			.replace("FullScreen", "Full_Screen_")));
					}
				}
				for (String Line: sb.toString().split("\n")) {
					if (Line.startsWith("CustomCursors")) {
						Storage.nxtGraphicsSetting_CustomCursors = Line.replace("CustomCursors ", "").equals("1");
						NXTSettingsGUI.CustomCursorsCheckbox.setSelected(Storage.nxtGraphicsSetting_CustomCursors);
					}
					else if (Line.startsWith("Shadows")) {
						Storage.nxtGraphicsSetting_Shadows = Line.replace("Shadows ", "").equals("1");
						NXTSettingsGUI.ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
					}
					else if (Line.startsWith("FlickeringEffects")) {
						Storage.nxtGraphicsSetting_FlickeringEffects = Line.replace("FlickeringEffects ", "").equals("1");
						NXTSettingsGUI.FlickeringEffectsCheckbox.setSelected(Storage.nxtGraphicsSetting_FlickeringEffects);
					}
					else if (Line.startsWith("GroundDecor")) {
						Storage.nxtGraphicsSetting_GroundDecor = Line.replace("GroundDecor ", "").equals("1");
						NXTSettingsGUI.GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
					}
					else if (Line.startsWith("GroundBlending")) {
						Storage.nxtGraphicsSetting_TerrainBlending = Line.replace("GroundBlending ", "").equals("1");
						NXTSettingsGUI.TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
					}
					else if (Line.startsWith("DrawDistance")) {
						Storage.nxtGraphicsSetting_DrawDistance = new Integer(Line.replace("DrawDistance ", ""));
						NXTSettingsGUI.DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
					}
					else if (Line.startsWith("ShadowQuality")) {
						Storage.nxtGraphicsSetting_ShadowQuality = new Integer(Line.replace("ShadowQuality ", ""));
						NXTSettingsGUI.ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
					}
					else if (Line.startsWith("LightingQuality")) {
						Storage.nxtGraphicsSetting_LightingQuality = new Integer(Line.replace("LightingQuality ", ""));
						NXTSettingsGUI.LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
					}
					else if (Line.startsWith("AntialiasingQuality")) {
						Storage.nxtGraphicsSetting_AntiAliasingQuality = new Integer(Line.replace("AntialiasingQuality ", ""));
						NXTSettingsGUI.AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
					}
					else if (Line.startsWith("Reflections")) {
						Storage.nxtGraphicsSetting_WaterQuality = new Integer(Line.replace("Reflections ", ""));
						NXTSettingsGUI.WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
					}
					else if (Line.startsWith("VolumetricLighting")) {
						Storage.nxtGraphicsSetting_VolumetricLighting = new Integer(Line.replace("VolumetricLighting ", ""));
						NXTSettingsGUI.VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
					}
					else if (Line.startsWith("Bloom")) {
						Storage.nxtGraphicsSetting_Bloom = new Integer(Line.replace("Bloom ", ""));
						NXTSettingsGUI.BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
					}
					else if (Line.startsWith("AmbientOcclusion")) {
						Storage.nxtGraphicsSetting_AmbientOcclusion = new Integer(Line.replace("AmbientOcclusion ", ""));
						NXTSettingsGUI.AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
					}
					else if (Line.startsWith("Texturing")) {
						Storage.nxtGraphicsSetting_TextureQuality = new Integer(Line.replace("Texturing ", ""));
						NXTSettingsGUI.TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
					}
					else if (Line.startsWith("AntialiasingMode")) {
						Storage.nxtGraphicsSetting_AntiAliasingMode = new Integer(Line.replace("AntialiasingMode ", ""));
						NXTSettingsGUI.AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
					}
					else if (Line.startsWith("AnisotropicFiltering")) {
						Storage.nxtGraphicsSetting_AnisotropicFiltering = new Integer(Line.replace("AnisotropicFiltering ", ""));
						NXTSettingsGUI.AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
					}
					else if (Line.startsWith("VSync")) {
						Storage.nxtGraphicsSetting_VSync = new Integer(Line.replace("VSync ", ""));
						NXTSettingsGUI.VSyncComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VSync);
					}
					else if (Line.startsWith("RemoveRoof")) {
						Storage.nxtGraphicsSetting_RemoveRoofs = new Integer(Line.replace("RemoveRoof ", ""));
						NXTSettingsGUI.RemoveRoofsComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_RemoveRoofs);
					}
					else if (Line.startsWith("Brightness")) {
						Storage.nxtGraphicsSetting_Brightness = new Integer(Line.replace("Brightness ", ""));
						NXTSettingsGUI.BrightnessSlider.setValue(Storage.nxtGraphicsSetting_Brightness);
					}
					else if (Line.startsWith("DOF")) {
						Storage.nxtGraphicsSetting_DepthOfField = new Integer(Line.replace("DOF ", ""));
						NXTSettingsGUI.DepthOfFieldComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DepthOfField);
					}
					else if (Line.startsWith("HeatHaze")) {
						Storage.nxtGraphicsSetting_HeatHaze = Line.replace("HeatHaze ", "").equals("1");
						NXTSettingsGUI.HeatHazeCheckbox.setSelected(Storage.nxtGraphicsSetting_HeatHaze);
					}
					else if (Line.startsWith("MaxForegroundFps")) {
						Storage.nxtGraphicsSetting_MaxForegroundFps = new Integer(Line.replace("MaxForegroundFps ", ""));
						if (Storage.nxtGraphicsSetting_MaxForegroundFps < 5){
							Storage.nxtGraphicsSetting_MaxForegroundFps = 5;
						} else if (Storage.nxtGraphicsSetting_MaxForegroundFps > 300){
							Storage.nxtGraphicsSetting_MaxForegroundFps = 300;
						}
						NXTSettingsGUI.MaxForegroundFpsInput.setText(""+Storage.nxtGraphicsSetting_MaxForegroundFps);
						if (NXTSettingsGUI.MaxForegroundFpsInput.getText().equals("") ||
							NXTSettingsGUI.MaxForegroundFpsInput.getText() == null){
							NXTSettingsGUI.MaxForegroundFpsInput.setText(""+(Storage.FrameRate + 10));
							Storage.nxtGraphicsSetting_MaxForegroundFps = Storage.FrameRate + 10;
						}
					}
					else if (Line.startsWith("MaxBackgroundFps")) {
						Storage.nxtGraphicsSetting_MaxBackgroundFps = new Integer(Line.replace("MaxBackgroundFps ", ""));
						if (Storage.nxtGraphicsSetting_MaxBackgroundFps < 5){
							Storage.nxtGraphicsSetting_MaxBackgroundFps = 5;
						} else if (Storage.nxtGraphicsSetting_MaxBackgroundFps > 300){
							Storage.nxtGraphicsSetting_MaxBackgroundFps = 300;
						}
						NXTSettingsGUI.MaxBackgroundFpsInput.setText(""+Storage.nxtGraphicsSetting_MaxBackgroundFps);
						if (NXTSettingsGUI.MaxBackgroundFpsInput.getText().equals("") ||
							NXTSettingsGUI.MaxBackgroundFpsInput.getText() == null){
							NXTSettingsGUI.MaxBackgroundFpsInput.setText("30");
							Storage.nxtGraphicsSetting_MaxBackgroundFps = 30;
						}
					}
					
					else if (Line.startsWith("GameRenderScale")) {
						Storage.nxtClientSettings_GameRenderScale = new Integer(Line.replace("GameRenderScale ", ""));
						if (Storage.nxtClientSettings_GameRenderScale < 33){
							Storage.nxtClientSettings_GameRenderScale = 33;
						} else if (Storage.nxtClientSettings_GameRenderScale > 200){
							Storage.nxtClientSettings_GameRenderScale = 200;
						}
						NXTSettingsGUI.GameRenderScaleInput.setText(""+Storage.nxtClientSettings_GameRenderScale);
						if (NXTSettingsGUI.GameRenderScaleInput.getText().equals("") ||
							NXTSettingsGUI.GameRenderScaleInput.getText() == null){
							NXTSettingsGUI.GameRenderScaleInput.setText("100");
							Storage.nxtClientSettings_GameRenderScale = 100;
						}
					}
					
					else if (Line.startsWith("InterfaceScale")) {
						Storage.nxtClientSettings_InterfaceScale = new Integer(Line.replace("InterfaceScale ", ""));
						if (Storage.nxtClientSettings_InterfaceScale < 100){
							Storage.nxtClientSettings_InterfaceScale = 100;
						} else if (Storage.nxtClientSettings_InterfaceScale > 400){
							Storage.nxtClientSettings_InterfaceScale = 400;
						}
						NXTSettingsGUI.InterfaceScaleInput.setText(""+Storage.nxtClientSettings_InterfaceScale);
						if (NXTSettingsGUI.InterfaceScaleInput.getText().equals("") ||
							NXTSettingsGUI.InterfaceScaleInput.getText() == null){
							NXTSettingsGUI.InterfaceScaleInput.setText("100");
							Storage.nxtClientSettings_InterfaceScale = 100;
						}
					}
					else if (Line.startsWith("VolumeLoginMusic")) {
						Storage.nxtClientSettings_LoginMusicVolume = new Integer(Line.replace("VolumeLoginMusic ", ""));
					}
					else {}
				}

				SendVerboseMessage("{{nl}}NXT's username+favourite world log:");
				try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Cache_settings_location);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM \"vt-varc\"")) {
					while (rs.next()) {
						if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME)) {
							if (NXTSettingsGUI.ShowSensitiveInformation.isSelected()) {
								SendVerboseMessage("\t" + "NXT's saved username was once: " + rs.getString("DATA"));
								NXTSettingsGUI.UsernameInput.setText(rs.getString("DATA"));
							} else {
								SendVerboseMessage("\t" + "NXT's saved username was once: (CENSORED)");
							}
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1)) {
							SendVerboseMessage("\tNXT's favourite world slot #1 was once: " + rs.getString("DATA"));
							NXTSettingsGUI.FavouriteWorld1Input.setText(rs.getString("DATA"));
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2)) {
							SendVerboseMessage("\tNXT's favourite world slot #2 was once: " + rs.getString("DATA"));
							NXTSettingsGUI.FavouriteWorld2Input.setText(rs.getString("DATA"));
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3)) {
							SendVerboseMessage("\tNXT's favourite world slot #3 was once: " + rs.getString("DATA"));
							NXTSettingsGUI.FavouriteWorld3Input.setText(rs.getString("DATA"));
						}
					}
					conn.close();
					stmt.close();
					rs.close();
				} catch(SQLException e) {
					SendVerboseMessage("\t" + "No history.{{nl}}");
				}

				try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Cache_settings_location);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM \"vt-varc\"")) {
					while (rs.next()) {
						if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_LOADING_SCREENS)) {
							Storage.nxtGraphicsSetting_LoadingScreens = rs.getString("DATA").equals("1");
							NXTSettingsGUI.LoadingScreensCheckbox.setSelected(Storage.nxtGraphicsSetting_LoadingScreens);
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER)) {
							Storage.nxtClientSettings_RandomizeLoginWallpaper = rs.getString("DATA").equals("1");
							NXTSettingsGUI.RandomizeLoginWallpaperCheckbox.setSelected(Storage.nxtClientSettings_RandomizeLoginWallpaper);
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VERC_WALLPAPER_ID)) {
							Storage.nxtClientSettings_LoginWallpaperID = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.WallpaperIDInput.setText("" + Storage.nxtClientSettings_LoginWallpaperID);
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_REMEMBER_USERNAME)) {
							Storage.nxtClientSettings_RememberUsername = rs.getString("DATA").equals("1");
							NXTSettingsGUI.RememberUsernameCheckbox.setSelected(Storage.nxtClientSettings_RememberUsername);
						}
					}
					Legality.CheckAudio();
					conn.close();
					stmt.close();
					rs.close();
				} catch(SQLException e) {
					SendVerboseMessage("\t" + "No history.{{nl}}");
				}

				SendVerboseMessage("{{nl}}NXT's Developer-Console log:");
				try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Cache_settings_location);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM \"console\"")) {
					while (rs.next()) {
						SendVerboseMessage("\t" + rs.getString("DATA"));
					}
					conn.close();
					stmt.close();
					rs.close();
				} catch(SQLException e) {
					SendVerboseMessage("\t" + "No history.{{nl}}");
				}
			} else {
				SendVerboseMessage("Cache settings file not found!");
			}

		} else {
			if (OS_TYPE == 0) {
				SendVerboseMessage("NXT was NOT found on the system via the registry path: HKEY_CURRENT_USER\\" + NXT_REGISTRY_LOCATION_BASE);
			} else {
				SendVerboseMessage("NXT was NOT found on the system via the path: " + NXT_REGISTRY_LOCATION_BASE);
			}
		}
		Legality.CheckSettings();
	}

	public static void TestWrite() {
		System.out.print("Saving updated values to: "+Cache_settings_location+"...");
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Cache_settings_location);
			Statement stmt;
			stmt = conn.createStatement();

			// Graphics Settings.
			Legality.CheckSettings();

			// Left Column
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_RemoveRoofs + "\" WHERE KEY=\"RemoveRoof\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_DrawDistance + "\" WHERE KEY=\"DrawDistance\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_ShadowQuality + "\" WHERE KEY=\"ShadowQuality\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_VSync + "\" WHERE KEY=\"VSync\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_AntiAliasingMode + "\" WHERE KEY=\"AntialiasingMode\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_AntiAliasingQuality + "\" WHERE KEY=\"AntialiasingQuality\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_WaterQuality + "\" WHERE KEY=\"Reflections\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_LightingQuality + "\" WHERE KEY=\"LightingQuality\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_AmbientOcclusion + "\" WHERE KEY=\"AmbientOcclusion\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_Bloom + "\" WHERE KEY=\"Bloom\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_DepthOfField + "\" WHERE KEY=\"DOF\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_MaxForegroundFps + "\" WHERE KEY=\"MaxForegroundFps\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtClientSettings_InterfaceScale + "\" WHERE KEY=\"InterfaceScale\";");

			// Right Column
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_Brightness + "\" WHERE KEY=\"Brightness\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_TextureQuality + "\" WHERE KEY=\"Texturing\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_AnisotropicFiltering + "\" WHERE KEY=\"AnisotropicFiltering\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_VolumetricLighting + "\" WHERE KEY=\"VolumetricLighting\";");

			if (Storage.nxtGraphicsSetting_FlickeringEffects) {
				stmt.addBatch("UPDATE \"Config\" SET DATA=\"1\" WHERE KEY=\"FlickeringEffects\";");
			} else {
				stmt.addBatch("UPDATE \"Config\" SET DATA=\"0\" WHERE KEY=\"FlickeringEffects\";");
			}
			if (Storage.nxtGraphicsSetting_Shadows) {
				stmt.addBatch("UPDATE \"Config\" SET DATA=\"1\" WHERE KEY=\"Shadows\";");
			} else {
				stmt.addBatch("UPDATE \"Config\" SET DATA=\"0\" WHERE KEY=\"Shadows\";");
			}
			if (Storage.nxtGraphicsSetting_CustomCursors) {
				stmt.addBatch("UPDATE \"Config\" SET DATA=\"1\" WHERE KEY=\"CustomCursors\";");
			} else {
				stmt.addBatch("UPDATE \"Config\" SET DATA=\"0\" WHERE KEY=\"CustomCursors\";");
			}
			if (Storage.nxtGraphicsSetting_LoadingScreens) {
				stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"1\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_LOADING_SCREENS+"\";");
			} else {
				stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"0\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_LOADING_SCREENS+"\";");
			}
			if (Storage.nxtGraphicsSetting_GroundDecor) {
				stmt.addBatch("UPDATE \"Config\" SET DATA=\"1\" WHERE KEY=\"GroundDecor\";");
			} else {
				stmt.addBatch("UPDATE \"Config\" SET DATA=\"0\" WHERE KEY=\"GroundDecor\";");
			}
			if (Storage.nxtGraphicsSetting_TerrainBlending) {
				stmt.addBatch("UPDATE \"Config\" SET DATA=\"1\" WHERE KEY=\"GroundBlending\";");
			} else {
				stmt.addBatch("UPDATE \"Config\" SET DATA=\"0\" WHERE KEY=\"GroundBlending\";");
			}
			if (Storage.nxtGraphicsSetting_HeatHaze) {
				stmt.addBatch("UPDATE \"Config\" SET DATA=\"1\" WHERE KEY=\"HeatHaze\";");
			} else {
				stmt.addBatch("UPDATE \"Config\" SET DATA=\"0\" WHERE KEY=\"HeatHaze\";");
			}
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtGraphicsSetting_MaxBackgroundFps + "\" WHERE KEY=\"MaxBackgroundFps\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtClientSettings_GameRenderScale + "\" WHERE KEY=\"GameRenderScale\";");

			// Volumes
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtClientSettings_LoginMusicVolume + "\" WHERE KEY=\"VolumeLoginMusic\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtClientSettings_InGameMusicVolume + "\" WHERE KEY=\"VolumeMainMusic\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtClientSettings_InGameSoundEffectsVolume + "\" WHERE KEY=\"VolumeMainEffects\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume + "\" WHERE KEY=\"VolumeBackgroundEffects\";");
			stmt.addBatch("UPDATE \"Config\" SET DATA=\"" + Storage.nxtClientSettings_InGameVoiceOverVolume + "\" WHERE KEY=\"VolumeSpeech\";");
			stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"" + Storage.nxtClientSettings_InGameMusicVolume + "\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_IN_GAME_MUSIC_VOLUME+"\";");
			stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"" + Storage.nxtClientSettings_InGameSoundEffectsVolume + "\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_IN_GAME_SOUND_EFFECTS_VOLUME+"\";");
			stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"" + Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume + "\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_IN_GAME_AMBIENT_EFFECTS_VOLUME+"\";");
			stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"" + Storage.nxtClientSettings_InGameVoiceOverVolume + "\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_IN_GAME_VOICE_OVER_VOLUME+"\";");
			if (Storage.nxtClientSettings_GlobalMute) {
				stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"1\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_GLOBAL_AUDIO_MUTE+"\";");
			} else {
				stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"0\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_GLOBAL_AUDIO_MUTE+"\";");
			}

			// Player Info;
			Legality.CheckSettingsBeforeSave();
			if (Storage.nxtClientSettings_RememberUsername &&
					Storage.nxtClientSettings_TemporaryUsername != null &&
					!Storage.nxtClientSettings_TemporaryUsername.equals("")) {
				stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"" + Storage.nxtClientSettings_TemporaryUsername + "\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME+"\";");
				stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"1\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_REMEMBER_USERNAME+"\";");
			} else {
				// Redundancy check, No remember = no set. (it would clear on-load anyways if username field was set)
				stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME+"\";");
				stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"0\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_REMEMBER_USERNAME+"\";");
			}
			Storage.nxtClientSettings_TemporaryUsername = "";
			/* Clears some base-less paranoia if any for this value */

			stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"" + Storage.nxtClientSettings_FavouriteWorld1 + "\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1+"\";");
			stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"" + Storage.nxtClientSettings_FavouriteWorld2 + "\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2+"\";");
			stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"" + Storage.nxtClientSettings_FavouriteWorld3 + "\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3+"\";");

			// Wallpaper related;
			stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"" + Storage.nxtClientSettings_LoginWallpaperID + "\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VERC_WALLPAPER_ID+"\";");
			if (Storage.nxtClientSettings_RandomizeLoginWallpaper) {
				stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"1\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER+"\";");
			} else {
				stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"0\" WHERE KEY=\""+Storage.CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER+"\";");
			}

			// Execute/Save the changes.
			stmt.executeBatch();
			System.out.print(" Saved.\n");
			JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Saved updated values to:\n\n"+Cache_settings_location);

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}