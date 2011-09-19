package de.kurka.mobile.contact.client;

import java.util.List;

import de.kurka.mobile.contact.client.module.Contact;
import de.kurka.mobile.contact.client.module.Group;

public interface Storage {
	public Contact getContact(String id) throws StoreException;

	public List<Contact> getContacts(List<String> ids) throws StoreException;

	public List<Group> getGroups() throws StoreException;
}
