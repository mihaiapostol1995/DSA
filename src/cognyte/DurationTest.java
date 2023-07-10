package cognyte;

import java.time.Duration;
import java.time.Instant;

public class DurationTest {

    public static void main(String[] args) {
        Instant start = Instant.parse("2017-10-03T10:15:30.00Z");
        Instant end = Instant.parse("2017-10-03T10:16:35.00Z");

        Duration duration = Duration.between(start, end);
        System.out.println(duration);
    }
}
