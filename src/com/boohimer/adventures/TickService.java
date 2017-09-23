package com.boohimer.adventures;

public class TickService implements Runnable {
  private long              tickTime = 10000;
  private boolean           running  = false;
  private ITickHandler      handler;
  private ILocationResolver resolver;
  
  public TickService( ITickHandler handler, ILocationResolver resolver  ) {
    this.handler   = handler;
    this.resolver = resolver;
  }
  
  public void run() {
    this.running = true;
    
    while( this.running ) {
      handler.tick( resolver );
      
      try {
        Thread.sleep( tickTime );
      }
      catch( InterruptedException e ) {
      }
    }
  }
  
  public void halt() {
    this.running = false;
  }
}
