
public class Event implements Comparable<Event> {

	private String Event_title;
	private String Event_date;
	private String Event_time;
	private String Event_location;
	private String start, end;
	private String st, ed;
	public boolean isEvent = true;
	public LinkedList<Contact> contacts_with_event = new LinkedList<Contact>();

	public Event(String Event_title, String Event_date, String Event_time, String Event_location) {
		this.Event_date = Event_date;
		this.Event_location = Event_location;
		this.Event_time = Event_time;
		this.Event_title = Event_title;
	}


	public Event(String eventTitle, String eventDateTime, String eventLocation) {
		this.Event_location = eventLocation;
		this.Event_time = eventDateTime;
		this.Event_title = eventTitle;
	}

	public Event(String eventTitle, String eventDateTime, String eventLocation, LinkedList<Contact> contactsList) {
		this.Event_date = eventDateTime;
		this.Event_location = eventLocation;
		this.contacts_with_event = contactsList;
		this.Event_title = eventTitle;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public String getEvent_title() {
		return Event_title;
	}

	public String getEvent_date() {
		return Event_date;
	}

	public String getEvent_location() {
		return Event_location;
	}

	public String getEvent_time() {
		return Event_time;
	}

	public void setEvent_title(String Event_title) {
		this.Event_title = Event_title;
	}

	public void setEvent_time(String Event_time) {
		this.Event_time = Event_time;
	}

	public void setEvent_date(String Event_date) {
		this.Event_date = Event_date;
	}

	public void setEvent_location(String Event_location) {
		this.Event_location = Event_location;
	}

	public String printEvent() {
		return "Event{" + "Event_Title=" + Event_title + "Event_Date=" + Event_date + "Event_Time=" + Event_time
				+ "Event_Location=" + Event_location;
	}

	public void addContact(Contact contact) {
		contacts_with_event.add(contact);
	}

	public LinkedList<Contact> getContacts_with_event() {
		return contacts_with_event;
	}

	public int compareTo(Event o) {
		return this.Event_title.compareTo(o.getEvent_title());

	}

}
