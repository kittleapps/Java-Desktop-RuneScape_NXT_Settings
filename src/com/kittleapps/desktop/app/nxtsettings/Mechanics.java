package com.kittleapps.desktop.app.nxtsettings;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Mechanics {

	public static void GrabInternalBuildNumber(boolean CopyToClipboard){
		// Location will always be with the preferences file, so let's just change the path.
		String Path = Storage.configuration_location.replace("preferences.cfg", "rs2client.exe");
		File file = new File(Path);

		// Initializers for the file and clipboard manager.
		FileInputStream in = null;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();

		// Check if the file exists.
		if (!file.exists()) {
			System.out.println(file.getAbsolutePath() + " does not exist. Abort!");
			JOptionPane.showMessageDialog(null, file.getAbsolutePath() + " does not exist.", "Error.", JOptionPane.ERROR_MESSAGE);
		} else if (!file.canRead()) {
			System.out.println(file.getAbsolutePath() + " can't be read. Abort!");
			JOptionPane.showMessageDialog(null, file.getAbsolutePath() + " can't be read.", "Error.", JOptionPane.ERROR_MESSAGE);
		} else if (!file.isFile()) {
			System.out.println("Somehow, " + file.getAbsolutePath() + " isn't a file. Abort!");
			JOptionPane.showMessageDialog(null, "Somehow " + file.getAbsolutePath() + " isn't a file.", "Error.", JOptionPane.ERROR_MESSAGE);
		}

		try {
			// Read the file in bytes; Convert to a string.
			in = new FileInputStream(file);
			byte fileBytes[] = new byte[(int) file.length()];
			in.read(fileBytes);
			String fileContents = new String(fileBytes);
			in.close();

			// Locate the build label.
			int offset = fileContents.indexOf("NXT-BUIL");
			int end = fileContents.indexOf("\\build\\nxt\\bin\\rs2client\\FINAL\\rs2client.pdb");
			String output = "Invalid";
			if (offset > 0 && end > offset){
				output = fileContents.substring(offset, end);
			}
			System.out.println("NXT Build: " + output);
			NXTSettingsGUI.frame.setTitle("NXT's Settings (Graphic Setting's Version: \""+Storage.nxtClientSettings_SettingsVersion+"\"; Internal Build Label: \""+output+"\")");

			if (CopyToClipboard){
				// Ask to format the output prior to being sent to the clipboard; Format it in the selected manor.
				Object[] DialogueOptions = {
						"As-is",
						"Reddit-format",
						"Discord-format",
						"Cancel" };
				int n = JOptionPane.showOptionDialog(null,
						"Would you like to format the output? If so, which way?",
						"Format it?",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						DialogueOptions,
						DialogueOptions[3]);

				if (n == 1) {
					// Reddit-format was selected.
					output = "`" + output + "`";
				} else if (n == 2) {
					// Discord-format was selected.
					output = "\n```\n" + output + "\n```";
				}

				if (n >= 0 && n < 3) {
					// Add the output to the keyboard.
					StringSelection strSel = new StringSelection(output);
					clipboard.setContents(strSel, null);
					JOptionPane.showMessageDialog(null,
							"Copied NXT's build label to the clipboard.",
							"Done.",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
