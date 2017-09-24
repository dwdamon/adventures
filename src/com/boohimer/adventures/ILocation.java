package com.boohimer.adventures;

import java.util.Map;

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

  public Map<String, String> getMovements();
  
  public String describe( ILocationResolver resolver );
  
  public void addNonPlayerCharacter( NonPlayerCharacterBase npc );
  
  public void removeNonPlayerCharacter( NonPlayerCharacterBase npc );
}