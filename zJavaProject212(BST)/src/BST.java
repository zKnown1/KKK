class BSTNode<T> {

	public String key;
	public T data;
	public BSTNode<T> left, right;

	public BSTNode(String k, T data) {
		this.key = k;
		this.data = data;
		left = right = null;

	}

	public BSTNode(String k, T data, BSTNode<T> l, BSTNode<T> r) {
		this.key = k;
		this.data = data;
		this.left = l;
		this.right = r;
	}
}

public class BST<T> {

	private BSTNode<T> root, current;

	public BSTNode<T> getRoot() {
		return root;
	}

	public BST() {
		current = root = null;
	}

	public void clear() {
		current = root = null;

	}

	public boolean empty() {
		return root == null;
	}

	public boolean full() {
		return false;
	}

	public T retrieve() {
		return current.data;
	}

	public boolean findKey(String k) {
		if (empty())
			return false;
		BSTNode<T> p = root;
		BSTNode<T> q = root;
		while (p != null) {
			q = p;
			if (k.compareToIgnoreCase(p.key) == 0) {
				current = p;
				return true;
			} else if (k.compareToIgnoreCase(p.key) < 0) {
				p = p.left;
			} else if (k.compareToIgnoreCase(p.key) > 0) {
				p = p.right;
			}
		}
		current = q;
		return false;

	}

	public boolean add(String k, T val) {
		if (root == null) {
			current = root = new BSTNode<>(k, val);
			return true;
		}

		BSTNode<T> p = current;
		if (findKey(k)) {
			current = p;
			return false;
		}

		BSTNode<T> tmp = new BSTNode<>(k, val);
		if (k.compareToIgnoreCase(p.key) < 0) {
			p.left = tmp;
		} else {
			p.right = tmp;
		}
		current = tmp;
		return true;
	}

	public boolean removeKey(String k) {

		String k1 = k;
		BSTNode<T> p = root;
		BSTNode<T> q = null;
		while (p != null) {

			if (k1.compareToIgnoreCase(p.key) < 0) {
				q = p;
				p = p.left;
			} else if (k1.compareToIgnoreCase(p.key) > 0) {
				q = p;
				p = p.right;

			} else {

				if ((p.left != null) && (p.right != null)) {
					BSTNode<T> min = p.right;
					q = p;
					while (min.left != null) {
						q = min;
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					k1 = min.key;
					p = min;

				}

				if (p.left != null) {
					p = p.left;
				} else {
					p = p.right;
				}

				if (q == null) {
					root = p;
				} else {
					if (k1.compareToIgnoreCase(q.key) < 0) {
						q.left = p;
					} else {
						q.right = p;
					}
				}
				current = root;
				return true;
			}
		}
		return false;
	}

	public boolean checkPhoneExist(String phone) {
		if (root == null) {
			return false;
		}
		return checkPhoneInOrder((BSTNode<Contact>) root, phone);
	}

	private boolean checkPhoneInOrder(BSTNode<Contact> p, String phone) {
		if (p != null) {
			int compareResult = phone.compareToIgnoreCase(p.key);

			if (compareResult == 0) {
				return true; // Phone number exists
			} else if (compareResult < 0) {
				return checkPhoneInOrder(p.left, phone);
			} else {
				return checkPhoneInOrder(p.right, phone);
			}
		}

		return false; // Phone number not found
	}

	public LinkedList<Contact> searchByFirstName(String name) {

		LinkedList<Contact> result = new LinkedList<Contact>();
		if (root == null)
			return result;
		recSearchByFirstName(root, result, name);
		return result;
	}

	public void recSearchByFirstName(BSTNode<T> p, LinkedList<Contact> result, String name) {
		if (p == null)
			return;

		recSearchByFirstName(p.left, result, name);

		String currentFullName = p.key;

		String[] nameParts = currentFullName.split("\\s+");

		if (nameParts.length > 0) {
			String firstName = nameParts[0];

			if (firstName.equals(name)) {
				result.add((Contact) p.data);
			}
		}

		recSearchByFirstName(p.right, result, name);
	}

	public Contact searchByPhoneNumber(String phoneNumber) {
		if (empty()) {
			return null;
		}
		return searchByPhoneNumberRecursive((BSTNode<Contact>) root, phoneNumber);
	}

	private Contact searchByPhoneNumberRecursive(BSTNode<Contact> node, String phoneNumber) {
		if (node == null) {
			return null;
		}

		int compareResult = phoneNumber.compareToIgnoreCase(node.key);

		if (compareResult == 0) {
			return node.data;
		} else if (compareResult < 0) {
			return searchByPhoneNumberRecursive(node.left, phoneNumber);
		} else {
			return searchByPhoneNumberRecursive(node.right, phoneNumber);
		}
	}

	public Contact searchByEmail(String email) {
		if (empty()) {
			return null;
		}
		return searchByEmailRecursive((BSTNode<Contact>) root, email);
	}

	private Contact searchByEmailRecursive(BSTNode<Contact> node, String email) {
		if (node == null) {
			return null;
		}

		int compareResult = email.compareToIgnoreCase(node.data.getEmail());

		if (compareResult == 0) {
			return node.data;
		} else if (compareResult < 0) {
			return searchByEmailRecursive(node.left, email);
		} else {
			return searchByEmailRecursive(node.right, email);
		}
	}

	// Search by Address
	public Contact searchByAddress(String address) {
		if (empty()) {
			return null;
		}
		return searchByAddressRecursive((BSTNode<Contact>) root, address);
	}

	private Contact searchByAddressRecursive(BSTNode<Contact> node, String address) {
		if (node == null) {
			return null;
		}

		int compareResult = address.compareToIgnoreCase(node.data.getAddress());

		if (compareResult == 0) {
			return node.data;
		} else if (compareResult < 0) {
			return searchByAddressRecursive(node.left, address);
		} else {
			return searchByAddressRecursive(node.right, address);
		}
	}

	// Search by Birthday
	public Contact searchByBirthday(String birthday) {
		if (empty()) {
			return null;
		}
		return searchByBirthdayRecursive((BSTNode<Contact>) root, birthday);
	}

	private Contact searchByBirthdayRecursive(BSTNode<Contact> node, String birthday) {
		if (node == null) {
			return null;
		}

		int compareResult = birthday.compareToIgnoreCase(node.data.getBirthday());

		if (compareResult == 0) {
			return node.data;
		} else if (compareResult < 0) {
			return searchByBirthdayRecursive(node.left, birthday);
		} else {
			return searchByBirthdayRecursive(node.right, birthday);
		}
	}

	public void inOrder(BSTNode<T> root) {
		if (root != null) {
			inOrder(root.left);
			System.out.println((Contact) root.data);
			inOrder(root.right);
		}
	}

	public void preOrder(BSTNode<T> root) {
		if (root != null) {
			System.out.println((Contact) root.data);
			preOrder(root.left);
			preOrder(root.right);
		}
	}

}