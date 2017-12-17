package com.kittleapps.desktop.app.nxtsettings;


public class History {	public static int

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
	nxtClientSettings_CameraZoom,
	nxtClientSettings_KeyboardHSensitivity,
	nxtClientSettings_KeyboardVSensitivity,
	nxtClientSettings_MouseHSensitivity,
	nxtClientSettings_MouseVSensitivity,
	nxtClientSettings_WorldSorting,
	nxtClientSettings_LanguageSelected,
	nxtClientSettings_SettingsVersion,

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
	CACHE_KEY_VT_VARC_KEYBOARD_H_SENSITIVITY = "2827",
	CACHE_KEY_VT_VARC_KEYBOARD_V_SENSITIVITY = "2828",
	CACHE_KEY_VT_VARC_MOUSE_H_SENSITIVITY = "2829",
	CACHE_KEY_VT_VARC_MOUSE_V_SENSITIVITY = "2830",
	CACHE_KEY_VT_VARC_REMEMBER_USERNAME = "3681",
	CACHE_KEY_VT_VARC_SAVED_USERNAME = "3683",
	CACHE_KEY_VT_VARC_LOADING_SCREENS = "3698",
	CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3 = "4272",
	CACHE_KEY_VT_VERC_WALLPAPER_ID = "6040",
	CACHE_KEY_VT_VERC_RANDOMIZE_WALLPAPER = "6042",
	CACHE_KEY_VT_VERC_LOOP_CURRENT_TRACK = "6348"
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
	nxtClientSettings_LoopCurrentTrack,
	nxtClientSettings_RandomizeLoginWallpaper = true,
	nxtClientSettings_RememberUsername = true,
	nxtClientSettings_MouseOverTooltip = true,
	nxtClientSettings_TaskCompletedPopup = true,
	nxtClientSettings_CompatibilityMode,
	nxtClientSettings_AskToSwitchToCompatibility = true,
	nxtClientSettings_AskBeforeQuitting = true,

	// In-Program application/storage options;
	History_Saved
;

	public static void init(){
		// Initialize the temporary History values with the currently set ones.

		// Graphics Settings (ints)
		nxtGraphicsSetting_AnisotropicFiltering				=	Storage.nxtGraphicsSetting_AnisotropicFiltering;
		nxtGraphicsSetting_AntiAliasingMode					=	Storage.nxtGraphicsSetting_AntiAliasingMode;
		nxtGraphicsSetting_AntiAliasingQuality				=	Storage.nxtGraphicsSetting_AntiAliasingQuality;
		nxtGraphicsSetting_Bloom							=	Storage.nxtGraphicsSetting_Bloom;
		nxtGraphicsSetting_Brightness						=	Storage.nxtGraphicsSetting_Brightness;
		nxtGraphicsSetting_DrawDistance						=	Storage.nxtGraphicsSetting_DrawDistance;
		nxtGraphicsSetting_LightingQuality					=	Storage.nxtGraphicsSetting_LightingQuality;
		nxtGraphicsSetting_ShadowQuality					=	Storage.nxtGraphicsSetting_ShadowQuality;
		nxtGraphicsSetting_TextureQuality					=	Storage.nxtGraphicsSetting_TextureQuality;
		nxtGraphicsSetting_FullScreenWidth					=	Storage.nxtGraphicsSetting_FullScreenWidth;
		nxtGraphicsSetting_FullScreenHeight					=	Storage.nxtGraphicsSetting_FullScreenHeight;
		nxtGraphicsSetting_AmbientOcclusion					=	Storage.nxtGraphicsSetting_AmbientOcclusion;
		nxtGraphicsSetting_WaterQuality						=	Storage.nxtGraphicsSetting_WaterQuality;
		nxtGraphicsSetting_VolumetricLighting				=	Storage.nxtGraphicsSetting_VolumetricLighting;
		nxtGraphicsSetting_VSync							=	Storage.nxtGraphicsSetting_VSync;
		nxtGraphicsSetting_RemoveRoofs						=	Storage.nxtGraphicsSetting_RemoveRoofs;
		nxtGraphicsSetting_MaxForegroundFps					=	Storage.nxtGraphicsSetting_MaxForegroundFps;
		nxtGraphicsSetting_MaxBackgroundFps					=	Storage.nxtGraphicsSetting_MaxBackgroundFps;

		// Client Settings (ints)
		nxtClientSettings_FavouriteWorld1					=	Storage.nxtClientSettings_FavouriteWorld1;
		nxtClientSettings_FavouriteWorld2					=	Storage.nxtClientSettings_FavouriteWorld2;
		nxtClientSettings_FavouriteWorld3					=	Storage.nxtClientSettings_FavouriteWorld3;
		nxtClientSettings_LoginMusicVolume					=	Storage.nxtClientSettings_LoginMusicVolume;
		nxtClientSettings_InGameMusicVolume					=	Storage.nxtClientSettings_InGameMusicVolume;
		nxtClientSettings_InGameSoundEffectsVolume			=	Storage.nxtClientSettings_InGameSoundEffectsVolume;
		nxtClientSettings_InGameAmbientSoundEffectsVolume	=	Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume;
		nxtClientSettings_InGameVoiceOverVolume				=	Storage.nxtClientSettings_InGameVoiceOverVolume;
		nxtClientSettings_LoginWallpaperID					=	Storage.nxtClientSettings_LoginWallpaperID;
		nxtClientSettings_UIScaling							=	Storage.nxtClientSettings_UIScaling;
		nxtClientSettings_GameWorldScaling					=	Storage.nxtClientSettings_GameWorldScaling;
		nxtClientSettings_CameraZoom						=	Storage.nxtClientSettings_CameraZoom;
		nxtClientSettings_KeyboardHSensitivity				=	Storage.nxtClientSettings_KeyboardHSensitivity;
		nxtClientSettings_KeyboardVSensitivity				=	Storage.nxtClientSettings_KeyboardVSensitivity;
		nxtClientSettings_MouseHSensitivity					=	Storage.nxtClientSettings_MouseHSensitivity;
		nxtClientSettings_MouseVSensitivity					=	Storage.nxtClientSettings_MouseVSensitivity;
		nxtClientSettings_WorldSorting						=	Storage.nxtClientSettings_WorldSorting;
		nxtClientSettings_LanguageSelected					=	Storage.nxtClientSettings_LanguageSelected;

		// User Settings (Strings)
		nxtClientSettings_TemporaryUserID					=	Storage.nxtClientSettings_TemporaryUserID;
		nxtClientSettings_TemporaryUsername					=	Storage.nxtClientSettings_TemporaryUsername;

		// Graphics Settings (booleans)
		nxtGraphicsSetting_FlickeringEffects				=	Storage.nxtGraphicsSetting_FlickeringEffects;
		nxtGraphicsSetting_TerrainBlending					=	Storage.nxtGraphicsSetting_TerrainBlending;
		nxtGraphicsSetting_GroundDecor						=	Storage.nxtGraphicsSetting_GroundDecor;
		nxtGraphicsSetting_Shadows							=	Storage.nxtGraphicsSetting_Shadows;
		nxtGraphicsSetting_CustomCursors					=	Storage.nxtGraphicsSetting_CustomCursors;
		nxtGraphicsSetting_LoadingScreens					=	Storage.nxtGraphicsSetting_LoadingScreens;

		// Client Settings (booleans)
		nxtClientSettings_LoopCurrentTrack					=	Storage.nxtClientSettings_LoopCurrentTrack;
		nxtClientSettings_RandomizeLoginWallpaper			=	Storage.nxtClientSettings_RandomizeLoginWallpaper;
		nxtClientSettings_RememberUsername					=	Storage.nxtClientSettings_RememberUsername;
		nxtClientSettings_MouseOverTooltip					=	Storage.nxtClientSettings_MouseOverTooltip;
		nxtClientSettings_TaskCompletedPopup				=	Storage.nxtClientSettings_TaskCompletedPopup;

		// Initializer boolean
		History_Saved = true;
	}
}
