package codes.blitz.game.algos.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ThreadedLinear implements Runnable{

    List<Integer> computedLengths;
    List<Integer> trackLengthsFromOrigin;
    List<List<Integer>> items;
    int minItemsIndex;
    int maxItemsIndex;

    public ThreadedLinear(List<Integer> trackLengthsFromOrigin, List<List<Integer>> items, int minItemsIndex, int maxItemsIndex) {
        this.computedLengths = new ArrayList<>();
        this.trackLengthsFromOrigin = trackLengthsFromOrigin;
        this.items = items;
        this.minItemsIndex = minItemsIndex;
        this.maxItemsIndex = maxItemsIndex;
    }

    @Override
    public void run() {
        for(int i = minItemsIndex; i < maxItemsIndex; i++) {
            computedLengths.add(
                    Math.abs(trackLengthsFromOrigin.get(items.get(i).get(1))
                            - trackLengthsFromOrigin.get(items.get(i).get(0)))
            );
        }
    }

    public List<Integer> getResult() {
        return computedLengths;
    }
}
