//*****************************************************************************************************************************************************************\\
package BookingSystem;
  
 public class Flight {
   
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private int gateNumber;
    private String [][]seatMap;
    private int row;
    private int column;
    public int column_index = column -65;
    public int row_index = row -1;  
//*****************************************************************************************************************************************************************\\
    public Flight(String flightNumber, String departureCity, String arrivalCity, int gateNumber, int row, int column) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.gateNumber = gateNumber;
        this.row = row;
        this.column = column;
        // 2D Array 
        seatMap = new String[row][column];
      }
//*****************************************************************************************************************************************************************\\
    public String getFlightNumber(){
                return flightNumber;
            }
        public String getDepartureCity(){
                return departureCity;
            }
        public String getArrivalCity(){
                return arrivalCity;
            }
        public int getGateNumber(){
                return gateNumber;
            }
        public String [][] getseteMap (){
                return seatMap;
            }
        public void setFlightNumber(String flightNumber){
                this.flightNumber = flightNumber;
            }
        public void setDepartureCity(String departureCity){
                this.departureCity = departureCity;
            }
        public void setArrivalCity(String arrivalCity){
                this.arrivalCity = arrivalCity;
            }
        public void setGateNumber(int gateNumber){
                this.gateNumber = gateNumber;
            }
//*****************************************************************************************************************************************************************\\
    public boolean bookSeat(int row, char column, String passengerID) {
        if (isSeatAvailable(row, column)) {
            column_index = column - 65;
            row_index = row - 1;
            seatMap[row_index][column_index] = passengerID;
            return true;
          }
        else
           return false;
      }
//*****************************************************************************************************************************************************************\\
     public boolean isSeatAvailable(int row, char column) {
                int column_index = column - 65;
                if (column_index < this.column && row < this.row && seatMap[row][column_index] == null)
                   return true;
                else
                   return false;
              }
//*****************************************************************************************************************************************************************\\        
        @Override
        public String toString() {
                return "Flight{" + "flightNumber=" + flightNumber + 
                        ", departureCity=" + departureCity + ", arrivalCity="
                        + arrivalCity + ", gateNumber=" + gateNumber + ", seatMap=" 
                        + seatMap + ", row=" + row + ", column=" + column + '}';
            }
  
}//class
//*****************************************************************************************************************************************************************\\