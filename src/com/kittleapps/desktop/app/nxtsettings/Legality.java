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
		 * > Apply the favourite world values
		 *
		 * > Check if any favourite worlds are -1 in favourite worlds 1+2
		 *  > If favourite world 1 is -1, set favourite worlds 2+3 to -1
		 *  > If favourtie world 2 is -1. set favorutie world 3 to -1
		 */
		Storage.nxtClientSettings_TemporaryUsername = NXTSettingsGUI.UsernameInput.getText();
		if ((NXTSettingsGUI.UsernameInput.getText() != null) &&
		   !NXTSettingsGUI.UsernameInput.getText().equals("")){
			NXTSettingsGUI.RememberUsernameCheckbox.setSelected(true);
		}
		Storage.nxtClientSettings_RememberUsername = NXTSettingsGUI.RememberUsernameCheckbox.isSelected();

		if (Storage.nxtClientSettings_FavouriteWorld1 <= 0) {
			Storage.nxtClientSettings_FavouriteWorld2 = -1;
			Storage.nxtClientSettings_FavouriteWorld3 = -1;
			NXTSettingsGUI.FavouriteWorld2ComboBox.setSelectedItem(0);
			NXTSettingsGUI.FavouriteWorld3ComboBox.setSelectedItem(0);
		}
		else if (Storage.nxtClientSettings_FavouriteWorld2 <= 0) {
			Storage.nxtClientSettings_FavouriteWorld3 = -1;
			NXTSettingsGUI.FavouriteWorld3ComboBox.setSelectedItem(0);
		}
	}
}