package com.kittleapps.desktop.app.nxtconfiguration;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import javax.swing.text.html.HTMLEditorKit;

public class NXTSettingsGUI extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = -4228920598014351419L;
	public static JEditorPane VerboseOutputArea;
	public static HTMLEditorKit VerboseOutputAreaEditor;
	public static JCheckBox
		FlickeringEffectsCheckbox,
		ShadowsCheckbox,
		CustomCursorsCheckbox,
		LoadingScreensCheckbox,
		GroundDecorationsCheckbox,
		TerrainBlendingCheckbox,
		HeatHazeCheckbox,
		ShowSensitiveInformation,
		RememberUsernameCheckbox,
		RandomizeLoginWallpaperCheckbox,
		GlobalAudioMuteCheckbox,
		InGameSoundEffectsBoostCheckbox,
		InGameAmbientSoundEffectsBoostCheckbox,
		InGameVoiceOverBoostCheckbox;

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
		WindowModeComboBox;

	public static JSlider
		BrightnessSlider,
		LoginMusicSlider,
		InGameMusicSlider,
		InGameSoundEffectsSlider,
		InGameAmbientSoundEffectsSlider,
		InGameVoiceOverSlider;

	public static JTextField
		MaxForegroundFpsInput,
		MaxBackgroundFpsInput,
		GameRenderScaleInput,
		InterfaceScaleInput,
		UsernameInput,
		FavouriteWorld1Input,
		FavouriteWorld2Input,
		FavouriteWorld3Input,
		WallpaperIDInput;

	public static JButton
		AddSpriteFlagToUsername,
		AddColourFlagToUsername,
		ClearConsole,
		FavouriteWorld1To2147m,
		FavouriteWorld2To2147m,
		FavouriteWorld3To2147m,
		btnRead,
		btnWrite;

	public static JFrame frame;
	public static void main(final String[] args) {
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

	public static JPanel contentPane;

	public NXTSettingsGUI() {
		final Color backgroundColour = new Color(45, 45, 45), optionBackgroundColor = new Color (40, 40, 40);
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
					Settings.Cache_settings_location = file.getAbsolutePath();
				} else {
					JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "The File at:\n\n" + file.getAbsolutePath() + " was not the Cache file this program is loking for. Please select 'Settings.jcache'");
				}
			} else {}
		});
		FileMenu.add(FileMenuSelectCache);

		final JMenuItem FileMenuSelectPreferences = new JMenuItem("Manually select preferences");
		FileMenuSelectPreferences.addActionListener(e -> {
			final FileNameExtensionFilter filter = new FileNameExtensionFilter("NXT Preference Files", "cfg");
			final JFileChooser fileChooser = new JFileChooser();
			if (Storage.OS_TYPE == 0) {
				fileChooser.setCurrentDirectory(new File(System.getenv("ProgramData") + System.getProperty("file.separator") + "Jagex" + System.getProperty("file.separator") + "launcher" + System.getProperty("file.separator")));
			} else if (Storage.OS_TYPE == 1) {
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Jagex" + System.getProperty("file.separator") + "launcher" + System.getProperty("file.separator")));
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
					Settings.preferences_config = file;
				} else {
					JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "The File at:\n\n" + file.getAbsolutePath() + " was not the preference file this program is loking for. Please select 'preferences.cfg'");
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

		final JLabel RemoveRoofsLabel = new JLabel("Remove Roofs");
		RemoveRoofsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		RemoveRoofsLabel.setBounds(15 + (30 * 0), 15, 150, 25);
		GraphicsSettingsTab.add(RemoveRoofsLabel);

		RemoveRoofsComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[0]);
		RemoveRoofsComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_RemoveRoofs = RemoveRoofsComboBox.getSelectedIndex());
		RemoveRoofsComboBox.setToolTipText(Storage.REMOVEROOFS_TOOLTIP);
		RemoveRoofsComboBox.setBounds(165, 15 + (30 * 0), 175, 25);
		RemoveRoofsComboBox.setSelectedIndex(1);
		GraphicsSettingsTab.add(RemoveRoofsComboBox);

		final JLabel DrawDistanceLabel = new JLabel("Draw Distance");
		DrawDistanceLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		DrawDistanceLabel.setBounds(15, 15 + (30 * 1), 150, 25);
		GraphicsSettingsTab.add(DrawDistanceLabel);

		DrawDistanceComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[1]);
		DrawDistanceComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_DrawDistance = DrawDistanceComboBox.getSelectedIndex());
		DrawDistanceComboBox.setBounds(165, 15 + (30 * 1), 175, 25);
		GraphicsSettingsTab.add(DrawDistanceComboBox);

		final JLabel ShadowQualityLabel = new JLabel("Shadow Quality");
		ShadowQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShadowQualityLabel.setBounds(15, 15 + (30 * 2), 150, 25);
		GraphicsSettingsTab.add(ShadowQualityLabel);

		ShadowQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[2]);
		ShadowQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_ShadowQuality = ShadowQualityComboBox.getSelectedIndex());
		ShadowQualityComboBox.setEnabled(false);
		ShadowQualityComboBox.setBounds(165, 15 + (30 * 2), 175, 25);
		GraphicsSettingsTab.add(ShadowQualityComboBox);

		final JLabel VSyncLabel = new JLabel("VSync");
		VSyncLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		VSyncLabel.setBounds(15, 15 + (30 * 3), 150, 25);
		GraphicsSettingsTab.add(VSyncLabel);

		VSyncComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[3]);
		VSyncComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_VSync = VSyncComboBox.getSelectedIndex() - 1);
		VSyncComboBox.setBounds(165, 15 + (30 * 3), 175, 25);
		GraphicsSettingsTab.add(VSyncComboBox);

		final JLabel AntiAliasingModeLabel = new JLabel("Anti-aliasing Mode");
		AntiAliasingModeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AntiAliasingModeLabel.setBounds(15, 15 + (30 * 4), 150, 25);
		GraphicsSettingsTab.add(AntiAliasingModeLabel);

		AntiAliasingModeComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[4]);
		AntiAliasingModeComboBox.addItemListener(e -> {
			Storage.nxtGraphicsSetting_AntiAliasingMode = AntiAliasingModeComboBox.getSelectedIndex();
			Legality.CheckSettings();
		});
		AntiAliasingModeComboBox.setBounds(165, 15 + (30 * 4), 175, 25);
		GraphicsSettingsTab.add(AntiAliasingModeComboBox);

		final JLabel AntiAliasingQualityLabel = new JLabel("Anti-aliasing Quality");
		AntiAliasingQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AntiAliasingQualityLabel.setBounds(15, 15 + (30 * 5), 150, 25);
		GraphicsSettingsTab.add(AntiAliasingQualityLabel);

		AntiAliasingQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[5]);
		AntiAliasingQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_AntiAliasingQuality = AntiAliasingQualityComboBox.getSelectedIndex());
		AntiAliasingQualityComboBox.setEnabled(false);
		AntiAliasingQualityComboBox.setBounds(165, 15 + (30 * 5), 175, 25);
		GraphicsSettingsTab.add(AntiAliasingQualityComboBox);

		final JLabel WaterQualityLabel = new JLabel("Water Quality");
		WaterQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		WaterQualityLabel.setBounds(15, 15 + (30 * 6), 150, 25);
		GraphicsSettingsTab.add(WaterQualityLabel);

		WaterQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[6]);
		WaterQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_WaterQuality = WaterQualityComboBox.getSelectedIndex());
		WaterQualityComboBox.setBounds(165, 15 + (30 * 6), 175, 25);
		GraphicsSettingsTab.add(WaterQualityComboBox);

		final JLabel LightingDetailLabel = new JLabel("Lighting Detail");
		LightingDetailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		LightingDetailLabel.setBounds(15, 15 + (30 * 7), 150, 25);
		GraphicsSettingsTab.add(LightingDetailLabel);

		LightingDetailComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[7]);
		LightingDetailComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_LightingQuality = LightingDetailComboBox.getSelectedIndex());
		LightingDetailComboBox.setBounds(165, 15 + (30 * 7), 175, 25);
		GraphicsSettingsTab.add(LightingDetailComboBox);

		final JLabel AmbientOcclusionLabel = new JLabel("Ambient Occlusion");
		AmbientOcclusionLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AmbientOcclusionLabel.setToolTipText(Storage.AO_TOOLTIP);
		AmbientOcclusionLabel.setBounds(15, 15 + (30 * 8), 150, 25);
		GraphicsSettingsTab.add(AmbientOcclusionLabel);

		AmbientOcclusionComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[8]);
		AmbientOcclusionComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_AmbientOcclusion = AmbientOcclusionComboBox.getSelectedIndex());
		AmbientOcclusionComboBox.setBounds(165, 15 + (30 * 8), 175, 25);
		AmbientOcclusionComboBox.setToolTipText(Storage.AO_TOOLTIP);
		GraphicsSettingsTab.add(AmbientOcclusionComboBox);

		final JLabel BloomQualityLabel = new JLabel("Bloom Quality");
		BloomQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		BloomQualityLabel.setBounds(15, 15 + (30 * 9), 150, 25);
		GraphicsSettingsTab.add(BloomQualityLabel);

		BloomQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[9]);
		BloomQualityComboBox.addItemListener(e -> {
			Storage.nxtGraphicsSetting_Bloom = BloomQualityComboBox.getSelectedIndex();
			Legality.CheckSettings();
		});
		BloomQualityComboBox.setBounds(165, 15 + (30 * 9), 175, 25);
		GraphicsSettingsTab.add(BloomQualityComboBox);

		final JLabel DepthOfFieldLabel = new JLabel("Depth of Field");
		DepthOfFieldLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		DepthOfFieldLabel.setToolTipText(Storage.DOF_TOOLTIP);
		DepthOfFieldLabel.setBounds(15, 15 + (30 * 10), 150, 25);
		GraphicsSettingsTab.add(DepthOfFieldLabel);

		DepthOfFieldComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[10]);
		DepthOfFieldComboBox.setToolTipText(Storage.DOF_TOOLTIP);
		DepthOfFieldComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_DepthOfField = DepthOfFieldComboBox.getSelectedIndex());
		DepthOfFieldComboBox.setBounds(165, 15 + (30 * 10), 175, 25);
		GraphicsSettingsTab.add(DepthOfFieldComboBox);

		final JLabel MaxForegroundFpsLabel = new JLabel("Maximum Foreground FPS");
		MaxForegroundFpsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		MaxForegroundFpsLabel.setToolTipText(Storage.MAXFOREGOUNDFPS_TOOLTIP);
		MaxForegroundFpsLabel.setBounds(15, 15 + (30 * 11), 150, 25);
		GraphicsSettingsTab.add(MaxForegroundFpsLabel);

		MaxForegroundFpsInput = new JFormattedTextField(WorldNumberFormatter);
		MaxForegroundFpsInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		MaxForegroundFpsInput.setHorizontalAlignment(SwingConstants.RIGHT);
		MaxForegroundFpsInput.setToolTipText(Storage.MAXFOREGOUNDFPS_TOOLTIP);
		MaxForegroundFpsInput.setText("" + (Storage.FrameRate + 10));
		MaxForegroundFpsInput.setBounds(165, 15 + (30 * 11), 175, 25);
		GraphicsSettingsTab.add(MaxForegroundFpsInput);

		final JLabel InterfaceScalingLabel = new JLabel("Interface Scale");
		InterfaceScalingLabel.setToolTipText(Storage.INTERFACESCALING_TOOLTIP);
		InterfaceScalingLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		InterfaceScalingLabel.setBounds(15, 15 + (30 * 12), 150, 25);
		GraphicsSettingsTab.add(InterfaceScalingLabel);

		InterfaceScaleInput = new JFormattedTextField(InterfaceScalingNumberFormatter);
		InterfaceScaleInput.setToolTipText(Storage.INTERFACESCALING_TOOLTIP);
		InterfaceScaleInput.setText("100");
		InterfaceScaleInput.setHorizontalAlignment(SwingConstants.RIGHT);
		InterfaceScaleInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		InterfaceScaleInput.setBounds(165, 15 + (30 * 12), 175, 25);
		GraphicsSettingsTab.add(InterfaceScaleInput);



		// Right Column



		final JLabel BrightnessLabel = new JLabel("Brightness");
		BrightnessLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		BrightnessLabel.setBounds(355, 15 + (30 * 0), 150, 25);
		GraphicsSettingsTab.add(BrightnessLabel);

		BrightnessSlider = new JSlider();
		BrightnessSlider.addChangeListener(e -> Storage.nxtGraphicsSetting_Brightness = BrightnessSlider.getValue());
		BrightnessSlider.setSnapToTicks(true);
		BrightnessSlider.setMaximum(4);
		BrightnessSlider.setPaintTicks(true);
		BrightnessSlider.setBounds(505, 15 + (30 * 0), 175, 25);
		BrightnessSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		BrightnessSlider.setBackground(optionBackgroundColor);
		GraphicsSettingsTab.add(BrightnessSlider);

		final JLabel TextureQualityLabel = new JLabel("Texture Quality");
		TextureQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		TextureQualityLabel.setBounds(355, 15 + (30 * 1), 150, 25);
		GraphicsSettingsTab.add(TextureQualityLabel);

		TextureQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[11]);
		TextureQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_TextureQuality = TextureQualityComboBox.getSelectedIndex());
		TextureQualityComboBox.setEnabled(false);
		TextureQualityComboBox.setBounds(505, 15 + (30 * 1), 175, 25);
		GraphicsSettingsTab.add(TextureQualityComboBox);

		final JLabel AnisotropicFilteringLabel = new JLabel("Anisotropic Filtering");
		AnisotropicFilteringLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AnisotropicFilteringLabel.setBounds(355, 15 + (30 * 2), 150, 25);
		GraphicsSettingsTab.add(AnisotropicFilteringLabel);

		AnisotropicFilteringComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[12]);
		AnisotropicFilteringComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_AnisotropicFiltering = AnisotropicFilteringComboBox.getSelectedIndex());
		AnisotropicFilteringComboBox.setBounds(505, 15 + (30 * 2), 175, 25);
		GraphicsSettingsTab.add(AnisotropicFilteringComboBox);

		final JLabel VolumetricLightingLabel = new JLabel("Volumetric Lighting");
		VolumetricLightingLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		VolumetricLightingLabel.setBounds(355, 15 + (30 * 3), 150, 25);
		GraphicsSettingsTab.add(VolumetricLightingLabel);

		VolumetricLightingComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[13]);
		VolumetricLightingComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_VolumetricLighting = VolumetricLightingComboBox.getSelectedIndex());
		VolumetricLightingComboBox.setEnabled(false);
		VolumetricLightingComboBox.setBounds(505, 15 + (30 * 3), 175, 25);
		GraphicsSettingsTab.add(VolumetricLightingComboBox);

		final JLabel FlickeringEffectsLabel = new JLabel("Flickering Effects");
		FlickeringEffectsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		FlickeringEffectsLabel.setBounds(355, 15 + (30 * 4), 150, 25);
		GraphicsSettingsTab.add(FlickeringEffectsLabel);

		FlickeringEffectsCheckbox = new JCheckBox();
		FlickeringEffectsCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_FlickeringEffects = FlickeringEffectsCheckbox.isSelected());
		FlickeringEffectsCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		FlickeringEffectsCheckbox.setBounds(665, 15 + (30 * 4) + 5, 15, 15);
		GraphicsSettingsTab.add(FlickeringEffectsCheckbox);

		final JLabel ShadowsLabel = new JLabel("Shadows");
		ShadowsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShadowsLabel.setBounds(355, 15 + (30 * 5), 150, 25);
		GraphicsSettingsTab.add(ShadowsLabel);

		ShadowsCheckbox = new JCheckBox();
		ShadowsCheckbox.addActionListener(e -> {
			Storage.nxtGraphicsSetting_Shadows = ShadowsCheckbox.isSelected();
			Legality.CheckSettings();
		});
		ShadowsCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		ShadowsCheckbox.setBounds(665, 15 + (30 * 5) + 5, 15, 15);
		GraphicsSettingsTab.add(ShadowsCheckbox);

		final JLabel CustomCursorsLabel = new JLabel("Custom Cursors");
		CustomCursorsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		CustomCursorsLabel.setBounds(355, 15 + (30 * 6), 150, 25);
		GraphicsSettingsTab.add(CustomCursorsLabel);

		CustomCursorsCheckbox = new JCheckBox();
		CustomCursorsCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_CustomCursors = CustomCursorsCheckbox.isSelected());
		CustomCursorsCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		CustomCursorsCheckbox.setBounds(665, 15 + (30 * 6) + 5, 15, 15);
		CustomCursorsCheckbox.setSelected(true);
		GraphicsSettingsTab.add(CustomCursorsCheckbox);

		final JLabel LoadingScreensLabel = new JLabel("Loading Screens");
		LoadingScreensLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		LoadingScreensLabel.setBounds(355, 15 + (30 * 7), 150, 25);
		GraphicsSettingsTab.add(LoadingScreensLabel);

		LoadingScreensCheckbox = new JCheckBox();
		LoadingScreensCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_LoadingScreens = LoadingScreensCheckbox.isSelected());
		LoadingScreensCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		LoadingScreensCheckbox.setBounds(665, 15 + (30 * 7) + 5, 15, 15);
		LoadingScreensCheckbox.setSelected(true);
		GraphicsSettingsTab.add(LoadingScreensCheckbox);

		final JLabel GroundDecorationsLabel = new JLabel("Ground Decorations");
		GroundDecorationsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GroundDecorationsLabel.setBounds(355, 15 + (30 * 8), 150, 25);
		GraphicsSettingsTab.add(GroundDecorationsLabel);

		GroundDecorationsCheckbox = new JCheckBox();
		GroundDecorationsCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_GroundDecor = GroundDecorationsCheckbox.isSelected());
		GroundDecorationsCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		GroundDecorationsCheckbox.setBounds(665, 15 + (30 * 8) + 5, 15, 15);
		GraphicsSettingsTab.add(GroundDecorationsCheckbox);

		final JLabel TerrainBlendingLabel = new JLabel("Terrain Blending");
		TerrainBlendingLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		TerrainBlendingLabel.setBounds(355, 15 + (30 * 9), 150, 25);
		GraphicsSettingsTab.add(TerrainBlendingLabel);

		TerrainBlendingCheckbox = new JCheckBox();
		TerrainBlendingCheckbox.addActionListener(e -> {
			Storage.nxtGraphicsSetting_TerrainBlending = TerrainBlendingCheckbox.isSelected();
			Legality.CheckSettings();
		});
		TerrainBlendingCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		TerrainBlendingCheckbox.setBounds(665, 15 + (30 * 9) + 5, 15, 15);
		GraphicsSettingsTab.add(TerrainBlendingCheckbox);

		final JLabel HeatHazeLabel = new JLabel("Heat Haze");
		HeatHazeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		HeatHazeLabel.setToolTipText(Storage.HEATHAZE_TOOLTIP);
		HeatHazeLabel.setBounds(355, 15 + (30 * 10), 150, 25);
		GraphicsSettingsTab.add(HeatHazeLabel);

		HeatHazeCheckbox = new JCheckBox();
		HeatHazeCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		HeatHazeCheckbox.setBounds(665, 15 + (30 * 10) + 5, 15, 15);
		GraphicsSettingsTab.add(HeatHazeCheckbox);

		final JLabel MaxBackgroundFpsLabel = new JLabel("Maximum Background FPS");
		MaxBackgroundFpsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		MaxBackgroundFpsLabel.setToolTipText(Storage.MAXBACKGOUNDFPS_TOOLTIP);
		MaxBackgroundFpsLabel.setBounds(355, 15 + (30 * 11), 150, 25);
		GraphicsSettingsTab.add(MaxBackgroundFpsLabel);

		MaxBackgroundFpsInput = new JFormattedTextField(WorldNumberFormatter);
		MaxBackgroundFpsInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		MaxBackgroundFpsInput.setHorizontalAlignment(SwingConstants.RIGHT);
		MaxBackgroundFpsInput.setToolTipText(Storage.MAXBACKGOUNDFPS_TOOLTIP);
		MaxBackgroundFpsInput.setText("30");
		MaxBackgroundFpsInput.setBounds(505, 15 + (30 * 11), 175, 25);
		GraphicsSettingsTab.add(MaxBackgroundFpsInput);

		final JLabel GameScalingLabel = new JLabel("Game Render Scale");
		GameScalingLabel.setToolTipText(Storage.GAMESCALING_TOOLTIP);
		GameScalingLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GameScalingLabel.setBounds(355, 15 + (30 * 12), 150, 25);
		GraphicsSettingsTab.add(GameScalingLabel);

		GameRenderScaleInput = new JFormattedTextField(GameScalingNumberFormatter);
		GameRenderScaleInput.setToolTipText(Storage.GAMESCALING_TOOLTIP);
		GameRenderScaleInput.setText("100");
		GameRenderScaleInput.setHorizontalAlignment(SwingConstants.RIGHT);
		GameRenderScaleInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		GameRenderScaleInput.setBounds(505, 15 + (30 * 12), 175, 25);
		GraphicsSettingsTab.add(GameRenderScaleInput);



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
		UsernameInput.setToolTipText(Storage.USERNAME_INPUT_TOOLTIP);
		UsernameInput.setBounds(170, 15, 535, 25);
		ClientSettingsTab.add(UsernameInput);
		UsernameInput.setColumns(10);

		final JLabel FavouriteWorld1Label = new JLabel("Favourite world 1");
		FavouriteWorld1Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		FavouriteWorld1Label.setBounds(15, 45, 130, 25);
		ClientSettingsTab.add(FavouriteWorld1Label);

		FavouriteWorld1Input = new JFormattedTextField(WorldNumberFormatter);
		FavouriteWorld1Input.setToolTipText(Storage.FAVOURITE_WORLD_INPUT_TOOLTIP);
		FavouriteWorld1Input.setBounds(170, 45, 85, 25);
		ClientSettingsTab.add(FavouriteWorld1Input);

		final JLabel FavouriteWorld2Label = new JLabel("Favourite world 2");
		FavouriteWorld2Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		FavouriteWorld2Label.setBounds(260, 45, 130, 25);
		ClientSettingsTab.add(FavouriteWorld2Label);

		FavouriteWorld2Input = new JFormattedTextField(WorldNumberFormatter);
		FavouriteWorld2Input.setToolTipText(Storage.FAVOURITE_WORLD_INPUT_TOOLTIP);
		FavouriteWorld2Input.setBounds(395, 45, 85, 25);
		ClientSettingsTab.add(FavouriteWorld2Input);

		final JLabel FavouriteWorld3Label = new JLabel("Favourite world 3");
		FavouriteWorld3Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		FavouriteWorld3Label.setBounds(485, 45, 130, 25);
		ClientSettingsTab.add(FavouriteWorld3Label);

		FavouriteWorld3Input = new JFormattedTextField(WorldNumberFormatter);
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
		InGameMusicSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameMusicVolume = InGameMusicSlider.getValue());
		InGameMusicSlider.setMaximum(255);
		InGameMusicSlider.setMajorTickSpacing(51);
		InGameMusicSlider.setPaintTicks(false);
		InGameMusicSlider.setBounds(170, 135, 535, 25);
		InGameMusicSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(InGameMusicSlider);

		final JLabel InGameSoundEffectsLabel = new JLabel("Sound Effect Volume");
		InGameSoundEffectsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameSoundEffectsLabel.setBounds(15, 165, 150, 25);
		ClientSettingsTab.add(InGameSoundEffectsLabel);

		InGameSoundEffectsSlider = new JSlider();
		InGameSoundEffectsSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameSoundEffectsVolume = InGameSoundEffectsSlider.getValue());
		InGameSoundEffectsSlider.setMaximum(255);
		InGameSoundEffectsSlider.setPaintTicks(false);
		InGameSoundEffectsSlider.setBounds(170, 165, 445, 25);
		InGameSoundEffectsSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(InGameSoundEffectsSlider);

		InGameSoundEffectsBoostCheckbox = new JCheckBox("Boost?");
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
		InGameAmbientSoundEffectsSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume = InGameAmbientSoundEffectsSlider.getValue());
		InGameAmbientSoundEffectsSlider.setMaximum(255);
		InGameAmbientSoundEffectsSlider.setPaintTicks(false);
		InGameAmbientSoundEffectsSlider.setBounds(170, 195, 445, 25);
		InGameAmbientSoundEffectsSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(InGameAmbientSoundEffectsSlider);

		InGameAmbientSoundEffectsBoostCheckbox = new JCheckBox("Boost?");
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
		InGameVoiceOverSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameVoiceOverVolume = InGameVoiceOverSlider.getValue());
		InGameVoiceOverSlider.setMaximum(255);
		InGameVoiceOverSlider.setPaintTicks(false);
		InGameVoiceOverSlider.setBounds(170, 225, 445, 25);
		InGameVoiceOverSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(InGameVoiceOverSlider);

		InGameVoiceOverBoostCheckbox = new JCheckBox("Boost?");
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






		/* Special Mechanics */






		final JPanel SpecialMechanicsTab = new JPanel();
		tabbedPane.addTab("Special Mechanics", null, SpecialMechanicsTab, "Add special mechanics here. (More details inside)");
		SpecialMechanicsTab.setBackground(backgroundColour);
		SpecialMechanicsTab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		SpecialMechanicsTab.setLayout(null);

		AddSpriteFlagToUsername = new JButton("Add sprite flag to Username");
		AddSpriteFlagToUsername.setToolTipText(Storage.ADD_SPRITE_FLAG_TOOLTIP);
		AddSpriteFlagToUsername.addActionListener(e -> {
			if (UsernameInput != null) {
				UsernameInput.setText(UsernameInput.getText() + "<sprite=SPRITE_ID,SPRITE_SUB_ID>");
			}
		});
		AddSpriteFlagToUsername.setFont(new Font("Dialog", Font.PLAIN, 11));
		AddSpriteFlagToUsername.setBounds(15, 15, 225, 25);
		SpecialMechanicsTab.add(AddSpriteFlagToUsername);

		AddColourFlagToUsername = new JButton("Add colour flag to Username");
		AddColourFlagToUsername.setToolTipText(Storage.ADD_COLOUR_FLAG_TOOLTIP);
		AddColourFlagToUsername.addActionListener(e ->{
			if (UsernameInput != null) {
				UsernameInput.setText(UsernameInput.getText() + "<col=ffffff></col>");
			}
		});
		AddColourFlagToUsername.setFont(new Font("Dialog", Font.PLAIN, 11));
		AddColourFlagToUsername.setBounds(245, 15, 225, 25);
		SpecialMechanicsTab.add(AddColourFlagToUsername);

		ClearConsole = new JButton("Clear Developer Console Log");
		ClearConsole.setToolTipText(Storage.CLEAR_DEV_CONSOLE_LOGS_TOOLTIP);
		ClearConsole.setEnabled(false);
		ClearConsole.setFont(new Font("Dialog", Font.PLAIN, 11));
		ClearConsole.addActionListener(e -> {
			Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:" + Settings.Cache_settings_location);
				Statement stmt;
				stmt = conn.createStatement();
				stmt.addBatch("DELETE FROM 'console';");
				stmt.executeBatch();
			} catch(final SQLException e1) {
				e1.printStackTrace();
			}
		});
		ClearConsole.setBounds(475, 15, 225, 25);
		SpecialMechanicsTab.add(ClearConsole);

		FavouriteWorld1To2147m = new JButton("Set favourite world 1 to 2147m");
		FavouriteWorld1To2147m.setToolTipText(Storage.FAVOURITE_WORLD_TO_2147M_TOOLTIP.replace("{{{SLOT}}}","first"));
		FavouriteWorld1To2147m.setEnabled(false);
		FavouriteWorld1To2147m.addActionListener(e -> {
			Storage.nxtClientSettings_FavouriteWorld1 = 2147000000;
			Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:" + Settings.Cache_settings_location);
				Statement stmt;
				stmt = conn.createStatement();
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_1+"', '" + Storage.nxtClientSettings_FavouriteWorld1 + "');");
				stmt.executeBatch();
			} catch(final SQLException e1) {
				e1.printStackTrace();
			}
		});
		FavouriteWorld1To2147m.setFont(new Font("Dialog", Font.PLAIN, 11));
		FavouriteWorld1To2147m.setBounds(15, 45, 225, 25);
		SpecialMechanicsTab.add(FavouriteWorld1To2147m);

		FavouriteWorld2To2147m = new JButton("Set favourite world 2 to 2147m");
		FavouriteWorld2To2147m.setToolTipText(Storage.FAVOURITE_WORLD_TO_2147M_TOOLTIP.replace("{{{SLOT}}}","second"));
		FavouriteWorld2To2147m.setEnabled(false);
		FavouriteWorld2To2147m.addActionListener(e -> {
			Storage.nxtClientSettings_FavouriteWorld2 = 2147000000;
			Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:" + Settings.Cache_settings_location);
				Statement stmt;
				stmt = conn.createStatement();
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_2+"', '" + Storage.nxtClientSettings_FavouriteWorld2 + "');");
				stmt.executeBatch();
			} catch(final SQLException e1) {
				e1.printStackTrace();
			}
		});
		FavouriteWorld2To2147m.setFont(new Font("Dialog", Font.PLAIN, 11));
		FavouriteWorld2To2147m.setBounds(245, 45, 225, 25);
		SpecialMechanicsTab.add(FavouriteWorld2To2147m);

		FavouriteWorld3To2147m = new JButton("Set favourite world 3 to 2147m");
		FavouriteWorld3To2147m.setToolTipText(Storage.FAVOURITE_WORLD_TO_2147M_TOOLTIP.replace("{{{SLOT}}}","third"));
		FavouriteWorld3To2147m.setEnabled(false);
		FavouriteWorld3To2147m.addActionListener(e -> {
			Storage.nxtClientSettings_FavouriteWorld3 = 2147000000;
			Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:" + Settings.Cache_settings_location);
				Statement stmt;
				stmt = conn.createStatement();
				stmt.addBatch("DELETE FROM 'vt-varc' WHERE KEY='"+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3+"';");
				stmt.addBatch("INSERT INTO 'vt-varc' ('KEY', 'DATA') VALUES ('"+Storage.CACHE_KEY_VT_VARC_FAVOURITE_WORLD_3+"', '" + Storage.nxtClientSettings_FavouriteWorld3 + "');");
				stmt.executeBatch();
			} catch(final SQLException e1) {
				e1.printStackTrace();
			}
		});
		FavouriteWorld3To2147m.setFont(new Font("Dialog", Font.PLAIN, 11));
		FavouriteWorld3To2147m.setBounds(475, 45, 225, 25);
		SpecialMechanicsTab.add(FavouriteWorld3To2147m);

		final JScrollPane VerboseOutputTab = new JScrollPane();
		VerboseOutputTab.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		VerboseOutputTab.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		VerboseOutputTab.setBackground(backgroundColour);
		VerboseOutputTab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tabbedPane.addTab("Read-Only Information", null, VerboseOutputTab, "Read the Verbose information as it occurs.");

		VerboseOutputArea = new JEditorPane();
		VerboseOutputArea.setContentType("text/html");
		VerboseOutputArea.setText("<html></html>");
		VerboseOutputArea.setEditable(false);
		VerboseOutputArea.setBackground(Color.BLACK);
		VerboseOutputAreaEditor = new HTMLEditorKit();
		VerboseOutputArea.setEditorKit(VerboseOutputAreaEditor);
		VerboseOutputTab.setViewportView(VerboseOutputArea);





		/* Bottom Pane */





		ShowSensitiveInformation = new JCheckBox("Show Sensitive Information?");
		ShowSensitiveInformation.setToolTipText("Show information such as: Your Username, UID, etc.");
		ShowSensitiveInformation.setBounds(15, 610, 230, 25);
		ShowSensitiveInformation.setBackground(optionBackgroundColor);
		contentPane.add(ShowSensitiveInformation);

		btnRead = new JButton("Read");
		btnRead.setToolTipText("Read information currently saved in your setting file(s); Writes to Read-Only Information output.");
		btnRead.addActionListener(e -> {
			if ((VerboseOutputArea != null) && (VerboseOutputAreaEditor != null)) {
				VerboseOutputArea.setText("<html></html>");
				UsernameInput.setText("");
			}
			/* Init */
			Settings.TestRead();
			/* Apply */
			Settings.TestRead();
		});
		btnRead.setBounds(582, 610, 75, 25);
		contentPane.add(btnRead);

		btnWrite = new JButton("Write");
		btnWrite.setBounds(662, 610, 75, 25);
		btnWrite.setEnabled(false);
		btnWrite.addActionListener(e -> Settings.TestWrite());
		contentPane.add(btnWrite);

		final JCheckBox AllowWritingCheckbox = new JCheckBox("Allow Writing?");
		AllowWritingCheckbox.setToolTipText("Allow writing of all settings when \"Write\" is clicked; Some special mechanic values will be written instantly.");
		AllowWritingCheckbox.addActionListener(e -> {
			if (AllowWritingCheckbox.isSelected()) {
				btnWrite.setEnabled(true);
				ClearConsole.setEnabled(true);
				FavouriteWorld1To2147m.setEnabled(true);
				FavouriteWorld2To2147m.setEnabled(true);
				FavouriteWorld3To2147m.setEnabled(true);
			} else {
				btnWrite.setEnabled(false);
				ClearConsole.setEnabled(false);
				FavouriteWorld1To2147m.setEnabled(false);
				FavouriteWorld2To2147m.setEnabled(false);
				FavouriteWorld3To2147m.setEnabled(false);
			}
		});
		AllowWritingCheckbox.setBounds(250, 610, 130, 25);
		AllowWritingCheckbox.setBackground(optionBackgroundColor);
		contentPane.add(AllowWritingCheckbox);



		 /* Currently required to force a read twice, due to an issue with the audio sliders. */
		Settings.TestRead();
		Settings.TestRead();
	}
}