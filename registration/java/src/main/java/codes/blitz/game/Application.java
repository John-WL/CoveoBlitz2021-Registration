package codes.blitz.game;

import codes.blitz.game.algos.AlgorithmInput;
import codes.blitz.game.algos.LinearSolutionWithArrays;
import codes.blitz.game.algos.RailTransportProblem;
import io.javalin.Javalin;
import picocli.CommandLine;
import picocli.CommandLine.Option;

public class Application {
    private static final RailTransportProblem algorithm = new LinearSolutionWithArrays();

    public static class Args {
        @Option(names = {"-p", "--port"},
                description = "set port number (default: 27178)",
                defaultValue = "27178")
        public int port;
    }

    public static void main(String[] args) {
        final var arguments = new Args();
        new CommandLine(arguments).parseArgs(args);

        final var app = Javalin.create(cfg -> {
            cfg.requestCacheSize = 1000 * 1000 * 2L;
        });

        app.start(arguments.port);

        app.post("/microchallenge", ctx -> {
            final var input = ctx.bodyAsClass(AlgorithmInput.class);
            final var output = algorithm.execute(input);
            ctx.json(output);
        });
    }
}
