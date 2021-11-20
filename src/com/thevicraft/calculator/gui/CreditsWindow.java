package com.thevicraft.calculator.gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

@SuppressWarnings("serial")
public class CreditsWindow extends JFrame {
	public Font normal; // = new Font("Tahoma", Font.BOLD, 12);

	private static String label = "<html><body>This GUI-Calculator, published under the name Simple Calculator, "
			+ "has the open-source license and is only a test project. If you wish to share this project, "
			+ "you should link to the github-page and the author 'thevicraft'. If you wish to report a bug, "
			+ "then you should use then you should the 'Report Bug' button to do so.";
	// <html><body>Textzeile1<br>Textzeile2</body></html>
	private int FRAME_WIDTH;
	private int FRAME_HEIGHT;

	private JPanel mainPanel;

	private JLabel mainLabel;

	private float sizeFactor;

	public CreditsWindow(String titel, int width, int height, float factor, Color mode) {
		FRAME_HEIGHT = (int) (height * factor);
		FRAME_WIDTH = (int) (width * factor);
		sizeFactor = factor;
		setTitle(titel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		initPanel();
		initComponents();
		mainPanel.add(mainLabel);
		add(mainPanel);
		setLocationRelativeTo(null);
		setColorOfComponents(mode);
		setVisible(true);
	}

	private void initPanel() {
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

	}

	private void initComponents() {
		normal = new Font("Tahoma", Font.BOLD, (int) (12 * sizeFactor));
		mainLabel = new JLabel();
		mainLabel.setPreferredSize(new Dimension(FRAME_WIDTH - 10, FRAME_HEIGHT - 10));
		mainLabel.setBackground(Color.black);
		mainLabel.setFont(normal);
		mainLabel.setText(label);
	}
	private void setColorOfComponents(Color d){
		getContentPane().setBackground(d);
		mainPanel.setBackground(d);
		mainLabel.setBackground(d);
		
		if(d.equals(GuiTaschenrechner.dark)) {
			d = GuiTaschenrechner.bright;
		}else {
			d = GuiTaschenrechner.dark;
		}
		mainLabel.setForeground(d);
		
	}

}
