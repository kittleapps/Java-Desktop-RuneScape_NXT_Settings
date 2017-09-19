package com.kittleapps.desktop.app.nxtconfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Legality {
	public static void CheckSettings() {
		
		// Textures
		
		if (Storage.nxtGraphicsSetting_TerrainBlending == false) {
			Storage.nxtGraphicsSetting_TextureQuality = 0;
			NXTSettingsGUI.TextureQualityComboBox.setEnabled(false);
		} else {
			NXTSettingsGUI.TextureQualityComboBox.setEnabled(true);
		}
		
		// Shadows
		
		if (Storage.nxtGraphicsSetting_Shadows == false) {
			Storage.nxtGraphicsSetting_VolumetricLighting = 0;
			Storage.nxtGraphicsSetting_ShadowQuality = 0;
			NXTSettingsGUI.VolumetricLightingComboBox.setEnabled(false);
			NXTSettingsGUI.ShadowQualityComboBox.setEnabled(false);
		} else {
			NXTSettingsGUI.VolumetricLightingComboBox.setEnabled(true);
			NXTSettingsGUI.ShadowQualityComboBox.setEnabled(true);
		}
		
		
		// AA
		
		if (Storage.nxtGraphicsSetting_AntiAliasingMode == 0) {
			Storage.nxtGraphicsSetting_AntiAliasingQuality = 0;
			NXTSettingsGUI.AntiAliasingQualityComboBox.setEnabled(false);
		} else {
			NXTSettingsGUI.AntiAliasingQualityComboBox.setEnabled(true);
		}
		
		// Max Foreground FPS
		
		if (NXTSettingsGUI.MaxForegroundFpsInput.getText() == null ||
			NXTSettingsGUI.MaxForegroundFpsInput.getText().equals("")){
			NXTSettingsGUI.MaxForegroundFpsInput.setText(""+(Storage.FrameRate + 10));
		} else {
			Storage.nxtGraphicsSetting_MaxForegroundFps = new Integer(NXTSettingsGUI.MaxForegroundFpsInput.getText().trim().replace(",", ""));
		}
		
		if (Storage.nxtGraphicsSetting_MaxForegroundFps < 5){
			Storage.nxtGraphicsSetting_MaxForegroundFps = 5;
			NXTSettingsGUI.MaxForegroundFpsInput.setText("5");
		} else if (Storage.nxtGraphicsSetting_MaxForegroundFps > 300){
			Storage.nxtGraphicsSetting_MaxForegroundFps = 300;
			NXTSettingsGUI.MaxForegroundFpsInput.setText("300");
		}
		
		// Max Backgound FPS
		
		if (NXTSettingsGUI.MaxBackgroundFpsInput.getText() == null ||
			NXTSettingsGUI.MaxBackgroundFpsInput.getText().equals("")){
			NXTSettingsGUI.MaxBackgroundFpsInput.setText("30");
		}  else {
				Storage.nxtGraphicsSetting_MaxBackgroundFps = new Integer(NXTSettingsGUI.MaxBackgroundFpsInput.getText().trim().replace(",", ""));
		}
		
		if (Storage.nxtGraphicsSetting_MaxBackgroundFps < 5){
			Storage.nxtGraphicsSetting_MaxBackgroundFps = 5;
			NXTSettingsGUI.MaxBackgroundFpsInput.setText("5");
		} else if (Storage.nxtGraphicsSetting_MaxBackgroundFps > 300){
			Storage.nxtGraphicsSetting_MaxBackgroundFps = 300;
			NXTSettingsGUI.MaxBackgroundFpsInput.setText("300");
		}
		
		// Game Render Scaling
		
		if (NXTSettingsGUI.GameRenderScaleInput.getText() == null ||
				NXTSettingsGUI.GameRenderScaleInput.getText().equals("")){
				NXTSettingsGUI.GameRenderScaleInput.setText("100");
		} else {
				Storage.nxtClientSettings_GameRenderScale = new Integer(NXTSettingsGUI.GameRenderScaleInput.getText().trim().replace(",", ""));
		}
			
		if (Storage.nxtClientSettings_GameRenderScale < 33){
			Storage.nxtClientSettings_GameRenderScale = 33;
			NXTSettingsGUI.GameRenderScaleInput.setText("33");
		} else if (Storage.nxtClientSettings_GameRenderScale > 200){
			Storage.nxtClientSettings_GameRenderScale = 200;
			NXTSettingsGUI.GameRenderScaleInput.setText("200");
		}
		
		// Interface Scaling
		
		if (NXTSettingsGUI.InterfaceScaleInput.getText() == null ||
				NXTSettingsGUI.InterfaceScaleInput.getText().equals("")){
				NXTSettingsGUI.InterfaceScaleInput.setText("100");
		} else {
				Storage.nxtClientSettings_InterfaceScale = new Integer(NXTSettingsGUI.InterfaceScaleInput.getText().trim().replace(",", ""));
		}
			
		if (Storage.nxtClientSettings_InterfaceScale < 100){
			Storage.nxtClientSettings_InterfaceScale = 100;
			NXTSettingsGUI.InterfaceScaleInput.setText("100");
		} else if (Storage.nxtClientSettings_InterfaceScale > 400){
			Storage.nxtClientSettings_InterfaceScale = 400;
			NXTSettingsGUI.InterfaceScaleInput.setText("400");
		}
		
	}

	public static void CheckAudio(){
		// Required to check seperately, due to some awkward shifting values.
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + Settings.Cache_settings_location);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM \"vt-varc\"")) {
					while (rs.next()) {
						if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_GLOBAL_AUDIO_MUTE)) {
							Storage.nxtClientSettings_GlobalMute = rs.getString("DATA").equals("1");
							NXTSettingsGUI.GlobalAudioMuteCheckbox.setSelected(Storage.nxtClientSettings_GlobalMute);
						}
						else if (rs.getString("KEY").equals(Storage.CACHE_KEY_VT_VARC_IN_GAME_MUSIC_VOLUME)) {
							Storage.nxtClientSettings_InGameMusicVolume = new Integer(rs.getString("DATA"));
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
						}
					}
					conn.close();
					stmt.close();
					rs.close();
				} catch(SQLException e) {
				}
		NXTSettingsGUI.LoginMusicSlider.setValue(Storage.nxtClientSettings_LoginMusicVolume);	
		NXTSettingsGUI.InGameMusicSlider.setValue(Storage.nxtClientSettings_InGameMusicVolume);
		NXTSettingsGUI.InGameSoundEffectsSlider.setValue(Storage.nxtClientSettings_InGameSoundEffectsVolume);
		NXTSettingsGUI.InGameAmbientSoundEffectsSlider.setValue(Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume);
		NXTSettingsGUI.InGameVoiceOverSlider.setValue(Storage.nxtClientSettings_InGameVoiceOverVolume);
	}
	public static void CheckSettingsBeforeSave() {
		Storage.nxtClientSettings_TemporaryUsername = NXTSettingsGUI.UsernameInput.getText();
		if (NXTSettingsGUI.FavouriteWorld1Input.getText() == null ||
			NXTSettingsGUI.FavouriteWorld1Input.getText().equals("")){
			NXTSettingsGUI.FavouriteWorld1Input.setText("-1");
		}
		if (NXTSettingsGUI.FavouriteWorld2Input.getText() == null ||
			NXTSettingsGUI.FavouriteWorld2Input.getText().equals("")){
			NXTSettingsGUI.FavouriteWorld2Input.setText("-1");
		}
		if (NXTSettingsGUI.FavouriteWorld3Input.getText() == null ||
			NXTSettingsGUI.FavouriteWorld3Input.getText().equals("")){
			NXTSettingsGUI.FavouriteWorld3Input.setText("-1");
		}
		if (NXTSettingsGUI.WallpaperIDInput.getText() == null ||
			NXTSettingsGUI.WallpaperIDInput.getText().equals("")){
			NXTSettingsGUI.WallpaperIDInput.setText("0");
		}
		Storage.nxtClientSettings_FavouriteWorld1 =  new Integer(NXTSettingsGUI.FavouriteWorld1Input.getText().replace(",", ""));
		Storage.nxtClientSettings_FavouriteWorld2 =  new Integer(NXTSettingsGUI.FavouriteWorld2Input.getText().replace(",", ""));
		Storage.nxtClientSettings_FavouriteWorld3 =  new Integer(NXTSettingsGUI.FavouriteWorld3Input.getText().replace(",", ""));
		Storage.nxtClientSettings_LoginWallpaperID = new Integer(NXTSettingsGUI.WallpaperIDInput.getText().replace(",", ""));
		if (Storage.nxtClientSettings_FavouriteWorld1 <= 0) {
			Storage.nxtClientSettings_FavouriteWorld2 = -1;
			Storage.nxtClientSettings_FavouriteWorld3 = -1;
		} else if (Storage.nxtClientSettings_FavouriteWorld2 <= 0) {
				   Storage.nxtClientSettings_FavouriteWorld3 = -1;
		}
	}
}