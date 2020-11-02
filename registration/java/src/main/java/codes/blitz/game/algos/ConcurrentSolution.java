package codes.blitz.game.algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentSolution implements RailTransportProblem {
    private static final int THREAD_AMOUNT = 8;
    private final ExecutorCompletionService<Void> executor =
        new ExecutorCompletionService<>(Executors.newFixedThreadPool(THREAD_AMOUNT));

    @Override
    public String execute(List<Integer> tracks, List<List<Integer>> items) throws InterruptedException {
        final var computedItemLengths = new int[items.size()];
        final var trackLengthsFromOrigin = computeTrackLengths(tracks);

        final var atomicCurrentItemIndex = new AtomicInteger(0);
        final int itemsSize = items.size();

        fillThreadPool(() -> {
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
        }, Math.min(THREAD_AMOUNT, itemsSize));

        return Arrays.toString(computedItemLengths);
    }

    private List<Integer> computeTrackLengths(List<Integer> tracks) {
        var trackLengthsFromOrigin = new ArrayList<Integer>(tracks.size());

        int currentTrackLength = 0;
        for(var track: tracks) {
            currentTrackLength += track;
            trackLengthsFromOrigin.add(currentTrackLength);
        }

        return trackLengthsFromOrigin;
    }

    private void fillThreadPool(
        final Runnable threadedCallback,
        final int taskAmount
    ) throws InterruptedException {
        for(int i = 0; i < taskAmount; ++i) {
            executor.submit(threadedCallback, null);
        }

        executor.take();
    }
}
