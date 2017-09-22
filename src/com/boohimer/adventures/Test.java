package com.boohimer.adventures;

import java.util.Scanner;

public class Test {
  private final ILocationResolver resolver = new LocationResolver(); 
  private       ILocation         treasureLocation; 
  private       ILocation         startingLocation;
  private       boolean           hasTreasure = false;
  private       boolean           playerWon   = false;
  
 
  private void createMaze() {
    //System.out.println(  "Creating your adventure." );
    resolver.addRoom( new Location( "Entrance"
                                  , "You are in an open field.  To the North there is an entrance to a dreary looking cave.  Might be spiders in there, so be careful if you decide to enter."
                                  , "The field you started in.  Freedom awaits in this direction!"
                                  , "north:mainCavern"
                                  ))
            .addRoom( new Location( "mainCavern"
                                  , "You are in a cave.  You see a bright light to the South.  To the East and West you can see dimly lit tunnels."
                                  , "A small cave."
                                  , "south:Entrance,east:dryTunnel,west:wetTunnel"
                                  ))
             .addRoom( new Location( "dryTunnel"
                                  , "You are in a narrow tunnel."
                                  , "a narrow tunnel."
                                  , "north:hallOfBones,west:mainCavern"
                                  ))           
             .addRoom( new Location( "wetTunnel"
                                  , "You are in a narrow, wet tunnel.  Water drips from the ceiling and it smells dank."
                                  , "a wet, narrow tunnel."
                                  , "north:curvingTunnel,east:mainCavern"
                                  ))           
             .addRoom( new Location( "curvingTunnel"
                                  , "You are in a narrow, curving tunnel.  There is an odd odor from the west."
                                  , "a narrow curving tunnel."
                                  , "south:wetTunnel,east:tinyRoom,west:latrine,up:mossRoom"
                                  ))           
               .addRoom( new Location( "latrine"
                                  , "You are fairly large room.  It STINKS in here.  The ground revales the truth!  You are in a latrine.  Watch where you step!"
                                  , "a farily large room, if a bit smelly."
                                  , "east:curvingTunnel"
                                  ))  
               .addRoom( new Location( "tinyRoom"
                                  , "You are really small dark room.  There is not much room to stand.  Carved in the wall are the words 'Brainard was here.  Briefly.'"
                                  , "a tiny dark room."
                                  , "west:curvingTunnel"
                                  ))  
               .addRoom( new Location( "mossRoom"
                                  , "You are room covered in moss. Hungry? You see a tombstone here that reads: 'Brainard died here.  He ate some moss.'"
                                  , "a mossy looking room."
                                  , "east:spiderRoom,down:curvingTunnel"
                                  ))  
               .addRoom( new Location( "spiderRoom"
                                  , "You are room with a giant spider in the corner.  Better creep by."
                                  , "a room with a spider in it."
                                  , "east:non-descriptRoom,west:mossRoom"
                                  ))  
               .addRoom( new Location( "non-descriptRoom"
                                  , "You are really plain room.  Nothing is particulary significant about it, except for the hole in the ground at the center."
                                  , "a non-descript room."
                                  , "west:spiderRoom,down:hallOfBones"
                                  ))  
               .addRoom( new Location( "hallOfBones"
                                  , "You are room filled with lots and lots of bones.  You hope you don't add yours to the collection."
                                  , "the hall of bones."
                                  , "south:dryTunnel,east:throneRoom,up:non-descriptRoom"
                                  ))
               .addRoom( new Location( "throneRoom"
                                     , "You are room with a throne in the middle of the room."
                                     , "the throne room."
                                     , "east:kingsBoudoir,west:hallOfBones"
                                     ))               
               .addRoom( new Location( "kingsBoudoir"
                                     , "You are room with a large royal bed in the corner.  It's the kings boudior!  Don't sleep here... he may return.  Besides the porrige is way too cold.\nThere is a tiny hole to the south.  You might be able to fit through it."
                                     , "the kings boudior."
                                     , "west:throneRoom,south:Entrance"
                                     ))
               ;
  }

  private String getDirection( String input ) {
    // here we can muck around with the input string to determine the direction the player wants to go
    // we are real simple right now so just expect the user to input one word directions
    return input.trim();
  }
  
  private void preamble() {
    StringBuilder builder = new StringBuilder( "\nYou are a lone adventurer who has stumbled upon a mysterious cave.\n" )
                                      .append( "You have heard rumors of a pirate who lives in a cave near here.\n" )
                                      .append( "The rumors say that he has hidden his treasure in a cave, but, that he guards it jealously!\n" )
                                      .append( "(well.... wouldn't you?)\n" )
                                      .append( "You start wondering if this might be his hidden lair.... dare you enter?\n\n"
                                      );
    
    System.out.println( builder.toString() );
  }
  
  private void placeTreasure() {
    ILocation newTreasureLocation = resolver.getRandomLocation();
    
    while( newTreasureLocation == startingLocation && newTreasureLocation != treasureLocation ) {
     newTreasureLocation = resolver.getRandomLocation();
    }
    
    treasureLocation = newTreasureLocation;
  }
  
  private void runMaze( String startingRoom ) {
    ILocation currentLocation = resolver.getLocationByName( startingRoom );
    
    startingLocation = currentLocation;
    
    placeTreasure();
    
    preamble();
    
    System.out.println( currentLocation.describe( resolver ));
    
    Scanner scanner = new Scanner( System.in );
    try {
      while( !playerWon && scanner.hasNext() ) {
        String input = scanner.nextLine();
      
        currentLocation = resolver.getLocationByDirection( currentLocation, getDirection( input ));
        
        System.out.println( currentLocation.describe( resolver ));
        
        if( currentLocation == this.startingLocation ) {
          System.out.println(  "You are back where you started!" );
          
          if( hasTreasure ) {
            System.out.println( "You have retrieved the treasure.  You win!" );
            playerWon = true;
          }
        }
        
        else if( currentLocation == treasureLocation ) {
          System.out.println( "You found the Treasure!!!!  Better get out before the pirate catches you." );
          
          hasTreasure      = true;
          treasureLocation = null;
        }
        else {
          if( hasTreasure ) {
            int priateProbabilityRoll = (int)( Math.random() * 100 );
            //System.out.println(  "Probability roll: " + priateProbabilityRoll  );
            if( currentLocation != this.startingLocation && priateProbabilityRoll < 10 ) {
              System.out.println( "A pirate jumps out dashes by you and steals the treasure from your hande.  Dang!" );
            
              hasTreasure = false;
              placeTreasure();
            }
          }
        }
      }
    }
    finally {
      scanner.close();
    }
  }
  
  public static void main( String[] args ) {
    Test test = new Test();
    
    test.createMaze();
    test.runMaze( "Entrance" );
  }

}
