package com.kittleapps.desktop.app.nxtsettings;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

public class NXTSettingsGUI extends JFrame {
	private static final long serialVersionUID = -4228920598014351419L;
	public static JCheckBox
		FlickeringEffectsCheckbox,
		ShadowsCheckbox,
		CustomCursorsCheckbox,
		LoadingScreensCheckbox,
		GroundDecorationsCheckbox,
		TerrainBlendingCheckbox,
		HeatHazeCheckbox,
		RememberUsernameCheckbox,
		RandomizeLoginWallpaperCheckbox,
		GlobalAudioMuteCheckbox,
		InGameSoundEffectsBoostCheckbox,
		InGameAmbientSoundEffectsBoostCheckbox,
		InGameVoiceOverBoostCheckbox,
		CompatibilityModeCheckBox,
		CompatibilityModeOnErrorCheckBox,
		AskBeforeQuittingCheckBox,
		ShowSensitiveInformation,
		AllowWritingCheckbox,
		GraphicsPresets;

	public static JComboBox<?>
		RemoveRoofsComboBox,
		DrawDistanceComboBox,
		ShadowQualityComboBox,
		VSyncComboBox,
		AntiAliasingModeComboBox,
		AntiAliasingQualityComboBox,
		WaterQualityComboBox,
		LightingDetailComboBox,
		AmbientOcclusionComboBox,
		BloomQualityComboBox,
		DepthOfFieldComboBox,
		TextureQualityComboBox,
		AnisotropicFilteringComboBox,
		VolumetricLightingComboBox,
		MaxForegroundFpsComboBox,
		MaxBackgroundFpsComboBox,
		UIScalingComboBox,
		GameWorldScalingComboBox,
		LanguageSelectionComboBox;

	public static JSlider
		BrightnessSlider,
		LoginMusicSlider,
		InGameMusicSlider,
		InGameSoundEffectsSlider,
		InGameAmbientSoundEffectsSlider,
		InGameVoiceOverSlider;

	public static JTextField
		UsernameInput,
		FavouriteWorld1Input,
		FavouriteWorld2Input,
		FavouriteWorld3Input,
		WallpaperIDInput;

	public static JButton

		MinimumGraphicsPresetButton,
		LowGraphicsPresetButton,
		MediumGraphicsPresetButton,
		HighGraphicsPresetButton,
		UltraGraphicsPresetButton,
		MaxedGraphicsPresetButton,
		WikianGraphicsPresetButton,
		RedditGraphicsPresetButton,
		AddSpriteFlagToUsername,
		AddColourFlagToUsername,
		BecomeZezima,
		ClearConsole,
		PlayerConsole,
		JagexConsole,
		ReadSettings,
		WriteSettings;

	private static Color backgroundColour = new Color(45, 45, 45), optionBackgroundColor = new Color (40, 40, 40);

	public static JFrame frame;
	public static JTable DeveloperConsoleHistoryTable;
	public static void main(final String[] args) {
		if (!Runtime.class.getPackage().getImplementationVersion().startsWith("1.8.") && !Runtime.class.getPackage().getImplementationVersion().startsWith("1.7.")){
			System.out.println("JRE was not Oracle's JRE 7/8 (1.7/1.8); Abort functioning until internal bugs are fixed.");
			JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "<html>This program is tested and stable on Oracle JRE 8 (JRE 1.8)"+
																"<br><br>"+
																"Please use that version for the time being until I fix some issues on JRE 9 (JRE 1.9)<br>"+
																"This version currently isn't compatible with OpenJRE variants."+
																"<br><br>"+
																"Thank you. -Sudo Bash =)");
			System.exit(0);
		}
		EventQueue.invokeLater(() -> {
			try {
				//Initialize values, and load the program.
				Storage.init();
				frame = new NXTSettingsGUI();
			}	catch(final Exception e) {
				e.printStackTrace();
			}
		});
	}

	private static JPanel contentPane;

	public NXTSettingsGUI() {
		frame = this;
		frame.setVisible(true);
		frame.setAlwaysOnTop(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 755, 675);
		contentPane = new JPanel();
		contentPane.setBackground(backgroundColour);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setResizable(false);
		frame.setTitle("NXT Settings");

		try	{
			// Apply dark theme.
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		}	catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		final JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 755, 25);
		contentPane.add(menuBar);

		final JMenu FileMenu = new JMenu("File");
		menuBar.add(FileMenu);

		final JMenuItem FileMenuSelectCache = new JMenuItem("Manually select cache");
		FileMenuSelectCache.addActionListener(e -> {
			final FileNameExtensionFilter filter = new FileNameExtensionFilter("NXT Cache Files", "jcache");
			final JFileChooser fileChooser = new JFileChooser();
			if (Storage.OS_TYPE == 0) {
				fileChooser.setCurrentDirectory(new File(System.getenv("LOCALAPPDATA") +
														 System.getProperty("file.separator") + "Jagex" +
														 System.getProperty("file.separator") + "RuneScape" +
														 System.getProperty("file.separator")
														 ));
			}	else if (Storage.OS_TYPE == 1) {
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") +
														 System.getProperty("file.separator") + "Jagex" +
														 System.getProperty("file.separator") + "RuneScape" +
														 System.getProperty("file.separator")
														 ));
			}
			fileChooser.setDialogTitle("Locate 'Settings.jcache'");
			fileChooser.setApproveButtonText("Load");
			fileChooser.setSelectedFile(new File("Settings.jcache"));
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setFileFilter(filter);
			final int returnVal = fileChooser.showOpenDialog(NXTSettingsGUI.frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				final File file = fileChooser.getSelectedFile();
				if (file.getName().equalsIgnoreCase("Settings.jcache")) {
					Storage.Cache_settings_location = file.getAbsolutePath();
				}	else {
					JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "The File at:" +
																		"\n\n" +
																		file.getAbsolutePath() +
																		" was not the Cache file this program is looking for." +
																		" Please select 'Settings.jcache'");
				}
			}
		});
		FileMenu.add(FileMenuSelectCache);

		final JMenuItem FileMenuSelectPreferences = new JMenuItem("Manually select preferences");
		FileMenuSelectPreferences.addActionListener(e -> {
			final FileNameExtensionFilter filter = new FileNameExtensionFilter("NXT Preference Files", "cfg");
			final JFileChooser fileChooser = new JFileChooser();
			if (Storage.OS_TYPE == 0) {
				fileChooser.setCurrentDirectory(new File(System.getenv("ProgramData") +
														 System.getProperty("file.separator") + "Jagex" +
														 System.getProperty("file.separator") + "launcher" +
														 System.getProperty("file.separator")));
			}	else if (Storage.OS_TYPE == 1) {
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") +
														 System.getProperty("file.separator") + "Jagex" +
														 System.getProperty("file.separator") + "launcher" +
														 System.getProperty("file.separator")));
			}
			fileChooser.setDialogTitle("Locate 'preferences.cfg'");
			fileChooser.setApproveButtonText("Load");
			fileChooser.setSelectedFile(new File("preferences.cfg"));
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setFileFilter(filter);
			final int returnVal = fileChooser.showOpenDialog(NXTSettingsGUI.frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				final File file = fileChooser.getSelectedFile();
				if (file.getName().equalsIgnoreCase("preferences.cfg")) {
					Storage.preferences_config = file;
				}	else {
					JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "The File at:"+
																		"\n\n" +
																		file.getAbsolutePath() +
																		" was not the preference file this program is looking for."+
																		" Please select 'preferences.cfg'");
				}
			}	else {}
		});
		FileMenu.add(FileMenuSelectPreferences);

		final JMenuItem FileMenuAlwaysOnTop = new JMenuItem("Toggle Always On Top");
		FileMenuAlwaysOnTop.addActionListener(e -> {
			if (!Storage.DEVELOPER_WindowAlwaysOnTop){
				if (frame.isAlwaysOnTop()) {
					frame.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Always on top is now disabled.");
				}	else {
					frame.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Always on top is now enabled.");
				}
			}	else {
					frame.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "The program option 'DEVELOPER_ALWAYS_STAY_ON_TOP' is currently enabled.\n\nYou will not be able to toggle Always on Top at this time.");
			}
		});
		FileMenu.add(FileMenuAlwaysOnTop);

		final JMenuItem FileMenuExit = new JMenuItem("Exit");
		FileMenuExit.addActionListener(e -> System.exit(0));

		FileMenu.add(FileMenuExit);

		final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setBounds(15, 26, 720, 569);
		contentPane.add(tabbedPane);

		final NumberFormat intFormat = NumberFormat.getIntegerInstance();

		final NumberFormatter FpsNumberFormatter = new NumberFormatter(intFormat);
		FpsNumberFormatter.setValueClass(Integer.class);
		FpsNumberFormatter.setAllowsInvalid(false);

		final NumberFormatter InterfaceScalingNumberFormatter = new NumberFormatter(intFormat);
		InterfaceScalingNumberFormatter.setValueClass(Integer.class);
		InterfaceScalingNumberFormatter.setAllowsInvalid(false);

		final NumberFormatter GameScalingNumberFormatter = new NumberFormatter(intFormat);
		GameScalingNumberFormatter.setValueClass(Integer.class);
		GameScalingNumberFormatter.setAllowsInvalid(false);

		final NumberFormatter WorldNumberFormatter = new NumberFormatter(intFormat);
		WorldNumberFormatter.setValueClass(Integer.class);
		WorldNumberFormatter.setAllowsInvalid(false);
		WorldNumberFormatter.setMinimum(-1);
		WorldNumberFormatter.setMaximum(Integer.MAX_VALUE);

		final NumberFormatter WallpaperNumberFormatter = new NumberFormatter(intFormat);
		WallpaperNumberFormatter.setValueClass(Integer.class);
		WallpaperNumberFormatter.setAllowsInvalid(false);
		WallpaperNumberFormatter.setMinimum(0);





		/* Graphics Settings */



		final JPanel GraphicsSettingsTab = new JPanel();
		tabbedPane.addTab("Graphics Settings", null, GraphicsSettingsTab, null);
		GraphicsSettingsTab.setBackground(backgroundColour);
		GraphicsSettingsTab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GraphicsSettingsTab.setLayout(null);


		final JLabel BrightnessLabel = new JLabel("  Brightness");
		BrightnessLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		BrightnessLabel.setBounds(15, 15 + (30 * 0), 150, 25);
		GraphicsSettingsTab.add(BrightnessLabel);

		BrightnessSlider = new JSlider();
		BrightnessLabel.setLabelFor(BrightnessSlider);
		BrightnessSlider.addChangeListener(e -> Storage.nxtGraphicsSetting_Brightness = BrightnessSlider.getValue());
		BrightnessSlider.setSnapToTicks(true);
		BrightnessSlider.setMaximum(4);
		BrightnessSlider.setPaintTicks(true);
		BrightnessSlider.setBounds(165, 15 + (30 * 0), 175, 25);
		BrightnessSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		BrightnessSlider.setBackground(optionBackgroundColor);
		GraphicsSettingsTab.add(BrightnessSlider);

		final JLabel DrawDistanceLabel = new JLabel("  Draw distance");
		DrawDistanceLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		DrawDistanceLabel.setBounds(15, 15 + (30 * 1), 150, 25);
		GraphicsSettingsTab.add(DrawDistanceLabel);

		DrawDistanceComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[1]);
		DrawDistanceLabel.setLabelFor(DrawDistanceComboBox);
		DrawDistanceComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_DrawDistance = DrawDistanceComboBox.getSelectedIndex());
		DrawDistanceComboBox.setBounds(165, 15 + (30 * 1), 175, 25);
		GraphicsSettingsTab.add(DrawDistanceComboBox);

		final JLabel VSyncLabel = new JLabel("  VSync");
		VSyncLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		VSyncLabel.setBounds(15, 15 + (30 * 2), 150, 25);
		GraphicsSettingsTab.add(VSyncLabel);

		VSyncComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[3]);
		VSyncLabel.setLabelFor(VSyncComboBox);
		VSyncComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_VSync = VSyncComboBox.getSelectedIndex()-1);
		VSyncComboBox.setBounds(165, 15 + (30 * 2), 175, 25);
		GraphicsSettingsTab.add(VSyncComboBox);

		final JLabel AntiAliasingQualityLabel = new JLabel("  Anti-aliasing Quality");
		AntiAliasingQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AntiAliasingQualityLabel.setBounds(15, 15 + (30 * 3), 150, 25);
		GraphicsSettingsTab.add(AntiAliasingQualityLabel);

		AntiAliasingQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[5]);
		AntiAliasingQualityLabel.setLabelFor(AntiAliasingQualityComboBox);
		AntiAliasingQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_AntiAliasingQuality = AntiAliasingQualityComboBox.getSelectedIndex());
		AntiAliasingQualityComboBox.setEnabled(false);
		AntiAliasingQualityComboBox.setBounds(165, 15 + (30 * 3), 175, 25);
		GraphicsSettingsTab.add(AntiAliasingQualityComboBox);

		final JLabel LightingDetailLabel = new JLabel("  Lighting detail");
		LightingDetailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		LightingDetailLabel.setBounds(15, 15 + (30 * 4), 150, 25);
		GraphicsSettingsTab.add(LightingDetailLabel);

		LightingDetailComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[7]);
		LightingDetailLabel.setLabelFor(LightingDetailComboBox);
		LightingDetailComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_LightingQuality = LightingDetailComboBox.getSelectedIndex());
		LightingDetailComboBox.setBounds(165, 15 + (30 * 4), 175, 25);
		GraphicsSettingsTab.add(LightingDetailComboBox);

		final JLabel BloomQualityLabel = new JLabel("  Bloom");
		BloomQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		BloomQualityLabel.setBounds(15, 15 + (30 * 5), 150, 25);
		GraphicsSettingsTab.add(BloomQualityLabel);

		BloomQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[9]);
		BloomQualityLabel.setLabelFor(BloomQualityComboBox);
		BloomQualityComboBox.addItemListener(e -> {
			Storage.nxtGraphicsSetting_Bloom = BloomQualityComboBox.getSelectedIndex();
			Legality.CheckSettings();
		});
		BloomQualityComboBox.setBounds(165, 15 + (30 * 5), 175, 25);
		GraphicsSettingsTab.add(BloomQualityComboBox);

		final JLabel AnisotropicFilteringLabel = new JLabel("  Anisotropic Filtering");
		AnisotropicFilteringLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AnisotropicFilteringLabel.setBounds(15, 15 + (30 * 6), 150, 25);
		GraphicsSettingsTab.add(AnisotropicFilteringLabel);

		AnisotropicFilteringComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[12]);
		AnisotropicFilteringLabel.setLabelFor(AnisotropicFilteringComboBox);
		AnisotropicFilteringComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_AnisotropicFiltering = AnisotropicFilteringComboBox.getSelectedIndex());
		AnisotropicFilteringComboBox.setBounds(165, 15 + (30 * 6), 175, 25);
		GraphicsSettingsTab.add(AnisotropicFilteringComboBox);

		final JLabel MaxForegroundFpsLabel = new JLabel("  Foreground FPS");
		MaxForegroundFpsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		MaxForegroundFpsLabel.setToolTipText(Storage.MAXFOREGOUNDFPS_TOOLTIP);
		MaxForegroundFpsLabel.setBounds(15, 15 + (30 * 7), 150, 25);
		GraphicsSettingsTab.add(MaxForegroundFpsLabel);

		MaxForegroundFpsComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[14]);
		MaxForegroundFpsLabel.setLabelFor(MaxForegroundFpsComboBox);
		MaxForegroundFpsComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_MaxForegroundFps = ((MaxForegroundFpsComboBox.getSelectedIndex()+1)*5));
		MaxForegroundFpsComboBox.setBounds(165, 15 + (30 * 7), 175, 25);
		GraphicsSettingsTab.add(MaxForegroundFpsComboBox);

		final JLabel UIScalingLabel = new JLabel("  UI Scaling");
		UIScalingLabel.setToolTipText(Storage.INTERFACESCALING_TOOLTIP);
		UIScalingLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		UIScalingLabel.setBounds(15, 15 + (30 * 8), 150, 25);
		GraphicsSettingsTab.add(UIScalingLabel);

		UIScalingComboBox = new JComboBox<Object>(Storage.GRAPHICS_OPTIONS[15]);
		UIScalingComboBox.addItemListener(e -> Storage.nxtClientSettings_UIScaling = ((UIScalingComboBox.getSelectedIndex()*5)+100));
		UIScalingComboBox.setBounds(165, 255, 175, 25);
		GraphicsSettingsTab.add(UIScalingComboBox);

		FlickeringEffectsCheckbox = new JCheckBox(String.format("%-85s", "Flickering effects"));
		FlickeringEffectsCheckbox.setBackground(optionBackgroundColor);
		FlickeringEffectsCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		FlickeringEffectsCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_FlickeringEffects = FlickeringEffectsCheckbox.isSelected());
		FlickeringEffectsCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		FlickeringEffectsCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		FlickeringEffectsCheckbox.setBounds(15, 15 + (30 * 9), 325, 25);
		GraphicsSettingsTab.add(FlickeringEffectsCheckbox);

		CustomCursorsCheckbox = new JCheckBox(String.format("%-81s", "Custom Cursors"));
		CustomCursorsCheckbox.setBackground(optionBackgroundColor);
		CustomCursorsCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		CustomCursorsCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_CustomCursors = CustomCursorsCheckbox.isSelected());
		CustomCursorsCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		CustomCursorsCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		CustomCursorsCheckbox.setBounds(15, 15 + (30 * 10), 325, 25);
		CustomCursorsCheckbox.setSelected(true);
		GraphicsSettingsTab.add(CustomCursorsCheckbox);

		GroundDecorationsCheckbox = new JCheckBox(String.format("%-81s", "Ground decoration"));
		GroundDecorationsCheckbox.setBackground(optionBackgroundColor);
		GroundDecorationsCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		GroundDecorationsCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_GroundDecor = GroundDecorationsCheckbox.isSelected());
		GroundDecorationsCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		GroundDecorationsCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		GroundDecorationsCheckbox.setBounds(15, 15 + (30 * 11), 325, 25);
		GraphicsSettingsTab.add(GroundDecorationsCheckbox);



		// Right Column




		final JLabel RemoveRoofsLabel = new JLabel("  Remove roofs");
		RemoveRoofsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		RemoveRoofsLabel.setBounds(378 + (30 * 0), 15, 150, 25);
		GraphicsSettingsTab.add(RemoveRoofsLabel);

		RemoveRoofsComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[0]);
		RemoveRoofsLabel.setLabelFor(RemoveRoofsComboBox);
		RemoveRoofsComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_RemoveRoofs = RemoveRoofsComboBox.getSelectedIndex());
		RemoveRoofsComboBox.setBounds(528, 15 + (30 * 0), 175, 25);
		RemoveRoofsComboBox.setSelectedIndex(1);
		GraphicsSettingsTab.add(RemoveRoofsComboBox);

		final JLabel ShadowQualityLabel = new JLabel("  Shadow Quality");
		ShadowQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShadowQualityLabel.setBounds(378, 15 + (30 * 1), 150, 25);
		GraphicsSettingsTab.add(ShadowQualityLabel);

		ShadowQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[2]);
		ShadowQualityLabel.setLabelFor(ShadowQualityComboBox);
		ShadowQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_ShadowQuality = ShadowQualityComboBox.getSelectedIndex());
		ShadowQualityComboBox.setEnabled(false);
		ShadowQualityComboBox.setBounds(528, 15 + (30 * 1), 175, 25);
		GraphicsSettingsTab.add(ShadowQualityComboBox);

		final JLabel AntiAliasingModeLabel = new JLabel("  Anti-aliasing Mode");
		AntiAliasingModeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AntiAliasingModeLabel.setBounds(378, 15 + (30 * 2), 150, 25);
		GraphicsSettingsTab.add(AntiAliasingModeLabel);

		AntiAliasingModeComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[4]);
		AntiAliasingModeLabel.setLabelFor(AntiAliasingModeComboBox);
		AntiAliasingModeComboBox.addItemListener(e -> {
			Storage.nxtGraphicsSetting_AntiAliasingMode = AntiAliasingModeComboBox.getSelectedIndex();
			Legality.CheckSettings();
		});
		AntiAliasingModeComboBox.setBounds(528, 15 + (30 * 2), 175, 25);
		GraphicsSettingsTab.add(AntiAliasingModeComboBox);

		final JLabel WaterQualityLabel = new JLabel("  Water");
		WaterQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		WaterQualityLabel.setBounds(378, 15 + (30 * 3), 150, 25);
		GraphicsSettingsTab.add(WaterQualityLabel);

		WaterQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[6]);
		WaterQualityLabel.setLabelFor(WaterQualityComboBox);
		WaterQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_WaterQuality = WaterQualityComboBox.getSelectedIndex());
		WaterQualityComboBox.setBounds(528, 15 + (30 * 3), 175, 25);
		GraphicsSettingsTab.add(WaterQualityComboBox);

		final JLabel AmbientOcclusionLabel = new JLabel("  Ambient Occlusion");
		AmbientOcclusionLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AmbientOcclusionLabel.setToolTipText(Storage.AO_TOOLTIP);
		AmbientOcclusionLabel.setBounds(378, 15 + (30 * 4), 150, 25);
		GraphicsSettingsTab.add(AmbientOcclusionLabel);

		AmbientOcclusionComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[8]);
		AmbientOcclusionLabel.setLabelFor(AmbientOcclusionComboBox);
		AmbientOcclusionComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_AmbientOcclusion = AmbientOcclusionComboBox.getSelectedIndex());
		AmbientOcclusionComboBox.setBounds(528, 15 + (30 * 4), 175, 25);
		GraphicsSettingsTab.add(AmbientOcclusionComboBox);

		final JLabel TextureQualityLabel = new JLabel("  Textures");
		TextureQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		TextureQualityLabel.setBounds(378, 15 + (30 * 5), 150, 25);
		GraphicsSettingsTab.add(TextureQualityLabel);

		TextureQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[11]);
		TextureQualityLabel.setLabelFor(TextureQualityComboBox);
		TextureQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_TextureQuality = TextureQualityComboBox.getSelectedIndex());
		TextureQualityComboBox.setEnabled(false);
		TextureQualityComboBox.setBounds(528, 15 + (30 * 5), 175, 25);
		GraphicsSettingsTab.add(TextureQualityComboBox);

		final JLabel VolumetricLightingLabel = new JLabel("  Volumetric Lighting");
		VolumetricLightingLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		VolumetricLightingLabel.setBounds(378, 15 + (30 * 6), 150, 25);
		GraphicsSettingsTab.add(VolumetricLightingLabel);

		VolumetricLightingComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[13]);
		VolumetricLightingLabel.setLabelFor(VolumetricLightingComboBox);
		VolumetricLightingComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_VolumetricLighting = VolumetricLightingComboBox.getSelectedIndex());
		VolumetricLightingComboBox.setEnabled(false);
		VolumetricLightingComboBox.setBounds(528, 15 + (30 * 6), 175, 25);
		GraphicsSettingsTab.add(VolumetricLightingComboBox);


		final JLabel MaxBackgroundFpsLabel = new JLabel("  Background FPS");
		MaxBackgroundFpsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		MaxBackgroundFpsLabel.setToolTipText(Storage.MAXBACKGOUNDFPS_TOOLTIP);
		MaxBackgroundFpsLabel.setBounds(378, 15 + (30 * 7), 150, 25);
		GraphicsSettingsTab.add(MaxBackgroundFpsLabel);

		MaxBackgroundFpsComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[14]);
		MaxBackgroundFpsLabel.setLabelFor(MaxBackgroundFpsComboBox);
		MaxBackgroundFpsComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_MaxBackgroundFps = ((MaxBackgroundFpsComboBox.getSelectedIndex()+1)*5));
		MaxBackgroundFpsComboBox.setBounds(528, 15 + (30 * 7), 175, 25);
		GraphicsSettingsTab.add(MaxBackgroundFpsComboBox);

		final JLabel GameWorldScalingLabel = new JLabel("  Game World Scaling");
		GameWorldScalingLabel.setToolTipText(Storage.GAMESCALING_TOOLTIP);
		GameWorldScalingLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GameWorldScalingLabel.setBounds(378, 15 + (30 * 8), 150, 25);
		GraphicsSettingsTab.add(GameWorldScalingLabel);


		GameWorldScalingComboBox = new JComboBox<Object>(Storage.GRAPHICS_OPTIONS[16]);
		GameWorldScalingComboBox.addItemListener(e -> Storage.nxtClientSettings_GameWorldScaling = ((GameWorldScalingComboBox.getSelectedIndex()*5)+35));
		GameWorldScalingComboBox.setBounds(528, 255, 175, 25);
		GraphicsSettingsTab.add(GameWorldScalingComboBox);

		ShadowsCheckbox = new JCheckBox(String.format("%-88s", "Shadows"));
		ShadowsCheckbox.setBackground(optionBackgroundColor);
		ShadowsCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShadowsCheckbox.addActionListener(e -> {
			Storage.nxtGraphicsSetting_Shadows = ShadowsCheckbox.isSelected();
			Legality.CheckSettings();
		});
		ShadowsCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		ShadowsCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		ShadowsCheckbox.setBounds(378, 15 + (30 * 9), 325, 25);
		GraphicsSettingsTab.add(ShadowsCheckbox);

		LoadingScreensCheckbox = new JCheckBox(String.format("%-82s", "Loading screens"));
		LoadingScreensCheckbox.setBackground(optionBackgroundColor);
		LoadingScreensCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		LoadingScreensCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_LoadingScreens = LoadingScreensCheckbox.isSelected());
		LoadingScreensCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		LoadingScreensCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		LoadingScreensCheckbox.setBounds(378, 15 + (30 * 10), 325, 25);
		LoadingScreensCheckbox.setSelected(true);
		GraphicsSettingsTab.add(LoadingScreensCheckbox);

		TerrainBlendingCheckbox = new JCheckBox(String.format("%-84s", "Terrain blending"));
		TerrainBlendingCheckbox.setBackground(optionBackgroundColor);
		TerrainBlendingCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		TerrainBlendingCheckbox.addActionListener(e -> {
			Storage.nxtGraphicsSetting_TerrainBlending = TerrainBlendingCheckbox.isSelected();
			Legality.CheckSettings();
		});
		TerrainBlendingCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		TerrainBlendingCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		TerrainBlendingCheckbox.setBounds(378, 15 + (30 * 11), 325, 25);
		GraphicsSettingsTab.add(TerrainBlendingCheckbox);

		final JLabel GraphicsPresetLabel = new JLabel("Presets: ");
		GraphicsPresetLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GraphicsPresetLabel.setBounds(15, 505, 50, 25);
		GraphicsSettingsTab.add(GraphicsPresetLabel);

		MinimumGraphicsPresetButton = new JButton("Minimum");
		MinimumGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		MinimumGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		MinimumGraphicsPresetButton.addActionListener(e -> {
			Storage.nxtGraphicsSetting_DrawDistance			= 0;
			Storage.nxtGraphicsSetting_ShadowQuality		= 0;
			Storage.nxtGraphicsSetting_AntiAliasingMode		= 0;
			Storage.nxtGraphicsSetting_AntiAliasingQuality	= 0;
			Storage.nxtGraphicsSetting_WaterQuality			= 0;
			Storage.nxtGraphicsSetting_LightingQuality		= 0;
			Storage.nxtGraphicsSetting_AmbientOcclusion		= 0;
			Storage.nxtGraphicsSetting_Bloom				= 0;
			Storage.nxtGraphicsSetting_TextureQuality		= 0;
			Storage.nxtGraphicsSetting_AnisotropicFiltering	= 0;
			Storage.nxtGraphicsSetting_VolumetricLighting	= 0;
			Storage.nxtGraphicsSetting_Shadows				= false;
			Storage.nxtGraphicsSetting_GroundDecor			= false;
			Storage.nxtGraphicsSetting_TerrainBlending		= true;
			// Apply
			DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
		});
		MinimumGraphicsPresetButton.setEnabled(false);
		MinimumGraphicsPresetButton.setBounds(70, 505, 75, 25);
		GraphicsSettingsTab.add(MinimumGraphicsPresetButton);

		LowGraphicsPresetButton = new JButton("Low");
		LowGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		LowGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		LowGraphicsPresetButton.addActionListener(e -> {
			Storage.nxtGraphicsSetting_DrawDistance			= 0;
			Storage.nxtGraphicsSetting_ShadowQuality		= 0;
			Storage.nxtGraphicsSetting_AntiAliasingMode		= 0;
			Storage.nxtGraphicsSetting_AntiAliasingQuality	= 0;
			Storage.nxtGraphicsSetting_WaterQuality			= 0;
			Storage.nxtGraphicsSetting_LightingQuality		= 0;
			Storage.nxtGraphicsSetting_AmbientOcclusion		= 0;
			Storage.nxtGraphicsSetting_Bloom				= 0;
			Storage.nxtGraphicsSetting_TextureQuality		= 1;
			Storage.nxtGraphicsSetting_AnisotropicFiltering	= 0;
			Storage.nxtGraphicsSetting_VolumetricLighting	= 0;
			Storage.nxtGraphicsSetting_Shadows				= true;
			Storage.nxtGraphicsSetting_GroundDecor			= true;
			Storage.nxtGraphicsSetting_TerrainBlending		= true;
			// Apply
			DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
		});
		LowGraphicsPresetButton.setEnabled(false);
		LowGraphicsPresetButton.setBounds(150, 505, 75, 25);
		GraphicsSettingsTab.add(LowGraphicsPresetButton);

		MediumGraphicsPresetButton = new JButton("Medium");
		MediumGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		MediumGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		MediumGraphicsPresetButton.addActionListener(e -> {
			Storage.nxtGraphicsSetting_DrawDistance			= 1;
			Storage.nxtGraphicsSetting_ShadowQuality		= 1;
			Storage.nxtGraphicsSetting_AntiAliasingMode		= 1;
			Storage.nxtGraphicsSetting_AntiAliasingQuality	= 0;
			Storage.nxtGraphicsSetting_WaterQuality			= 1;
			Storage.nxtGraphicsSetting_LightingQuality		= 1;
			Storage.nxtGraphicsSetting_AmbientOcclusion		= 0;
			Storage.nxtGraphicsSetting_Bloom				= 1;
			Storage.nxtGraphicsSetting_TextureQuality		= 1;
			Storage.nxtGraphicsSetting_AnisotropicFiltering	= 1;
			Storage.nxtGraphicsSetting_VolumetricLighting	= 0;
			Storage.nxtGraphicsSetting_Shadows				= true;
			Storage.nxtGraphicsSetting_GroundDecor			= true;
			Storage.nxtGraphicsSetting_TerrainBlending		= true;
			// Apply
			DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
		});
		MediumGraphicsPresetButton.setEnabled(false);
		MediumGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		MediumGraphicsPresetButton.setBounds(230, 505, 75, 25);
		GraphicsSettingsTab.add(MediumGraphicsPresetButton);

		HighGraphicsPresetButton = new JButton("High");
		HighGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		HighGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		HighGraphicsPresetButton.addActionListener(e -> {
			Storage.nxtGraphicsSetting_DrawDistance			= 2;
			Storage.nxtGraphicsSetting_ShadowQuality		= 2;
			Storage.nxtGraphicsSetting_AntiAliasingMode		= 2;
			Storage.nxtGraphicsSetting_AntiAliasingQuality	= 1;
			Storage.nxtGraphicsSetting_WaterQuality			= 2;
			Storage.nxtGraphicsSetting_LightingQuality		= 2;
			Storage.nxtGraphicsSetting_AmbientOcclusion		= 0;
			Storage.nxtGraphicsSetting_Bloom				= 2;
			Storage.nxtGraphicsSetting_TextureQuality		= 1;
			Storage.nxtGraphicsSetting_AnisotropicFiltering	= 2;
			Storage.nxtGraphicsSetting_VolumetricLighting	= 0;
			Storage.nxtGraphicsSetting_Shadows				= true;
			Storage.nxtGraphicsSetting_GroundDecor			= true;
			Storage.nxtGraphicsSetting_TerrainBlending		= true;
			// Apply
			DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
		});
		HighGraphicsPresetButton.setEnabled(false);
		HighGraphicsPresetButton.setBounds(310, 505, 75, 25);
		GraphicsSettingsTab.add(HighGraphicsPresetButton);

		UltraGraphicsPresetButton = new JButton("Ultra");
		UltraGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		UltraGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		UltraGraphicsPresetButton.addActionListener(e -> {
			Storage.nxtGraphicsSetting_DrawDistance			= 3;
			Storage.nxtGraphicsSetting_ShadowQuality		= 3;
			Storage.nxtGraphicsSetting_AntiAliasingMode		= 2;
			Storage.nxtGraphicsSetting_AntiAliasingQuality	= 2;
			Storage.nxtGraphicsSetting_WaterQuality			= 3;
			Storage.nxtGraphicsSetting_LightingQuality		= 3;
			Storage.nxtGraphicsSetting_AmbientOcclusion		= 2;
			Storage.nxtGraphicsSetting_Bloom				= 3;
			Storage.nxtGraphicsSetting_TextureQuality		= 1;
			Storage.nxtGraphicsSetting_AnisotropicFiltering = 3;
			Storage.nxtGraphicsSetting_VolumetricLighting	= 3;
			Storage.nxtGraphicsSetting_Shadows				= true;
			Storage.nxtGraphicsSetting_GroundDecor			= true;
			Storage.nxtGraphicsSetting_TerrainBlending		= true;
			// Apply
			DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
		});
		UltraGraphicsPresetButton.setEnabled(false);
		UltraGraphicsPresetButton.setBounds(390, 505, 75, 25);
		GraphicsSettingsTab.add(UltraGraphicsPresetButton);

		MaxedGraphicsPresetButton = new JButton("\"Maxed\"");
		MaxedGraphicsPresetButton.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		MaxedGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		MaxedGraphicsPresetButton.addActionListener(e -> {
			Storage.nxtGraphicsSetting_RemoveRoofs			= 0;
			Storage.nxtGraphicsSetting_DrawDistance 		= 3;
			Storage.nxtGraphicsSetting_ShadowQuality 		= 3;
			Storage.nxtGraphicsSetting_AntiAliasingMode		= 3;
			Storage.nxtGraphicsSetting_AntiAliasingQuality	= 3;
			Storage.nxtGraphicsSetting_WaterQuality			= 3;
			Storage.nxtGraphicsSetting_LightingQuality		= 3;
			Storage.nxtGraphicsSetting_AmbientOcclusion		= 2;
			Storage.nxtGraphicsSetting_Bloom				= 3;
			Storage.nxtGraphicsSetting_TextureQuality		= 2;
			Storage.nxtGraphicsSetting_AnisotropicFiltering	= 4;
			Storage.nxtGraphicsSetting_VolumetricLighting	= 4;
			Storage.nxtGraphicsSetting_Shadows				= true;
			Storage.nxtGraphicsSetting_GroundDecor			= true;
			Storage.nxtGraphicsSetting_TerrainBlending		= true;
			Storage.nxtGraphicsSetting_FlickeringEffects	= true;
			Storage.nxtGraphicsSetting_CustomCursors		= true;
			Storage.nxtGraphicsSetting_MaxForegroundFps 	= 300;
			Storage.nxtGraphicsSetting_MaxBackgroundFps 	= 300;
			Storage.nxtClientSettings_GameWorldScaling  	= 200;
			// Apply
			RemoveRoofsComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_RemoveRoofs);
			DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
			FlickeringEffectsCheckbox.setSelected(Storage.nxtGraphicsSetting_FlickeringEffects);
			CustomCursorsCheckbox.setSelected(Storage.nxtGraphicsSetting_CustomCursors);
			MaxForegroundFpsComboBox.setSelectedIndex((Storage.nxtGraphicsSetting_MaxForegroundFps/5)-1);
			MaxBackgroundFpsComboBox.setSelectedIndex((Storage.nxtGraphicsSetting_MaxBackgroundFps/5)-1);
			GameWorldScalingComboBox.setSelectedIndex((Storage.nxtClientSettings_GameWorldScaling-35)/5);
		});
		MaxedGraphicsPresetButton.setEnabled(false);
		MaxedGraphicsPresetButton.setBounds(470, 505, 75, 25);
		GraphicsSettingsTab.add(MaxedGraphicsPresetButton);

		WikianGraphicsPresetButton = new JButton("Wikian");
		WikianGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		WikianGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		WikianGraphicsPresetButton.addActionListener(e -> {
			Storage.nxtGraphicsSetting_DrawDistance 		= 3;
			Storage.nxtGraphicsSetting_ShadowQuality 		= 3;
			Storage.nxtGraphicsSetting_AntiAliasingMode		= 2;
			Storage.nxtGraphicsSetting_AntiAliasingQuality	= 3;
			Storage.nxtGraphicsSetting_WaterQuality			= 3;
			Storage.nxtGraphicsSetting_LightingQuality		= 3;
			Storage.nxtGraphicsSetting_AmbientOcclusion		= 2;
			Storage.nxtGraphicsSetting_Bloom				= 3;
			Storage.nxtGraphicsSetting_TextureQuality		= 1;
			Storage.nxtGraphicsSetting_AnisotropicFiltering	= 4;
			Storage.nxtGraphicsSetting_VolumetricLighting	= 4;
			Storage.nxtGraphicsSetting_Shadows				= true;
			Storage.nxtGraphicsSetting_GroundDecor			= true;
			Storage.nxtGraphicsSetting_TerrainBlending		= true;
			Storage.nxtGraphicsSetting_FlickeringEffects	= true;
			Storage.nxtGraphicsSetting_CustomCursors		= true;
			Storage.nxtClientSettings_UIScaling				= 100;
			Storage.nxtClientSettings_GameWorldScaling  	= 200;
			// Apply
			DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
			FlickeringEffectsCheckbox.setSelected(Storage.nxtGraphicsSetting_FlickeringEffects);
			CustomCursorsCheckbox.setSelected(Storage.nxtGraphicsSetting_CustomCursors);
			UIScalingComboBox.setSelectedIndex((Storage.nxtClientSettings_UIScaling-100)/5);
			GameWorldScalingComboBox.setSelectedIndex((Storage.nxtClientSettings_GameWorldScaling-35)/5);
		});
		WikianGraphicsPresetButton.setEnabled(false);
		WikianGraphicsPresetButton.setBounds(550, 505, 75, 25);
		GraphicsSettingsTab.add(WikianGraphicsPresetButton);

		RedditGraphicsPresetButton = new JButton("Reddit");
		RedditGraphicsPresetButton.setFont(new Font("Dialog", Font.ITALIC, 10));
		RedditGraphicsPresetButton.setVerticalAlignment(SwingConstants.TOP);
		RedditGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		RedditGraphicsPresetButton.addActionListener(e -> {
			Storage.nxtGraphicsSetting_Brightness			= 0;
			Storage.nxtGraphicsSetting_RemoveRoofs			= 1;
			Storage.nxtGraphicsSetting_DrawDistance			= 0;
			Storage.nxtGraphicsSetting_ShadowQuality		= 0;
			Storage.nxtGraphicsSetting_AntiAliasingMode		= 0;
			Storage.nxtGraphicsSetting_AntiAliasingQuality	= 0;
			Storage.nxtGraphicsSetting_WaterQuality			= 0;
			Storage.nxtGraphicsSetting_LightingQuality		= 0;
			Storage.nxtGraphicsSetting_AmbientOcclusion		= 0;
			Storage.nxtGraphicsSetting_Bloom				= 0;
			Storage.nxtGraphicsSetting_TextureQuality		= 0;
			Storage.nxtGraphicsSetting_AnisotropicFiltering	= 0;
			Storage.nxtGraphicsSetting_VolumetricLighting	= 0;
			Storage.nxtGraphicsSetting_Shadows				= false;
			Storage.nxtGraphicsSetting_GroundDecor			= false;
			Storage.nxtGraphicsSetting_TerrainBlending		= false;
			Storage.nxtGraphicsSetting_MaxForegroundFps 	= 15;
			Storage.nxtGraphicsSetting_MaxBackgroundFps 	= 5;
			Storage.nxtClientSettings_GameWorldScaling  	= 35;
			// Apply
			BrightnessSlider.setValue(Storage.nxtGraphicsSetting_Brightness);
			RemoveRoofsComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_RemoveRoofs);
			DrawDistanceComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_DrawDistance);
			ShadowQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_ShadowQuality);
			AntiAliasingModeComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingMode);
			AntiAliasingQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AntiAliasingQuality);
			WaterQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_WaterQuality);
			LightingDetailComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_LightingQuality);
			AmbientOcclusionComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AmbientOcclusion);
			BloomQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_Bloom);
			TextureQualityComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_TextureQuality);
			AnisotropicFilteringComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_AnisotropicFiltering);
			VolumetricLightingComboBox.setSelectedIndex(Storage.nxtGraphicsSetting_VolumetricLighting);
			ShadowsCheckbox.setSelected(Storage.nxtGraphicsSetting_Shadows);
			GroundDecorationsCheckbox.setSelected(Storage.nxtGraphicsSetting_GroundDecor);
			TerrainBlendingCheckbox.setSelected(Storage.nxtGraphicsSetting_TerrainBlending);
			FlickeringEffectsCheckbox.setSelected(Storage.nxtGraphicsSetting_FlickeringEffects);
			CustomCursorsCheckbox.setSelected(Storage.nxtGraphicsSetting_CustomCursors);
			MaxForegroundFpsComboBox.setSelectedIndex((Storage.nxtGraphicsSetting_MaxForegroundFps/5)-1);
			MaxBackgroundFpsComboBox.setSelectedIndex((Storage.nxtGraphicsSetting_MaxBackgroundFps/5)-1);
			GameWorldScalingComboBox.setSelectedIndex((Storage.nxtClientSettings_GameWorldScaling-35)/5);
		});
		RedditGraphicsPresetButton.setEnabled(false);
		RedditGraphicsPresetButton.setBounds(630, 505, 75, 25);
		GraphicsSettingsTab.add(RedditGraphicsPresetButton);





		/* Client Settings */






		final JPanel ClientSettingsTab = new JPanel();
		tabbedPane.addTab("Client Settings", null, ClientSettingsTab, null);
		ClientSettingsTab.setBackground(backgroundColour);
		ClientSettingsTab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ClientSettingsTab.setLayout(null);

		final JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		UsernameLabel.setBounds(15, 15, 150, 25);
		ClientSettingsTab.add(UsernameLabel);

		UsernameInput = new JTextField();
		UsernameLabel.setLabelFor(UsernameInput);
		UsernameInput.setToolTipText(Storage.USERNAME_INPUT_TOOLTIP);
		UsernameInput.setBounds(170, 15, 535, 25);
		ClientSettingsTab.add(UsernameInput);
		UsernameInput.setColumns(10);

		final JLabel FavouriteWorld1Label = new JLabel("Favourite world 1");
		FavouriteWorld1Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		FavouriteWorld1Label.setBounds(15, 45, 150, 25);
		ClientSettingsTab.add(FavouriteWorld1Label);

		FavouriteWorld1Input = new JFormattedTextField(WorldNumberFormatter);
		FavouriteWorld1Label.setLabelFor(FavouriteWorld1Input);
		FavouriteWorld1Input.setText("1");
		FavouriteWorld1Input.setToolTipText(Storage.FAVOURITE_WORLD_INPUT_TOOLTIP);
		FavouriteWorld1Input.setBounds(170, 45, 85, 25);
		ClientSettingsTab.add(FavouriteWorld1Input);

		final JLabel FavouriteWorld2Label = new JLabel("Favourite world 2");
		FavouriteWorld2Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		FavouriteWorld2Label.setBounds(260, 45, 130, 25);
		ClientSettingsTab.add(FavouriteWorld2Label);

		FavouriteWorld2Input = new JFormattedTextField(WorldNumberFormatter);
		FavouriteWorld2Label.setLabelFor(FavouriteWorld2Input);
		FavouriteWorld2Input.setText("2");
		FavouriteWorld2Input.setToolTipText(Storage.FAVOURITE_WORLD_INPUT_TOOLTIP);
		FavouriteWorld2Input.setBounds(395, 45, 85, 25);
		ClientSettingsTab.add(FavouriteWorld2Input);

		final JLabel FavouriteWorld3Label = new JLabel("Favourite world 3");
		FavouriteWorld3Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		FavouriteWorld3Label.setBounds(485, 45, 130, 25);
		ClientSettingsTab.add(FavouriteWorld3Label);

		FavouriteWorld3Input = new JFormattedTextField(WorldNumberFormatter);
		FavouriteWorld3Label.setLabelFor(FavouriteWorld3Input);
		FavouriteWorld3Input.setText("3");
		FavouriteWorld3Input.setToolTipText(Storage.FAVOURITE_WORLD_INPUT_TOOLTIP);
		FavouriteWorld3Input.setBounds(620, 45, 85, 25);
		ClientSettingsTab.add(FavouriteWorld3Input);

		GlobalAudioMuteCheckbox = new JCheckBox("Global Audio Mute?");
		GlobalAudioMuteCheckbox.addActionListener(e -> Storage.nxtClientSettings_GlobalMute = GlobalAudioMuteCheckbox.isSelected());
		GlobalAudioMuteCheckbox.setToolTipText(Storage.GLOBAL_AUDIO_MUTE_TOOLTIP);
		GlobalAudioMuteCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		GlobalAudioMuteCheckbox.setBounds(15, 75, 150, 25);
		GlobalAudioMuteCheckbox.setBackground(optionBackgroundColor);
		ClientSettingsTab.add(GlobalAudioMuteCheckbox);

		RememberUsernameCheckbox = new JCheckBox("Remember Saved Username?");
		RememberUsernameCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		RememberUsernameCheckbox.addActionListener(e -> Storage.nxtClientSettings_RememberUsername = RememberUsernameCheckbox.isSelected());
		RememberUsernameCheckbox.setToolTipText(Storage.REMEMBER_USERNAME_TOOLTIP);
		RememberUsernameCheckbox.setBounds(170, 75, 220, 25);
		RememberUsernameCheckbox.setBackground(optionBackgroundColor);
		ClientSettingsTab.add(RememberUsernameCheckbox);

		RandomizeLoginWallpaperCheckbox = new JCheckBox("Randomize Wallpaper? Use ID:");
		RandomizeLoginWallpaperCheckbox.addActionListener(e -> Storage.nxtClientSettings_RandomizeLoginWallpaper = RandomizeLoginWallpaperCheckbox.isSelected());
		RandomizeLoginWallpaperCheckbox.setToolTipText(Storage.RANDOMIZE_LOGIN_WALLPAPER_TOOLTIP);
		RandomizeLoginWallpaperCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		RandomizeLoginWallpaperCheckbox.setBounds(395, 75, 220, 25);
		RandomizeLoginWallpaperCheckbox.setBackground(optionBackgroundColor);
		ClientSettingsTab.add(RandomizeLoginWallpaperCheckbox);

		WallpaperIDInput = new JFormattedTextField(WallpaperNumberFormatter);
		WallpaperIDInput.setText("0");
		WallpaperIDInput.setToolTipText(Storage.WALLPAPER_ID_TOOLTIP);
		WallpaperIDInput.setBounds(620, 75, 85, 25);
		FavouriteWorld2Input.setDocument(new TextLimitor(2));
		ClientSettingsTab.add(WallpaperIDInput);

		LoginMusicSlider = new JSlider();
		LoginMusicSlider.addChangeListener(e -> Storage.nxtClientSettings_LoginMusicVolume = LoginMusicSlider.getValue());

		final JLabel LoginMusicLabel = new JLabel("Login Music Volume");
		LoginMusicLabel.setLabelFor(LoginMusicSlider);
		LoginMusicLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		LoginMusicLabel.setBounds(15, 105, 150, 25);
		ClientSettingsTab.add(LoginMusicLabel);

		LoginMusicSlider.setMaximum(255);
		LoginMusicSlider.setPaintTicks(false);
		LoginMusicSlider.setBounds(170, 105, 535, 25);
		LoginMusicSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(LoginMusicSlider);

		final JLabel InGameMusicLabel = new JLabel("Game Music Volume");
		InGameMusicLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameMusicLabel.setBounds(15, 135, 150, 25);
		ClientSettingsTab.add(InGameMusicLabel);

		InGameMusicSlider = new JSlider();
		InGameMusicLabel.setLabelFor(InGameMusicSlider);
		InGameMusicSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameMusicVolume = InGameMusicSlider.getValue());
		InGameMusicSlider.setMaximum(255);
		InGameMusicSlider.setPaintTicks(false);
		InGameMusicSlider.setBounds(170, 135, 535, 25);
		InGameMusicSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(InGameMusicSlider);

		final JLabel InGameSoundEffectsLabel = new JLabel("Sound Effect Volume");
		InGameSoundEffectsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameSoundEffectsLabel.setBounds(15, 165, 150, 25);
		ClientSettingsTab.add(InGameSoundEffectsLabel);

		InGameSoundEffectsSlider = new JSlider();
		InGameSoundEffectsLabel.setLabelFor(InGameSoundEffectsSlider);
		InGameSoundEffectsSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameSoundEffectsVolume = InGameSoundEffectsSlider.getValue());
		InGameSoundEffectsSlider.setMaximum(127);
		InGameSoundEffectsSlider.setPaintTicks(false);
		InGameSoundEffectsSlider.setBounds(170, 165, 445, 25);
		InGameSoundEffectsSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(InGameSoundEffectsSlider);

		InGameSoundEffectsBoostCheckbox = new JCheckBox("Boost?");
		InGameSoundEffectsBoostCheckbox.setToolTipText(Storage.BOOSTED_VOLUMES_TOOLTIP);
		InGameSoundEffectsBoostCheckbox.addActionListener(e -> {
			if (InGameSoundEffectsBoostCheckbox.isSelected()) {
				InGameSoundEffectsSlider.setMaximum(255);
			}	else {
				InGameSoundEffectsSlider.setMaximum(127);
			}
		});
		InGameSoundEffectsBoostCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameSoundEffectsBoostCheckbox.setBounds(620, 165, 85, 25);
		InGameSoundEffectsBoostCheckbox.setBackground(optionBackgroundColor);
		ClientSettingsTab.add(InGameSoundEffectsBoostCheckbox);

		final JLabel InGameAmbientSoundEffectsLabel = new JLabel("Ambient Sound Volume");
		InGameAmbientSoundEffectsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameAmbientSoundEffectsLabel.setBounds(15, 195, 150, 25);
		ClientSettingsTab.add(InGameAmbientSoundEffectsLabel);

		InGameAmbientSoundEffectsSlider = new JSlider();
		InGameAmbientSoundEffectsLabel.setLabelFor(InGameAmbientSoundEffectsSlider);
		InGameAmbientSoundEffectsSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume = InGameAmbientSoundEffectsSlider.getValue());
		InGameAmbientSoundEffectsSlider.setMaximum(127);
		InGameAmbientSoundEffectsSlider.setPaintTicks(false);
		InGameAmbientSoundEffectsSlider.setBounds(170, 195, 445, 25);
		InGameAmbientSoundEffectsSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(InGameAmbientSoundEffectsSlider);

		InGameAmbientSoundEffectsBoostCheckbox = new JCheckBox("Boost?");
		InGameAmbientSoundEffectsBoostCheckbox.setToolTipText(Storage.BOOSTED_VOLUMES_TOOLTIP);
		InGameAmbientSoundEffectsBoostCheckbox.addActionListener(e -> {
			if (InGameAmbientSoundEffectsBoostCheckbox.isSelected()) {
				InGameAmbientSoundEffectsSlider.setMaximum(255);
			}	else {
				InGameAmbientSoundEffectsSlider.setMaximum(127);
			}
		});
		InGameAmbientSoundEffectsBoostCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameAmbientSoundEffectsBoostCheckbox.setBounds(620, 195, 85, 25);
		InGameAmbientSoundEffectsBoostCheckbox.setBackground(optionBackgroundColor);
		ClientSettingsTab.add(InGameAmbientSoundEffectsBoostCheckbox);

		final JLabel InGameVoiceOverLabel = new JLabel("Voice Over Volume");
		InGameVoiceOverLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameVoiceOverLabel.setBounds(15, 225, 150, 25);
		ClientSettingsTab.add(InGameVoiceOverLabel);

		InGameVoiceOverSlider = new JSlider();
		InGameVoiceOverLabel.setLabelFor(InGameVoiceOverSlider);
		InGameVoiceOverSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameVoiceOverVolume = InGameVoiceOverSlider.getValue());
		InGameVoiceOverSlider.setMaximum(127);
		InGameVoiceOverSlider.setPaintTicks(false);
		InGameVoiceOverSlider.setBounds(170, 225, 445, 25);
		InGameVoiceOverSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(InGameVoiceOverSlider);

		InGameVoiceOverBoostCheckbox = new JCheckBox("Boost?");
		InGameVoiceOverBoostCheckbox.setToolTipText(Storage.BOOSTED_VOLUMES_TOOLTIP);
		InGameVoiceOverBoostCheckbox.addActionListener(e -> {
			if (InGameVoiceOverBoostCheckbox.isSelected()) {
				InGameVoiceOverSlider.setMaximum(255);
			}	else {
				InGameVoiceOverSlider.setMaximum(127);
			}
		});
		InGameVoiceOverBoostCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameVoiceOverBoostCheckbox.setBounds(620, 225, 85, 25);
		InGameVoiceOverBoostCheckbox.setBackground(optionBackgroundColor);
		ClientSettingsTab.add(InGameVoiceOverBoostCheckbox);

		CompatibilityModeCheckBox = new JCheckBox("Compatibility Mode");
		CompatibilityModeCheckBox.addActionListener(e -> Storage.nxtClientSettings_CompatibilityMode = CompatibilityModeCheckBox.isSelected());
		CompatibilityModeCheckBox.setBackground(optionBackgroundColor);
		CompatibilityModeCheckBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		CompatibilityModeCheckBox.setBounds(15, 255, 150, 25);
		ClientSettingsTab.add(CompatibilityModeCheckBox);

		CompatibilityModeOnErrorCheckBox = new JCheckBox("Change to Compatibility on error?");
		CompatibilityModeOnErrorCheckBox.addActionListener(e -> Storage.nxtClientSettings_AskToSwitchToCompatibility = CompatibilityModeOnErrorCheckBox.isSelected());
		CompatibilityModeOnErrorCheckBox.setBackground(optionBackgroundColor);
		CompatibilityModeOnErrorCheckBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		CompatibilityModeOnErrorCheckBox.setBounds(170, 255, 220, 25);
		ClientSettingsTab.add(CompatibilityModeOnErrorCheckBox);

		AskBeforeQuittingCheckBox = new JCheckBox("Confirm quit on exit?");
		AskBeforeQuittingCheckBox.addActionListener(e -> Storage.nxtClientSettings_AskBeforeQuitting = AskBeforeQuittingCheckBox.isSelected());
		AskBeforeQuittingCheckBox.setBackground(optionBackgroundColor);
		AskBeforeQuittingCheckBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		AskBeforeQuittingCheckBox.setBounds(395, 255, 155, 25);
		ClientSettingsTab.add(AskBeforeQuittingCheckBox);

		final JLabel LanguageSelectionLabel = new JLabel("Language");
		LanguageSelectionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		LanguageSelectionLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		LanguageSelectionLabel.setBounds(555, 255, 59, 25);
		LanguageSelectionLabel.setLabelFor(LanguageSelectionComboBox);
		ClientSettingsTab.add(LanguageSelectionLabel);

		LanguageSelectionComboBox = new JComboBox < Object > (Storage.LANGUAGES);
		LanguageSelectionComboBox.addItemListener(e -> Storage.nxtClientSettings_LanguageSelected = LanguageSelectionComboBox.getSelectedIndex());
		LanguageSelectionComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		LanguageSelectionComboBox.setBounds(620, 255, 85, 25);
		ClientSettingsTab.add(LanguageSelectionComboBox);




		/* Special Mechanics */






		final JPanel SpecialMechanicsTab = new JPanel();
		tabbedPane.addTab("Special Mechanics", null, SpecialMechanicsTab, null);
		SpecialMechanicsTab.setBackground(backgroundColour);
		SpecialMechanicsTab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		SpecialMechanicsTab.setLayout(null);

		AddSpriteFlagToUsername = new JButton("Add <sprite=ID,SUB_ID> to your username");
		AddSpriteFlagToUsername.setToolTipText(Storage.ADD_SPRITE_FLAG_TOOLTIP);
		AddSpriteFlagToUsername.addActionListener(e -> {
			if (UsernameInput != null) {
				UsernameInput.setText(UsernameInput.getText() + "<sprite=SPRITE_ID,SPRITE_SUB_ID>");
			}
		});
		AddSpriteFlagToUsername.setFont(new Font("Dialog", Font.PLAIN, 11));
		AddSpriteFlagToUsername.setBounds(15, 15, 225, 25);
		SpecialMechanicsTab.add(AddSpriteFlagToUsername);

		AddColourFlagToUsername = new JButton("Add <col=RRGGBB> to your Username");
		AddColourFlagToUsername.setToolTipText(Storage.ADD_COLOUR_FLAG_TOOLTIP);
		AddColourFlagToUsername.addActionListener(e ->{
			if (UsernameInput != null) {
				UsernameInput.setText(UsernameInput.getText() + "<col=RRGGBB></col>");
			}
		});
		AddColourFlagToUsername.setFont(new Font("Dialog", Font.PLAIN, 11));
		AddColourFlagToUsername.setBounds(245, 15, 225, 25);
		SpecialMechanicsTab.add(AddColourFlagToUsername);

		BecomeZezima = new JButton("Become a God! Become Zezima.");
		BecomeZezima.setEnabled(false);
		BecomeZezima.setFont(new Font("Dialog", Font.PLAIN, 11));
		BecomeZezima.addActionListener(e -> {
			try {
				Storage.conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
				Storage.stmt = Storage.conn.createStatement();
				final String Zezima = "<sprite=203> " +
									  "<col=FF3030>Z" +
									  "<col=FF4D72>e" +
									  "<col=FF69B4>z" +
									  "<col=FF69B4>i" +
									  "<col=FF4D72>m" +
									  "<col=FF3030>a" +
									  " <sprite=203>";
				JCache.Write(false, Storage.CACHE_KEY_VT_VARC_SAVED_USERNAME, 	Zezima);
				JCache.Write(false, Storage.CACHE_KEY_VT_VARC_REMEMBER_USERNAME, 1);
				Storage.stmt.executeBatch();
				Storage.stmt.clearBatch();
				UsernameInput.setText(Zezima);
			}	catch(final SQLException e1) {
				e1.printStackTrace();
			}
		});
		BecomeZezima.setBounds(475, 15, 225, 25);
		SpecialMechanicsTab.add(BecomeZezima);

		ClearConsole = new JButton("Clear Developer Console History Log");
		ClearConsole.setToolTipText(Storage.CLEAR_DEV_CONSOLE_LOGS_TOOLTIP);
		ClearConsole.setFont(new Font("Dialog", Font.PLAIN, 11));
		ClearConsole.addActionListener(e -> {
			if (DeveloperConsoleHistoryTable.isShowing()){
				DeveloperConsoleHistoryTable.clearSelection();
			}

			for(int i = 0; i < DeveloperConsoleHistoryTable.getRowCount(); i++){
				DeveloperConsoleHistoryTable.setValueAt("", i, 1);
			}
		});
		ClearConsole.setBounds(15, 50, 225, 25);
		SpecialMechanicsTab.add(ClearConsole);

		PlayerConsole = new JButton("Player Developer Console History Log");
		PlayerConsole.setToolTipText(Storage.POPULATE_PLAYER_DEV_CONSOLE_LOGS_TOOLTIP);
		PlayerConsole.setFont(new Font("Dialog", Font.PLAIN, 11));
		PlayerConsole.addActionListener(e -> {
			if (DeveloperConsoleHistoryTable.isShowing()){
				DeveloperConsoleHistoryTable.clearSelection();
			}

			for(int i = 0; i < DeveloperConsoleHistoryTable.getRowCount(); i++){
				DeveloperConsoleHistoryTable.setValueAt("", i, 1);
			}

			for (int i = 0; i < Storage.DEVELOPER_CONSOLE_COMMANDS[0].length; i++){
				DeveloperConsoleHistoryTable.setValueAt(Storage.DEVELOPER_CONSOLE_COMMANDS[0][i], i, 1);
			}
		});
		PlayerConsole.setBounds(245, 50, 225, 25);
		SpecialMechanicsTab.add(PlayerConsole);

		JagexConsole = new JButton("Jagex Developer Console History Log");
		JagexConsole.setToolTipText(Storage.POPULATE_JAGEX_DEV_CONSOLE_LOGS_TOOLTIP);
		JagexConsole.setFont(new Font("Dialog", Font.PLAIN, 11));
		JagexConsole.addActionListener(e -> {
				if (DeveloperConsoleHistoryTable.isShowing()){
					DeveloperConsoleHistoryTable.clearSelection();
				}

				for(int i = 0; i < DeveloperConsoleHistoryTable.getRowCount(); i++){
					DeveloperConsoleHistoryTable.setValueAt("", i, 1);
				}

				for (int i = 0; i < Storage.DEVELOPER_CONSOLE_COMMANDS[1].length; i++){
					DeveloperConsoleHistoryTable.setValueAt(Storage.DEVELOPER_CONSOLE_COMMANDS[1][i], i, 1);
				}
		});
		JagexConsole.setBounds(475, 50, 225, 25);
		SpecialMechanicsTab.add(JagexConsole);

		DeveloperConsoleHistoryTable = new JTable();
		DeveloperConsoleHistoryTable.setFont(new Font("Dialog", Font.PLAIN, 12));
		DeveloperConsoleHistoryTable.setModel(new DefaultTableModel(
			new Object[150][2],
			new String[] {
				"ID",
				"Developer Console History"
			}
			){
			/**
				 *
				 */
				private static final long serialVersionUID = -4520056399939983074L;

			@Override
			public boolean isCellEditable(final int rowIndex, final int columnIndex) {
				if (columnIndex == 1){
					return true;
				}
			    return false;
			}
		});
		DeveloperConsoleHistoryTable.getColumnModel().getColumn(0).setMinWidth(30);
		DeveloperConsoleHistoryTable.getColumnModel().getColumn(0).setMaxWidth(30);
		DeveloperConsoleHistoryTable.getColumnModel().getColumn(1).setMinWidth(635);
		DeveloperConsoleHistoryTable.getColumnModel().getColumn(1).setMaxWidth(635);
		DeveloperConsoleHistoryTable.setRowHeight(20);
		DeveloperConsoleHistoryTable.setDragEnabled(false);
		DeveloperConsoleHistoryTable.getTableHeader().setToolTipText(
				"<html>" +
				"The ordering of these values are from newest to oldest.<br>" +
				"<br>" +
				"This means the first command listed will be the first commands<br>" +
				"shown when you hit Page-Up key while using the Developer Console.<br>" +
				"<br>" +
				"The last "+DeveloperConsoleHistoryTable.getRowCount()+" commands used will be shown in this table."
				);
		DeveloperConsoleHistoryTable.getTableHeader().setReorderingAllowed(false);
		DeveloperConsoleHistoryTable.getTableHeader().setResizingAllowed(false);
		DeveloperConsoleHistoryTable.setFillsViewportHeight(true);
		DeveloperConsoleHistoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		DeveloperConsoleHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final JScrollPane pane = new JScrollPane(DeveloperConsoleHistoryTable);
        pane.setBounds(15, 85, 685, 440);
		SpecialMechanicsTab.add(pane);
		for(int i = 0; i < DeveloperConsoleHistoryTable.getRowCount(); i++){
			DeveloperConsoleHistoryTable.setValueAt(i+1, i, 0);
		}




		/* Bottom Pane */





		ShowSensitiveInformation = new JCheckBox("Show sensitive information?");
		ShowSensitiveInformation.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShowSensitiveInformation.setToolTipText("Show information such as your username?");
		ShowSensitiveInformation.setBounds(15, 610, 185, 25);
		ShowSensitiveInformation.setBackground(optionBackgroundColor);
		contentPane.add(ShowSensitiveInformation);

		AllowWritingCheckbox = new JCheckBox("Enable 'Write Settings'?");
		AllowWritingCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		AllowWritingCheckbox.setToolTipText("Allow writing of all settings when 'Write Settings' is clicked. Some special mechanic values will be written instantly.");
		AllowWritingCheckbox.addActionListener(e -> {
			if (AllowWritingCheckbox.isSelected() && !Storage.DEVELOPER_ReadOnlyCache) {
				WriteSettings.setEnabled(true);
				BecomeZezima.setEnabled(true);
			}	else {
				WriteSettings.setEnabled(false);
				BecomeZezima.setEnabled(false);
			}
		});

		AllowWritingCheckbox.setBounds(205, 610, 160, 25);
		AllowWritingCheckbox.setBackground(optionBackgroundColor);
		contentPane.add(AllowWritingCheckbox);

		GraphicsPresets = new JCheckBox("Graphics presets?");
		GraphicsPresets.setFont(new Font("Dialog", Font.PLAIN, 12));
		GraphicsPresets.setToolTipText(Storage.GRAPHICS_PRESET_TOOLTIP);
		GraphicsPresets.addActionListener(e -> {
			if (GraphicsPresets.isSelected()) {
				MinimumGraphicsPresetButton.setEnabled(true);
				LowGraphicsPresetButton.setEnabled(true);
				MediumGraphicsPresetButton.setEnabled(true);
				HighGraphicsPresetButton.setEnabled(true);
				UltraGraphicsPresetButton.setEnabled(true);
				MaxedGraphicsPresetButton.setEnabled(true);
				WikianGraphicsPresetButton.setEnabled(true);
				RedditGraphicsPresetButton.setEnabled(true);
			}	else {
				MinimumGraphicsPresetButton.setEnabled(false);
				LowGraphicsPresetButton.setEnabled(false);
				MediumGraphicsPresetButton.setEnabled(false);
				HighGraphicsPresetButton.setEnabled(false);
				UltraGraphicsPresetButton.setEnabled(false);
				MaxedGraphicsPresetButton.setEnabled(false);
				WikianGraphicsPresetButton.setEnabled(false);
				RedditGraphicsPresetButton.setEnabled(false);
			}
		});
		GraphicsPresets.setBounds(370, 610, 132, 25);
		GraphicsPresets.setBackground(optionBackgroundColor);
		contentPane.add(GraphicsPresets);

		ReadSettings = new JButton("Read Settings");
		ReadSettings.setFont(new Font("Dialog", Font.PLAIN, 12));
		ReadSettings.setToolTipText("Read information currently saved in your setting file(s).");
		ReadSettings.addActionListener(e -> {
				if (DeveloperConsoleHistoryTable.isShowing()){
					DeveloperConsoleHistoryTable.clearSelection();
				}
				JCache.Read();
			}
		);
		ReadSettings.setBounds(510, 610, 110, 25);
		contentPane.add(ReadSettings);

		WriteSettings = new JButton("Write Settings");
		WriteSettings.setFont(new Font("Dialog", Font.PLAIN, 12));
		WriteSettings.addActionListener(e -> {
				if (DeveloperConsoleHistoryTable.isShowing()){
					DeveloperConsoleHistoryTable.clearSelection();
				}
				JCache.Write();
			}
		);
		WriteSettings.setBounds(625, 610, 110, 25);
		WriteSettings.setEnabled(false);
		contentPane.add(WriteSettings);

		JCache.Read();
		if (!History.History_Saved){
			History.init();
		}
	}
}