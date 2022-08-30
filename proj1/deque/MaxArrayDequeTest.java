package deque;

import org.junit.Test;
import java.util.Comparator;
import java.util.function.ToDoubleBiFunction;
import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {
    @Test
    public void MaxTest() {
        MaxArrayDeque<Integer> Max = new MaxArrayDeque<>(new IntComparator());

        Max.addFirst(123123);
        Max.addFirst(124124);
        Max.addFirst(123124);
        Max.addFirst(123123);
        Max.addFirst(999999);

        assertEquals((Integer) 999999,Max.max());
    }

    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

}
