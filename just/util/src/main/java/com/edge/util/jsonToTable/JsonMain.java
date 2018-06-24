package com.edge.util.jsonToTable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import org.json.simple.parser.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author edge
 *
 */
public class JsonMain {

	JsonParser parser = new JsonParser();
	static String json = "";
	static Map<String, Object> map;
	static List<String> completeQuery = new LinkedList<String>();

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

		JsonMain jm = new JsonMain();
		jm.getProp();
		map = jm.createHashMapFromJsonString(json);
		jm.createTable(map, "","","");
		Collections.reverse(completeQuery);
		for (String query : completeQuery)
			System.out.println(query);
	}

	void getProp() throws IOException {
		json = new String(Files.readAllBytes(Paths.get("JSONExample.json")));
	}

	public Map<String, Object> createHashMapFromJsonString(String json) {

		JsonObject object = (JsonObject) parser.parse(json);
		Set<Map.Entry<String, JsonElement>> set = object.entrySet();
		Iterator<Map.Entry<String, JsonElement>> iterator = set.iterator();
		Map<String, Object> localMap = new TreeMap<String, Object>();

		while (iterator.hasNext()) {

			Map.Entry<String, JsonElement> entry = iterator.next();
			String key = entry.getKey();
			JsonElement value = entry.getValue();

			if (null != value) {
				if (!value.isJsonPrimitive()) {
					if (value.isJsonObject()) {

						localMap.put(key, createHashMapFromJsonString(value.toString()));
					} else if (value.isJsonArray() && value.toString().contains(":")) {

						List<Map<String, Object>> list = new ArrayList<>();
						JsonArray array = value.getAsJsonArray();
						if (null != array) {
							for (JsonElement element : array) {
								list.add(createHashMapFromJsonString(element.toString()));
							}
							localMap.put(key, list);
						}
					} else if (value.isJsonArray() && !value.toString().contains(":")) {
						localMap.put(key, value.getAsJsonArray());
					}
				} else {
					localMap.put(key, value.getAsString());
				}
			}
		}
		return localMap;
	}

	void createTable(Map<String, Object> map, String foreign,String foreignType,String table) {
		String query = "";
		String primary = "";String primaryType = "";
		String tempTable = "";
		for (Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue().getClass().getName() == "java.lang.String") {
				if (entry.getKey().split("-")[0].equals("00")) {
					primary = entry.getKey().split("-")[1];
					tempTable=(String) entry.getValue();
					query += "DROP TABLE  IF EXISTS " + entry.getValue() + ";\nCREATE TABLE " + entry.getValue()+ " ( ";
				} else {
					query += entry.getKey().split("-")[1] + " " + entry.getValue() + " , ";
					if(entry.getKey().split("-")[1].equals(primary))primaryType=(String) entry.getValue();
				}
			} else {
				createTable((Map<String, Object>) entry.getValue(), primary,primaryType,tempTable);
			}

		}
		if(!foreign.equals(""))
		foreign=","+foreign+" "+foreignType+ " NOT NULL, KEY "+foreign+"_idx ("+foreign+"), CONSTRAINT "+foreign+" FOREIGN KEY ( "+foreign+") REFERENCES "+table + " ("+foreign+") ON DELETE CASCADE ON UPDATE NO ACTION";
		query+=" PRIMARY KEY( "+primary+")"+foreign;		
		query += " ) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		completeQuery.add(query);
	}

}
