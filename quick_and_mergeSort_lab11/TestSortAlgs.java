import edu.princeton.cs.algs4.Queue;

import org.junit.Assert;
import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> unsort = new Queue<String>();
        unsort.enqueue("as");
        unsort.enqueue("rain");
        unsort.enqueue("mad");
        unsort.enqueue("bee");
        unsort.enqueue("fish");
        unsort.enqueue("island");
        unsort.enqueue("log");
        Assert.assertTrue(!isSorted(unsort));
        Queue<String> alreadysort = new Queue<String>();
        unsort.enqueue("as");
        unsort.enqueue("bee");
        unsort.enqueue("fish");
        unsort.enqueue("island");
        unsort.enqueue("log");
        unsort.enqueue("mad");
        unsort.enqueue("rain");
        Queue<String> msorted = MergeSort.mergeSort(unsort);
        Queue<String> qsorted = QuickSort.quickSort(unsort);

        Assert.assertTrue(isSorted(msorted));
        Assert.assertTrue(isSorted(qsorted));

    }

    @Test
    public void testMergeSort() {

    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
