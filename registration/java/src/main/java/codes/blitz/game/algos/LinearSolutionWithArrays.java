package codes.blitz.game.algos;

import java.util.Arrays;

public class LinearSolutionWithArrays implements RailTransportProblem {
    @Override
    public String execute(final AlgorithmInput input) {
        input.updateTracksToLengths();

        final var computedLengths = new int[input.items.size()];
        input.computeLengths((index, value) -> computedLengths[index] = value);
        return Arrays.toString(computedLengths);
    }
}
