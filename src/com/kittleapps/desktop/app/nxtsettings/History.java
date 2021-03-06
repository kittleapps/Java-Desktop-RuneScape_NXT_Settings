package com.kittleapps.desktop.app.nxtsettings;


public class History {
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
		nxtGraphicsSetting_ScreenSizingMode					=	Storage.nxtGraphicsSetting_ScreenSizingMode;
		nxtGraphicsSetting_ResizableResolution				=	Storage.nxtGraphicsSetting_ResizableResolution;

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
		nxtClientSettings_MusicSorting						=	Storage.nxtClientSettings_MusicSorting;
		nxtClientSettings_EmoteSorting						=	Storage.nxtClientSettings_EmoteSorting;
		nxtClientSettings_WorldMapOverlays					=	Storage.nxtClientSettings_WorldMapOverlays;
		nxtClientSettings_OoOMovementSpeed					=	Storage.nxtClientSettings_OoOMovementSpeed;
		nxtClientSettings_OoORotationSpeed					=	Storage.nxtClientSettings_OoORotationSpeed;
		nxtClientSettings_FriendsListDivider				=	Storage.nxtClientSettings_FriendsListDivider;
		nxtClientSettings_FriendsChatListDivider			=	Storage.nxtClientSettings_FriendsChatListDivider;
		nxtClientSettings_ClanChatListDivider				=	Storage.nxtClientSettings_ClanChatListDivider;
		nxtClientSettings_GuestClanChatListDivider			=	Storage.nxtClientSettings_GuestClanChatListDivider;
		nxtClientSettings_CustomisationsWardrobe			=	Storage.nxtClientSettings_CustomisationsWardrobe;
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
		nxtGraphicsSetting_CanopyCutout		 				=	Storage.nxtGraphicsSetting_CanopyCutout;
		nxtGraphicsSetting_SmoothClipFade					=	Storage.nxtGraphicsSetting_SmoothClipFade;
		nxtGraphicsSetting_EntityHighlights					=	Storage.nxtGraphicsSetting_EntityHighlights;
		nxtGraphicsSetting_DepthOfField						=	Storage.nxtGraphicsSetting_DepthOfField;
		nxtGraphicsSetting_LoadingScreens					=	Storage.nxtGraphicsSetting_LoadingScreens;

		// Client Settings (booleans)
		nxtClientSettings_LoopCurrentTrack					=	Storage.nxtClientSettings_LoopCurrentTrack;
		nxtClientSettings_RandomizeLoginWallpaper			=	Storage.nxtClientSettings_RandomizeLoginWallpaper;
		nxtClientSettings_RememberUsername					=	Storage.nxtClientSettings_RememberUsername;
		nxtClientSettings_HideUsername						=	Storage.nxtClientSettings_HideUsername;

		// Initializer boolean
		History_Saved = true;
	}
}
