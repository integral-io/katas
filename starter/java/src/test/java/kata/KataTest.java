package kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KataTest {
    @Test
    void testSomeKataMethod() {
        Kata subjectUnderTest = new Kata();
        assertEquals("hello, world!", subjectUnderTest.someKataMethod());
    }
}
