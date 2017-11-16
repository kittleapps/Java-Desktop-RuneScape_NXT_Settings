# Java-Desktop-RuneScape_NXT_Settings
**Basic Description**:
Manually and Externally edits various NXT-Client settings offline. (no need to load the client)
- - - -
**Beginners Notes**:
* This program is still experimental, and may cause issues.
* Not all functions in this program act as-if on the client.
* Not all of these functions comply to Jagex's ToS (e.g. boosting the volumes/"None" Remove Roofs)
* This program can become obslete in any NXT update, I will try to update when I feel up-to it/have time.
* This program does **NOT** Collect user information outside the program. 
  * Meaning, what's read by the program, stays in the program.
    * Except what's saved in the `Settings.jcache` if you allowed it to write.
  * Nothing is sent outside the program, and can be ran purely offline.
    * Nothing in the program uses internet anyways..
  * **ANY** of the program's sensitive contents clear once the program is closed.
    * When writing the username saved is cleared automatically.
    * There's no persistant log/data file being stored. (other then Jagex's `Settings.jcache` of course)
  * Your username and UID will **NOT** be taken/stolen.
    * Straps on a tin-foil hat to reply: `You can check the source code here if you still don't believe me.`
  * **NO** Pre-compiled versions will be made, due to the above points. (people get paranoia, and point fingers)
    * Okay.. I made some Pre-compiled versions.. But that was because I got tired of "How do I build this?" questions. (Standard "Use at your own risk." Disclaimer here, despite no risks at all..)
- - - -
**Current Features**:
* All current graphics settings options
  * Game Render and Interface/UI Scaling options are added. They are live, but currently their options are not.
    * These scaling mechanics are currently experimental, and not meant for full use my players. Visual issues may occur.
* All audio options
  * Includes boosting the volumes for most audio streams
    * While I personally have not heard any quality-issues, this makes the in-game bars go past their normal limits visually.
  * Global audio mute toggle is included. (programmatically, this is bugged in the clients as the volumes are read first, then global audio mute when Sound Settings are opened. so this toggle may be redundant)
* Editing your username field
  * This does NOT save anywhere but in `Settings.jcache` where the settings are normally stored.
  * This allows Jagex-Exclusive code-markups to be used such as:
    * `<br>` for new-lines
    * `<i>` for italic lettering.
    * `<b>` for bold lettering.
    * `<sprite=ID,SUB_ID>` for sprites (e.g. `<sprite=1455,1>` for the `staffmodlevel`/Jagex Moderator crowns
    * `<col=RRGGBB>` for colouring the text.
  * Buttons to add some of these flags are included.
  * The username will clear if:
    * `Remember username?` in unchecked. (due to how the client works, if this is unchecked, it would clear it anyways making this slightly redundant)
    * `Show sensitive information?` is unchecked. (due to personal security it will not be read automatically until it's checked)
    * The username field is blank on-writing.
* Favourite world editing
  * Buttons are included to make it `2,147,000,000` for fun. (redundant)
  * Allows editing up-to 2,147,483,647 for visual purposes.
    * Note any world that is NOT valid will act as-if it's not on your language, or is offline.
    * In addition the the above, while in-game any invalid world will NOT show but will act as-is the slots are taken.
    * Setting to `-1` is done by-default, `-1` will also be applied to slots 2 and/or 3 depending if 1 and or 2 is set to `-1` to prevent visual glitching/skipping of the worlds. 
    * Any value that is `-1` is read to the client as-if it was never set/removed.
* Randomizing the wallpaper
  * This includes setting the regular ID for the wallpaper if not randomizing. This ID can change with any update, unfortunately.
  * The id used will be the first wallpaper shown while randomizing.
* Clearing the Developer Console log.
  * Currently in the client this is not clear-able, this merely wipes that part of the database clean.
* Populating the Developer Console log with some known commands.
  * Includes Players, and Jagex Moderator commands.
* Zezima Button.
  * I'll let you find out what that does in the Source Code, or while ran.
    * Hint: It's cosmetic, but a fun reference for some.
* Compatibility Mode toggling
* Switching to Compatibility Mode on-error (or when your GPU's drivers are outdated) toggling
* Prompting on-exiting toggling
* Language setting changing
* Presets
  * These follow the same ones in-game, with two joke ones, and the [RuneScape Wiki Guidelines](http://runescape.wikia.com/wiki/RuneScape:Images_and_media_policy#Content)
* Partial Program function disabling.
  * This is currently a WIP, and more options will be added later on.
  * This mechanic will add a new table `Config-External` to `Settings.Jcache`, but is only used for this program.
  * Some mechanics are not suitable for most people as i add more to this, but they are all 100% optional.
    * An example of one that may not be suitable is `DEVELOPER_ALWAYS_SHOW_SENSITIVE_INFO`, which would help in speed-updating of values without having to check the `Show sensitive information?` permission, re-read, and save. This may be unwanted because it will make your username visibe on-launch for instance.
  * Currently the options for this mechanic are:
    * `TableCreated (yyyy-MM-dd hh:mm:ss)`, Nothing more for this as it's just to let you know when this program originally made the current table. This may be removed at a later time.
    * `DEVELOPER_DEBUGS_ENABLED`, which allows this mechanic to be used. If this is set to `false` any other mechanics will not be utilized, and will be disabled.
    * `DEVELOPER_ALWAYS_SHOW_SENSITIVE_INFO`, which was explained a bit above. It allows sensitve information like your username to be read and visible on-start, skipping the `Show sensitive information?` permission to allow faster saving.
    * `DEVELOPER_READ_ONLY_CACHE`, which as it may seem it prevents the settigns from being written once changed. This will make the program a basic read-only program to view your settings offline, without worrying about it being overwritten.
- - - -
**TO-DO**:
* Add custom Developer Console history editing.
  * Hopefully includes editing values, and adding/removing values
* Exporting & Importing your settings in Json. (sensitive information excluded)
* Re-add support to view your UID.
* More options to disable.
  * Unknown what else I could have it disable, maybe I'll throw in a few (fun/visual) easter eggs for the Pre-Compiled variants to be suprised with.
* Even more cleanups! (Have to stay clean!)
