import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class UseCase10TrainConsistMgmt {

    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    public static int totalCapacity(List<Bogie> bogies) {
        return bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56)
        );

        System.out.println("Total: " + totalCapacity(bogies));
    }

    @Test
    void testTotalCapacity() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56)
        );

        int total = totalCapacity(bogies);
        assertEquals(128, total);
    }
}