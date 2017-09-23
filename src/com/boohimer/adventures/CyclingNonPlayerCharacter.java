package com.boohimer.adventures;

public class CyclingNonPlayerCharacter extends NonPlayerCharacterBase {
  private int       index = 0;
  private String[]  rooms;
  //private ILocation currentLocation;
  
  public CyclingNonPlayerCharacter( String name, String[] rooms ) {
    super( name, rooms[ 0 ] );
    this.rooms = rooms;
  }

  @Override
  public synchronized void tick( ILocationResolver resolver ) {
    if( rooms != null && rooms.length > 0 ) {
      if( this.getCurrentLocation() == null ) {
        setCurrentLocation( rooms[ index ]);
        //currentLocation = resolver.getLocationByName( rooms[ index ]);
      }
      else {
        ILocation workingLocation = resolver.getLocationByName( rooms[ index ]);
        workingLocation.removeNonPlayerCharacter( this );
        if( ++index >= rooms.length ) {
          index = 0;
        }
        
        workingLocation = resolver.getLocationByName( rooms[ index ]);
        setCurrentLocation( rooms[ index ]);
        workingLocation.addNonPlayerCharacter( this );
      }
    }
  }

}
