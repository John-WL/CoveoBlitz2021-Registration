package codes.blitz.game.deserializers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrackDeserializer {

    public static List<Integer> parse(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray trackJsonArray = jsonObject.getJSONArray("track");
        List<Integer> parsedTracks = new ArrayList<>(trackJsonArray.length());
        parsedTracks.add(0);

        for(int i = 0; i < trackJsonArray.length(); i++) {
            parsedTracks.add(Integer.valueOf(trackJsonArray.get(i).toString()));
        }

        return parsedTracks;
    }
}
