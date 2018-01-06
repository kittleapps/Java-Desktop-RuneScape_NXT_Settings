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
		nxtClientSettings_CameraZoom,
		nxtClientSettings_KeyboardHSensitivity,
		nxtClientSettings_KeyboardVSensitivity,
		nxtClientSettings_MouseHSensitivity,
		nxtClientSettings_MouseVSensitivity,
		nxtClientSettings_WorldSorting,
		nxtClientSettings_MusicSorting,
		nxtClientSettings_EmoteSorting,
		nxtClientSettings_OoOMovementSpeed,
		nxtClientSettings_OoORotationSpeed,
		nxtClientSettings_LanguageSelected,
		nxtClientSettings_SettingsVersion,

		FrameRate,
		OS_TYPE
	;

	public static String
		nxtClientSettings_TemporaryUserID = "",
		nxtClientSettings_TemporaryUsername = "",

		OS = System.getProperty("os.name").toLowerCase(),
		NXT_REGISTRY_LOCATION_BASE,
		Cache_settings_location,
		configuration_location
	;

	public final static String
		CACHE_KEY_VT_VARC_FULLSCREEN_RESOLUTION = "178",
		CACHE_KEY_VT_VARC_CUSTOM_CURSORS = "987",
		CACHE_KEY_VT_VARC_SCREEN_SIZING = "994",
		CACHE_KEY_VT_VARC_WORLD_SORTING = "996",
		CACHE_KEY_VT_VARC_WORLD_SORTING_HISTORY = "997",
		CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1 = "998",
		CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2 = "999",
		CACHE_KEY_VT_VARC_TASKS_COMPLETED_POPUP = "1429",
		CACHE_KEY_VT_VARC_HOVER_OVER_TOOLTIPS = "1686",
		CACHE_KEY_VT_VARC_CAMERA_ZOOM = "1971",
		CACHE_KEY_VT_VARC_ABILITY_BAR_MINIMIZED = "2058",
		CACHE_KEY_VT_VARC_EMOTE_SORTING = "2768",
		CACHE_KEY_VT_VARC_KEYBOARD_H_SENSITIVITY = "2827",
		CACHE_KEY_VT_VARC_KEYBOARD_V_SENSITIVITY = "2828",
		CACHE_KEY_VT_VARC_MOUSE_H_SENSITIVITY = "2829",
		CACHE_KEY_VT_VARC_MOUSE_V_SENSITIVITY = "2830",
		CACHE_KEY_VT_VARC_REMEMBER_USERNAME = "3681",
		CACHE_KEY_VT_VARC_SAVED_USERNAME = "3683",
		CACHE_KEY_VT_VARC_LOADING_SCREENS = "3698",
		CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3 = "4272",
		CACHE_KEY_VT_VARC_OoO_MOVEMENT_SPEED = "4667",
		CACHE_KEY_VT_VARC_OoO_ROTATION_SPEED = "4668",
		CACHE_KEY_VT_VERC_MUSIC_SORTING = "5917",
		CACHE_KEY_VT_VERC_WALLPAPER_ID = "6040",
		CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER = "6042",
		CACHE_KEY_VT_VERC_LOOP_CURRENT_TRACK = "6348"
	;

	public static boolean
		nxtGraphicsSetting_FlickeringEffects = true,
		nxtGraphicsSetting_TerrainBlending = true,
		nxtGraphicsSetting_GroundDecor = true,
		nxtGraphicsSetting_Shadows,
		nxtGraphicsSetting_CustomCursors = true,
		nxtGraphicsSetting_LoadingScreens = true,

		nxtClientSettings_LoopCurrentTrack,
		nxtClientSettings_RandomizeLoginWallpaper = true,
		nxtClientSettings_RememberUsername = true,
		nxtClientSettings_MouseOverTooltip = true,
		nxtClientSettings_TaskCompletedPopup = true,
		nxtClientSettings_AbilityBarMinimized,
		nxtClientSettings_CompatibilityMode,
		nxtClientSettings_AskToSwitchToCompatibility = true,
		nxtClientSettings_AskBeforeQuitting = true,

		NXT_INSTALLED,
		ShowSensitiveInfo,

		DEVELOPER_DebugsEnabled,
		DEVELOPER_ReadOnlyCache,
		DEVELOPER_AlwaysShowSensitiveInfo,
		DEVELOPER_WindowAlwaysOnTop
	;

   public static File preferences_config, Settings_db;
	public static Connection conn;
	public static Statement stmt;
	public static StringBuilder messages;
	public static List<String> nxtClientSettings_DeveloperConsoleLog = new ArrayList<>(100);
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
				"clientdrop",
				"fps ",
				"360camera",
				"debugcamera",
				"getclientvarp",
				"directlogin ",
				"setlobby ",
				"setvar ",
				"setstat ",
				"setcamera ",
				"advancestat ",
				"tele ",
				"teleto ",
				"teletome ",
				"addnpc ",
				"give ",
				"~eqme ",
				"~jmod_tool",
				"~htfi",
				"~hideall",
				"~unhideall",
				"~test_hitall"
			}
	};
	public final static String[] LANGUAGES = {
			"English",
			"Deutsch",
			"Fran\u00E7ias",
			"Portugu\u00EAs"
	};
	public final static String[][] SETTINGS_OPTIONS = {
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
							      "5 Frames/Second",
			 "10 Frames/Second", "15 Frames/Second",
			 "20 Frames/Second", "25 Frames/Second",
			 "30 Frames/Second", "35 Frames/Second",
			 "40 Frames/Second", "45 Frames/Second",
			 "50 Frames/Second", "55 Frames/Second",
			 "60 Frames/Second", "65 Frames/Second",
			 "70 Frames/Second", "75 Frames/Second",
			 "80 Frames/Second", "85 Frames/Second",
			 "90 Frames/Second", "95 Frames/Second",
			"100 Frames/Second","105 Frames/Second",
			"110 Frames/Second","115 Frames/Second",
			"120 Frames/Second","125 Frames/Second",
			"130 Frames/Second","135 Frames/Second",
			"140 Frames/Second","145 Frames/Second",
			"150 Frames/Second","155 Frames/Second",
			"160 Frames/Second","165 Frames/Second",
			"170 Frames/Second","175 Frames/Second",
			"180 Frames/Second","185 Frames/Second",
			"190 Frames/Second","195 Frames/Second",
			"200 Frames/Second","205 Frames/Second",
			"210 Frames/Second","215 Frames/Second",
			"220 Frames/Second","225 Frames/Second",
			"230 Frames/Second","235 Frames/Second",
			"240 Frames/Second","245 Frames/Second",
			"250 Frames/Second","255 Frames/Second",
			"260 Frames/Second","265 Frames/Second",
			"270 Frames/Second","275 Frames/Second",
			"280 Frames/Second","285 Frames/Second",
			"290 Frames/Second","295 Frames/Second",
			"300 Frames/Second"
		},
		{
			/* 15: UI Scaling Settings (Minimum 100; Maximum 400; Increments of 5) */
			"100% Scale","105% Scale",
			"110% Scale","115% Scale",
			"120% Scale","125% Scale",
			"130% Scale","135% Scale",
			"140% Scale","145% Scale",
			"150% Scale","155% Scale",
			"160% Scale","165% Scale",
			"170% Scale","175% Scale",
			"180% Scale","185% Scale",
			"190% Scale","195% Scale",
			"200% Scale","205% Scale",
			"210% Scale","215% Scale",
			"220% Scale","225% Scale",
			"230% Scale","235% Scale",
			"240% Scale","245% Scale",
			"250% Scale","255% Scale",
			"260% Scale","265% Scale",
			"270% Scale","275% Scale",
			"280% Scale","285% Scale",
			"290% Scale","295% Scale",
			"300% Scale","305% Scale",
			"310% Scale","315% Scale",
			"320% Scale","325% Scale",
			"330% Scale","335% Scale",
			"340% Scale","345% Scale",
			"350% Scale","355% Scale",
			"360% Scale","365% Scale",
			"370% Scale","375% Scale",
			"380% Scale","385% Scale",
			"390% Scale","395% Scale",
			"400% Scale"
		},
		{
			/* 16: Game World Scaling Settings (Minimum 33; Maximum 200; Increments of 5; Indirect Minimum of 35) */
			"35% Scale","40% Scale",
			"45% Scale","50% Scale",
			"55% Scale","60% Scale",
			"65% Scale","70% Scale",
			"75% Scale","80% Scale",
			"85% Scale","90% Scale",
			"95% Scale","100% Scale",
			"105% Scale","110% Scale",
			"115% Scale","120% Scale",
			"125% Scale","130% Scale",
			"135% Scale","140% Scale",
			"145% Scale","150% Scale",
			"155% Scale","160% Scale",
			"165% Scale","170% Scale",
			"175% Scale","180% Scale",
			"185% Scale","190% Scale",
			"195% Scale","200% Scale",
		},
		{
			/* 17: World List sorting */
			"World Number: Least-to-Most",
			"World Number: Most-to-Least",
			"Players Online: Least-to-Most",
			"Players Online: Most-to-Least",
			"Activity/Location: A-to-Z",
			"Activity/Location: Z-to-A",
			"Lootshare: Disabled",
			"Lootshare: Enabled",
			"Membership: F2P First",
			"Membership: P2P First",
			"Ping: Lowest Ping First",
			"Ping: Highest Ping First"
		},
		{
			/* 18: Music List sorting */
			"ALL music will display.",
			"All UNLOCKED music will display.",
			"All LOCKED music will display.",
		},
		{
			/* 19: Wallpapers */
			"Default",
			"Nex: Angel of Death",
			"15th Year Anniversary",
			"The Arc: Batch 2",
			"Prifddinas",
			"Sliske's Endgame",
			"Children of Mah",
			"Heart of Gielinor",
			"Memorial to Guthix",
			"Invention",
			"Mega May",
			"River of Blood",
			"Dimension of Disaster",
			"Summer Special '16",
			"Back to the Freezer",
			"Menaphos Gates",
			"Menaphos Tombs",
			"Menaphos Thralls",
			"Menaphos Characters",
			"Ghost Stries of Gielinor",
			"Dimension of the Damned",
			"The G-Nome Project",
			"Unknown Wallpaper"
		},
		{
			/* 20: Emotes */
			"All",
			"All (Unlocked)",
			"Greetings",
			"Conversation",
			"Dance",
			"Feelings",
			"Mime",
			"Zombie",
			"Holiday",
			"Achievements",
			"Weather",
			"Treasure Trail",
			"Loyalty",
			"Clan Stage",
			"Clan Audience",
			"Store Emotes"
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
					"Note: The first frame of this will be the wallpaper to the left.<br>" +
					"If this option is unchecked, that wallpaper will be used." +
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