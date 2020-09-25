public class Screen implements ScreenSpec {
  String resolution;
  int refreshRate;
  int responseTime;

  //Constructor 3 parameters.
  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  //Returns string with all fields, empty space for Screen type when implemented.
  public String toString(){
    return "Screen: \nResolution: " + resolution + "\nRefresh rate: "
        + refreshRate + "\nResponse Time: " + responseTime;
  }

  //Implemented methods from ScreenSpec interface.
  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  @Override
  public int getResponseTime() {
    return responseTime;
  }
}
