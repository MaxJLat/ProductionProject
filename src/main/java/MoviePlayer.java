import javax.management.monitor.Monitor;

public class MoviePlayer extends Product implements MultimediaControl {

  //additional fields
  Screen screen;
  MonitorType monitorType;

  //constructor that calls Product constructor, sets ItemType to VISUAL,
  MoviePlayer(String name, String manufacturer,
      Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  //Overriding toString() method that includes Product.toString(), Screen.toString(),
  //and adds MonitorType.
  public String toString() {
    return super.toString() + screen.toString() + "\nMonitor Type: " + monitorType;
  }


  //Implemented from MultimediaControl.
  @Override
  public void play() {
    System.out.println("Playing movie");

  }

  @Override
  public void stop() {
    System.out.println("Stopping movie");

  }

  @Override
  public void previous() {
    System.out.println("Previous movie");

  }

  @Override
  public void next() {
    System.out.println("Next movie");

  }
}
