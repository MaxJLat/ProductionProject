
//Enum with Type, and code String as the field.
//code field is public so can be fetched directly with .code
public enum ItemType {
  AUDIO("AU"), VISUAL("VI"), AUDIO_MOBILE("AM"), VISUAL_MOBILE("VM");
  public String code;

  ItemType(String code) {
    this.code = code;
  }
}
