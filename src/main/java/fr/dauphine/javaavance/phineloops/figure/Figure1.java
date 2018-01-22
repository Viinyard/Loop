package fr.dauphine.javaavance.phineloops.figure;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import fr.dauphine.javaavance.phineloops.figure.GridBuilder.GridModel;

public class Figure1 extends Figure {
	
	public static final int NORD = 0;
	public static final int EST = 1;
	public static final int SUD = 2;
	public static final int OUEST = 3;
	
	@Override
	public int getNbConnection() {
		return 1;
	}
	
	@Override
	public void drawFigure(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		switch(this.orientation) {
		case NORD :
			g2.rotate(Math.toRadians(0), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		case EST :
			g2.rotate(Math.toRadians(90), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		case SUD :
			g2.rotate(Math.toRadians(180), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		case OUEST :
			g2.rotate(Math.toRadians(270), Figure.dimension.getWidth() / 2.0, Figure.dimension.getHeight() / 2.0);
			break;
		default :
			throw new RuntimeException("Bad Orientation Exception !");
		}
		
		g2.draw(new Arc2D.Double(Figure.size, Figure.size, Figure.dimension.getWidth() - Figure.size * 2.0, Figure.dimension.getHeight() - Figure.size * 2.0, 110, 320, Arc2D.OPEN));
		g2.draw(new Ellipse2D.Double(2 * Figure.size, 2 * Figure.size, Figure.dimension.getWidth() - Figure.size * 4.0, Figure.dimension.getHeight() - Figure.size * 4.0));
		g2.draw(new Line2D.Double(Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, 0, Figure.dimension.getWidth() / 2.0 - Figure.size / 2.0, Figure.size));
		g2.draw(new Line2D.Double(Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, 0, Figure.dimension.getWidth() / 2.0 + Figure.size / 2.0, Figure.size));
	
		g2.setTransform(at);
	}

	@Override
	public Connection[] getConnections(int orientation) {
		switch(orientation) {
		case NORD :
			return new Connection[] {
					Connection.NORD
			};
		case SUD :
			return new Connection[] {
					Connection.SUD
			};
		case EST :
			return new Connection[] {
					Connection.EST
			};
		case OUEST :
			return new Connection[] {
					Connection.OUEST
			};
			default :
				throw new RuntimeException("Bad Orientation Exception !");
		}
	}

	@Override
	public int[] getOrientations() {
		return new int[] {
				Figure1.NORD,
				Figure1.SUD,
				Figure1.EST,
				Figure1.OUEST
		};
	}
	
}
