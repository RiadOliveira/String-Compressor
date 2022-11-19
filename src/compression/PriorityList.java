package compression;

import java.util.ArrayList;
import java.util.List;

public class PriorityList {
    private List<Node> nodesList;

    public PriorityList(int listSize) {
        nodesList = new ArrayList<>(listSize);
    }

    public int size() {
        return nodesList.size();
    }

    public Node getFirst() {
        return nodesList.get(0);
    }

    public void add(Node newNode) {
        nodesList.add(newNode);
        riseNode(nodesList.size() - 1);
    }

    private Node getNodeAtPosition(int index) {
        if(index >= nodesList.size() || index < 0) return null;
        return nodesList.get(index);
    }

    public Node remove() {
        if(size() == 0) return null;
        return nodesList.remove(size() - 1);
    }

    public Node removeFirst() {
        if(size() == 0) return null;

        int lowerPriorityNodeIndex = nodesList.size() - 1;
        Node higherPriorityNode = getNodeAtPosition(0);
        Node lowerPriorityNode = getNodeAtPosition(
            lowerPriorityNodeIndex
        );

        nodesList.set(0, lowerPriorityNode);
        nodesList.remove(lowerPriorityNodeIndex);
        descendNode(0);

        return higherPriorityNode;
    }

    private void exchangePositions(int firstIndex, int secondIndex) {
        Node firstNode = getNodeAtPosition(firstIndex);
        Node secondNode = getNodeAtPosition(secondIndex);

        nodesList.set(firstIndex, secondNode);
        nodesList.set(secondIndex, firstNode);
    }

    private void riseNode(int nodeIndex) {
        if(nodeIndex == 0) return;

        Node Node = getNodeAtPosition(nodeIndex);
        int parentIndex = (nodeIndex + 1)/2 - 1;
        Node parentNode = getNodeAtPosition(parentIndex);

        if(Node.hasBiggerPriorityThan(parentNode)) {
            exchangePositions(nodeIndex, parentIndex);
            riseNode(parentIndex);
        }
    }

    private void descendNode(int nodeIndex) {
        int leftChildIndex = (nodeIndex + 1) * 2 - 1;
        if(leftChildIndex > nodesList.size() - 1) return;

        Node leftChild = getNodeAtPosition(leftChildIndex);
        int rightChildIndex = leftChildIndex + 1;
        Node rightChild = getNodeAtPosition(rightChildIndex);

        boolean leftChildHasBiggerPriority = leftChild.hasBiggerPriorityThan(rightChild);
        int higherPriorityChildIndex = 
            leftChildHasBiggerPriority ? leftChildIndex : rightChildIndex;
        Node higherPriorityChildNode = 
            leftChildHasBiggerPriority ? leftChild : rightChild;

        Node Node = getNodeAtPosition(nodeIndex);
        boolean needsExchange = higherPriorityChildNode.hasBiggerPriorityThan(Node);
        if(needsExchange) {
            exchangePositions(nodeIndex, higherPriorityChildIndex);
            descendNode(higherPriorityChildIndex);
        }
    }
}
