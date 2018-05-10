package trivia.runner;

import trivia.uglytrivia.Game;

import java.io.PrintStream;
import java.util.Random;

public class GameRunner {

    private static boolean notAWinner;

    public static void main(String[] args) {
        new GameRunner().run(System.out, new Random());
    }

    public void run(PrintStream out, Random random) {
        Game aGame = new Game(out);

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = random;

        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }
}


