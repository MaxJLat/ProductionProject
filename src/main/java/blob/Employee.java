package blob;

public class Employee {
  private StringBuilder name;
  private String username;
  private String password;
  private String email;



  /*Constructor for the employee Class.
  * @param Employee name.
  * @param Employee password*/
  public Employee(String stringName, String password){
    this.name = new StringBuilder().append(stringName);
    if(checkName(this.name)){
      setUsername(stringName);
      setEmail(stringName);
    }
    else{
      username = "default";
      email = "user@oracleacademy.Test";
    }
    if(isValidPassword(password)){
      this.password = password;
    }
    else{
      this.password = "pw";
    }

  }
  /*Set username to initial of first name and last name combined.
  * @param String of name, called in constructor.*/
  public void setUsername(String name){
    //Store the index of the last known space in the name, since some people type middle names.
    int spaceInName = name.lastIndexOf(" ");
    //set username equal to the initial of the first name,
    //and take a substring starting from the char after the last space.
    username = name.substring(0,1)+name.substring(spaceInName+1);
    //make it all lower case.
    username.toLowerCase();



  }

  /*Set email based on name, all to lowercase.*/
  public void setEmail(String name){
    int indexOfFirstSpace = name.indexOf(" ");
    int indexOfLastSpace = name.lastIndexOf(" ");
    email = name.substring(0,indexOfFirstSpace).toLowerCase() + "." + name.substring(indexOfLastSpace+1).toLowerCase()
        + "@oracleacademy.Test";
  }

  /*check if the name has a space in it.*/
  public boolean checkName(StringBuilder name){
    if(name.indexOf(" ") == -1){
      return false;
    }
    else {
      return true;
    }
  }

  /*
  Checks to see if password is a valid password.
   */
  private boolean isValidPassword(String password) {
    //Three flags that are triggered when looping through if any of the chars match the type.
    boolean upperCaseFlag = false;
    boolean lowerCaseFlag = false;
    boolean specialFlag = false;

    for(int i =0;i<password.length();i++){
      char ch = password.charAt(i);
      if(Character.isLetterOrDigit(ch)){
        if(Character.isLowerCase(ch)){
          lowerCaseFlag = true;
        }
        if(Character.isUpperCase(ch)){
          upperCaseFlag = true;
        }
      }
      else if(Character.isSpaceChar(ch)){
        System.out.println("there is a space in the password");
      }
      else{
        specialFlag = true;
      }
    }
    if(upperCaseFlag && lowerCaseFlag && specialFlag){
      return true;
    }
    else{
      return false;
    }
  }

  @Override
  public String toString() {
    return
      "Employee Details\n"
          + "Name : " + name + "\n"
          + "Username : " + username + "\n"
          + "Email : " + email + "\n"
          + "Initial Password : " + password;
  }

  public String reverseString(String string) {
    if (string.length() <= 1 || string == null) {
      return string;
    } else {
      return reverseString(string.substring(1)) + string.charAt(0);

    }
  }

}
