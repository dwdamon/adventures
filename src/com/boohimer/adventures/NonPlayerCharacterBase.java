package com.boohimer.adventures;

public abstract class NonPlayerCharacterBase extends Player {
  
  public NonPlayerCharacterBase( String name ) {
    super( name );
  }
  
  public NonPlayerCharacterBase( String name, String startingLocation, String currentLocation ) {
    super( name, startingLocation, currentLocation );
  }

  public NonPlayerCharacterBase( String name, String startingLocation ) {
    super( name, startingLocation );
  }
  
  public abstract void tick( ILocationResolver resolver );
  
}
