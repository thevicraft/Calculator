package com.thevicraft.calculator.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

/**
 * Loader with loading Bar to show a loading sequence / working process
 * 
 * @author thevicraft
 * @category JFrame
 * @see GuiTaschenrechner
 * 
 */
@SuppressWarnings("serial")
public class Loader extends JFrame {

	public JProgressBar loadBar;

	public JPanel loadCircle;

	public Thread anim;

	private int count;
	private Timer timer;
	private Graphics2D g2d;
	
	private Color backbround = new Color(207, 207, 207);

	/**
	 * Constructor for Loader
	 * 
	 * @param typeLoading - title of the loader
	 * @param max_value   - maximum of loading value steps (e.g.: the loader needs
	 *                    to show how 78 images are loaded, so this number is set to
	 *                    78)
	 * @author thevicraft
	 * @see Loader
	 * 
	 */
	public Loader(String typeLoading, int max_value) {
		setTitle("Loading " + typeLoading + "...");
		setSize(310, 175);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(backbround);
		
		loadBar = new JProgressBar(0, max_value);
		loadBar.setPreferredSize(new Dimension(300, 25));
		loadBar.setStringPainted(true);
		loadBar.setForeground(new Color(0, 230, 27));
		loadBar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		Image icon = Images.fromFileName("loading.png").getImage();// .getScaledInstance(64, 64, 0);
		
		loadCircle = new JPanel() {

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g2d = (Graphics2D) g;
				float x = this.getWidth() / 2.0f;
				float y = this.getHeight() / 2.0f;
				g2d.rotate(count / 180.0 * Math.PI, x, y);
				// the length of the panel (100) minus the height of the image (64) equals 36,
				// to positionate it correctly it has to be the half, so 18
				g2d.drawImage(icon, 18, 18, null);
			}

			@Override
			public void paintChildren(Graphics g) {
				super.paintChildren(g2d);
			}
		};

		timer = new Timer(3, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				count += 1;
				if (count > 360)
					count -= 360;
				loadCircle.repaint();
			}
		});
		loadCircle.setPreferredSize(new Dimension(100, 100));
		loadCircle.setBackground(backbround);
		add(loadBar);
		add(loadCircle);
		loadBar.setValue(0);
		setIconImage(icon);
		setVisible(true);
		loadBar.updateUI();
		// loadAnim();
		timer.start();
	}

	/**
	 * Method to iterate the loader after a certain loading step
	 * 
	 * @author thevicraft
	 * @see Loader
	 * 
	 */
	public void iterate() {
		if (loadBar.getMaximum() >= loadBar.getValue())
			loadBar.setValue(loadBar.getValue() + 1);
	}

	public void disposeIt() {
		try {
			timer.stop();
		} catch (Exception e) {
		}
		dispose();
	}
}
