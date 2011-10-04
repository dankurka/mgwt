package com.googlecode.mgwt.examples.contact.client.module;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class Group {
	private String name;

	private List<String> members;

	private final String id;

	public Group(String id) {
		this.id = id;

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

	public static Map<String, Group> fromJSON(String json) throws ParseException {
		if (json == null)
			return new HashMap<String, Group>();
		JSONValue jsonParse = JSONParser.parseStrict(json);

		JSONObject groupObject = jsonParse.isObject();
		Set<String> keySet = groupObject.keySet();

		Map<String, Group> map = new HashMap<String, Group>();

		for (String key : keySet) {
			JSONObject jsonGroup = groupObject.get(key).isObject();

			String groupId = jsonGroup.get("id").isString().stringValue();

			Group group = new Group(groupId);

			JSONString jsonName = jsonGroup.get("name").isString();
			String name = jsonName != null ? jsonName.stringValue() : "";
			group.setName(name);

			JSONValue memberValue = jsonGroup.get("members");
			if (memberValue != null) {
				JSONArray array = memberValue.isArray();
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
			}

			map.put(group.getId(), group);
		}

		return map;
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

	public String getId() {
		return id;
	}
}
