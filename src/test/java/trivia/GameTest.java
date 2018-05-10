package trivia;

import org.approvaltests.combinations.CombinationApprovals;
import org.junit.Test;
import trivia.runner.GameRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class GameTest {

    @Test
    public void play() throws Exception {
        Integer[] seeds = {0, 1, 2, 3, -12, 100029, Integer.MAX_VALUE, Integer.MIN_VALUE};
        CombinationApprovals.verifyAllCombinations(this::playAGame, seeds);
    }

    private ByteArrayOutputStream playAGame(Integer seed) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        GameRunner gameRunner = new GameRunner();
        gameRunner.run(printStream, new Random(seed));
        return output;
    }
}
