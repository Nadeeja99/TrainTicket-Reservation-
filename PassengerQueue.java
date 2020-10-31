// Student Name: D. L. Nadeeja Perera 

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class PassengerQueue {

    // method for add passenger to the TrainQueue from waitingRoom
    public static void addPassenger(String[][] passenger, String[][] trainQueue)  {

        /*create a 1 six sided die to generate the no of passengers
          which should be move to a trainqueue at a time.*/
        Random dice = new Random();
        int randomValue = 1 + dice.nextInt(6);

        // create an array to store random passengers
        String[][] randomQueue = new String[randomValue][2];

        System.out.println("----------Waiting Room----------");

        for (int i =0,x=0; i < passenger.length; i++){
            if (passenger[i][0] != null && passenger[i][1] != null){
                // show current Waiting Room
                System.out.println("Name : " + passenger[i][0] +"   " + "Seat No: " + passenger[i][1]);

                if (x < randomValue){
                    // add random passengers to the randomQueue
                    randomQueue[x][0] = passenger[i][0];    // passenger's name
                    randomQueue[x][1] = passenger[i][1];    // passenger's seat No

                    passenger[i][1] = null;

                    x++;
                }
            }
        }

        System.out.println("---------------------------------");

        // show how many passengers can be added to the TrainQueue
        for (int i = 0; i < 1; i++){
            if (randomQueue[i][0] != null && randomQueue[i][1] != null ) {
                System.out.println(randomValue + " Passengers can Enter to the TrainQueue.");
            }
            else {
                System.out.println("There are no passengers in the waiting room !!!");
            }
        }

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-base: #2e2927;");

        Button btnExit = new Button();
        btnExit.setText("Exit");
        btnExit.setLayoutX(350);
        btnExit.setLayoutY(300);
        btnExit.setMinSize(50,30);

        Label name = new Label("Passenger Name : ");
        name.setLayoutX(10);
        name.setLayoutY(200);
        name.setStyle("-fx-font-weight: bold");
        name.setFont(new Font("Franklin Gothic Heavy", 16));

        Label getName = new Label();
        getName.setLayoutX(160);
        getName.setLayoutY(200);
        getName.setStyle("-fx-font-weight: bold");
        getName.setFont(new Font("Franklin Gothic Heavy", 16));

        Label messeage = new Label();
        messeage.setLayoutX(10);
        messeage.setLayoutY(240);
        messeage.setStyle("-fx-font-weight: bold");
        messeage.setFont(new Font("Franklin Gothic Heavy", 16));

        // create a Button Array to display random number of passenger
        Button[] randomPassengers = new Button[randomValue];

        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(20);
        gridPane.setLayoutY(120);

        // initialize gridpane's indexes
        int x=0,y=1;

        for (int i = 0; i < randomValue; i++){
            if (randomQueue[i][0] != null && randomQueue[i][1] != null) {
                randomPassengers[i] = new Button();
                randomPassengers[i].setStyle("-fx-base: green;");
                randomPassengers[i].setMinSize(60, 20);
                randomPassengers[i].setText("Seat : " + randomQueue[i][1]);

                // added Button Array to the gridpane
                gridPane.add(randomPassengers[i], x, y);
                x++;
                gridPane.setHgap(10);

                /*Introducing the seatArray[i] as a final variable.
                  Because local variables referenced from a lambda expression
                  must be final or effectively final*/
                final int index = i;

                int j = Integer.parseInt(randomQueue[i][1]);

                randomPassengers[i].setOnAction(event -> {
                    randomPassengers[index].setStyle("-fx-base: #eb8423;");
                    randomPassengers[index].setText("Added");
                    getName.setText(String.valueOf(randomQueue[index][0]));
                    messeage.setText("Seat No " + randomQueue[index][1] + " added to Train Queue Successfully !!! ");

                    // random passenger added to the TrainQueue
                    trainQueue[j - 1][0] = randomQueue[index][0];
                    trainQueue[j - 1][1] = randomQueue[index][1];

                    int time1 = 1 + dice.nextInt(6);
                    int time2 = 1 + dice.nextInt(6);
                    int time3 = 1 + dice.nextInt(6);

                    int timeTot = time1 + time2 + time3;

                    int [] waitingTime = new int[42];
                    waitingTime[j - 1] = timeTot;

                    trainQueue[j - 1][2] =String.valueOf(waitingTime[j - 1]);
                });
            }

            btnExit.setOnAction(event -> {
                Stage stageClose = (Stage) btnExit.getScene().getWindow();
                stageClose.close();
            });
        }

        Text head = new Text();
        head.setText("Denuwara Menike train to Badulla ");
        head.setLayoutY(25);
        head.setLayoutX(80);
        head.setFill(Color.LIGHTBLUE);
        head.setFont(Font.font("Franklin Gothic Heavy", 20));
        head.setStyle("-fx-font-weight: bold");
        head.setUnderline(true);

        Text text = new Text();
        text.setText("* Please select a passenger to add to the TrainQueue.\n* Click 'Exit' to close the window.");
        text.setLayoutY(60);
        text.setLayoutX(10);                              // set the positions
        text.setFill(Color.YELLOW);                          // set styles to the text
        text.setFont(new Font("Franklin Gothic Heavy", 14));

        anchorPane.getChildren().addAll(gridPane,btnExit,head,text,name,getName,messeage);

        Stage stage = new Stage();

        //create a scene with anchorPane as the root
        Scene scene = new Scene(anchorPane, 450, 350);

        //configure the stage,set the scene and display
        stage.setTitle("Train ticket Reservation System");
        stage.setScene(scene);
        stage.showAndWait();
    }

    // method for view TrainQueue
    public static void viewTrain (String[][] trainQueue){
        AnchorPane pane2 = new AnchorPane();
        pane2.setStyle("-fx-base: #2e2927;");

        Text txtHead = new Text();
        txtHead.setText("Denuwara Menike Train to Badulla ");
        txtHead.setLayoutY(25);
        txtHead.setLayoutX(30);
        txtHead.setFill(Color.LIGHTBLUE);
        txtHead.setFont(Font.font("Franklin Gothic Heavy", 20));
        txtHead.setStyle("-fx-font-weight: bold");
        txtHead.setUnderline(true);

        Text lblTitle = new Text("Train Queue");
        lblTitle.setLayoutY(60);
        lblTitle.setLayoutX(150);
        lblTitle.setFill(Color.YELLOW);
        lblTitle.setStyle("-fx-font-weight: bold");
        lblTitle.setFont(new Font("Franklin Gothic Heavy", 18));

        Button btnExit = new Button();
        btnExit.setText("Exit");
        btnExit.setLayoutX(350);
        btnExit.setLayoutY(460);
        btnExit.setMinSize(50,30);

        btnExit.setOnAction(event -> {
            Stage stageClose = (Stage) btnExit.getScene().getWindow();
            stageClose.close();

        });

        // create Button Array to display train queue with 42 slots
        Button [] seatsArray = new Button[42];

        for (int i = 0; i < 42; i++){
            seatsArray[i] = new Button("Seat " + (i + 1));

            seatsArray[i].setStyle("-fx-base: green;");

            if (trainQueue[i][0] != null && trainQueue[i][1] != null){
                seatsArray[i].setStyle("-fx-base: red;");
                // if the passenger arrived show passenger's name
                seatsArray[i].setText(trainQueue[i][1] + " : " + trainQueue[i][0]);
            }
            else {
                // if the passenger not arrived show 'Empty'.
                seatsArray[i].setText("Empty");
            }
        }

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

        pane2.getChildren().addAll(lblTitle,hBox7,txtHead,btnExit);

        Stage stage = new Stage();

        //create a scene with anchorPane as the root
        Scene scene = new Scene(pane2, 450, 520);

        //configure the stage,set the scene and display
        stage.setTitle("Train ticket Reservation System");
        stage.setScene(scene);
        stage.showAndWait();
    }

    // method for Delete a passenger from the Train Queue
    public static void deleteFromTrainQueue(String[][] trainQueue){
        Scanner myScn = new Scanner(System.in);
        System.out.println("Which passenger's seat number do you want to delete from Train Queue?");
        int seatNum = myScn.nextInt();

        // checks if the passenger came or not
        if (trainQueue[seatNum - 1][0] != null && trainQueue[seatNum - 1][1] != null){

            trainQueue[seatNum - 1][0] = null;      // if the passenger in the trainQueue,delete the passenger.
            trainQueue[seatNum - 1][1] = null;
            trainQueue[seatNum - 1][2] = null;
            System.out.println("Seat No " + seatNum + " deleted successfully from TrainQueue.");
        }
        else{
            System.out.println("There is no passenger here to delete.\nIt's already Empty.");
        }
    }

    // method for Store TrainQueue details to a file
    public static void storeDetails(String[][] trainQueue)throws Exception{

        // FileWriter to store TrainQueue details.
        FileWriter fileWriter = new FileWriter("TrainQueue_details.txt");

        for (int i = 0; i < trainQueue.length; i++){
            if (trainQueue[i][0] != null && trainQueue[i][1] != null){
                fileWriter.write( "Seat No : " + trainQueue[i][1] + "   Passenger : " + trainQueue[i][0] + "\n");
            }
        }

        fileWriter.close();
        System.out.println("Stored Train Queue details into a file successfully.");
    }

    // method for Load rainQueue details from a file
    public static void loadDetails() {

        try {
            // File to load TrainQueue details.
            File readFile = new File("TrainQueue_details.txt");
            Scanner myScn = new Scanner(readFile);
            System.out.println("------ Train Queue Details ------");
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
}


