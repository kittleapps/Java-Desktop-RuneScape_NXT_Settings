package com.kittleapps.desktop.app.nxtsettings;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.sql.Connection;
import java.sql.Statement;

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
		splash_x_position,
		splash_y_position,
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
		CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER = "6042",

		NXT_REGISTRY_LOCATION_BASE,
		Cache_settings_location,
		configuration_location,
		launcher_client_position
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
		NXT_INSTALLED,
		ShowSensitiveInfo = false
	;

	public static File
		preferences_config,
		Settings_db
	;
	public static Connection conn;
	public static Statement stmt;
	public static StringBuilder messages;
	public final static String[][] DEVELOPER_CONSOLE_COMMANDS = {
			{
			/* Player-Enabled Commands */
				"help",
				"commands",
				"cls",
				"deletejs5caches",
				"renderer",
				"getcamerapos",
				"displayfps",
				"displayfpssmall",
				"displayfpsfull"
			},
			{
			/* Known Jagex Moderator Commands */
				"help",
				"commands",
				"cls",
				"deletejs5caches",
				"renderer",
				"getcamerapos",
				"displayfps",
				"displayfpssmall",
				"displayfpsfull",
				"360camera",
				"debugcamera",
				"getclientvarp",
				"clientdrop",
				"directlogin ",
				"setlobby ",
				"setvar ",
				"setstat ",
				"advancestat",
				"give ",
				"tele ",
				"teleto ",
				"teletome ",
				"~ftfi",
				"~hideall",
			}
	};
	public final static String[][] GRAPHICS_OPTIONS = {
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
	public final static String

	// HTML-Based Tooltip storage.

	REMOVEROOFS_TOOLTIP =
			"<html>" +
					"Note: \"None\" is not natively an option for NXT.<br>" +
					"<br>" +
					"This option may cause graphical issues.<br>" +
					"Set to another option if you dislike the roofs always being visible." +
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
				"Manually set the maximum Frames-Per-Second (FPS) while NXT is the focused window.<br>" +
				"<br>" +
				"By-default this is +10 of your refresh rate. (Example: 60hz = 70 max FPS)<br>" +
				"Currently the minimum for this value is 5, and the maximum 300." +
			"</html>",
	MAXBACKGOUNDFPS_TOOLTIP =
			"<html>" +
				"Manually set the maximum Frames-Per-Second (FPS) while NXT is not the focused window.<br>" +
				"<br>" +
				"By-default this is 30. (Half the \"normal\" refresh rate for the common 60hz monitors)<br>" +
				"Currently the minimum for this value is 5, and the maximum 300." +
			"</html>",
	INTERFACESCALING_TOOLTIP =
			"<html>" +
					"Manually set the interface scaling for all interfaces.<br>" +
					"<br>" +
					"By-default this is 100.<br>" +
					"Currently the minimum for this value is 100 (100%), and the maximum 400 (400%).<br>" +
					"<br>" +
					"NOTE: This option is currently experimental, and may cause graphical issues for some.<br>" +
					"This includes messing up some layouts while using the New Interface System."+
			"</html>",
	GAMESCALING_TOOLTIP =
			"<html>" +
				"Manually set the game render scaling for the 3D world underlay.<br>" +
				"<br>" +
				"By-default this is 100.<br>" +
				"Currently the minimum for this value is 33 (33%), and the maximum 200 (200%).<br>" +
				"<br>" +
				"This option is currently experimental, and may cause graphical issues for some.<br>" +
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
				"The removal is currently not a NXT limitation, but it causes graphical issues." +
			"</html>",
	GLOBAL_AUDIO_MUTE_TOOLTIP =
			"<html>"+
				"Disables the audio streams while logged in the game.<br>" +
				"<br>"+
				"Other volume setting's options will remain uneffected." +
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
				"NOTE: The first frame of this will be the id input to the Right<br>" +
				"If this option is unchecked, that id's wallpaper will be used." +
			"</html>",
	WALLPAPER_ID_TOOLTIP =
			"<html>" +
				"Input your desired wallpaper id.<br>" +
				"<br>" +
				"NOTE: This will apply to the first wallpaper while randomizing.<br>" +
				"This will also be the static id if randomizing is off.<br>" +
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
				"Note: Using this button will instantly write to your client's settings." +
			"</html>",
	POPULATE_PLAYER_DEV_CONSOLE_LOGS_TOOLTIP =
			"<html>" +
				"This will populate the Developer Console's command history with player-allowed commands.<br>" +
				"<br>" +
				"Note: Using this button will instantly write to your client's settings." +
			"</html>",
	POPULATE_JAGEX_DEV_CONSOLE_LOGS_TOOLTIP =
			"<html>" +
				"This will populate the Developer Console's command history with all known commands.<br>" +
				"<br>" +
				"Note: Using this button will instantly write to your client's settings<br>" +
				"Note: This list in incomplete, and many commands are not use-able by players."+
			"</html>",
	BOOSTED_VOLUMES_TOOLTIP =
			"<html>" +
				"Checking this will make the slider's maximum goto '254'<br>"+
				"When the volume's over the default '127' it will allow a boosted volume to occur.<br>"+
				"This allows for louder sound effects, like mining sounds, to be heard better."+
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

		if (Mechanics.isWindows()) {
			OS_TYPE = 0;
		} else if (Mechanics.isUnix()) {
			OS_TYPE = 1;
		} else if (Mechanics.isMac()) {
			OS_TYPE = 2;
		} else if (Mechanics.isSolaris()) {
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
}