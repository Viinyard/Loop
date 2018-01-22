package fr.dauphine.javaavance.phineloops;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.dauphine.javaavance.phineloops.figure.Figure;

public class Grid extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private Figure[][] grid;
	private JFrame frame;
	
	public static void main(String[] args) {
		new Grid();
	}

	public Grid() {
		this.frame = new JFrame();
		this.frame.setTitle("Infinity Loop");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setSize(new Dimension(500, 500));

		this.frame.setContentPane(this);

		this.frame.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = g2.getTransform();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		if(this.grid != null) {
			for(int x = 0; x < this.grid.length; x++) {
				for(int y = 0; y < this.grid[0].length; y++) {
					this.grid[x][y].drawFigure(g2);
					g2.translate(Figure.dimension.width, 0);
				}
				g2.translate(-this.grid[0].length * Figure.dimension.width, Figure.dimension.height);
			}
		}

		g2.setTransform(at);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof Figure[][]) {
			Figure[][] oldGrid = this.grid;
			this.grid = (Figure[][]) arg1;
			
			if(this.grid != oldGrid) {
				this.setPreferredSize(new Dimension(this.grid.length * Figure.dimension.width, this.grid[0].length * Figure.dimension.height));
				this.revalidate();
				
				this.frame.pack();
			}
			
			this.repaint();
		}
	}
}
