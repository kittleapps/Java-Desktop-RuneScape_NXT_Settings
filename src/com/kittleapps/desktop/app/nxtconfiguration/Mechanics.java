package com.kittleapps.desktop.app.nxtconfiguration;

import java.io.File;
import java.io.IOException;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;

public class Mechanics {

	public static String
		NXT_REGISTRY_LOCATION_BASE = "Software\\Jagex\\RuneScape",
		Cache_settings_location = "",
		configuration_location = "",
		launcher_client_position = "",
		current_line = "";
	public static File preferences_config, Settings_db;

	public static String graphicsSettingsFilter(final int FilterType, final String msg) {
		/*
		 * > Get filter type/ID
		 *
		 * > Get the String from msg
		 *
		 * > Replace spaces with "_=_" for verbose output use
		 *
		 * > Replace underscores with spaces for verbose output use
		 *
		 * > Replace some values with a user-friendly readable string for Verbose output use
		 *
		 * > Return the value prepended with a tab
		 */
		String value = msg.replace(" ", "_=_").replace("_", " ");
		if (FilterType == -1) {} else if (FilterType == 0) {
			value = value.replace("0", "Disabled")
						 .replace("1", "Enabled");
		} else if (FilterType == 1) {
			value = value.replace("0", "Low")
						 .replace("1", "Medium")
						 .replace("2", "High")
						 .replace("3", "Ultra");
		} else if (FilterType == 2) {
			value = value.replace("0", "Off")
						  .replace("1", "Low")
						  .replace("2", "Medium")
						  .replace("3", "High")
						  .replace("4", "Ultra");
		} else if (FilterType == 3) {
			value = value.replace("0", "Off")
						 .replace("1", "SSAO")
						 .replace("2", "HBAO");
		} else if (FilterType == 4) {
			value = value.replace("0", "Off")
						 .replace("1", "Compressed")
						 .replace("2", "Uncompressed");
		} else if (FilterType == 5) {
			value = value.replace("0", "Off")
						 .replace("1", "FXAA")
						 .replace("2", "MSAA")
						 .replace("3", "FXAA+MSAA");
		} else if (FilterType == 6) {
			value = value.replace("0", "Off")
						 .replace("1", "x2")
						 .replace("2", "x4")
						 .replace("3", "x8")
						 .replace("4", "x16");
		} else if (FilterType == 7) {
			value = value.replace("-1", "Adaptive")
						 .replace("0",  "Off")
						 .replace("1",  "On")
						 .replace("2",  "Half")
						 .replace("3",  "Quarter");
		} else if (FilterType == 8) {
			value = value.replace("0", "None")
						 .replace("1", "Selectively")
						 .replace("2", "Always")
						 .replace("3", "All");
		} else if (FilterType == 10) {
			value = value.replace("0", "Off (0)");
		} else if (FilterType == 11) {
			value = value.replace("0", "Lowest (Slider = 0%)")
						 .replace("1", "Low (Slider = 25%)")
						 .replace("2", "Medium (Slider = 50%)")
						 .replace("3", "High (Slider = 75%)")
						 .replace("4", "Highest (Slider = 100%)");
		} else {
			value = "Invalid FilterType Used.";
		}
		return "\t" + value;
	}

	public static void SendVerboseMessage(final String msg) {
		/*
		 * > Get the String in msg
		 *
		 * > Convert to a <html> formatted String
		 *
		 * > Initialize a temporary Document
		 *
		 * > Append to NXTSettingsGUI.VerboseOutputAreaEditor
		 */
		Storage.messages.append("<div class=\"test\"><font color=\"green\">" + msg + "</font></div>\n");
		final Document doc = NXTSettingsGUI.VerboseOutputArea.getDocument();
		try {
			NXTSettingsGUI.VerboseOutputAreaEditor.insertHTML((HTMLDocument) doc, doc.getLength(),
															"<div class=\"test\"><font color=\"green\">" +
															 msg.replace("{{nl}}", "<br>").replace("\t", "&ensp;&ensp;&ensp;") +
															 "</font></div>", 0, 0, null);
		} catch(BadLocationException | IOException e) {
			e.printStackTrace();
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