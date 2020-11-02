package codes.blitz.game.algos;

import java.util.ArrayList;
import java.util.List;

public class BruteForce implements RailTransportProblem {
    @Override
    public String execute(List<Integer> tracks, List<List<Integer>> items) {
    List<Integer> computedLengths = new ArrayList<>(items.size());

    for(int i = 0; i < items.size(); i++) {
        int lengthSum = 0;
        int smallerIndex = Math.min(items.get(i).get(0), items.get(i).get(1));
        int biggerIndex = Math.max(items.get(i).get(0), items.get(i).get(1));
        for(int j = smallerIndex+1; j <= biggerIndex; j++) {
            lengthSum += tracks.get(j);
        }
        computedLengths.add(lengthSum);
    }

    return computedLengths.toString();
    }
}
