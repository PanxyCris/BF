package ui;

import javafx.scene.control.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import rmi.RemoteHelper;


public class NameOfDocument extends Application{
       

	
	public NameOfDocument(){
		
	}

  
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		GridPane pane = new GridPane();
		
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11,12,13,14));
		pane.setHgap(5);
		pane.setVgap(5);
		

		Button button = new Button("Done");
		TextField printField = new TextField();
	    button.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
			
			   
				String name =readUser()+"_"+printField.getText()+".bf";
			    File file = new File(name);
				try {
					FileWriter rw = new FileWriter(file);
					rw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					String documentName =printField.getText()+".bf";
					RemoteHelper.getInstance().getUserService().storedocument(documentName);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				primaryStage.close();
			}
		});
		pane.add(printField,0,0);
	    pane.add(button,1,0);
		
		Scene scene = new Scene(pane,300,100);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Print the name of the document.");
		primaryStage.show();
		
	}
	public String readUser(){
		StringBuffer str=new StringBuffer();
		try{

		FileReader fileReader=new FileReader("E:\\软件工程与计算Ⅰ\\大作业\\userinformation\\store.txt");
		
		BufferedReader reader=new BufferedReader(fileReader);
		
		String line=null;
		
		while((line=reader.readLine())!=null){
				str.append(line);
			
		}
		reader.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return str.toString();
	}
	

	public static void main(String[] args){
		launch(args);
	}
}
