package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

	static Boolean answer;
	
	public static Boolean display(String title, String message)
	{		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label("Do you want to do the needful?");
		label.setText(message);
		
		Button yesButton = new Button("Yes");
		yesButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				answer = true;
				window.close();				
			}
		});
		
		Button noButton = new Button("No");
		noButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				answer = false;
				window.close();				
			}
		});
		
		HBox buttonLayout = new HBox(10);
		buttonLayout.getChildren().addAll(yesButton, noButton);
		buttonLayout.setAlignment(Pos.CENTER);
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label,buttonLayout);
		layout.setAlignment(Pos.CENTER);
				
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}

}
