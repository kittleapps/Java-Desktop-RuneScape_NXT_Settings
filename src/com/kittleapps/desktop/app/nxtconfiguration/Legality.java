package com.kittleapps.desktop.app.nxtconfiguration;

public class Legality {
	public static void CheckSettings() {
		if (Storage.nxtGraphicsSetting_TerrainBlending == false) {
			Storage.nxtGraphicsSetting_TextureQuality = 0;
			NXTSettingsGUI.TextureQualityComboBox.setEnabled(false);
		} else {
			NXTSettingsGUI.TextureQualityComboBox.setEnabled(true);
		}
		if (Storage.nxtGraphicsSetting_Shadows == false) {
			Storage.nxtGraphicsSetting_VolumetricLighting = 0;
			Storage.nxtGraphicsSetting_ShadowQuality = 0;
			NXTSettingsGUI.VolumetricLightingComboBox.setEnabled(false);
			NXTSettingsGUI.ShadowQualityComboBox.setEnabled(false);
		} else {
			NXTSettingsGUI.VolumetricLightingComboBox.setEnabled(true);
			NXTSettingsGUI.ShadowQualityComboBox.setEnabled(true);
		}
		if (Storage.nxtGraphicsSetting_AntiAliasingMode == 0) {
			Storage.nxtGraphicsSetting_AntiAliasingQuality = 0;
			NXTSettingsGUI.AntiAliasingQualityComboBox.setEnabled(false);
		} else {
			NXTSettingsGUI.AntiAliasingQualityComboBox.setEnabled(true);
		}
	}

	public static void CheckSettingsBeforeSave() {
		Storage.nxtClientSettings_TemporaryUsername = NXTSettingsGUI.UsernameInput.getText();
		Storage.nxtClientSettings_FavouriteWorld1 = new Integer(NXTSettingsGUI.FavouriteWorld1Input.getText());
		Storage.nxtClientSettings_FavouriteWorld2 = new Integer(NXTSettingsGUI.FavouriteWorld2Input.getText());
		Storage.nxtClientSettings_FavouriteWorld3 = new Integer(NXTSettingsGUI.FavouriteWorld3Input.getText());
		Storage.nxtClientSettings_LoginWallpaperID = new Integer(NXTSettingsGUI.WallpaperIDInput.getText());
		if (Storage.nxtClientSettings_FavouriteWorld1 == -1) {
			Storage.nxtClientSettings_FavouriteWorld2 = -1;
			Storage.nxtClientSettings_FavouriteWorld3 = -1;
		} else if (Storage.nxtClientSettings_FavouriteWorld2 == -1) {
			Storage.nxtClientSettings_FavouriteWorld3 = -1;
		}
	}
}