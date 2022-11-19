package compression;
public class Node {
    private int frequency = 1;
    private char character;

    private Node left = null;
    private Node right = null;

    public Node(char character) {
        this.character = character;
    }

    public Node(int frequency, char character) {
        this.frequency = frequency;
        this.character = character;
    }

    public boolean hasBiggerPriorityThan(Node node) {
        if(node == null) return true;
        return this.frequency > node.getFrequency();
    }

    public void updateFrequency() {
        frequency++;
    }

    public int getFrequency() {
        return frequency;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
