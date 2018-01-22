package fr.dauphine.javaavance.phineloops; 

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import fr.dauphine.javaavance.phineloops.figure.GridBuilder;
import fr.dauphine.javaavance.phineloops.figure.GridBuilder.GridModel;

public class Main {
    private static String  inputFile= null;  
    private static String  outputFile= null;
    private static Integer width = -1;
    private static Integer height = -1;
    private static Integer maxcc = -1; 
    
    
    public static void main(String[] args) {
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        
        options.addOption("g", "generate ", true, "Generate a grid of size height x width.");
        options.addOption("c", "check", true, "Check whether the grid in <arg> is solved.");
        
        options.addOption("s", "solve", true, "Solve the grid stored in <arg>.");   
        options.addOption("o", "output", true, "Store the generated or solved grid in <arg>. (Use only with --generate and --solve.)");
        options.addOption("t", "threads", true, "Maximum number of solver threads. (Use only with --solve.)");
        options.addOption("x", "nbcc", true, "Maximum number of connected components. (Use only with --generate.)");
        options.addOption("h", "help", false, "Display this help");
        
        try {
            cmd = parser.parse( options, args);         
        } catch (ParseException e) {
            System.err.println("Error: invalid command line format.");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "phineloops", options );
            System.exit(1);
        }       
                
    try{    
        if( cmd.hasOption( "g" ) ) {
            System.out.println("Running phineloops generator.");
            String[] gridformat = cmd.getOptionValue( "g" ).split("x");
            height = Integer.parseInt(gridformat[0]);
            width = Integer.parseInt(gridformat[1]); 
            if(! cmd.hasOption("o")) throw new ParseException("Missing mandatory --output argument.");
            outputFile = cmd.getOptionValue( "o" );

            // generate grid and store it to outputFile...
            //...            
        }
        else if( cmd.hasOption( "s" ) ) {
            System.out.println("Running phineloops solver.");
            inputFile = cmd.getOptionValue( "s" );
            if(! cmd.hasOption("o")) throw new ParseException("Missing mandatory --output argument.");      
            outputFile = cmd.getOptionValue( "o" );
            boolean solved = false; 
        
            // load grid from inputFile, solve it and store result to outputFile...
            // ...
            
            System.out.println("SOLVED: " + solved);            
        }
        
        else if( cmd.hasOption( "c" )) {
            System.out.println("Running phineloops checker.");
            inputFile = cmd.getOptionValue( "c" );
            boolean solved = true; 
            
            
            Grid grid = new Grid();
            
            try {
				FileReader fr = new FileReader(inputFile);
				BufferedReader br = new BufferedReader(fr);
				String line;
				int cpt = 0;
				GridBuilder builder = new GridBuilder();
				while((line = br.readLine()) != null) {
					switch(cpt) {
					case 0 :
						builder.setWidth(Integer.valueOf(line));
						break;
					case 1 :
						builder.setHeight(Integer.valueOf(line));
						break;
						default :
							String[] cols = line.split(" ");
							if(cols.length != 2) {
								throw new RuntimeException("Bad input at line : "+cpt);
							}
							builder.addFigure(Integer.valueOf(cols[0]), Integer.valueOf(cols[1]));
					}
					cpt++;
				}

                GridModel model = builder.build();
                
                //pour checker
                for(int x = 0; x < model.getGrid().length; x++) {
                	for(int y = 0; y < model.getGrid()[0].length; y++) {
                		solved = solved && model.getGrid()[x][y].isAllConnected(model, new Point(x, y));
                	}
                }
                
                //pour afficher les graphismes
                model.addObserver(grid);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            // load grid from inputFile and check if it is solved... 
            //...
            System.out.println("SOLVED: " + solved);           
        }
        else {
            throw new ParseException("You must specify at least one of the following options: -generate -check -solve ");           
        }
        } catch (ParseException e) {
        // TODO Auto-generated catch block
            System.err.println("Error parsing commandline : " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "phineloops", options );         
            System.exit(1); // exit with error      
    }
        //System.exit(0); // exit with success                            
    }
}
