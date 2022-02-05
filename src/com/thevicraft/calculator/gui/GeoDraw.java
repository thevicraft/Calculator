package com.thevicraft.calculator.gui;

import javax.swing.JButton;
import javax.swing.JColorChooser;

import com.thevicraft.calculator.integration.Print;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.thevicraft.calculator.console.Log;
import com.thevicraft.calculator.gui.Images.Pictures;

import java.awt.Component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

/**
 * Handler of GeoDraw Frame
 * 
 * @author thevicraft
 * @category JFrame
 */
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
	public JButton color;
	public JLabel functionLabel;

	private int scaleFactor;
	private int originX;
	private int originY;

	private JButton print;

	private String function;

	private Color mode;

	private Color graphColor = Color.green; // default Color

	public boolean ctrlPressed = false;

	public GeoDraw(String titel, int width, int height, float factor, Color mode, String function) {

		FRAME_HEIGHT = (int) (height * factor);
		FRAME_WIDTH = (int) (width * factor);
		this.function = function;
		this.mode = mode;
		sizeFactor = factor;
		scaleFactor = 40;
		// originX = 400;
		// originY = 300;
		originX = FRAME_WIDTH / 2;
		originY = FRAME_HEIGHT / 2;
		setTitle(titel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setMinimumSize(new Dimension(250, 250));
		setResizable(true);
		initPanel();
		initComponents();
		setLocationRelativeTo(null);
		setColorOfComponents(mode);
		setVisible(false);
		graph = new Graph(FRAME_WIDTH, FRAME_HEIGHT, 40, originX, originY, this.function, mode, graphColor, true);
		setVisible(false);
		mainPanel.add(graphPanel);
		graphPanel.add(graph);

		addActionListeners();
		
		mainPanel.add(functionLabel);
		mainPanel.add(color);
		mainPanel.add(zoomOut);
		mainPanel.add(zoomIn);
		mainPanel.add(print);

		mainPanel.add(padPanel);

		// --------------------------------------------
		padPanel.add(up, "North");
		padPanel.add(right, "East");
		padPanel.add(down, "Center");
		padPanel.add(left, "West");
		// -------------------------------------------

		for (Component c : mainPanel.getComponents()) {
			c.setFocusable(false);
		}
		for (Component c : padPanel.getComponents()) {
			c.setFocusable(false);
		}
		add(mainPanel);
		setIconOfComponents();
		setVisible(true);
		// graph.print(graph.getGraphics());
	}

	private void setIconOfComponents() {
		up.setIcon(Images.scaleImageIconFromDefault(Pictures.ARROW_UP, 25, 25));
		down.setIcon(Images.scaleImageIconFromDefault(Pictures.ARROW_DOWN, 25, 25));
		left.setIcon(Images.scaleImageIconFromDefault(Pictures.ARROW_LEFT, 25, 25));
		right.setIcon(Images.scaleImageIconFromDefault(Pictures.ARROW_RIGHT, 25, 25));
	}

	private void updateGraph() {
		graphPanel.removeAll();
		graph = new Graph(FRAME_WIDTH, FRAME_HEIGHT - 100, scaleFactor, originX, originY, function, mode, graphColor);
		// graph.update(FRAME_WIDTH, FRAME_HEIGHT - 100, scaleFactor, originX, originY,
		// function, mode,graphColor);
		graphPanel.add(graph);
		setSize(FRAME_WIDTH, FRAME_HEIGHT - 1);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		functionLabel.setForeground(graphColor);
	}

	private void initPanel() {
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		graphPanel = new JPanel();
		padPanel = new JPanel();
		padPanel.setLayout(new BorderLayout());
		padPanel.setPreferredSize(new Dimension(100, 50));
		graphPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT - 100));
	}

	private int jumpTo() {
		if (ctrlPressed == true) {
			return 50;
		}
		return 10;
	}

	private void addActionListeners() {
		color.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				graphColor = JColorChooser.showDialog(GeoDraw.this, "Select Graph Color", Color.green);
				updateGraph();

			}
		});
		zoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scaleFactor += jumpTo();
				updateGraph();
			}
		});
		zoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scaleFactor -= jumpTo();
				if (scaleFactor < 10) {
					scaleFactor = 10;
				}
				updateGraph();
			}
		});
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				originY += jumpTo();
				updateGraph();
			}
		});
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				originY -= jumpTo();
				updateGraph();
			}
		});
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				originX += jumpTo();
				updateGraph();
			}
		});
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				originX -= jumpTo();
				updateGraph();
			}
		});
		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// PrintService printService=PrintServiceLookup
				// .lookupDefaultPrintService();
				// System.out.println(printService.getName());
				// graph.setBackground(GuiTaschenrechner.dark);
				try {
					Print.printComponent(graphPanel);
					// new Print(graphPanel).print(graphPanel.getGraphics(), null, 1);
				} catch (Exception e2) {
					// TODO: handle exception
					Log.console(e2.toString());
				}
			}
		});
	}

	private void initComponents() {
		normal = new Font("System", Font.BOLD, (int) (12 * sizeFactor));

		color = new JButton("Color");
		color.setFont(new Font("System", Font.BOLD, 9));
		zoomOut = new JButton("-");
		zoomIn = new JButton("+");
		Dimension pad = new Dimension(25, 25);
		up = new JButton();
		right = new JButton();
		down = new JButton();
		left = new JButton();
		color.setPreferredSize(new Dimension(60, 25));
		up.setPreferredSize(pad);
		right.setPreferredSize(pad);
		down.setPreferredSize(pad);
		left.setPreferredSize(pad);
		print = new JButton("PRINT");
		
		functionLabel = new JLabel("f(x) = "+function);
		functionLabel.setFont(new Font("System", Font.BOLD, (int) (20 * sizeFactor)));
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
		
		functionLabel.setForeground(graphColor);
		if (d.equals(GuiTaschenrechner.dark)) {
			d = GuiTaschenrechner.bright;
		} else {
			d = GuiTaschenrechner.dark;
		}
		// foregrounds that are reversed due to mode

	}
}
