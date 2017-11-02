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
		nxtClientSettings_LanguageSelected
	;

	public static String
		nxtClientSettings_TemporaryUserID = "",
		nxtClientSettings_TemporaryUsername = ""
	;

	public static void init(){
		// Initialize the temporary History values with the currently set ones.
		// These will be used in the writing mechanics to only replace updated values and hopefully speed up saving.

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
		nxtClientSettings_RandomizeLoginWallpaper			=	Storage.nxtClientSettings_RandomizeLoginWallpaper;
		nxtClientSettings_RememberUsername					=	Storage.nxtClientSettings_RememberUsername;
		
		// Initializer boolean
		History_Saved = true;
	}

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
		nxtClientSettings_CompatibilityMode,
		nxtClientSettings_AskToSwitchToCompatibility,
		nxtClientSettings_AskBeforeQuitting,
		History_Saved
	;
}
