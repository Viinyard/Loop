package fr.dauphine.javaavance.phineloops;

import fr.dauphine.javaavance.phineloops.figure.GridBuilder;
import fr.dauphine.javaavance.phineloops.figure.GridBuilder.GridModel;

public class MainTest {
	
	public static void main(String[] args) {
		Grid grid = new Grid();
		
		
		GridBuilder builder = new GridBuilder();
		
		builder.setWidth(1);
		builder.setHeight(1);
		builder.addFigure(2, 0);
		
		GridModel model = builder.build();
		
		model.addObserver(grid);
	}

}
