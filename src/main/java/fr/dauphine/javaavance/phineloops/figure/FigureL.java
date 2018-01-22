package fr.dauphine.javaavance.phineloops.figure;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;

public class FigureL extends Figure {

	public static final int NORD_EST = 0;
	public static final int EST_SUD = 1;
	public static final int SUD_OUEST = 2;
	public static final int OUEST_NORD = 3;
	
	@Override
	public int getNbConnection() {
		return 2;
	}

	@Override
	public void drawFigure(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		switch(this.orientation) {
		case NORD_EST :
			g2.rotate(Math.toRadians(180), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		case EST_SUD :
			g2.rotate(Math.toRadians(270), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		case SUD_OUEST :
			g2.rotate(Math.toRadians(0), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		case OUEST_NORD :
			g2.rotate(Math.toRadians(90), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
			default :
				throw new RuntimeException("Bad Orientation Exception !");
		}
		g2.draw(new Arc2D.Double(- Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.dimension.getWidth() + Figure.size , Figure.dimension.getHeight() + Figure.size, 0, 90, Arc2D.OPEN));
		g2.draw(new Arc2D.Double(- Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.dimension.getWidth() - Figure.size , Figure.dimension.getHeight() - Figure.size, 0, 90, Arc2D.OPEN));
	
		g2.setTransform(at);
	}
	
	@Override
	public Connection[] getConnections(int orientation) {
		switch(orientation) {
		case NORD_EST :
			return new Connection[] {
					Connection.NORD,
					Connection.EST
			};
		case EST_SUD :
			return new Connection[] {
					Connection.EST,
					Connection.SUD
			};
		case SUD_OUEST :
			return new Connection[] {
					Connection.SUD,
					Connection.OUEST
			};
		case OUEST_NORD :
			return new Connection[] {
					Connection.OUEST,
					Connection.NORD
			};
			default :
				throw new RuntimeException("Bad Orientation Exception !");
		}
	}

	@Override
	public int[] getOrientations() {
		return new int[] {
				FigureL.EST_SUD,
				FigureL.NORD_EST,
				FigureL.OUEST_NORD,
				FigureL.SUD_OUEST
		};
	}
}
