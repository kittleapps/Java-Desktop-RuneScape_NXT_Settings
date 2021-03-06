# Java-Desktop-RuneScape_NXT_Settings
**Basic Description**:

Manually and Externally edits various NXT-Client settings offline. (Removing the need to load up the client)

- - - -

**How-To Install/Use/Compile this program**:

See [Here](https://raw.githubusercontent.com/kittleapps/Java-Desktop-RuneScape_NXT_Settings/master/How-To.txt) for general installation, running, and compiling instructions.

- - - -

**Beginners Notes**:
* This program is still experimental, and may cause issues.
* Not all functions in this program act as-if on the client.
* Not all of these functions may comply to Jagex's ToS (e.g. Enabling Hidden settings)
* This program can become obsolete in any NXT update, I will try to update when I feel up-to it/have time.
* This program does **NOT** Collect user information outside the program. 
  * Meaning, what's read by the program, stays in the program.
    * Except what's saved in the `Settings.jcache` if you allowed it to write.
  * Nothing is sent outside the program, and can be ran purely offline.
  * **ANY** of the program's sensitive contents clear once the program is closed.
    * When writing the username saved is cleared automatically.
    * There's no persistent log/data file being stored. (other then Jagex's `Settings.jcache` of course)
  * Your username and UID will **NOT** be taken/stolen.
- - - -

**Current Features**:

* All current graphics settings options.
  * Game Render and Interface/UI Scaling options are added. They are live, but currently their options are not usable.
* Most audio options
  * Looping the current Music Track.
  * Music List Sorting.
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
    * `Remember username?` is unchecked. (due to how the client works, if this is unchecked, it would clear it anyways making this slightly redundant)
    * `Show sensitive information?` is unchecked. due to personal security it will not be read automatically until it's checked or if you enable `DEVELOPER_ALWAYS_SHOW_SENSITIVE_INFO`. See below for more information.
    * The username field is blank on-writing.
* Favourite world editing.
* World List menu sorting.
* Keyboard Sensitivity changing.
* Mouse sensitivity changing.
* Friends, Friends Chat, Clan Chat, and Guest Clan Chat's player list divider changing.
* World Map Overlay toggling.
* World Map Icon Editing.
  * This defaults to all icons shown unless you specify otherwise.
* In-Game camera Zoom changing.
  * This doesn't allow any illegal values, as it will revert to default.
* Orb of Oculus/Freecam's Movement & Rotation Speed changing.
* Randomizing the wallpaper
  * This includes setting the regular ID for the wallpaper if not randomizing. This ID can change with any update, unfortunately.
  * The id used will be the first wallpaper shown while randomizing.
* Clearing the Developer Console log.
  * Currently in the client this is not clear-able, this merely wipes that part of the database clean.
* Populating the Developer Console log with some known commands.
  * Includes Players, and some Jagex Moderator commands.
* Developer Console history editing.
  * Full control of values/entries inside the Developer Console's history up-to 100 commands.
    * Client-Sided limitations with NXT will only display the last 100 commands used on-load. So this is the limit I'll set.
  * To delete entries, simply erase their value. If it's only spaces or empty this program will simply not add it back deleting that slot fully.
* Grabs the Internal build number from `rs2client.exe`
  * Formats to some chat formats that I use personally.
    * As-is; Or as it is in the client.
    * Reddit-format; or \`this\` to highlight it slightly in a in-line codeblock.
    * Discord-format; or \`\`\`this\`\`\` for a codeblock on a new line.
* Compatibility Mode toggling
* Switching to Compatibility Mode on-error (or when your GPU's drivers are outdated) toggling
* Prompting on-exiting toggling
* Customisation wardrobe sorting
* Language setting changing
* Presets
  * These follow the same ones in-game, with two joke ones, and the [RuneScape Wiki Guidelines](http://runescape.wikia.com/wiki/RuneScape:Images_and_media_policy#Content)
* Partial Program function disabling.
  * This is currently a WIP, and more options will be added later on.
  * This mechanic will add a new table `Config-External` to `Settings.Jcache`, but is only used for this program.
  * Some mechanics are not suitable for most people as I add more to this, but they are all 100% optional.
    * An example of one that may not be suitable is `DEVELOPER_ALWAYS_SHOW_SENSITIVE_INFO`, which would help in speed-updating of values without having to check the `Show sensitive information?` permission, re-read, and save. This may be unwanted because it will make your username visible on-launch for instance.
  * Currently the options for this mechanic are:
    * `TableCreated (yyyy-MM-dd hh:mm:ss)`, Nothing more for this as it's just to let you know when this program originally made the current table. This may be removed at a later time.
    * `DEVELOPER_DEBUGS_ENABLED`, which allows this mechanic to be used. If this is set to `false` any other mechanics will not be utilized, and will be disabled.
    * `DEVELOPER_ALWAYS_SHOW_SENSITIVE_INFO`, which was explained a bit above. It allows sensitive information like your username to be read and visible on-start, skipping the `Show sensitive information?` permission to allow faster saving.
    * `DEVELOPER_READ_ONLY_CACHE`, which as it may seem it prevents the settings from being written once changed. This will make the program a basic read-only program to view your settings offline, without worrying about it being overwritten.
    * `DEVELOPER_ALWAYS_STAY_ON_TOP`, which makes the program always stay on top other windows. This option will render the toggle useless.
    * `DEVELOPER_ALWAYS_START_ON_TOP`, which works similar to `DEVELOPER_ALWAYS_STAY_ON_TOP` but allows the use of the toggle.
