public class AudioPlayer extends Product implements MultimediaControl {

  //Fields specific to AudioPlayer.
  String supportedAudioFormats;
  String supportedPlaylistFormats;

  //Constructor takes 4 Strings, creates an AudioPlayer Object through the super constructor.
  AudioPlayer(String name, String manufacturer,
      String supportedAudioFormats, String supportedPlaylistFormats ) {

    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  //toString function override. Prints the same as objects but added
  //supportedAudioFormats and supportedPlaylistFormats.
  public String toString(){
    return super.toString() + "\nSupported Audio Formats: " + supportedAudioFormats
        + "\nSupported Playlist Formats: " + supportedPlaylistFormats;
  }

  //Implemented methods from MultimediaControl interface.
  @Override
  public void play() {
    System.out.println("Playing");

  }

  @Override
  public void stop() {
    System.out.println("Stopping");

  }

  @Override
  public void previous() {
    System.out.println("Previous");

  }

  @Override
  public void next() {
    System.out.println("Next");

  }
}
