import java.util.Date;
/*ProductionRecord class is used to store data about what is being produced.*/
public class ProductionRecord {

  //fields
  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  /*Constructor for default ProductionRecord object*/
  public ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  /*Constructor to be used when fetching from Database.*/
  public ProductionRecord(int productionNumber, int productID, String serialNumber,
      Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }
/*Constructor to be used when fetching from user selection from product list
* Creates the serialNumber from by formatting manufacturer, the product type, and incrementing in database.*/
  public ProductionRecord(Product product, int itemCount){
    serialNumber = product.manufacturer.substring(0,3) + product.type.code
        +String.format("%05d", itemCount);
    productID = product.getId();
    dateProduced = new Date();
    productionNumber = 0;
  }

  /*toString() override which prints all the fields in ProductionRecord.
  * @return a formatted string of the fields in ProductionRecord object*/
  public String toString() {
    return "Prod. Num: " + productionNumber + " Product ID: " + productID
        + " Serial Num: " + serialNumber + " Date: " + dateProduced;
  }

  //Getters and Setters.
  public int getProductionNum() {
    return productionNumber;
  }

  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public String getSerialNum() {
    return serialNumber;
  }

  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Date getProdDate() {
    return dateProduced;
  }

  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced;
  }
}
