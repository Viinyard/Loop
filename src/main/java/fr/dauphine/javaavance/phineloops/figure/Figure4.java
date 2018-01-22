package fr.dauphine.javaavance.phineloops.figure;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Figure4 extends Figure {
	public static final int NORD_SUD_EST_OUEST = 0;
	
	@Override
	public int getNbConnection() {
		return 2;
	}
	
	@Override
	public void drawFigure(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		switch(this.orientation) {
		case NORD_SUD_EST_OUEST :
			g2.rotate(Math.toRadians(0), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		default :
			throw new RuntimeException("Bad Orientation Exception !");
		}
		
		g2.draw(new Line2D.Double(0, Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0));
		g2.draw(new Line2D.Double(Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth(), Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0));
		g2.draw(new Line2D.Double(Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getWidth()));
		g2.draw(new Line2D.Double(Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth()));
		
		g2.draw(new Line2D.Double(0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0));
		g2.draw(new Line2D.Double(Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getWidth(), Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0));
		g2.draw(new Line2D.Double(Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, 0, Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0));
		g2.draw(new Line2D.Double(Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, 0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0));
		
		g2.setTransform(at);
	}
	
	@Override
	public Connection[] getConnections(int orientation) {
		return new Connection[] {
				Connection.NORD,
				Connection.SUD,
				Connection.EST,
				Connection.OUEST
		};
	}

	@Override
	public int[] getOrientations() {
		return new int[] {
				Figure4.NORD_SUD_EST_OUEST
		};
	}
}
