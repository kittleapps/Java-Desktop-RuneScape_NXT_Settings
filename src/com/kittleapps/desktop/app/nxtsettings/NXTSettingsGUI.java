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
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;

public class NXTSettingsGUI extends JFrame {
	/**
	 *
	 */
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
		AllowWritingCheckbox;

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
		LanguageSelectionComboBox;

	public static JSlider
		BrightnessSlider,
		LoginMusicSlider,
		InGameMusicSlider,
		InGameSoundEffectsSlider,
		InGameAmbientSoundEffectsSlider,
		InGameVoiceOverSlider;

	public static JTextField
		GameWorldScalingInput,
		UIScalingInput,
		UsernameInput,
		FavouriteWorld1Input,
		FavouriteWorld2Input,
		FavouriteWorld3Input,
		WallpaperIDInput;

	public static JButton
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
			} catch(final Exception e) {
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

		try {
			// Apply dark theme.
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
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
			} else if (Storage.OS_TYPE == 1) {
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
				} else {
					JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "The File at:" +
																		"\n\n" +
																		file.getAbsolutePath() +
																		" was not the Cache file this program is loking for." +
																		" Please select 'Settings.jcache'");
				}
			} else {}
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
			} else if (Storage.OS_TYPE == 1) {
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
				} else {
					JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "The File at:"+
																		"\n\n" +
																		file.getAbsolutePath() +
																		" was not the preference file this program is loking for."+
																		" Please select 'preferences.cfg'");
				}
			} else {}
		});
		FileMenu.add(FileMenuSelectPreferences);

		final JMenuItem FileMenuAlwaysOnTop = new JMenuItem("Toggle Always On Top");
		FileMenuAlwaysOnTop.addActionListener(e -> {
			if (frame.isAlwaysOnTop()){
				frame.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Always on top is now disabled.");
			}
			else{
				frame.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Always on top is now enabled.");
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
		tabbedPane.addTab("Graphics Settings", null, GraphicsSettingsTab, "Edit your graphics settings here.");
		GraphicsSettingsTab.setBackground(backgroundColour);
		GraphicsSettingsTab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GraphicsSettingsTab.setLayout(null);

		final JLabel RemoveRoofsLabel = new JLabel("  Remove roofs");
		RemoveRoofsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		RemoveRoofsLabel.setBounds(15 + (30 * 0), 15, 150, 25);
		GraphicsSettingsTab.add(RemoveRoofsLabel);

		RemoveRoofsComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[0]);
		RemoveRoofsLabel.setLabelFor(RemoveRoofsComboBox);
		RemoveRoofsComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_RemoveRoofs = RemoveRoofsComboBox.getSelectedIndex());
		RemoveRoofsComboBox.setToolTipText(Storage.REMOVEROOFS_TOOLTIP);
		RemoveRoofsComboBox.setBounds(165, 15 + (30 * 0), 175, 25);
		RemoveRoofsComboBox.setSelectedIndex(1);
		GraphicsSettingsTab.add(RemoveRoofsComboBox);

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

		UIScalingInput = new JFormattedTextField(InterfaceScalingNumberFormatter);
		UIScalingLabel.setLabelFor(UIScalingInput);
		UIScalingInput.setToolTipText(Storage.INTERFACESCALING_TOOLTIP);
		UIScalingInput.setText("100");
		UIScalingInput.setHorizontalAlignment(SwingConstants.RIGHT);
		UIScalingInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		UIScalingInput.setBounds(165, 15 + (30 * 8), 175, 25);
		GraphicsSettingsTab.add(UIScalingInput);

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



		final JLabel BrightnessLabel = new JLabel("  Brightness");
		BrightnessLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		BrightnessLabel.setBounds(355, 15 + (30 * 0), 150, 25);
		GraphicsSettingsTab.add(BrightnessLabel);

		BrightnessSlider = new JSlider();
		BrightnessLabel.setLabelFor(BrightnessSlider);
		BrightnessSlider.addChangeListener(e -> Storage.nxtGraphicsSetting_Brightness = BrightnessSlider.getValue());
		BrightnessSlider.setSnapToTicks(true);
		BrightnessSlider.setMaximum(4);
		BrightnessSlider.setPaintTicks(true);
		BrightnessSlider.setBounds(505, 15 + (30 * 0), 175, 25);
		BrightnessSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		BrightnessSlider.setBackground(optionBackgroundColor);
		GraphicsSettingsTab.add(BrightnessSlider);


		final JLabel ShadowQualityLabel = new JLabel("  Shadow Quality");
		ShadowQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShadowQualityLabel.setBounds(355, 15 + (30 * 1), 150, 25);
		GraphicsSettingsTab.add(ShadowQualityLabel);

		ShadowQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[2]);
		ShadowQualityLabel.setLabelFor(ShadowQualityComboBox);
		ShadowQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_ShadowQuality = ShadowQualityComboBox.getSelectedIndex());
		ShadowQualityComboBox.setEnabled(false);
		ShadowQualityComboBox.setBounds(505, 15 + (30 * 1), 175, 25);
		GraphicsSettingsTab.add(ShadowQualityComboBox);

		final JLabel AntiAliasingModeLabel = new JLabel("  Anti-aliasing Mode");
		AntiAliasingModeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AntiAliasingModeLabel.setBounds(355, 15 + (30 * 2), 150, 25);
		GraphicsSettingsTab.add(AntiAliasingModeLabel);

		AntiAliasingModeComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[4]);
		AntiAliasingModeLabel.setLabelFor(AntiAliasingModeComboBox);
		AntiAliasingModeComboBox.addItemListener(e -> {
			Storage.nxtGraphicsSetting_AntiAliasingMode = AntiAliasingModeComboBox.getSelectedIndex();
			Legality.CheckSettings();
		});
		AntiAliasingModeComboBox.setBounds(505, 15 + (30 * 2), 175, 25);
		GraphicsSettingsTab.add(AntiAliasingModeComboBox);

		final JLabel WaterQualityLabel = new JLabel("  Water");
		WaterQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		WaterQualityLabel.setBounds(355, 15 + (30 * 3), 150, 25);
		GraphicsSettingsTab.add(WaterQualityLabel);

		WaterQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[6]);
		WaterQualityLabel.setLabelFor(WaterQualityComboBox);
		WaterQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_WaterQuality = WaterQualityComboBox.getSelectedIndex());
		WaterQualityComboBox.setBounds(505, 15 + (30 * 3), 175, 25);
		GraphicsSettingsTab.add(WaterQualityComboBox);

		final JLabel AmbientOcclusionLabel = new JLabel("  Ambient Occlusion");
		AmbientOcclusionLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AmbientOcclusionLabel.setToolTipText(Storage.AO_TOOLTIP);
		AmbientOcclusionLabel.setBounds(355, 15 + (30 * 4), 150, 25);
		GraphicsSettingsTab.add(AmbientOcclusionLabel);

		AmbientOcclusionComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[8]);
		AmbientOcclusionLabel.setLabelFor(AmbientOcclusionComboBox);
		AmbientOcclusionComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_AmbientOcclusion = AmbientOcclusionComboBox.getSelectedIndex());
		AmbientOcclusionComboBox.setBounds(505, 15 + (30 * 4), 175, 25);
		AmbientOcclusionComboBox.setToolTipText(Storage.AO_TOOLTIP);
		GraphicsSettingsTab.add(AmbientOcclusionComboBox);

		final JLabel TextureQualityLabel = new JLabel("  Textures");
		TextureQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		TextureQualityLabel.setBounds(355, 15 + (30 * 5), 150, 25);
		GraphicsSettingsTab.add(TextureQualityLabel);

		TextureQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[11]);
		TextureQualityLabel.setLabelFor(TextureQualityComboBox);
		TextureQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_TextureQuality = TextureQualityComboBox.getSelectedIndex());
		TextureQualityComboBox.setEnabled(false);
		TextureQualityComboBox.setBounds(505, 15 + (30 * 5), 175, 25);
		GraphicsSettingsTab.add(TextureQualityComboBox);

		final JLabel VolumetricLightingLabel = new JLabel("  Volumetric Lighting");
		VolumetricLightingLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		VolumetricLightingLabel.setBounds(355, 15 + (30 * 6), 150, 25);
		GraphicsSettingsTab.add(VolumetricLightingLabel);

		VolumetricLightingComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[13]);
		VolumetricLightingLabel.setLabelFor(VolumetricLightingComboBox);
		VolumetricLightingComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_VolumetricLighting = VolumetricLightingComboBox.getSelectedIndex());
		VolumetricLightingComboBox.setEnabled(false);
		VolumetricLightingComboBox.setBounds(505, 15 + (30 * 6), 175, 25);
		GraphicsSettingsTab.add(VolumetricLightingComboBox);


		final JLabel MaxBackgroundFpsLabel = new JLabel("  Background FPS");
		MaxBackgroundFpsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		MaxBackgroundFpsLabel.setToolTipText(Storage.MAXBACKGOUNDFPS_TOOLTIP);
		MaxBackgroundFpsLabel.setBounds(355, 15 + (30 * 7), 150, 25);
		GraphicsSettingsTab.add(MaxBackgroundFpsLabel);

		MaxBackgroundFpsComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[14]);
		MaxBackgroundFpsLabel.setLabelFor(MaxBackgroundFpsComboBox);
		MaxBackgroundFpsComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_MaxBackgroundFps = ((MaxBackgroundFpsComboBox.getSelectedIndex()+1)*5));
		MaxBackgroundFpsComboBox.setBounds(505, 15 + (30 * 7), 175, 25);
		GraphicsSettingsTab.add(MaxBackgroundFpsComboBox);

		final JLabel GameWorldScalingLabel = new JLabel("  Game World Scaling");
		GameWorldScalingLabel.setToolTipText(Storage.GAMESCALING_TOOLTIP);
		GameWorldScalingLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GameWorldScalingLabel.setBounds(355, 15 + (30 * 8), 150, 25);
		GraphicsSettingsTab.add(GameWorldScalingLabel);

		GameWorldScalingInput = new JFormattedTextField(GameScalingNumberFormatter);
		GameWorldScalingLabel.setLabelFor(GameWorldScalingInput);
		GameWorldScalingInput.setToolTipText(Storage.GAMESCALING_TOOLTIP);
		GameWorldScalingInput.setText("100");
		GameWorldScalingInput.setHorizontalAlignment(SwingConstants.RIGHT);
		GameWorldScalingInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		GameWorldScalingInput.setBounds(505, 15 + (30 * 8), 175, 25);
		GraphicsSettingsTab.add(GameWorldScalingInput);

		ShadowsCheckbox = new JCheckBox(String.format("%-88s", "Shadows"));
		ShadowsCheckbox.setBackground(optionBackgroundColor);
		ShadowsCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShadowsCheckbox.addActionListener(e -> {
			Storage.nxtGraphicsSetting_Shadows = ShadowsCheckbox.isSelected();
			Legality.CheckSettings();
		});
		ShadowsCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		ShadowsCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		ShadowsCheckbox.setBounds(355, 15 + (30 * 9), 325, 25);
		GraphicsSettingsTab.add(ShadowsCheckbox);

		LoadingScreensCheckbox = new JCheckBox(String.format("%-82s", "Loading screens"));
		LoadingScreensCheckbox.setBackground(optionBackgroundColor);
		LoadingScreensCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		LoadingScreensCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_LoadingScreens = LoadingScreensCheckbox.isSelected());
		LoadingScreensCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		LoadingScreensCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		LoadingScreensCheckbox.setBounds(355, 15 + (30 * 10), 325, 25);
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
		TerrainBlendingCheckbox.setBounds(355, 15 + (30 * 11), 325, 25);
		GraphicsSettingsTab.add(TerrainBlendingCheckbox);





		/* Client Settings */






		final JPanel ClientSettingsTab = new JPanel();
		tabbedPane.addTab("Client Settings", null, ClientSettingsTab, "Edit your client settings here.");
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
			} else {
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
			} else {
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
			} else {
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
		LanguageSelectionComboBox.addItemListener(e -> {
			Storage.nxtClientSettings_LanguageSelected = LanguageSelectionComboBox.getSelectedIndex();
			System.out.println(Storage.nxtClientSettings_LanguageSelected);
		});
		LanguageSelectionComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		LanguageSelectionComboBox.setBounds(620, 255, 85, 25);
		ClientSettingsTab.add(LanguageSelectionComboBox);


		
		/* Special Mechanics */






		final JPanel SpecialMechanicsTab = new JPanel();
		tabbedPane.addTab("Special Mechanics", null, SpecialMechanicsTab, "Use special mechanics here.");
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
			} catch(final SQLException e1) {
				e1.printStackTrace();
			}
		});
		BecomeZezima.setBounds(475, 15, 225, 25);
		SpecialMechanicsTab.add(BecomeZezima);

		ClearConsole = new JButton("Clear Developer Console History Log");
		ClearConsole.setToolTipText(Storage.CLEAR_DEV_CONSOLE_LOGS_TOOLTIP);
		ClearConsole.setEnabled(false);
		ClearConsole.setFont(new Font("Dialog", Font.PLAIN, 11));
		ClearConsole.addActionListener(e -> {
			try {
				Storage.conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
				Storage.stmt = Storage.conn.createStatement();
				Storage.stmt.addBatch("DELETE FROM 'console';");
				Storage.stmt.executeBatch();
				Storage.stmt.clearBatch();
			} catch(final SQLException e1) {
				e1.printStackTrace();
			}
		});
		ClearConsole.setBounds(15, 45, 225, 25);
		SpecialMechanicsTab.add(ClearConsole);

		PlayerConsole = new JButton("Player Developer Console History Log");
		PlayerConsole.setToolTipText(Storage.POPULATE_PLAYER_DEV_CONSOLE_LOGS_TOOLTIP);
		PlayerConsole.setEnabled(false);
		PlayerConsole.setFont(new Font("Dialog", Font.PLAIN, 11));
		PlayerConsole.addActionListener(e -> {
			try {
				Storage.conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
				Storage.stmt = Storage.conn.createStatement();
				Storage.stmt.addBatch("DELETE FROM 'console';");
				for (int i = 0; i < Storage.DEVELOPER_CONSOLE_COMMANDS[0].length; i++){
					Storage.stmt.addBatch("INSERT INTO 'console' ('KEY', 'DATA') "+
								  		  "VALUES ('"+i+"', '"+Storage.DEVELOPER_CONSOLE_COMMANDS[0][i]+"');");
				}
				Storage.stmt.executeBatch();
			} catch(final SQLException e1) {
				e1.printStackTrace();
			}
		});
		PlayerConsole.setBounds(245, 45, 225, 25);
		SpecialMechanicsTab.add(PlayerConsole);

		JagexConsole = new JButton("Jagex Developer Console History Log");
		JagexConsole.setToolTipText(Storage.POPULATE_JAGEX_DEV_CONSOLE_LOGS_TOOLTIP);
		JagexConsole.setEnabled(false);
		JagexConsole.setFont(new Font("Dialog", Font.PLAIN, 11));
		JagexConsole.addActionListener(e -> {
			try {
				Storage.conn = DriverManager.getConnection("jdbc:sqlite:" + Storage.Cache_settings_location);
				Storage.stmt = Storage.conn.createStatement();
				Storage.stmt.addBatch("DELETE FROM 'console';");
				for (int i = 0; i < Storage.DEVELOPER_CONSOLE_COMMANDS[1].length; i++){
					Storage.stmt.addBatch("INSERT INTO 'console' ('KEY', 'DATA') "+
								  		  "VALUES ('"+i+"', '"+Storage.DEVELOPER_CONSOLE_COMMANDS[1][i]+"');");
				}
				Storage.stmt.executeBatch();
			} catch(final SQLException e1) {
				e1.printStackTrace();
			}
		});
		JagexConsole.setBounds(475, 45, 225, 25);
		SpecialMechanicsTab.add(JagexConsole);





		/* Bottom Pane */





		ShowSensitiveInformation = new JCheckBox("Show Sensitive Information?");
		ShowSensitiveInformation.setToolTipText("Show information such as your Username?");
		ShowSensitiveInformation.setBounds(15, 610, 230, 25);
		ShowSensitiveInformation.setBackground(optionBackgroundColor);
		contentPane.add(ShowSensitiveInformation);

		AllowWritingCheckbox = new JCheckBox("Allow Writing?");
		AllowWritingCheckbox.setToolTipText("Allow writing of all settings when \"Write\" is clicked. Some special mechanic values will be written instantly.");
		AllowWritingCheckbox.addActionListener(e -> {
			if (AllowWritingCheckbox.isSelected()) {
				WriteSettings.setEnabled(true);
				BecomeZezima.setEnabled(true);
				ClearConsole.setEnabled(true);
				PlayerConsole.setEnabled(true);
				JagexConsole.setEnabled(true);
			} else {
				WriteSettings.setEnabled(false);
				BecomeZezima.setEnabled(false);
				ClearConsole.setEnabled(false);
				PlayerConsole.setEnabled(false);
				JagexConsole.setEnabled(false);
			}
		});

		AllowWritingCheckbox.setBounds(250, 610, 130, 25);
		AllowWritingCheckbox.setBackground(optionBackgroundColor);
		contentPane.add(AllowWritingCheckbox);

		ReadSettings = new JButton("Read Settings");
		ReadSettings.setToolTipText("Read information currently saved in your setting file(s).");
		ReadSettings.addActionListener(e -> JCache.Read());
		ReadSettings.setBounds(480, 610, 125, 25);
		contentPane.add(ReadSettings);

		WriteSettings = new JButton("Write Settings");
		WriteSettings.setBounds(610, 610, 125, 25);
		WriteSettings.setEnabled(false);
		WriteSettings.addActionListener(e -> JCache.Write());
		contentPane.add(WriteSettings);

		JCache.Read();
	}
}