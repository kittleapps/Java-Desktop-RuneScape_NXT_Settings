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
	nxtGraphicsSetting_ScreenSizingMode,
	nxtGraphicsSetting_ResizableResolution,

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
	nxtClientSettings_WorldMapOverlays,
	nxtClientSettings_OoOMovementSpeed,
	nxtClientSettings_OoORotationSpeed,
	nxtClientSettings_FriendsListDivider,
	nxtClientSettings_FriendsChatListDivider,
	nxtClientSettings_ClanChatListDivider,
	nxtClientSettings_GuestClanChatListDivider,
	nxtClientSettings_CustomisationsWardrobe,
	nxtClientSettings_LanguageSelected,
	nxtClientSettings_SettingsVersion,
	nxtClientSettings_SettingsVersionHistory,

	FrameRate,
	OS_TYPE
	;

	public static String
	nxtClientSettings_TemporaryUserID = "",
	nxtClientSettings_TemporaryUsername = "",

	OS = System.getProperty("os.name").toLowerCase(),
	NXT_REGISTRY_LOCATION_BASE,
	Cache_location,
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
	CACHE_KEY_VT_VARC_CLAN_CHAT_LIST_DIVIDER = "1035",
	CACHE_KEY_VT_VARC_FRIENDS_LIST_DIVIDER = "1036",
	CACHE_KEY_VT_VARC_FRIENDS_CHAT_LIST_DIVIDER = "1505",
	CACHE_KEY_VT_VARC_GUEST_CLAN_CHAT_LIST_DIVIDER = "1506",
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
	CACHE_KEY_VT_VARC_WORLD_MAP_OVERLAYS = "3935",
	CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3 = "4272",
	CACHE_KEY_VT_VARC_OoO_MOVEMENT_SPEED = "4667",
	CACHE_KEY_VT_VARC_OoO_ROTATION_SPEED = "4668",
	CACHE_KEY_VT_VERC_MUSIC_SORTING = "5917",
	CACHE_KEY_VT_VERC_CUSTOMIZATIONS_WARDROBE = "5936",
	CACHE_KEY_VT_VERC_WALLPAPER_ID = "6040",
	CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER = "6042",
	CACHE_KEY_VT_VERC_LOOP_CURRENT_TRACK = "6348",
	CACHE_KEY_VT_VARC_HIDE_USERNAME = "6406";
	;

	public static boolean
	nxtGraphicsSetting_DepthOfField = true,
	nxtGraphicsSetting_SmoothClipFade = true,
	nxtGraphicsSetting_EntityHighlights = true,
	nxtGraphicsSetting_CanopyCutout = true,
	nxtGraphicsSetting_FlickeringEffects = true,
	nxtGraphicsSetting_TerrainBlending = true,
	nxtGraphicsSetting_GroundDecor = true,
	nxtGraphicsSetting_Shadows,
	nxtGraphicsSetting_CustomCursors = true,
	nxtGraphicsSetting_LoadingScreens = true,

	nxtClientSettings_LoopCurrentTrack,
	nxtClientSettings_RandomizeLoginWallpaper = true,
	nxtClientSettings_RememberUsername = true,
	nxtClientSettings_HideUsername = true,
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
			"DEVELOPER_ALWAYS_START_ON_TOP",
			"DEVELOPER_LAST_GRAPHICS_VERSION_SAVED" // Always keep this one at the bottom.
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
	public static int[] WorldMapIconStorage = {
			-1,
			-1,
			-1,
			-1,
			-1
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
				/* 10: Depth of Field (Not Used, Kept for Easy of IDs) */
				"Off",
				"Near-Focus Blur",
				"Far-Focus Blur",
			},
			{
				/* 11: Texture Quality */
				"Off",
				"Compressed",
				//"Uncompressed" // Currently disabled in live. Uncomment when fixed?
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
				"Ghost Stories of Gielinor",
				"Dimension of the Damned",
				"The G-Nome Project",
				"Pieces of Hate",
				"Deep-sea Fishing",
				"Solak",
				"Unknown Wallpaper"
			},
			{
				/* 20: Emote Sorting */
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
			},
			{
				/* 21: Screen Sizing Mode */
				"Fixed",
				"Resizable",
				"FullScreen"
			},
			{
				/* 22: World Map Overlays */
				"Legend+Key Expanded; Key+Overview Visible",
				"Legend+Key Expanded; Overview Hidden",
				"Legend+Key Expanded; Key Hidden",
				"Legend+Key Expanded; Key+Overview Hidden",
				"Legend Minimized; Key+Overview Visible",
				"Legend Minimized; Overview Hidden",
				"Legend Minimized; Key Hidden",
				"Legend Minimized; Key+Overview Hidden",
				"Key Minimized; Key+Overview Visible",
				"Key Minimized; Overview Hidden",
				"Key Minimized; Key Hidden",
				"Key Minimized; Key+Overview Hidden",
				"Legend+Key Minimized; Key+Overview Visible",
				"Legend+Key Minimized; Overview Hidden",
				"Legend+Key Minimized; Key Hidden",
				"Legend+Key Minimized; Key+Overview Hidden"
			},
			{
				/* 23: Customizations Wardrobe Sorting */
				"Aura: List",
				"Aura: Grid",
				"Head: List",
				"Head: Grid",
				"Back: List",
				"Back: Grid",
				"Neck: List",
				"Neck: Grid",
				"Wings: List",
				"Wings: Grid",
				"Torso: List",
				"Torso: Grid",
				"Main-Hand: List",
				"Main-Hand: Grid",
				"Off-Hand: List",
				"Off-Hand: Grid",
				"Legs: List",
				"Legs: Grid",
				"Hands: List",
				"Hands: Grid",
				"Feet: List",
				"Feet: Grid"
			},
	};

	public static int[] CustomisationWardrobeSlots = 
		{
				59928,57880,
				2584,536,
				6680,4632,
				10776,8728,
				76312,74264,
				18968,16920,
				14872,12824,
				23064,21016,
				31256,29208,
				39448,37400,
				43544,41496
		};
	public static Object[][] WorldMapIcons = {
			//{Index, "Label", Offset-1},
			{1, true, "Agility Training", -131073},
			{3, true, "Agility Tutor", -32769},
			{0, true, "Altar", -524289},
			{0, true, "Amulet Shop", -129},
			{0, true, "Anvil", -1025},
			{0, true, "Apothecary", -268435457},
			{0, true, "Archery Shop", -131073},
			{0, true, "Axe Shop", -9},
			{0, true, "Bank", -33},
			{4, true, "Bonfire", -4097},
			{1, true, "Brewery", -268435457},
			{0, true, "Candle Shop", -16777217},
			{1, true, "Chainmail Shop", -8193},
			{3, true, "Clans: Guard", -129},
			{3, true, "Clans: Portal", -33},
			{3, true, "Clans: Scribe", -513},
			{3, true, "Clans: Sergeant-At-Arms", -257},
			{3, true, "Clans: Vexillum Stands", -17},
			{0, true, "Clothes Shop", -134217729},
			{0, true, "Combat Training", -2049},
			{3, true, "Combat Tutor", -16385},
			{3, true, "Construction Tutor", -1048577},
			{1, true, "Cookery Shop", -33},
			{1, true, "Cooking Range", -257},
			{3, true, "Cooking Tutor", -536870913},
			{4, true, "Corrupt Altar", -2049},
			{0, true, "Crafting Shop", -8388609},
			{3, true, "Crafting Tutor", -134217729},
			{4, true, "Daily Challenges", -257},
			{1, true, "Dairy Churn", -536870913},
			{2, true, "Distractions/Diversions", -1073741825},
			{4, true, "Divination Tutor", -129},
			{0, true, "Dungeon", -4097},
			{4, true, "Dungeoneering Tutor", -3},
			{2, true, "Estate Agent", -9},
			{1, true, "Farming Patch", -2097153},
			{1, true, "Farming Shop", -67108865},
			{3, true, "Farming Tutor", -524289},
			{2, true, "Fired Up Beacon", 2147483647},
			{3, true, "Firemaking Tutor", -1073741825},
			{0, true, "Fishing Shop", -33554433},
			{0, true, "Fishing Spot", -67108865},
			{3, true, "Fishing Tutor", -268435457},
			{3, true, "Fletching Tutor", -262145},
			{1, true, "Food Shop", -17},
			{1, true, "Fur Trader", -32769},
			{0, true, "Furnace", -513},
			{0, true, "Gem Shop", -4194305},
			{0, true, "General Store", -2},
			{4, true, "God Emissary", -33},
			{2, true, "Grand Exchange", -16385},
			{3, true, "Gravestone Exchange", -1025},
			{1, true, "Guide", -8388609},
			{1, true, "Hairdresser", -1048577},
			{0, true, "Helmet Shop", -17},
			{0, true, "Herbalist", -1048577},
			{3, true, "Herblore Tutor", -65537},
			{2, true, "Holiday Event", -257},
			{2, true, "Hunter Store", -3},
			{1, true, "Hunter Training", 2147483647},
			{3, true, "Hunter Tutor", -2097153},
			{4, true, "Inventor's Workbench", -513},
			{0, true, "Jewellery Shop", -2097153},
			{0, true, "Kebab Seller", -1073741825},
			{4, true, "Lamp Trader", -1025},
			{4, true, "Lodestone", -5},
			{1, true, "Loom", -134217729},
			{3, true, "Loyalty Rewards Shop", -2049},
			{1, true, "Mace Shop", -2},
			{0, true, "Magic Shop", -5},
			{1, true, "Makeover Mage", -4194305},
			{2, true, "Mini Obelisk", -536870913},
			{1, true, "Minigame", -65},
			{1, true, "Mining Shop", -4097},
			{0, true, "Mining Site", -257},
			{3, true, "Mining Tutor", -16777217},
			{2, true, "Pet Shop", -268435457},
			{3, true, "Photo Booth", -5},
			{0, true, "Platebody Shop", -16385},
			{0, true, "Platelegs Shop", -32769},
			{1, true, "Plateskirt Shop", -513},
			{1, true, "POH Portal", -33554433},
			{1, true, "Pottery Wheel", -1025},
			{4, true, "Prayer - Nexus", -17},
			{4, true, "Prayer Tutor", -2},
			{0, true, "Pub / Bar", 2147483647},
			{0, true, "Quest Start", -65},
			{1, true, "Rare Trees", -5},
			{3, true, "Resting Spots", -2},
			{3, true, "Runecrafting Altar", -4097},
			{3, true, "Runecrafting Tutor", -33554433},
			{2, true, "Sandpit", -513},
			{2, true, "Sawmill", -17},
			{0, true, "Scimitar Shop", -65537},
			{3, true, "Seasonal Activity", -65},
			{0, true, "Shield Shop", -262145},
			{2, true, "Shortcut", -65},
			{0, true, "Silk Trader", -536870913},
			{1, true, "Silver Shop", -16385},
			{4, true, "Slayer Contracts", -9},
			{1, true, "Slayer Master", -524289},
			{3, true, "Slayer Tutor", -8388609},
			{3, true, "Smithing Tutor", -67108865},
			{1, true, "Spice Shop", -65537},
			{1, true, "Spinning Wheel", -9},
			{0, true, "Staff Shop", -8193},
			{2, true, "Summoning Obelisk", -4097},
			{2, true, "Summoning Store", -8193},
			{3, true, "Summoning Tutor", -4194305},
			{0, true, "Sword Shop", -3},
			{1, true, "Tannery", -3},
			{2, true, "Taskmaster", -1025},
			{3, true, "Thieving Tutor", -131073},
			{3, true, "Tools for Games", -9},
			{1, true, "Transportation", -16777217},
			{2, true, "Vambrace Exchange", -2049},
			{1, true, "Vegitable Store", -262145},
			{1, true, "Water Source", -129},
			{3, true, "Watermill", -8193},
			{1, true, "Windmill", -2049},
			{4, true, "Wisp Colony", -65},
			{2, true, "Woodcutting Stump", -129},
			{3, true, "Woodcutting Tutor", 2147483647},
	};
	public final static String
	// HTML-Based Tooltip storage.
			AO_TOOLTIP = "<html>" + "FXAA: Fast approximate anti-aliasing is a<br>" + "high performance screen space anti-<br>"
					+ "aliasing technique.<br>" + "<br>" + "MSAA: Multisample anti-aliasing is a<br>"
					+ "superior form of antialiasing over image<br>" + "space techniques like FXAA, but with a<br>"
					+ "much higher performance requirement.<br>" + "<br>" + "FXAA+MSAA: Add FXAA to MSAA to enable<br>"
					+ "anti-aliasing to transparent objects, but<br>" + "will result in slightly blurrier images." + "</html>",
			MAXFOREGOUNDFPS_TOOLTIP = "<html>"
					+ "Manually set the maximum Frames-Per-Second (FPS) while NXT is the focused window.<br>" + "<br>"
					+ "The default value for this entry is +10 of your refresh rate. (Example: 60hz = 70 Foreground FPS)"
					+ "</html>",
			MAXBACKGOUNDFPS_TOOLTIP = "<html>"
					+ "Manually set the maximum Frames-Per-Second (FPS) while NXT is not the focused window.<br>"
					+ "<br>"
					+ "The default value for this entry is 30. (Half the \"normal\" refresh rate for monitors which is 60hz)"
					+ "</html>",
			INTERFACESCALING_TOOLTIP = "<html>" + "Manually set the interface scaling for all interfaces.<br>" + "<br>"
					+ "The default value for this option is 100.<br>" + "<br>"
					+ "Note: This option is currently experimental, and may cause graphical issues for some.<br>"
					+ "This includes messing up some layouts while using the New Interface System." + "</html>",
			GAMESCALING_TOOLTIP = "<html>" + "Manually set the game render scaling for the 3D world underlay.<br>"
					+ "<br>" + "The default value for this entry is 100.<br>" + "<br>"
					+ "Note: This option is currently experimental, and may cause graphical issues for some.<br>"
					+ "This includes blurs, and loss of FPS depending on the settings used." + "</html>",
			USERNAME_INPUT_TOOLTIP = "<html>" + "This will be the display username when loading NXT.<br>"
					+ "Currently this field allows Jagex's Colour+Sprite flags to be used.<br>" + "<br>"
					+ "Note: When using sprite+colour flags it will still read is-if you types them here<br>"
					+ "Graphically it may be a login-able name, but it will have invalid characters/login names.<br>"
					+ "Some HTML elements like &lt;br&gt;, &lt;b&gt;, and &lt;i&gt; can be used as well." + "</html>",
			FAVOURITE_WORLD_INPUT_TOOLTIP = "<html>" + "To clear this slot use the value -1.<br>"
					+ "If Slots 1 or 2 have -1 as their value, any later numbers will be disabled.<br>"
					+ "The removal is currently not a NXT limitation, but it causes graphical issues." + "</html>",
			GLOBAL_AUDIO_MUTE_TOOLTIP = "<html>" + "Disables the audio streams while logged in the game.<br>"
					+ "Other volume setting's options will remain uneffected." + "</html>",
			REMEMBER_USERNAME_TOOLTIP = "<html>"
					+ "If unchecked: NXT will clear the username on the next client load.<br>" + "<br>"
					+ "Note: This feature is redundant for this program if the username field is empty." + "</html>",
			RANDOMIZE_LOGIN_WALLPAPER_TOOLTIP = "<html>" + "Randomize your login screen's wallpaper.<br>" + "<br>"
					+ "Note: The first frame of this will be the wallpaper to the left.<br>"
					+ "If this option is unchecked, that wallpaper will be used." + "</html>",
			WALLPAPER_ID_TOOLTIP = "<html>" + "Input your desired wallpaper id.<br>" + "<br>"
					+ "The default value for this entry is 0." + "<br>"
					+ "Note: This will apply to the first wallpaper while randomizing.<br>"
					+ "This will also be the static id if randomizing is off.<br>" + "</html>",
			ADD_SPRITE_FLAG_TOOLTIP = "<html>" + "Adds the &lt;sprite=#,#&gt; flag to your username field.<br>"
					+ "This flag is that Jagex uses to add sprites to the chatbox e.g. staffmodlevel Crowns."
					+ "</html>",
			ADD_COLOUR_FLAG_TOOLTIP = "<html>" + "Adds the &lt;col=RRGGBB&gt; flag to your username field.<br>"
					+ "This flag is used by Jagex to change the colours of text full or mid-sentence e.g: Green warnings.<br>"
					+ "As far as I've known, this does NOT support alpha. Keep it in RRGGBB Hex format." + "</html>",
			CLEAR_DEV_CONSOLE_LOGS_TOOLTIP = "<html>" + "This will clear the Developer Console's command history logs."
					+ "</html>",
			POPULATE_PLAYER_DEV_CONSOLE_LOGS_TOOLTIP = "<html>"
					+ "This will populate the Developer Console's command history with player-allowed commands."
					+ "</html>",
			POPULATE_JAGEX_DEV_CONSOLE_LOGS_TOOLTIP = "<html>"
					+ "This will populate the Developer Console's command history with all known commands.<br>" + "<br>"
					+ "Note: This list in incomplete, and many commands are not use-able by players." + "</html>",
			BOOSTED_VOLUMES_TOOLTIP = "<html>" + "Checking this will make the slider's maximum goto '254'<br>"
					+ "When the volume's over the default '127' it will allow a boosted volume to occur.<br>"
					+ "This allows for louder sound effects, like mining sounds, to be heard better." + "</html>",
			GRAPHICS_PRESET_TOOLTIP = "<html>" + "Checking this will unlock the graphics presets." + "</html>",
			GRAPHICS_PRESET_BUTTON_TOOLTIP = "<html>" + "Note: This will overwrite your current settings.<br>"
					+ "You will be required to change them back manually, or re-read to restore prior to writing."
					+ "</html>"
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