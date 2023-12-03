
public class Phonebook {

	static BST<Contact> all_contacts_BST;
	static LinkedList<Event> all_events;

	public Phonebook() {
		all_events = new LinkedList<Event>();
		all_contacts_BST = new BST<Contact>();
	}

	public void add_contact(Contact d) {
		boolean name_inserted = false;
		boolean phoneExist = all_contacts_BST.checkPhoneExist(d.getPhoneNumber());
		if (phoneExist)
			System.out.println("the given contact has phone Exist before cannot add" + d.getContactName());
		else {
			name_inserted = all_contacts_BST.add(d.getContactName(), d);
			if (!name_inserted)
				System.out.println("the given contact has name Exist before cannot add" + d.getContactName());
		}
	}

	public Contact search_by_name(String name) {
		if (all_contacts_BST.empty())
			return null;

		boolean found = all_contacts_BST.findKey(name);
		if (found)
			return all_contacts_BST.retrieve();
		return null;
	}

	public LinkedList<Contact> search_by_first_name(String firstName) {
		return all_contacts_BST.searchByFirstName(firstName);
	}

	public static void print_all_contacts() {
		all_contacts_BST.inOrder(all_contacts_BST.getRoot());
	}

	public static void print_all_contacts_preorder() {
		all_contacts_BST.preOrder(all_contacts_BST.getRoot());
	}

	public static void print_Linked_List_of_all_contacts(LinkedList<Contact> L) {
		if (L.isEmpty()) {
			System.out.println("empty list");
		} else {
			L.findFirst();
			while (!(L.findLast().getNext() == null)) {
				L.retrieve().printContact();
				System.out.println("");
				L.findNext();
			}
			L.retrieve().printContact();
			System.out.println("");
		}
	}

	public static void print_Linked_List_contacts_by_name(LinkedList<Contact> L) {
		if (L.isEmpty()) {
			System.out.println("empty list");
		} else {
			L.findFirst();
			while (!L.isLast()) {
				System.out.println(L.retrieve().getContactName());
				System.out.println("");
				L.findNext();
			}
			System.out.println(L.retrieve().getContactName());
			System.out.println("");

		}
	}

	public boolean is_conflict(Event e, Contact c) {
	    LinkedList<Event> contact_events = c.contact_events;
	    if (contact_events == null || contact_events.isEmpty()) {
	        return false;
	    }

	    contact_events.findFirst();
	    while (!contact_events.isLast()) {
	        Event currentEvent = contact_events.retrieve();
	        if (currentEvent != null && e.getEvent_date().equals(currentEvent.getEvent_date())) {
	            if (isTimeConflict(e, currentEvent)) {
	                return true;
	            }
	        }
	        contact_events.findNext();
	    }

	    Event lastEvent = contact_events.retrieve();
	    if (lastEvent != null && e.getEvent_date().equals(lastEvent.getEvent_date())) {
	        if (isTimeConflict(e, lastEvent)) {
	            return true;
	        }
	    }

	    return false;
	}

	private boolean isTimeConflict(Event e1, Event e2) {
	    return (is_greater(e1.getEnd(), e2.getStart()) >= 0 && is_greater(e1.getEnd(), e2.getEnd()) <= 0)
	            || (is_greater(e1.getStart(), e2.getStart()) >= 0 && is_greater(e1.getStart(), e2.getEnd()) <= 0)
	            || (is_greater(e2.getStart(), e1.getStart()) >= 0 && is_greater(e2.getEnd(), e1.getEnd()) <= 0);
	}
	public int is_greater(String tt1, String tt2) {
		String a1[] = tt1.split(":");
		String a2[] = tt2.split(":");
		if (Integer.parseInt(a1[0]) > Integer.parseInt(a2[0]))
			return 1;
		else if (Integer.parseInt(a1[0]) < Integer.parseInt(a2[0]))
			return -1;
		else {
			if (Integer.parseInt(a1[1]) > Integer.parseInt(a2[1]))
				return 1;
			else if (Integer.parseInt(a1[1]) < Integer.parseInt(a2[1]))
				return -1;
			else
				return 0;
		}
	}

	public void schedule_event(Event e, String contact_name) {
	    String[] splitNames = contact_name.split(",\\s*");

	    for (String name : splitNames) {
	        Contact this_contact = search_by_name(name);

	        if (this_contact == null) {
	            System.out.println("Cannot schedule this event because the contact does not exist: " + name);
	        } else {
	            System.out.println("Scheduling event for contact: " + this_contact.getContactName());

	            if (!e.isEvent) {
	                if (!this_contact.contact_events.isEmpty()) {
	                    System.out.println("This appointment was scheduled with an account before");
	                }
	                return;
	            }

	            boolean is_conflict = is_conflict(e, this_contact);

	            if (!is_conflict) {
	                System.out.println("Scheduling contact: " + this_contact.getContactName() + ", Event: " + e.getEvent_title());
	                this_contact.contact_events.add(e);
	                e.contacts_with_event.add(this_contact);
	                add_Event(e);
	            } else {
	                System.out.println("There is a conflict. Event title=" + e.getEvent_title() + ", Contact=" + name);
	            }
	        }
	    }
	}

	public Event search_Event_by_title(String n) {
	    if (all_events.isEmpty()) {
	        System.out.println("No events in the list.");
	        return null;
	    }

	    all_events.findFirst();
	    while (!all_events.isLast()) {
	        Event currentEvent = all_events.retrieve();
	        if (currentEvent != null && currentEvent.getEvent_title().equals(n)) {
	            return currentEvent;
	        }
	        all_events.findNext();
	    }

	    Event lastEvent = all_events.retrieve();
	    if (lastEvent != null && lastEvent.getEvent_title().equals(n)) {
	        return lastEvent;
	    }

	    System.out.println("Event not found with title: " + n);
	    return null;
	}

	public void add_Event(Event e) {
		Event found = search_Event_by_title(e.getEvent_title());
		if (found == null) {
			all_events.add(e);
		}
	}

	public LinkedList<Event> getEvents_in_contact(String n) {
		Contact this_contact = search_by_name(n);
		if (this_contact != null)
			return this_contact.getContact_events();
		return new LinkedList<Event>();
	}

	public LinkedList<Contact> getContacts_in_Event(String n) {
		Event this_Event = search_Event_by_title(n);
		if (this_Event != null)
			return this_Event.getContacts_with_event();
		return new LinkedList<Contact>();
	}

	public void delete_contact(String n) {
		if (all_contacts_BST.empty()) {
			System.out.println("Empty List can not delete");
			return;
		}
		LinkedList<Event> L = new LinkedList<>();
		boolean found = all_contacts_BST.findKey(n);
		if (!found) {
			System.out.println("can not be deleted because it is not exist");
			return;
		}
		L = all_contacts_BST.retrieve().getContact_events();
		delete_all_events_with_contact(n, L);
		boolean deleted = all_contacts_BST.removeKey(n);
		if (deleted)
			System.out.println(n + " contact deleted");
		else
			System.out.println("not deleted");
	}

	public void delete_all_events_with_contact(String n, LinkedList<Event> L) {
		while (L != null) {
			String cur_event_title = L.retrieve().getEvent_title();
			delete_event(cur_event_title, n);
			L.removeCurrent();
		}
	}

	public void delete_event(String tit, String n) {
		System.out.println("deleting event " + tit + "with contact " + n);
		LinkedList<Contact> contacts_with_cur_event = getContacts_in_Event(tit);
		contacts_with_cur_event.findFirst();
		while (!contacts_with_cur_event.isEmpty() && !contacts_with_cur_event.isLast()) {
			if (contacts_with_cur_event.retrieve().getContactName().equals(n)) {
				contacts_with_cur_event.removeCurrent();
				break;
			}
		}
		contacts_with_cur_event.findNext();
		if (!contacts_with_cur_event.isEmpty() && contacts_with_cur_event.retrieve().getContactName().equals(n)) {
			contacts_with_cur_event.removeCurrent();
		}
		if (!contacts_with_cur_event.isEmpty())
			return;
		if (all_events.isEmpty()) {
			return;
		}
		all_events.findFirst();
		while (!all_events.isLast()) {
			if (all_events.retrieve().getEvent_title().equals(tit)) {
				all_events.removeCurrent();
				System.out.println(tit + " event deleted");
				return;
			}
			all_events.findNext();
		}
		if (all_events.retrieve().getEvent_title().equals(tit)) {
			all_events.removeCurrent();
			System.out.println(tit + " event deleted");
			return;
		} else
			System.out.println("event can not be deleted Because it is not exist");
	}

	public void listEventsAlphabetically() {
		int eventCount = all_events.getSize();
		if (eventCount == 0) {
			System.out.println("No events found.");
		} else {
			Event[] eventsArray = new Event[eventCount];
			Node<Event> current = all_events.findFirst();
			int index = 0;

			while (current != null) {
				eventsArray[index++] = current.getData();
				current = current.getNext();
			}

			// bubble sort
			for (int i = 0; i < eventCount - 1; i++) {
				for (int j = 0; j < eventCount - i - 1; j++) {
					if (eventsArray[j].compareTo(eventsArray[j + 1]) > 0) {
						Event temp = eventsArray[j];
						eventsArray[j] = eventsArray[j + 1];
						eventsArray[j + 1] = temp;
					}
				}
			}

			System.out.println("Events ordered alphabetically by event title:");
			for (Event event : eventsArray) {
				event.printEvent();
				System.out.println();
			}
		}
	}

	public void print_Event_Details(int eventSearchChoice, String eventSearchTerm) {
		switch (eventSearchChoice) {
		case 1:
			// Print events by contact name
			LinkedList<Event> eventsByContactName = getEvents_in_contact(eventSearchTerm);
			printEvents(eventsByContactName);
			break;
		case 2:
			// Print events by event title
			Event searchedEvent = search_Event_by_title(eventSearchTerm);
			if (searchedEvent != null) {
				LinkedList<Contact> contactsWithEvent = searchedEvent.getContacts_with_event();
				printContacts(contactsWithEvent);
			} else {
				System.out.println("No events found with the title: " + eventSearchTerm);
			}
			break;
		default:
			System.out.println("Invalid choice. Please enter 1 or 2.");
			break;
		}
	}

	private void printEvents(LinkedList<Event> events) {
		if (events.isEmpty()) {
			System.out.println("No events found.");
		} else {
			System.out.println("Events with the specified criteria:");
			events.findFirst();
			while (!events.isLast()) {
				events.retrieve().printEvent();
				System.out.println();
				events.findNext();
			}
			events.retrieve().printEvent();
			System.out.println();
		}
	}

	private void printContacts(LinkedList<Contact> contacts) {
		if (contacts.isEmpty()) {
			System.out.println("No contacts found for the specified event title.");
		} else {
			System.out.println("Contacts associated with the specified event title:");
			contacts.findFirst();
			while (!contacts.isLast()) {
				contacts.retrieve().printContact();
				System.out.println();
				contacts.findNext();
			}
			contacts.retrieve().printContact();
			System.out.println();
		}
	}

	public Contact searchByPhoneNumber(String phoneNumber) {
		return all_contacts_BST.searchByPhoneNumber(phoneNumber);
	}

	public Contact searchByPhoneNumberArray(String phoneNumber) {
		return all_contacts_BST.searchByPhoneNumber(phoneNumber);
	}

	public Contact searchByEmail(String email) {
		return all_contacts_BST.searchByEmail(email);
	}

	public Contact searchByAddress(String address) {
		return all_contacts_BST.searchByAddress(address);
	}

	public Contact searchByBirthday(String birthday) {
		return all_contacts_BST.searchByBirthday(birthday);
	}
}
