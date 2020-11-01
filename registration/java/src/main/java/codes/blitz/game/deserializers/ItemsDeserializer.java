package codes.blitz.game.deserializers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemsDeserializer {

    public static List<List<Integer>> parse(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray itemsJsonArray = jsonObject.getJSONArray("items");
        List<List<Integer>> parsedItems = new ArrayList<>(itemsJsonArray.length());

        for(int i = 0; i < itemsJsonArray.length(); i++) {
            parsedItems.add(new ArrayList<>());
            JSONArray itemJsonArray = itemsJsonArray.getJSONArray(i);
            parsedItems.get(i).add(Integer.valueOf(itemJsonArray.get(0).toString()));
            parsedItems.get(i).add(Integer.valueOf(itemJsonArray.get(1).toString()));
        }

        return parsedItems;
    }
}
