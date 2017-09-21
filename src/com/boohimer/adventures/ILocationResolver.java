package com.boohimer.adventures;

public interface ILocationResolver {
  public ILocationResolver addRoom( ILocation location );
  public ILocation         getLocationByName( ILocation currentLocation, String nextRoomName );
  public ILocation         getLocationByName( String nextRoomName );
  public ILocation         getLocationByDirection( ILocation currentLocation, String direction );
  public ILocation         getRandomLocation();
}
