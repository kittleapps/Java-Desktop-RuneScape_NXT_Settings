package com.kittleapps.desktop.app.nxtsettings;

import static com.sun.jna.platform.win32.WinReg.HKEY_CURRENT_USER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.sun.jna.platform.win32.Advapi32Util;

public class JCache {

	private static BufferedReader reader;
	private static String current_line;

	public static void Read() {
		/*
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
		Storage.messages = new StringBuilder("<html>");

		if (OS_TYPE == 0) {
			Storage.NXT_REGISTRY_LOCATION_BASE = "Software\\Jagex\\RuneScape";
			Storage.NXT_INSTALLED = Advapi32Util.registryKeyExists(HKEY_CURRENT_USER, Storage.NXT_REGISTRY_LOCATION_BASE);
		} else if (OS_TYPE == 1) {
			Storage.NXT_REGISTRY_LOCATION_BASE = "/usr/bin/runescape-launcher";
			Storage.NXT_INSTALLED = new File(Storage.NXT_REGISTRY_LOCATION_BASE).exists();
		}
		if (Storage.NXT_INSTALLED) {
			if (OS_TYPE == 0) {
				Storage.configuration_location = Advapi32Util.registryGetStringValue(HKEY_CURRENT_USER, Storage.NXT_REGISTRY_LOCATION_BASE, "splash")
															 .replace("splash6.gif", "preferences.cfg")
															 .replace("\\", "/");
			} else if (OS_TYPE >= 1 && OS_TYPE <= 3) {
				Storage.configuration_location = new File(System.getProperty("user.home").replace("\\", "/") +
														 "/Jagex/launcher/preferences.cfg").getAbsolutePath();
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
				System.out.println("Error: Unknown OS Value: "+Storage.OS);
				JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Error: Unknown OS Value: " + Storage.OS+
																	"\n\n"+
																	"Settings will not be read, Aborting program functions.");
				System.exit(0);
			}


			Storage.Cache_settings_location = "";
			Storage.preferences_config = new File(Storage.configuration_location);


			if (Storage.preferences_config.exists()) {
				try {
					reader = new BufferedReader(new FileReader(Storage.preferences_config));
					current_line = "";
					while ((current_line = reader.readLine()) != null) {
						if (current_line.startsWith("compatibility=")) {
							final String CompatMode = current_line.trim()
																  .replace("compatibility=", "")
																  .replace("true", "On.")
																  .replace("false", "Off.")
																  .replace("default", "Automatic.");
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
						}
						if (current_line.startsWith("dont_ask_graphics=")) {
							Storage.nxtClientSettings_AskToSwitchToCompatibility = current_line.trim()
																								.replace("dont_ask_graphics=", "")
																								.equals("1");
						}
						if (current_line.startsWith("confirm_quit=")) {
							Storage.nxtClientSettings_ConfirmQuit = current_line.trim()
																				.replace("confirm_quit=", "")
																				.equals("1");
						}
						if (current_line.startsWith("user_folder=")) {
							Storage.Cache_settings_location = current_line.trim()
																		  .replace("user_folder=", "")
																		  .replace("\\", "/") +
																		  "/RuneScape/Settings.jcache";
						}
					}
					reader.close();
				} catch(final IOException e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Error: The configuration file does NOT exist at: " + Storage.configuration_location +
																	"\n\n"+
																	"Aborting the program's functioning.");
				System.exit(0);
			}


			Storage.Settings_db = new File(Storage.Cache_settings_location);

			if (Storage.Settings_db.isFile() && Storage.Settings_db.exists()) {
				try {
					Class.forName("org.sqlite.JDBC");
				} catch(final ClassNotFoundException eString) {
					System.err.println("Could not init JDBC driver - driver not found");
					JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Could not init JDBC driver - driver not found"+
																		"\n\n"+
																		"Settings can not be read, Aborting the program's functioning.");
				}
				try{
					Storage.conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
					Storage.stmt = Storage.conn.createStatement();
				} catch(final SQLException e) {
					System.err.println(e.getMessage());
				}
				try(ResultSet rs = Storage.stmt.executeQuery("SELECT * FROM Config")) {
					while (rs.next()) {
						if (rs.getString("KEY").equals("CustomCursors")) {
							Storage.nxtGraphicsSetting_CustomCursors = rs.getString("DATA").equals("1");
							NXTSettingsGUI.CustomCursorsCheckbox.setSelected(Storage.nxtGraphicsSetting_CustomCursors);
						}
						else if (rs.getString("KEY").equals("Shadows")) {
							Storage.nxtGraphicsSetting_Shadows = rs.getString("DATA").equals("1");
							NXTSettingsGUI.ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
						}
						else if (rs.getString("KEY").equals("FlickeringEffects")) {
							Storage.nxtGraphicsSetting_FlickeringEffects = rs.getString("DATA").equals("1");
							NXTSettingsGUI.FlickeringEffectsCheckbox.setSelected(Storage.nxtGraphicsSetting_FlickeringEffects);
						}
						else if (rs.getString("KEY").equals("GroundDecor")) {
							Storage.nxtGraphicsSetting_GroundDecor = rs.getString("DATA").equals("1");
							NXTSettingsGUI.GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
						}
						else if (rs.getString("KEY").equals("GroundBlending")) {
							Storage.nxtGraphicsSetting_TerrainBlending = rs.getString("DATA").equals("1");
							NXTSettingsGUI.TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
						}
						else if (rs.getString("KEY").equals("DrawDistance")) {
							Storage.nxtGraphicsSetting_DrawDistance = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
						}
						else if (rs.getString("KEY").equals("ShadowQuality")) {
							Storage.nxtGraphicsSetting_ShadowQuality = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
						}
						else if (rs.getString("KEY").equals("LightingQuality")) {
							Storage.nxtGraphicsSetting_LightingQuality = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
						}
						else if (rs.getString("KEY").equals("AntialiasingQuality")) {
							Storage.nxtGraphicsSetting_AntiAliasingQuality = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
						}
						else if (rs.getString("KEY").equals("Reflections")) {
							Storage.nxtGraphicsSetting_WaterQuality = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
						}
						else if (rs.getString("KEY").equals("VolumetricLighting")) {
							Storage.nxtGraphicsSetting_VolumetricLighting = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
						}
						else if (rs.getString("KEY").equals("Bloom")) {
							Storage.nxtGraphicsSetting_Bloom = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
						}
						else if (rs.getString("KEY").equals("AmbientOcclusion")) {
							Storage.nxtGraphicsSetting_AmbientOcclusion = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
						}
						else if (rs.getString("KEY").equals("Texturing")) {
							Storage.nxtGraphicsSetting_TextureQuality = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
						}
						else if (rs.getString("KEY").equals("AntialiasingMode")) {
							Storage.nxtGraphicsSetting_AntiAliasingMode = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
						}
						else if (rs.getString("KEY").equals("AnisotropicFiltering")) {
							Storage.nxtGraphicsSetting_AnisotropicFiltering = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
						}
						else if (rs.getString("KEY").equals("VSync")) {
							Storage.nxtGraphicsSetting_VSync = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.VSyncComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VSync+1);
						}
						else if (rs.getString("KEY").equals("RemoveRoof")) {
							Storage.nxtGraphicsSetting_RemoveRoofs = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.RemoveRoofsComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_RemoveRoofs);
						}
						else if (rs.getString("KEY").equals("Brightness")) {
							Storage.nxtGraphicsSetting_Brightness = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.BrightnessSlider.setValue(Storage.nxtGraphicsSetting_Brightness);
						}
						else if (rs.getString("KEY").equals("MaxForegroundFps")) {
							Storage.nxtGraphicsSetting_MaxForegroundFps = new Integer(rs.getString("DATA"));
							int ID = (Storage.nxtGraphicsSetting_MaxForegroundFps/5)-1;
							if (ID < 0){
								ID = 0;
							}
							if (ID > Storage.GRAPHICS_OPTIONS[14].length){
								ID = Storage.GRAPHICS_OPTIONS[14].length;
							}
							NXTSettingsGUI.MaxForegroundFpsComboBox.setSelectedIndex(ID);
						}
						else if (rs.getString("KEY").equals("MaxBackgroundFps")) {
							Storage.nxtGraphicsSetting_MaxBackgroundFps = new Integer(rs.getString("DATA"));
							int ID = (Storage.nxtGraphicsSetting_MaxBackgroundFps/5)-1;
							if (ID < 0){
								ID = 0;
							}
							if (ID > Storage.GRAPHICS_OPTIONS[14].length){
								ID = Storage.GRAPHICS_OPTIONS[14].length;
							}
							NXTSettingsGUI.MaxBackgroundFpsComboBox.setSelectedIndex(ID);
						}
						else if (rs.getString("KEY").equals("GameRenderScale")) {
							Storage.nxtClientSettings_GameWorldScaling = new Integer(rs.getString("DATA"));
							if (Storage.nxtClientSettings_GameWorldScaling < 33){
								Storage.nxtClientSettings_GameWorldScaling = 33;
							} else if (Storage.nxtClientSettings_GameWorldScaling > 200){
								Storage.nxtClientSettings_GameWorldScaling = 200;
							}
							NXTSettingsGUI.GameWorldScalingInput.setText(""+Storage.nxtClientSettings_GameWorldScaling);
							if (NXTSettingsGUI.GameWorldScalingInput.getText().equals("") ||
								NXTSettingsGUI.GameWorldScalingInput.getText() == null){
								NXTSettingsGUI.GameWorldScalingInput.setText("100");
								Storage.nxtClientSettings_GameWorldScaling = 100;
							}
						}

						else if (rs.getString("KEY").equals("InterfaceScale")) {
							Storage.nxtClientSettings_UIScaling = new Integer(rs.getString("DATA"));
							if (Storage.nxtClientSettings_UIScaling < 100){
								Storage.nxtClientSettings_UIScaling = 100;
							} else if (Storage.nxtClientSettings_UIScaling > 400){
								Storage.nxtClientSettings_UIScaling = 400;
							}
							NXTSettingsGUI.UIScalingInput.setText(""+Storage.nxtClientSettings_UIScaling);
							if (NXTSettingsGUI.UIScalingInput.getText().equals("") ||
								NXTSettingsGUI.UIScalingInput.getText() == null){
								NXTSettingsGUI.UIScalingInput.setText("100");
								Storage.nxtClientSettings_UIScaling = 100;
							}
						}
						else if (rs.getString("KEY").equals("VolumeLoginMusic")) {
							Storage.nxtClientSettings_LoginMusicVolume = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.LoginMusicSlider.setValue(Storage.nxtClientSettings_LoginMusicVolume);
						}
						else if (rs.getString("KEY").equals("Version")) {
							NXTSettingsGUI.frame.setTitle("NXT Settings (Settings Version: "+rs.getString("DATA")+")");
						}
					}
					rs.close();
				} catch(final SQLException e) {
					System.err.println(e.getMessage());
				}

				try(ResultSet rs = Storage.stmt.executeQuery("SELECT * FROM 'vt-varc'")) {
					while (rs.next()) {
						if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME)) {
							if (NXTSettingsGUI.ShowSensitiveInformation.isSelected()) {
								NXTSettingsGUI.UsernameInput.setText(rs.getString("DATA"));
							}
							else{
								NXTSettingsGUI.UsernameInput.setText("");
							}
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1)) {
							NXTSettingsGUI.FavouriteWorld1Input.setText(rs.getString("DATA"));
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2)) {
							NXTSettingsGUI.FavouriteWorld2Input.setText(rs.getString("DATA"));
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3)) {
							NXTSettingsGUI.FavouriteWorld3Input.setText(rs.getString("DATA"));
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_LOADING_SCREENS)) {
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
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_GLOBAL_AUDIO_MUTE)) {
							Storage.nxtClientSettings_GlobalMute = rs.getString("DATA").equals("1");
							NXTSettingsGUI.GlobalAudioMuteCheckbox.setSelected(Storage.nxtClientSettings_GlobalMute);
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_IN_GAME_MUSIC_VOLUME)) {
							Storage.nxtClientSettings_InGameMusicVolume = new Integer(rs.getString("DATA"));
							NXTSettingsGUI.InGameMusicSlider.setValue(Storage.nxtClientSettings_InGameMusicVolume);
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_IN_GAME_SOUND_EFFECTS_VOLUME)) {
							Storage.nxtClientSettings_InGameSoundEffectsVolume = new Integer(rs.getString("DATA"));
							if (Storage.nxtClientSettings_InGameSoundEffectsVolume > 127){
								NXTSettingsGUI.InGameSoundEffectsBoostCheckbox.setSelected(true);
								NXTSettingsGUI.InGameSoundEffectsSlider.setMaximum(254);
							} else {
								NXTSettingsGUI.InGameSoundEffectsBoostCheckbox.setSelected(false);
								NXTSettingsGUI.InGameSoundEffectsSlider.setMaximum(127);
							}
							NXTSettingsGUI.InGameSoundEffectsSlider.setValue(new Integer(rs.getString("DATA")));
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_IN_GAME_AMBIENT_EFFECTS_VOLUME)) {
							Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume = new Integer(rs.getString("DATA"));
							if (Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume > 127){
								NXTSettingsGUI.InGameAmbientSoundEffectsBoostCheckbox.setSelected(true);
								NXTSettingsGUI.InGameAmbientSoundEffectsSlider.setMaximum(254);
							} else {
								NXTSettingsGUI.InGameAmbientSoundEffectsBoostCheckbox.setSelected(false);
								NXTSettingsGUI.InGameAmbientSoundEffectsSlider.setMaximum(127);
							}
							NXTSettingsGUI.InGameAmbientSoundEffectsSlider.setValue(new Integer(rs.getString("DATA")));
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_IN_GAME_VOICE_OVER_VOLUME)) {
							Storage.nxtClientSettings_InGameVoiceOverVolume = new Integer(rs.getString("DATA"));
							if (Storage.nxtClientSettings_InGameVoiceOverVolume > 127){
								NXTSettingsGUI.InGameVoiceOverBoostCheckbox.setSelected(true);
								NXTSettingsGUI.InGameVoiceOverSlider.setMaximum(254);
							} else {
								NXTSettingsGUI.InGameVoiceOverBoostCheckbox.setSelected(false);
								NXTSettingsGUI.InGameVoiceOverSlider.setMaximum(127);
							}
							NXTSettingsGUI.InGameVoiceOverSlider.setValue(new Integer(rs.getString("DATA")));
							}
					}
					rs.close();
				} catch(final SQLException e) {}

				/*
				 * > Check every entry in the vt-varc table
				 *
				 * > Save the entries to a List
				 *
				 * > Convert the List to a String Array
				 *
				 * > TO-DO: Make an editable display
				 */

				try(ResultSet rs = Storage.stmt.executeQuery("SELECT * FROM 'console'")) {
					while (rs.next()) {
						Storage.nxtClientSettings_DeveloperConsoleLog.add(rs.getString("DATA"));
					}
					Storage.nxtClientSettings_DeveloperConsoleLogs = Storage.nxtClientSettings_DeveloperConsoleLog.toArray(new String[0]);
					rs.close();
				} catch(final SQLException e) {}
			}
		} else {
			JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Error: NXT is not being read as installed on this system."+
																"\n\n"+
																"Settings will not be read, Aborting program functions.");
			System.exit(0);
		}
		Legality.CheckSettings();
	}
	public static void Write() {
		/*
		 * > Output where the `Settings.jcache` file will be saved to
		 *
		 * > Initialize the Sqlite3 database
		 *  > Set it to edit `Settings.jcache`
		 *
		 * > Start a legality check
		 *
		 * > Start adding batches to delete older entries
		 *
		 * > Start adding batches to insert updated entries
		 *
		 * > Repeat the last two until finished for all values
		 *  > Boolean-type entries will be manually 1 <> 0 dependant on their value related to true <> false
		 *  > Legality checks will take place before username+favourite world settings to prevent invalid values
		 *  > Temporary username+UID field will be cleared to prevent any paranoia post-saving
		 *
		 * > Execute batches (write to the database)
		 *
		 * > Output that the files were saved, in a dialogue, with the path to the file
		 */
		System.out.print("Saving updated values to: "+Storage.Cache_settings_location+"...");
		try {

			Legality.CheckSettings();

			// Left Column
			Write(true,	"RemoveRoof",			Storage.nxtGraphicsSetting_RemoveRoofs);
			Write(true,	"DrawDistance",			Storage.nxtGraphicsSetting_DrawDistance);
			Write(true,	"ShadowQuality",		Storage.nxtGraphicsSetting_ShadowQuality);
			Write(true,	"VSync",				Storage.nxtGraphicsSetting_VSync);
			Write(true,	"AntialiasingMode",		Storage.nxtGraphicsSetting_AntiAliasingMode);
			Write(true,	"AntialiasingQuality",	Storage.nxtGraphicsSetting_AntiAliasingQuality);
			Write(true,	"Reflections",			Storage.nxtGraphicsSetting_WaterQuality);
			Write(true,	"LightingQuality",		Storage.nxtGraphicsSetting_LightingQuality);
			Write(true,	"AmbientOcclusion",		Storage.nxtGraphicsSetting_AmbientOcclusion);
			Write(true,	"Bloom",				Storage.nxtGraphicsSetting_Bloom);
			Write(true,	"MaxForegroundFps",		Storage.nxtGraphicsSetting_MaxForegroundFps);
			Write(true,	"InterfaceScale",		Storage.nxtClientSettings_UIScaling);

			// Right Column
			Write(true,	"Brightness",			Storage.nxtGraphicsSetting_Brightness);
			Write(true,	"Texturing",			Storage.nxtGraphicsSetting_TextureQuality);
			Write(true,	"AnisotropicFiltering",	Storage.nxtGraphicsSetting_AnisotropicFiltering);
			Write(true,	"VolumetricLighting",	Storage.nxtGraphicsSetting_VolumetricLighting);

			if (Storage.nxtGraphicsSetting_FlickeringEffects) {
				Write(true,	"FlickeringEffects", 1);
			} else {
				Write(true,	"FlickeringEffects", 0);
			}
			if (Storage.nxtGraphicsSetting_Shadows) {
				Write(true,	"Shadows", 1);
			} else {
				Write(true,	"Shadows", 0);
			}
			if (Storage.nxtGraphicsSetting_CustomCursors) {
				Write(true,	 "CustomCursors",							1);
				Write(false, Storage.CACHE_KEY_VT_VARC_CUSTOM_CURSORS,	1);
			} else {
				Write(true,	 "CustomCursors",							0);
				Write(false, Storage.CACHE_KEY_VT_VARC_CUSTOM_CURSORS,	0);
			}
			if (Storage.nxtGraphicsSetting_LoadingScreens) {
				Write(false, Storage.CACHE_KEY_VT_VARC_LOADING_SCREENS,	1);
			} else {
				Write(false, Storage.CACHE_KEY_VT_VARC_LOADING_SCREENS,	0);
			}
			if (Storage.nxtGraphicsSetting_GroundDecor) {
				Write(true,	"GroundDecor",	1);
			} else {
				Write(true,	"GroundDecor",	0);
			}
			if (Storage.nxtGraphicsSetting_TerrainBlending) {
				Write(true,	"GroundBlending",	1);
			} else {
				Write(true,	"GroundBlending",	0);
			}

			Write(true,		"MaxBackgroundFps",											Storage.nxtGraphicsSetting_MaxBackgroundFps);
			Write(true,		"GameRenderScale",											Storage.nxtClientSettings_GameWorldScaling);
			Write(true,		"VolumeLoginMusic",											Storage.nxtClientSettings_LoginMusicVolume);
			Write(true,		"VolumeMainMusic",											Storage.nxtClientSettings_InGameMusicVolume);
			Write(false,	Storage.CACHE_KEY_VT_VARC_IN_GAME_MUSIC_VOLUME,				Storage.nxtClientSettings_InGameMusicVolume);
			Write(true,		"VolumeMainEffects",										Storage.nxtClientSettings_InGameSoundEffectsVolume);
			Write(false,	Storage.CACHE_KEY_VT_VARC_IN_GAME_SOUND_EFFECTS_VOLUME,		Storage.nxtClientSettings_InGameSoundEffectsVolume);
			Write(true,		"VolumeBackgroundEffects",									Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume);
			Write(false,	Storage.CACHE_KEY_VT_VARC_IN_GAME_AMBIENT_EFFECTS_VOLUME,	Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume);
			Write(true,		"VolumeSpeech", 											Storage.nxtClientSettings_InGameVoiceOverVolume);
			Write(false,	Storage.CACHE_KEY_VT_VARC_IN_GAME_VOICE_OVER_VOLUME,		Storage.nxtClientSettings_InGameVoiceOverVolume);

			if (Storage.nxtClientSettings_GlobalMute) {
				Write(false, Storage.CACHE_KEY_VT_VARC_GLOBAL_AUDIO_MUTE,	1);
			} else {
				Write(false, Storage.CACHE_KEY_VT_VARC_GLOBAL_AUDIO_MUTE,	0);
			}

			// Usernames
			Legality.CheckSettingsBeforeSave();

			if (Storage.nxtClientSettings_RememberUsername &&
				Storage.nxtClientSettings_TemporaryUsername != null &&
			   !Storage.nxtClientSettings_TemporaryUsername.equals("")) {
				Write(false, Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME,		Storage.nxtClientSettings_TemporaryUsername);
				Write(false, Storage.CACHE_KEY_VT_VARC_REMEMBER_USERNAME,	1);
			} else {
				Write(false, Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME,		"");
				Write(false, Storage.CACHE_KEY_VT_VARC_REMEMBER_USERNAME,	0);
			}

			// Clears any paranoia, if any, for these values
			Storage.nxtClientSettings_TemporaryUsername = "";
			Storage.nxtClientSettings_TemporaryUserID = "";

			// Favourite Worlds
			Write(false, Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1,	Storage.nxtClientSettings_FavouriteWorld1);
			Write(false, Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2,	Storage.nxtClientSettings_FavouriteWorld2);
			Write(false, Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3,	Storage.nxtClientSettings_FavouriteWorld3);

			// Wallpapers
			Write(false, Storage.CACHE_KEY_VT_VERC_WALLPAPER_ID,		Storage.nxtClientSettings_LoginWallpaperID);

			if (Storage.nxtClientSettings_RandomizeLoginWallpaper) {
				Write(false, Storage.CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER,	1);
			} else {
				Write(false, Storage.CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER,	0);
			}

			// Execute/Save the changes
			Storage.stmt.executeBatch();
			Storage.stmt.clearBatch();
			System.out.print(" Saved.\n");
			JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Saved updated values to:\n\n"+Storage.Cache_settings_location);

		} catch(final SQLException e) {
			e.printStackTrace();
		}
	}
	public static void Write(final boolean isConfigTable, final String Key, final Object Value){
		/*
		 * > Check if `Storage.conn` or `Storage.stmt` is `null`, and `Storage.Cache_settings_location` isn't `` or `null`.
		 *  > If `conn` or `stmt` is `null`, initialize. Otherwise continue.
		 *
		 * > If `isConfigTable` is `true`, default the `Config` table. Otherwise use the `vt-varc` table.
		 *
		 * > Add the batch if `Key` and `Value` aren't `null` or the `Key` is ``.
		 *
		 */
		try {
			String Table = "";
			if ((Storage.conn == null || Storage.stmt == null) &&
				(Storage.Cache_settings_location != null && !Storage.Cache_settings_location.trim().equals(""))){
				 Storage.conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
				 Storage.stmt = Storage.conn.createStatement();
			}
			if (isConfigTable){
				Table = "Config";
			} else{
				Table = "vt-varc";
			}
			if (!Table.equals("") && Key != null && !Key.equals("") && Value != null){
			Storage.stmt.addBatch("DELETE FROM '"+Table+"' "+
						  		  "WHERE KEY='"+Key+"';");
			Storage.stmt.addBatch("INSERT INTO '"+Table+"' ('KEY', 'DATA')" +
								  "VALUES ('"+Key+"', '" + Value + "');");
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}
}
