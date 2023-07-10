package cognyte;

import java.time.*;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.DAYS;

public class ParseShort {
    public static void main(String[] args) {
        long hoursInMs = TimeUnit.HOURS.toMillis
                (Short.parseShort("-2"));
        System.out.println(hoursInMs);

        LocalDateTime date1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis() + 1000),
                ZoneId.systemDefault());
        LocalDateTime date2 = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(60)),
                ZoneId.systemDefault());

        long days = DAYS.between(date1, date2);
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date1.atZone(ZoneId.systemDefault()).toEpochSecond()); //millis
       // System.out.println(ZonedDateTime.of(date1.toLocalDate(), ZoneId.systemDefault())); //millis
        System.out.println(days);
        System.out.println(ZoneOffset.UTC.getTotalSeconds());

        System.out.println(date1.toLocalDate().plusDays(20));
        System.out.println(date1.toLocalDate().atStartOfDay().toEpochSecond(ZoneOffset.UTC));

        System.out.println(31/5);
        System.out.println(30%5);
        System.out.println(2/10);

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
// Duration oneDay = Duration.between(today, yesterday); // throws an exception
        long days2 = Duration.between(today.atStartOfDay(), yesterday.atStartOfDay()).toDays(); // another option
        long days3 = Duration.between(today.atStartOfDay(), date1).toDays(); // another option
        System.out.println(days2);
        System.out.println(days3);
    }
}
