// Student Name: D. L. Nadeeja Perera 

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class TrainStation extends Application {

    // create an array to load previous data from CW_01
    private static String[][] passenger = new String[42][2];

    // create an array to store Train passenger's details
    private static String[][] trainQueue = new String[42][3];

    public static void main(String[] args)  {
        launch(args);
    }

    // method to get data from CW_01
    public static void getData() throws FileNotFoundException {

        // load a file where CW_ 01 data stored
        FileReader fileReader = new FileReader("ReservationSeats/file.txt");

        Scanner sc = new Scanner(fileReader);

        while (sc.hasNextLine()) {

            String data = sc.nextLine();
            String[] passengerData = data.split("   ");

            for (int count = 0; count < passengerData.length; count++) {

                if (count % 2 == 1) {
                    int seatNo = Integer.parseInt(passengerData[count]);

                    // store all details from cw 01 to a 2D Array.
                    passenger[seatNo - 1][0] = passengerData[count - 1];  // passenger's name
                    passenger[seatNo - 1][1] = passengerData[count];      // passenger's seat

                }
            }
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        getData();
        mainMenu();

    }

    public void mainMenu() throws Exception {

        // Prompt the menu to user to choose an option
        System.out.println("--- Denuwara Menike train to Badulla --- ");
        System.out.println("Welcome to the Train ticket Reservation System");
        System.out.println("Please observe the following instructions to proceed ");
        System.out.println("--------------------------------------------------");
        System.out.println("Press A - Add a passenger to the trainQueue ");
        System.out.println("Press V - View the trainQueue ");
        System.out.println("Press D - Delete passenger from the trainQueue ");
        System.out.println("Press S - Store trainQueue data into a plain text file");
        System.out.println("Press L - Load data back from the file into the trainQueue");
        System.out.println("Press R - Run the simulation and produce report ");
        System.out.println("Press Q - To Quit the program");
        System.out.println("--------------------------------------------------");

        Scanner sc = new Scanner(System.in);

        while (true){

            System.out.print("Please Enter your option : ");
            String userInput = sc.nextLine().toUpperCase();

            switch (userInput){
                case "A" :
                    PassengerQueue.addPassenger(passenger,trainQueue);  // calling the method
                    break;
                case "V":
                    PassengerQueue.viewTrain(trainQueue);
                    break;
                case "D":
                    PassengerQueue.deleteFromTrainQueue(trainQueue);
                    break;
                case "S":
                    PassengerQueue.storeDetails(trainQueue);
                    break;
                case "L" :
                    PassengerQueue.loadDetails();
                    break;
                case "R":
                    Passenger.simulation(trainQueue);
                    break;
                case "Q":
                    // loop will Exit after Enter "Q"
                    System.out.println("\nThank you for joined with us.\nHope to see you again!!!");
                    System.exit(0);
                default:
                    System.out.println("Please Enter valid option !!!");
                    break;
            }
        }
    }
}

