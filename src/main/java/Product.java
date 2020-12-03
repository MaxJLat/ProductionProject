/*abstract class to be implemented by specific products as a template.*/
abstract class Product implements Item {

  //Fields for the methods.
  int id;
  ItemType type;
  String manufacturer;
  String name;

  Product(String name, String manufacturer, ItemType type) {
    this.type = type;
    this.name = name;
    this.manufacturer = manufacturer;
  }
/*toString override method to return in a specific readable fashion that's easy to print
* @return a formatted string of name, manufacturer, and type*/
  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }



  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  public ItemType getType() {
    return type;
  }
}

class Widget extends Product {

  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }
}