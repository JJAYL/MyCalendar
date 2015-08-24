import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.*;

enum MONTHS
{
	January, February, March, April, May, June, July, August, September, October, November, December;
}
enum DAYS
{
	Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday ;
}

public class Cal
{
	   ArrayList<Event>eventmonth =new ArrayList<Event>();
	   GregorianCalendar cal = new GregorianCalendar(); // capture today
	   char printMode;
	   int todayDay=cal.get(Calendar.DAY_OF_MONTH);
	   int todayMonth=cal.get(Calendar.MONTH);
	   int todayYear=cal.get(Calendar.YEAR);
	   /**
	    * Constructor for the Cal object
	    */
	   public Cal(){}
	   
	   /**
	    *changes variable to print to month
	    */
	   public void setPrintMonth()
	   {
		   printMode='M';
	   }
	   
	   /**
	    *changes variable to print to month
	    */
	   public void setPrintDay()
	   {
		   printMode='D';
	   }
	   
	   /**
	    * moves the calendar forward by one day or one month 
	    */
	   public void moveForward()
	   {
		   if(printMode=='D')
			   cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)+1);
			  else 
			   if (printMode=='M')
				   cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH));
	   }

	   	   /**
	   	    * moves the calendar backwards by one day or one month
	   	    */
		   public void moveBackward()
		   {
			   if(printMode=='D')
				   cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)-1);
			   else 
			   if (printMode=='M')
				   cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)-1, cal.get(Calendar.DAY_OF_MONTH));
		   }
		   /**
		    * converts a string input to be inputs as calendar inputs 
		    * @param date string input of the date to be converted
		    */
		   public void moveToDate(String date)
		   {
				 String[] arr = date.split("/");
				 int year = Integer.parseInt(arr[2]);
				 int month = Integer.parseInt(arr[0]);
				 int day = Integer.parseInt(arr[1]);
				 cal.set(year, month-1, day);
		   }

		   /**
		    * prints out the calendar 
		    */
		   public void printCalendar()
		   {   
			MONTHS[] arrayOfMonths = MONTHS.values();
		    DAYS[] arrayOfDays = DAYS.values();
		    
		    if(printMode=='D')
		    {
		    	
			    System.out.print(arrayOfDays[cal.get(Calendar.DAY_OF_WEEK)-1]);  // ex "Sun"
			    System.out.print(" ");
				System.out.print(arrayOfMonths[cal.get(Calendar.MONTH)]);		 // ex "March"
				System.out.print(" ");
				System.out.print(cal.get(Calendar.DAY_OF_MONTH));				 // ex "8"
				System.out.println();
				for(int i=0; i<eventmonth.size(); i++)
				{
					if(  
						(eventmonth.get(i).getDate().get(Calendar.YEAR) == cal.get(Calendar.YEAR))
					 && (eventmonth.get(i).getDate().get(Calendar.MONTH) == cal.get(Calendar.MONTH)) 
					 && (eventmonth.get(i).getDate().get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH))
					   )
					{
						Event s = eventmonth.get(i);
						int startHour = s.getStarttime() / 60;
					    int startMinute = s.getStarttime() - startHour * 60;
					    int endHour = s.getEndtime() / 60;
					    int endMinute = s.getEndtime() - startHour * 60;
				 		System.out.println(s.getDate().get(Calendar.YEAR) + "\n" + arrayOfDays[s.getDate().get(Calendar.DAY_OF_WEEK)] + ", " + arrayOfMonths[s.getDate().get(Calendar.MONTH)] + " " + s.getDate().get(Calendar.DAY_OF_MONTH) + "    " + startHour + ":" + startMinute + " - " + endHour + ":" + endMinute + " " + s.getTitle());
					}
				}
		    	
		    } else if (printMode=='M')
		    {
				System.out.print(" " + arrayOfMonths[cal.get(Calendar.MONTH)]);		 // ex "March"
			    System.out.print(" ");
				System.out.println(cal.get(Calendar.YEAR));		 // ex "2015"
				
				for(DAYS dd : arrayOfDays ) {
				    System.out.print("  " + dd.name() + " ");
				}	
				System.out.println();
				
				// obtain the weekday of the first day of month.
				int firstWeekdayOfMonth = cal.get(Calendar.DAY_OF_WEEK);
				// obtain the number of days in month.
				int numberOfMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

				// reset index of weekday
				int weekdayIndex = 0;

				// skip weekdays before the first day of month
				for (int day = 1; day < firstWeekdayOfMonth; day++) 
				{
					System.out.print("      ");
					weekdayIndex++;
				}
				

				// print the days of month in tabular format.
				for (int day = 1; day <= numberOfMonthDays; day++) 
				{
					// print day
					if((todayDay==day)&&(todayMonth==cal.get(Calendar.MONTH))&&(todayYear==cal.get(Calendar.YEAR))) System.out.printf("[%2d]", day);
					else System.out.printf("%4d", day);

					// next weekday
					weekdayIndex++;
					// if it is the last weekday
					if (weekdayIndex == 7) {
						// reset it
						weekdayIndex = 0;
						// and go to next line
						System.out.println();
					} else { // otherwise
						// print space
						System.out.print("  ");
					}
				}

				// print a final new-line.
				System.out.println();
				for(int i=0; i<eventmonth.size(); i++)
				{
					if((eventmonth.get(i).getDate().get(Calendar.YEAR) == cal.get(Calendar.YEAR))&& (eventmonth.get(i).getDate().get(Calendar.MONTH) == cal.get(Calendar.MONTH)))
					{
						Event s = eventmonth.get(i);
						int startHour = s.getStarttime() / 60;
					    int startMinute = s.getStarttime() - startHour * 60;
					    int endHour = s.getEndtime() / 60;
					    int endMinute = s.getEndtime() - startHour * 60;
				 		System.out.println(s.getDate().get(Calendar.YEAR) + "\n" + arrayOfDays[s.getDate().get(Calendar.DAY_OF_WEEK)] + ", " + arrayOfMonths[s.getDate().get(Calendar.MONTH)] + " " + s.getDate().get(Calendar.DAY_OF_MONTH) + "    " + startHour + ":" + startMinute + " - " + endHour + ":" + endMinute + " " + s.getTitle());
					}
				}
		    }
		}


		   /**
		    * create event with date as string
		    * @param date date given as string
		    * @param starttime time the event starts
		    * @param endtime time the event ends
		    * @param title title of the event
		    */
		 public void createEvent(String date, int starttime, int endtime, String title)
		 {
			 String[] arr = date.split("/");
			 int year = Integer.parseInt(arr[2]);
			 int month = Integer.parseInt(arr[0]);
			 int day = Integer.parseInt(arr[1]);
			 GregorianCalendar ctmp = new GregorianCalendar();
			 ctmp.set(year, month-1, day);
			 createEvent(ctmp, starttime, endtime, title);
		 }
		 
		 
		 /**
		  * inserts elements in the event list and keeps the list ordered
		  * @param evList list to insert the event to
		  * @param evElem event being added
		  */
		 public void insertOrdered(ArrayList <Event> evList, Event evElem) 
		 {
 	    	// convert the date and start time to a number to be used to order the list
			 long levElem = evElem.getDate().getTimeInMillis()/1000;  // in seconds
			 levElem += evElem.getStarttime() * 60; // include start time
			 
			 boolean added = false;
			 int i = 0;
			 for (; ((i < evList.size()) && (!added)) ; i++) 
			 {
			    	Event ev = evList.get(i);
			    	
			    	long ldate = ev.getDate().getTimeInMillis()/1000;
			    	ldate += ev.getStarttime() * 60;
			    	
			    	if (levElem < ldate)
			    	{
			    		evList.add(i, evElem);
			    		added = true;
			    	}
			 }
			 if(!added) evList.add(evElem);
			}	 
	   
	 /**
	  * create event with date as GregorianCalendar
	  * @param date event date 
	  * @param starttime event start time
	  * @param endtime event end time
	  * @param title event title
	  */
	 public void createEvent(GregorianCalendar date, int starttime, int endtime, String title)
	 {
		 Event ev = new Event(date, starttime, endtime, title);
		 //eventmonth.add(ev);
		 // insert and keep the arraylist ordered
		 insertOrdered(eventmonth, ev);
		 
		 
		 System.out.println(eventmonth.get(0).getTitle() + " created");
	 }
	 
	 /**
	  * event list for a specific day
	  * @param date day you want the events will list for
	  */
	 public void eventList(GregorianCalendar date)
	 {
		    DAYS[] arrayOfDays = DAYS.values();
			MONTHS[] arrayOfMonths = MONTHS.values();

			 Event cmp = new Event(date,0,0,"");
		    
			 for(int i=0; i<eventmonth.size(); i++)
		        {
				    Event s= eventmonth.get(i);
					 if(							// display only the events of this day  
							 (cmp.getDate().get(Calendar.YEAR) == s.getDate().get(Calendar.YEAR)) 
						  && (cmp.getDate().get(Calendar.MONTH) == s.getDate().get(Calendar.MONTH)) 
						  && (cmp.getDate().get(Calendar.DAY_OF_MONTH) == s.getDate().get(Calendar.DAY_OF_MONTH)) 
					   )
					 {
						    int startHour = s.getStarttime() / 60;
						    int startMinute = s.getStarttime() - startHour * 60;
						    int endt = s.getEndtime();
						    int endHour = endt / 60;
						    int endMinute = endt - startHour * 60;
						    
					 		System.out.print(s.getDate().get(Calendar.YEAR) + "\n" + arrayOfDays[s.getDate().get(Calendar.DAY_OF_WEEK)] + ", " + arrayOfMonths[s.getDate().get(Calendar.MONTH)] + " " + s.getDate().get(Calendar.DAY_OF_MONTH) + "    ");
					 		System.out.format("%02d:%02d ", startHour, startMinute);
					 		if (endt!=0)
					 		{
						 		System.out.format("- %02d:%02d ", endHour, endMinute);
					 		}
					 		System.out.println(" " + s.getTitle());
					 }
		        }
	 }
	 
	 /**
	  * event list for all events
	  */
	 public void eventList()
	 {
	    DAYS[] arrayOfDays = DAYS.values();
		MONTHS[] arrayOfMonths = MONTHS.values();

	    
		 for(int i=0; i<eventmonth.size(); i++)
	        {
			    Event s= eventmonth.get(i);
			    int startHour = s.getStarttime() / 60;
			    int startMinute = s.getStarttime() - startHour * 60;
			    int endt = s.getEndtime();
			    int endHour = endt / 60;
			    int endMinute = endt - startHour * 60;
			    
		 		System.out.print(s.getDate().get(Calendar.YEAR) + "\n" + arrayOfDays[s.getDate().get(Calendar.DAY_OF_WEEK)] + ", " + arrayOfMonths[s.getDate().get(Calendar.MONTH)] + " " + s.getDate().get(Calendar.DAY_OF_MONTH) + "    ");
		 		System.out.format("%02d:%02d ", startHour, startMinute);
		 		if (endt!=0)
		 		{
			 		System.out.format("- %02d:%02d ", endHour, endMinute);
		 		}
		 		System.out.println(" " + s.getTitle());
	        }
	}  // eventList
	 /**
	  * deleteAllevents
	  */
	 public void deleteAllevents()
	 {
		 eventmonth.clear();
	 }
	 
	 // delete event with date as string
	 /**
	  * delete event on a specific day
	  * @param date day you want to remove events
	  */
	 public void deleteEventDate(String date)
	 {
		 String[] arr = date.split("/");
		 int year = Integer.parseInt(arr[2]);
		 int month = Integer.parseInt(arr[0]);
		 int day = Integer.parseInt(arr[1]);
		 GregorianCalendar ctmp = new GregorianCalendar();
		 ctmp.set(year, month-1, day);
		 deleteEventDate(ctmp);
	 }
	 
	 // delete event with date as calendar
	 /**
	  * delete event with a date as a calendar
	  * @param dateDel date to delete events
	  */
	 public void deleteEventDate(GregorianCalendar dateDel)
	 {
		 boolean found=false;
		 String t = "";
		 
		 Event del = new Event(dateDel,0,0,"");
		 //System.out.println(del.getDate().get(Calendar.YEAR) + " " + del.getDate().get(Calendar.MONTH) + " " + del.getDate().get(Calendar.DAY_OF_MONTH) );
		 
		 for(int i=0; i<eventmonth.size(); i++)
		 {
			 
			 Event s= eventmonth.get(i);
			 //System.out.print( s.getDate().get(Calendar.YEAR) + " " + s.getDate().get(Calendar.MONTH) + " " + s.getDate().get(Calendar.DAY_OF_MONTH) );
			 if(  
					 (del.getDate().get(Calendar.YEAR) == s.getDate().get(Calendar.YEAR)) 
				  && (del.getDate().get(Calendar.MONTH) == s.getDate().get(Calendar.MONTH)) 
				  && (del.getDate().get(Calendar.DAY_OF_MONTH) == s.getDate().get(Calendar.DAY_OF_MONTH)) 
			   )
			 {
				 eventmonth.remove(i);
				 found=true;
				 t=s.getTitle();
			 }
		 }
		 
		 System.out.println(  t + " " + (found?" Deleted":" Not found"));
	 }


	 /**
	  * saves event object arraylist
	  */
	 public void serializeEventList()
	 {
		 try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("eventlist.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(eventmonth);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in eventlist.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	 }
	 /**
	  * loads a saved event object arraylist
	  */
	 public void loadEvents()
	 {
		 try
	      {
	         FileInputStream fileIn = new FileInputStream("eventlist.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         eventmonth = (ArrayList <Event>) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
		         System.out.println("This is the first run");
	         //i.printStackTrace();
	         //return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("This is the first run");
	         // c.printStackTrace();
	         //return;
	      }
	      //System.out.println("Deserialized Calendar...");
	 }

}