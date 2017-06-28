package ui;
import java.io.IOException;
import java.rmi.RemoteException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import rmi.RemoteHelper;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.TextField;


public class Register extends Application{
	
		
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		AnchorPane root = new AnchorPane();
        GridPane pane = new GridPane();
        root.prefHeight(400.0);
        root.prefWidth(500.0);
 
        pane.prefHeight(400.0);
        pane.prefWidth(494.0);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setMaxWidth(214.0);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(103.0);
        ColumnConstraints columnConstraints2 = new ColumnConstraints();
        columnConstraints2.setMaxWidth(191.0);
        columnConstraints2.setMinWidth(10.0);
        columnConstraints2.setPrefWidth(75.0);
        ColumnConstraints columnConstraints3 = new ColumnConstraints();
        columnConstraints3.setMaxWidth(277.0);
        columnConstraints3.setMinWidth(10.0);
        columnConstraints3.setPrefWidth(268.0);
        ColumnConstraints columnConstraints4 = new ColumnConstraints();
        columnConstraints4.setMaxWidth(108.0);
        columnConstraints4.setMinWidth(10.0);
        columnConstraints4.setPrefWidth(48.0);
        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setMaxHeight(120.0);
        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(114.0);
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints2.setMaxHeight(146.0);
        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(57.0);
        RowConstraints rowConstraints3 = new RowConstraints();
        rowConstraints3.setMaxHeight(74.0);
        rowConstraints3.setMinHeight(4.0);
        rowConstraints3.setPrefHeight(4.0);
        RowConstraints rowConstraints4 = new RowConstraints();
        rowConstraints4.setMaxHeight(138.0);
        rowConstraints4.setMinHeight(10.0);
        rowConstraints4.setPrefHeight(45.0);
        RowConstraints rowConstraints5 = new RowConstraints();
        rowConstraints5.setMaxHeight(138.0);
        rowConstraints5.setMinHeight(10.0);
        rowConstraints5.setPrefHeight(45.0);
        RowConstraints rowConstraints6 = new RowConstraints();
        rowConstraints6.setMaxHeight(178.0);
        rowConstraints6.setMinHeight(10.0);
        rowConstraints6.setPrefHeight(178.0);
      
        pane.getColumnConstraints().addAll(columnConstraints1,columnConstraints2,columnConstraints3,columnConstraints4);
        pane.getRowConstraints().addAll(rowConstraints1,rowConstraints2,rowConstraints3,rowConstraints4,rowConstraints5,rowConstraints6);
        
        Label username = new Label("User name");
        username.prefHeight(58.0);
        username.prefWidth(198.0);
        pane.add(username, 1, 1);
        
        Label passWord = new Label("Password");
        pane.add(passWord, 1, 3);
        
        Label conFirm = new Label("Confirm");
        conFirm.prefHeight(34.0);
        conFirm.prefWidth(67.0);
        pane.add(conFirm, 1, 4);
        
        TextField user_name = new TextField();
        PasswordField password = new PasswordField();
        PasswordField confirm = new PasswordField();
        
        pane.add(user_name, 2, 1);
        pane.add(password, 2, 3);
        pane.add(confirm, 2, 4);
        
        Label registersurface = new Label("RegisterSurface");
        registersurface.setLayoutX(249.0);
        registersurface.setLayoutY(57.0);
        
        Button registerButton = new Button("Register");
        registerButton.setLayoutX(184.0);
        registerButton.setLayoutY(302.0);
        
        registerButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if(user_name.getText().equals("")||password.getText().equals("")||confirm.getText().equals("")){
					Label reminder1 = new Label("You haven't finished your information.");
			    	pane.add(reminder1, 2, 5);
			    	reminder1.setText(null);
				}
				
				else if(password.getText().equals(confirm.getText())){
			    	try {
						if(RemoteHelper.getInstance().getUserService().register(user_name.getText(),password.getText())){
							primaryStage.close();
						}
						else{
							Label reminder3 = new Label("You have registered before.");
							pane.add(reminder3,2,5);
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }else{
			    	Label reminder2 = new Label("Please print the password again.");
			    	pane.add(reminder2, 2, 5);
			    	password.setText(null);
			    	confirm.setText(null);
			    	reminder2.setText(null);
			    }
				
			}

        });
        
    
        root.getChildren().addAll(pane,registerButton,registersurface);
        
        Scene scene = new Scene(root,500, 400);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LogSurface");
        primaryStage.show();
        
     
	}
	
	public static void main(String[] args){
		launch(args);
	}


	
}