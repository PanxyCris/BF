package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.Printer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import rmi.RemoteHelper;
import service.IOService;
import service.ExecuteService;

public class MainFrame extends Application {
          PrintWriter writer;
	    Printer reader;
	public MainFrame() {
	  
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	
		
		TextArea workingspace = new TextArea();
		TextArea input = new TextArea();
		TextArea output = new TextArea();
		
		workingspace.setLayoutY(27.0);
		workingspace.prefHeight(194.0);
		workingspace.prefWidth(463.0);
		
		input.setLayoutY(221.0);
		input.prefHeight(210.0);
		input.prefWidth(233.0);
		
		output.setLayoutX(230.0);
		output.setLayoutY(221.0);
		output.prefHeight(210.0);
		output.prefWidth(233.0);
		
		
		AnchorPane root = new AnchorPane();
		BorderPane pane = new BorderPane();
		root.getChildren().add(pane);
		
		MenuBar menuBar = new MenuBar();
		pane.setTop(menuBar);
		
		Menu menuFile = new Menu("File");
		Menu menuRun = new Menu("Run");
		Menu menuVersion = new Menu("Version");
		
		menuBar.getMenus().addAll(menuFile,menuRun,menuVersion);
		
		Menu menuItemNew = new Menu("New");
		MenuItem menuItemNewBF = new MenuItem("BF");
		menuItemNewBF.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
			      
		    	  Platform.runLater(new Runnable() {
		        	    public void run() {             
		        	        try {
		    					new NameOfDocument().start(new Stage());
		    				} catch (Exception e) {
		    					e.printStackTrace();
		    				}
		        	    }
		        	});    
		    	workingspace.setText("");
		    	input.setText("");
		}  
	});
		MenuItem menuItemNewOok = new MenuItem("Ook");
		menuItemNewOok.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				      
			    	  Platform.runLater(new Runnable() {
			        	    public void run() {             
			        	        try {
			    					new NameOfDocument1().start(new Stage());
			    				} catch (Exception e) {
			    					e.printStackTrace();
			    				}
			        	    }
			        	});    
			    	  
			}  
		});
		menuItemNew.getItems().addAll(menuItemNewBF,menuItemNewOok);
		
		MenuItem menuItemOpen = new MenuItem("Open");
		menuItemOpen.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				try {
					FileChooser fileChooser = new FileChooser();
				    FileChooser.ExtensionFilter extFilterBF = new FileChooser.ExtensionFilter("BF","*.bf");	
			        FileChooser.ExtensionFilter extFilterOok = new FileChooser.ExtensionFilter("OOK", "*.Ook"); 
			        fileChooser.getExtensionFilters().addAll(extFilterOok,extFilterBF);
			        File file = fileChooser.showOpenDialog(new Stage());  
					workingspace.setText(RemoteHelper.getInstance().getIOService().readFileList(file.getAbsolutePath()));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		
		MenuItem menuItemSave = new MenuItem("Save");
		menuItemSave.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				String code=workingspace.getText();
		    	try {
		           
					RemoteHelper.getInstance().getIOService().writeFile(readUser(),readName(),code);
					Date date = new Date();
					DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");  
			        String time = sdf.format(date);  
					MenuItem history = new MenuItem(time);
					menuVersion.getItems().add(history);
					history.setOnAction(new EventHandler<ActionEvent>(){
						@Override
						public void handle(ActionEvent event){
							workingspace.setText(code);
						}
					});
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		MenuItem menuItemExit = new MenuItem("Exit");
		menuItemExit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				Platform.exit();
			}
		});
		menuFile.getItems().addAll(menuItemNew,menuItemOpen,menuItemSave, menuItemExit);
		
		MenuItem menuItemExecute = new MenuItem("Execute");
		menuRun.getItems().add(menuItemExecute);
		menuItemExecute.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				String code=workingspace.getText();
		    	String param=input.getText();
		    	try{
		    		output.setText(RemoteHelper.getInstance().getExecuteService().execute(code, param));
		    	}catch(RemoteException e){
		    	e.printStackTrace();
		    	}
			}
		});
		
		Button image = new Button("", new ImageView("ui/resources/timg.jpg"));
		image.setPrefHeight(10.0);
		image.setPrefWidth(10.0);
		image.setLayoutX(420.0);
		image.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				Platform.runLater(new Runnable() {
		    	    public void run() {             
		    	        try {
							new LogSurface().start(new Stage());
						} catch (Exception e) {
							e.printStackTrace();
						}
		    	    }
		    	});
			}
		});
		
		
		root.getChildren().addAll(image,workingspace,input,output);
		
        Scene scene = new Scene(root, 460, 400);
   
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BF Client");
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
	public String readName(){
		StringBuffer str=new StringBuffer();
		try{

		FileReader fileReader=new FileReader("E:\\软件工程与计算Ⅰ\\大作业\\userinformation\\name.txt");
		
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
	public void getGui(String[] args){
		
		Application.launch(args);
	}
}