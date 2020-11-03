package codes.blitz.game.algos;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

public class AlgorithmInput {
    public List<Integer> track;
    public List<List<Integer>> items;

    public void updateTracksToLengths() {
        int currentTrackLength = 0;

        final var trackSize = this.track.size();
        for(int i = 0; i < trackSize; i++) {
            currentTrackLength += this.track.get(i);
            this.track.set(i, currentTrackLength);
        }
    }

    public void computeLengths(final BiConsumer<Integer, Integer> setter) {
        final var itemsSize = this.items.size();
        for(int i = 0; i < itemsSize; ++i) {
            setter.accept(i, computeLength(i));
        }
    }

    public int computeLength(final int itemIndex) {
        final var item = this.items.get(itemIndex);
        final var itemEnd = getTrackLength(this.track, item, 1);
        final var itemStart = getTrackLength(this.track, item, 0);
        return Math.abs(itemEnd - itemStart);
    }

    private static Integer getTrackLength(final List<Integer> track, final List<Integer> item, final int itemIndex) {
        final var trackIndex = item.get(itemIndex);
        return trackIndex == 0 ? 0 : track.get(trackIndex - 1);
    }
}
