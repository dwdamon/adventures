package com.boohimer.adventures;

import java.util.HashMap;
import java.util.Map;

public class LocationResolver implements ILocationResolver {
  private Map<String,ILocation> locations = new HashMap<String,ILocation>();
  
  public LocationResolver() {
  }
  
  // This is where we add rooms to the map. 
  // We will return this instance of LocationResolverImpl
  // so we can chain the addRoom() calls.
  public ILocationResolver addRoom( ILocation location ) {
    locations.put( location.getName().toLowerCase(), location );
    return this;
  }
  
  
  @Override
  public ILocation getLocationByName( String nextRoomName ) {
    ILocation retval = null;

    if( nextRoomName != null ) {
      retval = locations.get( nextRoomName.toLowerCase() );
    }
    
    return retval;
  }
  
  
  @Override
  public ILocation getLocationByName( ILocation currentLocation, String nextRoomName ) {
    ILocation retval       = currentLocation;
    
    if( nextRoomName != null && nextRoomName.length() > 0 ) {
      ILocation locatedRoom = locations.get( nextRoomName.toLowerCase() );
      
      if( locatedRoom != null ) {
        retval = locatedRoom;
      }
    }
    
    return retval;
  }
  
  @Override
  public ILocation getLocationByDirection( ILocation currentLocation, String direction ) {
    String    nextRoomName = null;
    ILocation retval       = currentLocation;
    
    switch( direction.toUpperCase() ) {
      case "NORTH":
        nextRoomName = currentLocation.getNorth();
        break;
        
      case "SOUTH":
        nextRoomName = currentLocation.getSouth();
        break;
        
      case "EAST":
        nextRoomName = currentLocation.getEast();
        break;
        
      case "WEST":
        nextRoomName = currentLocation.getWest();
        break;
        
      case "UP":
        nextRoomName = currentLocation.getUp();
        break;
        
      case "DOWN":
        nextRoomName = currentLocation.getDown();
        break;
    }
    
    if( nextRoomName == null ) {
      System.out.println( "You can't go that way" );
    }
    else {
      System.out.println( "\n\nYou go " + direction + "\n\n" );
      retval = getLocationByName( currentLocation, nextRoomName );
    }
    
    return retval;
  }


  public ILocation getRandomLocation() {
    int    random = (int)( Math.random() * locations.size() );
    String key    = (String)( locations.keySet().toArray()[ random ]);
    
    return locations.get( key );
  }
}
