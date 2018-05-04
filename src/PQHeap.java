
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

