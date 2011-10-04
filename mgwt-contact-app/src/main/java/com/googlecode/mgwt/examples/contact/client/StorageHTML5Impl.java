package com.googlecode.mgwt.examples.contact.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
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

	private Map<String, Group> getGroupMap() throws ParseException {
		String item = store.getItem("groups");
		return Group.fromJSON(item);
	}

	@Override
	public List<Group> getGroups() throws StoreException {

		try {
			Map<String, Group> map = getGroupMap();

			Collection<Group> groups = map.values();

			ArrayList<Group> list = new ArrayList<Group>(groups);

			Collections.sort(list, new Comparator<Group>() {

				@Override
				public int compare(Group o1, Group o2) {
					return o1.getName().compareToIgnoreCase(o2.getName());
				}
			});

			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new StoreException();
		}

	}

	@Override
	public Group addGroup(String groupName) throws StoreException {
		List<Group> groups = getGroups();

		Group group = new Group(getPseudoGUUID());
		group.setName(groupName);

		groups.add(group);
		storeGroups(groups);

		return group;
	}

	private void storeGroups(Collection<Group> list) {
		JSONObject object = new JSONObject();
		for (Group group : list) {
			JSONObject groupObject = new JSONObject();
			groupObject.put("id", new JSONString(group.getId()));
			groupObject.put("name", group.getName() != null ? new JSONString(group.getName()) : new JSONString(""));
			object.put(group.getId(), groupObject);
		}

		store.setItem("groups", object.toString());
	}

	private static final char[] CHAR_ARRAY = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	public static String getPseudoGUUID() {
		char[] buffer = new char[36];

		for (int i = 0; i < 36; i++) {

			int random = (int) (Math.random() * 16);
			int index = 0;
			if (i == 19) {
				index = (random & 0x3) | 0x8;
			} else {
				index = random & 0xf;
			}
			buffer[i] = CHAR_ARRAY[index];

		}

		buffer[8] = buffer[13] = buffer[18] = buffer[23] = '-';
		buffer[14] = '4';

		return new String(buffer);
	}

	@Override
	public Group getGroup(String groupId) throws StoreException {
		List<Group> groups = getGroups();
		for (Group group : groups) {
			if (groupId.equals(group.getId())) {
				return group;
			}
		}

		throw new StoreException();
	}

	@Override
	public void updateGroup(Group group) throws StoreException {
		try {

			Map<String, Group> map = getGroupMap();
			map.put(group.getId(), group);
			storeGroups(map.values());

		} catch (ParseException e) {

			e.printStackTrace();
			throw new StoreException();
		}
	}
}
