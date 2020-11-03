package codes.blitz.game.algos;

import java.util.ArrayList;
import java.util.List;

public class BruteForce implements RailTransportProblem {
    @Override
    public String execute(final AlgorithmInput input) {
    List<Integer> computedLengths = new ArrayList<>(input.items.size());

    for(int i = 0; i < input.items.size(); i++) {
        int lengthSum = 0;
        int smallerIndex = Math.min(input.items.get(i).get(0), input.items.get(i).get(1));
        int biggerIndex = Math.max(input.items.get(i).get(0), input.items.get(i).get(1));
        for(int j = smallerIndex+1; j <= biggerIndex; j++) {
            lengthSum += input.track.get(j);
        }
        computedLengths.add(lengthSum);
    }

    return computedLengths.toString();
    }
}
