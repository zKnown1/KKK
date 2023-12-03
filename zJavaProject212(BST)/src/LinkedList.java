class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
  
}

class LinkedList<T> {
    private Node<T> head;
    private Node<T> current;
    
    public LinkedList() {
        head = null;
    }

    public boolean isEmpty() {
    	if(head == null)
    		return true;
    	return false;
    }
    
    public Node<T> findFirst() {
        return head;
    }
    
    public boolean isLast() {
        return current != null && current.getNext() == null;    	
    }
    
    public int getSize() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
    
    public Node<T> findLast() {
        if (head == null) {
            return null;  
        }

        Node<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();  
        }

        return current;  
    }
    
    public Node<T> findNext() {
        if (current != null) {
            return current.getNext();
        }
        return null;
    }
    
    public T retrieve() {
        if (current == null) {
            return null;
        }
        return current.getData();
    }
    
    public void removeCurrent() {
        if (current != null) {
            current = current.getNext();
        }
    }
    
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public boolean search(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public T searchByName(String name) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
                Contact contact = (Contact) current.getData();
                if (contact.getContactName().equalsIgnoreCase(name)) {
                    return current.getData();
                }
            }
            current = current.getNext();
        }
        return null;
    }

    public T[] searchByNameArray(String name) {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
                Contact contact = (Contact) current.getData();
                if (contact.getContactName().equalsIgnoreCase(name)) {
                    count++;
                }
            }
            current = current.getNext();
        }

        if (count == 0) {
            return null;
        }

        T[] results = (T[]) new Object[count];
        int index = 0;

        current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
                Contact contact = (Contact) current.getData();
                if (contact.getContactName().equalsIgnoreCase(name)) {
                    results[index] = current.getData();
                    index++;
                }
            }
            current = current.getNext();
        }

        return results;
    }

    public T searchByPhoneNumber(String phoneNumber) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
                Contact contact = (Contact) current.getData();
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    return current.getData();
                }
            }
            current = current.getNext();
        }
        return null;
    }

    public T[] searchByPhoneNumberArray(String phoneNumber) {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
                Contact contact = (Contact) current.getData();
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    count++;
                }
            }
            current = current.getNext();
        }

        if (count == 0) {
            return null;
        }

        T[] results = (T[]) new Object[count];
        int index = 0;

        current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
                Contact contact = (Contact) current.getData();
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    results[index] = current.getData();
                    index++;
                }
            }
            current = current.getNext();
        }

        return results;
    }

    public T[] searchByEmailArray(String email) {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
                Contact contact = (Contact) current.getData();
                if (contact.getEmail().equalsIgnoreCase(email)) {
                    count++;
                }
            }
            current = current.getNext();
        }

        if (count == 0) {
            return null;
        }

        T[] results = (T[]) new Object[count];
        int index = 0;

        current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
                Contact contact = (Contact) current.getData();
                if (contact.getEmail().equalsIgnoreCase(email)) {
                    results[index] = current.getData();
                    index++;
                }
            }
            current = current.getNext();
        }

        return results;
    }

    public T[] searchByAddressArray(String address) {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
                Contact contact = (Contact) current.getData();
                if (contact.getAddress().equalsIgnoreCase(address)) {
                    count++;
                }
            }
            current = current.getNext();
        }

        if (count == 0) {
            return null;
        }

        T[] results = (T[]) new Object[count];
        int index = 0;

        current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
                Contact contact = (Contact) current.getData();
                if (contact.getAddress().equalsIgnoreCase(address)) {
                    results[index] = current.getData();
                    index++;
                }
            }
            current = current.getNext();
        }

        return results;
    }

    public T[] searchByBirthdayArray(String birthday) {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
                Contact contact = (Contact) current.getData();
                if (contact.getBirthday().equalsIgnoreCase(birthday)) {
                    count++;
                }
            }
            current = current.getNext();
        }

        if (count == 0) {
            return null;
        }

        T[] results = (T[]) new Object[count];
        int index = 0;

        current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
                Contact contact = (Contact) current.getData();
                if (contact.getBirthday().equalsIgnoreCase(birthday)) {
                    results[index] = current.getData();
                    index++;
                }
            }
            current = current.getNext();
        }

        return results;
    }

    public String delete(String nameToDelete) {
        if (head == null) {
            return null;
        }
        if (head.getData() instanceof Contact) {
            Contact contact = (Contact) head.getData();
            if (contact.getContactName().equalsIgnoreCase(nameToDelete)) {
                head = head.getNext();
                return "Deleted successfully!";
            }
        }

        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData() instanceof Contact) {
                Contact contact = (Contact) current.getNext().getData();
                if (contact.getContactName().equalsIgnoreCase(nameToDelete)) {
                    current.setNext(current.getNext().getNext());
                    return "Deleted Successfully!";
                }
            }
            current = current.getNext();
        }
        return null;
    }

    public T[] searchByFirstNameArray(String firstName) {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
                Contact contact = (Contact) current.getData();
                String[] nameParts = contact.getContactName().split(" ");
                String firstNameFromContact = nameParts[0];
                if (firstNameFromContact.equalsIgnoreCase(firstName)) {
                    count++;
                }
            }
            current = current.getNext();
        }
        if (count == 0) {
            return null;
        }
        Contact[] results = new Contact[count];
        int index = 0;
        current = head;
        while (current != null) {
            if (current.getData() instanceof Contact) {
            	Contact contact = (Contact) current.getData();
                String[] nameParts = contact.getContactName().split(" ");
                String firstNameFromContact = nameParts[0];
                if (firstNameFromContact.equalsIgnoreCase(firstName)) {
                    results[index] = (Contact) current.getData();
                    index++;
                }
            }
            current = current.getNext();
        }
        return (T[]) results;
    }
    
    public T get(int index) {
        Node<T> current = head;
        int currentIndex = 0;

        while (current != null) {
            if (currentIndex == index) {
                return current.getData();
            }

            current = current.getNext();
            currentIndex++;
        }

        return null;
    }
    }
