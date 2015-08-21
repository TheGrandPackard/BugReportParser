package ui;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class LogTable
{	
	public static VBox GetDmesgTable(ObservableList<LogMessage> observableMessages)
	{	
		return GetSyslogTable(observableMessages, true);
	}
	
	public static VBox GetSyslogTable(ObservableList<LogMessage> observableMessages)
	{
		return GetSyslogTable(observableMessages, false);
	}
	
	static VBox GetSyslogTable(ObservableList<LogMessage> observableMessages, Boolean isDmesg)
	{
		VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));		
        
        HBox hbox = new HBox();
		TextField filterField = new TextField();
		
		if(isDmesg)
		{
			ToggleButton s0,s1,s2,s3,s4,s5,s6,s7;
	        s0 = new ToggleButton("0");
	        s0.setOnAction((event) -> {if(filterField.getText().contains("0")) filterField.setText(filterField.getText().replace("0", "")); 
	        						  else filterField.setText(filterField.getText() + "0");});  
	        s1 = new ToggleButton("1");
	        s1.setOnAction((event) -> {if(filterField.getText().contains("1")) filterField.setText(filterField.getText().replace("1", "")); 
	        						  else filterField.setText(filterField.getText() + "1");});   
	        s2 = new ToggleButton("2");
	        s2.setOnAction((event) -> {if(filterField.getText().contains("2")) filterField.setText(filterField.getText().replace("2", "")); 
	        						  else filterField.setText(filterField.getText() + "2");});  
	        s3 = new ToggleButton("3");
	        s3.setOnAction((event) -> {if(filterField.getText().contains("3")) filterField.setText(filterField.getText().replace("3", "")); 
	        						  else filterField.setText(filterField.getText() + "3");});  
	        s4 = new ToggleButton("4");
	        s4.setOnAction((event) -> {if(filterField.getText().contains("4")) filterField.setText(filterField.getText().replace("4", "")); 
	        						  else filterField.setText(filterField.getText() + "4");});  
	        s5 = new ToggleButton("5");
	        s5.setOnAction((event) -> {if(filterField.getText().contains("5")) filterField.setText(filterField.getText().replace("5", "")); 
	        						  else filterField.setText(filterField.getText() + "5");});  
	        s6 = new ToggleButton("6");
	        s6.setOnAction((event) -> {if(filterField.getText().contains("6")) filterField.setText(filterField.getText().replace("6", "")); 
	        						  else filterField.setText(filterField.getText() + "6");});  
	        s7 = new ToggleButton("7");
	        s7.setOnAction((event) -> {if(filterField.getText().contains("7")) filterField.setText(filterField.getText().replace("7", "")); 
	        						  else filterField.setText(filterField.getText() + "7");});   
	        
	        hbox.getChildren().addAll(s0,s1,s2,s3,s4,s5,s6,s7);
		}
		else
		{
	        ToggleButton f,e,w,i,d,v;
	        f = new ToggleButton("F");
	        f.setOnAction((event) -> {if(filterField.getText().contains("F")) filterField.setText(filterField.getText().replace("F", "")); 
	        						  else filterField.setText(filterField.getText() + "F");});        
	        e = new ToggleButton("E");
	        e.setOnAction((event) -> {if(filterField.getText().contains("E")) filterField.setText(filterField.getText().replace("E", "")); 
			  else filterField.setText(filterField.getText() + "E");});
	        w = new ToggleButton("W");
	        w.setOnAction((event) -> {if(filterField.getText().contains("W")) filterField.setText(filterField.getText().replace("W", "")); 
			  else filterField.setText(filterField.getText() + "W");});
	        i = new ToggleButton("I");
	        i.setOnAction((event) -> {if(filterField.getText().contains("I")) filterField.setText(filterField.getText().replace("I", "")); 
			  else filterField.setText(filterField.getText() + "I");});
	        d = new ToggleButton("D");
	        d.setOnAction((event) -> {if(filterField.getText().contains("D")) filterField.setText(filterField.getText().replace("D", "")); 
			  else filterField.setText(filterField.getText() + "D");});
	        v = new ToggleButton("V");
	        v.setOnAction((event) -> {if(filterField.getText().contains("V")) filterField.setText(filterField.getText().replace("V", "")); 
			  else filterField.setText(filterField.getText() + "V");});
	        
	        hbox.getChildren().addAll(f,e,w,i,d,v);
		}
		
		TableView<LogMessage> table = new TableView<LogMessage>();
		VBox.setVgrow(table, Priority.ALWAYS);

		TableColumn<LogMessage,String> dateCol = null;
		TableColumn<LogMessage,String> severityCol;
		TableColumn<LogMessage,String> processCol = null;
		TableColumn<LogMessage,String> pidCol = null;
		TableColumn<LogMessage,String> messageCol;
		
		dateCol = new TableColumn<LogMessage,String>("Date");
		dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LogMessage,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<LogMessage, String> param) {
				System.out.println("Detected a change in dateCol");
				return null;
			}
		});
		
		severityCol = new TableColumn<LogMessage,String>("Severity");
		dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LogMessage,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<LogMessage, String> param) {
				System.out.println("Detected a change in dateCol");
				return null;
			}
		});
		
		
		if(!isDmesg)
		{
			processCol = new TableColumn<LogMessage,String>("Process Name");
			dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LogMessage,String>, ObservableValue<String>>() {
				
				@Override
				public ObservableValue<String> call(CellDataFeatures<LogMessage, String> param) {
					System.out.println("Detected a change in processCol");
					return null;
				}
			});
			
			pidCol = new TableColumn<LogMessage,String>("Process ID");
			dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LogMessage,String>, ObservableValue<String>>() {
				
				@Override
				public ObservableValue<String> call(CellDataFeatures<LogMessage, String> param) {
					System.out.println("Detected a change in pidCol");
					return null;
				}
			});
		}
		
		messageCol = new TableColumn<LogMessage,String>("Message");
		dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LogMessage,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<LogMessage, String> param) {
				System.out.println("Detected a change in messageCol");
				return null;
			}
		});
		
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		severityCol.setCellValueFactory(new PropertyValueFactory<>("severity"));
		if(!isDmesg)
		{
			processCol.setCellValueFactory(new PropertyValueFactory<>("processName"));
			pidCol.setCellValueFactory(new PropertyValueFactory<>("processID"));
		}
		messageCol.setCellValueFactory(new PropertyValueFactory<>("message"));
		
		if(!isDmesg)
			table.getColumns().addAll(dateCol, severityCol,processCol,pidCol,messageCol);
		else
			table.getColumns().addAll(dateCol, severityCol,messageCol);

        
        vbox.getChildren().addAll(hbox, table);
        
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
 		FilteredList<LogMessage> filteredData = new FilteredList<>(observableMessages, p -> true);
 				
 		// 2. Set the filter Predicate whenever the filter changes.
 		if(isDmesg)
 		{
 			filterField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(log -> {
					// If filter text is empty, display all log.
					
					//System.out.println("'" + newValue + "'");
					
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
	
					String filter = "";
					
					switch(log.getSeverity())
					{
						case "Emergency":
							filter = "0";
							break;
						case "Alert":
							filter = "1";
							break;
						case "Critical":
							filter = "2";
							break;
						case "Error":
							filter = "3";
							break;
						case "Warning":
							filter = "4";
							break;
						case "Notice":
							filter = "5";
							break;
						case "Informational":
							filter = "6";
							break;
						case "Debugging":
							filter = "7";
							break;
					}
					
					if (filterField.getText().contains(filter)) {
						return true; // Filter matches severity
					}
					
					return false; // Does not match.
				});
			});
 		}
 		else
 		{			
			filterField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(log -> {
					// If filter text is empty, display all log.
					
					//System.out.println("'" + newValue + "'");
					
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
	
					String filter = "";
					
					switch(log.getSeverity())
					{
						case "Fatal":
							filter = "F";
							break;
						case "Error":
							filter = "E";
							break;
						case "Warning":
							filter = "W";
							break;
						case "Informational":
							filter = "I";
							break;
						case "Debugging":
							filter = "D";
							break;
						case "Verbose":
							filter = "V";
							break;
					}
					
					if (filterField.getText().contains(filter)) {
						return true; // Filter matches severity
					}
					
					return false; // Does not match.
				});
			});
 		}
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<LogMessage> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
		
        return vbox;
	}
}