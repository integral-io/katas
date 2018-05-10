package trivia;

import org.approvaltests.Approvals;
import org.junit.Test;

public class GameTest {

    @Test
    public void play() {
        Approvals.verify(true);
    }
}
