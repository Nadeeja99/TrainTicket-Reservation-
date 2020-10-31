// Student Name: D. L. Nadeeja Perera 

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Reservation extends Application {

    static final int SEATING_CAPACITY = 42;  // Number of seats in the train

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainMenu();
    }

    public void mainMenu() throws Exception {

        // Prompt the menu to user to choose an option
        System.out.println("--- Denuwara Menike train to Badulla --- ");
        System.out.println("Welcome to the Train ticket Reservation System");
        System.out.println("Please observe the following instructions to proceed \n");
        System.out.println("Press A - Add customer to seat");
        System.out.println("Press V - Views All seats");
        System.out.println("Press E - Display Empty seats ");
        System.out.println("Press D - Delete customer from seat ");
        System.out.println("Press F - Find the seat for a given customers name ");
        System.out.println("Press S - Store program data in to file");
        System.out.println("Press L - Load program data from file ");
        System.out.println("Press O - View seats Ordered alphabetically by name");
        System.out.println("Press Q - To Quit the program \n");

        Scanner sc = new Scanner(System.in);

        // Array to store user's reservations.
        String seatDetails[][] = new String[SEATING_CAPACITY][2];

        while (true){

            System.out.print("Please Enter your option : ");
            String userInput = sc.nextLine().toUpperCase();

            switch (userInput){
                case "A" :
                    seatAdd(seatDetails);  // calling the method
                    break;
                case "V":
                    viewALL(seatDetails);
                    break;
                case "E" :
                    emptySeats(seatDetails);
                    break;
                case "D":
                    delete(seatDetails);
                    break;
                case "F" :
                    find(seatDetails);
                    break;
                case "S":
                    storeData(seatDetails);
                    break;
                case "L" :
                    loadData();
                    break;
                case "O":
                    nameOrder(seatDetails);
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

    // method for booking seats
    public void seatAdd(String seatDetails[][]){

        AnchorPane anchorPane = new AnchorPane();

        Text text = new Text();
        text.setText("*Please select a seat and enter the user's name.\n*Then click,\"Reserve\" button to complete the reservation.");
        text.setLayoutY(50);
        text.setLayoutX(10);                              // set the positions
        text.setFill(Color.RED);                          // set styles to the text
        text.setFont(new Font("Arial", 12));

        Label label1 = new Label();
        label1.setText("Username :");
        label1.setLayoutX(20);
        label1.setLayoutY(470);
        label1.setStyle("-fx-font-weight: bold");

        TextField textField1 = new TextField();
        textField1.setPromptText("Enter the Name");
        textField1.setLayoutX(20);
        textField1.setLayoutY(490);

        Label label2 = new Label();
        label2.setText("Seat No : ");
        label2.setLayoutX(210);
        label2.setLayoutY(470);
        label2.setStyle("-fx-font-weight: bold");

        TextField textField2 = new TextField();
        textField2.setPromptText("Reserved Seat No");
        textField2.setLayoutX(210);
        textField2.setLayoutY(490);

        Button btnOk = new Button();
        btnOk.setText("Reserve");
        btnOk.setLayoutX(150);
        btnOk.setLayoutY(530);
        btnOk.setMinSize(100,30);
        btnOk.setStyle("-fx-base: red;");

        Button btnExit = new Button();
        btnExit.setText("Exit");
        btnExit.setLayoutX(300);
        btnExit.setLayoutY(560);
        btnExit.setMinSize(50,30);

        Button [] seatsArray = new Button[42];        // create a button array

        for (int i = 0; i < SEATING_CAPACITY; i++){
            seatsArray[i] = new Button("Seat " + (i + 1));

            seatsArray[i].setStyle("-fx-base: green;");    // set style for the array

            final Button seatsArr = seatsArray[i];    /* Introducing the seatArray[i] as a final variable.
                                                       Because local variables referenced from a lambda expression
                                                      must be final or effectively final*/
            // convert int to string
            String seatN = String.valueOf(i+1);

            // create button's action
            seatsArray[i].setOnAction(event ->  {
                seatsArr.setStyle("-fx-base: red;");
                seatsArr.setText("Reserved");
                seatsArr.setDisable(true);

                textField2.setText(seatN);

            });

            btnOk.setOnAction(event -> {
                String name = textField1.getText();
                String seatNo = textField2.getText();

                // check if the textfields are empty or not
                if (!name.isEmpty() && !seatNo.isEmpty()){
                    int seatNo2 = Integer.parseInt(seatNo);  // convert string to int

                    // Checking the seat's availability
                    if (seatDetails[seatNo2 - 1][0] == null && seatDetails[seatNo2 - 1][1] == null){

                        seatDetails[seatNo2 - 1][0] = seatNo;    // get user's reserved seat No to the array
                        seatDetails[seatNo2 - 1][1] = name;      // get user's name to the array

                        System.out.println(name +",You reserved seat No : "+ seatNo);

                    }
                    else {
                        System.out.println("This seat is already reserved.Please try another seat !!!");
                    }
                }
                else{
                    System.out.println("Please input a Name and select a seat !!!");
                }

            });

            btnExit.setOnAction(event -> {
                Stage stageClose = (Stage) btnExit.getScene().getWindow();
                stageClose.close();
            });

        }

        seatStructure(anchorPane, seatsArray);

        AnchorPane anchorPane2 = new AnchorPane();
        anchorPane2.setStyle("-fx-base: orange;");

        //add elements to anchorPane
        anchorPane2.getChildren().addAll(anchorPane,label1,label2,textField1,textField2,btnOk,btnExit,text);

        Stage stage = new Stage();

        //create a scene with anchorPane2 as the root
        Scene scene = new Scene(anchorPane2, 400, 600);

        //configure the stage,set the scene and display
        stage.setTitle("Train ticket Reservation System");
        stage.setScene(scene);
        stage.showAndWait();

    }

    // method for create seat structure
    public static void seatStructure(AnchorPane anchorPane,Button [] seatsArray  ){

        anchorPane.setStyle("-fx-base: orange;");

        Text head = new Text();
        head.setText("Denuwara Menike train to Badulla ");
        head.setLayoutY(25);
        head.setLayoutX(30);
        head.setFill(Color.BLUE);
        head.setFont(Font.font("Franklin Gothic Heavy", 20));
        head.setStyle("-fx-font-weight: bold");
        head.setUnderline(true);

        // use HBoxes and VBoxes to get well structured button array
        HBox[] hBox1 = new HBox[11];
        HBox[] hBox2 = new HBox[11];
        HBox[] hBox3 = new HBox[10];
        HBox[] hBox4 = new HBox[10];

        for (int i = 0; i < 11; i++){
            if (i == 10){
                hBox1[i] = new HBox(seatsArray[i * 4]);
                hBox2[i] = new HBox(seatsArray[i * 4 + 1]);
            }
            else{
                hBox1[i] = new HBox(seatsArray[i *4]);
                hBox2[i] = new HBox(seatsArray[i *4 + 1]);
                hBox3[i] = new HBox(seatsArray[i *4 + 2]);
                hBox4[i] = new HBox(seatsArray[i *4 + 3]);
            }
        }

        VBox vBox1 = new VBox(10, hBox1);
        VBox vBox2 = new VBox(10, hBox2);
        VBox vBox3 = new VBox(10, hBox3);
        VBox vBox4 = new VBox(10, hBox4);

        HBox hBox5 = new HBox(10, vBox1, vBox2);
        HBox hBox6 = new HBox(10, vBox3, vBox4);

        HBox hBox7 = new HBox(80, hBox5,hBox6);

        hBox7.setLayoutX(20);
        hBox7.setLayoutY(80);

        //add elements to anchorPane
        anchorPane.getChildren().addAll(hBox7,head);

    }

    // method for view all seats
    public static void viewALL(String seatDetails[][]){
        AnchorPane anchorPane = new AnchorPane();

        Button [] seatsArray = new Button[42];

        for (int i = 0; i < SEATING_CAPACITY; i++){
            seatsArray[i] = new Button("Seat " + (i + 1));

            seatsArray[i].setStyle("-fx-base: green;");

            if (seatDetails[i][0] != null && seatDetails[i][1] != null){
                seatsArray[i].setStyle("-fx-base: red;");
                seatsArray[i].setText("Reserved");
            }
        }

        seatStructure(anchorPane, seatsArray);

        Label label = new Label("All Seats");
        label.setLayoutY(40);
        label.setLayoutX(150);
        label.setStyle("-fx-font-weight: bold");
        label.setFont(new Font("Franklin Gothic Heavy", 18));

        Button btnExit = new Button();
        btnExit.setText("Exit");
        btnExit.setLayoutX(300);
        btnExit.setLayoutY(450);
        btnExit.setMinSize(50,30);

        btnExit.setOnAction(event -> {
            Stage stageClose = (Stage) btnExit.getScene().getWindow();
            stageClose.close();
        });

        anchorPane.getChildren().addAll(label,btnExit);

        Stage stage = new Stage();

        //create a scene with anchorPane as the root
        Scene scene = new Scene(anchorPane, 400, 500);

        //configure the stage,set the scene and display
        stage.setTitle("Train ticket Reservation System");
        stage.setScene(scene);
        stage.showAndWait();

    }

    // method for view empty seats
    public static void emptySeats(String seatDetails[][]){
        AnchorPane anchorPane = new AnchorPane();

        Button [] seatsArray = new Button[42];

        for (int i = 0; i < SEATING_CAPACITY; i++){
            seatsArray[i] = new Button("Seat " + (i + 1));

            //seatsArray[i].setStyle("-fx-base: green;");

            if (seatDetails[i][0] != null && seatDetails[i][1] != null){
                seatsArray[i].setVisible(false);       // not visible the seat(button) if it was reserved

            }
            else{
                seatsArray[i].setStyle("-fx-base: green;");
                seatsArray[i].setText((i + 1) + " : empty");
            }
        }

        seatStructure(anchorPane, seatsArray);

        Label label = new Label("Empty Seats");
        label.setLayoutY(40);
        label.setLayoutX(150);
        label.setStyle("-fx-font-weight: bold");
        label.setFont(new Font("Franklin Gothic Heavy", 18));

        Button btnExit = new Button();
        btnExit.setText("Exit");
        btnExit.setLayoutX(300);
        btnExit.setLayoutY(450);
        btnExit.setMinSize(50,30);

        btnExit.setOnAction(event -> {
            Stage stageClose = (Stage) btnExit.getScene().getWindow();
            stageClose.close();
        });

        anchorPane.getChildren().addAll(label,btnExit);

        Stage stage = new Stage();

        //create a scene with anchorPane as the root
        Scene scene = new Scene(anchorPane, 450, 500);

        //configure the stage,set the scene and display
        stage.setTitle("Train ticket Reservation System");
        stage.setScene(scene);
        stage.showAndWait();

    }

    // method for delete customer from seat
    public void delete(String seatDetails[][]){
        Scanner myScn = new Scanner(System.in);
        System.out.println("Which seat number do you want to delete from customer?");
        int seatNum = myScn.nextInt();

        // checks if the seat reserved or not
        if (seatDetails[seatNum - 1][0] != null && seatDetails[seatNum - 1][1] != null){

            seatDetails[seatNum - 1][0] = null;      // if the seat is reserved,delete the reservation.
            seatDetails[seatNum - 1][1] = null;
            System.out.println("Seat No " + seatNum + " deleted successfully.\n");
        }

        else{
            System.out.println("This seat is already empty.\n");
        }
    }

    // method for find seat for a given customer
    public static void find(String seatDetails[][]){
        Scanner myScn = new Scanner(System.in);
        System.out.println("Enter user's name to find reserved seat :");
        String userName = myScn.nextLine();

        for (int x = 0; x < SEATING_CAPACITY; x++){
            // check the user's input match with the reservations
            if (seatDetails[x][1] != null && seatDetails[x][1].equalsIgnoreCase(userName)){
                System.out.println(userName + "'s seat is " + seatDetails[x][0] + "\n");
                break;
            }
            else if (x == (SEATING_CAPACITY - 1)){
                System.out.println("No seat reserved for such a name like " + userName + "\nPlease check again!!!\n");
            }
        }
    }

    // method for Store program data in to file
    public static void storeData(String seatDetails[][])throws Exception{

        // FileWriter to store program data.
        FileWriter fileWriter = new FileWriter("file.txt");

        for (int i = 0; i < seatDetails.length; i++){
            if (seatDetails[i][0] != null && seatDetails[i][1] != null){
                fileWriter.write( seatDetails[i][1] + "   " + seatDetails[i][0] + "\n");
            }
        }

        fileWriter.close();
        System.out.println("Stored program data into a file successfully \n");
    }

    // method for Load program data from file
    public static void loadData() {

        try {
            // File to load program data.
            File readFile = new File("file.txt");
            Scanner myScn = new Scanner(readFile);
            System.out.println("------ Reservations ------");
            while (myScn.hasNextLine()) {
                String data = myScn.nextLine();
                System.out.println(data);
            }
            myScn.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // method for view seats Ordered alphabetically by name
    public static void nameOrder(String seatDetails[][]){

        // create a copy of seatDetails array
        String myStr[][] = seatDetails.clone();
        String temp;
        String temp2;

        // used bubble sort implementation
        for (int i = 0; i < myStr.length; i++){
            if (myStr[i][1] != null && myStr[i][0] != null){
                for (int j =+ i; j < myStr.length; j++){
                    if (myStr[j][1] != null && myStr[i][0] != null){
                        if (myStr[j][1].compareTo(myStr[i][1]) < 0){
                            temp = myStr[i][1];
                            temp2 = myStr[i][0];
                            myStr[i][1] = myStr[j][1];
                            myStr[i][0] = myStr[j][0];
                            myStr[j][1] = temp;
                            myStr[j][0] = temp2;
                        }
                    }
                }
            }
        }

        System.out.println("---- Sorted Name List ----");
        for (int i = 0; i < SEATING_CAPACITY; i++){
            if (myStr[i][1] != null && myStr[i][0] != null){
                System.out.println("Name : " + myStr[i][1] + "\tSeat No : " + myStr[i][0]);
            }
        }
    }
}
