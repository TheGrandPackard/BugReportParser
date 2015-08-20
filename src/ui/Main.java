package ui;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application implements EventHandler<WindowEvent>{

	Stage window;
	Scene scene1;
			
	BugReportParser parser;
	Label bugReportFilename;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("Bug Report Viewer");
		window.setOnCloseRequest(this);

		bugReportFilename = new Label();
		
		TabPane tabPane = new TabPane();
		VBox.setVgrow(tabPane, Priority.ALWAYS);
		
		MenuBar menuBar = new MenuBar();
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(menuBar, tabPane);		
		scene1 = new Scene(vBox, 640, 480);	
		
                Menu menuFile = new Menu("File");
		MenuItem open = new MenuItem("Open");
		open.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Bug Report File");
				File file = fileChooser.showOpenDialog(window);
				
				if(file != null)
				{
					tabPane.getTabs().clear();
					parser = new BugReportParser(tabPane, file);
				}
				else
				{
					AlertBox.display("No File Selected", "No Bug Report File Selected. Please select a Bug Report file.");
				}
			}
		});
		menuFile.getItems().add(open);
		
		menuBar.getMenus().addAll(menuFile);
		
		window.setScene(scene1);
		window.show();		
	}

	@Override
	public void handle(WindowEvent event) {
		if(event.getEventType() == WindowEvent.WINDOW_CLOSE_REQUEST)
		{
			event.consume();
			closeProgram();
		}
	}
	
	private void closeProgram()
	{
		if(ConfirmBox.display("Bug Report Viewer", "Are you sure you want to exit?"))
			window.close();
	}
}
