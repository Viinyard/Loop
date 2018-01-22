package fr.dauphine.javaavance.phineloops.figure;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import fr.dauphine.javaavance.phineloops.figure.GridBuilder.GridModel;

public abstract class Figure {
	
	public static Dimension dimension = new Dimension(64, 64);
	public static int size = 12;
	protected int orientation = 0;
	
	public Figure() {
	}
	
	public abstract void drawFigure(Graphics2D g2);
	
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	
	public int getOrientation() {
		return this.orientation;
	}
	
	public boolean isOrientationPossible(GridModel gridModel, Point pOrig, int orientation) {
		for(Connection connection : this.getConnections(orientation)) {
			if(!gridModel.isPossible(connection, pOrig)) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<Integer> getConnectionsPossibles(GridModel gridModel, Point pOrig) {
		ArrayList<Integer> cPos = new ArrayList<Integer>();
		for(int orientation : this.getOrientations()) {
			if(this.isOrientationPossible(gridModel, pOrig, orientation)) {
				cPos.add(orientation);
			}
		}
		return cPos;
	}
	
	public boolean isConnection(GridModel gridModel, Point pOrig, Connection connection) {
		Figure fDest = gridModel.getFigureDest(connection, pOrig);
		switch(connection) {
		case EST:
			for(Connection cDest : fDest.getConnections()) {
				if(cDest.equals(Connection.OUEST)) {
					return true;
				}
			}
			break;
		case NORD:
			for(Connection cDest : fDest.getConnections()) {
				if(cDest.equals(Connection.SUD)) {
					return true;
				}
			}
			break;
		case OUEST:
			for(Connection cDest : fDest.getConnections()) {
				if(cDest.equals(Connection.EST)) {
					return true;
				}
			}
			break;
		case SUD:
			for(Connection cDest : fDest.getConnections()) {
				if(cDest.equals(Connection.NORD)) {
					return true;
				}
			}
			break;
		}

		return false;
	}
	
	public int getNbConnection(GridModel gridModel, Point pOrig) {
		int cpt = 0;
		for(Connection connection : this.getConnections()) {
			if(this.isConnection(gridModel, pOrig, connection)) {
				cpt++;
			}
		}
		return cpt;
	}
	
	public boolean isAllConnected(GridModel gridModel, Point pOrig) {
		for(Connection connection : this.getConnections()) {
			if(!this.isConnection(gridModel, pOrig, connection)) {
				return false;
			}
		}
		return true;
	}
	
	public abstract int[] getOrientations();
	
	public Connection[] getConnections() {
		return this.getConnections(this.getOrientation());
	}
	
	public abstract Connection[] getConnections(int orientation);
	
	public abstract int getNbConnection();
}
