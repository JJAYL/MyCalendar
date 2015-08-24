import java.util.GregorianCalendar;
import java.io.*;

public class Event implements Serializable
{

	private GregorianCalendar date = new GregorianCalendar();
	private int starttime;
	private int endtime;
	private String title;
	
	/**
	 * returns the GregorianCalendar date of the event
	 * @return date of the event
	 */
	public GregorianCalendar getDate()
	{
		return date;
	}
	
	/**
	 * changes the GregorianCalendar date of the event
	 * @param date the date you want to change the event to
	 */
	public void setDate(GregorianCalendar date)
	{
		this.date = date;
	}
	
	/**
	 * return the time the event starts
	 * @return the time the event starts
	 */
	public int getStarttime() 
	{
		return starttime;
	}
	
	/**
	 * changes the startime of an event
	 * @param starttime time you want the event to change to
	 */
	public void setStarttime(int starttime) 
	{
		this.starttime = starttime;
	}
	
	/**
	 * returns the time the event ends
	 * @return the time the event ends
	 */
	public int getEndtime() 
	{
		return endtime;
	}
	
	/**
	 * changes the end time of an event
	 * @param endtime time you want to change to
	 */
	public void setEndtime(int endtime) 
	{
		this.endtime = endtime;
	}
	
	/**
	 * returns the name of the event
	 * @return the name of the event
	 */
	public String getTitle() 
	{
		return title;
	}
	
	/**
	 * changes the name of the event to a new name
	 * @param title new name that it will be changed to
	 */
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	/**
	 * constructor of event
	 * @param date date that the event will be
	 * @param starttime time the event will start
	 * @param endtime time the event will finish
	 * @param title name that it will have
	 */
	public Event(GregorianCalendar date, int starttime, int endtime, String title)
	{
		this.date = date;
		this.starttime = starttime;
		this.endtime = endtime;
		this.title = title;
	}
} 