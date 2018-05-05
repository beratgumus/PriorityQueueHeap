

public class PQHeap {

    private PQHNode root;

    public PQHeap() {
        root = null;
    }

    public void insertMessage(String message, int priority) {

        PQHNode newNode = new PQHNode(message, priority);
        // If heap is empty, then make the new node root
        if (isEmpty())
            root = newNode;
        else {
            PQHNode current = root;
            // Loop to find correct place to insert new message in heap. Each node holds information about how many nodes there are in its left and right subtrees.
            // Thus these information and its priority level are the decision mechanism to traverse a heap to find correct place.
            while (current.getLeft() != null && current.getRight() != null && newNode.getPriority() > current.getPriority()) {
                if (current.getLeftCount() <= current.getRightCount()) {
                    current.incrementLeftCount();
                    current = current.getLeft();
                } else {
                    current.incrementRightCount();
                    current = current.getRight();
                }
            }
            // Now we found the first message that has lower priority than the new message. Let`s insert the new message and percolate down the heap to regulate the order of messages.
            downHeapForInsertion(current, newNode);
        }
    }

    // A method to percolate down the heap for insertion.
    private void downHeapForInsertion(PQHNode current, PQHNode newNode) {
        String tempMessage;
        int tempPriority;
        // Find the child that has higher priority and do swapping for percolating down.
        while (current.getLeft() != null && current.getRight() != null) {
            if (current.getLeft().compareTo(current.getRight()) > 0) {
                current.incrementLeftCount();
                current = swapForDownHeapInsertion(current, current.getLeft(), newNode);
            } else {
                current.incrementRightCount();
                current = swapForDownHeapInsertion(current, current.getRight(), newNode);
            }
        }
        // We reached the node that does not have 2 child, let`s insert the new node to the that node.
        if (current.getLeft() == null) {
            current.setLeft(newNode);
            newNode.setParent(current);
        } else {
            current.setRight(newNode);
            newNode.setParent(current);

        }
        // Check if the node that lastly added violate the heap property, then swap its values with its parent.
        if (newNode.getPriority() < newNode.getParent().getPriority()) {
            PQHNode parent = newNode.getParent();
            tempMessage = parent.getMessage();
            tempPriority = parent.getPriority();
            parent.setMessage(newNode.getMessage());
            parent.setPriority(newNode.getPriority());
            newNode.setMessage(tempMessage);
            newNode.setPriority(tempPriority);

        }
    }

    // A method to swap proper nodes` values to provide heap property.
    private PQHNode swapForDownHeapInsertion(PQHNode current, PQHNode child, PQHNode newNode) {
        String tempMessage = child.getMessage();
        int tempPriority = child.getPriority();
        child.setMessage(current.getMessage());
        child.setPriority(current.getPriority());
        current.setMessage(newNode.getMessage());
        current.setPriority(newNode.getPriority());
        newNode.setMessage(tempMessage);
        newNode.setPriority(tempPriority);
        return child;
    }


    public PQHNode getNextMessage() {
        // If the heap is empty, return a message that inform them.
        if (isEmpty())
            return new PQHNode("You do not have any message", 0);
        else {
            // Return a root message. a root needs to be refilled. Thus percolate down the heap to provide heap property.
            PQHNode nextMessage = new PQHNode(root.getMessage(), root.getPriority());
            downHeapForDeletion();
            return nextMessage;
        }
    }

    // A method to percolate down the heap for deletion.
    private void downHeapForDeletion() {
        PQHNode current = root;
        // a variable to hold the last move in a heap. - 1 represent left, 1 represent right, 0 represent that while loop did not work(heap does not have any node that has 2 child).
        int lastMove = 0;
        // percolating down till the end of heap.
        while (current.getLeft() != null && current.getRight() != null) {
            if (current.getLeft().compareTo(current.getRight()) > 0) {
                current.decrementLeftCount();
                current = swapForDownHeapDeletion(current, current.getLeft());
                lastMove = -1;
            } else {
                current.decrementRightCount();
                current = swapForDownHeapDeletion(current, current.getRight());
                lastMove = 1;
            }
        }
        // the remaining nodes does not have 2 child anymore but need to keep move to the leaf node to delete proper message.
        while (current.getRightCount() > 1 || current.getLeftCount() > 1) {
            if (current.hasLeft()) {
                current.decrementLeftCount();
                current = swapForDownHeapDeletion(current, current.getLeft());
                lastMove = -1;
            } else {
                current.decrementRightCount();
                current = swapForDownHeapDeletion(current, current.getRight());
                lastMove = 1;
            }
        }
        // If the node to be deleted is not a leaf node.
        if (current.getLeft() != null || current.getRight() != null) {
            if (current.hasLeft()) {
                deleteChild(current, current.getLeft());
                current.setLeft(null);
            } else {
                deleteChild(current, current.getRight());
                current.setRight(null);
            }
        }
        // If the node to be deleted is a leaf node.
        else {
            if (lastMove == -1) {
                current.getParent().setLeft(null);
                current.setParent(null);
            } else if (lastMove == 1) {
                current.getParent().setRight(null);
                current.setParent(null);
            } else {
                root = null;
            }
        }
    }

    // A method to swap proper nodes` values to provide heap property.
    private PQHNode swapForDownHeapDeletion(PQHNode current, PQHNode child) {
        current.setMessage(child.getMessage());
        current.setPriority(child.getPriority());
        return child;
    }

    // A method to delete its child.
    private void deleteChild(PQHNode current, PQHNode child) {
        current.setMessage(child.getMessage());
        current.setPriority(child.getPriority());
        child.setParent(null);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public PQHNode getRoot() {
        return root;
    }
}

