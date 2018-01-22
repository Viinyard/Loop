package fr.dauphine.javaavance.phineloops.figure;

import java.awt.Graphics2D;

public class FigureVide extends Figure {

	@Override
	public void drawFigure(Graphics2D g2) {
		// nothing to draw
	}

	@Override
	public int getNbConnection() {
		return 0;
	}

	@Override
	public Connection[] getConnections(int orientation) {
		return new Connection[0];
	}

	@Override
	public int[] getOrientations() {
		return new int[0];
	}

}
