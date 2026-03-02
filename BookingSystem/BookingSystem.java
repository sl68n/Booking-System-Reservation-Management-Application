//*****************************************************************************************************************************************************************\\
package BookingSystem;
import java.util.Scanner;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
//*****************************************************************************************************************************************************************\\
public class BookingSystem {
  
    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("C:\\Users\\sltan\\Downloads\\سطح المكتب\\flight_passenger.txt.txt");
        File input2 = new File("C:\\Users\\sltan\\Downloads\\سطح المكتب\\inputCommands.txt.txt");
        File outpt = new File("C:\\Users\\sltan\\Downloads\\سطح المكتب\\output.txt.txt");

        if (!input.exists() || !input.exists()){
            System.out.println("The input files does not exist !!");
            System.exit(0);
            }//if(1)
         
        Scanner flightPass = new Scanner (input);
        Scanner inputCom = new Scanner (input2);
        PrintWriter output= new PrintWriter (outpt);
        int numOfFlight = flightPass.nextInt();
        int numOfpass = flightPass.nextInt();
        int passCount = 0 , flightCount = 0 , ticketCount = 0;
        
        //--------------------------------------------------\\
        Passenger [] passengers = new Passenger[numOfpass];
        Flight [] flights = new Flight[numOfFlight];
        Ticket [] tickets = new Ticket[100];
        //--------------------------------------------------\\
        while (flightPass.hasNext()) {
            String command = flightPass.next();
            if (command.equals("AddFlight")) {
                
                // Read the flight information
                String flightNumber = flightPass.next();
                String departureCity = flightPass.next();
                String arrivalCity = flightPass.next();
                int gateNumber = flightPass.nextInt();
                int rows = flightPass.nextInt();
                int column = flightPass.nextInt();
                
                // Create the Flight object
                Flight flight = new Flight(flightNumber, departureCity, arrivalCity, gateNumber, rows, column);
                
                //Flight object 
                flights[flightCount] = flight;
                flightCount++;
                
                //flight information
                output.println("Flight " + flightNumber + " added successfully");

            }//if(2)
            
               else if (command.equals("AddPassenger")) {
                String passportNumber = flightPass.next();
                String name = flightPass.next();
                Passenger passenger = new Passenger(passportNumber, name);
                passengers[passCount] = passenger;
                passCount++;
                output.println("Passenger " + name + " added successfully");
               
               }//else if
            }//end loop
//*****************************************************************************************************************************************************************\\   
        //second input file
        while (inputCom.hasNext()) {
               String command = inputCom.next();
               if (command.equals("BookTicket")){
               output.println("\n********************BookTicket************************* \n");
               //booking information
                String passengerPassportNumber = inputCom.next();
                String flightNumber = inputCom.next();
                int seatRow = inputCom.nextInt();
                String seatColumn = inputCom.next();
                String seatClass = inputCom.next();
                
                // Check if the passenger exist
                int passengerIndex = searchPassenger(passengers, passengerPassportNumber);
                if (passengerIndex == -1){
                // If the passenger does not exist
                output.println("Passenger with Passport number " + passengerPassportNumber + " is not Registered");
                }
                else { //If passenegr exist
              
               // Check if flight exist
               int flightIndex = searchFlight(flights, flightNumber);
               if (flightIndex == -1){ 
               output.println("Flight " + flightNumber + " Not Found");
               
              }
                    else{ //If flight exist
                        
                        //if the seat is available
                        boolean seatAvailable = flights[flightIndex].isSeatAvailable(seatRow, seatColumn.charAt(0));
                        if (seatAvailable == false) {
                        output.println("Seat " + seatRow + seatColumn + " is already Reserved Or Not found");
                            
                        }
                        else {
                          // Create the Ticket object
                          Ticket ticket = new Ticket(flights[flightIndex], passengers[passengerIndex], seatColumn,seatRow, seatClass);
                          
                         //Ticket object
                           tickets[ticketCount] = ticket;
                               ticketCount++;
                         
                            //seat for the passenger
                            flights[flightIndex].bookSeat(seatRow, seatColumn.charAt(0), passengerPassportNumber);
                           
                            //ticket information
                            output.println("Seat booked successfully.");
                            output.println("Ticket Information: ");
                            output.println(ticket.toString());
                        }
                      }
                    }
                  }
                     else if (command.equals("GenerateInvoice")) {
                     output.println("\n********************Generate Ticket Invoice*************************\n");
                     int reservationNumber = inputCom.nextInt();
                     // Check if the ticket exist or not
                     int ticketIndex = searchTicket(tickets, reservationNumber);
                     if (ticketIndex == -1) { // If the ticket does not exist
                     output.println("Reservation Number is not available");
                      }
                     else { // If the ticket exist
                    output.println("Ticket Information: ");
                    output.println(tickets[ticketIndex].toString());
                    output.println("Total ticket price = " + tickets[ticketIndex].ticketPrice());
                      }
                   }
                    else if (command.equals("GenerateIFlightnvoice")) {
                    output.println("\n********************Generate Flight Invoice************************* \n");
                    String flightNumber = inputCom.next();
                   // Check if the flight exist or not
                   int flightIndex = searchFlight(flights, flightNumber);
                   if (flightIndex == -1) { // If the flight does not exist
                  output.println("Flight Not Found or No Ticket booked for this flight");
                   }
                  else { // If the flight exist
                    output.println("Seat Plane for flight " + flightNumber + " is: ");
                    output.println("************************************");
                    String [][] seatMap = flights[flightIndex].getseteMap();
                    //header of seat map
                    output.printf(" %-14s", "Row");
                    for (int i = 0; i < seatMap[0].length; i++){
                        int char_ascii_value = i + 97;
                        char char_value = (char) char_ascii_value;
                        output.printf("%-13s", char_value+"");
                    }
                        output.println();
                   //seat map
                    for (int i = 0; i < seatMap.length; i++) {
                        output.printf(" %-13d", i);
                         for (int j = 0; j < seatMap[i].length; j++){
                            if (seatMap[i][j] == null)
                                output.printf("%-13s", "O");
                            else
                                output.printf("%-13s", seatMap[i][j]);
                        }
                                output.println();
                    }
                                output.println();
                    
                    double totalInvoicePrice = 0;
                    for (int i = 0; i < seatMap.length; i++){
                        for (int j = 0; j < seatMap[i].length; j++){
                             if (seatMap[i][j] != null) {
                                int ticketIndex = -1;
                                for (int k = 0; k < tickets.length; k++){
                                    if (tickets[k] != null && tickets[k].getPassenger().getPassportNumber().equals(seatMap[i][j]) 
                                        && tickets[k].getFlight().getFlightNumber().equals(flightNumber)){ 
                                        //if seatMap[i][j] represent  passenger passport number
                                        ticketIndex = k;
                                        break;
                                    }
                                 }
                                
                                //ticket
                                output.println("Ticket Information: ");
                                output.println(tickets[ticketIndex].toString());
                                //  ticket price to the total
                                totalInvoicePrice += tickets[ticketIndex].ticketPrice();
                            }
                         }
                       }
                               output.println();
                               output.println("Total Invoice price =" + totalInvoicePrice);
                 }
             }
          }//end loop
                               flightPass.close();
                               inputCom.close();
                               output.close();
   }//main
//*****************************************************************************************************************************************************************\\
    public static int searchPassenger(Passenger [] passengers, String passPort){
        int index = -1;
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i] != null && passengers[i].getPassportNumber().equals(passPort)){ 
                index = i;
                break;
            }
         }
           return index;
    }
    
//*****************************************************************************************************************************************************************\\
     public static int searchFlight(Flight[] flights, String flightNumber){
    int index = -1;
    for (int i = 0; i < flights.length; i++) {
        if (flights[i] != null && flightNumber.equals(flights[i].getFlightNumber())){
            index = i;
            break;
        }
    }
    return index;

      }
//*****************************************************************************************************************************************************************\\
    public static int searchTicket(Ticket [] tickets, int reservationNumber){
        int index = -1;
         for (int i = 0; i < tickets.length; i++) {
             if (tickets[i] != null && tickets[i].getReservationConfirmationNumber() == reservationNumber){  
                index = i;
                break;
            }
         }
         return index;
    }      
 }//package 
 //*****************************************************************************************************************************************************************\\
