package com.kittleapps.desktop.app.nxtconfiguration;

import java.awt.GraphicsEnvironment;

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
		nxtGraphicsSetting_MaxForegroundFps,
		nxtGraphicsSetting_MaxBackgroundFps,

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
		nxtClientSettings_InterfaceScale,
		nxtClientSettings_GameRenderScale,
		FrameRate,
		OS_TYPE
	;

	public static String
		OS = System.getProperty("os.name").toLowerCase(),
		nxtClientSettings_TemporaryUsername = "",

		// The following values are made just in case the vt-varc keys change numeral values for ease-of-changing.

		CACHE_KEY_VT_VARC_CUSTOM_CURSORS = "987",
		CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1 = "998",
		CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2 = "999",
		CACHE_KEY_VT_VARC_GLOBAL_AUDIO_MUTE = "3513",
		CACHE_KEY_VT_VARC_IN_GAME_MUSIC_VOLUME = "3514",
		CACHE_KEY_VT_VARC_IN_GAME_SOUND_EFFECTS_VOLUME = "3515",
		CACHE_KEY_VT_VARC_IN_GAME_AMBIENT_EFFECTS_VOLUME = "3516",
		CACHE_KEY_VT_VARC_IN_GAME_VOICE_OVER_VOLUME = "3517",
		CACHE_KEY_VT_VARC_REMEMBER_USERNAME = "3681",
		CACHE_KEY_VT_VARC_SAVED_USERNAME = "3683",
		CACHE_KEY_VT_VARC_LOADING_SCREENS = "3698",
		CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3 = "4272",
		CACHE_KEY_VT_VERC_WALLPAPER_ID = "6040",
		CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER = "6042"
	;

	public static boolean
		nxtGraphicsSetting_FlickeringEffects,
		nxtGraphicsSetting_TerrainBlending,
		nxtGraphicsSetting_GroundDecor,
		nxtGraphicsSetting_Shadows,
		nxtGraphicsSetting_CustomCursors,
		nxtGraphicsSetting_LoadingScreens,
		nxtGraphicsSetting_HeatHaze,
		nxtClientSettings_GlobalMute,
		nxtClientSettings_RandomizeLoginWallpaper,
		nxtClientSettings_RememberUsername,

		ShowSensitiveInfo = false
	;

	public static String[][] GRAPHICS_OPTIONS = {
		// Graphics options used for Drop-Down menus in the graphics settings.
		{
			/* Remove Roofs */
			"None",
			"Always",
			"Selectively",
			"All"
		},
		{
			/* Draw Distance */
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		{
			/* Shadow Quality */
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		{
			/* VSync */
			"Adaptive",
			"Off",
			"On",
			"Half",
			"Quarter"
		},
		{
			/* Anti-aliasing Mode */
			"Off",
			"FXAA",
			"MSAA",
			"FXAA+MSAA"
		},
		{
			/* Anti-aliasing Quality */
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		{
			/* Water Quality */
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		{
			/* Lighting Detail */
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		{
			/* Ambient Occlusion */
			"Off",
			"SSAO",
			"HBAO"
		},
		{
			/* Bloom Quality */
			"Off",
			"Low",
			"Medium",
			"High",
		},
		{
			/* Placeholders for Depth of Field */
			"Off (Placeholder)",
			"Near-Focus (Placeholder)",
			"Far-Focus (Placeholder)",
		},
		{
			/* Texture Quality */
			"Off",
			"Compressed",
			"Uncompressed"
		},
		{
			/* Anisotropic Filtering */
			"Off",
			"2x",
			"4x",
			"8x",
			"16x"
		},
		{
			/* Volumetric Lighting Detail */
			"Off",
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
	};
	public static String

	// HTML-Based Tooltip storage.

	REMOVEROOFS_TOOLTIP =
			"<html>" +
					"Note: \"None\" is NOT natively an option for NXT.<br>" +
					"<br>" +
					"This option WILL cause graphical issues.<br>" +
					"Set to another option if you dislike the roofs." +
			"</html>",
	AO_TOOLTIP =
			"<html>" +
				"FXAA: Fast approximate anti-aliasing is a<br>" +
				"high performance screen space anti-<br>" +
				"aliasing technique.<br>" +
				"<br>" +
				"MSAA: Multisample anti-aliasing is a<br>" +
				"superior form of antialiasing over image<br>" +
				"space techniques like FXAA, but with a<br>" +
				"much higher performance requirement.<br>" +
				"<br>" +
				"FXAA+MSAA: Add FXAA to MSAA to enable<br>" +
				"anti-aliasing to transparent objects, but<br>" +
				"will result in slightly blurrier images." +
			"</html>",
	MAXFOREGOUNDFPS_TOOLTIP =
			"<html>" +
				"Manually set the Maximum Frames-Per-Second (FPS) while NXT is the focused window.<br>" +
				"<br>" +
				"By-Default this is +10 of your refresh rate. (60hz = 70 Max FPS here)<br>" +
				"Currently the Minimum for this value is 5, and the maximum 300." +
			"</html>",
	MAXBACKGOUNDFPS_TOOLTIP =
			"<html>" +
				"Manually set the Maximum Frames-Per-Second (FPS) while NXT is not the focused window.<br>" +
				"<br>" +
				"By-Default this is 30. (Half the \"Normal\" Refresh rate for the common 60hz monitors)<br>" +
				"Currently the Minimum for this value is 5, and the maximum 300." +
			"</html>",
	INTERFACESCALING_TOOLTIP =
			"<html>" +
					"Manually set the interface scaling for all interfaces.<br>" +
					"<br>" +
					"By-Default this is 100.<br>" +
					"Currently the Minimum for this value is 100 (100%), and the maximum 400 (400%).<br>" +
					"<br>" +
					"NOTE: This Option is currently experimental, and may cause graphical issues for some.<br>" +
					"This includes messing up some layouts while using the New Interface System."+
			"</html>",
	GAMESCALING_TOOLTIP =
			"<html>" +
				"Manually set the game render scaling for the 3D world underlay.<br>" +
				"<br>" +
				"By-Default this is 100.<br>" +
				"Currently the Minimum for this value is 33 (33%), and the maximum 200 (200%).<br>" +
				"<br>" +
				"This Option is currently experimental, and may cause graphical issues for some.<br>" +
				"This includes blurs, and loss of FPS depending on the settings used."+
			"</html>",
	DOF_TOOLTIP =
			"This option is not released in the live versions, this is merely a placeholder.",
	HEATHAZE_TOOLTIP =
			"This option is not released in the live versions, this is merely a placeholder.",
	USERNAME_INPUT_TOOLTIP =
			"<html>"+
				"This will be the display username when loading NXT.<br>"+
				"Currently this field allows Jagex's Colour+Sprite flags to be used.<br>" +
				"<br>"+
				"NOTE: When using sprite+colour flags it will still read is-if you types them here<br>" +
				"Graphically it may be a login-able name, but it will have invalid characters/login names.<br>"+
				"<br>"+
				"Some HTML elements like &lt;br&gt;, &lt;b&gt;, and &lt;i&gt; can be used as well." +
			"</html>",
	FAVOURITE_WORLD_INPUT_TOOLTIP =
			"<html>"+
				"To clear this slot use the value -1.<br>"+
				"If Slots 1 or 2 have -1 as their value, any later numbers will be disabled.<br>" +
				"<br>"+
				"The removal is currently NOT a NXT limitation, but it causes graphical issues." +
			"</html>",
	GLOBAL_AUDIO_MUTE_TOOLTIP =
			"<html>"+
				"Disables the audio streams while logged in the game.<br>" +
				"<br>"+
				"Other volume options will remain uneffected." +
			"</html>",
	REMEMBER_USERNAME_TOOLTIP =
			"<html>" +
				"If unchecked: NXT will clear the username on the next client load.<br>" +
				"<br>" +
				"Note: This feature is redundant for this program if the username field is empty." +
			"</html>",
	RANDOMIZE_LOGIN_WALLPAPER_TOOLTIP =
			"<html>"+
				"Randomize your login screen's wallpaper.<br>" +
				"<br>" +
				"NOTE: The FIRST frame of this will be the ID input to the Right<br>" +
				"If this option is unchecked, that ID's wallpaper will be used." +
			"</html>",
	WALLPAPER_ID_TOOLTIP =
			"<html>" +
				"Input your desired wallpaper ID.<br>" +
				"<br>" +
				"NOTE: This will apply to the FIRST wallpaper while randomizing.<br>" +
				"This will also be the Static ID if randomizing is OFF.<br>" +
				"<br>" +
				"ID: 0 = Default." +
			"</html>",
	ADD_SPRITE_FLAG_TOOLTIP =
			"<html>" +
				"Adds the &lt;sprite=#,#&gt; flag to your username field.<br>" +
				"This flag is that Jagex uses to add sprites to the chatbox e.g. staffmodlevel Crowns." +
			"</html>",
	ADD_COLOUR_FLAG_TOOLTIP =
			"<html>" +
				"Adds the &lt;col=RRGGBB&gt; flag to your username field.<br>" +
				"This flag is used by Jagex to change the colours of text full or mid-sentence e.g: Green warnings.<br>" +
				"As far as I've known, this does NOT support alpha. Keep it in RRGGBB Hex format." +
			"</html>",
	CLEAR_DEV_CONSOLE_LOGS_TOOLTIP =
			"<html>" +
				"This will clear the Developer Console's command history logs.<br>" +
				"<br>" +
				"Note: Using this will instantly Write to your client's settings." +
			"</html>",
	FAVOURITE_WORLD_TO_2147M_TOOLTIP =
			"<html>" +
				"This will set your {{{SLOT}}} favourite world slot to: 2,147,000,000;<br>" +
				"<br>" +
				"Note: Using this will instantly write to your client's settings." +
			"</html>"
	;
	public static void init() {
		/*
		 * > Initialize the OS_Type
		 *
		 * > Find+save the frame rate of the default device
		 *
		 * > Load the driver for the Sqlite3 library
		 *
		 */
		OS_TYPE = -1;
		FrameRate = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();

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
		try {
			Class.forName("org.sqlite.JDBC");
		} catch(final ClassNotFoundException eString) {
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