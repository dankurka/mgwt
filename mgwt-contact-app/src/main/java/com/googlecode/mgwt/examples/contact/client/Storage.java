package com.googlecode.mgwt.examples.contact.client;

import java.util.List;

import com.googlecode.mgwt.examples.contact.client.module.Contact;
import com.googlecode.mgwt.examples.contact.client.module.Group;


public interface Storage {
	public Contact getContact(String id) throws StoreException;

	public List<Contact> getContacts(List<String> ids) throws StoreException;

	public List<Group> getGroups() throws StoreException;
}
