package codes.blitz.game.local_test;

import codes.blitz.game.algos.RailTransportProblem;
import codes.blitz.game.algos.concurrency.ConcurrentSolution;

import java.util.ArrayList;
import java.util.List;

public class RunAlgo {

    public static void main(String [] args) {
        List<Integer> tracks = new ArrayList<>();
        tracks.add(0);
        tracks.add(3);
        tracks.add(4);
        tracks.add(5);
        tracks.add(4);
        List<List<Integer>> items = new ArrayList<>();
        items.add(new ArrayList<>());
        items.get(0).add(0);
        items.get(0).add(1);
        items.add(new ArrayList<>());
        items.get(1).add(0);
        items.get(1).add(3);
        items.add(new ArrayList<>());
        items.get(2).add(1);
        items.get(2).add(4);
        items.add(new ArrayList<>());
        items.get(3).add(4);
        items.get(3).add(3);

        RailTransportProblem algorithm = new ConcurrentSolution();

        System.out.println(algorithm.execute(tracks, items));
    }
}
