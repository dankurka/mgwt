package com.googlecode.mgwt.examples.contact.client;

import java.util.LinkedList;
import java.util.List;

import com.googlecode.mgwt.examples.contact.client.module.Contact;
import com.googlecode.mgwt.examples.contact.client.module.Group;
import com.googlecode.mgwt.examples.contact.client.module.ParseException;


public class StorageHTML5Impl implements Storage {

	private com.google.gwt.storage.client.Storage store;

	/**
	 * not a good impl, but for demo this is okay.....
	 */
	public StorageHTML5Impl() {
		store = com.google.gwt.storage.client.Storage.getLocalStorageIfSupported();
		if (store == null) {
			throw new IllegalStateException("can not run this without storage support in the browser");
		}
	}

	@Override
	public Contact getContact(String id) throws StoreException {

		String item = store.getItem("contact_" + id);
		try {
			return Contact.fromJSON(item);
		} catch (ParseException e) {
			throw new StoreException();
		}

	}

	@Override
	public List<Contact> getContacts(List<String> ids) throws StoreException {
		LinkedList<Contact> list = new LinkedList<Contact>();

		try {
			for (String id : ids) {
				list.add(Contact.fromJSON(id));
			}
			return list;
		} catch (ParseException e) {
			throw new StoreException();
		}

	}

	@Override
	public List<Group> getGroups() throws StoreException {
		String item = store.getItem("groups");
		try {
			return Group.fromJSON(item);
		} catch (Exception e) {
			throw new StoreException();
		}

	}
}
