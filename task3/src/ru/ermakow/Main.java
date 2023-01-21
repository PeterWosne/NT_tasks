package ru.ermakow;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            Object tests = parser.parse(new FileReader(args[0]));
            Object values = parser.parse(new FileReader(args[1]));

            JSONObject jsonValue =  (JSONObject) values;

            JSONArray valuesArray = (JSONArray) jsonValue.get("values");
            Map<Long, String> valuesMap = new HashMap<Long, String>();

            for(Object o: valuesArray){
                if ( o instanceof JSONObject ) {
                    valuesMap.put((Long)((JSONObject) o).get("id"), (String)((JSONObject)o).get("value"));
                }
            }

            JSONObject jsonTests =  (JSONObject) tests;
            JSONArray testsArray = (JSONArray) jsonTests.get("tests");

            testsArray = putValue(testsArray, valuesMap);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{ \"tests\"").append(": ").append(testsArray).append("}");

            String content = stringBuilder.toString();

            String path = args[0].substring(0, 9);
            FileWriter writer = new FileWriter(path + "report.json");
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray putValue(JSONArray array, Map<Long, String> valuesMap) {
        for(Object test: array){
            if ( test instanceof JSONObject) {
                if(valuesMap.get(((JSONObject) test).get("id")) == null) {
                    ((JSONObject) test).put("value", "");
                }else {
                    ((JSONObject) test).put("value", valuesMap.get(((JSONObject) test).get("id")));
                }

            }

            if(((JSONObject) test).get("values") != null) {
                JSONArray childTest = (JSONArray)((JSONObject) test).get("values");
                ((JSONObject) test).put("values", putValue(childTest, valuesMap));
            }
        }
        return array;
    }
}
