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
  String getRoomNameByDirection( String direction );

  
  public String describe( ILocationResolver resolver );
}