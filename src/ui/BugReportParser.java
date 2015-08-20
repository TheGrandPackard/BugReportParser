package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;


public class BugReportParser {

	File bugReport;
	TabPane pane;
		
	public BugReportParser(TabPane pane, File bugReport)
	{
		this.bugReport = bugReport;
		this.pane = pane;
		
		this.parse();
	}
	
	public void parse()
	{		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(bugReport));
			
			String outputBuffer = "";
			String inputBuffer = "";
			
			// Validate File
			if((inputBuffer = br.readLine()) != null)
			{				
				if(!inputBuffer.equals("========================================================"))
				{
					AlertBox.display("Invalid Bug Report", "The selected file is not a valid bug report file. Please select a new file and try again.");
					br.close();
					return;
				}
				else
					outputBuffer = inputBuffer;
			}
			
			String section = "Summary"; 
			
			// Get Sections
			while((inputBuffer = br.readLine()) != null)
			{				
				// Look for start of new section
				if(inputBuffer.startsWith("------ ") && inputBuffer.endsWith(" ------"))
				{
					//String section = this.sectionHeaders.get(this.sectionHeader);

					if(section != null)
					{
						Tab tab = new Tab(section);	
							
						if(section.contains("LOG") && !section.contains("BINDER"))
						{
							ArrayList<LogMessage> messages = new ArrayList<>();
	
							Pattern kernelLog = Pattern.compile("<\\d>\\[\\d*\\.\\d*@\\d\\] .*");
							Pattern systemLog = Pattern.compile("\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d\\.\\d\\d\\d\\s*\\d*\\s*\\d* [A-Z] .*");
							
							String[] lines = outputBuffer.split("\r\n");
							for (String line : lines) {
								
								if(	kernelLog.matcher(line).matches() ||
									systemLog.matcher(line).matches())
								{							
									messages.add(new LogMessage(line));	
								}
							}
	
							ObservableList<LogMessage> observableMessages = FXCollections.observableList(messages);
							TableView<LogMessage> table;
							
							if(section.equals("KERNEL LOG (dmesg)"))
								table = LogTable.GetDmesgTable(observableMessages);
							else
								table = LogTable.GetSyslogTable(observableMessages);
								
							tab.setContent(table);
						}
						else
						{
							tab.setContent(new TextArea(outputBuffer));						
						}
						
						pane.getTabs().add(tab);					
	
						outputBuffer = inputBuffer;
					}
					
					section = inputBuffer.substring(inputBuffer.indexOf("- ")+2, inputBuffer.indexOf(" -")); 
					
					if(section.contains("SHOW MAP") || section.contains("SMAPS OF ALL PROCESSES"))
					{
						section = null;
					}					
					
				}
				else
				{
					outputBuffer += "\r\n" + inputBuffer;
				}
			}
			
			// Close final section
			Tab tab = new Tab(section);
			tab.setContent(new TextArea(outputBuffer));
			pane.getTabs().add(tab);					

			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			AlertBox.display("Invalid Bug Report", "The selected file is not a valid bug report file. Please select a new file and try again.");
		} catch (IOException e) {
			e.printStackTrace();
			AlertBox.display("Invalid Bug Report", "The selected file is not a valid bug report file. Please select a new file and try again.");
		}
	}
}