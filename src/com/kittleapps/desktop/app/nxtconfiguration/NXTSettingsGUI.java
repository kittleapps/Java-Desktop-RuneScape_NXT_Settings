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

	private static final long serialVersionUID = 6599149859239454448L;
	public static JEditorPane VerboseOutputArea;
	public static HTMLEditorKit VerboseOutputAreaEditor;
	public static JCheckBox FlickeringEffectsCheckbox,
	ShadowsCheckbox,
	CustomCursorsCheckbox,
	LoadingScreensCheckbox,
	GroundDecorationsCheckbox,
	TerrainBlendingCheckbox,
	ShowSensitiveInformation,
	RememberUsernameCheckbox,
	RandomizeLoginWallpaperCheckbox,
	GlobalAudioMuteCheckbox;
	public static JComboBox < ?>RemoveRoofsComboBox,
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
	HeatHazeComboBox,
	WindowModeComboBox;
	public static JSlider BrightnessSlider,
	LoginMusicSlider,
	InGameMusicSlider,
	InGameSoundEffectsSlider,
	InGameAmbientSoundEffectsSlider,
	InGameVoiceOverSlider;
	public static JTextField UsernameInput,
	FavouriteWorld1Input,
	FavouriteWorld2Input,
	FavouriteWorld3Input,
	WallpaperIDInput;
	public static JButton AddSpriteFlagToUsername,
	AddColourFlagToUsername,
	ClearConsole,
	FavouriteWorld1To2147m,
	FavouriteWorld2To2147m,
	FavouriteWorld3To2147m,
	btnRead,
	btnWrite;

	public static void main(final String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Storage.init();
				NXTSettingsGUI frame = new NXTSettingsGUI();
				frame.setVisible(true);
			} catch(Exception e) {
				e.printStackTrace();
			}
		});
	}

	private final JPanel contentPane;

	public NXTSettingsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 675);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setTitle("NXT Settings");
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 750, 25);
		contentPane.add(menuBar);
		JMenu FileMenu = new JMenu("File");
		menuBar.add(FileMenu);

		JMenuItem FileMenuSelectCache = new JMenuItem("Manually select cache");
		FileMenuSelectCache.addActionListener(e -> {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("NXT Cache Files", "jcache");
			JFileChooser fileChooser = new JFileChooser();
			if (Storage.OS_TYPE == 0) {
				fileChooser.setCurrentDirectory(new File(System.getenv("LOCALAPPDATA") + System.getProperty("file.separator") + "Jagex" + System.getProperty("file.separator") + "RuneScape" + System.getProperty("file.separator")));
			} else if (Storage.OS_TYPE == 1) {
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Jagex" + System.getProperty("file.separator") + "RuneScape" + System.getProperty("file.separator")));
			}
			fileChooser.setDialogTitle("Locate 'Settings.jcache'");
			fileChooser.setApproveButtonText("Load");
			fileChooser.setSelectedFile(new File("Settings.jcache"));
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setFileFilter(filter);
			int returnVal = fileChooser.showOpenDialog(NXTSettingsGUI.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				if (file.getName().equalsIgnoreCase("Settings.jcache")) {
					Settings.Cache_settings_location = file.getAbsolutePath();
				} else {
					JOptionPane.showMessageDialog(NXTSettingsGUI.this, "The File at:\n\n" + file.getAbsolutePath() + " was not the Cache file this program is loking for. Please select 'Settings.jcache'");
				}
			} else {

}
		});
		FileMenu.add(FileMenuSelectCache);

		JMenuItem FileMenuSelectPreferences = new JMenuItem("Manually select preferences");
		FileMenuSelectPreferences.addActionListener(e -> {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("NXT Preference Files", "cfg");
			JFileChooser fileChooser = new JFileChooser();
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
			int returnVal = fileChooser.showOpenDialog(NXTSettingsGUI.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				if (file.getName().equalsIgnoreCase("preferences.cfg")) {
					Settings.preferences_config = file;
				} else {
					JOptionPane.showMessageDialog(NXTSettingsGUI.this, "The File at:\n\n" + file.getAbsolutePath() + " was not the preference file this program is loking for. Please select 'preferences.cfg'");
				}
			} else {

}
		});
		FileMenu.add(FileMenuSelectPreferences);

		JMenuItem FileMenuExit = new JMenuItem("Exit");
		FileMenuExit.addActionListener(e -> System.exit(0));

		FileMenu.add(FileMenuExit);

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setBounds(15, 26, 720, 569);
		contentPane.add(tabbedPane);

		JPanel GraphicsSettingsTab = new JPanel();
		tabbedPane.addTab("Graphics Settings", null, GraphicsSettingsTab, "Edit your graphics settings here.");
		GraphicsSettingsTab.setBackground(Color.BLACK);
		GraphicsSettingsTab.setLayout(null);

		JLabel RemoveRoofsLabel = new JLabel("Remove Roofs");
		RemoveRoofsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		RemoveRoofsLabel.setBounds(15 + (30 * 0), 15, 150, 25);
		GraphicsSettingsTab.add(RemoveRoofsLabel);

		RemoveRoofsComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[0]);
		RemoveRoofsComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_RemoveRoofs = RemoveRoofsComboBox.getSelectedIndex());
		RemoveRoofsComboBox.setToolTipText("<html>Note: \"None\" is NOT natively an option for NXT.<br>"+
				"<br>"+
				"This option WILL cause graphical issues.<br>" +
				"Set to another option if you dislike the roofs.</html>");
		RemoveRoofsComboBox.setBounds(165, 15 + (30 * 0), 175, 25);
		RemoveRoofsComboBox.setSelectedIndex(1);
		GraphicsSettingsTab.add(RemoveRoofsComboBox);

		JLabel DrawDistanceLabel = new JLabel("Draw Distance");
		DrawDistanceLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		DrawDistanceLabel.setBounds(15, 15 + (30 * 1), 150, 25);
		GraphicsSettingsTab.add(DrawDistanceLabel);

		DrawDistanceComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[1]);
		DrawDistanceComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_DrawDistance = DrawDistanceComboBox.getSelectedIndex());
		DrawDistanceComboBox.setBounds(165, 15 + (30 * 1), 175, 25);
		GraphicsSettingsTab.add(DrawDistanceComboBox);

		JLabel ShadowQualityLabel = new JLabel("Shadow Quality");
		ShadowQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShadowQualityLabel.setBounds(15, 15 + (30 * 2), 150, 25);
		GraphicsSettingsTab.add(ShadowQualityLabel);

		ShadowQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[2]);
		ShadowQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_ShadowQuality = ShadowQualityComboBox.getSelectedIndex());
		ShadowQualityComboBox.setEnabled(false);
		ShadowQualityComboBox.setBounds(165, 15 + (30 * 2), 175, 25);
		GraphicsSettingsTab.add(ShadowQualityComboBox);

		JLabel VSyncLabel = new JLabel("VSync");
		VSyncLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		VSyncLabel.setBounds(15, 15 + (30 * 3), 150, 25);
		GraphicsSettingsTab.add(VSyncLabel);

		VSyncComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[3]);
		VSyncComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_VSync = VSyncComboBox.getSelectedIndex() - 1);
		VSyncComboBox.setBounds(165, 15 + (30 * 3), 175, 25);
		GraphicsSettingsTab.add(VSyncComboBox);

		JLabel AntiAliasingModeLabel = new JLabel("Anti-aliasing Mode");
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

		JLabel AntiAliasingQualityLabel = new JLabel("Anti-aliasing Quality");
		AntiAliasingQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AntiAliasingQualityLabel.setBounds(15, 15 + (30 * 5), 150, 25);
		GraphicsSettingsTab.add(AntiAliasingQualityLabel);

		AntiAliasingQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[5]);
		AntiAliasingQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_AntiAliasingQuality = AntiAliasingQualityComboBox.getSelectedIndex());
		AntiAliasingQualityComboBox.setEnabled(false);
		AntiAliasingQualityComboBox.setBounds(165, 15 + (30 * 5), 175, 25);
		GraphicsSettingsTab.add(AntiAliasingQualityComboBox);

		JLabel WaterQualityLabel = new JLabel("Water Quality");
		WaterQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		WaterQualityLabel.setBounds(15, 15 + (30 * 6), 150, 25);
		GraphicsSettingsTab.add(WaterQualityLabel);

		WaterQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[6]);
		WaterQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_WaterQuality = WaterQualityComboBox.getSelectedIndex());
		WaterQualityComboBox.setBounds(165, 15 + (30 * 6), 175, 25);
		GraphicsSettingsTab.add(WaterQualityComboBox);

		JLabel LightingDetailLabel = new JLabel("Lighting Detail");
		LightingDetailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		LightingDetailLabel.setBounds(15, 15 + (30 * 7), 150, 25);
		GraphicsSettingsTab.add(LightingDetailLabel);

		LightingDetailComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[7]);
		LightingDetailComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_LightingQuality = LightingDetailComboBox.getSelectedIndex());
		LightingDetailComboBox.setBounds(165, 15 + (30 * 7), 175, 25);
		GraphicsSettingsTab.add(LightingDetailComboBox);

		JLabel AmbientOcclusionLabel = new JLabel("Ambient Occlusion");
		AmbientOcclusionLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AmbientOcclusionLabel.setToolTipText(Storage.AO_TOOLTIP);
		AmbientOcclusionLabel.setBounds(15, 15 + (30 * 8), 150, 25);
		GraphicsSettingsTab.add(AmbientOcclusionLabel);

		AmbientOcclusionComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[8]);
		AmbientOcclusionComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_AmbientOcclusion = AmbientOcclusionComboBox.getSelectedIndex());
		AmbientOcclusionComboBox.setBounds(165, 15 + (30 * 8), 175, 25);
		AmbientOcclusionComboBox.setToolTipText(Storage.AO_TOOLTIP);
		GraphicsSettingsTab.add(AmbientOcclusionComboBox);

		JLabel BloomQualityLabel = new JLabel("Bloom Quality");
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

		JLabel DepthOfFieldLabel = new JLabel("Unknown Option");
		DepthOfFieldLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		DepthOfFieldLabel.setToolTipText("This Option is Bokehen.");
		DepthOfFieldLabel.setBounds(15, 15 + (30 * 10), 150, 25);
		GraphicsSettingsTab.add(DepthOfFieldLabel);

		DepthOfFieldComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[10]);
		DepthOfFieldComboBox.setToolTipText("This Option is Bokehen.");
		DepthOfFieldComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_DepthOfField = DepthOfFieldComboBox.getSelectedIndex());
		DepthOfFieldComboBox.setEnabled(false);
		DepthOfFieldComboBox.setBounds(165, 15 + (30 * 10), 175, 25);
		GraphicsSettingsTab.add(DepthOfFieldComboBox);

		// Right Column
		JLabel BrightnessLabel = new JLabel("Brightness");
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
		GraphicsSettingsTab.add(BrightnessSlider);

		JLabel TextureQualityLabel = new JLabel("Texture Quality");
		TextureQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		TextureQualityLabel.setBounds(355, 15 + (30 * 1), 150, 25);
		GraphicsSettingsTab.add(TextureQualityLabel);

		TextureQualityComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[11]);
		TextureQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_TextureQuality = TextureQualityComboBox.getSelectedIndex());
		TextureQualityComboBox.setEnabled(false);
		TextureQualityComboBox.setBounds(505, 15 + (30 * 1), 175, 25);
		GraphicsSettingsTab.add(TextureQualityComboBox);

		JLabel AnisotropicFilteringLabel = new JLabel("Anisotropic Filtering");
		AnisotropicFilteringLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AnisotropicFilteringLabel.setBounds(355, 15 + (30 * 2), 150, 25);
		GraphicsSettingsTab.add(AnisotropicFilteringLabel);

		AnisotropicFilteringComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[12]);
		AnisotropicFilteringComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_AnisotropicFiltering = AnisotropicFilteringComboBox.getSelectedIndex());
		AnisotropicFilteringComboBox.setBounds(505, 15 + (30 * 2), 175, 25);
		GraphicsSettingsTab.add(AnisotropicFilteringComboBox);

		JLabel VolumetricLightingLabel = new JLabel("Volumetric Lighting");
		VolumetricLightingLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		VolumetricLightingLabel.setBounds(355, 15 + (30 * 3), 150, 25);
		GraphicsSettingsTab.add(VolumetricLightingLabel);

		VolumetricLightingComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[13]);
		VolumetricLightingComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_VolumetricLighting = VolumetricLightingComboBox.getSelectedIndex());
		VolumetricLightingComboBox.setEnabled(false);
		VolumetricLightingComboBox.setBounds(505, 15 + (30 * 3), 175, 25);
		GraphicsSettingsTab.add(VolumetricLightingComboBox);

		JLabel FlickeringEffectsLabel = new JLabel("Flickering Effects");
		FlickeringEffectsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		FlickeringEffectsLabel.setBounds(355, 15 + (30 * 4), 150, 25);
		GraphicsSettingsTab.add(FlickeringEffectsLabel);

		FlickeringEffectsCheckbox = new JCheckBox();
		FlickeringEffectsCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_FlickeringEffects = FlickeringEffectsCheckbox.isSelected());
		FlickeringEffectsCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		FlickeringEffectsCheckbox.setBounds(665, 15 + (30 * 4) + 5, 15, 15);
		GraphicsSettingsTab.add(FlickeringEffectsCheckbox);

		JLabel ShadowsLabel = new JLabel("Shadows");
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

		JLabel CustomCursorsLabel = new JLabel("Custom Cursors");
		CustomCursorsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		CustomCursorsLabel.setBounds(355, 15 + (30 * 6), 150, 25);
		GraphicsSettingsTab.add(CustomCursorsLabel);

		CustomCursorsCheckbox = new JCheckBox();
		CustomCursorsCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_CustomCursors = CustomCursorsCheckbox.isSelected());
		CustomCursorsCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		CustomCursorsCheckbox.setBounds(665, 15 + (30 * 6) + 5, 15, 15);
		CustomCursorsCheckbox.setSelected(true);
		GraphicsSettingsTab.add(CustomCursorsCheckbox);

		JLabel LoadingScreensLabel = new JLabel("Loading Screens");
		LoadingScreensLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		LoadingScreensLabel.setBounds(355, 15 + (30 * 7), 150, 25);
		GraphicsSettingsTab.add(LoadingScreensLabel);

		LoadingScreensCheckbox = new JCheckBox();
		LoadingScreensCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_LoadingScreens = LoadingScreensCheckbox.isSelected());
		LoadingScreensCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		LoadingScreensCheckbox.setBounds(665, 15 + (30 * 7) + 5, 15, 15);
		LoadingScreensCheckbox.setSelected(true);
		GraphicsSettingsTab.add(LoadingScreensCheckbox);

		JLabel GroundDecorationsLabel = new JLabel("Ground Decorations");
		GroundDecorationsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GroundDecorationsLabel.setBounds(355, 15 + (30 * 8), 150, 25);
		GraphicsSettingsTab.add(GroundDecorationsLabel);

		GroundDecorationsCheckbox = new JCheckBox();
		GroundDecorationsCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_GroundDecor = GroundDecorationsCheckbox.isSelected());
		GroundDecorationsCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		GroundDecorationsCheckbox.setBounds(665, 15 + (30 * 8) + 5, 15, 15);
		GraphicsSettingsTab.add(GroundDecorationsCheckbox);

		JLabel TerrainBlendingLabel = new JLabel("Terrain Blending");
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

		JLabel HeatHazeLabel = new JLabel("Unknown Option");
		HeatHazeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		HeatHazeLabel.setToolTipText("This Option is in a mild haze.");
		HeatHazeLabel.setBounds(355, 15 + (30 * 10), 150, 25);
		GraphicsSettingsTab.add(HeatHazeLabel);

		HeatHazeComboBox = new JComboBox < Object > (Storage.GRAPHICS_OPTIONS[14]);
		HeatHazeComboBox.setToolTipText("This Option is in a mild haze.");
		HeatHazeComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_HeatHaze = HeatHazeComboBox.getSelectedIndex());
		HeatHazeComboBox.setEnabled(false);
		HeatHazeComboBox.setBounds(505, 15 + (30 * 10), 175, 25);
		GraphicsSettingsTab.add(HeatHazeComboBox);

		JPanel ClientSettingsTab = new JPanel();
		tabbedPane.addTab("Client Settings", null, ClientSettingsTab, "Edit your client settings here.");
		ClientSettingsTab.setBackground(Color.BLACK);
		ClientSettingsTab.setLayout(null);

		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		UsernameLabel.setBounds(15, 15, 150, 25);
		ClientSettingsTab.add(UsernameLabel);

		UsernameInput = new JTextField();
		UsernameInput.setToolTipText(
				"<html>This will be the display username when loading NXT.<br>"+
				"Currently this field allows Jagex's Colour+Sprite flags to be used.<br>" +
				"<br>"+
				"NOTE: When using sprite+colour flags it will still read is-if you types them here<br>" +
				"Graphically it may be a login-able name, but it will have invalid characters/login names.<br>"+
				"<br>"+
				"Some HTML elements like &lt;br&gt;, &lt;b&gt;, and &lt;i&gt; can be used as well.</html>"
				);
		UsernameInput.setBounds(170, 15, 535, 25);
		ClientSettingsTab.add(UsernameInput);
		UsernameInput.setColumns(10);

		NumberFormat intFormat = NumberFormat.getIntegerInstance();
		NumberFormatter WorldNumberFormatter = new NumberFormatter(intFormat);
		WorldNumberFormatter.setValueClass(Integer.class);
		WorldNumberFormatter.setAllowsInvalid(false);
		WorldNumberFormatter.setMinimum( - 1);
		WorldNumberFormatter.setMaximum(255);
		NumberFormatter WallpaperNumberFormatter = new NumberFormatter(intFormat);
		WallpaperNumberFormatter.setValueClass(Integer.class);
		WallpaperNumberFormatter.setAllowsInvalid(false);
		WallpaperNumberFormatter.setMinimum(0);

		JLabel FavouriteWorld1Label = new JLabel("Favourite world 1");
		FavouriteWorld1Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		FavouriteWorld1Label.setBounds(15, 45, 130, 25);
		ClientSettingsTab.add(FavouriteWorld1Label);

		FavouriteWorld1Input = new JFormattedTextField(WorldNumberFormatter);
		FavouriteWorld1Input.setToolTipText(
				"<html>To clear this slot use the value -1.<br>"+
				"If Slots 1 or 2 have -1 as their value, any later numbers will be disabled.<br>" +
				"<br>"+
				"The removal is currently NOT a NXT limitation, but it causes graphical issues.</html>"
				);
		FavouriteWorld1Input.setText("-1");
		FavouriteWorld1Input.setDocument(new TextLimitor(4));
		FavouriteWorld1Input.setBounds(170, 45, 85, 25);
		ClientSettingsTab.add(FavouriteWorld1Input);

		JLabel FavouriteWorld2Label = new JLabel("Favourite world 2");
		FavouriteWorld2Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		FavouriteWorld2Label.setBounds(260, 45, 130, 25);
		ClientSettingsTab.add(FavouriteWorld2Label);

		FavouriteWorld2Input = new JFormattedTextField(WorldNumberFormatter);
		FavouriteWorld2Input.setToolTipText(
				"<html>To clear this slot use the value -1.<br>"+
				"If Slots 1 or 2 have -1 as their value, any later numbers will be disabled.<br>" +
				"<br>"+
				"The removal is currently NOT a NXT limitation, but it causes graphical issues.</html>"
				);
		FavouriteWorld2Input.setText("-1");
		FavouriteWorld2Input.setDocument(new TextLimitor(4));
		FavouriteWorld2Input.setBounds(395, 45, 85, 25);
		ClientSettingsTab.add(FavouriteWorld2Input);

		JLabel FavouriteWorld3Label = new JLabel("Favourite world 3");
		FavouriteWorld3Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		FavouriteWorld3Label.setBounds(485, 45, 130, 25);
		ClientSettingsTab.add(FavouriteWorld3Label);

		FavouriteWorld3Input = new JFormattedTextField(WorldNumberFormatter);
		FavouriteWorld3Input.setToolTipText(
				"<html>To clear this slot use the value -1.<br>" +
				"If Slots 1 or 2 have -1 as their value any later numbers will be disabled.<br>" +
				"<br>" +
				"The removal is currently NOT a NXT limitation, but it causes graphical issues.</html>"
				);
		FavouriteWorld3Input.setText("-1");
		FavouriteWorld3Input.setDocument(new TextLimitor(4));
		FavouriteWorld3Input.setBounds(620, 45, 85, 25);
		ClientSettingsTab.add(FavouriteWorld3Input);

		GlobalAudioMuteCheckbox = new JCheckBox("Global Audio Mute?");
		GlobalAudioMuteCheckbox.addActionListener(e -> Storage.nxtClientSettings_GlobalMute = GlobalAudioMuteCheckbox.isSelected());
		GlobalAudioMuteCheckbox.setToolTipText(
				"<html>Disables the audio streams WHILE LOGGED IN<br>" +
				"Other volume options should remain uneffected.</html>"
				);
		GlobalAudioMuteCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		GlobalAudioMuteCheckbox.setBounds(15, 75, 150, 25);
		ClientSettingsTab.add(GlobalAudioMuteCheckbox);

		RememberUsernameCheckbox = new JCheckBox("Remember Saved Username?");
		RememberUsernameCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		RememberUsernameCheckbox.addActionListener(e -> Storage.nxtClientSettings_RememberUsername = RememberUsernameCheckbox.isSelected());

		RememberUsernameCheckbox.setToolTipText(
				"<html>Redundant feature for here;<br>" +
				"if unchecked NXT will clear the username on the next client load.</html>"
				);
		RememberUsernameCheckbox.setBounds(170, 75, 220, 25);
		ClientSettingsTab.add(RememberUsernameCheckbox);

		RandomizeLoginWallpaperCheckbox = new JCheckBox("Randomize Wallpaper? Use ID:");
		RandomizeLoginWallpaperCheckbox.addActionListener(e -> Storage.nxtClientSettings_RandomizeLoginWallpaper = RandomizeLoginWallpaperCheckbox.isSelected());
		RandomizeLoginWallpaperCheckbox.setToolTipText(
				"<html>Randomize your login screen's wallpaper.<br>" +
				"<br>" +
				"NOTE: The FIRST frame of this will be the ID input to the Right<br>" +
				"If this option is unchecked, that ID's wallpaper will be used.</html>"
				);
		RandomizeLoginWallpaperCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		RandomizeLoginWallpaperCheckbox.setBounds(395, 75, 220, 25);
		ClientSettingsTab.add(RandomizeLoginWallpaperCheckbox);

		WallpaperIDInput = new JFormattedTextField(WallpaperNumberFormatter);
		WallpaperIDInput.setText("0");
		WallpaperIDInput.setToolTipText(
				"<html>Input your desired wallpaper ID.<br>" +
				"<br>" +
				"NOTE: This will apply to the FIRST wallpaper in randomizing.<br>" +
				"This will also be the Static ID if randomizing if OFF.<br>" +
				"<br>" +
				"ID: 0 = Default.</html>"
				);
		WallpaperIDInput.setBounds(620, 75, 85, 25);
		FavouriteWorld2Input.setDocument(new TextLimitor(2));
		ClientSettingsTab.add(WallpaperIDInput);

		JLabel InGameMusicLabel = new JLabel("Game Music Volume");
		InGameMusicLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameMusicLabel.setBounds(15, 105, 150, 25);
		ClientSettingsTab.add(InGameMusicLabel);

		InGameMusicSlider = new JSlider();
		InGameMusicSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameMusicVolume = InGameMusicSlider.getValue());
		InGameMusicSlider.setMaximum(127);
		InGameMusicSlider.setValue(127);
		InGameMusicSlider.setPaintTicks(true);
		InGameMusicSlider.setBounds(170, 105, 445, 25);
		InGameMusicSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(InGameMusicSlider);

		JCheckBox InGameMusicBoostCheckbox = new JCheckBox("Boost?");
		InGameMusicBoostCheckbox.addActionListener(e -> {
			if (InGameMusicBoostCheckbox.isSelected()) {
				InGameMusicSlider.setMaximum(255);
			} else {
				InGameMusicSlider.setMaximum(127);
			}
		});
		InGameMusicBoostCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameMusicBoostCheckbox.setBounds(620, 105, 85, 25);
		ClientSettingsTab.add(InGameMusicBoostCheckbox);

		JLabel InGameSoundEffectsLabel = new JLabel("Sound Effect Volume");
		InGameSoundEffectsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameSoundEffectsLabel.setBounds(15, 135, 150, 25);
		ClientSettingsTab.add(InGameSoundEffectsLabel);

		InGameSoundEffectsSlider = new JSlider();
		InGameSoundEffectsSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameSoundEffectsVolume = InGameSoundEffectsSlider.getValue());
		InGameSoundEffectsSlider.setMaximum(127);
		InGameSoundEffectsSlider.setValue(127);
		InGameSoundEffectsSlider.setPaintTicks(true);
		InGameSoundEffectsSlider.setBounds(170, 135, 445, 25);
		InGameSoundEffectsSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(InGameSoundEffectsSlider);

		JCheckBox InGameSoundEffectsBoostCheckbox = new JCheckBox("Boost?");
		InGameSoundEffectsBoostCheckbox.addActionListener(e -> {
			if (InGameSoundEffectsBoostCheckbox.isSelected()) {
				InGameSoundEffectsSlider.setMaximum(255);
			} else {
				InGameSoundEffectsSlider.setMaximum(127);
			}
		});
		InGameSoundEffectsBoostCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameSoundEffectsBoostCheckbox.setBounds(620, 135, 85, 25);
		ClientSettingsTab.add(InGameSoundEffectsBoostCheckbox);

		JLabel InGameAmbientSoundEffectsLabel = new JLabel("Ambient Sound Volume");
		InGameAmbientSoundEffectsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameAmbientSoundEffectsLabel.setBounds(15, 165, 150, 25);
		ClientSettingsTab.add(InGameAmbientSoundEffectsLabel);

		InGameAmbientSoundEffectsSlider = new JSlider();
		InGameAmbientSoundEffectsSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume = InGameAmbientSoundEffectsSlider.getValue());
		InGameAmbientSoundEffectsSlider.setMaximum(127);
		InGameAmbientSoundEffectsSlider.setValue(127);
		InGameAmbientSoundEffectsSlider.setPaintTicks(true);
		InGameAmbientSoundEffectsSlider.setBounds(170, 165, 445, 25);
		InGameAmbientSoundEffectsSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(InGameAmbientSoundEffectsSlider);

		JCheckBox InGameAmbientSoundEffectsBoostCheckbox = new JCheckBox("Boost?");
		InGameAmbientSoundEffectsBoostCheckbox.addActionListener(e -> {
			if (InGameAmbientSoundEffectsBoostCheckbox.isSelected()) {
				InGameAmbientSoundEffectsSlider.setMaximum(255);
			} else {
				InGameAmbientSoundEffectsSlider.setMaximum(127);
			}
		});
		InGameAmbientSoundEffectsBoostCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameAmbientSoundEffectsBoostCheckbox.setBounds(620, 165, 85, 25);
		ClientSettingsTab.add(InGameAmbientSoundEffectsBoostCheckbox);

		JLabel InGameVoiceOverLabel = new JLabel("Voice Over Volume");
		InGameVoiceOverLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameVoiceOverLabel.setBounds(15, 195, 150, 25);
		ClientSettingsTab.add(InGameVoiceOverLabel);

		InGameVoiceOverSlider = new JSlider();
		InGameVoiceOverSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameVoiceOverVolume = InGameVoiceOverSlider.getValue());
		InGameVoiceOverSlider.setMaximum(127);
		InGameVoiceOverSlider.setValue(127);
		InGameVoiceOverSlider.setPaintTicks(true);
		InGameVoiceOverSlider.setBounds(170, 195, 445, 25);
		InGameVoiceOverSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(InGameVoiceOverSlider);

		JCheckBox InGameVoiceOverBoostCheckbox = new JCheckBox("Boost?");
		InGameVoiceOverBoostCheckbox.addActionListener(e -> {
			if (InGameVoiceOverBoostCheckbox.isSelected()) {
				InGameVoiceOverSlider.setMaximum(255);
			} else {
				InGameVoiceOverSlider.setMaximum(127);
			}
		});
		InGameVoiceOverBoostCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameVoiceOverBoostCheckbox.setBounds(620, 195, 85, 25);
		ClientSettingsTab.add(InGameVoiceOverBoostCheckbox);

		JLabel LobbyMusicLabel = new JLabel("Login Music Volume");
		LobbyMusicLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		LobbyMusicLabel.setBounds(15, 232, 150, 25);
		ClientSettingsTab.add(LobbyMusicLabel);

		LoginMusicSlider = new JSlider();
		LoginMusicSlider.addChangeListener(e -> Storage.nxtClientSettings_LoginMusicVolume = LoginMusicSlider.getValue());
		LoginMusicSlider.setMaximum(254);
		LoginMusicSlider.setValue(254);
		LoginMusicSlider.setPaintTicks(true);
		LoginMusicSlider.setBounds(170, 232, 535, 25);
		LoginMusicSlider.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
		ClientSettingsTab.add(LoginMusicSlider);

		JPanel SpecialMechanicsTab = new JPanel();
		tabbedPane.addTab("Special Mechanics", null, SpecialMechanicsTab, "Add special mechanics here. (More details inside)");
		SpecialMechanicsTab.setBackground(Color.BLACK);
		SpecialMechanicsTab.setLayout(null);

		AddSpriteFlagToUsername = new JButton("Add sprite flag to Username");
		AddSpriteFlagToUsername.setToolTipText(
				"<html>Adds the &lt;sprite=#,#&gt; flag to your username field.<br>" +
				"This flag is that Jagex uses to add sprites to the chatbox e.g. staffmodlevel Crowns.</html>"
				);
		AddSpriteFlagToUsername.addActionListener(e -> {
			if (UsernameInput != null) {
				UsernameInput.setText(UsernameInput.getText() + "<sprite=SPRITE_ID,SPRITE_SUB_ID>");
			}
		});
		AddSpriteFlagToUsername.setFont(new Font("Dialog", Font.PLAIN, 11));
		AddSpriteFlagToUsername.setBounds(15, 15, 225, 25);
		SpecialMechanicsTab.add(AddSpriteFlagToUsername);

		AddColourFlagToUsername = new JButton("Add colour flag to Username");
		AddColourFlagToUsername.setToolTipText(
				"<html>Adds the &lt;col=RRGGBB&gt; flag to your username field.<br>" +
				"This flag is used by Jagex to change the colours of text full or mid-sentence e.g: Green warnings.<br>" +
				"As far as I've known, this does NOT support alpha. Keep it RRGGBB where possible.</html>"
				);
		AddColourFlagToUsername.addActionListener(e ->{
			if (UsernameInput != null) {
				UsernameInput.setText(UsernameInput.getText() + "<col=ffffff></col>");
			}
		});
		AddColourFlagToUsername.setFont(new Font("Dialog", Font.PLAIN, 11));
		AddColourFlagToUsername.setBounds(245, 15, 225, 25);
		SpecialMechanicsTab.add(AddColourFlagToUsername);

		ClearConsole = new JButton("Clear Developer Console Log");
		ClearConsole.setToolTipText(
				"<html>This will clear the Developer Console's command history logs.<br>" +
				"<br>" +
				"Note: Using this will instantly Write to your client's settings.<br>" +
				"This may clear any previous values!</html>"
				);
		ClearConsole.setEnabled(false);
		ClearConsole.setFont(new Font("Dialog", Font.PLAIN, 11));
		ClearConsole.addActionListener(e -> {
			Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:" + Settings.Cache_settings_location);
				Statement stmt;
				stmt = conn.createStatement();
				stmt.addBatch("DELETE FROM \"console\";");@SuppressWarnings("unused")
				int[] Updates = stmt.executeBatch();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		});
		ClearConsole.setBounds(475, 15, 225, 25);
		SpecialMechanicsTab.add(ClearConsole);

		FavouriteWorld1To2147m = new JButton("Set favourite world 1 to 2147m");
		FavouriteWorld1To2147m.setToolTipText(
				"<html>This will set your first favourite world slot to: 2,147,000,000;<br>" +
				"<br>" +
				"Note: Using this will instantly Write to your client's settings.<br>" +
				"This may clear any previous values!</html>"
				);
		FavouriteWorld1To2147m.setEnabled(false);
		FavouriteWorld1To2147m.addActionListener(e -> {
			Storage.nxtClientSettings_FavouriteWorld1 = 2147000000;
			Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:" + Settings.Cache_settings_location);
				Statement stmt;
				stmt = conn.createStatement();
				stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"" + Storage.nxtClientSettings_FavouriteWorld1 + "\" WHERE KEY=\"998\";");@SuppressWarnings("unused")
				int[] Updates = stmt.executeBatch();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		});
		FavouriteWorld1To2147m.setFont(new Font("Dialog", Font.PLAIN, 11));
		FavouriteWorld1To2147m.setBounds(15, 45, 225, 25);
		SpecialMechanicsTab.add(FavouriteWorld1To2147m);

		FavouriteWorld2To2147m = new JButton("Set favourite world 2 to 2147m");
		FavouriteWorld2To2147m.setToolTipText(
				"<html>This will set your second favourite world slot to: 2,147,000,000;<br>" +
				"<br>" +
				"Note: Using this will instantly Write to your client's settings.<br>" +
				"This may clear any previous values!</html>"
				);
		FavouriteWorld2To2147m.setEnabled(false);
		FavouriteWorld2To2147m.addActionListener(e -> {
			Storage.nxtClientSettings_FavouriteWorld2 = 2147000000;
			Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:" + Settings.Cache_settings_location);
				Statement stmt;
				stmt = conn.createStatement();
				stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"" + Storage.nxtClientSettings_FavouriteWorld2 + "\" WHERE KEY=\"999\";");@SuppressWarnings("unused")
				int[] Updates = stmt.executeBatch();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		});
		FavouriteWorld2To2147m.setFont(new Font("Dialog", Font.PLAIN, 11));
		FavouriteWorld2To2147m.setBounds(245, 45, 225, 25);
		SpecialMechanicsTab.add(FavouriteWorld2To2147m);

		FavouriteWorld3To2147m = new JButton("Set favourite world 3 to 2147m");
		FavouriteWorld3To2147m.setToolTipText(
				"<html>This will set your third favourite world slot to: 2,147,000,000;<br>" +
				"<br>" +
				"Note: Using this will instantly Write to your client's settings.<br>" +
				"This may clear any previous values!</html>"
				);
		FavouriteWorld3To2147m.setEnabled(false);
		FavouriteWorld3To2147m.addActionListener(e -> {
			Storage.nxtClientSettings_FavouriteWorld3 = 2147000000;
			Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:" + Settings.Cache_settings_location);
				Statement stmt;
				stmt = conn.createStatement();
				stmt.addBatch("UPDATE \"vt-varc\" SET DATA=\"" + Storage.nxtClientSettings_FavouriteWorld3 + "\" WHERE KEY=\"4272\";");@SuppressWarnings("unused")
				int[] Updates = stmt.executeBatch();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		});
		FavouriteWorld3To2147m.setFont(new Font("Dialog", Font.PLAIN, 11));
		FavouriteWorld3To2147m.setBounds(475, 45, 225, 25);
		SpecialMechanicsTab.add(FavouriteWorld3To2147m);

		JScrollPane VerboseOutputTab = new JScrollPane();
		VerboseOutputTab.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		VerboseOutputTab.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tabbedPane.addTab("Read-Only Information", null, VerboseOutputTab, "Read the Verbose information as it occurs.");

		VerboseOutputArea = new JEditorPane();
		VerboseOutputArea.setContentType("text/html");
		VerboseOutputArea.setText("<html></html>");
		VerboseOutputArea.setEditable(false);
		VerboseOutputArea.setBackground(Color.BLACK);
		VerboseOutputAreaEditor = new HTMLEditorKit();
		VerboseOutputArea.setEditorKit(VerboseOutputAreaEditor);
		VerboseOutputTab.setViewportView(VerboseOutputArea);

		ShowSensitiveInformation = new JCheckBox("Show Sensitive Information?");
		ShowSensitiveInformation.setToolTipText("Show information such as: Your Username, UID, etc.");
		ShowSensitiveInformation.setBounds(15, 610, 230, 25);
		contentPane.add(ShowSensitiveInformation);

		btnRead = new JButton("Read");
		btnRead.setToolTipText("Read information currently saved in your setting file(s); Writes to Read-Only Information output.");
		btnRead.addActionListener(e -> {
			if ((VerboseOutputArea != null) && (VerboseOutputAreaEditor != null)) {
				VerboseOutputArea.setText("<html></html>");
				UsernameInput.setText("");
			}
			Settings.TestRead();
		});
		btnRead.setBounds(582, 610, 75, 25);
		contentPane.add(btnRead);

		btnWrite = new JButton("Write");
		btnWrite.setBounds(662, 610, 75, 25);
		btnWrite.setEnabled(false);
		btnWrite.addActionListener(e -> Settings.TestWrite());
		contentPane.add(btnWrite);

		JCheckBox AllowWritingCheckbox = new JCheckBox("Allow Writing?");
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
		contentPane.add(AllowWritingCheckbox);
		Settings.TestRead();
	}
}