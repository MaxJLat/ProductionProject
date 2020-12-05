/*AudioPlayer class is designed to represent audio players.
* @author Maximilien Latura*/
public class AudioPlayer extends Product implements MultimediaControl {

  //Fields specific to AudioPlayer.
  String supportedAudioFormats;
  String supportedPlaylistFormats;

  /*Constructor takes 4 Strings, creates an AudioPlayer Object through the super constructor.
  * @param String name is the name of the product
  * @param String manufacturer is the name of the manufacturer.
  * @param String supportedAudioFormats is a String containing the supportedAudioFormats.
  * @param String supportedPlaylistFormats is a String containing what playlist formats it supports.*/
  AudioPlayer(String name, String manufacturer,
      String supportedAudioFormats, String supportedPlaylistFormats) {

    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  /*
  toString function override. Prints the same as objects but added
  supportedAudioFormats and supportedPlaylistFormats.
  @returns Returns a String that's formatted to nicely contain supportedAudioFormat and supportedPlaylistFormats.
  */
  public String toString() {
    return super.toString() + "\nSupported Audio Formats: " + supportedAudioFormats
        + "\nSupported Playlist Formats: " + supportedPlaylistFormats;
  }

  /*Implemented methods from MultimediaControl interface.*/
  @Override
  /*Test methods not used yet.*/
  public void play() {
    System.out.println("Playing");

  }

  @Override
  /*Test methods not used yet.*/
  public void stop() {
    System.out.println("Stopping");

  }

  @Override
  /*Test methods not used yet.*/
  public void previous() {
    System.out.println("Previous");

  }

  @Override
  /*Test methods not used yet.*/
  public void next() {
    System.out.println("Next");

  }
}
