package com.boohimer.adventures;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Location implements ILocation {
  // Define all the attributes of a location
  private String           name;
  private String           description;
  private String           peekDescription;
  
  private Vector<NonPlayerCharacterBase> nonPlayerCharacters = new Vector<NonPlayerCharacterBase>();
  private Map<String,String>             movements           = new HashMap<String,String>();
  
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
  
 
  public void addNonPlayerCharacter( NonPlayerCharacterBase npc ) {
    this.nonPlayerCharacters.add( npc );
  }
  
  public void removeNonPlayerCharacter( NonPlayerCharacterBase npc ) {
    this.nonPlayerCharacters.remove( npc );
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

 
  
  public Map<String, String> getMovements() {
    return new HashMap<String,String>( movements );
  }


  private void appendPeekDescription( StringBuilder builder, String direction, ILocation location ) {
    if( location != null ) {
      builder.append( "\n    Looking " ).append( direction ).append( " you see: " ).append(  location.getPeekDescription() );
    }
  }
  
  public String describe( ILocationResolver resolver ) {
    StringBuilder builder = new StringBuilder(  "You look around:\n    " );
    builder.append( getDescription() );
    
    if( !nonPlayerCharacters.isEmpty() ) {
      builder.append( "\n    ------------------\n    Also here:\n" );
      for( Object npc : nonPlayerCharacters.toArray() ) {
        builder.append( "    " ).append(((NonPlayerCharacterBase) npc ).getName() ).append( "\n" );
      }
      builder.append( "    ------------------\n" );
    }
    
    builder.append( "\n\nDirections you can go:" );
    
    for( String key : movements.keySet() ) {
      appendPeekDescription( builder
                           , key.toLowerCase()
                           , resolver.getLocationByName( movements.get( key ))
                           );
    }
    
    return builder.toString();
  }
}
