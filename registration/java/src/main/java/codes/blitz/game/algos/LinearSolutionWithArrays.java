package codes.blitz.game.algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// solution in O(N)
public class LinearSolutionWithArrays implements RailTransportProblem {

    @Override
    public String execute(List<Integer> tracks, List<List<Integer>> items) {
        Integer[] computedLengths = new Integer[items.size()];
        int[] trackLengthsFromOrigin = new int[tracks.size()];
        int currentTrackLength = 0;

        for(int i = 0; i < tracks.size(); i++) {
            currentTrackLength += tracks.get(i);
            trackLengthsFromOrigin[i] = currentTrackLength;
        }

        for(int i = 0; i < items.size(); i++) {
            computedLengths[i] =
                    Math.abs(trackLengthsFromOrigin[items.get(i).get(1)]
                            - trackLengthsFromOrigin[items.get(i).get(0)]);
        }

        return Arrays.toString(computedLengths);
    }
}
