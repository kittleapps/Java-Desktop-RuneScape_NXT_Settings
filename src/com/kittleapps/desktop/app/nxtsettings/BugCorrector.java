package com.kittleapps.desktop.app.nxtsettings;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class BugCorrector {

	private static File js5_16;

	public static void CheckKnownBugs() throws IOException {
		if (CheckArdougneCrasher()) {
			final int dialogResult = JOptionPane.showConfirmDialog(null,"<html><center>The Ardougne crash bug was detected in your cache!<br>Do you want this program to patch it for you?<br><br>This will patch it by deleting the file 'js5-16.jcache' from your cache directory.","Ardougne crash detected!",JOptionPane.ERROR_MESSAGE);
			if(dialogResult == JOptionPane.YES_OPTION){
				js5_16.delete();
				JOptionPane.showMessageDialog(null, "<html><center>The Ardougne crash has been patched.<br>Please Reload NXT to redownload the corrected file.");
			}
			else {
				JOptionPane.showMessageDialog(null, "<html><center>This program will not patch the Ardougne crash at this time.");
			}
		}
	}

	private static boolean CheckArdougneCrasher() {
		boolean wasPatched = true; // Patched Feb 5th, 2018;
		if (wasPatched) {
			return false;
		}
		else {
			js5_16 = new File(Storage.Cache_location+"js5-16.jcache");
			String DataOutput = "data", CorruptedDataOutput = "Data";
			if(js5_16.exists() && js5_16.isFile()) {
				Connection conn = null;
				Statement stmt = null;
				try {
					conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_location + "js5-16.jcache");
					stmt = conn.createStatement();
				} catch(final SQLException e) {}
	
				try(ResultSet rs = stmt.executeQuery("SELECT * FROM 'cache'")) {
					while (rs.next()) {
						switch(rs.getString("KEY")) {
						case "431":
							final byte[] Data = rs.getBytes("DATA");
							final byte[] KnownCorruptedData = {90,76,66,1,0,0,0,45,120,-100,3,0,0,0,0,1};
							DataOutput = "";
							CorruptedDataOutput = "";
							for (final byte data : Data) {
								DataOutput = DataOutput+data;
							}
							for (final byte data : KnownCorruptedData) {
								CorruptedDataOutput = CorruptedDataOutput+data;
							}
							break;
						}
					}
					conn.close();
					stmt.close();
					rs.close();
				} catch (final SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return DataOutput.equals(CorruptedDataOutput);
		}
	}
}
