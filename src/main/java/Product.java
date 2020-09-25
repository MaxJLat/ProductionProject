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

  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }


  //Implemented from Item interface.
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
}

class Widget extends Product {

  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }
}