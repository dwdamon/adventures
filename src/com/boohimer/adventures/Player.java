package com.boohimer.adventures;

public class Player implements IPlayer {
  private String name;
  private String currentLocation;
  private String startingLocation;
  
  public Player( String name ) {
    this.name = name;
  }
  
  public Player( String name, String startingLocation, String currentLocation ) {
    this( name );
    this.startingLocation = startingLocation;
    this.currentLocation  = currentLocation;
  }

  public Player( String name, String startingLocation ) {
    this( name, startingLocation, startingLocation );
  }
  
  /* (non-Javadoc)
   * @see com.boohimer.adventures.IPlayer#getName()
   */
  @Override
  public String getName() {
    return name;
  }
  /* (non-Javadoc)
   * @see com.boohimer.adventures.IPlayer#setName(java.lang.String)
   */
  @Override
  public void setName( String name ) {
    this.name = name;
  }
  /* (non-Javadoc)
   * @see com.boohimer.adventures.IPlayer#getCurrentLocation()
   */
  @Override
  public String getCurrentLocation() {
    return currentLocation;
  }
  /* (non-Javadoc)
   * @see com.boohimer.adventures.IPlayer#setCurrentLocation(com.boohimer.adventures.ILocation)
   */
  @Override
  public void setCurrentLocation( String currentLocation ) {
    this.currentLocation = currentLocation;
  }
  /* (non-Javadoc)
   * @see com.boohimer.adventures.IPlayer#getStartingLocation()
   */
  @Override
  public String getStartingLocation() {
    return startingLocation;
  }
  /* (non-Javadoc)
   * @see com.boohimer.adventures.IPlayer#setStartingLocation(com.boohimer.adventures.ILocation)
   */
  @Override
  public void setStartingLocation( String startingLocation ) {
    this.startingLocation = startingLocation;
  }
}
