package fr.dauphine.javaavance.phineloops.figure;

import java.awt.Point;

public enum Connection {
	NORD(0, 1),
	SUD(0, -1),
	EST(1, 0),
	OUEST(-1, 0);
	
	private int x;
	private int y;
	
	Connection(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point getDest(Point pOrig) {
		return new Point(pOrig.x + this.x, pOrig.y + this.y);
	}
}
