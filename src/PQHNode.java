
public class PQHNode implements Comparable<PQHNode> {
    private int priority;
    private String message;
    // Each node holds 3 nodes for its left child, right child and its parent.
    private PQHNode left, right, parent;
    //Each node holds information about how many nodes there are in its left and right subtrees. It is useful to make a decision while inserting a new node to the heap.
    private int leftCount, rightCount;

    public PQHNode(String message, int priority) {
        this.message = message;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public String getMessage() {
        return message;
    }

    public PQHNode getLeft() {
        return left;
    }

    public PQHNode getRight() {
        return right;
    }

    public PQHNode getParent() {
        return parent;
    }

    public int getLeftCount() {
        return leftCount + 1;
    }

    public int getRightCount() {
        return rightCount + 1;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLeft(PQHNode left) {
        this.left = left;
    }

    public void setRight(PQHNode right) {
        this.right = right;
    }

    public void setParent(PQHNode parent) {
        this.parent = parent;
    }

    public void incrementLeftCount() {
        this.leftCount += 1;
    }

    public void incrementRightCount() {
        this.rightCount += 1;
    }

    public void decrementLeftCount() {
        this.leftCount -= 1;
    }

    public void decrementRightCount() {
        this.rightCount -= 1;
    }

    public boolean hasLeft() {
        return (this.left != null);
    }

    public boolean hasRight() {
        return (this.right != null);
    }

    public String toString() {
        return "<" + message + "," + priority + ">";
    }

    public int compareTo(PQHNode nodeToCompare) {
        if (this.getPriority() < nodeToCompare.getPriority())
            return 1;
        else
            return -1;
    }
}
