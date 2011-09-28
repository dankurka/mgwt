package com.googlecode.mgwt.examples.contact.client.module;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class Group {
	private String name;

	private List<String> members;

	public Group() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMembers() {
		if (members == null) {
			this.members = new LinkedList<String>();
		}
		return members;
	}

	public void setMembers(List<String> members) {
		if (members == null) {
			throw new IllegalArgumentException("members can not be null");
		}
		this.members = members;
	}

	public static List<Group> fromJSON(String json) throws ParseException {
		JSONValue jsonParse = JSONParser.parseStrict(json);

		JSONArray grouparray = jsonParse.isArray();

		LinkedList<Group> list = new LinkedList<Group>();

		for (int i = 0; i < grouparray.size(); i++) {
			JSONObject jsonGroup = grouparray.get(i).isObject();

			Group group = new Group();

			JSONString jsonName = jsonGroup.get("name").isString();
			String name = jsonName != null ? jsonName.stringValue() : "";
			group.setName(name);

			JSONArray array = jsonGroup.get("members").isArray();
			if (array != null) {
				for (int j = 0; j < array.size(); j++) {
					JSONString jsonString = array.get(j).isString();
					if (jsonString != null) {
						String id = jsonString.stringValue();
						if (id != null) {
							group.getMembers().add(id);
						}
					}

				}
			}
			list.add(group);
		}

		return list;
	}

	public String toJSON() {
		JSONObject object = new JSONObject();

		object.put("name", new JSONString(this.name != null ? this.name : ""));

		JSONArray array = new JSONArray();
		int count = 0;
		for (String id : this.getMembers()) {
			array.set(count, new JSONString(id));
			count++;
		}

		object.put("members", array);

		return object.toString();
	}
}
