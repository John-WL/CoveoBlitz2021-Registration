package codes.blitz.game.algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentSolution implements RailTransportProblem {
    private static final int MAX_THREAD_AMOUNT = 8;
    private final ExecutorCompletionService<Void> executor =
        new ExecutorCompletionService<>(Executors.newFixedThreadPool(MAX_THREAD_AMOUNT));

    @Override
    public String execute(
        final List<Integer> tracks,
        final List<List<Integer>> items
    ) throws InterruptedException {
        final var computedItemLengths = new int[items.size()];
        final var trackLengthsFromOrigin = computeTrackLengths(tracks);

        final var atomicCurrentItemIndex = new AtomicInteger(0);
        final int itemsSize = items.size();

        computeSolution(() -> {
            int currentItemIndex;

            while(true) {
                synchronized (atomicCurrentItemIndex) {
                    currentItemIndex = atomicCurrentItemIndex.getAndIncrement();
                }
                if(currentItemIndex >= itemsSize) {
                    return;
                }

                final var itemTuple = items.get(currentItemIndex);
                final int itemEnd = trackLengthsFromOrigin.get(itemTuple.get(1));
                final int itemStart = trackLengthsFromOrigin.get(itemTuple.get(0));
                computedItemLengths[currentItemIndex] = Math.abs(itemEnd - itemStart);
            }
        }, Math.min(MAX_THREAD_AMOUNT, itemsSize));

        return Arrays.toString(computedItemLengths);
    }

    private List<Integer> computeTrackLengths(final List<Integer> tracks) {
        final var trackLengthsFromOrigin = new ArrayList<Integer>(tracks.size());

        int currentTrackLength = 0;
        for(final var track: tracks) {
            currentTrackLength += track;
            trackLengthsFromOrigin.add(currentTrackLength);
        }

        return trackLengthsFromOrigin;
    }

    private void computeSolution(
        final Runnable callback,
        final int amountOfThreads
    ) throws InterruptedException {
        for(int i = 0; i < amountOfThreads; ++i) {
            executor.submit(callback, null);
        }
        executor.take();
    }
}
