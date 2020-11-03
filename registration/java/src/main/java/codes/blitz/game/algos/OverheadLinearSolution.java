package codes.blitz.game.algos;

import java.util.ArrayList;

// Solution in O(N)
// It does A LOT of overhead
// For example, if the same exact item is there twice, it's not going to remember it,
// It's going to compute it twice.
public class OverheadLinearSolution implements RailTransportProblem {

    @Override
    public String execute(final AlgorithmInput input) {
        input.updateTracksToLengths();

        final var computedLengths = new ArrayList<Integer>(input.items.size());
        input.computeLengths((index, value) -> computedLengths.add(value));
        return computedLengths.toString();
    }
}
