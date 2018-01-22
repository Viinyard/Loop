package fr.dauphine.javaavance.phineloops.figure;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GridBuilder extends Observable {
	
	private int width = 0;
	private int height = 0;
	
	private ArrayList<Figure> alFigure;
	
	public GridBuilder() {
		this.alFigure = new ArrayList<Figure>();
	}
	
	public GridBuilder setWidth(int width) {
		if(width <= 0) {
			throw new RuntimeException("Grid Width must be greater than zero !");
		}
		System.out.println("Width is set to : "+width);
		this.width = width;
		return this;
	}

	public GridBuilder setHeight(int height) {
		if(height <= 0) {
			throw new RuntimeException("Grid Width must be greater than zero !");
		}
		System.out.println("Height is set to : "+height);
		this.height = height;
		return this;
	}
	
	public GridBuilder addFigure(int figure, int orientation) {
		Figure f;
		switch(figure) {
		case 0 :
			f = new FigureVide();
			break;
		case 1 :
			f = new Figure1();
			break;
		case 2 :
			f = new Figure2();
			break;
		case 3 :
			f = new Figure3();
			break;
		case 4 :
			f = new Figure4();
			break;
		case 5 :
			f = new FigureL();
			break;
			
			default :
				throw new RuntimeException("Bad figure number exception");
		}
		
		f.setOrientation(orientation);
		
		this.alFigure.add(f);
		
		return this;
	}
	
	public GridModel build() {
		
		if(this.width <= 0 || this.height <= 0) {
			throw new RuntimeException("Grid size is not set !");
		}
		
		Figure[][] tabFigure = new Figure[this.width][this.height];
		if(this.alFigure.size() != (tabFigure.length * tabFigure[0].length)) {
			throw new RuntimeException("Bad number of figure : attempt ["+this.width+"]x["+this.height+"] = "+(this.width * this.height)+", but have "+this.alFigure.size()+" !");
		}
		
		for(int x = 0; x < this.width; x++) {
			for(int y = 0; y < this.height; y++) {
				tabFigure[x][y] = this.alFigure.get(x * y);
			}
		}
		
		return new GridModel(tabFigure);
	}
	
	public class GridModel extends Observable {
		private Figure[][] grid;
		private Rectangle bounds;
		private GridModel(Figure[][] grid) {
			this.grid = grid;
			this.bounds = new Rectangle(this.grid.length, this.grid[0].length);
			this.notifyObservers(this.grid);
		}
		
		public Figure[][] getGrid() {
			return this.grid;
		}
		
		public Figure getFigureDest(Connection connection, Point pOrig) {
			Point pDest = connection.getDest(pOrig);
			if(!this.bounds.contains(pDest)) {
				return null;
			}
			
			return this.grid[pDest.x][pDest.y];
		}
		
		public boolean isPossible(Connection connection, Point pOrig) {
			Point pDest = connection.getDest(pOrig);
			if(!this.bounds.contains(pDest)) {
				return false;
			}
			
			return !(this.grid[pDest.x][pDest.y] instanceof FigureVide);
		}
		
		@Override
		public void addObserver(Observer o) {
			super.addObserver(o);
			o.update(this, this.grid);
		}
	}
}
