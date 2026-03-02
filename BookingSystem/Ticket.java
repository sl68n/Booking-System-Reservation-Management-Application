//*****************************************************************************************************************************************************************\\
package BookingSystem;
  
  public class Ticket {

    private Flight flight;
    private Passenger passenger;
    private String seatNumber;
    private int seatRow;
    private String classType;
    private int reservationConfirmationNumber;
    static int reservationNumber = 100;
//*****************************************************************************************************************************************************************\\
public Ticket(Flight flight, Passenger passenger, String seatNumber,
            int seatrow, String classType) {
            this.flight = flight;
            this.passenger = passenger;
            this.seatRow = seatrow;
            this.seatNumber = seatNumber;
            this.classType = classType;
            reservationConfirmationNumber = reservationNumber++;
      }
//*****************************************************************************************************************************************************************\\       
        public Flight getFlight() {
               return flight;
            } 
        public Passenger getPassenger() {
                return passenger;
            } 
        public String  getSeatNumber() {
                return seatNumber; 
            } 
        public void setSeatNumber(String seatNumber) {
                this.seatNumber = seatNumber; 
            } 
         public int getSeatRow(){
                return seatRow;
            }
        public String getClassTyape(){
                return classType;
            }
        public int getReservationConfirmationNumber(){
                return reservationConfirmationNumber;
            }
        public void setFlight(Flight flight) {
                this.flight = flight;
            }
        public void seatSeatNumber(String seatNumber){
                 this.seatNumber = seatNumber;
             }
        public void setPassenger(Passenger passenger){
                 this.passenger = passenger;
             }
        public void setSetRow(int seatRow){
                 this.seatRow = seatRow;
             }
        public void setClassType(String classType){
                 this.classType = classType;
             }
//*****************************************************************************************************************************************************************\\
    public double ticketPrice(){
             double price = 2000; 
             if (flight.getArrivalCity().equals("JED")) {
               price = price- (price*0.20);
            }
          if (classType.equals("BusinessClass")) {
      // type is BusinessClass ,Add 200%
                price = price+(price*2);
            }
            else if (classType.equals("FirstClass")) {//type is FirstClass , Add 400%
                price = price + (price*4);
            }
              price = price+(price*0.15);
               //Vat 15%
               return price;
     }  
//*****************************************************************************************************************************************************************\\
@Override
public String toString() {
        return "Reservation Confirmation Number= " 
                + reservationConfirmationNumber + ", Flight Number="
                + flight.getFlightNumber() + ", Passenger Name= " 
                + passenger.getName() + ", Seat Number= " + seatRow
                +seatNumber  + " , classType= " + classType;
    }
 }//class  input  
//*****************************************************************************************************************************************************************\\
   

