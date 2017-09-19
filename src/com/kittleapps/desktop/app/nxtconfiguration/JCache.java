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

import com.sun.jna.platform.win32.Advapi32Util;

public class JCache {

	private static StringBuilder sb;
	private static BufferedReader reader;
	private static String current_line;

	public static void Read() {
		/*
		 * This will run Twice initially at the start of the program, to force all values to change properly
		 *
		 * > Check which Operating System the user has currently
		 *  > If failed, tell user their OS is known at the time, Abort the program
		 *
		 * > Clear Verbose Output
		 *
		 * > Check system if NXT is installed
		 *  > If failed, Abort the program as they don't have NXT installed to begin with
		 *
		 * > Get the Configuration file's path
		 *  > If failed, tell user the configuration at {$Storage.configuration_location} is non-existant. Abort the program
		 *
		 * > Get the splash screen+launcher positions
		 *  > Set+Output the values
		 *
		 * > Read the preference file
		 *  > Set+Output the values
		 *
		 * > Start the Sqlite3 drivers
		 *
		 * > Start reading Settings.jcache
		 *  > Add to a StringBuilder for a seperate function
		 *  > Output the values, Parsed where needed
		 *
		 * > Read the StringBuilder values
		 *  > Set the Storage flags to the read values
		 *  > Set some fields prior to legality checks
		 *
		 * > Start legality checks
		 */
		final int OS_TYPE = Storage.OS_TYPE;
		NXTSettingsGUI.VerboseOutputArea.setText("");
		Storage.messages = new StringBuilder("<html>");

		if (OS_TYPE == 0) {
			Storage.NXT_REGISTRY_LOCATION_BASE = "Software\\Jagex\\RuneScape";
			Storage.NXT_INSTALLED = Advapi32Util.registryKeyExists(HKEY_CURRENT_USER, Storage.NXT_REGISTRY_LOCATION_BASE);
			Mechanics.SendVerboseMessage("NXT's installed: " + ("" + Storage.NXT_INSTALLED).replace("true", "Yes.")
																		 .replace("false", "No."));
		} else if (OS_TYPE == 1) {
			Storage.NXT_REGISTRY_LOCATION_BASE = "/usr/bin/runescape-launcher";
			Storage.NXT_INSTALLED = new File(Storage.NXT_REGISTRY_LOCATION_BASE).exists();
			Mechanics.SendVerboseMessage("NXT's installed: " + ("" + Storage.NXT_INSTALLED).replace("true", "Yes.")
																		 .replace("false", "No."));
		}
		if (Storage.NXT_INSTALLED) {
			if (OS_TYPE == 0) {
				Storage.configuration_location = Advapi32Util.registryGetStringValue(HKEY_CURRENT_USER, Storage.NXT_REGISTRY_LOCATION_BASE, "splash").replace("splash6.gif", "preferences.cfg").replace("\\", "/");
				Storage.splash_x_position = Advapi32Util.registryGetIntValue(HKEY_CURRENT_USER, Storage.NXT_REGISTRY_LOCATION_BASE + "\\Persistent_Options\\LauncherSplash\\Splash", "x");
				Storage.splash_y_position = Advapi32Util.registryGetIntValue(HKEY_CURRENT_USER, Storage.NXT_REGISTRY_LOCATION_BASE + "\\Persistent_Options\\LauncherSplash\\Splash", "y");
				Storage.launcher_client_position = Advapi32Util.registryGetStringValue(HKEY_CURRENT_USER, Storage.NXT_REGISTRY_LOCATION_BASE, "client_position");
				Mechanics.SendVerboseMessage("NXT's registry path: HKEY_CURRENT_USER\\" + Storage.NXT_REGISTRY_LOCATION_BASE);
				Mechanics.SendVerboseMessage("NXT's configuration file is located at: " + Storage.configuration_location);
				Mechanics.SendVerboseMessage("NXT's splash screen X-coordinate is set to: " + Storage.splash_x_position);
				Mechanics.SendVerboseMessage("NXT's splash screen Y-coordinate is set to: " + Storage.splash_y_position);
				Mechanics.SendVerboseMessage("NXT's client position is set to: " + Storage.launcher_client_position);
			} else if (OS_TYPE >= 1 && OS_TYPE <= 3) {
				Storage.configuration_location = new File(System.getProperty("user.home").replace("\\", "/") + "/Jagex/launcher/preferences.cfg").getAbsolutePath();
				try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home").replace("\\", "/") + "/.runescape"))) {
					String line;
					while ((line = br.readLine()) != null) {
						if (line.startsWith("client_position")) {
							Storage.launcher_client_position = line.trim().replace("client_position=", "");
						} else if (line.startsWith("x")) {
							Storage.splash_x_position = new Integer(line.trim().replace("x=", ""));
						} else if (line.startsWith("y")) {
							Storage.splash_y_position = new Integer(line.trim().replace("y=", ""));
						}
					}

				} catch(final IOException e) {
					e.printStackTrace();
				}
			}
			else {
				Mechanics.SendVerboseMessage("Error: Unknown OS Value: "+Storage.OS);
				System.out.println("Error: Unknown OS Value: "+Storage.OS);
				JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Error: Unknown OS Value: "+Storage.OS+"\n\nSettings will not be read, Aborting program functions.");
				System.exit(0);
			}


			Storage.Cache_settings_location = "";
			Storage.preferences_config = new File(Storage.configuration_location);


			if (Storage.preferences_config.exists()) {
				try {
					reader = new BufferedReader(new FileReader(Storage.preferences_config));
					current_line = "";
					while ((current_line = reader.readLine()) != null) {
						if (current_line.startsWith("graphics_device=")) {
							Mechanics.SendVerboseMessage("NXT's graphics device is currently set to: " + current_line.trim().replace("graphics_device=", ""));
						}
						if (current_line.startsWith("compatibility=")) {
							final String CompatMode = current_line.trim()
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
							Mechanics.SendVerboseMessage("NXT's compatibility mode is currently: " + CompatMode);
						}
						if (current_line.startsWith("dont_ask_graphics=")) {
							Mechanics.SendVerboseMessage("NXT will currently" +
												current_line.trim().replace("dont_ask_graphics=", "")
																   .replace("1", " NOT ")
																   .replace("0", " ") +
											   "ask to switch to compatibility mode on-error."
									);
						}
						if (current_line.startsWith("confirm_quit=")) {
							Mechanics.SendVerboseMessage("NXT will currently" +
												current_line.trim()
															.replace("confirm_quit=", "")
															.replace("0", " NOT ")
															.replace("1", " ") +
												"ask if it's okay to close the program while logged in."
									);
						}
						if (current_line.startsWith("graphics_version=")) {
							Mechanics.SendVerboseMessage("NXT's last read graphics driver version was: " + current_line.trim().replace("graphics_version=", ""));
						}
						if (current_line.startsWith("cache_folder=")) {
							if (OS_TYPE == 0) {
								Mechanics.SendVerboseMessage("NXT's cache is located at: " + current_line.trim().replace("cache_folder=", "").replace("\\", "/") + "/RuneScape");
							} else if (OS_TYPE == 1) {
								Mechanics.SendVerboseMessage("NXT's cache is located at: " + current_line.trim().replace("cache_folder=", "").replace("\\", "/") + "/RuneScape");
							}
						}
						if (current_line.startsWith("Language=")) {
							Mechanics.SendVerboseMessage("NXT's server language is currently set to: " +
									current_line.trim()
												.replace("Language=", "")
												.replace("0", "English (English/Standard Servers)")
												.replace("1", "Deutsch (Dutch Servers)")
												.replace("2", "Fran�ais (French Servers)")
												.replace("3", "portugu� (Portuguese/Brazilian Servers)")
									);
						}
						if (current_line.startsWith("user_folder=")) {
							Storage.Cache_settings_location = current_line.trim().replace("user_folder=", "").replace("\\", "/") + "/RuneScape/Settings.jcache";
							Mechanics.SendVerboseMessage("NXT's standard cache/graphics settings are located at: " + Storage.Cache_settings_location);
						}
					}
					reader.close();
				} catch(final IOException e) {
					e.printStackTrace();
				}
			} else {
				Mechanics.SendVerboseMessage("The configuration file does NOT exist at: " + Storage.configuration_location);
				JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Error: The configuration file does NOT exist at: " + Storage.configuration_location+"\n\nAborting the program's functioning.");
				System.exit(0);
			}

			Storage.Settings_db = new File(Storage.Cache_settings_location);
			sb = new StringBuilder("");

			if (Storage.Settings_db.isFile()) {
				try {
					Class.forName("org.sqlite.JDBC");
				} catch(final ClassNotFoundException eString) {
					System.err.println("Could not init JDBC driver - driver not found");
				}
				try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM Config")) {
					while (rs.next()) {
						sb.append(rs.getString("KEY").trim() + " " + rs.getString("DATA").trim() + "\n");
					}
					conn.close();
					stmt.close();
					rs.close();
				} catch(final SQLException e) {
					System.err.println(e.getMessage());
				}
				try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM 'player'")) {
					while (rs.next()) {
						if (rs.getString("KEY").equals("uid")) {
							if (NXTSettingsGUI.ShowSensitiveInformation.isSelected()) {
								Mechanics.SendVerboseMessage("NXT's current uid is: " + rs.getString("DATA") + "{{nl}}");
							} else {
								Mechanics.SendVerboseMessage("NXT's current uid is: (CENSORED){{nl}}");
							}
						}
					}
					conn.close();
					stmt.close();
					rs.close();
				} catch(final SQLException e) {
					Mechanics.SendVerboseMessage("\t" + "No settings found.{{nl}}");
				}

				Mechanics.SendVerboseMessage("NXT's current graphics settings are currently:");
				for (final String Line: sb.toString().split("\n")) {
					if (Line.startsWith("CustomCursors") ||
							Line.startsWith("Shadows") ||
							Line.startsWith("FlickeringEffects") ||
							Line.startsWith("GroundDecor") ||
							Line.startsWith("GroundBlending")) {
						Mechanics.SendVerboseMessage(Mechanics.graphicsSettingsFilter(0, Line.replace("CustomCursors", "Custom_Cursors")
																		 .replace("FlickeringEffects", "Flickering_Effects")
																		 .replace("GroundDecor", "Ground_Decororations")
																		 .replace("GroundBlending", "Terrain_Blending")
								));
					} else if (Line.startsWith("DrawDistance") ||
							Line.startsWith("ShadowQuality") ||
							Line.startsWith("LightingQuality") ||
							Line.startsWith("AntialiasingQuality") ||
							Line.startsWith("Reflections")) {
						Mechanics.SendVerboseMessage(Mechanics.graphicsSettingsFilter(1, Line.replace("DrawDistance", "Draw_Distance")
																	 	 .replace("ShadowQuality", "Shadow_Quality")
																		 .replace("LightingQuality", "Lighting_Quality")
																		 .replace("AntialiasingQuality", "Anti-aliasing_Quality")
																		 .replace("Reflections", "Water_Detail")
								));
					} else if (Line.startsWith("VolumetricLighting") ||
							Line.startsWith("Bloom")) {
						Mechanics.SendVerboseMessage(Mechanics.graphicsSettingsFilter(2, Line.replace("VolumetricLighting", "Volumetric_Lighting_Quality")
								.replace("Bloom", "Bloom_Quality")));
					} else if (Line.startsWith("AmbientOcclusion")) {
						Mechanics.SendVerboseMessage(Mechanics.graphicsSettingsFilter(3, Line.replace("AmbientOcclusion", "Ambient_Occlusion")));
					} else if (Line.startsWith("Texturing")) {
						Mechanics.SendVerboseMessage(Mechanics.graphicsSettingsFilter(4, Line.replace("Texturing", "Texture_Quality")));
					} else if (Line.startsWith("AntialiasingMode")) {
						Mechanics.SendVerboseMessage(Mechanics.graphicsSettingsFilter(5, Line.replace("AntialiasingMode", "Anti-aliasing_Mode")));
					} else if (Line.startsWith("AnisotropicFiltering")) {
						Mechanics.SendVerboseMessage(Mechanics.graphicsSettingsFilter(6, Line.replace("AnisotropicFiltering", "Anisotropic_Filtering")));
					} else if (Line.startsWith("VSync")) {
						Mechanics.SendVerboseMessage(Mechanics.graphicsSettingsFilter(7, Line));
					} else if (Line.startsWith("RemoveRoof")) {
						Mechanics.SendVerboseMessage(Mechanics.graphicsSettingsFilter(8, Line.replace("RemoveRoof", "Remove_Roofs")));
					} else if (Line.startsWith("Version")) {
						NXTSettingsGUI.frame.setTitle("NXT Settings (Settings Version: "+Line.replace("Version ", "")+")");
					} else if (Line.startsWith("VolumeLoginMusic") ||
							Line.startsWith("VolumeMainEffects") ||
							Line.startsWith("VolumeBackgroundEffects") ||
							Line.startsWith("VolumeMainMusic") ||
							Line.startsWith("VolumeSpeech")) {
						Mechanics.SendVerboseMessage(Mechanics.graphicsSettingsFilter(10, Line.replace("VolumeLoginMusic", "Login_Music_Volume")
																		  .replace("VolumeMainMusic", "In-Game_Music_Volume")
																		  .replace("VolumeMainEffects", "Sound_Effects_Volume")
																		  .replace("VolumeBackgroundEffects", "Ambient_Sounds_Volume")
																		  .replace("VolumeSpeech", "Voice_Over_Volume")
								));
					} else if (Line.startsWith("Brightness")) {
						Mechanics.SendVerboseMessage(Mechanics.graphicsSettingsFilter(11, Line.replace("Brightness", "Brightness_Slider")));
					} else if (Line.startsWith("MaxForegroundFps") ||
							Line.startsWith("MaxBackgroundFps") ||
							Line.startsWith("InterfaceScale") ||
							Line.startsWith("GameRenderScale")) {
						Mechanics.SendVerboseMessage(Mechanics.graphicsSettingsFilter(-1, Line.replace("MaxForegroundFps", "Maximum_Foreground_FPS")
																		  .replace("MaxForegroundFps", "Maximum_Foreground_FPS")
																		  .replace("InterfaceScale", "Interface_Scale")
																		  .replace("GameRenderScale", "Game_Render_Scale")
								));
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
						Mechanics.SendVerboseMessage(Mechanics.graphicsSettingsFilter( - 1, Line.replace("DOF", "Depth_Of_Field")
																			.replace("HeatHaze", "Heat_Haze")
																			.replace("FullScreen", "Full_Screen_")));
					}
				}
				for (final String Line: sb.toString().split("\n")) {
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

				Mechanics.SendVerboseMessage("{{nl}}NXT's username+favourite world log:");
				try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM 'vt-varc'")) {
					while (rs.next()) {
						if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME)) {
							if (NXTSettingsGUI.ShowSensitiveInformation.isSelected()) {
								Mechanics.SendVerboseMessage("\t" + "NXT's saved username was once: " + rs.getString("DATA"));
								NXTSettingsGUI.UsernameInput.setText(rs.getString("DATA"));
							} else {
								Mechanics.SendVerboseMessage("\t" + "NXT's saved username was once: (CENSORED)");
							}
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1)) {
							Mechanics.SendVerboseMessage("\tNXT's favourite world slot #1 was once: " + rs.getString("DATA"));
							NXTSettingsGUI.FavouriteWorld1Input.setText(rs.getString("DATA"));
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2)) {
							Mechanics.SendVerboseMessage("\tNXT's favourite world slot #2 was once: " + rs.getString("DATA"));
							NXTSettingsGUI.FavouriteWorld2Input.setText(rs.getString("DATA"));
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3)) {
							Mechanics.SendVerboseMessage("\tNXT's favourite world slot #3 was once: " + rs.getString("DATA"));
							NXTSettingsGUI.FavouriteWorld3Input.setText(rs.getString("DATA"));
						}
					}
					conn.close();
					stmt.close();
					rs.close();
				} catch(final SQLException e) {
					Mechanics.SendVerboseMessage("\t" + "No history.{{nl}}");
				}

				try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM 'vt-varc'")) {
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
				} catch(final SQLException e) {
					Mechanics.SendVerboseMessage("\t" + "No history.{{nl}}");
				}

				Mechanics.SendVerboseMessage("{{nl}}NXT's Developer-Console log:");
				try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM 'console'")) {
					while (rs.next()) {
						Mechanics.SendVerboseMessage("\t" + rs.getString("DATA"));
					}
					conn.close();
					stmt.close();
					rs.close();
				} catch(final SQLException e) {
					Mechanics.SendVerboseMessage("\t" + "No history.{{nl}}");
				}
			} else {
				Mechanics.SendVerboseMessage("Cache settings file not found!");
			}

		} else {
			if (OS_TYPE == 0) {
				Mechanics.SendVerboseMessage("NXT was NOT found on the system via the registry path: HKEY_CURRENT_USER\\" + Storage.NXT_REGISTRY_LOCATION_BASE);
			} else {
				Mechanics.SendVerboseMessage("NXT was NOT found on the system via the path: " + Storage.NXT_REGISTRY_LOCATION_BASE);
			}
		}
		Legality.CheckSettings();
	}

	public static void Write() {
		/*
		 * > Output where the Settings.jcache file will be saved to
		 *
		 * > Initialize the Sqlite3 database
		 *  > Set it to edit Settings.jcache
		 *
		 * > Start a Legality check
		 *
		 * > Start adding batches to delete older entries
		 *
		 * > Start adding batches to insert updated entries
		 *
		 * > Repeat the last two until finished for all values
		 *  > Boolean-type entries will be manually 1 <> 0 dependant on their value related to true <> false
		 *  > Legality checks will take place before username+favourite world settings to prevent invalid values
		 *  > Temporary username field will be cleared to prevent any paranoia post-saving
		 *
		 * > Execute batches (write to the database)
		 *
		 * > Output that the files were saved, in a dialogue, with the path to the file
		 */
		System.out.print("Saving updated values to: "+Storage.Cache_settings_location+"...");
		try {
			final Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
			Statement stmt;
			stmt = conn.createStatement();
			Legality.CheckSettings();

			// Left Column
			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='RemoveRoof';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('RemoveRoof', '" + Storage.nxtGraphicsSetting_RemoveRoofs + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='DrawDistance';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('DrawDistance', '" + Storage.nxtGraphicsSetting_DrawDistance + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='ShadowQuality';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('ShadowQuality', '" + Storage.nxtGraphicsSetting_ShadowQuality + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='VSync';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('VSync', '" + Storage.nxtGraphicsSetting_VSync + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='AntialiasingMode';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('AntialiasingMode', '" + Storage.nxtGraphicsSetting_AntiAliasingMode + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='AntialiasingQuality';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('AntialiasingQuality', '" + Storage.nxtGraphicsSetting_AntiAliasingQuality + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='Reflections';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('Reflections', '" + Storage.nxtGraphicsSetting_WaterQuality + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='LightingQuality';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('LightingQuality', '" + Storage.nxtGraphicsSetting_LightingQuality + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='AmbientOcclusion';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('AmbientOcclusion', '" + Storage.nxtGraphicsSetting_AmbientOcclusion + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='Bloom';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('Bloom', '" + Storage.nxtGraphicsSetting_Bloom + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='DOF';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('DOF', '" + Storage.nxtGraphicsSetting_DepthOfField + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='MaxForegroundFps';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('MaxForegroundFps', '" + Storage.nxtGraphicsSetting_MaxForegroundFps + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='InterfaceScale';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('InterfaceScale', '" + Storage.nxtClientSettings_InterfaceScale + "');");


			// Right Column
			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='Brightness';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('Brightness', '" + Storage.nxtGraphicsSetting_Brightness + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='Texturing';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('Texturing', '" + Storage.nxtGraphicsSetting_TextureQuality + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='AnisotropicFiltering';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('AnisotropicFiltering', '" + Storage.nxtGraphicsSetting_AnisotropicFiltering + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='VolumetricLighting';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('VolumetricLighting', '" + Storage.nxtGraphicsSetting_VolumetricLighting + "');");

			if (Storage.nxtGraphicsSetting_FlickeringEffects) {
				stmt.addBatch("DELETE FROM 'Config' WHERE KEY='FlickeringEffects';");
				stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('FlickeringEffects', '1');");
			} else {
				stmt.addBatch("DELETE FROM 'Config' WHERE KEY='FlickeringEffects';");
				stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('FlickeringEffects', '0');");
			}
			if (Storage.nxtGraphicsSetting_Shadows) {
				stmt.addBatch("DELETE FROM 'Config' WHERE KEY='Shadows';");
				stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('Shadows', '1');");
			} else {
				stmt.addBatch("DELETE FROM 'Config' WHERE KEY='Shadows';");
				stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('Shadows', '0');");
			}
			if (Storage.nxtGraphicsSetting_CustomCursors) {
				stmt.addBatch("DELETE FROM 'Config' WHERE KEY='CustomCursors';");
				stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('CustomCursors', '1');");
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_CUSTOM_CURSORS+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_CUSTOM_CURSORS+"', '1');");
			} else {
				stmt.addBatch("DELETE FROM 'Config' WHERE KEY='CustomCursors';");
				stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('CustomCursors', '0');");
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_CUSTOM_CURSORS+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_CUSTOM_CURSORS+"', '0');");
			}
			if (Storage.nxtGraphicsSetting_LoadingScreens) {
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_LOADING_SCREENS+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_LOADING_SCREENS+"', '1');");
			} else {
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_LOADING_SCREENS+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_LOADING_SCREENS+"', '0');");
			}
			if (Storage.nxtGraphicsSetting_GroundDecor) {
				stmt.addBatch("DELETE FROM 'Config' WHERE KEY='GroundDecor';");
				stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('GroundDecor', '1');");
			} else {
				stmt.addBatch("DELETE FROM 'Config' WHERE KEY='GroundDecor';");
				stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('GroundDecor', '0');");
			}
			if (Storage.nxtGraphicsSetting_TerrainBlending) {
				stmt.addBatch("DELETE FROM 'Config' WHERE KEY='GroundBlending';");
				stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('GroundBlending', '1');");
			} else {
				stmt.addBatch("DELETE FROM 'Config' WHERE KEY='GroundBlending';");
				stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('GroundBlending', '0');");
			}
			if (Storage.nxtGraphicsSetting_HeatHaze) {
				stmt.addBatch("DELETE FROM 'Config' WHERE KEY='HeatHaze';");
				stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('HeatHaze', '1');");
			} else {
				stmt.addBatch("DELETE FROM 'Config' WHERE KEY='HeatHaze';");
				stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('HeatHaze', '0');");
			}

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='MaxBackgroundFps';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('MaxBackgroundFps', '" + Storage.nxtGraphicsSetting_MaxBackgroundFps + "');");

			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='GameRenderScale';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('GameRenderScale', '" + Storage.nxtClientSettings_GameRenderScale + "');");

			// Volumes
			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='VolumeLoginMusic';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('VolumeLoginMusic', '" + Storage.nxtClientSettings_LoginMusicVolume + "');");

			stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_IN_GAME_MUSIC_VOLUME+"';");
			stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_IN_GAME_MUSIC_VOLUME+"', '" + Storage.nxtClientSettings_InGameMusicVolume + "');");
			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='VolumeMainMusic';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('VolumeMainMusic', '" + Storage.nxtClientSettings_InGameMusicVolume + "');");

			stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_IN_GAME_SOUND_EFFECTS_VOLUME+"';");
			stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_IN_GAME_SOUND_EFFECTS_VOLUME+"', '" + Storage.nxtClientSettings_InGameSoundEffectsVolume + "');");
			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='VolumeMainEffects';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('VolumeMainEffects', '" + Storage.nxtClientSettings_InGameSoundEffectsVolume + "');");

			stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_IN_GAME_AMBIENT_EFFECTS_VOLUME+"';");
			stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_IN_GAME_AMBIENT_EFFECTS_VOLUME+"', '" + Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume + "');");
			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='VolumeBackgroundEffects';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('VolumeBackgroundEffects', '" + Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume + "');");

			stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_IN_GAME_VOICE_OVER_VOLUME+"';");
			stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_IN_GAME_VOICE_OVER_VOLUME+"', '" + Storage.nxtClientSettings_InGameVoiceOverVolume + "');");
			stmt.addBatch("DELETE FROM 'Config' WHERE KEY='VolumeSpeech';");
			stmt.addBatch("INSERT INTO 'Config' ('KEY', 'DATA') VALUES ('VolumeSpeech', '" + Storage.nxtClientSettings_InGameVoiceOverVolume + "');");

			if (Storage.nxtClientSettings_GlobalMute) {
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_GLOBAL_AUDIO_MUTE+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_GLOBAL_AUDIO_MUTE+"', '1');");
			} else {
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_GLOBAL_AUDIO_MUTE+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_GLOBAL_AUDIO_MUTE+"', '0');");
			}

			// Username
			Legality.CheckSettingsBeforeSave();
			if (Storage.nxtClientSettings_RememberUsername &&
				Storage.nxtClientSettings_TemporaryUsername != null &&
			   !Storage.nxtClientSettings_TemporaryUsername.equals("")) {
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME+"', '" + Storage.nxtClientSettings_TemporaryUsername + "');");
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_REMEMBER_USERNAME+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_REMEMBER_USERNAME+"', '1');");
			} else {
				// Redundancy check, don't remember = no username is set. (it would clear on-load even if username field was set)
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME+"', '');");
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_REMEMBER_USERNAME+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_REMEMBER_USERNAME+"', '0');");
			}
			// Clears some base-less paranoia if any for this value
			Storage.nxtClientSettings_TemporaryUsername = "";

			// Favourite Worlds
			stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1+"';");
			stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1+"', '" + Storage.nxtClientSettings_FavouriteWorld1 + "');");

			stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2+"';");
			stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2+"', '" + Storage.nxtClientSettings_FavouriteWorld2 + "');");

			stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3+"';");
			stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3+"', '" + Storage.nxtClientSettings_FavouriteWorld3 + "');");

			// Wallpapers
			stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VERC_WALLPAPER_ID+"';");
			stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VERC_WALLPAPER_ID+"', '" + Storage.nxtClientSettings_LoginWallpaperID + "');");

			if (Storage.nxtClientSettings_RandomizeLoginWallpaper) {
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER+"', '1');");
			} else {
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER+"', '0');");
			}

			// Execute/Save the changes
			stmt.executeBatch();
			System.out.print(" Saved.\n");
			JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Saved updated values to:\n\n"+Storage.Cache_settings_location);

		} catch(final SQLException e) {
			e.printStackTrace();
		}
	}
}
