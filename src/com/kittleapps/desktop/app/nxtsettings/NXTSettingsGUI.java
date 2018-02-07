package com.kittleapps.desktop.app.nxtsettings;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import javax.swing.table.DefaultTableModel;

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
	HoverOverTextCheckbox,
	LoopCurrentMusicTrackCheckbox,
	InGameMouseOverPopupsCheckbox,
	InGameTaskPopupsCheckbox,
	MinimizeMainAbilityBarCheckBox,
	CompatibilityModeCheckBox,
	CompatibilityModeOnErrorCheckBox,
	AskBeforeQuittingCheckBox,
	ShowSensitiveInformation,
	AllowWritingCheckbox,
	GraphicsPresets;

	public static JComboBox<Object>
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
	ScreenSizingModeComboBox,
	UIScalingComboBox,
	GameWorldScalingComboBox,
	LoginWallpaperIDComboBox,
	InGameWorldSortingComboBox,
	FavouriteWorld1ComboBox,
	FavouriteWorld2ComboBox,
	FavouriteWorld3ComboBox,
	InGameMusicSortingComboBox,
	InGameEmoteSortingComboBox,
	WorldMapOverlayComboBox,
	LanguageSelectionComboBox;

	public static JSlider
	BrightnessSlider,
	LoginMusicSlider,
	InGameMusicSlider,
	InGameSoundEffectsSlider,
	InGameAmbientSoundEffectsSlider,
	InGameVoiceOverSlider,
	InGameCameraZoomSlider,
	InGameKeyboardHSensitivitySlider,
	InGameKeyboardVSensitivitySlider,
	InGameMouseHSensitivitySlider,
	InGameMouseVSensitivitySlider,
	OoOMovementSpeedSlider,
	OoORotationSpeedSlider,
	FriendsListDividerSlider,
	FriendsChatListDividerSlider,
	ClanChatListDividerSlider,
	GuestClanChatListDividerSlider;

	public static JTextField
	UsernameInput;

	public static JButton
	MinimumGraphicsPresetButton,
	LowGraphicsPresetButton,
	MediumGraphicsPresetButton,
	HighGraphicsPresetButton,
	UltraGraphicsPresetButton,
	MaxedGraphicsPresetButton,
	WikianGraphicsPresetButton,
	RedditGraphicsPresetButton,
	WorldMapIconSelectionButton,
	AddSpriteFlagToUsername,
	AddColourFlagToUsername,
	GrabInternalBuildLabel,
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
				Storage.init();
				Mechanics.CheckWorldMapIcons();
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
		frame.setTitle("NXT's Settings");

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

		final JMenuItem FileMenuAlwaysOnTop = new JMenuItem("Toggle Always-On-Top");
		FileMenuAlwaysOnTop.addActionListener(e -> {
			if (!Storage.DEVELOPER_WindowAlwaysOnTop){
				if (frame.isAlwaysOnTop()) {
					frame.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Always-on-top is now disabled.");
				}	else {
					frame.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "Always-on-top is now enabled.");
				}
			}	else {
				frame.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(NXTSettingsGUI.frame, "The program option 'DEVELOPER_ALWAYS_STAY_ON_TOP' is currently enabled.\n\n" +
						"You will not be able to toggle Always-on-Top at this time.");
			}
		});
		FileMenu.add(FileMenuAlwaysOnTop);
		final JMenuItem FileMenuExit = new JMenuItem("Exit");
		FileMenuExit.addActionListener(e -> System.exit(0));
		FileMenu.add(FileMenuExit);

		final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setBounds(15, 26, 720, 569);
		contentPane.add(tabbedPane);

		final JPanel GraphicsSettingsTab = new JPanel();
		tabbedPane.addTab("Graphics Settings", null, GraphicsSettingsTab, null);
		GraphicsSettingsTab.setBackground(backgroundColour);
		GraphicsSettingsTab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GraphicsSettingsTab.setLayout(null);

		final JPanel ClientSettingsTab = new JPanel();
		tabbedPane.addTab("Client Settings", null, ClientSettingsTab, null);
		ClientSettingsTab.setBackground(backgroundColour);
		ClientSettingsTab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ClientSettingsTab.setLayout(null);

		final JPanel ControlSettingsTab = new JPanel();
		tabbedPane.addTab("Control Settings", null, ControlSettingsTab, null);
		ControlSettingsTab.setBackground(backgroundColour);
		ControlSettingsTab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ControlSettingsTab.setLayout(null);

		final JPanel AudioSettingsTab = new JPanel();
		tabbedPane.addTab("Audio Settings", null, AudioSettingsTab, null);
		AudioSettingsTab.setBackground(backgroundColour);
		AudioSettingsTab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		AudioSettingsTab.setLayout(null);

		final JPanel SpecialMechanicsTab = new JPanel();
		tabbedPane.addTab("Special Mechanics", null, SpecialMechanicsTab, null);
		SpecialMechanicsTab.setBackground(backgroundColour);
		SpecialMechanicsTab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		SpecialMechanicsTab.setLayout(null);

		final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		final Image DefaultCursorImg = Toolkit.getDefaultToolkit().getImage(classloader.getResource("Default_cursor.png")),
				HResizeCursorImg = Toolkit.getDefaultToolkit().getImage(classloader.getResource("Resize_(horizontal)_cursor.png")),
				VResizeCursorImg = Toolkit.getDefaultToolkit().getImage(classloader.getResource("Resize_(vertical)_cursor.png"));
		Cursor DefaultCursor = Cursor.getDefaultCursor(), HResizeCursor = Cursor.getDefaultCursor(), VResizeCursor = Cursor.getDefaultCursor();
		final Point DefaultCursorHotspot = new Point(0, 0);
		final Point ResizeCursorHotspot = new Point(15, 15);
		DefaultCursor = Toolkit.getDefaultToolkit().createCustomCursor(DefaultCursorImg, DefaultCursorHotspot, "DefaultCursor");
		HResizeCursor = Toolkit.getDefaultToolkit().createCustomCursor(HResizeCursorImg, ResizeCursorHotspot, "HResizeCursor");
		VResizeCursor = Toolkit.getDefaultToolkit().createCustomCursor(VResizeCursorImg, ResizeCursorHotspot, "VResizeCursor");
		this.setCursor(DefaultCursor);

		/* Left Graphics Settings */



		final JLabel BrightnessLabel = new JLabel("  Brightness");
		BrightnessLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		BrightnessLabel.setBounds(15, GetRow(0), 150, 25);
		GraphicsSettingsTab.add(BrightnessLabel);

		BrightnessSlider = new JSlider();
		BrightnessLabel.setLabelFor(BrightnessSlider);
		BrightnessSlider.addChangeListener(e -> Storage.nxtGraphicsSetting_Brightness = BrightnessSlider.getValue());
		BrightnessSlider.setSnapToTicks(true);
		BrightnessSlider.setMaximum(4);
		BrightnessSlider.setPaintTicks(true);
		BrightnessSlider.setBounds(165, GetRow(0), 175, 25);
		BrightnessSlider.setCursor(HResizeCursor);
		BrightnessSlider.setBackground(optionBackgroundColor);
		GraphicsSettingsTab.add(BrightnessSlider);

		final JLabel DrawDistanceLabel = new JLabel("  Draw Distance");
		DrawDistanceLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		DrawDistanceLabel.setBounds(15, GetRow(1), 150, 25);
		GraphicsSettingsTab.add(DrawDistanceLabel);

		DrawDistanceComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[1]);
		DrawDistanceComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		DrawDistanceLabel.setLabelFor(DrawDistanceComboBox);
		DrawDistanceComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_DrawDistance = DrawDistanceComboBox.getSelectedIndex());
		DrawDistanceComboBox.setBounds(165, GetRow(1), 175, 25);
		GraphicsSettingsTab.add(DrawDistanceComboBox);

		final JLabel VSyncLabel = new JLabel("  VSync");
		VSyncLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		VSyncLabel.setBounds(15, GetRow(2), 150, 25);
		GraphicsSettingsTab.add(VSyncLabel);

		VSyncComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[3]);
		VSyncComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		VSyncLabel.setLabelFor(VSyncComboBox);
		VSyncComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_VSync = VSyncComboBox.getSelectedIndex()-1);
		VSyncComboBox.setBounds(165, GetRow(2), 175, 25);
		GraphicsSettingsTab.add(VSyncComboBox);

		final JLabel AntiAliasingQualityLabel = new JLabel("  Anti-Aliasing Quality");
		AntiAliasingQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AntiAliasingQualityLabel.setBounds(15, GetRow(3), 150, 25);
		GraphicsSettingsTab.add(AntiAliasingQualityLabel);

		AntiAliasingQualityComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[5]);
		AntiAliasingQualityComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		AntiAliasingQualityLabel.setLabelFor(AntiAliasingQualityComboBox);
		AntiAliasingQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_AntiAliasingQuality = AntiAliasingQualityComboBox.getSelectedIndex());
		AntiAliasingQualityComboBox.setEnabled(false);
		AntiAliasingQualityComboBox.setBounds(165, GetRow(3), 175, 25);
		GraphicsSettingsTab.add(AntiAliasingQualityComboBox);

		final JLabel LightingDetailLabel = new JLabel("  Lighting Quality");
		LightingDetailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		LightingDetailLabel.setBounds(15, GetRow(4), 150, 25);
		GraphicsSettingsTab.add(LightingDetailLabel);

		LightingDetailComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[7]);
		LightingDetailComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		LightingDetailLabel.setLabelFor(LightingDetailComboBox);
		LightingDetailComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_LightingQuality = LightingDetailComboBox.getSelectedIndex());
		LightingDetailComboBox.setBounds(165, GetRow(4), 175, 25);
		GraphicsSettingsTab.add(LightingDetailComboBox);

		final JLabel BloomQualityLabel = new JLabel("  Bloom Quality");
		BloomQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		BloomQualityLabel.setBounds(15, GetRow(5), 150, 25);
		GraphicsSettingsTab.add(BloomQualityLabel);

		BloomQualityComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[9]);
		BloomQualityComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		BloomQualityLabel.setLabelFor(BloomQualityComboBox);
		BloomQualityComboBox.addItemListener(e -> {
			Storage.nxtGraphicsSetting_Bloom = BloomQualityComboBox.getSelectedIndex();
			Legality.CheckSettings();
		});
		BloomQualityComboBox.setBounds(165, GetRow(5), 175, 25);
		GraphicsSettingsTab.add(BloomQualityComboBox);

		final JLabel AnisotropicFilteringLabel = new JLabel("  Anisotropic Filtering");
		AnisotropicFilteringLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AnisotropicFilteringLabel.setBounds(15, GetRow(6), 150, 25);
		GraphicsSettingsTab.add(AnisotropicFilteringLabel);

		AnisotropicFilteringComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[12]);
		AnisotropicFilteringComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		AnisotropicFilteringLabel.setLabelFor(AnisotropicFilteringComboBox);
		AnisotropicFilteringComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_AnisotropicFiltering = AnisotropicFilteringComboBox.getSelectedIndex());
		AnisotropicFilteringComboBox.setBounds(165, GetRow(6), 175, 25);
		GraphicsSettingsTab.add(AnisotropicFilteringComboBox);

		final JLabel MaxForegroundFpsLabel = new JLabel("  Foreground FPS Cap");
		MaxForegroundFpsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		MaxForegroundFpsLabel.setToolTipText(Storage.MAXFOREGOUNDFPS_TOOLTIP);
		MaxForegroundFpsLabel.setBounds(15, GetRow(7), 150, 25);
		GraphicsSettingsTab.add(MaxForegroundFpsLabel);

		MaxForegroundFpsComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[14]);
		MaxForegroundFpsComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		MaxForegroundFpsLabel.setLabelFor(MaxForegroundFpsComboBox);
		MaxForegroundFpsComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_MaxForegroundFps = ((MaxForegroundFpsComboBox.getSelectedIndex()+1)*5));
		MaxForegroundFpsComboBox.setBounds(165, GetRow(7), 175, 25);
		GraphicsSettingsTab.add(MaxForegroundFpsComboBox);

		final JLabel UIScalingLabel = new JLabel("  Interface Scaling");
		UIScalingLabel.setToolTipText(Storage.INTERFACESCALING_TOOLTIP);
		UIScalingLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		UIScalingLabel.setBounds(15, GetRow(8), 150, 25);
		GraphicsSettingsTab.add(UIScalingLabel);

		UIScalingComboBox = new JComboBox<>(Storage.SETTINGS_OPTIONS[15]);
		UIScalingComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		UIScalingComboBox.addItemListener(e -> Storage.nxtClientSettings_UIScaling = ((UIScalingComboBox.getSelectedIndex()*5)+100));
		UIScalingComboBox.setBounds(165, GetRow(8), 175, 25);
		GraphicsSettingsTab.add(UIScalingComboBox);

		final JLabel DepthOfFieldLabel = new JLabel("  Depth of Field");
		DepthOfFieldLabel.setToolTipText(Storage.DOF_TOOLTIP);
		DepthOfFieldLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		DepthOfFieldLabel.setBounds(15, GetRow(9), 150, 25);
		GraphicsSettingsTab.add(DepthOfFieldLabel);

		DepthOfFieldComboBox = new JComboBox<>(Storage.SETTINGS_OPTIONS[10]);
		DepthOfFieldComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		DepthOfFieldComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_DepthOfField = ((DepthOfFieldComboBox.getSelectedIndex()*5)+35));
		DepthOfFieldComboBox.setBounds(165, GetRow(9), 175, 25);
		GraphicsSettingsTab.add(DepthOfFieldComboBox);

		FlickeringEffectsCheckbox = new JCheckBox(String.format("%-85s", "Flickering Effects"));
		FlickeringEffectsCheckbox.setBackground(optionBackgroundColor);
		FlickeringEffectsCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		FlickeringEffectsCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_FlickeringEffects = FlickeringEffectsCheckbox.isSelected());
		FlickeringEffectsCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		FlickeringEffectsCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		FlickeringEffectsCheckbox.setBounds(15, GetRow(10), 325, 25);
		GraphicsSettingsTab.add(FlickeringEffectsCheckbox);

		CustomCursorsCheckbox = new JCheckBox(String.format("%-81s", "Custom Cursors"));
		CustomCursorsCheckbox.setBackground(optionBackgroundColor);
		CustomCursorsCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		CustomCursorsCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_CustomCursors = CustomCursorsCheckbox.isSelected());
		CustomCursorsCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		CustomCursorsCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		CustomCursorsCheckbox.setBounds(15, GetRow(11), 325, 25);
		CustomCursorsCheckbox.setSelected(true);
		GraphicsSettingsTab.add(CustomCursorsCheckbox);

		GroundDecorationsCheckbox = new JCheckBox(String.format("%-79s", "Ground Decorations"));
		GroundDecorationsCheckbox.setBackground(optionBackgroundColor);
		GroundDecorationsCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		GroundDecorationsCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_GroundDecor = GroundDecorationsCheckbox.isSelected());
		GroundDecorationsCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		GroundDecorationsCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		GroundDecorationsCheckbox.setBounds(15, GetRow(12), 325, 25);
		GraphicsSettingsTab.add(GroundDecorationsCheckbox);



		/* Right-Column Graphics Settings */



		final JLabel RemoveRoofsLabel = new JLabel("  Remove Roofs");
		RemoveRoofsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		RemoveRoofsLabel.setBounds(378, 15, 150, 25);
		GraphicsSettingsTab.add(RemoveRoofsLabel);

		RemoveRoofsComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[0]);
		RemoveRoofsComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		RemoveRoofsLabel.setLabelFor(RemoveRoofsComboBox);
		RemoveRoofsComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_RemoveRoofs = RemoveRoofsComboBox.getSelectedIndex());
		RemoveRoofsComboBox.setBounds(528, GetRow(0), 175, 25);
		RemoveRoofsComboBox.setSelectedIndex(1);
		GraphicsSettingsTab.add(RemoveRoofsComboBox);

		final JLabel ShadowQualityLabel = new JLabel("  Shadow Quality");
		ShadowQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShadowQualityLabel.setBounds(378, GetRow(1), 150, 25);
		GraphicsSettingsTab.add(ShadowQualityLabel);

		ShadowQualityComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[2]);
		ShadowQualityComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShadowQualityLabel.setLabelFor(ShadowQualityComboBox);
		ShadowQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_ShadowQuality = ShadowQualityComboBox.getSelectedIndex());
		ShadowQualityComboBox.setEnabled(false);
		ShadowQualityComboBox.setBounds(528, GetRow(1), 175, 25);
		GraphicsSettingsTab.add(ShadowQualityComboBox);

		final JLabel AntiAliasingModeLabel = new JLabel("  Anti-Aliasing Mode");
		AntiAliasingModeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AntiAliasingModeLabel.setBounds(378, GetRow(2), 150, 25);
		GraphicsSettingsTab.add(AntiAliasingModeLabel);

		AntiAliasingModeComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[4]);
		AntiAliasingModeComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		AntiAliasingModeLabel.setLabelFor(AntiAliasingModeComboBox);
		AntiAliasingModeComboBox.addItemListener(e -> {
			Storage.nxtGraphicsSetting_AntiAliasingMode = AntiAliasingModeComboBox.getSelectedIndex();
			Legality.CheckSettings();
		});
		AntiAliasingModeComboBox.setBounds(528, GetRow(2), 175, 25);
		GraphicsSettingsTab.add(AntiAliasingModeComboBox);

		final JLabel WaterQualityLabel = new JLabel("  Water Quality");
		WaterQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		WaterQualityLabel.setBounds(378, GetRow(3), 150, 25);
		GraphicsSettingsTab.add(WaterQualityLabel);

		WaterQualityComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[6]);
		WaterQualityComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		WaterQualityLabel.setLabelFor(WaterQualityComboBox);
		WaterQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_WaterQuality = WaterQualityComboBox.getSelectedIndex());
		WaterQualityComboBox.setBounds(528, GetRow(3), 175, 25);
		GraphicsSettingsTab.add(WaterQualityComboBox);

		final JLabel AmbientOcclusionLabel = new JLabel("  Ambient Occlusion");
		AmbientOcclusionLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		AmbientOcclusionLabel.setToolTipText(Storage.AO_TOOLTIP);
		AmbientOcclusionLabel.setBounds(378, GetRow(4), 150, 25);
		GraphicsSettingsTab.add(AmbientOcclusionLabel);

		AmbientOcclusionComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[8]);
		AmbientOcclusionComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		AmbientOcclusionLabel.setLabelFor(AmbientOcclusionComboBox);
		AmbientOcclusionComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_AmbientOcclusion = AmbientOcclusionComboBox.getSelectedIndex());
		AmbientOcclusionComboBox.setBounds(528, GetRow(4), 175, 25);
		GraphicsSettingsTab.add(AmbientOcclusionComboBox);

		final JLabel TextureQualityLabel = new JLabel("  Texture Quality");
		TextureQualityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		TextureQualityLabel.setBounds(378, GetRow(5), 150, 25);
		GraphicsSettingsTab.add(TextureQualityLabel);

		TextureQualityComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[11]);
		TextureQualityComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		TextureQualityLabel.setLabelFor(TextureQualityComboBox);
		TextureQualityComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_TextureQuality = TextureQualityComboBox.getSelectedIndex());
		TextureQualityComboBox.setEnabled(false);
		TextureQualityComboBox.setBounds(528, GetRow(5), 175, 25);
		GraphicsSettingsTab.add(TextureQualityComboBox);

		final JLabel VolumetricLightingLabel = new JLabel("  Volumetric Lighting");
		VolumetricLightingLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		VolumetricLightingLabel.setBounds(378, GetRow(6), 150, 25);
		GraphicsSettingsTab.add(VolumetricLightingLabel);

		VolumetricLightingComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[13]);
		VolumetricLightingComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		VolumetricLightingLabel.setLabelFor(VolumetricLightingComboBox);
		VolumetricLightingComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_VolumetricLighting = VolumetricLightingComboBox.getSelectedIndex());
		VolumetricLightingComboBox.setEnabled(false);
		VolumetricLightingComboBox.setBounds(528, GetRow(6), 175, 25);
		GraphicsSettingsTab.add(VolumetricLightingComboBox);

		final JLabel MaxBackgroundFpsLabel = new JLabel("  Background FPS Cap");
		MaxBackgroundFpsLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		MaxBackgroundFpsLabel.setToolTipText(Storage.MAXBACKGOUNDFPS_TOOLTIP);
		MaxBackgroundFpsLabel.setBounds(378, GetRow(7), 150, 25);
		GraphicsSettingsTab.add(MaxBackgroundFpsLabel);

		MaxBackgroundFpsComboBox = new JComboBox < > (Storage.SETTINGS_OPTIONS[14]);
		MaxBackgroundFpsComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		MaxBackgroundFpsLabel.setLabelFor(MaxBackgroundFpsComboBox);
		MaxBackgroundFpsComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_MaxBackgroundFps = ((MaxBackgroundFpsComboBox.getSelectedIndex()+1)*5));
		MaxBackgroundFpsComboBox.setBounds(528, GetRow(7), 175, 25);
		GraphicsSettingsTab.add(MaxBackgroundFpsComboBox);

		final JLabel GameWorldScalingLabel = new JLabel("  Game World Scaling");
		GameWorldScalingLabel.setToolTipText(Storage.GAMESCALING_TOOLTIP);
		GameWorldScalingLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GameWorldScalingLabel.setBounds(378, GetRow(8), 150, 25);
		GraphicsSettingsTab.add(GameWorldScalingLabel);

		GameWorldScalingComboBox = new JComboBox<>(Storage.SETTINGS_OPTIONS[16]);
		GameWorldScalingComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		GameWorldScalingComboBox.addItemListener(e -> Storage.nxtClientSettings_GameWorldScaling = ((GameWorldScalingComboBox.getSelectedIndex()*5)+35));
		GameWorldScalingComboBox.setBounds(528, GetRow(8), 175, 25);
		GraphicsSettingsTab.add(GameWorldScalingComboBox);

		final JLabel ScreensizingModeLabel = new JLabel("  Screen Sizing Mode");
		ScreensizingModeLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		ScreensizingModeLabel.setBounds(378, GetRow(9), 150, 25);
		GraphicsSettingsTab.add(ScreensizingModeLabel);

		ScreenSizingModeComboBox = new JComboBox<>(Storage.SETTINGS_OPTIONS[21]);
		ScreenSizingModeComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		ScreenSizingModeComboBox.addItemListener(e -> Storage.nxtGraphicsSetting_ScreenSizingMode = ((ScreenSizingModeComboBox.getSelectedIndex()*5)+35));
		ScreenSizingModeComboBox.setBounds(528, GetRow(9), 175, 25);
		GraphicsSettingsTab.add(ScreenSizingModeComboBox);

		ShadowsCheckbox = new JCheckBox(String.format("%-88s", "Shadows"));
		ShadowsCheckbox.setBackground(optionBackgroundColor);
		ShadowsCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShadowsCheckbox.addActionListener(e -> {
			Storage.nxtGraphicsSetting_Shadows = ShadowsCheckbox.isSelected();
			Legality.CheckSettings();
		});
		ShadowsCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		ShadowsCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		ShadowsCheckbox.setBounds(378, GetRow(10), 325, 25);
		GraphicsSettingsTab.add(ShadowsCheckbox);

		LoadingScreensCheckbox = new JCheckBox(String.format("%-82s", "Loading Screens"));
		LoadingScreensCheckbox.setBackground(optionBackgroundColor);
		LoadingScreensCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		LoadingScreensCheckbox.addActionListener(e -> Storage.nxtGraphicsSetting_LoadingScreens = LoadingScreensCheckbox.isSelected());
		LoadingScreensCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		LoadingScreensCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		LoadingScreensCheckbox.setBounds(378, GetRow(11), 325, 25);
		LoadingScreensCheckbox.setSelected(true);
		GraphicsSettingsTab.add(LoadingScreensCheckbox);

		TerrainBlendingCheckbox = new JCheckBox(String.format("%-84s", "Terrain Blending"));
		TerrainBlendingCheckbox.setBackground(optionBackgroundColor);
		TerrainBlendingCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		TerrainBlendingCheckbox.addActionListener(e -> {
			Storage.nxtGraphicsSetting_TerrainBlending = TerrainBlendingCheckbox.isSelected();
			Legality.CheckSettings();
		});
		TerrainBlendingCheckbox.setHorizontalAlignment(SwingConstants.TRAILING);
		TerrainBlendingCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
		TerrainBlendingCheckbox.setBounds(378, GetRow(12), 325, 25);
		GraphicsSettingsTab.add(TerrainBlendingCheckbox);



		/* Preset Settings */



		final JLabel GraphicsPresetLabel = new JLabel("Preset: ");
		GraphicsPresetLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		GraphicsPresetLabel.setBounds(15, GetRow(16), 50, 25);
		GraphicsSettingsTab.add(GraphicsPresetLabel);

		MinimumGraphicsPresetButton = new JButton("Minimum");
		MinimumGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		MinimumGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		MinimumGraphicsPresetButton.addActionListener(e -> Mechanics.SetGraphicsPreset("Min"));
		MinimumGraphicsPresetButton.setEnabled(false);
		MinimumGraphicsPresetButton.setBounds(70, GetRow(16), 75, 25);
		GraphicsSettingsTab.add(MinimumGraphicsPresetButton);

		LowGraphicsPresetButton = new JButton("Low");
		LowGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		LowGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		LowGraphicsPresetButton.addActionListener(e -> Mechanics.SetGraphicsPreset("Low"));
		LowGraphicsPresetButton.setEnabled(false);
		LowGraphicsPresetButton.setBounds(150, GetRow(16), 75, 25);
		GraphicsSettingsTab.add(LowGraphicsPresetButton);

		MediumGraphicsPresetButton = new JButton("Medium");
		MediumGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		MediumGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		MediumGraphicsPresetButton.addActionListener(e -> Mechanics.SetGraphicsPreset("Med"));
		MediumGraphicsPresetButton.setEnabled(false);
		MediumGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		MediumGraphicsPresetButton.setBounds(230, GetRow(16), 75, 25);
		GraphicsSettingsTab.add(MediumGraphicsPresetButton);

		HighGraphicsPresetButton = new JButton("High");
		HighGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		HighGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		HighGraphicsPresetButton.addActionListener(e -> Mechanics.SetGraphicsPreset("High"));
		HighGraphicsPresetButton.setEnabled(false);
		HighGraphicsPresetButton.setBounds(310, GetRow(16), 75, 25);
		GraphicsSettingsTab.add(HighGraphicsPresetButton);

		UltraGraphicsPresetButton = new JButton("Ultra");
		UltraGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		UltraGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		UltraGraphicsPresetButton.addActionListener(e -> Mechanics.SetGraphicsPreset("Ultra"));
		UltraGraphicsPresetButton.setEnabled(false);
		UltraGraphicsPresetButton.setBounds(390, GetRow(16), 75, 25);
		GraphicsSettingsTab.add(UltraGraphicsPresetButton);

		MaxedGraphicsPresetButton = new JButton("\"Maxed\"");
		MaxedGraphicsPresetButton.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		MaxedGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		MaxedGraphicsPresetButton.addActionListener(e -> Mechanics.SetGraphicsPreset("MAXED"));
		MaxedGraphicsPresetButton.setEnabled(false);
		MaxedGraphicsPresetButton.setBounds(470, GetRow(16), 75, 25);
		GraphicsSettingsTab.add(MaxedGraphicsPresetButton);

		WikianGraphicsPresetButton = new JButton("Wikian");
		WikianGraphicsPresetButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		WikianGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		WikianGraphicsPresetButton.addActionListener(e -> Mechanics.SetGraphicsPreset("wikian"));
		WikianGraphicsPresetButton.setEnabled(false);
		WikianGraphicsPresetButton.setBounds(550, GetRow(16), 75, 25);
		GraphicsSettingsTab.add(WikianGraphicsPresetButton);

		RedditGraphicsPresetButton = new JButton("Reddit");
		RedditGraphicsPresetButton.setFont(new Font("Dialog", Font.ITALIC, 10));
		RedditGraphicsPresetButton.setVerticalAlignment(SwingConstants.TOP);
		RedditGraphicsPresetButton.setToolTipText(Storage.GRAPHICS_PRESET_BUTTON_TOOLTIP);
		RedditGraphicsPresetButton.addActionListener(e -> Mechanics.SetGraphicsPreset("/r/runescape"));
		RedditGraphicsPresetButton.setEnabled(false);
		RedditGraphicsPresetButton.setBounds(630, GetRow(16), 75, 25);
		GraphicsSettingsTab.add(RedditGraphicsPresetButton);



		/* Client Settings */



		final JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		UsernameLabel.setBounds(15, GetRow(0), 150, 25);
		ClientSettingsTab.add(UsernameLabel);

		UsernameInput = new JTextField();
		UsernameInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		UsernameLabel.setLabelFor(UsernameInput);
		UsernameInput.setToolTipText(Storage.USERNAME_INPUT_TOOLTIP);
		UsernameInput.setBounds(170, GetRow(0), 310, 25);
		ClientSettingsTab.add(UsernameInput);
		UsernameInput.setColumns(10);

		RememberUsernameCheckbox = new JCheckBox("Remember Saved Username?");
		RememberUsernameCheckbox.setFont(new Font("Dialog", Font.PLAIN, 11));
		RememberUsernameCheckbox.addActionListener(e -> Storage.nxtClientSettings_RememberUsername = RememberUsernameCheckbox.isSelected());
		RememberUsernameCheckbox.setToolTipText(Storage.REMEMBER_USERNAME_TOOLTIP);
		RememberUsernameCheckbox.setBounds(485, GetRow(0), 220, 25);
		RememberUsernameCheckbox.setBackground(optionBackgroundColor);
		ClientSettingsTab.add(RememberUsernameCheckbox);

		final Object[] WORLDS = new Object[256];
		for (int i = 1; i < WORLDS.length; i++){
			WORLDS[i] = i;
		}
		WORLDS[0] = "None";

		final JLabel FavouriteWorld1Label = new JLabel("Favourite World 1");
		FavouriteWorld1Label.setFont(new Font("Dialog", Font.PLAIN, 11));
		FavouriteWorld1Label.setBounds(15, GetRow(1), 150, 25);
		ClientSettingsTab.add(FavouriteWorld1Label);

		FavouriteWorld1ComboBox = new JComboBox<>(WORLDS);
		FavouriteWorld1ComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		FavouriteWorld1ComboBox.addItemListener(e -> Storage.nxtClientSettings_FavouriteWorld1 = (FavouriteWorld1ComboBox.getSelectedIndex()));
		FavouriteWorld1ComboBox.setBounds(170, GetRow(1), 85, 25);
		ClientSettingsTab.add(FavouriteWorld1ComboBox);

		final JLabel FavouriteWorld2Label = new JLabel("Favourite World 2");
		FavouriteWorld2Label.setHorizontalAlignment(SwingConstants.CENTER);
		FavouriteWorld2Label.setFont(new Font("Dialog", Font.PLAIN, 11));
		FavouriteWorld2Label.setBounds(260, GetRow(1), 130, 25);
		ClientSettingsTab.add(FavouriteWorld2Label);

		FavouriteWorld2ComboBox = new JComboBox<>(WORLDS);
		FavouriteWorld2ComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		FavouriteWorld2ComboBox.addItemListener(e -> Storage.nxtClientSettings_FavouriteWorld2 = (FavouriteWorld2ComboBox.getSelectedIndex()));
		FavouriteWorld2ComboBox.setBounds(395, GetRow(1), 85, 25);
		ClientSettingsTab.add(FavouriteWorld2ComboBox);

		final JLabel FavouriteWorld3Label = new JLabel("Favourite World 3");
		FavouriteWorld3Label.setHorizontalAlignment(SwingConstants.CENTER);
		FavouriteWorld3Label.setFont(new Font("Dialog", Font.PLAIN, 11));
		FavouriteWorld3Label.setBounds(485, GetRow(1), 130, 25);
		ClientSettingsTab.add(FavouriteWorld3Label);

		FavouriteWorld3ComboBox = new JComboBox<>(WORLDS);
		FavouriteWorld3ComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		FavouriteWorld3ComboBox.addItemListener(e -> Storage.nxtClientSettings_FavouriteWorld3 = (FavouriteWorld3ComboBox.getSelectedIndex()));
		FavouriteWorld3ComboBox.setBounds(620, GetRow(1), 85, 25);
		ClientSettingsTab.add(FavouriteWorld3ComboBox);

		final JLabel WallpaperLabel = new JLabel("Wallpaper Settings");
		WallpaperLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		WallpaperLabel.setBounds(15, GetRow(2), 130, 25);
		ClientSettingsTab.add(WallpaperLabel);

		LoginWallpaperIDComboBox = new JComboBox<>(Storage.SETTINGS_OPTIONS[19]);
		LoginWallpaperIDComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		LoginWallpaperIDComboBox.addItemListener(e -> Storage.nxtClientSettings_LoginWallpaperID = LoginWallpaperIDComboBox.getSelectedIndex());
		LoginWallpaperIDComboBox.setBounds(170, GetRow(2), 265, 25);
		ClientSettingsTab.add(LoginWallpaperIDComboBox);

		RandomizeLoginWallpaperCheckbox = new JCheckBox("Randomize The Wallpaper?");
		RandomizeLoginWallpaperCheckbox.addActionListener(e -> Storage.nxtClientSettings_RandomizeLoginWallpaper = RandomizeLoginWallpaperCheckbox.isSelected());
		RandomizeLoginWallpaperCheckbox.setToolTipText(Storage.RANDOMIZE_LOGIN_WALLPAPER_TOOLTIP);
		RandomizeLoginWallpaperCheckbox.setFont(new Font("Dialog", Font.PLAIN, 11));
		RandomizeLoginWallpaperCheckbox.setBounds(440, GetRow(2), 265, 25);
		RandomizeLoginWallpaperCheckbox.setBackground(optionBackgroundColor);
		ClientSettingsTab.add(RandomizeLoginWallpaperCheckbox);

		final JLabel InGameWorldSortingLabel = new JLabel("World-List Menu Sorting");
		InGameWorldSortingLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		InGameWorldSortingLabel.setBounds(15, GetRow(3), 150, 25);
		ClientSettingsTab.add(InGameWorldSortingLabel);

		InGameWorldSortingComboBox = new JComboBox<>(Storage.SETTINGS_OPTIONS[17]);
		InGameWorldSortingComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameWorldSortingComboBox.addItemListener(e -> Storage.nxtClientSettings_WorldSorting = InGameWorldSortingComboBox.getSelectedIndex());
		InGameWorldSortingComboBox.setBounds(170, GetRow(3), 265, 25);
		ClientSettingsTab.add(InGameWorldSortingComboBox);

		final JLabel EmoteSelectionLabel = new JLabel("Emote-List Sorting");
		EmoteSelectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		EmoteSelectionLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		EmoteSelectionLabel.setBounds(440, GetRow(3), 130, 25);
		EmoteSelectionLabel.setLabelFor(LanguageSelectionComboBox);
		ClientSettingsTab.add(EmoteSelectionLabel);

		InGameEmoteSortingComboBox = new JComboBox<>(Storage.SETTINGS_OPTIONS[20]);
		InGameEmoteSortingComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameEmoteSortingComboBox.addItemListener(e -> Storage.nxtClientSettings_EmoteSorting = InGameEmoteSortingComboBox.getSelectedIndex());
		InGameEmoteSortingComboBox.setBounds(575, GetRow(3), 130, 25);
		ClientSettingsTab.add(InGameEmoteSortingComboBox);

		final JLabel WorldMapSettingsLabel = new JLabel("World Map Settings");
		WorldMapSettingsLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		WorldMapSettingsLabel.setBounds(15, GetRow(4), 150, 25);
		ClientSettingsTab.add(WorldMapSettingsLabel);

		WorldMapOverlayComboBox = new JComboBox<>(Storage.SETTINGS_OPTIONS[22]);
		WorldMapOverlayComboBox.setFont(new Font("Dialog", Font.PLAIN, 11));
		WorldMapOverlayComboBox.addItemListener(e -> Storage.nxtClientSettings_WorldMapOverlays = WorldMapOverlayComboBox.getSelectedIndex());
		WorldMapOverlayComboBox.setBounds(170, GetRow(4), 265, 25);
		ClientSettingsTab.add(WorldMapOverlayComboBox);

		WorldMapIconSelectionButton = new JButton("Load World Map Icon Selection GUI");
		WorldMapIconSelectionButton.setToolTipText("Load up the GUI to manually select from multiple World Map icons to display on the screen.");
		WorldMapIconSelectionButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		WorldMapIconSelectionButton.setBounds(440, GetRow(4), 265, 25);
		WorldMapIconSelectionButton.addActionListener(arg0 -> new NXTWorldMapSelectionGUI());
		ClientSettingsTab.add(WorldMapIconSelectionButton);

		final JLabel MiscTogglesLabel = new JLabel("ToggleScape Toggles");
		MiscTogglesLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		MiscTogglesLabel.setBounds(15, GetRow(5), 150, 25);
		ClientSettingsTab.add(MiscTogglesLabel);

		InGameTaskPopupsCheckbox = new JCheckBox("Show Task Completed Popups?");
		InGameTaskPopupsCheckbox.setToolTipText("Toggle the In-Game Task Completed Popups.");
		InGameTaskPopupsCheckbox.setFont(new Font("Dialog", Font.PLAIN, 11));
		InGameTaskPopupsCheckbox.addActionListener(e -> Storage.nxtClientSettings_TaskCompletedPopup = InGameTaskPopupsCheckbox.isSelected());
		InGameTaskPopupsCheckbox.setBackground(new Color(40, 40, 40));
		InGameTaskPopupsCheckbox.setBounds(170, GetRow(5), 265, 25);
		ClientSettingsTab.add(InGameTaskPopupsCheckbox);

		InGameMouseOverPopupsCheckbox = new JCheckBox("Show Mouse-Over/Hover-Over Tooltips?");
		InGameMouseOverPopupsCheckbox.addActionListener(e -> Storage.nxtClientSettings_MouseOverTooltip = InGameMouseOverPopupsCheckbox.isSelected());
		InGameMouseOverPopupsCheckbox.setToolTipText("Toggle the In-Game Hover-Over tooltips.");
		InGameMouseOverPopupsCheckbox.setFont(new Font("Dialog", Font.PLAIN, 11));
		InGameMouseOverPopupsCheckbox.setBackground(optionBackgroundColor);
		InGameMouseOverPopupsCheckbox.setBounds(440, GetRow(5), 265, 25);
		ClientSettingsTab.add(InGameMouseOverPopupsCheckbox);

		MinimizeMainAbilityBarCheckBox = new JCheckBox("Minimize The Main Ability Bar?");
		MinimizeMainAbilityBarCheckBox.addActionListener(e -> Storage.nxtClientSettings_AbilityBarMinimized = MinimizeMainAbilityBarCheckBox.isSelected());
		MinimizeMainAbilityBarCheckBox.setFont(new Font("Dialog", Font.PLAIN, 11));
		MinimizeMainAbilityBarCheckBox.setBackground(optionBackgroundColor);
		MinimizeMainAbilityBarCheckBox.setBounds(440, GetRow(6), 265, 25);
		ClientSettingsTab.add(MinimizeMainAbilityBarCheckBox);

		/* Separate */

		CompatibilityModeCheckBox = new JCheckBox("Compatibility Mode");
		CompatibilityModeCheckBox.addActionListener(e -> Storage.nxtClientSettings_CompatibilityMode = CompatibilityModeCheckBox.isSelected());
		CompatibilityModeCheckBox.setBackground(optionBackgroundColor);
		CompatibilityModeCheckBox.setFont(new Font("Dialog", Font.PLAIN, 11));
		CompatibilityModeCheckBox.setBounds(15, GetRow(16), 150, 25);
		ClientSettingsTab.add(CompatibilityModeCheckBox);

		CompatibilityModeOnErrorCheckBox = new JCheckBox("Switch to Compatibility on Error?");
		CompatibilityModeOnErrorCheckBox.addActionListener(e -> Storage.nxtClientSettings_AskToSwitchToCompatibility = CompatibilityModeOnErrorCheckBox.isSelected());
		CompatibilityModeOnErrorCheckBox.setBackground(optionBackgroundColor);
		CompatibilityModeOnErrorCheckBox.setFont(new Font("Dialog", Font.PLAIN, 11));
		CompatibilityModeOnErrorCheckBox.setBounds(170, GetRow(16), 220, 25);
		ClientSettingsTab.add(CompatibilityModeOnErrorCheckBox);

		AskBeforeQuittingCheckBox = new JCheckBox("Confirm Quit on Exit?");
		AskBeforeQuittingCheckBox.addActionListener(e -> Storage.nxtClientSettings_AskBeforeQuitting = AskBeforeQuittingCheckBox.isSelected());
		AskBeforeQuittingCheckBox.setBackground(optionBackgroundColor);
		AskBeforeQuittingCheckBox.setFont(new Font("Dialog", Font.PLAIN, 11));
		AskBeforeQuittingCheckBox.setBounds(395, GetRow(16), 155, 25);
		ClientSettingsTab.add(AskBeforeQuittingCheckBox);

		final JLabel LanguageSelectionLabel = new JLabel("Language");
		LanguageSelectionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		LanguageSelectionLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		LanguageSelectionLabel.setBounds(555, GetRow(16), 60, 25);
		LanguageSelectionLabel.setLabelFor(LanguageSelectionComboBox);
		ClientSettingsTab.add(LanguageSelectionLabel);

		LanguageSelectionComboBox = new JComboBox<>(Storage.LANGUAGES);
		LanguageSelectionComboBox.addItemListener(e -> Storage.nxtClientSettings_LanguageSelected = LanguageSelectionComboBox.getSelectedIndex());
		LanguageSelectionComboBox.setFont(new Font("Dialog", Font.PLAIN, 11));
		LanguageSelectionComboBox.setBounds(620, GetRow(16), 85, 25);
		ClientSettingsTab.add(LanguageSelectionComboBox);



		/* Control Settings */



		final JLabel InGameCameraZoomLabel = new JLabel("In-Game Camera Zoom");
		InGameCameraZoomLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		InGameCameraZoomLabel.setBounds(15, GetRow(0), 150, 25);
		ControlSettingsTab.add(InGameCameraZoomLabel);

		InGameCameraZoomSlider = new JSlider();
		InGameCameraZoomLabel.setLabelFor(InGameCameraZoomSlider);
		InGameCameraZoomSlider.addChangeListener(e -> Storage.nxtClientSettings_CameraZoom = InGameCameraZoomSlider.getValue());
		InGameCameraZoomSlider.setMinimum(1860);
		InGameCameraZoomSlider.setMaximum(7600);
		InGameCameraZoomSlider.setPaintTicks(false);
		InGameCameraZoomSlider.setBounds(170, GetRow(0), 535, 25);
		InGameCameraZoomSlider.setCursor(HResizeCursor);
		ControlSettingsTab.add(InGameCameraZoomSlider);

		final JLabel InGameKeyboardHSensitivityLabel = new JLabel("Keyboard Sensitivity (H)");
		InGameKeyboardHSensitivityLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		InGameKeyboardHSensitivityLabel.setBounds(15, GetRow(1), 150, 25);
		ControlSettingsTab.add(InGameKeyboardHSensitivityLabel);

		InGameKeyboardHSensitivitySlider = new JSlider();
		InGameKeyboardHSensitivitySlider.setToolTipText("Keyboard Horizontal Sensitivity");
		InGameKeyboardHSensitivitySlider.addChangeListener(e -> Storage.nxtClientSettings_KeyboardHSensitivity = InGameKeyboardHSensitivitySlider.getValue());
		InGameKeyboardHSensitivitySlider.setMinimum(100);
		InGameKeyboardHSensitivitySlider.setMaximum(250);
		InGameKeyboardHSensitivitySlider.setPaintTicks(false);
		InGameKeyboardHSensitivitySlider.setBounds(170, GetRow(1), 535, 25);
		InGameKeyboardHSensitivitySlider.setCursor(HResizeCursor);
		ControlSettingsTab.add(InGameKeyboardHSensitivitySlider);

		final JLabel InGameKeyboardVSensitivityLabel = new JLabel("Keyboard Sensitivity (V)");
		InGameKeyboardVSensitivityLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		InGameKeyboardVSensitivityLabel.setBounds(15, GetRow(2), 150, 25);
		ControlSettingsTab.add(InGameKeyboardVSensitivityLabel);

		InGameKeyboardVSensitivitySlider = new JSlider();
		InGameKeyboardVSensitivitySlider.setToolTipText("Keyboard Vertical Sensitivity");
		InGameKeyboardVSensitivitySlider.addChangeListener(e -> Storage.nxtClientSettings_KeyboardVSensitivity = InGameKeyboardVSensitivitySlider.getValue());
		InGameKeyboardVSensitivitySlider.setMinimum(70);
		InGameKeyboardVSensitivitySlider.setMaximum(200);
		InGameKeyboardVSensitivitySlider.setPaintTicks(false);
		InGameKeyboardVSensitivitySlider.setBounds(170, GetRow(2), 535, 25);
		InGameKeyboardVSensitivitySlider.setCursor(HResizeCursor);
		ControlSettingsTab.add(InGameKeyboardVSensitivitySlider);

		final JLabel InGameMouseHSensitivityLabel = new JLabel("Mouse Sensitivity (H)");
		InGameMouseHSensitivityLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		InGameMouseHSensitivityLabel.setBounds(15, GetRow(3), 150, 25);
		ControlSettingsTab.add(InGameMouseHSensitivityLabel);

		InGameMouseHSensitivitySlider = new JSlider();
		InGameMouseHSensitivitySlider.setToolTipText("The Mouse's Horizontal Sensitivity");
		InGameMouseHSensitivitySlider.addChangeListener(e -> Storage.nxtClientSettings_MouseHSensitivity = InGameMouseHSensitivitySlider.getValue());
		InGameMouseHSensitivitySlider.setMinimum(4);
		InGameMouseHSensitivitySlider.setMaximum(24);
		InGameMouseHSensitivitySlider.setPaintTicks(false);
		InGameMouseHSensitivitySlider.setBounds(170, GetRow(3), 535, 25);
		InGameMouseHSensitivitySlider.setCursor(HResizeCursor);
		ControlSettingsTab.add(InGameMouseHSensitivitySlider);


		final JLabel InGameMouseVSensitivityLabel = new JLabel("Mouse Sensitivity (V)");
		InGameMouseVSensitivityLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		InGameMouseVSensitivityLabel.setBounds(15, GetRow(4), 150, 25);
		ControlSettingsTab.add(InGameMouseVSensitivityLabel);

		InGameMouseVSensitivitySlider = new JSlider();
		InGameMouseVSensitivitySlider.setToolTipText("The Mouse's Vertical Sensitivity");
		InGameMouseVSensitivitySlider.addChangeListener(e -> Storage.nxtClientSettings_MouseVSensitivity = InGameMouseVSensitivitySlider.getValue());
		InGameMouseVSensitivitySlider.setMinimum(3);
		InGameMouseVSensitivitySlider.setMaximum(22);
		InGameMouseVSensitivitySlider.setPaintTicks(false);
		InGameMouseVSensitivitySlider.setBounds(170, GetRow(4), 535, 25);
		InGameMouseVSensitivitySlider.setCursor(HResizeCursor);
		ControlSettingsTab.add(InGameMouseVSensitivitySlider);

		final JLabel OoORotationMovementLabel = new JLabel("Freecam Move Speed");
		OoORotationMovementLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		OoORotationMovementLabel.setBounds(15, GetRow(5), 150, 25);
		ControlSettingsTab.add(OoORotationMovementLabel);

		OoOMovementSpeedSlider = new JSlider();
		OoOMovementSpeedSlider.setToolTipText("The Orb Of Oculus/Freecam's Movement Speed");
		OoOMovementSpeedSlider.addChangeListener(e -> Storage.nxtClientSettings_OoOMovementSpeed = OoOMovementSpeedSlider.getValue());
		OoOMovementSpeedSlider.setPaintTicks(false);
		OoOMovementSpeedSlider.setMinimum(64);
		OoOMovementSpeedSlider.setMaximum(511);
		OoOMovementSpeedSlider.setBounds(170, GetRow(5), 535, 25);
		OoOMovementSpeedSlider.setCursor(HResizeCursor);
		ControlSettingsTab.add(OoOMovementSpeedSlider);

		final JLabel OoORotationSpeedLabel = new JLabel("Freecam Rotation Speed");
		OoORotationSpeedLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		OoORotationSpeedLabel.setBounds(15, GetRow(6), 150, 25);
		ControlSettingsTab.add(OoORotationSpeedLabel);

		OoORotationSpeedSlider = new JSlider();
		OoORotationSpeedSlider.setToolTipText("The Orb Of Oculus/Freecam's Rotation Speed");
		OoORotationSpeedSlider.addChangeListener(e -> Storage.nxtClientSettings_OoORotationSpeed = OoORotationSpeedSlider.getValue());
		OoORotationSpeedSlider.setPaintTicks(false);
		OoORotationSpeedSlider.setMinimum(1);
		OoORotationSpeedSlider.setMaximum(49);
		OoORotationSpeedSlider.setBounds(170, GetRow(6), 535, 25);
		OoORotationSpeedSlider.setCursor(HResizeCursor);
		ControlSettingsTab.add(OoORotationSpeedSlider);

		final JLabel FriendsListDividerLabel = new JLabel("Friends List Divider");
		FriendsListDividerLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		FriendsListDividerLabel.setBounds(15, GetRow(7), 150, 25);
		ControlSettingsTab.add(FriendsListDividerLabel);

		FriendsListDividerSlider = new JSlider();
		FriendsListDividerSlider.addChangeListener(e -> Storage.nxtClientSettings_FriendsListDivider = FriendsListDividerSlider.getValue());
		FriendsListDividerSlider.setPaintTicks(false);
		FriendsListDividerSlider.setMinimum(0);
		FriendsListDividerSlider.setMaximum(350);
		FriendsListDividerSlider.setBounds(170, GetRow(7), 535, 25);
		FriendsListDividerSlider.setCursor(HResizeCursor);
		ControlSettingsTab.add(FriendsListDividerSlider);

		final JLabel FriendsChatListDividerLabel = new JLabel("Friends Chat Divider");
		FriendsChatListDividerLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		FriendsChatListDividerLabel.setBounds(15, GetRow(8), 150, 25);
		ControlSettingsTab.add(FriendsChatListDividerLabel);

		FriendsChatListDividerSlider = new JSlider();
		FriendsChatListDividerSlider.addChangeListener(e -> Storage.nxtClientSettings_FriendsChatListDivider = FriendsChatListDividerSlider.getValue());
		FriendsChatListDividerSlider.setPaintTicks(false);
		FriendsChatListDividerSlider.setMinimum(0);
		FriendsChatListDividerSlider.setMaximum(350);
		FriendsChatListDividerSlider.setBounds(170, GetRow(8), 535, 25);
		FriendsChatListDividerSlider.setCursor(HResizeCursor);
		ControlSettingsTab.add(FriendsChatListDividerSlider);

		final JLabel ClanChatListDividerLabel = new JLabel("Clan Chat Divider");
		ClanChatListDividerLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		ClanChatListDividerLabel.setBounds(15, GetRow(9), 150, 25);
		ControlSettingsTab.add(ClanChatListDividerLabel);

		ClanChatListDividerSlider = new JSlider();
		ClanChatListDividerSlider.addChangeListener(e -> Storage.nxtClientSettings_ClanChatListDivider = ClanChatListDividerSlider.getValue());
		ClanChatListDividerSlider.setPaintTicks(false);
		ClanChatListDividerSlider.setMinimum(0);
		ClanChatListDividerSlider.setMaximum(350);
		ClanChatListDividerSlider.setBounds(170, GetRow(9), 535, 25);
		ClanChatListDividerSlider.setCursor(HResizeCursor);
		ControlSettingsTab.add(ClanChatListDividerSlider);

		final JLabel GuestClanChatListDividerLabel = new JLabel("Guest Clan Chat Divider");
		GuestClanChatListDividerLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		GuestClanChatListDividerLabel.setBounds(15, GetRow(10), 150, 25);
		ControlSettingsTab.add(GuestClanChatListDividerLabel);

		GuestClanChatListDividerSlider = new JSlider();
		GuestClanChatListDividerSlider.addChangeListener(e -> Storage.nxtClientSettings_GuestClanChatListDivider = GuestClanChatListDividerSlider.getValue());
		GuestClanChatListDividerSlider.setPaintTicks(false);
		GuestClanChatListDividerSlider.setMinimum(0);
		GuestClanChatListDividerSlider.setMaximum(350);
		GuestClanChatListDividerSlider.setBounds(170, GetRow(10), 535, 25);
		GuestClanChatListDividerSlider.setCursor(HResizeCursor);
		ControlSettingsTab.add(GuestClanChatListDividerSlider);



		/* Audio Settings */



		final JLabel LoginMusicLabel = new JLabel("Login Music Volume");
		LoginMusicLabel.setLabelFor(LoginMusicSlider);
		LoginMusicLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		LoginMusicLabel.setBounds(15, GetRow(0), 150, 25);
		AudioSettingsTab.add(LoginMusicLabel);

		LoginMusicSlider = new JSlider();
		LoginMusicSlider.addChangeListener(e -> Storage.nxtClientSettings_LoginMusicVolume = LoginMusicSlider.getValue());
		LoginMusicSlider.setMaximum(255);
		LoginMusicSlider.setPaintTicks(false);
		LoginMusicSlider.setBounds(170, GetRow(0), 535, 25);
		LoginMusicSlider.setCursor(HResizeCursor);
		AudioSettingsTab.add(LoginMusicSlider);

		final JLabel InGameMusicLabel = new JLabel("Game Music Volume");
		InGameMusicLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		InGameMusicLabel.setBounds(15, GetRow(1), 150, 25);
		AudioSettingsTab.add(InGameMusicLabel);

		InGameMusicSlider = new JSlider();
		InGameMusicLabel.setLabelFor(InGameMusicSlider);
		InGameMusicSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameMusicVolume = InGameMusicSlider.getValue());
		InGameMusicSlider.setMaximum(255);
		InGameMusicSlider.setPaintTicks(false);
		InGameMusicSlider.setBounds(170, GetRow(1), 535, 25);
		InGameMusicSlider.setCursor(HResizeCursor);
		AudioSettingsTab.add(InGameMusicSlider);

		final JLabel InGameSoundEffectsLabel = new JLabel("Sound Effect Volume");
		InGameSoundEffectsLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		InGameSoundEffectsLabel.setBounds(15, GetRow(2), 150, 25);
		AudioSettingsTab.add(InGameSoundEffectsLabel);

		InGameSoundEffectsSlider = new JSlider();
		InGameSoundEffectsLabel.setLabelFor(InGameSoundEffectsSlider);
		InGameSoundEffectsSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameSoundEffectsVolume = InGameSoundEffectsSlider.getValue());
		InGameSoundEffectsSlider.setMaximum(127);
		InGameSoundEffectsSlider.setPaintTicks(false);
		InGameSoundEffectsSlider.setBounds(170, GetRow(2), 535, 25);
		InGameSoundEffectsSlider.setCursor(HResizeCursor);
		AudioSettingsTab.add(InGameSoundEffectsSlider);

		final JLabel InGameAmbientSoundEffectsLabel = new JLabel("Ambient Sound Volume");
		InGameAmbientSoundEffectsLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		InGameAmbientSoundEffectsLabel.setBounds(15, GetRow(3), 150, 25);
		AudioSettingsTab.add(InGameAmbientSoundEffectsLabel);

		InGameAmbientSoundEffectsSlider = new JSlider();
		InGameAmbientSoundEffectsLabel.setLabelFor(InGameAmbientSoundEffectsSlider);
		InGameAmbientSoundEffectsSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameAmbientSoundEffectsVolume = InGameAmbientSoundEffectsSlider.getValue());
		InGameAmbientSoundEffectsSlider.setMaximum(127);
		InGameAmbientSoundEffectsSlider.setPaintTicks(false);
		InGameAmbientSoundEffectsSlider.setBounds(170, GetRow(3), 535, 25);
		InGameAmbientSoundEffectsSlider.setCursor(HResizeCursor);
		AudioSettingsTab.add(InGameAmbientSoundEffectsSlider);

		final JLabel InGameVoiceOverLabel = new JLabel("Voice Over Volume");
		InGameVoiceOverLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		InGameVoiceOverLabel.setBounds(15, GetRow(4), 150, 25);
		AudioSettingsTab.add(InGameVoiceOverLabel);

		InGameVoiceOverSlider = new JSlider();
		InGameVoiceOverLabel.setLabelFor(InGameVoiceOverSlider);
		InGameVoiceOverSlider.addChangeListener(e -> Storage.nxtClientSettings_InGameVoiceOverVolume = InGameVoiceOverSlider.getValue());
		InGameVoiceOverSlider.setMaximum(127);
		InGameVoiceOverSlider.setPaintTicks(false);
		InGameVoiceOverSlider.setBounds(170, GetRow(4), 535, 25);
		InGameVoiceOverSlider.setCursor(HResizeCursor);
		AudioSettingsTab.add(InGameVoiceOverSlider);

		final JLabel InGameMusicSortingLabel = new JLabel("Music Track Sorting");
		InGameMusicSortingLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		InGameMusicSortingLabel.setBounds(15, GetRow(5), 150, 25);
		AudioSettingsTab.add(InGameMusicSortingLabel);

		InGameMusicSortingComboBox = new JComboBox<>(Storage.SETTINGS_OPTIONS[18]);
		InGameMusicSortingComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		InGameMusicSortingComboBox.addItemListener(e -> Storage.nxtClientSettings_MusicSorting = InGameMusicSortingComboBox.getSelectedIndex());
		InGameMusicSortingComboBox.setBounds(170, GetRow(5), 265, 25);
		AudioSettingsTab.add(InGameMusicSortingComboBox);

		LoopCurrentMusicTrackCheckbox = new JCheckBox("Loop the Currently Playing Music Track?");
		LoopCurrentMusicTrackCheckbox.addActionListener(e -> Storage.nxtClientSettings_LoopCurrentTrack = LoopCurrentMusicTrackCheckbox.isSelected());
		LoopCurrentMusicTrackCheckbox.setToolTipText(Storage.GLOBAL_AUDIO_MUTE_TOOLTIP);
		LoopCurrentMusicTrackCheckbox.setFont(new Font("Dialog", Font.PLAIN, 11));
		LoopCurrentMusicTrackCheckbox.setBackground(optionBackgroundColor);
		LoopCurrentMusicTrackCheckbox.setBounds(440, GetRow(5), 265, 25);
		AudioSettingsTab.add(LoopCurrentMusicTrackCheckbox);



		/* Special Mechanics */



		AddSpriteFlagToUsername = new JButton("Add <sprite> to your username");
		AddSpriteFlagToUsername.setToolTipText(Storage.ADD_SPRITE_FLAG_TOOLTIP);
		AddSpriteFlagToUsername.addActionListener(e -> {
			if (UsernameInput != null) {
				UsernameInput.setText(UsernameInput.getText() + "<sprite=SPRITE_ID,SPRITE_SUB_ID>");
			}
		});
		AddSpriteFlagToUsername.setFont(new Font("Dialog", Font.PLAIN, 11));
		AddSpriteFlagToUsername.setBounds(15, 15, 225, 25);
		SpecialMechanicsTab.add(AddSpriteFlagToUsername);

		AddColourFlagToUsername = new JButton("Add <col> to your username");
		AddColourFlagToUsername.setToolTipText(Storage.ADD_COLOUR_FLAG_TOOLTIP);
		AddColourFlagToUsername.addActionListener(e ->{
			if (UsernameInput != null) {
				UsernameInput.setText(UsernameInput.getText() + "<col=RRGGBB></col>");
			}
		});
		AddColourFlagToUsername.setFont(new Font("Dialog", Font.PLAIN, 11));
		AddColourFlagToUsername.setBounds(245, 15, 225, 25);
		SpecialMechanicsTab.add(AddColourFlagToUsername);

		GrabInternalBuildLabel = new JButton("Grab NXT's internal build label");
		GrabInternalBuildLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		GrabInternalBuildLabel.addActionListener(e -> {
			if (Storage.NXT_INSTALLED) {
				if (Storage.OS_TYPE == 0) {
					Mechanics.GrabInternalBuildNumber(true);
				} else {
					JOptionPane.showMessageDialog(null, "This label is only availible in the Windows NXT client version, unfortunately.", "Sorry.", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		GrabInternalBuildLabel.setBounds(475, 15, 225, 25);
		SpecialMechanicsTab.add(GrabInternalBuildLabel);

		ClearConsole = new JButton("Clear the Developer Console");
		ClearConsole.setToolTipText(Storage.CLEAR_DEV_CONSOLE_LOGS_TOOLTIP);
		ClearConsole.setFont(new Font("Dialog", Font.PLAIN, 11));
		ClearConsole.addActionListener(e -> {
			if (DeveloperConsoleHistoryTable.isShowing()){
				DeveloperConsoleHistoryTable.clearSelection();
			}

			if (DeveloperConsoleHistoryTable.isEditing()){
				DeveloperConsoleHistoryTable.getCellEditor().cancelCellEditing();
			}

			for(int i = 0; i < 100; i++){
				DeveloperConsoleHistoryTable.setValueAt("", i, 1);
			}
		});
		ClearConsole.setBounds(15, 50, 225, 25);
		SpecialMechanicsTab.add(ClearConsole);

		PlayerConsole = new JButton("Populate the Dev-Console (Players)");
		PlayerConsole.setToolTipText(Storage.POPULATE_PLAYER_DEV_CONSOLE_LOGS_TOOLTIP);
		PlayerConsole.setFont(new Font("Dialog", Font.PLAIN, 11));
		PlayerConsole.addActionListener(e -> {
			if (DeveloperConsoleHistoryTable.isShowing()){
				DeveloperConsoleHistoryTable.clearSelection();
			}

			if (DeveloperConsoleHistoryTable.isEditing()){
				DeveloperConsoleHistoryTable.getCellEditor().cancelCellEditing();
			}

			for(int i = 0; i < 100; i++){
				DeveloperConsoleHistoryTable.setValueAt("", i, 1);
			}

			for (int i = 0; i < Storage.DEVELOPER_CONSOLE_COMMANDS[0].length; i++){
				DeveloperConsoleHistoryTable.setValueAt(Storage.DEVELOPER_CONSOLE_COMMANDS[0][i], i, 1);
			}
		});
		PlayerConsole.setBounds(245, 50, 225, 25);
		SpecialMechanicsTab.add(PlayerConsole);

		JagexConsole = new JButton("Populate the Dev-Console (Jagex)");
		JagexConsole.setToolTipText(Storage.POPULATE_JAGEX_DEV_CONSOLE_LOGS_TOOLTIP);
		JagexConsole.setFont(new Font("Dialog", Font.PLAIN, 11));
		JagexConsole.addActionListener(e -> {
			if (DeveloperConsoleHistoryTable.isShowing()){
				DeveloperConsoleHistoryTable.clearSelection();
			}

			if (DeveloperConsoleHistoryTable.isEditing()){
				DeveloperConsoleHistoryTable.getCellEditor().cancelCellEditing();
			}

			for(int i = 0; i < 100; i++){
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
				new Object[100][2],
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
						"Due to client limitations only the last 100 commands used will be shown in this table."
				);
		DeveloperConsoleHistoryTable.getTableHeader().setReorderingAllowed(false);
		DeveloperConsoleHistoryTable.getTableHeader().setResizingAllowed(false);
		DeveloperConsoleHistoryTable.setFillsViewportHeight(true);
		DeveloperConsoleHistoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		DeveloperConsoleHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		final JScrollPane pane = new JScrollPane(DeveloperConsoleHistoryTable);
		pane.getVerticalScrollBar().setCursor(VResizeCursor);
		pane.setBounds(15, 85, 685, 440);
		SpecialMechanicsTab.add(pane);
		for(int i = 0; i < 100; i++){
			DeveloperConsoleHistoryTable.setValueAt(i+1, i, 0);
		}



		/* Bottom Pane */



		ShowSensitiveInformation = new JCheckBox("Show sensitive info?");
		ShowSensitiveInformation.setFont(new Font("Dialog", Font.PLAIN, 12));
		ShowSensitiveInformation.setToolTipText("Show sensitive information such as your username?");
		ShowSensitiveInformation.setBounds(15, 610, 185, 25);
		ShowSensitiveInformation.setBackground(optionBackgroundColor);
		contentPane.add(ShowSensitiveInformation);

		AllowWritingCheckbox = new JCheckBox("Enable writing?");
		AllowWritingCheckbox.setFont(new Font("Dialog", Font.PLAIN, 12));
		AllowWritingCheckbox.setToolTipText("Allow writing of all settings when 'Write Settings' is clicked. Some special mechanic values will be written instantly.");
		AllowWritingCheckbox.addActionListener(e -> {
			if (AllowWritingCheckbox.isSelected() && !Storage.DEVELOPER_ReadOnlyCache) {
				Mechanics.ToggleEnabled("Writing", true);
			}	else {
				Mechanics.ToggleEnabled("Writing", false);
			}
		});

		AllowWritingCheckbox.setBounds(205, 610, 160, 25);
		AllowWritingCheckbox.setBackground(optionBackgroundColor);
		contentPane.add(AllowWritingCheckbox);

		GraphicsPresets = new JCheckBox("Enable presets?");
		GraphicsPresets.setFont(new Font("Dialog", Font.PLAIN, 12));
		GraphicsPresets.setToolTipText(Storage.GRAPHICS_PRESET_TOOLTIP);
		GraphicsPresets.addActionListener(e -> {
			if (GraphicsPresets.isSelected()) {
				Mechanics.ToggleEnabled("Presets", true);
			} else {
				Mechanics.ToggleEnabled("Presets", false);
			}
		});
		GraphicsPresets.setBounds(370, 610, 132, 25);
		GraphicsPresets.setBackground(optionBackgroundColor);
		contentPane.add(GraphicsPresets);

		ReadSettings = new JButton("Read Settings");
		ReadSettings.setFont(new Font("Dialog", Font.PLAIN, 12));
		ReadSettings.setToolTipText("Read information currently saved in your setting file(s).");
		ReadSettings.addActionListener(e -> JCache.Read());
		ReadSettings.setBounds(510, 610, 110, 25);
		contentPane.add(ReadSettings);

		WriteSettings = new JButton("Write Settings");
		WriteSettings.setFont(new Font("Dialog", Font.PLAIN, 12));
		WriteSettings.addActionListener(e -> JCache.Write());
		WriteSettings.setBounds(625, 610, 110, 25);
		WriteSettings.setEnabled(false);
		contentPane.add(WriteSettings);

		JCache.Read();
		if (!History.History_Saved){
			History.init();
		}
	}
	private static int GetRow(int Row){
		final int Value = (15 + (30 * Row));
		return Value;
	}
}