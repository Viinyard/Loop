package fr.dauphine.javaavance.phineloops.figure;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Figure3 extends Figure {
	public static final int NORD_EST_OUEST = 0;
	public static final int NORD_SUD_EST = 1;
	public static final int EST_SUD_OUEST = 2;
	public static final int NORD_SUD_OUEST = 3;
	
	@Override
	public int getNbConnection() {
		return 2;
	}
	
	@Override
	public void drawFigure(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		switch(this.orientation) {
		case NORD_EST_OUEST :
			g2.rotate(Math.toRadians(0), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		case NORD_SUD_EST :
			g2.rotate(Math.toRadians(90), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		case EST_SUD_OUEST :
			g2.rotate(Math.toRadians(90), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		case NORD_SUD_OUEST :
			g2.rotate(Math.toRadians(90), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		default :
			throw new RuntimeException("Bad Orientation Exception !");
		}
		
		g2.draw(new Line2D.Double(0, Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth(), Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0));
		g2.draw(new Line2D.Double(0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0));
		g2.draw(new Line2D.Double(Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getWidth(), Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0));
		g2.draw(new Line2D.Double(Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, 0, Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0));
		g2.draw(new Line2D.Double(Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, 0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0));
		
		g2.setTransform(at);
	}

	@Override
	public Connection[] getConnections(int orientation) {
		switch(orientation) {
		case NORD_EST_OUEST :
			return new Connection[] {
					Connection.NORD,
					Connection.EST,
					Connection.OUEST
			};
		case NORD_SUD_EST :
			return new Connection[] {
					Connection.NORD,
					Connection.EST,
					Connection.SUD
			};
		case EST_SUD_OUEST :
			return new Connection[] {
					Connection.EST,
					Connection.SUD,
					Connection.OUEST
			};
		case NORD_SUD_OUEST :
			return new Connection[] {
					Connection.NORD,
					Connection.SUD,
					Connection.OUEST
			};
			default :
				throw new RuntimeException("Bad Orientation Exception !");
		}
	}

	@Override
	public int[] getOrientations() {
		return new int[] {
				Figure3.EST_SUD_OUEST,
				Figure3.NORD_EST_OUEST,
				Figure3.NORD_SUD_EST,
				Figure3.NORD_SUD_OUEST
		};
	}
	
	
}
