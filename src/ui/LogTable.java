package ui;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class LogTable
{	
	public static TableView<LogMessage> GetDmesgTable(ObservableList<LogMessage> observableMessages)
	{	
		return GetSyslogTable(observableMessages, true);
	}
	
	public static TableView<LogMessage> GetSyslogTable(ObservableList<LogMessage> observableMessages)
	{
		return GetSyslogTable(observableMessages, false);
	}
	
	static TableView<LogMessage> GetSyslogTable(ObservableList<LogMessage> observableMessages, Boolean isDmesg)
	{
		TableView<LogMessage> table = new TableView<LogMessage>(observableMessages);

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
		
		return table;
	}
}