package com.boohimer.adventures;

import java.util.Map;

public class RandomizingNonPlayerCharacter extends NonPlayerCharacterBase {
  private String    startingRoom;
  
  public RandomizingNonPlayerCharacter( String name, String startingRoom ) {
    super( name );
    this.startingRoom = startingRoom;
  }

  @Override
  public synchronized void tick( ILocationResolver resolver ) {
    if( this.getCurrentLocation() == null ) {
      setCurrentLocation( startingRoom );
      ILocation currentLocation = resolver.getLocationByName( startingRoom );
      currentLocation.addNonPlayerCharacter( this );
    }
    else {
      ILocation          workingLocation = resolver.getLocationByName( getCurrentLocation() );
      Map<String,String> movements       = workingLocation.getMovements();
      int                direction       = (int)( Math.random() * movements.size() );
      String             newLocationName = (String) movements.values().toArray()[ direction ];
      ILocation          newLocation     = resolver.getLocationByName( newLocationName );
      
      //System.out.println( "Random: " + this.getName() + "current: " + this.getCurrentLocation() + " random: " + direction + " newLocation: " + newLocationName );
      
      workingLocation.removeNonPlayerCharacter( this );
      setCurrentLocation( newLocationName );
      newLocation.addNonPlayerCharacter( this );
    }
  }

}
