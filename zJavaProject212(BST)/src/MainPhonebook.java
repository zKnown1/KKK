import java.util.Scanner;
public class MainPhonebook {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Phonebook phonebook = new Phonebook();

		System.out.println("Welcome to the Phonebook!");

		int choice = 0;

		while (choice != 8) {
			displayMenu();
			System.out.print("Enter your choice: ");

			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				Contact newContact = new Contact();
				newContact.readTheContact(scanner);
				phonebook.add_contact(newContact);
				System.out.println("Contact added successfully!");
				break;

			case 2:
				searchContact(phonebook, scanner);
				break;

			case 3:
				System.out.print("Enter the contact's name to delete: ");
				String contactToDelete = scanner.nextLine();
				phonebook.delete_contact(contactToDelete);

				break;

			case 4:
				scheduleEvent(phonebook, scanner);
				break;
			case 5:
				printEventDetails(phonebook, scanner);
				break;

			case 6:
				System.out.print("Enter the first name: ");
				String firstName = scanner.nextLine();
				LinkedList<Contact> matchingContacts = phonebook.search_by_first_name(firstName);

				if (matchingContacts.isEmpty()) {
					System.out.println("No contacts found with the specified first name.");
				} else {
					System.out.println("Contacts with the first name '" + firstName + "':");

					for (int i = 0; i < matchingContacts.getSize(); i++) {
						Contact contact = matchingContacts.get(i);
						System.out.println(contact);
					}
				}
				break;

			case 7:
				phonebook.listEventsAlphabetically();
				break;

			case 8:
				System.out.println("Goodbye!");
				break;

			default:
				System.out.println("Invalid choice. Please choose a valid option.");
				break;
			}
		}
	}

	private static void displayMenu() {
		System.out.println("Please choose an option:");
		System.out.println("1. Add a contact");
		System.out.println("2. Search for a contact");
		System.out.println("3. Delete a contact");
		System.out.println("4. Schedule an event/appointment");
		System.out.println("5. Print event details");
		System.out.println("6. Print contacts by first name");
		System.out.println("7. Print all events alphabetically");
		System.out.println("8. Exit");
	}

	private static void searchContact(Phonebook phonebook, Scanner scanner) {
		System.out.println("Enter search criteria:");
		System.out.println("1. Name");
		System.out.println("2. Phone Number");
		System.out.println("3. Email Address");
		System.out.println("4. Address");
		System.out.println("5. Birthday");

		System.out.print("Enter your choice: ");
		int searchChoice = scanner.nextInt();
		scanner.nextLine();

		switch (searchChoice) {
		case 1:
			System.out.print("Enter the contact's name: ");
			String nameToSearch = scanner.nextLine();
			Contact c = phonebook.search_by_name(nameToSearch);
			if (c == null) {
				System.out.println("Contact Doesnt exist");
			} else {
				System.out.println(c);
			}
			break;
		case 2:
			System.out.print("Enter the contact's phone number: ");
			String phoneNumberToSearch = scanner.nextLine();
			Contact b = phonebook.searchByPhoneNumber(phoneNumberToSearch);
			if (b == null) {
				System.out.println("Contact Doesnt exist");
			} else {
				System.out.println(b);
			}
			break;
		case 3:
			System.out.print("Enter the contact's email address: ");
			String emailToSearch = scanner.nextLine();
			Contact d = phonebook.searchByEmail(emailToSearch);
			if (d == null) {
				System.out.println("Contact Doesnt exist");
			} else {
				System.out.println(d);
			}
			break;
		case 4:
			System.out.print("Enter the contact's address: ");
			String addressToSearch = scanner.nextLine();
			Contact e = phonebook.searchByAddress(addressToSearch);
			if (e == null) {
				System.out.println("Contact Doesnt exist");
			} else {
				System.out.println(e);
			}
			break;
		case 5:
			System.out.print("Enter the contact's birthday: ");
			String birthdayToSearch = scanner.nextLine();
			Contact f = phonebook.searchByBirthday(birthdayToSearch);
			if (f == null) {
				System.out.println("Contact Doesnt exist");
			} else {
				System.out.println(f);
			}
			break;
		default:
			System.out.println("Invalid search criteria. Please enter a valid option.");
			break;
		}
	}

	private static void scheduleEvent(Phonebook phonebook, Scanner scanner) {
		System.out.println("Enter type:");
		System.out.println("1. Event");
		System.out.println("2. Appointment");
		System.out.print("Enter your choice: ");
		int eventType = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter event title: ");
		String eventTitle = scanner.nextLine();

		System.out.print("Enter contacts name separated by a comma: ");
		String contactNames = scanner.nextLine();

		LinkedList<Contact> contactsList = new LinkedList<>();
		boolean contactNotFound = false;

		String[] splitNames = contactNames.split(",\\s*");
		for (int i = 0; i < splitNames.length; i++) {
			String name = splitNames[i];
			Contact contact = phonebook.search_by_name(name);
			if (contact != null) {
				contactsList.add(contact);
			} else {
				System.out.println("Contact not found: " + name);
				contactNotFound = true;
				break;
			}
		}

		if (contactNotFound) {
			System.out.println("Some contacts were not found. Event not scheduled.");
			return;
		}

		System.out.print("Enter event date and time (MM/DD/YYYY HH:MM): ");
		String eventDateTime = scanner.nextLine();

		System.out.print("Enter event location: ");
		String eventLocation = scanner.nextLine();

		Event newEvent;
		if (eventType == 1) {
			newEvent = new Event(eventTitle, eventDateTime, eventLocation, contactsList);
		} else if (eventType == 2) {
			newEvent = new Event(eventTitle, eventDateTime, eventLocation);
		} else {
			System.out.println("Invalid event type.");
			return;
		}
		phonebook.schedule_event(newEvent, contactNames);
		System.out.println("Event scheduled successfully!");
	}

	private static void printEventDetails(Phonebook phonebook, Scanner scanner) {
		System.out.println("Enter search criteria:");
		System.out.println("1. Contact name");
		System.out.println("2. Event title");
		System.out.print("Enter your choice: ");
		int eventSearchChoice = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter the search term: ");
		String eventSearchTerm = scanner.nextLine();

		phonebook.print_Event_Details(eventSearchChoice, eventSearchTerm);
	}
}