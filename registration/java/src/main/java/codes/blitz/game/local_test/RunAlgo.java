package codes.blitz.game.local_test;

import codes.blitz.game.algos.RailTransportProblem;
import codes.blitz.game.algos.ConcurrentSolution;

import java.util.List;

public class RunAlgo {
    public static void main(String[] args) throws Exception {
        final var tracks = List.of(0, 3, 4, 5, 4, 17);
        final var items = List.of(
            List.of(0, 1),
            List.of(0, 3),
            List.of(1, 4),
            List.of(4, 3),
            List.of(4, 2));

        final var items2 = List.of(
            List.of(0, 4),
            List.of(3, 5));

        final ConcurrentSolution algorithm = new ConcurrentSolution();

        System.out.println(algorithm.execute(tracks, items));
        System.out.println(algorithm.execute(tracks, items2));
        System.exit(0);
    }
}
