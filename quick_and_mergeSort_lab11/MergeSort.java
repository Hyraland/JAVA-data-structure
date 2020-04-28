import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /**
     * Returns a queue of queues that each contain one item from items.
     *
     * This method should take linear time.
     *
     * @param   items  A Queue of items.
     * @return         A Queue of queues, each containing an item from items.
     *
     */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {

        Queue<Queue<Item>> SingleItemQueues = new Queue<Queue<Item>>();
        Queue<Item> todequeitems = new Queue<>();
        while(items.size()>0){
            Item item = items.dequeue();
            todequeitems.enqueue(item);
        }
        while(todequeitems.size()>0){
            Queue<Item> singlequeue = new Queue<>();
            singlequeue.enqueue(todequeitems.dequeue());
            SingleItemQueues.enqueue(singlequeue);
        }

        return SingleItemQueues;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        int s1 = q1.size()/2;
        int s2 = q2.size()/2;
        Queue<Item> sortedqueue = new Queue<>();
        Item i1;
        Item i2;
        while(q1.size()>0 && q2.size()>0){
            i1 = q1.dequeue();
            i2 = q2.dequeue();
            if (i1.compareTo(i2)> 0) {
                sortedqueue.enqueue(i2);
                sortedqueue.enqueue(i1);
            }
            else{
                sortedqueue.enqueue(i1);
                sortedqueue.enqueue(i2);
            }
        }
        while (q1.size()>0) {
            i1 = q1.dequeue();
            sortedqueue.enqueue(i1);
        }
        while (q2.size()>0){
            i2 = q2.dequeue();
            sortedqueue.enqueue(i2);
        }

        return sortedqueue;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     *
     * This method should take roughly nlogn time where n is the size of "items"
     * this method should be non-destructive and not empty "items".
     *
     * @param   items  A Queue to be sorted.
     * @return         A Queue containing every item in "items".
     *
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        Queue<Queue<Item>> SIQueues = makeSingleItemQueues(items);
        int qlen = items.size()/2;
        Queue<Item> q1 = new Queue<>();
        Queue<Item> q2 = new Queue<>();
        for (int i = 0; i < qlen; i += 1){
            Item titem = items.dequeue();
            q1.enqueue(titem);
        }

        for (int i = 0; i < items.size(); i += 1){
            Item titem = items.dequeue();
            q2.enqueue(titem);
        }
        if(q1.size() > 1){
            q1 = mergeSort(q1);
        }
        if(q2.size() > 1){
            q2 = mergeSort(q2);
        }

        if (q1.size()<= 1 && q2.size()<= 1){
            Queue<Item> squeue = mergeSortedQueues(q1, q2);
        }


        Queue<Item> squeue = mergeSortedQueues(q1, q2);
        // Your code here!
        return squeue;
    }
}
