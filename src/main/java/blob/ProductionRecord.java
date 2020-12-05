package blob;

import java.util.Date;
/*ProductionRecord class is used to store data about what is being produced.*/
public class ProductionRecord {

  //fields
  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  /*Constructor for default ProductionRecord object
  * @param int productID, the product id number.*/
  public ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  /*Constructor to be used when fetching from Database.
  * @param int productionNumber, The order of production
  * @param int productID, the product ID number.
  * @param String serialNumber, serial number for the record.
  * @param Date dateProduced, the date the record is created.*/
  public ProductionRecord(int productionNumber, int productID, String serialNumber,
      Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }
/*Constructor to be used when fetching from user selection from product list
* Creates the serialNumber from by formatting manufacturer, the product type, and incrementing in database.
* @param Product product, a product object that is being produced.
* @param int itemCount, the amount of the product that is being produced.*/
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
  /*@return int productionNumber, the order in which the records were put in.*/
  public int getProductionNum() {
    return productionNumber;
  }

  /*@param int productionNumber, sets the order in which the records were put in.*/
  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  /*@return int productID, id for the product in the record production. Use with database to retrieve the product.*/
  public int getProductID() {
    return productID;
  }

  /*@param int productID, id for the product is set to this.*/
  public void setProductID(int productID) {
    this.productID = productID;
  }

  /*@return String serialNumber, the unique serial number for the ProductionRecord.*/
  public String getSerialNum() {
    return serialNumber;
  }

  /*@param String serialNumber, sets serialNumber of the product to this.*/
  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /*@return Date dateProduced, returns the date the productionRecord was entered.*/
  public Date getProdDate() {
    return dateProduced;
  }

  /*@param Date dateProduced, sets the date the production record was produced.*/
  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced;
  }
}
