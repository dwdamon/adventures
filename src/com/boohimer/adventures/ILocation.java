package com.boohimer.adventures;

public interface ILocation {
  /**
   * @return the name
   */
  String getName();

  /**
   * @return the description
   */
  String getDescription();

  /**
   * @return the peekDescription
   */
  String getPeekDescription();

  /**
   * @return the north
   */
  String getNorth();

  /**
   * @return the south
   */
  String getSouth();

  /**
   * @return the east
   */
  String getEast();

  /**
   * @return the west
   */
  String getWest();

  /**
   * @return the up
   */
  String getUp();

  /**
   * @return the down
   */
  String getDown();

  
  public String describe( ILocationResolver resolver );
}