import java.util.*;

public class UserInterface
{
	/**
	 * constructor for the user interface
	 */
	public UserInterface(){}
	/**
	 * takes the inputs of the user and interacts with the calendar
	 */
	public  void startUI()
	{
	      Cal c = new Cal();

		      c.setPrintMonth();
		      c.printCalendar();

					String userInput= "";
					Scanner in = new Scanner(System.in);
					while(!userInput.equals("Q"))
					{
							c.eventList(c.cal);		// show the events of the day
							System.out.println("[L]oad [V]iew by [C]reate, [G]o to [E]vent list [D]elete [Q]uit");
							userInput = in.nextLine();
							userInput = userInput.toUpperCase();
							if(userInput.equals("L"))
							{
								c.loadEvents();
							} else 
							if(userInput.equals("V"))
							{
								System.out.println("[D]ay or [M]view ?");
								userInput= in.nextLine();
								userInput = userInput.toUpperCase();
								if(userInput.equals("D"))
								{
									c.setPrintDay();
									c.printCalendar();

								} else
								if(userInput.equals("M"))
								{
									c.setPrintMonth();
									c.printCalendar();
								}
								
								do 
								{
									System.out.println("[P]revious or [N]ext or [M]ain menu ");
									userInput= in.nextLine();
									userInput = userInput.toUpperCase();
									if(userInput.equals("P"))	// previous
									{
										c.moveBackward();
										c.printCalendar();
									} else
									if(userInput.equals("N"))	// next
									{
										c.moveForward();
										c.printCalendar();
									}
								} while(!userInput.equals("M"));								
								
								
							} else 
							if(userInput.equals("C")) 
							{   
								System.out.println("Title - required -  : ");
								String userInputTitle = in.nextLine();
								
								System.out.println("Date - required - (MM/DD/YYYY) : ");
								String userInputDate = in.nextLine();

								System.out.println("Starting time - required - (HH:MM) : ");
								String userInputStarting = in.nextLine();

								System.out.println("Ending time - optional - (HH:MM) : ");
								String userInputEnding= in.nextLine();
								
								int startingTime=0;
								if (userInputStarting.contains(":"))
								{
									// extract the time in HH:MM for start time
									String[] arr = userInputStarting.split(":");
									startingTime= Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
								}
								
								int endingTime=0;
								if (userInputEnding.contains(":"))
								{
									// extract the time in HH:MM for end time
									String[] arr = userInputEnding.split(":");
									endingTime= Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
								}
								
								c.createEvent(userInputDate, startingTime, endingTime, userInputTitle);
							} else 
							if(userInput.equals("E"))
							{
								c.eventList();
							} else  
							if(userInput.equals("G"))
							{
								System.out.println("Date (MM/DD/YYYY) : ");
								String userInputDate = in.nextLine();
								c.moveToDate(userInputDate);
								
							} else  
							if(userInput.equals("D"))
							{
								System.out.println("[S]elected date or [A]ll events");
								userInput = in.nextLine();
								userInput = userInput.toUpperCase();
								if(userInput.equals("S"))
								{
									System.out.println("Date (MM/DD/YYYY) : ");
									String userInputDate = in.nextLine();
									c.deleteEventDate(userInputDate);
							
								} else 
								if(userInput.equals("A"))
								{
									c.deleteAllevents();
								}
							} else  
							{
							//System.out.println("Invalid input");
							}
					}
					in.close();					
					c.serializeEventList();
		   }
		   
	}  