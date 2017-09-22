package com.boohimer.adventures;

import java.util.HashMap;
import java.util.Map;

public class Location implements ILocation {
  // Define all the attributes of a location
  private String           name;
  private String           description;
  private String           peekDescription;
  //private String           north;
  //private String           south;
  //private String           east;
  //private String           west;
  //private String           up;
  //private String           down;
  
  private Map<String,String> movements = new HashMap<String,String>();

  
  /**
   * @param name
   * @param description
   * @param peekDescription
   * @param north
   * @param south
   * @param east
   * @param west
   * @param up
   * @param down
   */
  /*
  public Location( String name
                 , String description
                 , String peekDescription
                 , String north
                 , String south
                 , String east
                 , String west
                 , String up
                 , String down ) {
    super();
    this.name            = name;
    this.description     = description;
    this.peekDescription = peekDescription;
    this.north           = north;
    this.south           = south;
    this.east            = east;
    this.west            = west;
    this.up              = up;
    this.down            = down;
  }
  */
  
  public Location( String name
                 , String description
                 , String peekDescription
                 , String movementString
                 ) {
    super();
    this.name            = name;
    this.description     = description;
    this.peekDescription = peekDescription;
    
    String[] movementMapping = movementString.split( "," );
    
    for( String item : movementMapping ) {
      String[] oneMovementMap = item.split( ":" );
      
      if( oneMovementMap.length > 1 ) {
        movements.put( oneMovementMap[ 0 ].toLowerCase(), oneMovementMap[ 1 ] );
      }
    }
  }
  
 
  /* (non-Javadoc)
   * @see com.boohimer.adventures.Location1#getName()
   */
  @Override
  public String getName() {
    return name;
  }

  /* (non-Javadoc)
   * @see com.boohimer.adventures.Location1#getDescription()
   */
  @Override
  public String getDescription() {
    return description;
  }

  /* (non-Javadoc)
   * @see com.boohimer.adventures.Location1#getPeekDescription()
   */
  @Override
  public String getPeekDescription() {
    return peekDescription;
  }

  @Override
  public String getRoomNameByDirection( String direction ) {
    String retval = null;
    
    String lowercaseDirection = direction.toLowerCase();
    
    if( movements.containsKey( lowercaseDirection )) {
      retval = movements.get( lowercaseDirection );
    }
    
    return retval;
  }

 
  
  private void appendPeekDescription( StringBuilder builder, String direction, ILocation location ) {
    if( location != null ) {
      builder.append( "\n    Looking " ).append( direction ).append( " you see: " ).append(  location.getPeekDescription() );
    }
  }
  
  public String describe( ILocationResolver resolver ) {
    StringBuilder builder = new StringBuilder(  "You look around:\n    " );
    builder.append( getDescription() )
           .append( "\n\nDirections you can go:" );
    
    for( String key : movements.keySet() ) {
      appendPeekDescription( builder
                           , key.toLowerCase()
                           , resolver.getLocationByName( movements.get( key ))
                           );
    }
    
    return builder.toString();
  }

  public static void main( String[] args ) {
    Location l = new Location( "test"
                             , "description"
                             , "peekDescription"
                             , "north:mainCavern,south:Entrance"
                             );
   
  }
}
