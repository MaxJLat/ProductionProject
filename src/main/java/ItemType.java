
//Enum with Type, and code String as the field.
//code field is public so can be fetched directly with .code
public enum ItemType {
  Audio("AU"), Visual("VI"), Audio_Mobile("AM"), Visual_Mobile("VM");
  public String code;

  ItemType(String code) {
    this.code = code;
  }
}
