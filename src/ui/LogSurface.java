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


public class LogSurface extends Application{
	

	private String information;
	
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
        rowConstraints3.setPrefHeight(20.0);
        RowConstraints rowConstraints4 = new RowConstraints();
        rowConstraints4.setMaxHeight(138.0);
        rowConstraints4.setMinHeight(10.0);
        rowConstraints4.setPrefHeight(47.0);
        RowConstraints rowConstraints5 = new RowConstraints();
        rowConstraints5.setMaxHeight(160.0);
        rowConstraints5.setMinHeight(10.0);
        rowConstraints5.setPrefHeight(160.0);
      
        pane.getColumnConstraints().addAll(columnConstraints1,columnConstraints2,columnConstraints3,columnConstraints4);
        pane.getRowConstraints().addAll(rowConstraints1,rowConstraints2,rowConstraints3,rowConstraints4,rowConstraints5);
        
        Label username = new Label("User name");
        username.prefHeight(58.0);
        username.prefWidth(198.0);
        pane.add(username, 1, 1);
        
        Label passWord = new Label("Password");
        pane.add(passWord, 1, 3);
        
        TextField user_name = new TextField();
        PasswordField password = new PasswordField();
        
        pane.add(user_name, 2, 1);
        pane.add(password, 2, 3);
        
        Label logsurface = new Label("LogSurface");
        logsurface.setLayoutX(249.0);
        logsurface.setLayoutY(57.0);
        
        Button loginButton = new Button("login");
        loginButton.setLayoutX(184.0);
        loginButton.setLayoutY(302.0);
        loginButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				try {
        			Boolean isSuccussful = RemoteHelper.getInstance().getUserService().login(user_name.getText(), password.getText());
        			if(isSuccussful){
        				String information = user_name.getText()+"_"+password.getText();
        				RemoteHelper.getInstance().getUserService().store(information);
        				primaryStage.close();
       
        			}
        	
        			else
        				password.setText(null);
        			
        		} catch (RemoteException ex) {
        			ex.printStackTrace();
        		}
				
			}

        });
        
        Button registerButton = new Button("Register");
        registerButton.setLayoutX(296.0);
        registerButton.setLayoutY(302.0);
        registerButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				  Platform.runLater(new Runnable() {
			      	    public void run() {             
			      	        try {
			  					new Register().start(new Stage());
			  				} catch (Exception e) {
			  					e.printStackTrace();
			  				}
			      	    }
			      	});   
			  }
			});
        root.getChildren().addAll(pane,loginButton,registerButton,logsurface);
        
        Scene scene = new Scene(root,500, 400);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LogSurface");
        primaryStage.show();
        
        
	}

	public String getUserName(){
		return information;
	}
	
	public static void main(String[] args){
		launch(args);
	}

}