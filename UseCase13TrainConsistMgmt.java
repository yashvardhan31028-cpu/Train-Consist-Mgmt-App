import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

public class UseCase13TrainConsistMgmt {

    static class Bogie {
        String type;
        int capacity;

        Bogie(String type, int capacity) {
            this.type = type;
            this.capacity = capacity;
        }
    }

    public static List<Bogie> loopFilter(List<Bogie> bogies) {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.capacity > 60) {
                result.add(b);
            }
        }
        return result;
    }

    public static List<Bogie> streamFilter(List<Bogie> bogies) {
        return bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
    }

    public static long measureTime(Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" UC13 - Performance Comparison ");
        System.out.println("=====================================\n");

        List<Bogie> bogies = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            bogies.add(new Bogie("Type" + i, i % 100));
        }

        long loopTime = measureTime(() -> loopFilter(bogies));
        long streamTime = measureTime(() -> streamFilter(bogies));

        System.out.println("Loop Execution Time (ns): " + loopTime);
        System.out.println("Stream Execution Time (ns): " + streamTime);

        System.out.println("\nUC13 benchmarking completed...");
    }

    @Test
    void testLoopFilteringLogic() {
        List<Bogie> list = List.of(
                new Bogie("A", 70),
                new Bogie("B", 50)
        );
        List<Bogie> result = loopFilter(list);
        assertEquals(1, result.size());
    }

    @Test
    void testStreamFilteringLogic() {
        List<Bogie> list = List.of(
                new Bogie("A", 70),
                new Bogie("B", 50)
        );
        List<Bogie> result = streamFilter(list);
        assertEquals(1, result.size());
    }

    @Test
    void testLoopAndStreamResultsMatch() {
        List<Bogie> list = List.of(
                new Bogie("A", 70),
                new Bogie("B", 80),
                new Bogie("C", 50)
        );
        assertEquals(loopFilter(list).size(), streamFilter(list).size());
    }

    @Test
    void testExecutionTimeMeasurement() {
        long time = measureTime(() -> {});
        assertTrue(time >= 0);
    }

    @Test
    void testLargeDatasetProcessing() {
        List<Bogie> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(new Bogie("T", i));
        }
        assertFalse(streamFilter(list).isEmpty());
    }
}