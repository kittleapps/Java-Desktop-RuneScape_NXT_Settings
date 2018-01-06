package com.kittleapps.desktop.app.nxtsettings;

import static com.sun.jna.platform.win32.WinReg.HKEY_CURRENT_USER;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.sun.jna.platform.win32.Advapi32Util;

public class JCache {

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
		 *  > If failed, tell user the configuration at {$Storage.configuration_location} is non-existent. Abort the program
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
		 *  > Add to a StringBuilder for a separate function
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
		}	else if (OS_TYPE == 1) {
			Storage.NXT_REGISTRY_LOCATION_BASE = "/usr/bin/runescape-launcher";
			Storage.NXT_INSTALLED = new File(Storage.NXT_REGISTRY_LOCATION_BASE).exists();
		}
		if (Storage.NXT_INSTALLED) {
			if (OS_TYPE == 0) {
				Storage.configuration_location = Advapi32Util.registryGetStringValue(HKEY_CURRENT_USER, Storage.NXT_REGISTRY_LOCATION_BASE, "splash")
															 .replace("splash6.gif", "preferences.cfg")
															 .replace("\\", "/");
			}	else if ((OS_TYPE >= 1) && (OS_TYPE <= 3)) {
				Storage.configuration_location = new File(System.getProperty("user.home").replace("\\", "/") +
														 "/Jagex/launcher/preferences.cfg").getAbsolutePath();
			}	else {
				System.out.println("Error: Unknown OS Value: "+Storage.OS);
				JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Error: Unknown OS Value: " + Storage.OS+
																	"\n\n"+
																	"Settings will not be read, Aborting program functions.");
				System.exit(0);
			}

			Storage.Cache_settings_location = "";
			Storage.preferences_config = new File(Storage.configuration_location);
			final Path Preferences = Storage.preferences_config.toPath();
			List<String> fileContent;

			try	{
				fileContent = new ArrayList<>(Files.readAllLines(Preferences, StandardCharsets.UTF_8));
				for (int i = 0; i < fileContent.size(); i++) {
					if (fileContent.get(i).toLowerCase().startsWith("compatibility=")) {
						final String CompatMode = fileContent.get(i).trim()
																	.replace(fileContent.get(i).substring(0, 14), "")
																	.replace("true", "On.")
																	.replace("false", "Off.")
																	.replace("default", "Automatic.");
						Storage.nxtClientSettings_CompatibilityMode = CompatMode.equalsIgnoreCase("On.");
					}
				    if (fileContent.get(i).toLowerCase().startsWith("dont_ask_graphics=")) {
				    	Storage.nxtClientSettings_AskToSwitchToCompatibility =	fileContent.get(i)
				    																	   .trim()
																							.replace(fileContent.get(i).substring(0, 18), "")
																						   .equals("1");
				    }
				    if (fileContent.get(i).toLowerCase().startsWith("confirm_quit=")) {
						Storage.nxtClientSettings_AskBeforeQuitting = fileContent.get(i).trim()
																						.replace(fileContent.get(i).substring(0, 13), "")
																						.equals("1");
				    }
				    if (fileContent.get(i).toLowerCase().startsWith("language=")) {
				    	Storage.nxtClientSettings_LanguageSelected = 0;
						final String TempString = fileContent.get(i)
															 .trim()
														  	 .replace(fileContent.get(i).substring(0, 9), "");
						if (TempString.equals("0")){
							Storage.nxtClientSettings_LanguageSelected = 0;
						}	else if (TempString.equals("1")){
							Storage.nxtClientSettings_LanguageSelected = 1;
						}	else if (TempString.equals("2")){
							Storage.nxtClientSettings_LanguageSelected = 2;
						}	else if (TempString.equals("3")){
							Storage.nxtClientSettings_LanguageSelected = 3;
						}	else {
							Storage.nxtClientSettings_LanguageSelected = 0;
						}
				    }
				    if (fileContent.get(i).toLowerCase().startsWith("user_folder=")) {
						Storage.Cache_settings_location = fileContent.get(i).trim()
																	  		.replace(fileContent.get(i).substring(0, 12), "")
																	  		.replace("\\", "/") +
																	  		"/RuneScape/Settings.jcache";
					}
				}

				if ((Storage.nxtClientSettings_LanguageSelected <= -1) || (Storage.nxtClientSettings_LanguageSelected >= 4)) {
					Storage.nxtClientSettings_LanguageSelected = 0;
					NXTSettingsGUI.LanguageSelectionComboBox.setSelectedIndex(Storage.nxtClientSettings_LanguageSelected);
				}	else {
					NXTSettingsGUI.LanguageSelectionComboBox.setSelectedIndex(Storage.nxtClientSettings_LanguageSelected);
				}

				NXTSettingsGUI.CompatibilityModeCheckBox.setSelected(Storage.nxtClientSettings_CompatibilityMode);
				NXTSettingsGUI.CompatibilityModeOnErrorCheckBox.setSelected(Storage.nxtClientSettings_AskToSwitchToCompatibility);
				NXTSettingsGUI.AskBeforeQuittingCheckBox.setSelected(Storage.nxtClientSettings_AskBeforeQuitting);

			} catch (final IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Error: Reading failed on the configuration file at: \n" +
																	Storage.configuration_location +
																	"\n\n"+
																	"Aborting the program's functioning.");
				System.exit(0);
			}

			Storage.Settings_db = new File(Storage.Cache_settings_location);

			if (Storage.Settings_db.isFile() && Storage.Settings_db.exists()) {
				try {
					Class.forName("org.sqlite.JDBC");
				}	catch(final ClassNotFoundException eString) {
					System.err.println("Could not init JDBC driver - driver not found");
					JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Could not init JDBC driver - driver not found"+
																		"\n\n"+
																		"Settings can not be read, Aborting the program's functioning.");
				}

				try {
					Storage.conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
					Storage.stmt = Storage.conn.createStatement();
				}	catch(final SQLException e) {}

				ReadConfigSettings();
				ReadVTVarcSettings();
				Mechanics.DeselectDevConsole();
				ReadDeveloperConsoleHistory();
				ReadProgramOptions();
				if (Storage.OS_TYPE == 0) {
					Mechanics.GrabInternalBuildNumber(false);
				}
			} 	else {
				JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Error: "+Storage.Settings_db.getAbsolutePath()+" was not found, or somehow was not a file.");
			}
		}	else {
			JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Error: NXT is not being read as installed on this system."+
																"\n\n"+
																"Settings will not be read, Aborting program functions.");
			System.exit(0);
		}
		Legality.CheckSettings();
	}

	public static void ReadConfigSettings(){
		try(ResultSet rs = Storage.stmt.executeQuery("SELECT * FROM Config")) {
			while (rs.next()) {
				switch(rs.getString("KEY")) {
					case "CustomCursors":
						Storage.nxtGraphicsSetting_CustomCursors = rs.getString("DATA").equals("1");
						NXTSettingsGUI.CustomCursorsCheckbox.setSelected(rs.getString("DATA").equals("1"));
						break;

					case "Shadows":
						Storage.nxtGraphicsSetting_Shadows = rs.getString("DATA").equals("1");
						NXTSettingsGUI.ShadowsCheckbox.setSelected(rs.getString("DATA").equals("1"));
						break;

					case "FlickeringEffects":
						Storage.nxtGraphicsSetting_FlickeringEffects = rs.getString("DATA").equals("1");
						NXTSettingsGUI.FlickeringEffectsCheckbox.setSelected(rs.getString("DATA").equals("1"));
						break;

					case "GroundDecor":
						Storage.nxtGraphicsSetting_GroundDecor = rs.getString("DATA").equals("1");
						NXTSettingsGUI.GroundDecorationsCheckbox.setSelected(rs.getString("DATA").equals("1"));
						break;

					case "GroundBlending":
						Storage.nxtGraphicsSetting_TerrainBlending = rs.getString("DATA").equals("1");
						NXTSettingsGUI.TerrainBlendingCheckbox.setSelected(rs.getString("DATA").equals("1"));
						break;

					case "DrawDistance":
						Storage.nxtGraphicsSetting_DrawDistance = rs.getInt("DATA");
						NXTSettingsGUI.DrawDistanceComboBox.setSelectedIndex(rs.getInt("DATA"));
						break;

					case "ShadowQuality":
						Storage.nxtGraphicsSetting_ShadowQuality = rs.getInt("DATA");
						NXTSettingsGUI.ShadowQualityComboBox.setSelectedIndex(rs.getInt("DATA"));
						break;

					case "LightingQuality":
						Storage.nxtGraphicsSetting_LightingQuality = rs.getInt("DATA");
						NXTSettingsGUI.LightingDetailComboBox.setSelectedIndex(rs.getInt("DATA"));
						break;

					case "AntialiasingQuality":
						Storage.nxtGraphicsSetting_AntiAliasingQuality = rs.getInt("DATA");
						NXTSettingsGUI.AntiAliasingQualityComboBox.setSelectedIndex(rs.getInt("DATA"));
						break;

					case "Reflections":
						Storage.nxtGraphicsSetting_WaterQuality = rs.getInt("DATA");
						NXTSettingsGUI.WaterQualityComboBox.setSelectedIndex(rs.getInt("DATA"));
						break;

					case "VolumetricLighting":
						Storage.nxtGraphicsSetting_VolumetricLighting = rs.getInt("DATA");
						NXTSettingsGUI.VolumetricLightingComboBox.setSelectedIndex(rs.getInt("DATA"));
						break;

					case "Bloom":
						Storage.nxtGraphicsSetting_Bloom = rs.getInt("DATA");
						NXTSettingsGUI.BloomQualityComboBox.setSelectedIndex(rs.getInt("DATA"));
						break;

					case "AmbientOcclusion":
						Storage.nxtGraphicsSetting_AmbientOcclusion = rs.getInt("DATA");
						NXTSettingsGUI.AmbientOcclusionComboBox.setSelectedIndex(rs.getInt("DATA"));
						break;

					case "Texturing":
						Storage.nxtGraphicsSetting_TextureQuality = rs.getInt("DATA");
						NXTSettingsGUI.TextureQualityComboBox.setSelectedIndex(rs.getInt("DATA"));
						break;

					case "AntialiasingMode":
						Storage.nxtGraphicsSetting_AntiAliasingMode = rs.getInt("DATA");
						NXTSettingsGUI.AntiAliasingModeComboBox.setSelectedIndex(rs.getInt("DATA"));
						break;

					case "AnisotropicFiltering":
						Storage.nxtGraphicsSetting_AnisotropicFiltering = rs.getInt("DATA");
						NXTSettingsGUI.AnisotropicFilteringComboBox.setSelectedIndex(rs.getInt("DATA"));
						break;

					case "VSync":
						Storage.nxtGraphicsSetting_VSync = rs.getInt("DATA");
						NXTSettingsGUI.VSyncComboBox.setSelectedIndex((rs.getInt("DATA")+1));
						break;

					case "RemoveRoof":
						Storage.nxtGraphicsSetting_RemoveRoofs = rs.getInt("DATA");
						NXTSettingsGUI.RemoveRoofsComboBox.setSelectedIndex(rs.getInt("DATA"));
						break;

					case "Brightness":
						Storage.nxtGraphicsSetting_Brightness = rs.getInt("DATA");
						NXTSettingsGUI.BrightnessSlider.setValue(rs.getInt("DATA"));
						break;

					case "MaxForegroundFps":
						Storage.nxtGraphicsSetting_MaxForegroundFps = rs.getInt("DATA");
						int ForegroundFpsIndexID = (rs.getInt("DATA")/5)-1;
						if (ForegroundFpsIndexID < 0){
							ForegroundFpsIndexID = 0;
						}
						if (ForegroundFpsIndexID > Storage.SETTINGS_OPTIONS[14].length){
							ForegroundFpsIndexID = Storage.SETTINGS_OPTIONS[14].length;
						}
						NXTSettingsGUI.MaxForegroundFpsComboBox.setSelectedIndex(ForegroundFpsIndexID);
						break;

					case "MaxBackgroundFps":
						Storage.nxtGraphicsSetting_MaxBackgroundFps = rs.getInt("DATA");
						int BackgroundFpsIndexID = (rs.getInt("DATA")/5)-1;
						if (BackgroundFpsIndexID < 0){
							BackgroundFpsIndexID = 0;
						}
						if (BackgroundFpsIndexID > Storage.SETTINGS_OPTIONS[14].length){
							BackgroundFpsIndexID = Storage.SETTINGS_OPTIONS[14].length;
						}
						NXTSettingsGUI.MaxBackgroundFpsComboBox.setSelectedIndex(BackgroundFpsIndexID);
						break;

					case "InterfaceScale":
						Storage.nxtClientSettings_UIScaling = rs.getInt("DATA");
						int UIScalingIndex = ((rs.getInt("DATA")-100)/5);
						if (UIScalingIndex < 0){
							UIScalingIndex = 0;
						}
						if (UIScalingIndex > Storage.SETTINGS_OPTIONS[15].length){
							UIScalingIndex = Storage.SETTINGS_OPTIONS[15].length;
						}
						NXTSettingsGUI.UIScalingComboBox.setSelectedIndex(UIScalingIndex);
						break;

					case "GameRenderScale":
						Storage.nxtClientSettings_GameWorldScaling = rs.getInt("DATA");
						int GameWorldScalingIndex = ((rs.getInt("DATA")-35)/5);
						if (GameWorldScalingIndex < 0){
							GameWorldScalingIndex = 0;
						}
						if (GameWorldScalingIndex > Storage.SETTINGS_OPTIONS[16].length){
							GameWorldScalingIndex = Storage.SETTINGS_OPTIONS[16].length;
						}
						NXTSettingsGUI.GameWorldScalingComboBox.setSelectedIndex(GameWorldScalingIndex);
						break;

					case "VolumeLoginMusic":
						Storage.nxtClientSettings_LoginMusicVolume = rs.getInt("DATA");
						NXTSettingsGUI.LoginMusicSlider.setValue(rs.getInt("DATA"));
						break;

					case "VolumeMainMusic":
						Storage.nxtClientSettings_InGameMusicVolume = rs.getInt("DATA");
						NXTSettingsGUI.InGameMusicSlider.setValue(rs.getInt("DATA"));
						break;

					case "VolumeMainEffects":
						Storage.nxtClientSettings_InGameSoundEffectsVolume = rs.getInt("DATA");
						NXTSettingsGUI.InGameSoundEffectsSlider.setValue(rs.getInt("DATA"));
						break;

					case "VolumeBackgroundEffects":
						Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume = rs.getInt("DATA");
						NXTSettingsGUI.InGameAmbientSoundEffectsSlider.setValue(rs.getInt("DATA"));
						break;

					case "VolumeSpeech":
						Storage.nxtClientSettings_InGameVoiceOverVolume = rs.getInt("DATA");
						NXTSettingsGUI.InGameVoiceOverSlider.setValue(rs.getInt("DATA"));
						break;

					case "Version":
						Storage.nxtClientSettings_SettingsVersion = rs.getInt("DATA");
						NXTSettingsGUI.frame.setTitle("NXT's Settings (Graphic Setting's Version: \""+rs.getInt("DATA")+"\")");
						break;

					case "DOF":
					case "HeatHaze":
					case "ResizableResolution":
					case "CameraOcclusion":
					case "ConsoleKeyPress":
					case "WindowMode":
					case "FullScreenWidth":
					case "FullScreenHeight":
						// These do nothing for the program currently, but I want them to one day.
						break;

				}
			}
			rs.close();
		} catch(final SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void ReadVTVarcSettings(){
		try(ResultSet rs = Storage.stmt.executeQuery("SELECT * FROM 'vt-varc'")) {
			while (rs.next()) {
				switch(rs.getString("KEY")) {
					case Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME:
						if (Storage.DEVELOPER_AlwaysShowSensitiveInfo) {
							NXTSettingsGUI.UsernameInput.setText(rs.getString("DATA"));
						}
						else if (NXTSettingsGUI.ShowSensitiveInformation.isSelected()) {
							NXTSettingsGUI.UsernameInput.setText(rs.getString("DATA"));
						}
						else {
							NXTSettingsGUI.UsernameInput.setText("");
						}
						break;

					case Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1:
						if (rs.getInt("DATA") <= 0){
							NXTSettingsGUI.FavouriteWorld1ComboBox.setSelectedIndex(0);
						} else if (rs.getInt("DATA") >= 255){
							NXTSettingsGUI.FavouriteWorld1ComboBox.setSelectedIndex(255);
						} else{
							NXTSettingsGUI.FavouriteWorld1ComboBox.setSelectedIndex(rs.getInt("DATA"));
						}
						break;

					case Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2:
						if (rs.getInt("DATA") <= 0){
							NXTSettingsGUI.FavouriteWorld2ComboBox.setSelectedIndex(0);
						} else if (rs.getInt("DATA") >= 255){
							NXTSettingsGUI.FavouriteWorld2ComboBox.setSelectedIndex(255);
						} else{
							NXTSettingsGUI.FavouriteWorld2ComboBox.setSelectedIndex(rs.getInt("DATA"));
						}
						break;

					case Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3:
						if (rs.getInt("DATA") <= 0){
							NXTSettingsGUI.FavouriteWorld3ComboBox.setSelectedIndex(0);
						} else if (rs.getInt("DATA") >= 255){
							NXTSettingsGUI.FavouriteWorld3ComboBox.setSelectedIndex(255);
						} else{
							NXTSettingsGUI.FavouriteWorld3ComboBox.setSelectedIndex(rs.getInt("DATA"));
						}
						break;

					case Storage.CACHE_KEY_VT_VARC_LOADING_SCREENS:
							Storage.nxtGraphicsSetting_LoadingScreens = rs.getString("DATA").equals("1");
							NXTSettingsGUI.LoadingScreensCheckbox.setSelected(Storage.nxtGraphicsSetting_LoadingScreens);
						break;

					case Storage.CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER:
							Storage.nxtClientSettings_RandomizeLoginWallpaper = rs.getString("DATA").equals("1");
							NXTSettingsGUI.RandomizeLoginWallpaperCheckbox.setSelected(Storage.nxtClientSettings_RandomizeLoginWallpaper);
						break;

					case Storage.CACHE_KEY_VT_VERC_WALLPAPER_ID:
						if (rs.getInt("DATA") > (Storage.SETTINGS_OPTIONS[19].length-1)){
							Storage.nxtClientSettings_LoginWallpaperID = Storage.SETTINGS_OPTIONS[19].length-1;
							NXTSettingsGUI.LoginWallpaperIDComboBox.setSelectedIndex(Storage.SETTINGS_OPTIONS[19].length-1);
						}
						else if (rs.getInt("DATA") < 0){
							Storage.nxtClientSettings_LoginWallpaperID = 0;
							NXTSettingsGUI.LoginWallpaperIDComboBox.setSelectedIndex(0);
						}
						else {
							Storage.nxtClientSettings_LoginWallpaperID = rs.getInt("DATA");
							NXTSettingsGUI.LoginWallpaperIDComboBox.setSelectedIndex(rs.getInt("DATA"));
						}
						break;

					case Storage.CACHE_KEY_VT_VARC_REMEMBER_USERNAME:
							Storage.nxtClientSettings_RememberUsername = rs.getString("DATA").equals("1");
							NXTSettingsGUI.RememberUsernameCheckbox.setSelected(Storage.nxtClientSettings_RememberUsername);
						break;

					case Storage.CACHE_KEY_VT_VARC_CAMERA_ZOOM:
						if (rs.getInt("DATA") > NXTSettingsGUI.InGameCameraZoomSlider.getMaximum()){
							Storage.nxtClientSettings_CameraZoom = NXTSettingsGUI.InGameCameraZoomSlider.getMaximum();
							NXTSettingsGUI.InGameCameraZoomSlider.setValue(NXTSettingsGUI.InGameCameraZoomSlider.getMaximum());
						}
						else if (rs.getInt("DATA") < NXTSettingsGUI.InGameCameraZoomSlider.getMinimum()){
							Storage.nxtClientSettings_CameraZoom = NXTSettingsGUI.InGameCameraZoomSlider.getMinimum();
							NXTSettingsGUI.InGameCameraZoomSlider.setValue(NXTSettingsGUI.InGameCameraZoomSlider.getMinimum());
						}
						else {
							Storage.nxtClientSettings_CameraZoom = rs.getInt("DATA");
							NXTSettingsGUI.InGameCameraZoomSlider.setValue(rs.getInt("DATA"));
						}
						break;

					case Storage.CACHE_KEY_VT_VARC_KEYBOARD_H_SENSITIVITY:
						if (rs.getInt("DATA") > NXTSettingsGUI.InGameKeyboardHSensitivitySlider.getMaximum()){
							Storage.nxtClientSettings_KeyboardHSensitivity = NXTSettingsGUI.InGameKeyboardHSensitivitySlider.getMaximum();
							NXTSettingsGUI.InGameKeyboardHSensitivitySlider.setValue(NXTSettingsGUI.InGameKeyboardHSensitivitySlider.getMaximum());
						}
						else if (rs.getInt("DATA") < NXTSettingsGUI.InGameMouseHSensitivitySlider.getMinimum()){
							Storage.nxtClientSettings_KeyboardHSensitivity = NXTSettingsGUI.InGameKeyboardHSensitivitySlider.getMinimum();
							NXTSettingsGUI.InGameKeyboardHSensitivitySlider.setValue(NXTSettingsGUI.InGameKeyboardHSensitivitySlider.getMinimum());
						}
						else {
							Storage.nxtClientSettings_KeyboardHSensitivity = rs.getInt("DATA");
							NXTSettingsGUI.InGameKeyboardHSensitivitySlider.setValue(rs.getInt("DATA"));
						}
						break;

					case Storage.CACHE_KEY_VT_VARC_KEYBOARD_V_SENSITIVITY:
						if (rs.getInt("DATA") > NXTSettingsGUI.InGameKeyboardVSensitivitySlider.getMaximum()){
							Storage.nxtClientSettings_KeyboardVSensitivity = NXTSettingsGUI.InGameKeyboardVSensitivitySlider.getMaximum();
							NXTSettingsGUI.InGameKeyboardVSensitivitySlider.setValue(NXTSettingsGUI.InGameKeyboardVSensitivitySlider.getMaximum());
						}
						else if (rs.getInt("DATA") < NXTSettingsGUI.InGameMouseHSensitivitySlider.getMinimum()){
							Storage.nxtClientSettings_KeyboardVSensitivity = NXTSettingsGUI.InGameKeyboardVSensitivitySlider.getMinimum();
							NXTSettingsGUI.InGameKeyboardVSensitivitySlider.setValue(NXTSettingsGUI.InGameKeyboardVSensitivitySlider.getMinimum());
						}
						else {
							Storage.nxtClientSettings_KeyboardVSensitivity = rs.getInt("DATA");
							NXTSettingsGUI.InGameMouseHSensitivitySlider.setValue(rs.getInt("DATA"));
						}
						break;

					case Storage.CACHE_KEY_VT_VARC_MOUSE_H_SENSITIVITY:
						if (rs.getInt("DATA") > NXTSettingsGUI.InGameMouseHSensitivitySlider.getMaximum()){
							Storage.nxtClientSettings_MouseHSensitivity = NXTSettingsGUI.InGameMouseHSensitivitySlider.getMaximum();
							NXTSettingsGUI.InGameMouseHSensitivitySlider.setValue(NXTSettingsGUI.InGameMouseHSensitivitySlider.getMaximum());
						}
						else if (rs.getInt("DATA") < NXTSettingsGUI.InGameMouseHSensitivitySlider.getMinimum()){
							Storage.nxtClientSettings_MouseHSensitivity = NXTSettingsGUI.InGameMouseHSensitivitySlider.getMinimum();
							NXTSettingsGUI.InGameMouseHSensitivitySlider.setValue(NXTSettingsGUI.InGameMouseHSensitivitySlider.getMinimum());
						}
						else {
							Storage.nxtClientSettings_MouseHSensitivity = rs.getInt("DATA");
							NXTSettingsGUI.InGameMouseHSensitivitySlider.setValue(rs.getInt("DATA"));
						}
						break;

					case Storage.CACHE_KEY_VT_VARC_MOUSE_V_SENSITIVITY:
						if (rs.getInt("DATA") > NXTSettingsGUI.InGameMouseVSensitivitySlider.getMaximum()){
							Storage.nxtClientSettings_MouseVSensitivity = NXTSettingsGUI.InGameMouseVSensitivitySlider.getMaximum();
							NXTSettingsGUI.InGameMouseVSensitivitySlider.setValue(NXTSettingsGUI.InGameMouseVSensitivitySlider.getMaximum());
						}
						else if (rs.getInt("DATA") < NXTSettingsGUI.InGameMouseVSensitivitySlider.getMinimum()){
							Storage.nxtClientSettings_MouseVSensitivity = NXTSettingsGUI.InGameMouseVSensitivitySlider.getMinimum();
							NXTSettingsGUI.InGameMouseVSensitivitySlider.setValue(NXTSettingsGUI.InGameMouseVSensitivitySlider.getMinimum());
						}
						else {
							Storage.nxtClientSettings_MouseVSensitivity = rs.getInt("DATA");
							NXTSettingsGUI.InGameMouseVSensitivitySlider.setValue(rs.getInt("DATA"));
						}
						break;

					case Storage.CACHE_KEY_VT_VARC_HOVER_OVER_TOOLTIPS:
							Storage.nxtClientSettings_MouseOverTooltip = rs.getString("DATA").equals("1");
							NXTSettingsGUI.InGameMouseOverPopupsCheckbox.setSelected(rs.getString("DATA").equals("1"));
						break;

					case Storage.CACHE_KEY_VT_VARC_TASKS_COMPLETED_POPUP:
							Storage.nxtClientSettings_TaskCompletedPopup = rs.getString("DATA").equals("0");
							NXTSettingsGUI.InGameTaskPopupsCheckbox.setSelected(rs.getString("DATA").equals("0"));
						break;

					case Storage.CACHE_KEY_VT_VERC_LOOP_CURRENT_TRACK:
							Storage.nxtClientSettings_LoopCurrentTrack = rs.getString("DATA").equals("1");
							NXTSettingsGUI.LoopCurrentMusicTrackCheckbox.setSelected(rs.getString("DATA").equals("1"));
						break;

					case Storage.CACHE_KEY_VT_VARC_WORLD_SORTING:
						if (rs.getInt("DATA") > NXTSettingsGUI.InGameWorldSortingComboBox.getItemCount()){
							Storage.nxtClientSettings_WorldSorting = NXTSettingsGUI.InGameWorldSortingComboBox.getItemCount();
							NXTSettingsGUI.InGameWorldSortingComboBox.setSelectedIndex(NXTSettingsGUI.InGameWorldSortingComboBox.getItemCount());
						}
						else {
							Storage.nxtClientSettings_WorldSorting = rs.getInt("DATA");
							NXTSettingsGUI.InGameWorldSortingComboBox.setSelectedIndex(rs.getInt("DATA"));
						}
						break;

					case Storage.CACHE_KEY_VT_VARC_EMOTE_SORTING:
						if (rs.getInt("DATA") > NXTSettingsGUI.InGameEmoteSortingComboBox.getItemCount()){
							Storage.nxtClientSettings_EmoteSorting = NXTSettingsGUI.InGameEmoteSortingComboBox.getItemCount();
							NXTSettingsGUI.InGameEmoteSortingComboBox.setSelectedIndex(NXTSettingsGUI.InGameEmoteSortingComboBox.getItemCount());
						}
						else {
							Storage.nxtClientSettings_EmoteSorting = rs.getInt("DATA");
							NXTSettingsGUI.InGameEmoteSortingComboBox.setSelectedIndex(rs.getInt("DATA"));
						}
						break;

					case Storage.CACHE_KEY_VT_VERC_MUSIC_SORTING:
						if (rs.getInt("DATA") >= NXTSettingsGUI.InGameMusicSortingComboBox.getItemCount()){
							Storage.nxtClientSettings_MusicSorting = NXTSettingsGUI.InGameMusicSortingComboBox.getItemCount();
							NXTSettingsGUI.InGameMusicSortingComboBox.setSelectedIndex(NXTSettingsGUI.InGameMusicSortingComboBox.getItemCount());
						}
						else {
							Storage.nxtClientSettings_MusicSorting = rs.getInt("DATA");
							NXTSettingsGUI.InGameMusicSortingComboBox.setSelectedIndex(rs.getInt("DATA"));
						}
						break;

					case Storage.CACHE_KEY_VT_VARC_ABILITY_BAR_MINIMIZED:
							Storage.nxtClientSettings_AbilityBarMinimized = rs.getString("DATA").equals("1");
							NXTSettingsGUI.MinimizeMainAbilityBarCheckBox.setSelected(rs.getString("DATA").equals("1"));
						break;

					case Storage.CACHE_KEY_VT_VARC_OoO_MOVEMENT_SPEED:
						if (rs.getInt("DATA") > NXTSettingsGUI.OoOMovementSpeedSlider.getMaximum()){
							Storage.nxtClientSettings_OoOMovementSpeed = NXTSettingsGUI.OoOMovementSpeedSlider.getMaximum();
							NXTSettingsGUI.OoOMovementSpeedSlider.setValue(NXTSettingsGUI.OoOMovementSpeedSlider.getMaximum());
						}
						else if (rs.getInt("DATA") < NXTSettingsGUI.OoOMovementSpeedSlider.getMinimum()){
							Storage.nxtClientSettings_OoOMovementSpeed = NXTSettingsGUI.OoOMovementSpeedSlider.getMinimum();
							NXTSettingsGUI.OoOMovementSpeedSlider.setValue(NXTSettingsGUI.OoOMovementSpeedSlider.getMinimum());
						}
						else {
							Storage.nxtClientSettings_OoOMovementSpeed = rs.getInt("DATA");
							NXTSettingsGUI.OoOMovementSpeedSlider.setValue(rs.getInt("DATA"));
						}
						break;

					case Storage.CACHE_KEY_VT_VARC_OoO_ROTATION_SPEED:
						if (rs.getInt("DATA") > NXTSettingsGUI.OoORotationSpeedSlider.getMaximum()){
							Storage.nxtClientSettings_OoORotationSpeed = NXTSettingsGUI.OoORotationSpeedSlider.getMaximum();
							NXTSettingsGUI.OoORotationSpeedSlider.setValue(NXTSettingsGUI.OoORotationSpeedSlider.getMaximum());
						}
						else if (rs.getInt("DATA") < NXTSettingsGUI.OoORotationSpeedSlider.getMinimum()){
							Storage.nxtClientSettings_OoORotationSpeed = NXTSettingsGUI.OoORotationSpeedSlider.getMinimum();
							NXTSettingsGUI.OoORotationSpeedSlider.setValue(NXTSettingsGUI.OoORotationSpeedSlider.getMinimum());
						}
						else {
							Storage.nxtClientSettings_OoORotationSpeed = rs.getInt("DATA");
							NXTSettingsGUI.OoORotationSpeedSlider.setValue(rs.getInt("DATA"));
						}
						break;
				}
			}
			rs.close();
		} catch(final SQLException e) {}

	}


	public static void ReadDeveloperConsoleHistory(){
		/*
		 * > Check every entry in the console table
		 *
		 * > Clear the displayed list
		 *
		 * > Populate the list with all the values.
		 *
		 * > Save the values as an array.
		 *
		 */

		try(ResultSet rs = Storage.stmt.executeQuery("SELECT * FROM 'console'")) {
			for (int i = 0; i < 100; i++){
				NXTSettingsGUI.DeveloperConsoleHistoryTable.setValueAt("", i, 1);
			}
			int DeveloperConsoleIndex = 0;
			while (rs.next()) {
				Storage.nxtClientSettings_DeveloperConsoleLog.add(rs.getString("DATA"));
				if (DeveloperConsoleIndex < 100){
					NXTSettingsGUI.DeveloperConsoleHistoryTable.setValueAt(rs.getString("DATA"), DeveloperConsoleIndex, 1);
				}
				DeveloperConsoleIndex++;
			}
			Storage.nxtClientSettings_DeveloperConsoleLogs = Storage.nxtClientSettings_DeveloperConsoleLog.toArray(new String[0]);
			rs.close();
		}	catch(final SQLException e) {}
	}

	public static void ReadProgramOptions(){
		/*
		 * > Check every entry in the Config-External table
		 *  > If the table doesn't exist, create it using the default values.
		 *
		 * > If some of the options are missing (counter-wise) recreate the table using the default values.
		 *
		 * > Set the In-Program options based on the information that can be available.
		 *
		 * > TO-DO: Make more options/Easter Eggs.
		 */


		boolean ExternalConfigExists = false;
		int DeveloperValueCounter = 0;
		try(ResultSet Count = Storage.stmt.executeQuery("SELECT COUNT(*) FROM 'Config-External'")) {
			if (Count.next()) {
				if (Count.getInt(1) != Storage.ProgramDeveloperValues.length) {
					// There was a value which was invalid. Re-initialize the table.
					RecreateDeveloperValues();
				}
			}
		} catch (final SQLException e2) {
			// Table doesn't exist. Create it with default values.
			if (!ExternalConfigExists) {
				try {
					if(Storage.stmt.execute("CREATE TABLE 'Config-External'(KEY STRING PRIMARY KEY UNIQUE,DATA TEXT)")) {
						ExternalConfigExists = true;
					}
					RecreateDeveloperValues();
				}	catch (final SQLException e4) {}
			}
		}

		try(ResultSet rs = Storage.stmt.executeQuery("SELECT * FROM 'Config-External'")) {
			while (rs.next()) {
				ExternalConfigExists = true;
				switch(rs.getString("KEY")) {

					// Personal In-Application Debuggers;
					// These values are NOT part of NXT, and ONLY for this program for testing purposes.
					// ALWAYS make `DEVELOPER_DEBUGS_ENABLED` in the cache before any other debugging settings.

					case "TableCreated (yyyy-MM-dd hh:mm:ss)":
						DeveloperValueCounter++;
						break;

					case "DEVELOPER_DEBUGS_ENABLED":
						DeveloperValueCounter++;
						if (rs.getString("DATA").equals("1") ||
							rs.getString("DATA").toLowerCase().equals("t") ||
							rs.getString("DATA").toLowerCase().equals("true")) {
							Storage.DEVELOPER_DebugsEnabled = true;
						}	else {
							Storage.DEVELOPER_DebugsEnabled = false;
						}
						break;

					case "DEVELOPER_ALWAYS_SHOW_SENSITIVE_INFO":
						DeveloperValueCounter++;
						if (rs.getString("DATA").equals("1") ||
							rs.getString("DATA").toLowerCase().equals("t") ||
							rs.getString("DATA").toLowerCase().equals("true")) {
							if (Storage.DEVELOPER_DebugsEnabled) {
								Storage.DEVELOPER_AlwaysShowSensitiveInfo = true;
								NXTSettingsGUI.ShowSensitiveInformation.setSelected(true);
								NXTSettingsGUI.ShowSensitiveInformation.setEnabled(false);
								NXTSettingsGUI.ShowSensitiveInformation.setFont(new Font("Dialog", Font.PLAIN, 11));
								NXTSettingsGUI.ShowSensitiveInformation.setText("<Always show sensitive Info>");
								NXTSettingsGUI.ShowSensitiveInformation.setToolTipText(
										"<html>"+
											"The in-program option 'DEVELOPER_ALWAYS_SHOW_SENSITIVE_INFO' was set in the cache.<br><br>"+
											"This means that the sensitive information in your cache will always be shown.<br>"+
											"Note: This is a redundant feature, but part of adding in user-disabled features."+
										"</html>");

							}	else {
								System.out.println("The in-program option 'DEVELOPER_DEBUGS_ENABLED' was not set in the cache first. As a result 'DEVELOPER_ALWAYS_SHOW_SENSITIVE_INFO' will not be set.");
								Storage.DEVELOPER_AlwaysShowSensitiveInfo = false;
							}
						}	else {
							Storage.DEVELOPER_AlwaysShowSensitiveInfo = false;
							NXTSettingsGUI.ShowSensitiveInformation.setEnabled(true);
							NXTSettingsGUI.ShowSensitiveInformation.setFont(new Font("Dialog", Font.PLAIN, 12));
							NXTSettingsGUI.ShowSensitiveInformation.setText("Show sensitive info?");
							NXTSettingsGUI.ShowSensitiveInformation.setToolTipText("Show sensitive information such as your username?");
						}
						break;

					case "DEVELOPER_READ_ONLY_CACHE":
						DeveloperValueCounter++;
						if (rs.getString("DATA").equals("1") ||
							rs.getString("DATA").toLowerCase().equals("t") ||
							rs.getString("DATA").toLowerCase().equals("true")) {
							if (Storage.DEVELOPER_DebugsEnabled) {
								Storage.DEVELOPER_ReadOnlyCache = true;
								NXTSettingsGUI.WriteSettings.setEnabled(false);
								NXTSettingsGUI.AllowWritingCheckbox.setEnabled(false);
								NXTSettingsGUI.AllowWritingCheckbox.setSelected(false);
								NXTSettingsGUI.AllowWritingCheckbox.setText("<Read-Only Cache>");
								NXTSettingsGUI.AllowWritingCheckbox.setToolTipText(
										"<html>"+
											"The in-program option 'DEVELOPER_READ_ONLY_CACHE' was set in the cache.<br><br>"+
											"This means that the cache is not allowed to be saved using this program."+
										"</html>");
							}	else {
								System.out.println("The in-program option 'DEVELOPER_DEBUGS_ENABLED' was not set in the cache first. As a result 'DEVELOPER_READ_ONLY_CACHE' will not be set.");
								Storage.DEVELOPER_ReadOnlyCache = false;
							}
						}	else {
							Storage.DEVELOPER_ReadOnlyCache = false;
							NXTSettingsGUI.AllowWritingCheckbox.setEnabled(true);
							NXTSettingsGUI.AllowWritingCheckbox.setText("Enable writing?");
							NXTSettingsGUI.AllowWritingCheckbox.setToolTipText("Allow writing of all settings when 'Write Settings' is clicked. Some special mechanic values will be written instantly.");
						}
					break;

					case "DEVELOPER_ALWAYS_STAY_ON_TOP":
						DeveloperValueCounter++;
						if (rs.getString("DATA").equals("1") ||
							rs.getString("DATA").toLowerCase().equals("t") ||
							rs.getString("DATA").toLowerCase().equals("true")) {
							Storage.DEVELOPER_WindowAlwaysOnTop = true;
							NXTSettingsGUI.frame.setAlwaysOnTop(true);
						}	else {
							Storage.DEVELOPER_WindowAlwaysOnTop = false;
						}
						break;

					case "DEVELOPER_ALWAYS_START_ON_TOP":
						DeveloperValueCounter++;
						if (rs.getString("DATA").equals("1") ||
							rs.getString("DATA").toLowerCase().equals("t") ||
							rs.getString("DATA").toLowerCase().equals("true")) {
							NXTSettingsGUI.frame.setAlwaysOnTop(true);
						}
						break;
				}
			}
			if(DeveloperValueCounter != Storage.ProgramDeveloperValues.length){
				// There was a value which was invalid. Re-initialize the table.
				RecreateDeveloperValues();
			}
			rs.close();
		}	catch(final SQLException e) {}
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
		 *  > Boolean-type entries will be manually 1 <> 0 dependent on their value related to true <> false
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
			Mechanics.DeselectDevConsole();

			if (History.nxtGraphicsSetting_RemoveRoofs != Storage.nxtGraphicsSetting_RemoveRoofs) {
				Write(true,	"RemoveRoof",			Storage.nxtGraphicsSetting_RemoveRoofs);
			}
			if (History.nxtGraphicsSetting_DrawDistance != Storage.nxtGraphicsSetting_DrawDistance) {
				Write(true,	"DrawDistance",			Storage.nxtGraphicsSetting_DrawDistance);
			}
			if (History.nxtGraphicsSetting_ShadowQuality != Storage.nxtGraphicsSetting_ShadowQuality) {
				Write(true,	"ShadowQuality",		Storage.nxtGraphicsSetting_ShadowQuality);
			}
			if (History.nxtGraphicsSetting_VSync != Storage.nxtGraphicsSetting_VSync) {
				Write(true,	"VSync",				Storage.nxtGraphicsSetting_VSync);
			}
			if (History.nxtGraphicsSetting_AntiAliasingMode != Storage.nxtGraphicsSetting_AntiAliasingMode) {
				Write(true,	"AntialiasingMode",		Storage.nxtGraphicsSetting_AntiAliasingMode);
			}
			if (History.nxtGraphicsSetting_AntiAliasingQuality != Storage.nxtGraphicsSetting_AntiAliasingQuality) {
				Write(true,	"AntialiasingQuality",	Storage.nxtGraphicsSetting_AntiAliasingQuality);
			}
			if (History.nxtGraphicsSetting_WaterQuality != Storage.nxtGraphicsSetting_WaterQuality) {
				Write(true,	"Reflections",			Storage.nxtGraphicsSetting_WaterQuality);
			}
			if (History.nxtGraphicsSetting_LightingQuality != Storage.nxtGraphicsSetting_LightingQuality) {
				Write(true,	"LightingQuality",		Storage.nxtGraphicsSetting_LightingQuality);
			}
			if (History.nxtGraphicsSetting_AmbientOcclusion != Storage.nxtGraphicsSetting_AmbientOcclusion) {
				Write(true,	"AmbientOcclusion",		Storage.nxtGraphicsSetting_AmbientOcclusion);
			}
			if (History.nxtGraphicsSetting_Bloom != Storage.nxtGraphicsSetting_Bloom) {
				Write(true,	"Bloom",				Storage.nxtGraphicsSetting_Bloom);
			}
			if (History.nxtGraphicsSetting_MaxForegroundFps != Storage.nxtGraphicsSetting_MaxForegroundFps) {
				Write(true,	"MaxForegroundFps",		Storage.nxtGraphicsSetting_MaxForegroundFps);
			}
			if (History.nxtClientSettings_UIScaling != Storage.nxtClientSettings_UIScaling) {
				Write(true,	"InterfaceScale",		Storage.nxtClientSettings_UIScaling);
			}
			if (History.nxtGraphicsSetting_Brightness != Storage.nxtGraphicsSetting_Brightness) {
				Write(true,	"Brightness",			Storage.nxtGraphicsSetting_Brightness);
			}
			if (History.nxtGraphicsSetting_TextureQuality != Storage.nxtGraphicsSetting_TextureQuality) {
				Write(true,	"Texturing",			Storage.nxtGraphicsSetting_TextureQuality);
			}
			if (History.nxtGraphicsSetting_AnisotropicFiltering != Storage.nxtGraphicsSetting_AnisotropicFiltering) {
				Write(true,	"AnisotropicFiltering",	Storage.nxtGraphicsSetting_AnisotropicFiltering);
			}
			if (History.nxtGraphicsSetting_VolumetricLighting != Storage.nxtGraphicsSetting_VolumetricLighting) {
				Write(true,	"VolumetricLighting",	Storage.nxtGraphicsSetting_VolumetricLighting);
			}
			if (History.nxtGraphicsSetting_FlickeringEffects != Storage.nxtGraphicsSetting_FlickeringEffects) {
				if (Storage.nxtGraphicsSetting_FlickeringEffects) {
					Write(true,	"FlickeringEffects", 1);
				}	else {
					Write(true,	"FlickeringEffects", 0);
				}
			}
			if (History.nxtGraphicsSetting_Shadows != Storage.nxtGraphicsSetting_Shadows) {
				if (Storage.nxtGraphicsSetting_Shadows) {
					Write(true,	"Shadows", 1);
				}	else {
					Write(true,	"Shadows", 0);
				}
			}
			if (History.nxtGraphicsSetting_CustomCursors != Storage.nxtGraphicsSetting_CustomCursors) {
				if (Storage.nxtGraphicsSetting_CustomCursors) {
					Write(true,	 "CustomCursors",							1);
					Write(false, Storage.CACHE_KEY_VT_VARC_CUSTOM_CURSORS,	1);
				}	else {
					Write(true,	 "CustomCursors",							0);
					Write(false, Storage.CACHE_KEY_VT_VARC_CUSTOM_CURSORS,	0);
				}
			}
			if (History.nxtGraphicsSetting_LoadingScreens != Storage.nxtGraphicsSetting_LoadingScreens) {
				if (Storage.nxtGraphicsSetting_LoadingScreens) {
					Write(false, Storage.CACHE_KEY_VT_VARC_LOADING_SCREENS,	1);
				}	else {
					Write(false, Storage.CACHE_KEY_VT_VARC_LOADING_SCREENS,	0);
				}
			}
			if (History.nxtGraphicsSetting_GroundDecor != Storage.nxtGraphicsSetting_GroundDecor) {
				if (Storage.nxtGraphicsSetting_GroundDecor) {
					Write(true,	"GroundDecor",	1);
				}	else {
					Write(true,	"GroundDecor",	0);
				}
			}
			if (History.nxtGraphicsSetting_TerrainBlending != Storage.nxtGraphicsSetting_TerrainBlending) {
				if (Storage.nxtGraphicsSetting_TerrainBlending) {
					Write(true,	"GroundBlending",	1);
				}	else {
					Write(true,	"GroundBlending",	0);
				}
			}
			if (History.nxtGraphicsSetting_MaxBackgroundFps != Storage.nxtGraphicsSetting_MaxBackgroundFps) {
				Write(true,		"MaxBackgroundFps",											Storage.nxtGraphicsSetting_MaxBackgroundFps);
			}
			if (History.nxtClientSettings_GameWorldScaling != Storage.nxtClientSettings_GameWorldScaling) {
				Write(true,		"GameRenderScale",											Storage.nxtClientSettings_GameWorldScaling);
			}

			if (History.nxtClientSettings_CameraZoom != Storage.nxtClientSettings_CameraZoom) {
				Write(false,		Storage.CACHE_KEY_VT_VARC_CAMERA_ZOOM,	Storage.nxtClientSettings_CameraZoom);
			}
			if (History.nxtClientSettings_KeyboardHSensitivity != Storage.nxtClientSettings_KeyboardHSensitivity) {
				Write(false,		Storage.CACHE_KEY_VT_VARC_KEYBOARD_H_SENSITIVITY,	Storage.nxtClientSettings_KeyboardHSensitivity);
			}
			if (History.nxtClientSettings_KeyboardVSensitivity != Storage.nxtClientSettings_KeyboardVSensitivity) {
				Write(false,		Storage.CACHE_KEY_VT_VARC_KEYBOARD_V_SENSITIVITY,	Storage.nxtClientSettings_KeyboardVSensitivity);
			}
			if (History.nxtClientSettings_MouseHSensitivity != Storage.nxtClientSettings_MouseHSensitivity) {
				Write(false,		Storage.CACHE_KEY_VT_VARC_MOUSE_H_SENSITIVITY,	Storage.nxtClientSettings_MouseHSensitivity);
			}
			if (History.nxtClientSettings_MouseVSensitivity != Storage.nxtClientSettings_MouseVSensitivity) {
				Write(false,		Storage.CACHE_KEY_VT_VARC_MOUSE_V_SENSITIVITY,	Storage.nxtClientSettings_MouseVSensitivity);
			}
			if (History.nxtClientSettings_LoginMusicVolume != Storage.nxtClientSettings_LoginMusicVolume) {
				Write(true,		"VolumeLoginMusic",	Storage.nxtClientSettings_LoginMusicVolume);
			}
			if (History.nxtClientSettings_InGameMusicVolume != Storage.nxtClientSettings_InGameMusicVolume) {
				Write(true,		"VolumeMainMusic",	Storage.nxtClientSettings_InGameMusicVolume);
			}
			if (History.nxtClientSettings_InGameSoundEffectsVolume != Storage.nxtClientSettings_InGameSoundEffectsVolume) {
				Write(true,		"VolumeMainEffects",	Storage.nxtClientSettings_InGameSoundEffectsVolume);
			}
			if (History.nxtClientSettings_InGameAmbientSoundEffectsVolume != Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume) {
				Write(true,		"VolumeBackgroundEffects",	Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume);
			}
			if (History.nxtClientSettings_InGameVoiceOverVolume != Storage.nxtClientSettings_InGameVoiceOverVolume) {
				Write(true,		"VolumeSpeech",	Storage.nxtClientSettings_InGameVoiceOverVolume);
			}
			if (History.nxtClientSettings_MusicSorting != Storage.nxtClientSettings_MusicSorting) {
				Write(false, Storage.CACHE_KEY_VT_VERC_MUSIC_SORTING,	Storage.nxtClientSettings_MusicSorting);
			}
			if (History.nxtClientSettings_WorldSorting != Storage.nxtClientSettings_WorldSorting) {
				Write(false, Storage.CACHE_KEY_VT_VARC_WORLD_SORTING,	Storage.nxtClientSettings_WorldSorting);
			}
			if (History.nxtClientSettings_EmoteSorting != Storage.nxtClientSettings_EmoteSorting) {
				Write(false, Storage.CACHE_KEY_VT_VARC_EMOTE_SORTING,	Storage.nxtClientSettings_EmoteSorting);
			}
			if (History.nxtClientSettings_OoOMovementSpeed != Storage.nxtClientSettings_OoOMovementSpeed) {
				Write(false, Storage.CACHE_KEY_VT_VARC_OoO_MOVEMENT_SPEED,	Storage.nxtClientSettings_OoOMovementSpeed);
			}
			if (History.nxtClientSettings_OoORotationSpeed != Storage.nxtClientSettings_OoORotationSpeed) {
				Write(false, Storage.CACHE_KEY_VT_VARC_OoO_ROTATION_SPEED,	Storage.nxtClientSettings_OoORotationSpeed);
			}
			if (History.nxtClientSettings_AbilityBarMinimized != Storage.nxtClientSettings_AbilityBarMinimized) {
				if (Storage.nxtClientSettings_AbilityBarMinimized) {
					Write(false, Storage.CACHE_KEY_VT_VARC_ABILITY_BAR_MINIMIZED,	1);
				}	else {
					Write(false, Storage.CACHE_KEY_VT_VARC_ABILITY_BAR_MINIMIZED,	0);
				}
			}

			if (History.nxtClientSettings_LoopCurrentTrack != Storage.nxtClientSettings_LoopCurrentTrack) {
				if (Storage.nxtClientSettings_LoopCurrentTrack) {
					Write(false, Storage.CACHE_KEY_VT_VERC_LOOP_CURRENT_TRACK,	1);
				}	else {
					Write(false, Storage.CACHE_KEY_VT_VERC_LOOP_CURRENT_TRACK,	0);
				}
			}
			if (History.nxtClientSettings_MouseOverTooltip != Storage.nxtClientSettings_MouseOverTooltip) {
				if (Storage.nxtClientSettings_MouseOverTooltip) {
					Write(false, Storage.CACHE_KEY_VT_VARC_HOVER_OVER_TOOLTIPS,	1);
				}	else {
					Write(false, Storage.CACHE_KEY_VT_VARC_HOVER_OVER_TOOLTIPS,	0);
				}
			}
			if (History.nxtClientSettings_TaskCompletedPopup != Storage.nxtClientSettings_TaskCompletedPopup) {
				if (Storage.nxtClientSettings_TaskCompletedPopup) {
					//Intentionally Swapped.
					Write(false, Storage.CACHE_KEY_VT_VARC_TASKS_COMPLETED_POPUP,	0);
				}	else {
					Write(false, Storage.CACHE_KEY_VT_VARC_TASKS_COMPLETED_POPUP,	1);
				}
			}

			Legality.CheckSettingsBeforeSave();

			if ((History.nxtClientSettings_RememberUsername != Storage.nxtClientSettings_RememberUsername) ||
				(History.nxtClientSettings_TemporaryUsername != Storage.nxtClientSettings_TemporaryUsername)) {
				if (Storage.nxtClientSettings_RememberUsername &&
					(Storage.nxtClientSettings_TemporaryUsername != null) &&
				   !Storage.nxtClientSettings_TemporaryUsername.equals("")) {
					Write(false, Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME,		Storage.nxtClientSettings_TemporaryUsername);
					Write(false, Storage.CACHE_KEY_VT_VARC_REMEMBER_USERNAME,	1);
				}	else {
					Write(false, Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME,		"");
					Write(false, Storage.CACHE_KEY_VT_VARC_REMEMBER_USERNAME,	0);
				}
			}
			Storage.nxtClientSettings_TemporaryUsername = "";
			Storage.nxtClientSettings_TemporaryUserID = "";

			if (History.nxtClientSettings_FavouriteWorld1 != Storage.nxtClientSettings_FavouriteWorld1) {
				if (Storage.nxtClientSettings_FavouriteWorld1 <= 0){
					Write(false, Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1,	-1);	
				} else {
					Write(false, Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1,	Storage.nxtClientSettings_FavouriteWorld1);
				}
			}
			if (History.nxtClientSettings_FavouriteWorld2 != Storage.nxtClientSettings_FavouriteWorld2) {
				if (Storage.nxtClientSettings_FavouriteWorld1 <= 0){
					Write(false, Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2,	-1);	
				} else {
					Write(false, Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2,	Storage.nxtClientSettings_FavouriteWorld2);
				}
			}
			if (History.nxtClientSettings_FavouriteWorld3 != Storage.nxtClientSettings_FavouriteWorld3) {
				if (Storage.nxtClientSettings_FavouriteWorld1 <= 0){
					Write(false, Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3,	-1);	
				} else {
					Write(false, Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3,	Storage.nxtClientSettings_FavouriteWorld3);
				}
			}

			if (History.nxtClientSettings_LoginWallpaperID != Storage.nxtClientSettings_LoginWallpaperID) {
				Write(false, Storage.CACHE_KEY_VT_VERC_WALLPAPER_ID,		Storage.nxtClientSettings_LoginWallpaperID);
			}
			if (History.nxtClientSettings_RandomizeLoginWallpaper != Storage.nxtClientSettings_RandomizeLoginWallpaper) {
				if (Storage.nxtClientSettings_RandomizeLoginWallpaper) {
					Write(false, Storage.CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER,	1);
				}	else {
					Write(false, Storage.CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER,	0);
				}
			}

			Storage.stmt.addBatch("DELETE FROM 'console';");
			int DeveloperConsoleHistoryIndex = 0;
			for (int i = 0; i < 100; i++){
				if (NXTSettingsGUI.DeveloperConsoleHistoryTable.getValueAt(i, 1) != null){
					if (!NXTSettingsGUI.DeveloperConsoleHistoryTable.getValueAt(i, 1).toString().trim().equals("")){
						Storage.stmt.addBatch("INSERT INTO 'console' ('KEY', 'DATA') "+
									  		  "VALUES ('"+DeveloperConsoleHistoryIndex+"', '"+NXTSettingsGUI.DeveloperConsoleHistoryTable.getValueAt(i, 1)+"');");
						DeveloperConsoleHistoryIndex++;
					}
				}
			}

			Storage.stmt.executeBatch();
			Storage.stmt.clearBatch();

			JCache.ReadDeveloperConsoleHistory();

			final Path Preferences = Storage.preferences_config.toPath();
			List<String> fileContent;
			try {
				fileContent = new ArrayList<>(Files.readAllLines(Preferences, StandardCharsets.UTF_8));
				for (int i = 0; i < fileContent.size(); i++) {
				    if (fileContent.get(i).startsWith("compatibility=")) {
				    	String Type = "true";
				    	if (!Storage.nxtClientSettings_CompatibilityMode) {
				    		Type = "false";
				    	}
				        fileContent.set(i, "compatibility="+Type);
				    }
				    if (fileContent.get(i).startsWith("dont_ask_graphics=")) {
				    	String Type = "1";
				    	if (!Storage.nxtClientSettings_AskToSwitchToCompatibility) {
				    		Type = "0";
				    	}
				        fileContent.set(i, "dont_ask_graphics="+Type);
				    }
				    if (fileContent.get(i).startsWith("confirm_quit=")) {
				    	String Type = "1";
				    	if (!Storage.nxtClientSettings_AskBeforeQuitting) {
				    		Type = "0";
				    	}
				        fileContent.set(i, "confirm_quit="+Type);
				    }
				    if (fileContent.get(i).startsWith("Language=")) {
				        fileContent.set(i, "Language="+Storage.nxtClientSettings_LanguageSelected);
				    }
				}
				Files.write(Preferences, fileContent, StandardCharsets.UTF_8);
			}	catch (final IOException e) {
				e.printStackTrace();
			}

			System.out.print(" Saved.\n");
			JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Saved updated values.");
			History.init();
		}	catch(final SQLException e) {
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
			if (((Storage.conn == null) || (Storage.stmt == null)) &&
				((Storage.Cache_settings_location != null) && !Storage.Cache_settings_location.trim().equals(""))) {
				 Storage.conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
				 Storage.stmt = Storage.conn.createStatement();
			}
			if (isConfigTable) {
				Table = "Config";
			}	else {
				Table = "vt-varc";
			}
			if (!Table.equals("") && (Key != null) && !Key.equals("") && (Value != null)) {
			Storage.stmt.addBatch("DELETE FROM '"+Table+"' "+
						  		  "WHERE KEY='"+Key+"';");
			Storage.stmt.addBatch("INSERT INTO '"+Table+"' ('KEY', 'DATA')" +
								  "VALUES ('"+Key+"', '" + Value + "');");
			}
		}	catch (final SQLException e) {
			e.printStackTrace();
		}
	}
	private static void RecreateDeveloperValues(){
		try {
			Storage.stmt.addBatch("DELETE FROM 'Config-External';");
			Storage.stmt.addBatch("INSERT INTO 'Config-External' ('KEY', 'DATA') VALUES ('TableCreated (yyyy-MM-dd hh:mm:ss)', '"+ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))+" UTC');");
			for(int i = 1; i < Storage.ProgramDeveloperValues.length; i++){
				Storage.stmt.addBatch("INSERT INTO 'Config-External' ('KEY', 'DATA') VALUES ('"+Storage.ProgramDeveloperValues[i]+"', 'false');");
			}
			Storage.stmt.executeBatch();
			Storage.stmt.clearBatch();
		}	catch(final SQLException e1) {}
	}
}
