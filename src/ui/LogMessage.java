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
		if(log.startsWith("<")) // Kernel Log (dmesg)
		{
			this.severity = new SimpleStringProperty(getSeverityFromInteger(Integer.valueOf(log.substring(1,2))));
			this.date = new SimpleStringProperty(log.substring(log.indexOf("[")+1, log.indexOf("]")));
			this.message = new SimpleStringProperty(log.substring(log.indexOf("]")+1));

			this.process = new SimpleStringProperty("");
			this.pid = new SimpleStringProperty("");
			
		}
		else // System, Event, Radio Log
		{
			this.date = new SimpleStringProperty(log.substring(0,18));
			this.severity = new SimpleStringProperty(getSeverityFromString(log.substring(19,20)));
			this.process = new SimpleStringProperty(log.substring(21,log.indexOf('(')));
			this.pid = new SimpleStringProperty(log.substring(log.indexOf('(')+1,log.indexOf(')')));
			this.message = new SimpleStringProperty(log.substring(log.indexOf("):")+2));
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
				return "FATAL";
			case "E":
				return "ERROR";
			case "W":
				return "WARNING";
			case "I":
				return "INFO";
			case "D":
				return "DEBUG";
			case "V":
				return "VERBOSE";
			default:
				return "";
		}
	}
}