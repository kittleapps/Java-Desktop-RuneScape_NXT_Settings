package com.kittleapps.desktop.app.nxtsettings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class NXTWorldMapSelectionGUI extends JFrame {

	private static final long serialVersionUID = -1599390418656101637L;
	public static JFrame frame;
	public static JLabel Content;
	public static JScrollPane scrollpane;
	private static Color backgroundColour = new Color(45, 45, 45), optionBackgroundColor = new Color (40, 40, 40);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
					frame = new NXTWorldMapSelectionGUI();
					Mechanics.CheckWorldMapIcons();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static JPanel addRow(int Row, String Label) {
		JPanel Panel = new JPanel();
		Panel.setLayout(new BorderLayout());
		Panel.setBounds(0, 30*Row, 800, 25);
		Panel.setBackground(backgroundColour);
		
		JLabel CheckboxLabel = new JLabel(Label);
		CheckboxLabel.setBackground(optionBackgroundColor);
		
		JCheckBox Checkbox = new JCheckBox();
		Checkbox.setBackground(optionBackgroundColor);
		Checkbox.setName("Checkbox_"+Row);
		Checkbox.setSelected((boolean) Storage.WorldMapIcons[Row][1]);
		Checkbox.addActionListener(e -> Storage.WorldMapIcons[Row][1] = Checkbox.isSelected());

		Panel.add(CheckboxLabel, BorderLayout.WEST);
		Panel.add(Checkbox, BorderLayout.EAST);
		return Panel;
	}
	public NXTWorldMapSelectionGUI() {
		setTitle("World Map Icons");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 250, 600);
		setResizable(false);
		setAlwaysOnTop(true);
		setLayout(new BorderLayout());
		
		Content = new JLabel("<html>");
		Content.setBorder(null);
		Content.setVerticalAlignment(SwingConstants.TOP);
		JPanel ScrollPanels = new JPanel();
		ScrollPanels.setLayout(new GridLayout(Storage.WorldMapIcons.length,1));
		for (int i = 0; i < Storage.WorldMapIcons.length; i++){
			ScrollPanels.add(addRow(i, (String) Storage.WorldMapIcons[i][2]));	
		}
		scrollpane = new JScrollPane(ScrollPanels);
		getContentPane().add(scrollpane, BorderLayout.CENTER);
	}
}
