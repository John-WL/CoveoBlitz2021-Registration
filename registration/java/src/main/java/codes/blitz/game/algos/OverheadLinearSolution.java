package codes.blitz.game.algos;

import java.util.ArrayList;
import java.util.List;

// Solution in O(N)
// It does A LOT of overhead
// For example, if the same exact item is there twice, it's not going to remember it,
// It's going to compute it twice.
public class OverheadLinearSolution implements RailTransportProblem {

    @Override
    public String execute(List<Integer> tracks, List<List<Integer>> items) {
        List<Integer> computedLengths = new ArrayList<>(items.size());
        List<Integer> trackLengthsFromOrigin = new ArrayList<>(tracks.size());
        int currentTrackLength = 0;

        for(var track: tracks) {
            currentTrackLength += track;
            trackLengthsFromOrigin.add(currentTrackLength);
        }

        for(var item: items) {
            computedLengths.add(
                    Math.abs(trackLengthsFromOrigin.get(item.get(1))
                            - trackLengthsFromOrigin.get(item.get(0)))
            );
        }

        return computedLengths.toString();
    }
}
