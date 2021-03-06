package com.boohimer.adventures;

import java.util.Scanner;
import java.util.Vector;

public class Test implements ITickHandler {
  private final ILocationResolver resolver = new LocationResolver();
  
  private       ILocation         treasureLocation;
  private       boolean           hasTreasure   = false;
  private       boolean           playerWon     = false;
  private       String[]          treasureRooms = { "latrine"
                                                  , "tinyRoom"
                                                  , "mossRoom"
                                                  , "tinyRoom"
                                                  , "non-descriptRoom"
                                                  , "hallOfBones"
                                                  , "ThroneRoom"
                                                  , "kingsPrivy"
                                                  , "pennegraphsSojourn"
                                                  };
  
  private Vector<NonPlayerCharacterBase> nonPlayerCharacters = new Vector<NonPlayerCharacterBase>();
  
  public void tick( ILocationResolver localResolver ) {
    for( NonPlayerCharacterBase npc : nonPlayerCharacters ) {
      npc.tick( localResolver );
    }
  }
 
  private void createMaze() {
    //System.out.println(  "Creating your adventure." );
    resolver.addRoom( new Location( "Entrance"
                                  , "You are in an open field.\nTo the North there is an entrance to a dreary looking cave.\nMight be spiders in there, so be careful if you decide to enter."
                                  , "The field you started in.  Freedom awaits in this direction!"
                                  , "north:mainCavern"
                                  ))
            .addRoom( new Location( "mainCavern"
                                  , "You are in a small cave chamber.\nYou see a bright light to the South.\nTo the East and West you can see dimly lit tunnels."
                                  , "A small cave chamger."
                                  , "south:Entrance,east:dryTunnel,west:wetTunnel"
                                  ))
            .addRoom( new Location( "dryTunnel"
                                  , "You are in a narrow tunnel."
                                  , "a narrow tunnel."
                                  , "north:hallOfBones,west:mainCavern"
                                  ))           
            .addRoom( new Location( "wetTunnel"
                                  , "You are in a narrow, wet tunnel.\nWater drips from the ceiling and it smells dank."
                                  , "a wet, narrow tunnel."
                                  , "north:curvingTunnel,east:mainCavern"
                                  ))           
            .addRoom( new Location( "curvingTunnel"
                                  , "You are in a narrow, curving tunnel.\nThere is an odd odor from the west."
                                  , "a narrow curving tunnel."
                                  , "south:wetTunnel,east:tinyRoom,west:latrine,up:mossRoom"
                                  ))           
            .addRoom( new Location( "latrine"
                                  , "You are fairly large room.\nIt STINKS in here.  The ground reveales the truth!  You are in a latrine.\nWatch where you step!"
                                  , "a farily large room, if a bit smelly."
                                  , "east:curvingTunnel"
                                  ))  
            .addRoom( new Location( "tinyRoom"
                                  , "You are really small dark room.\nThere is not much room to stand.  Carved in the wall are the words 'Brainard was here.  Briefly.'"
                                  , "a tiny dark room."
                                  , "west:curvingTunnel"
                                  ))  
            .addRoom( new Location( "mossRoom"
                                  , "You are room covered in moss.\nYou see a tombstone here that reads: 'Brainard died here.  He got hungry and ate some moss.'"
                                  , "a mossy looking room."
                                  , "east:spiderRoom,down:curvingTunnel"
                                  ))  
            .addRoom( new Location( "spiderRoom"
                                  , "You are room with a giant spider in the corner.\nBetter creep by."
                                  , "a room with a spider in it."
                                  , "east:non-descriptRoom,west:mossRoom"
                                  ))  
            .addRoom( new Location( "non-descriptRoom"
                                  , "You are really plain room.\nNothing is particulary significant about this room except for the hole in the ground at the center."
                                  , "a non-descript room."
                                  , "west:spiderRoom,down:hallOfBones"
                                  ))  
            .addRoom( new Location( "hallOfBones"
                                  , "You are room filled with lots and lots of bones.\nYou hope you don't add yours to the collection."
                                  , "the hall of bones."
                                  , "south:dryTunnel,east:throneRoom,up:non-descriptRoom"
                                  ))
            .addRoom( new Location( "throneRoom"
                                  , "You are room with a throne in the middle of the room."
                                  , "the throne room."
                                  , "east:kingsBoudoir,west:hallOfBones"
                                  ))               
            .addRoom( new Location( "kingsBoudoir"
                                  , "You are room with a large royal bed in the corner.\nIt's the kings boudior!  Don't sleep here... he may return.\nBesides the porrige is way too cold.\nThere is a tiny hole to the south.  You might be able to fit through it."
                                  , "the kings boudior."
                                  , "north:kingsPrivy,south:Entrance,west:throneRoom"
                                  ))
            .addRoom( new Location( "kingsPrivy"
                                  , "You are what appears to be the King's privy.\nThe place the King does his 'buisness' is to the west.  Shower it to the right.\n(A shower in a cave?  Who would have thought?)"
                                  , "the kings privy."
                                  , "north:pennegraphsSojourn,south:kingsBoudoir"
                                  ))
            .addRoom( new Location( "pennegraphsSojourn"
                                  , "You are in small room.  There is not much here.\nThere is a chisled carving on the wall with a burning torch next to it.\nThe carving reads \"Pennegraph's soujurn begins\""
                                  , "Pennegraph's soujourn beginning."
                                  , "south:kingsPrivy,east:pennegraphsLastStand"
                                  ))
            .addRoom( new Location( "pennegraphsLastStand"
                                  , "You are in large field.\nSkeletons are scattered about the ground everywhere.  Amongst the skeletons are rusty, decaying weapons of all types.\nThere was some mighty battle here a long time past.  There is a cave entrance to the west with a carving in the stone that reads \"I Pennegraph will fight no more.  Forever.\""
                                  , "Pennegraph's last stand."
                                  , "east:maurdersTrail,west:pennegraphsSojourn"
                                  ))
            .addRoom( new Location( "maurdersTrail"
                                  , "You are on a narrow trail through the woods.\nYou can tell that at one time thi was a wider trail.  Perhaps a road.\nThis is probably the path the marauders ran down Pennegraph and his 'horde of heros'. That battle didn't go too well for the marauders, but, it didnt' go so well for Pennegraph either.  Legends say that only Pennegraph survived that encounter."
                                  , "The marauders trail."
                                  , "west:pennegraphsLastStand"
                                  ))                          
            ;
  }
  
  private void addNonPlayers() {
    this.nonPlayerCharacters.add( new CyclingNonPlayerCharacter( "dead bob"
                                                               ,  new String[] { "mossRoom"
                                                                               , "spiderRoom"
                                                                               , "non-descriptRoom"
                                                                               , "spiderRoom" 
                                                                               }
                                                               ));
    
    this.nonPlayerCharacters.add( new CyclingNonPlayerCharacter( "King Louey"
                                                               , new String[] { "hallOfBones"
                                                                              , "throneRoom"
                                                                              , "kingsBoudoir"
                                                                              , "kingsPrivy"
                                                                              , "kingsBoudoir"
                                                                              , "throneRoom"
                                                                              }
                                                               ));
    
    this.nonPlayerCharacters.add( new RandomizingNonPlayerCharacter( "Jason",          "hallOfBones" ));
    this.nonPlayerCharacters.add( new RandomizingNonPlayerCharacter( "Frakenstein",    "mainCavern" ));
    this.nonPlayerCharacters.add( new RandomizingNonPlayerCharacter( "Freddy Krueger", "kingsBoudoir" ));
    this.nonPlayerCharacters.add( new RandomizingNonPlayerCharacter( "Pennegraph",     "pennegraphsSojourn" ));
    
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
  
  private ILocation getRandomTreasureLocation() {
    return resolver.getLocationByName( treasureRooms[ (int)( Math.random() * treasureRooms.length ) ]);
  }
  
  private void placeTreasure() {
    ILocation newTreasureLocation = getRandomTreasureLocation();
    
    while( newTreasureLocation == treasureLocation ) {
      newTreasureLocation = getRandomTreasureLocation();
    }
    
    treasureLocation = newTreasureLocation;
  }
  
  private void runMaze( IPlayer player ) {
    ILocation   currentLocation  = resolver.getLocationByName( player.getCurrentLocation() );
    ILocation   startingLocation = resolver.getLocationByName( player.getStartingLocation() );
    ILocation   lastLocation     = null;
    TickService service          = new TickService( this, resolver  );
    
    placeTreasure();
    addNonPlayers();
    
    Thread thread = new Thread( service );
    thread.start();
    
    preamble();
    
    System.out.println( currentLocation.describe( resolver ));
    
    Scanner scanner = new Scanner( System.in );
    try {
      while( !playerWon && scanner.hasNext() ) {
        String input = scanner.nextLine();
        lastLocation     = currentLocation;
        currentLocation = resolver.getLocationByDirection( currentLocation, getDirection( input ));
        
        System.out.println( currentLocation.describe( resolver ));
        
        if( currentLocation == resolver.getLocationByName( player.getStartingLocation() )) {
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
          if( hasTreasure && lastLocation != currentLocation ) {
            int priateProbabilityRoll = (int)( Math.random() * 100 );
            //System.out.println(  "Probability roll: " + priateProbabilityRoll  );
            if( currentLocation != startingLocation && priateProbabilityRoll < 10 ) {
              System.out.println( "\n*** A pirate jumps out dashes by you and steals the treasure from your hands.  Dang! ***" );
            
              hasTreasure = false;
              placeTreasure();
            }
          }
        }
      }
    }
    finally {
      scanner.close();
      service.halt();
      thread.interrupt();
    }
  }
  
  public static void main( String[] args ) {
    Test    test   = new Test();
    IPlayer player = new Player( "Thor", "Entrance" );
    
    test.createMaze();
    test.runMaze( player );
  }

}
