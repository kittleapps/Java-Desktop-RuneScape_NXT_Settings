package com.kittleapps.desktop.app.nxtsettings;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Storage {
	public static int

		// NXT Graphics settings options.
		nxtGraphicsSetting_AnisotropicFiltering,
		nxtGraphicsSetting_AntiAliasingMode,
		nxtGraphicsSetting_AntiAliasingQuality,
		nxtGraphicsSetting_Bloom,
		nxtGraphicsSetting_Brightness,
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

		// NXT Client settings options.
		nxtClientSettings_FavouriteWorld1,
		nxtClientSettings_FavouriteWorld2,
		nxtClientSettings_FavouriteWorld3,
		nxtClientSettings_LoginMusicVolume,
		nxtClientSettings_InGameMusicVolume,
		nxtClientSettings_InGameSoundEffectsVolume,
		nxtClientSettings_InGameAmbientSoundEffectsVolume,
		nxtClientSettings_InGameVoiceOverVolume,
		nxtClientSettings_LoginWallpaperID,
		nxtClientSettings_UIScaling,
		nxtClientSettings_GameWorldScaling,
		nxtClientSettings_LanguageSelected,

		// In-Program application/storage options.
		FrameRate,
		OS_TYPE
	;

	public static String

		// NXT User settings storage.
		nxtClientSettings_TemporaryUserID = "",
		nxtClientSettings_TemporaryUsername = "",

		// In-Program application/storage options.
		OS = System.getProperty("os.name").toLowerCase(),
		NXT_REGISTRY_LOCATION_BASE,
		Cache_settings_location,
		configuration_location
	;

	public final static String

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

		// NXT Graphics settings options.
		nxtGraphicsSetting_FlickeringEffects = true,
		nxtGraphicsSetting_TerrainBlending = true,
		nxtGraphicsSetting_GroundDecor = true,
		nxtGraphicsSetting_Shadows,
		nxtGraphicsSetting_CustomCursors = true,
		nxtGraphicsSetting_LoadingScreens = true,

		// NXT Client settigns options.
		nxtClientSettings_GlobalMute,
		nxtClientSettings_RandomizeLoginWallpaper = true,
		nxtClientSettings_RememberUsername = true,
		nxtClientSettings_CompatibilityMode,
		nxtClientSettings_AskToSwitchToCompatibility = true,
		nxtClientSettings_AskBeforeQuitting = true,

		// In-Program application/storage options;
		NXT_INSTALLED,
		ShowSensitiveInfo,

		// In-Program developer options;
		DEVELOPER_DebugsEnabled,
		DEVELOPER_ReadOnlyCache,
		DEVELOPER_AlwaysShowSensitiveInfo,
		DEVELOPER_WindowAlwaysOnTop
	;

   public static File preferences_config, Settings_db;
	public static Connection conn;
	public static Statement stmt;
	public static StringBuilder messages;
	public static List<String> nxtClientSettings_DeveloperConsoleLog = new ArrayList<>();
	public static String[] nxtClientSettings_DeveloperConsoleLogs;
	public static String[] ProgramDeveloperValues = {
		"TableCreated (yyyy-MM-dd hh:mm:ss)",
		"DEVELOPER_DEBUGS_ENABLED",
		"DEVELOPER_READ_ONLY_CACHE",
		"DEVELOPER_ALWAYS_SHOW_SENSITIVE_INFO",
		"DEVELOPER_ALWAYS_STAY_ON_TOP",
		"DEVELOPER_ALWAYS_START_ON_TOP"
	};
	public final static String[][] DEVELOPER_CONSOLE_COMMANDS = {
			{
			/* Player-Enabled Commands */
				"cls",
				"displayfps",
				"displayfpssmall",
				"displayfpsfull",
				"getcamerapos",
				"renderer",
				"help",
				"commands",
				"deletejs5caches",
			},
			{
			/* Known Jagex Moderator Commands */
				"cls",
				"displayfps",
				"displayfpssmall",
				"displayfpsfull",
				"getcamerapos",
				"renderer",
				"help",
				"commands",
				"deletejs5caches",
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
	public final static String[] LANGUAGES = {
			"English",
			"Deutsch",
			"Fran\u00E7ias",
			"Portugu\u00EAs"
	};
	public final static String[][] GRAPHICS_OPTIONS = {
		// Graphics options used for Drop-Down menus in the graphics settings.
		{
			/* 0: Remove Roofs */
			"None",
			"Always",
			"Selectively",
			"All"
		},
		{
			/* 1: Draw Distance */
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		{
			/* 2: Shadow Quality */
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		{
			/* 3: VSync */
			"Adaptive",
			"Off",
			"On",
			"Half",
			"Quarter"
		},
		{
			/* 4: Anti-aliasing Mode */
			"Off",
			"FXAA",
			"MSAA",
			"FXAA+MSAA"
		},
		{
			/* 5: Anti-aliasing Quality */
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		{
			/* 6: Water Quality */
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		{
			/* 7: Lighting Detail */
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		{
			/* 8: Ambient Occlusion */
			"Off",
			"SSAO",
			"HBAO"
		},
		{
			/* 9: Bloom Quality */
			"Off",
			"Low",
			"Medium",
			"High",
		},
		{
			/* 10: Depth of Field (Placeholders) */
			"Off (Placeholder)",
			"Near-Focus (Placeholder)",
			"Far-Focus (Placeholder)",
		},
		{
			/* 11: Texture Quality */
			"Off",
			"Compressed",
			"Uncompressed"
		},
		{
			/* 12: Anisotropic Filtering */
			"Off",
			"2x",
			"4x",
			"8x",
			"16x"
		},
		{
			/* 13: Volumetric Lighting Detail */
			"Off",
			"Low",
			"Medium",
			"High",
			"Ultra"
		},
		{
			/* 14: FPS Settings (Minimum 5; Maximum 300; Increments of 5) */
			"5",
			"10","15",
			"20","25",
			"30","35",
			"40","45",
			"50","55",
			"60","65",
			"70","75",
			"80","85",
			"90","95",
			"100","105",
			"110","115",
			"120","125",
			"130","135",
			"140","145",
			"150","155",
			"160","165",
			"170","175",
			"180","185",
			"190","195",
			"200","205",
			"210","215",
			"220","225",
			"230","235",
			"240","245",
			"250","255",
			"260","265",
			"270","275",
			"280","285",
			"290","295",
			"300"
		},
		{
			/* 15: UI Scaling Settings (Minimum 100; Maximum 400; Increments of 5) */
			"100%","105%",
			"110%","115%",
			"120%","125%",
			"130%","135%",
			"140%","145%",
			"150%","155%",
			"160%","165%",
			"170%","175%",
			"180%","185%",
			"190%","195%",
			"200%","205%",
			"210%","215%",
			"220%","225%",
			"230%","235%",
			"240%","245%",
			"250%","255%",
			"260%","265%",
			"270%","275%",
			"280%","285%",
			"290%","295%",
			"300%","305%",
			"310%","315%",
			"320%","325%",
			"330%","335%",
			"340%","345%",
			"350%","355%",
			"360%","365%",
			"370%","375%",
			"380%","385%",
			"390%","395%",
			"400%"
		},
		{
			/* 16: Game World Scaling Settings (Minimum 33; Maximum 200; Increments of 5; Indirect Minimum of 35) */
			"35%","40%",
			"45%","50%",
			"55%","60%",
			"65%","70%",
			"75%","80%",
			"85%","90%",
			"95%","100%",
			"105%","110%",
			"115%","120%",
			"125%","130%",
			"135%","140%",
			"145%","150%",
			"155%","160%",
			"165%","170%",
			"175%","180%",
			"185%","190%",
			"195%","200%",
		}
	};


	public final static String
	// HTML-Based Tooltip storage.
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
					"The default value for this entry is +10 of your refresh rate. (Example: 60hz = 70 Foreground FPS)" +
			"</html>",
	MAXBACKGOUNDFPS_TOOLTIP =
			"<html>" +
					"Manually set the maximum Frames-Per-Second (FPS) while NXT is not the focused window.<br>" +
				"<br>" +
					"The default value for this entry is 30. (Half the \"normal\" refresh rate for monitors which is 60hz)" +
			"</html>",
	INTERFACESCALING_TOOLTIP =
			"<html>" +
					"Manually set the interface scaling for all interfaces.<br>" +
				"<br>" +
					"The default value for this option is 100.<br>" +
				"<br>" +
					"Note: This option is currently experimental, and may cause graphical issues for some.<br>" +
					"This includes messing up some layouts while using the New Interface System."+
			"</html>",
	GAMESCALING_TOOLTIP =
			"<html>" +
					"Manually set the game render scaling for the 3D world underlay.<br>" +
				"<br>" +
					"The default value for this entry is 100.<br>" +
				"<br>" +
					"Note: This option is currently experimental, and may cause graphical issues for some.<br>" +
					"This includes blurs, and loss of FPS depending on the settings used."+
			"</html>",
	DOF_TOOLTIP =
			"<html>"+
					"This option is not released in the live versions, this is merely a placeholder." +
			"</html>",
	HEATHAZE_TOOLTIP =
			"<html>"+
					"Note: This option is not released in the live versions, this is merely a placeholder." +
			"</html>",
	USERNAME_INPUT_TOOLTIP =
			"<html>"+
					"This will be the display username when loading NXT.<br>"+
					"Currently this field allows Jagex's Colour+Sprite flags to be used.<br>" +
				"<br>"+
					"Note: When using sprite+colour flags it will still read is-if you types them here<br>" +
					"Graphically it may be a login-able name, but it will have invalid characters/login names.<br>"+
					"Some HTML elements like &lt;br&gt;, &lt;b&gt;, and &lt;i&gt; can be used as well." +
			"</html>",
	FAVOURITE_WORLD_INPUT_TOOLTIP =
			"<html>"+
					"To clear this slot use the value -1.<br>"+
					"If Slots 1 or 2 have -1 as their value, any later numbers will be disabled.<br>" +
					"The removal is currently not a NXT limitation, but it causes graphical issues." +
			"</html>",
	GLOBAL_AUDIO_MUTE_TOOLTIP =
			"<html>"+
					"Disables the audio streams while logged in the game.<br>" +
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
					"Note: The first frame of this will be the id input to the Right<br>" +
					"If this option is unchecked, that id's wallpaper will be used." +
			"</html>",
	WALLPAPER_ID_TOOLTIP =
			"<html>" +
					"Input your desired wallpaper id.<br>" +
				"<br>" +
					"The default value for this entry is 0." +
				"<br>" +
					"Note: This will apply to the first wallpaper while randomizing.<br>" +
					"This will also be the static id if randomizing is off.<br>" +
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
					"This will clear the Developer Console's command history logs."+
			"</html>",
	POPULATE_PLAYER_DEV_CONSOLE_LOGS_TOOLTIP =
			"<html>" +
					"This will populate the Developer Console's command history with player-allowed commands." +
			"</html>",
	POPULATE_JAGEX_DEV_CONSOLE_LOGS_TOOLTIP =
			"<html>" +
					"This will populate the Developer Console's command history with all known commands.<br>" +
				"<br>" +
					"Note: This list in incomplete, and many commands are not use-able by players."+
			"</html>",
	BOOSTED_VOLUMES_TOOLTIP =
			"<html>" +
					"Checking this will make the slider's maximum goto '254'<br>"+
					"When the volume's over the default '127' it will allow a boosted volume to occur.<br>"+
					"This allows for louder sound effects, like mining sounds, to be heard better."+
			"</html>",
	GRAPHICS_PRESET_TOOLTIP =
			"<html>" +
					"Checking this will unlock the graphics presets."+
			"</html>",
	GRAPHICS_PRESET_BUTTON_TOOLTIP =
			"<html>" +
					"Note: This will overwrite your current settings.<br>"+
					"You will be required to change them back manually, or re-read to restore prior to writing."+
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
			JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Could not init JDBC driver - driver not found"+
																"\n\n"+
																"Settings can not be read, Aborting the program's functioning.");
		}
	}
	public static boolean isMac() {
		return (Storage.OS.indexOf("mac") >= 0);
	}

	public static boolean isSolaris() {
		return (Storage.OS.indexOf("sunos") >= 0);
	}

	public static boolean isUnix() {
		return ((Storage.OS.indexOf("nix") >= 0) || (Storage.OS.indexOf("nux") >= 0) || (Storage.OS.indexOf("aix") > 0));
	}

	public static boolean isWindows() {
		return (Storage.OS.indexOf("win") >= 0);
	}
}