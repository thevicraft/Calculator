package com.thevicraft.calculator.gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

@SuppressWarnings("serial")
public class ShortcutWindow extends JFrame implements WindowHelper {
	public Font normal; // = new Font("Tahoma", Font.BOLD, 12);

	static String label = "<html><body>Shortcut Menu:<br>"
			+ "<br>"
			+ "Calculator:<br>"
			+ "[Ctrl,+] Enlarge window<br>"
			+ "[Ctrl,-] Ensize window<br>"
			+ "<br>"
			+ "[Esc] Close Window<br>"
			+ "<br>"
			+ "[Ctrl,o] Open new Calculator Window<br>"
			+ "[F1] Open Help Menu<br>"
			+ "[F3] Open Shortcut Menu<br>"
			+ "<br>"
			+ "[Ctrl,c] Copy result<br>"
			+ "<br>"
			+ "Graph:<br>"
			+ "[+] Zoom in<br>"
			+ "[-] Zoom out<br>"
			+ "<br>"
			+ "[Ctrl,+] Zoom in (far)<br>"
			+ "[Ctrl,-] Zoom out (far)<br>"
			+ "<br>"
			+ "[Key Arrows] Move<br>"
			+ "[Ctrl, Key Arrows] Move (far)<br>"
			+ "<br>";
	// <html><body>Textzeile1<br>Textzeile2</body></html>
	private int FRAME_WIDTH;
	private int FRAME_HEIGHT;

	private JPanel mainPanel;

	private JLabel mainLabel;

	private float sizeFactor;

	public ShortcutWindow(String titel, int width, int height, float factor, Color mode) {
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

	@Override
	public void initPanel() {
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

	}

	@Override
	public void initComponents() {
		normal = new Font("Tahoma", Font.BOLD, (int) (12 * sizeFactor));
		mainLabel = new JLabel();
		mainLabel.setPreferredSize(new Dimension(FRAME_WIDTH - 10, FRAME_HEIGHT - 10));
		mainLabel.setBackground(Color.black);
		mainLabel.setFont(normal);
		mainLabel.setText(label);
	}

	@Override
	public void setColorOfComponents(Color d) {
		getContentPane().setBackground(d);
		mainPanel.setBackground(d);
		mainLabel.setBackground(d);

		if (d.equals(GuiTaschenrechner.dark)) {
			d = GuiTaschenrechner.bright;
		} else {
			d = GuiTaschenrechner.dark;
		}
		mainLabel.setForeground(d);

	}
}