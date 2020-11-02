package codes.blitz.game;

import java.util.List;

import codes.blitz.game.algos.RailTransportProblem;
import codes.blitz.game.algos.ConcurrentSolution;
import codes.blitz.game.deserializers.ItemsDeserializer;
import codes.blitz.game.deserializers.TrackDeserializer;
import io.javalin.Javalin;
import picocli.CommandLine;
import picocli.CommandLine.Option;

public class Application {
    public static class Args {
        @Option(names = {"-p", "--port"},
                description = "set port number (default: 27178)",
                defaultValue = "27178")
        public int port;
    }

    public static void main(String[] args) {
        Args arguments = new Args();
        new CommandLine(arguments).parseArgs(args);

        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.requestCacheSize = 1000 * 1000 * 2L;
        }).start(arguments.port);

        RailTransportProblem algorithm = new ConcurrentSolution();

        app.post("/microchallenge", ctx -> {
            String jsonStringInput = ctx.body();
            List<Integer> tracks = TrackDeserializer.parse(jsonStringInput);
            List<List<Integer>> items = ItemsDeserializer.parse(jsonStringInput);

            final var output = algorithm.execute(tracks, items);
            ctx.json(output);
        });
    }
}
