import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PQHeapTest {
    PQHeap heap;

    @Before
    public void setUp() throws Exception {
        heap = new PQHeap();
        heap.insertMessage("message 4", 4);
        heap.insertMessage("message 9", 9);
        heap.insertMessage("message 12", 12);
        heap.insertMessage("message 4", 4);
        heap.insertMessage("message 4", 4);
        heap.insertMessage("message 15", 15);
    }

    @Test
    public void insertMessage() throws Exception {
        heap.insertMessage("message 2", 2);
        assertEquals("message 2", heap.getNextMessage().getMessage());
        heap.insertMessage("message 1", 1);
        assertEquals("message 1", heap.getNextMessage().getMessage());
    }

    @Test
    public void heapSize() throws Exception {
        PQHNode root = heap.getRoot();
        int size = root.getLeftCount() + root.getRightCount() + 1;
        assertEquals(6, size);
    }

    @Test
    public void getNextMessage() throws Exception {
        assertEquals("message 4", heap.getNextMessage().getMessage());
        assertEquals("message 4", heap.getNextMessage().getMessage());
        assertEquals("message 4", heap.getNextMessage().getMessage());
        assertEquals("message 9", heap.getNextMessage().getMessage());
        assertEquals("message 12", heap.getNextMessage().getMessage());
        assertEquals("message 15", heap.getNextMessage().getMessage());
        assertEquals("You do not have any message", heap.getNextMessage().getMessage());
        assertEquals("You do not have any message", heap.getNextMessage().getMessage());
    }

    @Test
    public void getNextMessage2() throws Exception {
        PQHeap hp = new PQHeap();
        assertEquals("You do not have any message", hp.getNextMessage().getMessage());

    }

    @Test
    public void getNextMessage3() throws Exception {
        PQHeap hp = new PQHeap();
        hp.insertMessage("message 2", 2);
        assertEquals("message 2", hp.getNextMessage().getMessage());
        assertEquals("You do not have any message", hp.getNextMessage().getMessage());

    }


}

