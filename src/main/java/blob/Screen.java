package blob;

/*Screen class is used to represent a screen with fields for data.*/
public class Screen implements ScreenSpec {
  String resolution;
  int refreshRate;
  int responseTime;

  /*Constructor for screen if there is specific data for resolution, refresh rate and response time
  * @param String resolution, the resolution of the screen.
  * @param int refreshRate, the refresh rate of the monitor
  * @param int responseTime, the response time of the monitor.*/
  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }
/*Default constructor for screen*/
  public Screen(){
    this.resolution = "1080x720";
    this.refreshRate = 0;
    this.responseTime = 0;
  }

  /*Returns string with all fields, empty space for Screen type when implemented.
  * @return formatted string containing resolution, refreshRate, and responseTime.*/
  public String toString(){
    return "\nResolution: " + resolution + "\nRefresh rate: "
        + refreshRate + "\nResponse Time: " + responseTime;
  }

  //Implemented methods from ScreenSpec interface.
  @Override
  /*@return String resolution, the resolution of the screen.*/
  public String getResolution() {
    return resolution;
  }

  @Override
  /*@return int refreshRate, the refresh rate of the monitor.*/
  public int getRefreshRate() {
    return refreshRate;
  }

  @Override
  /*@return int responseTime, the response time of the monitor.*/
  public int getResponseTime() {
    return responseTime;
  }
}
