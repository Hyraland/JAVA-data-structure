package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void testGetSmallest() {
        ArrayHeapMinPQ<String> ahmPQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 20; i++) {
            String a = "hi" + Integer.toString(i);
            ahmPQ.add(a, i);
        }
        String x1 = ahmPQ.getSmallest();
        assertEquals(x1, "hi0");
    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<String> ahmPQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 20; i++) {
            String a = "hi" + Integer.toString(i);
            ahmPQ.add(a, i);
        }
        String x1 = ahmPQ.removeSmallest();
        String x2 = ahmPQ.removeSmallest();
        assertEquals(x1, "hi0");
        assertEquals(x2, "hi1");
        String x3 = ahmPQ.removeSmallest();
        assertEquals(x3, "hi3");
        String x4 = ahmPQ.removeSmallest();
        assertEquals(x4, "hi7");
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<String> ahmPQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 20; i++) {
            String a = "hi" + Integer.toString(i);
            ahmPQ.add(a, i);
        }

        ahmPQ.changePriority("hi7", 0);
        ahmPQ.changePriority("hi0", 9);
        assertEquals(ahmPQ.getSmallest(), "hi7");
    }
}
