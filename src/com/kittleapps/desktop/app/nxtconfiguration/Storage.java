package com.kittleapps.desktop.app.nxtconfiguration;

public class Storage {
	public static int
	nxtGraphicsSetting_AnisotropicFiltering,
	nxtGraphicsSetting_AntiAliasingMode,
	nxtGraphicsSetting_AntiAliasingQuality,
	nxtGraphicsSetting_Bloom,
	nxtGraphicsSetting_Brightness,
	nxtGraphicsSetting_DepthOfField,
	nxtGraphicsSetting_DrawDistance,
	nxtGraphicsSetting_LightingQuality,
	nxtGraphicsSetting_ShadowQuality,
	nxtGraphicsSetting_TextureQuality,
	nxtGraphicsSetting_FullScreenWidth,
	nxtGraphicsSetting_FullScreenHeight,
	nxtGraphicsSetting_AmbientOcclusion,
	nxtGraphicsSetting_WaterQuality,
	nxtGraphicsSetting_VolumetricLighting,
	nxtGraphicsSetting_VSync,
	nxtGraphicsSetting_RemoveRoofs,
	nxtGraphicsSetting_HeatHaze;
	public static int
	nxtClientSettings_CompatibilityMode,
	nxtClientSettings_FavouriteWorld1,
	nxtClientSettings_FavouriteWorld2,
	nxtClientSettings_FavouriteWorld3,
	nxtClientSettings_LoginMusicVolume,
	nxtClientSettings_InGameMusicVolume,
	nxtClientSettings_InGameSoundEffectsVolume,
	nxtClientSettings_InGameAmbientSoundEffectsVolume,
	nxtClientSettings_InGameVoiceOverVolume,
	nxtClientSettings_LoginWallpaperID,
	OS_TYPE = -1;
	public static String
	OS = System.getProperty("os.name").toLowerCase(),
	nxtClientSettings_TemporaryUsername = "";
	public static boolean 
	nxtGraphicsSetting_FlickeringEffects,
	nxtGraphicsSetting_TerrainBlending,
	nxtGraphicsSetting_GroundDecor,
	nxtGraphicsSetting_Shadows,
	nxtGraphicsSetting_CustomCursors,
	nxtGraphicsSetting_LoadingScreens,
	nxtClientSettings_GlobalMute,
	nxtClientSettings_RandomizeLoginWallpaper,
	nxtClientSettings_RememberUsername,
	ShowSensitiveInfo = false;
	public static String[][] GRAPHICS_OPTIONS = {
		{
			"None",
			"Always",
			"Selectively",
			"All"
		},
		// Remove Roofs
		{
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		// Draw Distance
		{
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		// Shadow Quality
		{
			"Adaptive",
			"Off",
			"On",
			"Half",
			"Quarter"
		},
		// VSync
		{
			"Off",
			"FXAA",
			"MSAA",
			"FXAA+MSAA"
		},
		// Anti-aliasing Mode
		{
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		// Anti-aliasing Quality
		{
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		// Water Quality
		{
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		// Lighting Detail
		{
			"Off",
			"SSAO",
			"HBAO"
		},
		// Ambient Occlusion
		{
			"Off",
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		// Bloom Quality
		{
			"Off",
			"(UNDISCLOSED)",
			"(UNDISCLOSED)",
			"(UNDISCLOSED)"
		},
		// Depth of Field
		{
			"Off",
			"Compressed",
			"Uncompressed"
		},
		// Texture Quality
		{
			"Off",
			"2x",
			"4x",
			"8x",
			"16x"
		},
		// Anisotropic Filtering
		{
			"Off",
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		// Volumetric Lighting Detail
		{
			"Off",
			"(UNDISCLOSED)",
			"(UNDISCLOSED)",
			"(UNDISCLOSED)"
		},
		// Heat Haze
		{
			"Off",
			"Resizeable",
			"Fixed",
			"		Full Screen"
		},
		// Window Mode
	};
	public static String AO_TOOLTIP = 
			"<html>" +
			"FXAA: Fast approximate anti-aliasing is a<br>"+
			"high performance screen space anti-<br>"+
			"aliasing technique.<br>"+
			"<br>" +
			"MSAA: Multisample anti-aliasing is a<br>"+
			"superior form of antialiasing over image<br>"+
			"space techniques like FXAA, but with a<br>"+
			"much higher performance requirement.<br>"+
			"<br>" +
			"FXAA+MSAA: Add FXAA to MSAA to enable<br>"+
			"anti-aliasing to transparent objects, but<br>"+
			"will result in slightly blurrier images." +
			"</html>";

	public static void init() {
		OS_TYPE = -1;

		if (isWindows()) {
			OS_TYPE = 0;
		} else if (isUnix()) {
			OS_TYPE = 1;
		} else if (isMac()) {
			OS_TYPE = 2;
		} else if (isSolaris()) {
			OS_TYPE = 3;
		} else {
			OS_TYPE = -1;
		}

		nxtGraphicsSetting_RemoveRoofs = 1;
		nxtGraphicsSetting_DrawDistance = 0;
		nxtGraphicsSetting_ShadowQuality = 0;
		nxtGraphicsSetting_VSync = 0;
		nxtGraphicsSetting_AntiAliasingMode = 0;
		nxtGraphicsSetting_AntiAliasingQuality = 0;
		nxtGraphicsSetting_WaterQuality = 0;
		nxtGraphicsSetting_LightingQuality = 0;
		nxtGraphicsSetting_AmbientOcclusion = 0;
		nxtGraphicsSetting_Bloom = 0;
		nxtGraphicsSetting_Brightness = 4;
		nxtGraphicsSetting_TextureQuality = 0;
		nxtGraphicsSetting_AnisotropicFiltering = 0;
		nxtGraphicsSetting_VolumetricLighting = 0;
		nxtGraphicsSetting_FlickeringEffects = false;
		nxtGraphicsSetting_Shadows = false;
		nxtGraphicsSetting_CustomCursors = true;
		nxtGraphicsSetting_LoadingScreens = true;
		nxtGraphicsSetting_GroundDecor = false;
		nxtGraphicsSetting_TerrainBlending = false;
		nxtGraphicsSetting_DepthOfField = 0;
		nxtGraphicsSetting_HeatHaze = 0;

		nxtGraphicsSetting_FullScreenWidth = 800;
		nxtGraphicsSetting_FullScreenHeight = 600;
		nxtClientSettings_InGameSoundEffectsVolume = 127;
		nxtClientSettings_InGameAmbientSoundEffectsVolume = 127;
		nxtClientSettings_InGameVoiceOverVolume = 127;
		nxtClientSettings_InGameMusicVolume = 127;
		nxtClientSettings_LoginMusicVolume = 254;

		nxtClientSettings_CompatibilityMode = 0;
		nxtClientSettings_FavouriteWorld1 = 1;
		nxtClientSettings_FavouriteWorld2 = 2;
		nxtClientSettings_FavouriteWorld3 = 3;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException eString) {
			System.err.println("Could not init JDBC driver - driver not found");
		}
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public static boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}

	public static boolean isUnix() {
		return ((OS.indexOf("nix") >= 0) || (OS.indexOf("nux") >= 0) || (OS.indexOf("aix") > 0));
	}

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
}