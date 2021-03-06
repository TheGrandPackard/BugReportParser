package ui;

import javafx.beans.property.SimpleStringProperty;

public class LogMessage 
{
	private SimpleStringProperty  date;
	private SimpleStringProperty  severity;
	private SimpleStringProperty  process;
	private SimpleStringProperty  pid;
	private SimpleStringProperty  message;
	
	public LogMessage(String log)
	{
		if(log.startsWith("<")) // KERNEL LOG (dmesg)
		{
			this.severity = new SimpleStringProperty(getSeverityFromInteger(Integer.valueOf(log.substring(1,2))));
			this.date = new SimpleStringProperty(log.substring(log.indexOf("[")+1, log.indexOf("]")));
			this.message = new SimpleStringProperty(log.substring(log.indexOf("]")+1));

			this.process = new SimpleStringProperty("");
			this.pid = new SimpleStringProperty("");
			
		}
		else // SYSTEM LOG, EVENT LOG, RADIO LOG
		{
			this.date = new SimpleStringProperty(log.substring(0,18));
			this.pid = new SimpleStringProperty(log.substring(19,31));
			this.severity = new SimpleStringProperty(getSeverityFromString(log.substring(31,32)));
			this.process = new SimpleStringProperty(log.substring(33,log.indexOf(":",34)));
			this.message = new SimpleStringProperty(log.substring(log.indexOf(":",34)+2));
		}
	}
	
	public String toString()
	{	
		return message.get();
	}
	
	public String getDate() 
	{
		return this.date.get();
	}
	
	public String getSeverity() 
	{
		return this.severity.get();
	}
	
	public String getProcessName() 
	{
		return this.process.get();
	}
	
	public String getProcessID() 
	{
		return this.pid.get();
	}
	
	public String getMessage() 
	{
		return this.message.get();
	}
	
	String getSeverityFromInteger(Integer i)
	{
		switch(i)
		{
			case 0:
				return "Emergency";
			case 1:
				return "Alert";
			case 2:
				return "Critical";
			case 3:
				return "Error";
			case 4:
				return "Warning";
			case 5:
				return "Notice";
			case 6:
				return "Informational";
			case 7:
				return "Debugging";
			default:
				return "";
		}
	}
	
	String getSeverityFromString(String i)
	{
		switch(i)
		{
			case "F":
				return "Fatal";
			case "E":
				return "Error";
			case "W":
				return "Warning";
			case "I":
				return "Informational";
			case "D":
				return "Debugging";
			case "V":
				return "Verbose";
			default:
				return "";
		}
	}
}