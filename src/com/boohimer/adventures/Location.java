package com.boohimer.adventures;

public class Location implements ILocation {
  // Define all the attributes of a location
  private String           name;
  private String           description;
  private String           peekDescription;
  private String           north;
  private String           south;
  private String           east;
  private String           west;
  private String           up;
  private String           down;
  
 

  /**
   * @param name
   * @param description
   * @param peekDescription
   * @param north
   * @param south
   * @param east
   * @param west
   * @param up
   * @param down
   */
  public Location( String name
                     , String description
                     , String peekDescription
                     , String north
                     , String south
                     , String east
                     , String west
                     , String up
                     , String down ) {
    super();
    this.name            = name;
    this.description     = description;
    this.peekDescription = peekDescription;
    this.north           = north;
    this.south           = south;
    this.east            = east;
    this.west            = west;
    this.up              = up;
    this.down            = down;
  }
  
 
  /* (non-Javadoc)
   * @see com.boohimer.adventures.Location1#getName()
   */
  @Override
  public String getName() {
    return name;
  }

  /* (non-Javadoc)
   * @see com.boohimer.adventures.Location1#getDescription()
   */
  @Override
  public String getDescription() {
    return description;
  }

  /* (non-Javadoc)
   * @see com.boohimer.adventures.Location1#getPeekDescription()
   */
  @Override
  public String getPeekDescription() {
    return peekDescription;
  }

  /* (non-Javadoc)
   * @see com.boohimer.adventures.Location1#getNorth()
   */
  @Override
  public String getNorth() {
    return north;
  }

  /* (non-Javadoc)
   * @see com.boohimer.adventures.Location1#getSouth()
   */
  @Override
  public String getSouth() {
    return south;
  }

  /* (non-Javadoc)
   * @see com.boohimer.adventures.Location1#getEast()
   */
  @Override
  public String getEast() {
    return east;
  }

  /* (non-Javadoc)
   * @see com.boohimer.adventures.Location1#getWest()
   */
  @Override
  public String getWest() {
    return west;
  }

  /* (non-Javadoc)
   * @see com.boohimer.adventures.Location1#getUp()
   */
  @Override
  public String getUp() {
    return up;
  }

  /* (non-Javadoc)
   * @see com.boohimer.adventures.Location1#getDown()
   */
  @Override
  public String getDown() {
    return down;
  }
  
  private void appendPeekDescription( StringBuilder builder, String direction, ILocation location ) {
    if( location != null ) {
      builder.append( "\n    Looking " ).append( direction ).append( " you see: " ).append(  location.getPeekDescription() );
    }
  }
  
  public String describe( ILocationResolver resolver ) {
    StringBuilder builder = new StringBuilder(  "You look around:\n    " );
    builder.append( getDescription() )
           .append( "\n\nDirections you can go:" );
    
    ILocation northLocation = resolver.getLocationByName( north );
    ILocation southLocation = resolver.getLocationByName( south );
    ILocation eastLocation  = resolver.getLocationByName( east );
    ILocation westLocation  = resolver.getLocationByName( west );
    ILocation upLocation    = resolver.getLocationByName( up );
    ILocation downLocation  = resolver.getLocationByName( down );
    
    appendPeekDescription( builder, "north", northLocation );
    appendPeekDescription( builder, "south", southLocation );
    appendPeekDescription( builder, "east",  eastLocation );
    appendPeekDescription( builder, "west",  westLocation );
    appendPeekDescription( builder, "up",    upLocation );
    appendPeekDescription( builder, "down",  downLocation );
    
    return builder.toString();
  }
}
