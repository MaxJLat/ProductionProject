package blob;

/*abstract class to be implemented by specific products as a template.
* @author Maximilien Latura*/
abstract class Product implements Item {

  //Fields for the methods.
  int id;
  ItemType type;
  String manufacturer;
  String name;

  /*@param String name is the name of the product.
  * @param String manufacturer is the name of the manufacturer of the product.
  * @param ItemType type is the ENUM used to show the type of product.*/
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
  /*@return product ID number*/
  public int getId() {
    return id;
  }

  @Override
  /*@param String Name is the products name.*/
  public void setName(String name) {
    this.name = name;
  }

  @Override
  /*@return Product Name*/
  public String getName() {
    return name;
  }

  @Override
  /*@param String manufacturer is the name of the manufacturer.*/
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  @Override
  /*@return Manufacturer name*/
  public String getManufacturer() {
    return manufacturer;
  }

  /*@return the product type ENUM*/
  public ItemType getType() {
    return type;
  }
}

class Widget extends Product {

  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }
}