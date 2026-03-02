//*****************************************************************************************************************************************************************\\
package BookingSystem;

public class Passenger {
       
           private String passportNumber;
           private String name;
//*****************************************************************************************************************************************************************\\
public void setPassportNumber(String passportNumber, String name){
      this.name = name;
      this.passportNumber = passportNumber;
  }
public Passenger(String passportNumber, String name) {
        this.passportNumber = passportNumber;
        this.name = name;
    }
public String getPassportNumber() {
        return passportNumber;
        
    }
public void setName (String name){
         this.name = name;
     
     }
public String getName(){
       return name;
    }
public String getPassenger(){
      return passportNumber;
   
  }
public void setPassportNumber(String passportNumber){
      this.passportNumber = passportNumber;
  }
//*****************************************************************************************************************************************************************\\
@Override
public String toString() {
        return "Passenger{" + "passportNumber=" +
                passportNumber + ", name=" + name + "}";
    }
 }//class
//*****************************************************************************************************************************************************************\\

