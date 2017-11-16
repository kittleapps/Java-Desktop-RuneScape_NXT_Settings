package com.kittleapps.desktop.app.nxtsettings;

public class Legality {
	public static void CheckSettings() {

		/*
		 * > Check if Terrain/Ground Blending is disabled
		 * 	> If its value is disabled, disable Textures
		 *
		 * > Check if Shadows are disabled
		 *  > If its value is disabled, disable Volumetric Lighting
		 *  > If its value is disabled, disable Shadow Quality
		 *
		 * > Check if Anti-Aliasing is disabled
		 *  > If its value is disabled, disable Anti-Aliasing Quality.
		 *
		 * > Check if the Game World Scaling's value is null or empty
		 *  > If its value is null/empty, set its value to the default, otherwise apply it.
		 *
		 * > Check if the Game World Scaling's values are legal.
		 *  > If its value is under 33, set to 33
		 *  > If its value is over 200, set to 200
		 *
		 * > If the UI Scaling's value is null or empty
		 *  > If its value is null/empty, set its value to the default, otherwise apply it.
		 *
		 * > Check if the UI Scaling's values are legal.
		 *  > If its value is under 100, set to 100
		 *  > If its value is over 400, set to 400
		 *
		 */

		// Terrain/Ground Blending.

		if (Storage.nxtGraphicsSetting_TerrainBlending == false) {
			NXTSettingsGUI.TextureQualityComboBox.setEnabled(false);
			Storage.nxtGraphicsSetting_TextureQuality = 0;
		}
		else {
			NXTSettingsGUI.TextureQualityComboBox.setEnabled(true);
			Storage.nxtGraphicsSetting_TextureQuality = NXTSettingsGUI.TextureQualityComboBox.getSelectedIndex();
		}

		// Shadows

		if (Storage.nxtGraphicsSetting_Shadows == false) {
			NXTSettingsGUI.VolumetricLightingComboBox.setEnabled(false);
			Storage.nxtGraphicsSetting_VolumetricLighting = 0;
			NXTSettingsGUI.ShadowQualityComboBox.setEnabled(false);
			Storage.nxtGraphicsSetting_ShadowQuality = 0;
		}
		else {
			NXTSettingsGUI.VolumetricLightingComboBox.setEnabled(true);
			Storage.nxtGraphicsSetting_VolumetricLighting = NXTSettingsGUI.VolumetricLightingComboBox.getSelectedIndex();
			NXTSettingsGUI.ShadowQualityComboBox.setEnabled(true);
			Storage.nxtGraphicsSetting_ShadowQuality = NXTSettingsGUI.ShadowQualityComboBox.getSelectedIndex();
		}


		// Anti-Aliasing

		if (Storage.nxtGraphicsSetting_AntiAliasingMode == 0) {
			NXTSettingsGUI.AntiAliasingQualityComboBox.setEnabled(false);
			Storage.nxtGraphicsSetting_AntiAliasingQuality = 0;
		}
		else {
			NXTSettingsGUI.AntiAliasingQualityComboBox.setEnabled(true);
			Storage.nxtGraphicsSetting_AntiAliasingQuality = NXTSettingsGUI.AntiAliasingQualityComboBox.getSelectedIndex();
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

		Storage.nxtClientSettings_RememberUsername = NXTSettingsGUI.RememberUsernameCheckbox.isSelected();
		Storage.nxtClientSettings_FavouriteWorld1 =  new Integer(NXTSettingsGUI.FavouriteWorld1Input.getText().replace(",", ""));
		Storage.nxtClientSettings_FavouriteWorld2 =  new Integer(NXTSettingsGUI.FavouriteWorld2Input.getText().replace(",", ""));
		Storage.nxtClientSettings_FavouriteWorld3 =  new Integer(NXTSettingsGUI.FavouriteWorld3Input.getText().replace(",", ""));
		Storage.nxtClientSettings_LoginWallpaperID = new Integer(NXTSettingsGUI.WallpaperIDInput.getText().replace(",", ""));

		if (Storage.nxtClientSettings_FavouriteWorld1 <= 0) {
			Storage.nxtClientSettings_FavouriteWorld2 = -1;
			Storage.nxtClientSettings_FavouriteWorld3 = -1;
		}
		else if (Storage.nxtClientSettings_FavouriteWorld2 <= 0) {
			Storage.nxtClientSettings_FavouriteWorld3 = -1;
		}
	}
}