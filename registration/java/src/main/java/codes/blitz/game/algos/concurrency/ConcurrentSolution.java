package codes.blitz.game.algos.concurrency;

import codes.blitz.game.algos.RailTransportProblem;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentSolution implements RailTransportProblem {
    @Override
    public List<Integer> execute(List<Integer> tracks, List<List<Integer>> items) {
        List<Integer> computedLengths = new ArrayList<>(items.size());
        List<Integer> trackLengthsFromOrigin = new ArrayList<>(tracks.size());
        int currentTrackLength = 0;

        for(var track: tracks) {
            currentTrackLength += track;
            trackLengthsFromOrigin.add(currentTrackLength);
        }

        int itemsIndex0 = items.size()/4;
        int itemsIndex1 = items.size()/2;
        int itemsIndex2 = (items.size()*3)/4;
        int itemsIndex3 = items.size();

        ThreadedLinear threadedLinearSolver0 = new ThreadedLinear(trackLengthsFromOrigin, items, 0, itemsIndex0);
        ThreadedLinear threadedLinearSolver1 = new ThreadedLinear(trackLengthsFromOrigin, items, itemsIndex0, itemsIndex1);
        ThreadedLinear threadedLinearSolver2 = new ThreadedLinear(trackLengthsFromOrigin, items, itemsIndex1, itemsIndex2);
        ThreadedLinear threadedLinearSolver3 = new ThreadedLinear(trackLengthsFromOrigin, items, itemsIndex2, itemsIndex3);

        Thread thread0 = new Thread(threadedLinearSolver0);
        Thread thread1 = new Thread(threadedLinearSolver1);
        Thread thread2 = new Thread(threadedLinearSolver2);
        Thread thread3 = new Thread(threadedLinearSolver3);

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();

        while(thread0.isAlive()
                || thread1.isAlive()
                || thread2.isAlive()
                || thread3.isAlive()) {
        }

        computedLengths.addAll(threadedLinearSolver0.getResult());
        computedLengths.addAll(threadedLinearSolver1.getResult());
        computedLengths.addAll(threadedLinearSolver2.getResult());
        computedLengths.addAll(threadedLinearSolver3.getResult());


        return computedLengths;
    }
}
