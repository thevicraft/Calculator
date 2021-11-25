package com.thevicraft.calculator.gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
@SuppressWarnings("serial")
public class GeoDraw extends JFrame{
	public Font normal; // = new Font("Tahoma", Font.BOLD, 12);

	private int FRAME_WIDTH;
	private int FRAME_HEIGHT;

	private JPanel mainPanel;

	private JLabel mainLabel;

	private float sizeFactor;

	public GeoDraw(String titel, int width, int height, float factor, Color mode) {
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
		//add(mainPanel);
		setLocationRelativeTo(null);
		setColorOfComponents(mode);
		add(new Graph(FRAME_WIDTH, FRAME_HEIGHT,40,400,300,"1/X"));
		//add(new JLabel("U+2119"));
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
