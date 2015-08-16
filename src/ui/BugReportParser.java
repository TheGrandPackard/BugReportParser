package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;


public class BugReportParser {

	File bugReport;
	TabPane pane;
	
	ArrayList<String> sectionHeaders;
	int sectionHeader = 0;
	
	public BugReportParser(TabPane pane, File bugReport)
	{
		this.bugReport = bugReport;
		this.pane = pane;
		
		this.generateSectionHeaders();
		
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
			}
			
			// Get Sections
			while((inputBuffer = br.readLine()) != null)
			{			
				// Look for start of new section
				if(inputBuffer.startsWith("------ ") && inputBuffer.endsWith(" ------"))
				{
					Tab tab = new Tab(this.sectionHeaders.get(this.sectionHeader));
					tab.setContent(new TextArea(outputBuffer));
					pane.getTabs().add(tab);					

					outputBuffer = inputBuffer;
					//System.out.println("Added Tab: " + this.sectionHeaders.get(this.sectionHeader));
					this.sectionHeader++;
				}
				else
				{
					outputBuffer += "\r\n" + inputBuffer;
				}
			}
			// Close final section
			Tab tab = new Tab(this.sectionHeaders.get(this.sectionHeader));
			tab.setContent(new TextArea(outputBuffer));
			pane.getTabs().add(tab);					

			//System.out.println("Added Tab: " + this.sectionHeaders.get(this.sectionHeader));

			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			AlertBox.display("Invalid Bug Report", "The selected file is not a valid bug report file. Please select a new file and try again.");
		} catch (IOException e) {
			e.printStackTrace();
			AlertBox.display("Invalid Bug Report", "The selected file is not a valid bug report file. Please select a new file and try again.");
		}
	}
	
	private void generateSectionHeaders()
	{
		this.sectionHeaders = new ArrayList<>();

		this.sectionHeaders.add("Summary");
		this.sectionHeaders.add("Memory Info");
		this.sectionHeaders.add("CPU Info");
		this.sectionHeaders.add("Procrank");
		this.sectionHeaders.add("Virtual Memory Stats");		
		this.sectionHeaders.add("VMALLOC Info");
		this.sectionHeaders.add("SLAB Info");
		this.sectionHeaders.add("Zone Info");
		this.sectionHeaders.add("System Log");
		this.sectionHeaders.add("VM Traces Just Now");
		this.sectionHeaders.add("VM Traces at last ANR");
		this.sectionHeaders.add("Event Log");
		this.sectionHeaders.add("Radio Log");
		this.sectionHeaders.add("Network Interfaces");
		this.sectionHeaders.add("Network Routes");
		this.sectionHeaders.add("ARP Cache");
		this.sectionHeaders.add("Wifi Firmware Log");
		this.sectionHeaders.add("System Properties");
		this.sectionHeaders.add("Kernel Log (dmesg)");
		this.sectionHeaders.add("Kernel Wakelocks");
		this.sectionHeaders.add("Kernel CPUFREQ");
		this.sectionHeaders.add("VOLD Dump");
		this.sectionHeaders.add("Secure Containers");
		this.sectionHeaders.add("Processes");
		this.sectionHeaders.add("Processes and Threads");
		this.sectionHeaders.add("Librank");
		this.sectionHeaders.add("Binder Failed Transaction Logs");
		this.sectionHeaders.add("Binder Transaction Log");
		this.sectionHeaders.add("Binder Transactions");
		this.sectionHeaders.add("Binder Stats");
		this.sectionHeaders.add("Binder State");
		this.sectionHeaders.add("File Systems and Free Space");
		this.sectionHeaders.add("Package Settings");
		this.sectionHeaders.add("Package UID Errors");
		this.sectionHeaders.add("Last kmesg");
		this.sectionHeaders.add("Last Radio Log");
		this.sectionHeaders.add("Last Panic Console");
		this.sectionHeaders.add("Last Panic Threads");
		this.sectionHeaders.add("Blocked Process Wait Channels");
		this.sectionHeaders.add("Backlights");
		this.sectionHeaders.add("Dumpsys");
	}
}


//TODO: Get each section separated. Create array of sections to use as headers and determine how to know when to stop