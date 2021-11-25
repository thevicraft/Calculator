package com.thevicraft.calculator.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.thevicraft.calculator.gui.Images.Pictures;

import java.awt.Component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class GeoDraw extends JFrame {
	public Font normal; // = new Font("Tahoma", Font.BOLD, 12);

	private int FRAME_WIDTH;
	private int FRAME_HEIGHT;

	private JPanel mainPanel;
	private JPanel graphPanel;
	private JPanel padPanel;

	private float sizeFactor;
	
	private Graph graph;
	public JButton zoomIn;
	public JButton zoomOut;
	public JButton up;
	public JButton right;
	public JButton down;
	public JButton left;
	
	private int scaleFactor;
	private int originX;
	private int originY;

	public GeoDraw(String titel, int width, int height, float factor, Color mode) {
		FRAME_HEIGHT = (int) (height * factor);
		FRAME_WIDTH = (int) (width * factor);
		sizeFactor = factor;
		scaleFactor = 40;
		originX = 400;
		originY = 300;
		setTitle(titel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(true);
		initPanel();
		initComponents();
		setLocationRelativeTo(null);
		setColorOfComponents(mode);
		
		graph = new Graph(FRAME_WIDTH, FRAME_HEIGHT , 40, originX, originY, "(X^3)+2*(X^2)-X-3"/* "X" */);
		
		mainPanel.add(graphPanel);
		graphPanel.add(graph);
		
		addActionListeners();
		
		mainPanel.add(zoomOut);
		mainPanel.add(zoomIn);
		
		mainPanel.add(padPanel);
		
		//--------------------------------------------
		padPanel.add(up,"North");
		padPanel.add(right,"East");
		padPanel.add(down,"Center");
		padPanel.add(left,"West");
		//-------------------------------------------
		
		for (Component c : mainPanel.getComponents()) {
			c.setFocusable(false);
		}
		for (Component c : padPanel.getComponents()) {
			c.setFocusable(false);
		}
		add(mainPanel);
		setIconOfComponents();
		setVisible(true);
	}
	private void setIconOfComponents() {
		up.setIcon(Images.scaleImageIconFromDefault(Pictures.ARROW_UP, 25, 25));
		down.setIcon(Images.scaleImageIconFromDefault(Pictures.ARROW_DOWN, 25, 25));
		left.setIcon(Images.scaleImageIconFromDefault(Pictures.ARROW_LEFT, 25, 25));
		right.setIcon(Images.scaleImageIconFromDefault(Pictures.ARROW_RIGHT, 25, 25));
	}
	
	private void updateGraph() {
		graphPanel.removeAll();
		graph = new Graph(FRAME_WIDTH, FRAME_HEIGHT - 100, scaleFactor, originX, originY, "(X^3)+2*(X^2)-X-3"/* "X" */);
		graphPanel.add(graph);
		setSize(FRAME_WIDTH, FRAME_HEIGHT-1);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	private void initPanel() {
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		graphPanel = new JPanel();
		padPanel = new JPanel();
		padPanel.setLayout(new BorderLayout());
		padPanel.setPreferredSize(new Dimension(100,50));
		graphPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT - 100));
	}
	private void addActionListeners() {
		zoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scaleFactor += 10;
				updateGraph();
			}
		});
		
		zoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scaleFactor -= 10;
				updateGraph();
			}
		});
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				originY += 10;
				updateGraph();
			}
		});
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				originY -= 10;
				updateGraph();
			}
		});
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				originX += 10;
				updateGraph();
			}
		});
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				originX -= 10;
				updateGraph();
			}
		});
	}

	private void initComponents() {
		normal = new Font("Tahoma", Font.BOLD, (int) (12 * sizeFactor));
		
		zoomOut = new JButton("-");
		zoomIn = new JButton("+");
		Dimension pad = new Dimension(25,25);
		up = new JButton();
		right  = new JButton();
		down  = new JButton();
		left  = new JButton();
		up.setPreferredSize(pad);
		right.setPreferredSize(pad);
		down.setPreferredSize(pad);
		left.setPreferredSize(pad);
	}

	private void setColorOfComponents(Color d) {
		getContentPane().setBackground(d);
		mainPanel.setBackground(d);
		padPanel.setBackground(d);
		graphPanel.setBackground(d);
		up.setBackground(d);
		down.setBackground(d);
		left.setBackground(d);
		right.setBackground(d);
		
		if (d.equals(GuiTaschenrechner.dark)) {
			d = GuiTaschenrechner.bright;
		} else {
			d = GuiTaschenrechner.dark;
		}
		// foregrounds that are reversed due to mode
	}
}
