/*MoviePlayer class is designed to repersent movie players.
* It implements Multimedia control and extends Product.
* @author Maximilien Latura*/
public class MoviePlayer extends Product implements MultimediaControl {

  //additional fields
  Screen screen;
  MonitorType monitorType;

  /*constructor that calls Product constructor, sets ItemType to VISUAL.
  * @param String name, name of the movie player
  * @param String manufacturer, name of the manufacturer
  * @param Screen screen, object of Screen.
  * @param MonitorType monitorType, ENUM of monitor type.*/
  MoviePlayer(String name, String manufacturer,
      Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /*Overriding toString() method that includes Product.toString(), Screen.toString(),
  and adds MonitorType.
  @return formatted string for sysout containing super.toString(), screen.toString(), and monitorType.*/
  public String toString() {
    return super.toString() + screen.toString() + "\nMonitor Type: " + monitorType;
  }


  /*Implemented from MultimediaControl.
  * Test methods to be used in the future.*/
  @Override
  /*Test method not used.*/
  public void play() {
    System.out.println("Playing movie");

  }

  @Override
  /*Test method not used.*/
  public void stop() {
    System.out.println("Stopping movie");

  }

  @Override
  /*Test method not used.*/
  public void previous() {
    System.out.println("Previous movie");

  }

  @Override
  /*Test method not used.*/
  public void next() {
    System.out.println("Next movie");

  }
}
