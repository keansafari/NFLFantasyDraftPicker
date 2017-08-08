/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nflfantasydraftselection;


import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Kean_Jafari
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField one;
    @FXML
    private TextField two;
    @FXML
    private TextField three;
    @FXML
    private TextField four;
    @FXML
    private TextField five;
    @FXML
    private TextField six;
    @FXML
    private TextField seven;
    @FXML
    private TextField eight;
    @FXML
    private TextField nine;
    @FXML
    private TextField ten;
    @FXML
    private TextField eleven;
    @FXML
    private TextField twelve;
    @FXML
    private TextField teamName;
    @FXML
    private Button submitButton;
    private Button startButton;
    @FXML
    private Text enterTeamNameText;
    private TextField teamNameFlash;
    
    public LinkedList<String> teamList = new LinkedList<>();
    @FXML
    private Button beginRandomization;
    
    private int index = 0;
    @FXML
    private Text teamCounter;
    private int teamCount = 0;
    @FXML
    private Text teamText;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        beginRandomization.setDisable(true);
        
    }    

    @FXML
    private void submitAction(ActionEvent event) {
        
        //Team Name empty or too short
        if(teamName.getText().length() < 3) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Team name must be at least 3 characters!\nTry Again");
            alert.showAndWait();
        }
        
        //Team name too long
        else if (teamName.getText().length() > 40) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Team name too long!\nTry Again");
            alert.showAndWait();
        }
        
        //Adds team to linked list of teams
        else {
            teamList.add(teamName.getText());
            teamName.setText("");
            teamCount++;
            teamCounter.setText(""+teamCount);
            
            //If 12
            if (teamList.size() == 12) {
                beginRandomization.setDisable(false); 
                submitButton.setDisable(true);
            }
        }
    }

    @FXML
    private void startButton(ActionEvent event) {
        enterTeamNameText.setVisible(false);
        teamName.setVisible(false);
        submitButton.setVisible(false);
        beginRandomization.setVisible(false);
        teamText.setVisible(false);
        teamCounter.setVisible(false);
        //teamNameFlash.setVisible(true);
        doStuff();
    }
    
    private void doStuff() {
       //Shuffles teams
        Collections.shuffle(teamList);
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 11; i >= 0; i--) {
                    update(i);
                    Thread.sleep(2500);
                }    
                return null;
            }
        };
        new Thread(task).start();
        
    }

    
    private void update(int i) {
        switch (i) {
            case 11: twelve.setText(teamList.pop()); break;
            case 10: eleven.setText(teamList.pop()); break;
            case 9: ten.setText(teamList.pop()); break;
            case 8: nine.setText(teamList.pop()); break;
            case 7: eight.setText(teamList.pop()); break;
            case 6: seven.setText(teamList.pop()); break;
            case 5: six.setText(teamList.pop()); break;
            case 4: five.setText(teamList.pop()); break;
            case 3: four.setText(teamList.pop()); break;
            case 2: three.setText(teamList.pop()); break;
            case 1: two.setText(teamList.pop()); break;
            case 0: one.setText(teamList.pop()); break;
        }
        Collections.shuffle(teamList);
    }
    
   
}
