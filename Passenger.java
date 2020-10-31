// Student Name: D. L. Nadeeja Perera 

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileWriter;

public class Passenger {

    // array to get only waiting time of the passengers
    private static int[] waitingTime = new int[42];

    public static void simulation(String[][] trainQueue) throws Exception {
        AnchorPane anchorPane = new AnchorPane();

        Text txtHead = new Text();
        txtHead.setText("Denuwara Menike Train to Badulla ");
        txtHead.setLayoutY(25);
        txtHead.setLayoutX(40);
        txtHead.setFill(Color.BLUE);
        txtHead.setFont(Font.font("Franklin Gothic Heavy", 20));
        txtHead.setStyle("-fx-font-weight: bold");
        txtHead.setUnderline(true);

        Text lblTitle = new Text("Passenger Report");
        lblTitle.setLayoutY(60);
        lblTitle.setLayoutX(120);
        lblTitle.setFill(Color.YELLOW);
        lblTitle.setStyle("-fx-font-weight: bold");
        lblTitle.setFont(new Font("Franklin Gothic Heavy", 18));

        Text maxTime = new Text("Maximum Waiting Time : ");
        maxTime.setLayoutY(100);
        maxTime.setLayoutX(280);
        maxTime.setFill(Color.RED);
        maxTime.setStyle("-fx-font-weight: bold");
        maxTime.setFont(new Font("Arial", 12));

        Text minTime = new Text("Minimum Waiting Time : ");
        minTime.setLayoutY(150);
        minTime.setLayoutX(280);
        minTime.setFill(Color.RED);
        minTime.setStyle("-fx-font-weight: bold");
        minTime.setFont(new Font("Arial", 12));

        Text avgTime = new Text("Average Waiting Time : ");
        avgTime.setLayoutY(200);
        avgTime.setLayoutX(280);
        avgTime.setFill(Color.RED);
        avgTime.setStyle("-fx-font-weight: bold");
        avgTime.setFont(new Font("Arial", 12));

        Button btnOk = new Button();
        btnOk.setText("Ok");
        btnOk.setLayoutX(360);
        btnOk.setLayoutY(450);
        btnOk.setMinSize(50,30);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setLayoutX(40);
        gridPane.setLayoutY(70);

        Text[] passenger = new Text[trainQueue.length];

        int x = 1,y = 1;
        for (int i = 0; i < trainQueue.length; i++) {
            if (trainQueue[i][0] != null && trainQueue[i][1] != null ) {
                passenger[i] = new Text();
                passenger[i].setText("Seat No : " + trainQueue[i][1] + "\nPassenger Name : " + trainQueue[i][0] + "\nWaiting Time : " + trainQueue[i][2]);
                passenger[i].setStyle("-fx-font-weight: bold");
                passenger[i].setFill(Color.LIGHTGREEN);

                // get the integer values of waiting times.
                waitingTime[i] = Integer.parseInt(trainQueue[i][2]);

                // added Button Array to the gridpane
                gridPane.add(passenger[i], x, y);
                x++;
                if (x <= 2) {
                    y++;
                    x=1;
                    gridPane.setHgap(20);     // set the positions
                    gridPane.setVgap(20);
                }
            }
            btnOk.setOnAction(event -> {
                Stage stageClose = (Stage) btnOk.getScene().getWindow();
                stageClose.close();
            });
        }
        Text getMaxTime = new Text();
        getMaxTime.setText(String.valueOf(getMaxTime(waitingTime)));
        getMaxTime.setLayoutY(120);
        getMaxTime.setLayoutX(340);
        getMaxTime.setFill(Color.ORANGE);
        getMaxTime.setStyle("-fx-font-weight: bold");
        getMaxTime.setFont(new Font("Franklin Gothic Heavy", 14));

        Text getMinTime = new Text();
        getMinTime.setText(String.valueOf(getMinTime(waitingTime)));
        getMinTime.setLayoutY(170);
        getMinTime.setLayoutX(340);
        getMinTime.setFill(Color.ORANGE);
        getMinTime.setStyle("-fx-font-weight: bold");
        getMinTime.setFont(new Font("Franklin Gothic Heavy", 14));

        Text getAvgTime = new Text();
        getAvgTime.setText(String.valueOf(getAvgTime(waitingTime)));
        getAvgTime.setLayoutY(220);
        getAvgTime.setLayoutX(335);
        getAvgTime.setFill(Color.ORANGE);
        getAvgTime.setStyle("-fx-font-weight: bold");
        getAvgTime.setFont(new Font("Franklin Gothic Heavy", 14));

        anchorPane.getChildren().addAll(gridPane,lblTitle,txtHead,btnOk,maxTime,getMaxTime,minTime,getMinTime,avgTime,getAvgTime);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(anchorPane);
        scrollPane.setStyle("-fx-background: #2e2927;");

        Stage stage = new Stage();

        //create a scene with scrollPane as the root
        Scene scene = new Scene(scrollPane, 450, 500);

        //configure the stage,set the scene and display
        stage.setTitle("Passenger Details Report");
        stage.setScene(scene);
        stage.showAndWait();

        report(trainQueue);  // call the report() method to save the report to a file.

    }

    // method for Store TrainQueue details to a file
    public static void report(String[][] trainQueue)throws Exception{

        // FileWriter to store TrainQueue details.
        FileWriter fileWriter = new FileWriter("Report.txt");

        for (int i = 0; i < trainQueue.length; i++){
            if (trainQueue[i][0] != null && trainQueue[i][1] != null){
                fileWriter.write( "Seat No : " + trainQueue[i][1] + "\nPassenger : " + trainQueue[i][0] + "\nWaiting Time : " + trainQueue[i][2] + "\n\n");
            }
        }

        fileWriter.write("Maximum Waiting Time : " + getMaxTime(waitingTime) + "\n");
        fileWriter.write("Minimum Waiting Time : " + getMinTime(waitingTime) + "\n");
        fileWriter.write("Average Waiting Time : " + getAvgTime(waitingTime));

        fileWriter.close();
        System.out.println("Saved report into a file successfully.");
    }

    // method for getting the maximum waiting time
    public static int getMaxTime(int[] waitingTime) {
        int maxTime = waitingTime[0];                     // get the first index of array
        for (int i = 1; i < waitingTime.length; i++) {
            if (waitingTime[i] >= maxTime) {
                maxTime = waitingTime[i];
            }
        }
        return maxTime;
    }

    // method for getting the minimum waiting time
    public static int getMinTime(int[] waitingTime) {
        int minTime = waitingTime[0];

        for (int i = 1; i < waitingTime.length; i++) {
            if (waitingTime[i] > 0){
                if (waitingTime[i] < minTime) {
                    minTime = waitingTime[i];
                }
            }
        }
        return minTime;
    }

    // method for getting the average waiting time
    public static double getAvgTime(int[] waitingTime) {
        int sum = 0;
        double counter = 0;
        for (int i = 0; i < waitingTime.length; i++) {
            if (waitingTime[i] != 0){
                sum = sum + waitingTime[i];
                counter++;
            }
        }
        double avg = Math.round((sum / counter)*100.0)/100.0;
        return avg;
    }
}
