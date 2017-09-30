package com.kittleapps.desktop.app.nxtsettings;

public class Legality {
	public static void CheckSettings() {

		/*
		 * > Check if Terrain/Ground Blending is disabled
		 * 	> If its value is disabled, disable Textures
		 *
		 * > Check if  Shadows are disabled
		 *  > If its value is disabled, disable Volumetric Lighting
		 *  > If its value is disabled, disable Shadow Quality
		 *
		 * > Check if Anti-Aliasing is disabled
		 *  > If its value is disabled, disable Anti-Aliasing Quality.
		 *
		 * > Check if Maximum Forefround FPS's input is null or empty
		 *  > If its value is null/empty, set its value to the default, otherwise apply it.
		 *
		 * > Check if the Maximum Foreground FPS's values are legal.
		 *  > If its value is under 5, set to 5
		 *  > If its value is over 300, set to 300
		 *
		 * > Check if  the Maximum Background FPS's value is null or empty
		 *  > If its value is null/empty, set its value to the default, otherwise apply it.
		 *
		 * > Check if the Maximum Background FPS's values are legal.
		 *  > If its value is under 5, set to 5
		 *  > If its value is over 300, set to 300
		 *
		 * > Check if  the Game Render Scaling's value is null or empty
		 *  > If its value is null/empty, set its value to the default, otherwise apply it.
		 *
		 * > Check if the Game Render Scaling's values are legal.
		 *  > If its value is under 33, set to 33
		 *  > If its value is over 200, set to 200
		 *
		 * > If the MInterface Scaling's value is null or empty
		 *  > If its value is null/empty, set its value to the default, otherwise apply it.
		 *
		 * > Check if the Interface Scaling's values are legal.
		 *  > If its value is under 100, set to 100
		 *  > If its value is over 400, set to 400
		 *
		 */

		// Terrain/Ground Blending.

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


		// Anti-Aliasing

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

	public static void CheckSettingsBeforeSave() {
		/*
		 * > Set the temporary username
		 *
		 * > Check if the username is null or empty
		 *  > If it's not null or empty, tick the remember Username checkbox
		 *
		 * > Check is the favourite world 1 is null or empty
		 *  > If null or empty, set to -1. (cleared slot)
		 *
		 * > Check is the favourite world 2 is null or empty
		 *  > If null or empty, set to -1. (cleared slot)
		 *
		 * > Check is the favourite world 3 is null or empty
		 *  > If null or empty, set to -1. (cleared slot)
		 *
		 * > Check is the wallpaper id is null or empty
		 *  > If null or empty, set to the default value
		 *
		 * > Apply the favourite world and wallpaper values
		 *
		 * > Check if any favourite worlds are -1 in favourite worlds 1+2
		 *  > If favourite world 1 is -1, set favourite worlds 2+3 to -1
		 *  > If favourtie world 2 is -1. set favorutie world 3 to -1
		 */
		Storage.nxtClientSettings_TemporaryUsername = NXTSettingsGUI.UsernameInput.getText();
		if (NXTSettingsGUI.UsernameInput.getText() != null &&
		   !NXTSettingsGUI.UsernameInput.getText().equals("")){
			NXTSettingsGUI.RememberUsernameCheckbox.setSelected(true);
		}
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