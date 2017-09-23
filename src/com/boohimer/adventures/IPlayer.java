package com.boohimer.adventures;

public interface IPlayer {

  String getName();

  void setName( String name );

  String getCurrentLocation();

  void setCurrentLocation( String currentLocation );

  String getStartingLocation();

  void setStartingLocation( String startingLocation );

}