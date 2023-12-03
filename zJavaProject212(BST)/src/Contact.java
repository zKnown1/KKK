import java.util.Scanner;

class Contact implements Comparable<Contact> {
	private String contactName;
	private String phoneNumber;
	private String email;
	private String address;
	private String birthday;
	private String notes;
	public LinkedList<Event> contact_events;

	public LinkedList<Event> getContact_events() {
		return contact_events;
	}

	public void setContact_events(LinkedList<Event> contact_events) {
		this.contact_events = contact_events;
	}

	public Contact() {
        this.contact_events = new LinkedList<>();
	}

	public Contact(String contactName, String phoneNumber, String email, String address, String birthday, String notes) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.notes = notes;
        this.contact_events = new LinkedList<>();
    }

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int compareTo(Contact other) {
		return this.contactName.compareTo(other.getContactName());
	}

	public void readTheContact(Scanner scanner) {
		System.out.println("Enter the contact's name: ");
		setContactName(scanner.nextLine());

		System.out.println("Enter the contact's phone number: ");
		String inputPhoneNumber = scanner.nextLine();
		setPhoneNumber(inputPhoneNumber);

		System.out.println("Enter the contact's email: ");
		setEmail(scanner.nextLine());

		System.out.println("Enter the contact's address: ");
		setAddress(scanner.nextLine());

		System.out.println("Enter the contact's birthday: ");
		setBirthday(scanner.nextLine());

		System.out.println("Enter notes for the contact: ");
		setNotes(scanner.nextLine());
	}

	public void printContact() {
		System.out.println("Name: " + getContactName());
		System.out.println("Phone number: " + getPhoneNumber());
		System.out.println("Email: " + getEmail());
		System.out.println("Address: " + getAddress());
		System.out.println("Birthday date: " + getBirthday());
		System.out.println("Notes: " + getNotes());
	}

	public String toString() {
		return "Contact [contactName=" + contactName + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", address=" + address + ", birthday=" + birthday + ", notes=" + notes + "]";
	}
}
