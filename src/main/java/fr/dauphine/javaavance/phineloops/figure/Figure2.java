package fr.dauphine.javaavance.phineloops.figure;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Figure2 extends Figure {
	public static final int NORD_SUD = 0;
	public static final int EST_OUEST = 1;
	
	@Override
	public int getNbConnection() {
		return 2;
	}
	
	@Override
	public void drawFigure(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		switch(this.orientation) {
		case NORD_SUD :
			g2.rotate(Math.toRadians(0), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		case EST_OUEST :
			g2.rotate(Math.toRadians(90), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		default :
			throw new RuntimeException("Bad Orientation Exception !");
		}
		
		g2.draw(new Line2D.Double(Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, 0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getHeight()));
		g2.draw(new Line2D.Double(Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, 0, Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getHeight()));
	
		g2.setTransform(at);
	}

	@Override
	public Connection[] getConnections(int orientation) {
		switch(orientation) {
		case NORD_SUD :
			return new Connection[] {
					Connection.NORD,
					Connection.SUD
			};
		case EST_OUEST :
			return new Connection[] {
					Connection.EST,
					Connection.OUEST
			};
			default :
				throw new RuntimeException("Bad Orientation Exception !");
		}
	}

	@Override
	public int[] getOrientations() {
		return new int[] {
				Figure2.NORD_SUD,
				Figure2.EST_OUEST
		};
	}
}
