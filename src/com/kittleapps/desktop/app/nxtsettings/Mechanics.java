package com.kittleapps.desktop.app.nxtsettings;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Mechanics {

	public static void SetGraphicsPreset(String PresetID) {
		switch (PresetID.toLowerCase()) {
		case "lowest":
		case "min":
		case "minimum":
			Storage.nxtGraphicsSetting_DrawDistance = 0;
			Storage.nxtGraphicsSetting_ShadowQuality = 0;
			Storage.nxtGraphicsSetting_AntiAliasingMode = 0;
			Storage.nxtGraphicsSetting_AntiAliasingQuality = 0;
			Storage.nxtGraphicsSetting_WaterQuality = 0;
			Storage.nxtGraphicsSetting_LightingQuality = 0;
			Storage.nxtGraphicsSetting_AmbientOcclusion = 0;
			Storage.nxtGraphicsSetting_Bloom = 0;
			Storage.nxtGraphicsSetting_TextureQuality = 0;
			Storage.nxtGraphicsSetting_AnisotropicFiltering = 0;
			Storage.nxtGraphicsSetting_VolumetricLighting = 0;
			Storage.nxtGraphicsSetting_Shadows = false;
			Storage.nxtGraphicsSetting_GroundDecor = false;
			Storage.nxtGraphicsSetting_TerrainBlending = true;
			Storage.nxtClientSettings_GameWorldScaling = 100;
			NXTSettingsGUI.DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			NXTSettingsGUI.ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			NXTSettingsGUI.AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			NXTSettingsGUI.AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			NXTSettingsGUI.WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			NXTSettingsGUI.LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			NXTSettingsGUI.AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			NXTSettingsGUI.BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			NXTSettingsGUI.TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			NXTSettingsGUI.AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			NXTSettingsGUI.VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			NXTSettingsGUI.ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			NXTSettingsGUI.GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			NXTSettingsGUI.TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
			NXTSettingsGUI.GameWorldScalingComboBox.setSelectedIndex((Storage.nxtClientSettings_GameWorldScaling - 35) / 5);
			break;
		case "low":
			Storage.nxtGraphicsSetting_DrawDistance = 0;
			Storage.nxtGraphicsSetting_ShadowQuality = 0;
			Storage.nxtGraphicsSetting_AntiAliasingMode = 0;
			Storage.nxtGraphicsSetting_AntiAliasingQuality = 0;
			Storage.nxtGraphicsSetting_WaterQuality = 0;
			Storage.nxtGraphicsSetting_LightingQuality = 0;
			Storage.nxtGraphicsSetting_AmbientOcclusion = 0;
			Storage.nxtGraphicsSetting_Bloom = 0;
			Storage.nxtGraphicsSetting_TextureQuality = 1;
			Storage.nxtGraphicsSetting_AnisotropicFiltering = 0;
			Storage.nxtGraphicsSetting_VolumetricLighting = 0;
			Storage.nxtGraphicsSetting_Shadows = true;
			Storage.nxtGraphicsSetting_GroundDecor = true;
			Storage.nxtGraphicsSetting_TerrainBlending = true;
			Storage.nxtClientSettings_GameWorldScaling = 100;
			NXTSettingsGUI.DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			NXTSettingsGUI.ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			NXTSettingsGUI.AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			NXTSettingsGUI.AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			NXTSettingsGUI.WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			NXTSettingsGUI.LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			NXTSettingsGUI.AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			NXTSettingsGUI.BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			NXTSettingsGUI.TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			NXTSettingsGUI.AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			NXTSettingsGUI.VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			NXTSettingsGUI.ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			NXTSettingsGUI.GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			NXTSettingsGUI.TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
			NXTSettingsGUI.GameWorldScalingComboBox.setSelectedIndex((Storage.nxtClientSettings_GameWorldScaling - 35) / 5);
			break;
		case "mid":
		case "med":
		case "medium":
			Storage.nxtGraphicsSetting_DrawDistance = 1;
			Storage.nxtGraphicsSetting_ShadowQuality = 1;
			Storage.nxtGraphicsSetting_AntiAliasingMode = 1;
			Storage.nxtGraphicsSetting_AntiAliasingQuality = 0;
			Storage.nxtGraphicsSetting_WaterQuality = 1;
			Storage.nxtGraphicsSetting_LightingQuality = 1;
			Storage.nxtGraphicsSetting_AmbientOcclusion = 0;
			Storage.nxtGraphicsSetting_Bloom = 1;
			Storage.nxtGraphicsSetting_TextureQuality = 1;
			Storage.nxtGraphicsSetting_AnisotropicFiltering = 1;
			Storage.nxtGraphicsSetting_VolumetricLighting = 0;
			Storage.nxtGraphicsSetting_Shadows = true;
			Storage.nxtGraphicsSetting_GroundDecor = true;
			Storage.nxtGraphicsSetting_TerrainBlending = true;
			Storage.nxtClientSettings_GameWorldScaling = 100;
			NXTSettingsGUI.DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			NXTSettingsGUI.ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			NXTSettingsGUI.AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			NXTSettingsGUI.AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			NXTSettingsGUI.WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			NXTSettingsGUI.LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			NXTSettingsGUI.AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			NXTSettingsGUI.BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			NXTSettingsGUI.TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			NXTSettingsGUI.AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			NXTSettingsGUI.VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			NXTSettingsGUI.ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			NXTSettingsGUI.GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			NXTSettingsGUI.TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
			NXTSettingsGUI.GameWorldScalingComboBox.setSelectedIndex((Storage.nxtClientSettings_GameWorldScaling - 35) / 5);
			break;
		case "high":
			Storage.nxtGraphicsSetting_DrawDistance = 2;
			Storage.nxtGraphicsSetting_ShadowQuality = 2;
			Storage.nxtGraphicsSetting_AntiAliasingMode = 2;
			Storage.nxtGraphicsSetting_AntiAliasingQuality = 1;
			Storage.nxtGraphicsSetting_WaterQuality = 2;
			Storage.nxtGraphicsSetting_LightingQuality = 2;
			Storage.nxtGraphicsSetting_AmbientOcclusion = 0;
			Storage.nxtGraphicsSetting_Bloom = 2;
			Storage.nxtGraphicsSetting_TextureQuality = 1;
			Storage.nxtGraphicsSetting_AnisotropicFiltering = 2;
			Storage.nxtGraphicsSetting_VolumetricLighting = 0;
			Storage.nxtGraphicsSetting_Shadows = true;
			Storage.nxtGraphicsSetting_GroundDecor = true;
			Storage.nxtGraphicsSetting_TerrainBlending = true;
			Storage.nxtClientSettings_GameWorldScaling = 100;
			NXTSettingsGUI.DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			NXTSettingsGUI.ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			NXTSettingsGUI.AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			NXTSettingsGUI.AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			NXTSettingsGUI.WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			NXTSettingsGUI.LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			NXTSettingsGUI.AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			NXTSettingsGUI.BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			NXTSettingsGUI.TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			NXTSettingsGUI.AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			NXTSettingsGUI.VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			NXTSettingsGUI.ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			NXTSettingsGUI.GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			NXTSettingsGUI.TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
			NXTSettingsGUI.GameWorldScalingComboBox.setSelectedIndex((Storage.nxtClientSettings_GameWorldScaling - 35) / 5);
			break;
		case "ultra":
			Storage.nxtGraphicsSetting_DrawDistance = 3;
			Storage.nxtGraphicsSetting_ShadowQuality = 3;
			Storage.nxtGraphicsSetting_AntiAliasingMode = 2;
			Storage.nxtGraphicsSetting_AntiAliasingQuality = 2;
			Storage.nxtGraphicsSetting_WaterQuality = 3;
			Storage.nxtGraphicsSetting_LightingQuality = 3;
			Storage.nxtGraphicsSetting_AmbientOcclusion = 2;
			Storage.nxtGraphicsSetting_Bloom = 3;
			Storage.nxtGraphicsSetting_TextureQuality = 1;
			Storage.nxtGraphicsSetting_AnisotropicFiltering = 3;
			Storage.nxtGraphicsSetting_VolumetricLighting = 3;
			Storage.nxtGraphicsSetting_Shadows = true;
			Storage.nxtGraphicsSetting_GroundDecor = true;
			Storage.nxtGraphicsSetting_TerrainBlending = true;
			Storage.nxtClientSettings_GameWorldScaling = 100;
			NXTSettingsGUI.DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			NXTSettingsGUI.ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			NXTSettingsGUI.AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			NXTSettingsGUI.AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			NXTSettingsGUI.WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			NXTSettingsGUI.LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			NXTSettingsGUI.AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			NXTSettingsGUI.BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			NXTSettingsGUI.TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			NXTSettingsGUI.AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			NXTSettingsGUI.VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			NXTSettingsGUI.ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			NXTSettingsGUI.GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			NXTSettingsGUI.TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
			NXTSettingsGUI.GameWorldScalingComboBox.setSelectedIndex((Storage.nxtClientSettings_GameWorldScaling - 35) / 5);
			break;
		case "max":
		case "maxed":
		case "maximum":
			Storage.nxtGraphicsSetting_RemoveRoofs = 0;
			Storage.nxtGraphicsSetting_DrawDistance = 3;
			Storage.nxtGraphicsSetting_ShadowQuality = 3;
			Storage.nxtGraphicsSetting_AntiAliasingMode = 3;
			Storage.nxtGraphicsSetting_AntiAliasingQuality = 3;
			Storage.nxtGraphicsSetting_WaterQuality = 3;
			Storage.nxtGraphicsSetting_LightingQuality = 3;
			Storage.nxtGraphicsSetting_AmbientOcclusion = 2;
			Storage.nxtGraphicsSetting_Bloom = 3;
			Storage.nxtGraphicsSetting_TextureQuality = 2;
			Storage.nxtGraphicsSetting_AnisotropicFiltering = 4;
			Storage.nxtGraphicsSetting_VolumetricLighting = 4;
			Storage.nxtGraphicsSetting_Shadows = true;
			Storage.nxtGraphicsSetting_GroundDecor = true;
			Storage.nxtGraphicsSetting_TerrainBlending = true;
			Storage.nxtGraphicsSetting_FlickeringEffects = true;
			Storage.nxtGraphicsSetting_CustomCursors = true;
			Storage.nxtGraphicsSetting_MaxForegroundFps = 300;
			Storage.nxtGraphicsSetting_MaxBackgroundFps = 300;
			Storage.nxtClientSettings_GameWorldScaling = 200;
			NXTSettingsGUI.RemoveRoofsComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_RemoveRoofs);
			NXTSettingsGUI.DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			NXTSettingsGUI.ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			NXTSettingsGUI.AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			NXTSettingsGUI.AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			NXTSettingsGUI.WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			NXTSettingsGUI.LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			NXTSettingsGUI.AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			NXTSettingsGUI.BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			NXTSettingsGUI.TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			NXTSettingsGUI.AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			NXTSettingsGUI.VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			NXTSettingsGUI.ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			NXTSettingsGUI.GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			NXTSettingsGUI.TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
			NXTSettingsGUI.FlickeringEffectsCheckbox.setSelected(Storage.nxtGraphicsSetting_FlickeringEffects);
			NXTSettingsGUI.CustomCursorsCheckbox.setSelected(Storage.nxtGraphicsSetting_CustomCursors);
			NXTSettingsGUI.MaxForegroundFpsComboBox.setSelectedIndex((Storage.nxtGraphicsSetting_MaxForegroundFps / 5) - 1);
			NXTSettingsGUI.MaxBackgroundFpsComboBox.setSelectedIndex((Storage.nxtGraphicsSetting_MaxBackgroundFps / 5) - 1);
			NXTSettingsGUI.GameWorldScalingComboBox.setSelectedIndex((Storage.nxtClientSettings_GameWorldScaling - 35) / 5);
			break;
		case "wiki":
		case "wikia":
		case "wikian":
		case "image policy":
			Storage.nxtGraphicsSetting_DrawDistance = 3;
			Storage.nxtGraphicsSetting_ShadowQuality = 3;
			Storage.nxtGraphicsSetting_AntiAliasingMode = 2;
			Storage.nxtGraphicsSetting_AntiAliasingQuality = 3;
			Storage.nxtGraphicsSetting_WaterQuality = 3;
			Storage.nxtGraphicsSetting_LightingQuality = 3;
			Storage.nxtGraphicsSetting_AmbientOcclusion = 2;
			Storage.nxtGraphicsSetting_Bloom = 3;
			Storage.nxtGraphicsSetting_TextureQuality = 1;
			Storage.nxtGraphicsSetting_AnisotropicFiltering = 4;
			Storage.nxtGraphicsSetting_VolumetricLighting = 4;
			Storage.nxtGraphicsSetting_Shadows = true;
			Storage.nxtGraphicsSetting_GroundDecor = true;
			Storage.nxtGraphicsSetting_TerrainBlending = true;
			Storage.nxtGraphicsSetting_FlickeringEffects = true;
			Storage.nxtGraphicsSetting_CustomCursors = true;
			Storage.nxtGraphicsSetting_MaxForegroundFps = 60;
			Storage.nxtGraphicsSetting_MaxBackgroundFps = 45;
			Storage.nxtClientSettings_UIScaling = 100;
			Storage.nxtClientSettings_GameWorldScaling = 200;
			NXTSettingsGUI.DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			NXTSettingsGUI.ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			NXTSettingsGUI.AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			NXTSettingsGUI.AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			NXTSettingsGUI.WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			NXTSettingsGUI.LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			NXTSettingsGUI.AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			NXTSettingsGUI.BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			NXTSettingsGUI.TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			NXTSettingsGUI.AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			NXTSettingsGUI.VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			NXTSettingsGUI.ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			NXTSettingsGUI.GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			NXTSettingsGUI.TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
			NXTSettingsGUI.FlickeringEffectsCheckbox.setSelected(Storage.nxtGraphicsSetting_FlickeringEffects);
			NXTSettingsGUI.CustomCursorsCheckbox.setSelected(Storage.nxtGraphicsSetting_CustomCursors);
			NXTSettingsGUI.MaxForegroundFpsComboBox.setSelectedIndex((Storage.nxtGraphicsSetting_MaxForegroundFps / 5) - 1);
			NXTSettingsGUI.MaxBackgroundFpsComboBox.setSelectedIndex((Storage.nxtGraphicsSetting_MaxBackgroundFps / 5) - 1);
			NXTSettingsGUI.UIScalingComboBox.setSelectedIndex((Storage.nxtClientSettings_UIScaling - 100) / 5);
			NXTSettingsGUI.GameWorldScalingComboBox.setSelectedIndex((Storage.nxtClientSettings_GameWorldScaling - 35) / 5);
			break;
		case "reddit":
		case "/r/runescape":
		case "noob":
		case "noobish":
		case "compat":
		case "compatibility":
			Storage.nxtGraphicsSetting_Brightness = 0;
			Storage.nxtGraphicsSetting_RemoveRoofs = 1;
			Storage.nxtGraphicsSetting_DrawDistance = 0;
			Storage.nxtGraphicsSetting_ShadowQuality = 0;
			Storage.nxtGraphicsSetting_AntiAliasingMode = 0;
			Storage.nxtGraphicsSetting_AntiAliasingQuality = 0;
			Storage.nxtGraphicsSetting_WaterQuality = 0;
			Storage.nxtGraphicsSetting_LightingQuality = 0;
			Storage.nxtGraphicsSetting_AmbientOcclusion = 0;
			Storage.nxtGraphicsSetting_Bloom = 0;
			Storage.nxtGraphicsSetting_TextureQuality = 0;
			Storage.nxtGraphicsSetting_AnisotropicFiltering = 0;
			Storage.nxtGraphicsSetting_VolumetricLighting = 0;
			Storage.nxtGraphicsSetting_Shadows = false;
			Storage.nxtGraphicsSetting_GroundDecor = false;
			Storage.nxtGraphicsSetting_TerrainBlending = false;
			Storage.nxtGraphicsSetting_MaxForegroundFps = 15;
			Storage.nxtGraphicsSetting_MaxBackgroundFps = 5;
			Storage.nxtClientSettings_GameWorldScaling = 35;
			NXTSettingsGUI.BrightnessSlider.setValue(Storage.nxtGraphicsSetting_Brightness);
			NXTSettingsGUI.RemoveRoofsComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_RemoveRoofs);
			NXTSettingsGUI.DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			NXTSettingsGUI.ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			NXTSettingsGUI.AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			NXTSettingsGUI.AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			NXTSettingsGUI.WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			NXTSettingsGUI.LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			NXTSettingsGUI.AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			NXTSettingsGUI.BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			NXTSettingsGUI.TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			NXTSettingsGUI.AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			NXTSettingsGUI.VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			NXTSettingsGUI.ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			NXTSettingsGUI.GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			NXTSettingsGUI.TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
			NXTSettingsGUI.FlickeringEffectsCheckbox.setSelected(Storage.nxtGraphicsSetting_FlickeringEffects);
			NXTSettingsGUI.CustomCursorsCheckbox.setSelected(Storage.nxtGraphicsSetting_CustomCursors);
			NXTSettingsGUI.MaxForegroundFpsComboBox.setSelectedIndex((Storage.nxtGraphicsSetting_MaxForegroundFps / 5) - 1);
			NXTSettingsGUI.MaxBackgroundFpsComboBox.setSelectedIndex((Storage.nxtGraphicsSetting_MaxBackgroundFps / 5) - 1);
			NXTSettingsGUI.GameWorldScalingComboBox.setSelectedIndex((Storage.nxtClientSettings_GameWorldScaling - 35) / 5);
			break;
		}
	}

	public static void ToggleEnabled(String mode, boolean enabled) {
		switch (mode.toLowerCase()) {
		case "preset":
		case "presets":
			NXTSettingsGUI.MinimumGraphicsPresetButton.setEnabled(enabled);
			NXTSettingsGUI.LowGraphicsPresetButton.setEnabled(enabled);
			NXTSettingsGUI.MediumGraphicsPresetButton.setEnabled(enabled);
			NXTSettingsGUI.HighGraphicsPresetButton.setEnabled(enabled);
			NXTSettingsGUI.UltraGraphicsPresetButton.setEnabled(enabled);
			NXTSettingsGUI.MaxedGraphicsPresetButton.setEnabled(enabled);
			NXTSettingsGUI.WikianGraphicsPresetButton.setEnabled(enabled);
			NXTSettingsGUI.RedditGraphicsPresetButton.setEnabled(enabled);
			break;
		case "write":
		case "writing":
			NXTSettingsGUI.WriteSettings.setEnabled(enabled);
			break;
		}
	}

	public static void DeselectDevConsole() {
		if (NXTSettingsGUI.DeveloperConsoleHistoryTable.isShowing()) {
			NXTSettingsGUI.DeveloperConsoleHistoryTable.clearSelection();
		}
	}

	public static void GrabInternalBuildNumber(boolean CopyToClipboard) {
		final String Path = Storage.configuration_location.replace("preferences.cfg", "rs2client.exe");
		final File file = new File(Path);

		FileInputStream in = null;
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Clipboard clipboard = toolkit.getSystemClipboard();

		if (!file.exists()) {
			System.out.println(file.getAbsolutePath() + " does not exist. Abort!");
			JOptionPane.showMessageDialog(null, file.getAbsolutePath() + " does not exist.", "Error.",
					JOptionPane.ERROR_MESSAGE);
		} else if (!file.canRead()) {
			System.out.println(file.getAbsolutePath() + " can't be read. Abort!");
			JOptionPane.showMessageDialog(null, file.getAbsolutePath() + " can't be read.", "Error.",
					JOptionPane.ERROR_MESSAGE);
		} else if (!file.isFile()) {
			System.out.println("Somehow, " + file.getAbsolutePath() + " isn't a file. Abort!");
			JOptionPane.showMessageDialog(null, "Somehow " + file.getAbsolutePath() + " isn't a file.", "Error.",
					JOptionPane.ERROR_MESSAGE);
		}

		try {
			in = new FileInputStream(file);
			final byte fileBytes[] = new byte[(int) file.length()];
			in.read(fileBytes);
			final String fileContents = new String(fileBytes);
			in.close();

			final int offset = fileContents.indexOf("NXT-BUIL");
			final int end = fileContents.indexOf("\\build\\nxt\\bin\\rs2client\\FINAL\\rs2client.pdb");
			String output = "Invalid";
			if ((offset > 0) && (end > offset)) {
				output = fileContents.substring(offset, end);
			}
			System.out.println("NXT Build: " + output);
			NXTSettingsGUI.frame.setTitle("NXT's Settings (Graphic Setting's Version: \""
					+ Storage.nxtClientSettings_SettingsVersion + "\"; Internal Build Label: \"" + output + "\")");

			if (CopyToClipboard) {
				final Object[] DialogueOptions = { "As-is", "Reddit-format", "Discord-format", "Cancel" };
				final int n = JOptionPane.showOptionDialog(null,
						"Would you like to format the output? If so, which way?", "Format it?",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, DialogueOptions,
						DialogueOptions[3]);

				if (n == 1) {
					// Reddit-format was selected.
					output = "`" + output + "`";
				} else if (n == 2) {
					// Discord-format was selected.
					output = "\n```\n" + output + "\n```";
				}

				if ((n >= 0) && (n < 3)) {
					final StringSelection strSel = new StringSelection(output);
					clipboard.setContents(strSel, null);
					JOptionPane.showMessageDialog(null, "Copied NXT's build label to the clipboard.", "Done.",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
