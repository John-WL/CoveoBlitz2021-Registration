package codes.blitz.game.local_test;

import codes.blitz.game.algos.*;

import java.util.ArrayList;
import java.util.List;

public class LocalTest {
    public static void main(String[] args) throws Exception {
        final var input1 = new AlgorithmInput();
        final var input2 = new AlgorithmInput();

        input1.track = new ArrayList<>(List.of(3, 4, 5, 4, 17));
        input2.track = new ArrayList<>(input1.track);

        input1.items = List.of(
            List.of(0, 1),
            List.of(0, 3),
            List.of(1, 4),
            List.of(4, 3),
            List.of(4, 2));

        input2.items = List.of(
            List.of(0, 4),
            List.of(3, 5));

        final RailTransportProblem algorithm = new LinearSolutionWithArraysCopyElision();

        System.out.println(algorithm.execute(input1));
        System.out.println(algorithm.execute(input2));
        System.exit(0);
    }
}
