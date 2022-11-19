package compression;
public class HuffmanTree {
    Node root = null;

    public HuffmanTree(int treeNodesQuantity, Node treeNodes[]) {
        PriorityList nodesList = new PriorityList(treeNodesQuantity);

        for(int ind=0 ; ind<treeNodesQuantity ; ind++) {
            nodesList.add(treeNodes[ind]);
        }

        while(nodesList.size() > 1) {
            Node leftChild = nodesList.remove();
            Node rightChild = nodesList.remove();

            int parentFrequency = 
                leftChild.getFrequency() + rightChild.getFrequency();
            Node parentNode = new Node(parentFrequency, '-');

            parentNode.setLeft(leftChild);
            parentNode.setRight(rightChild);
            nodesList.add(parentNode);
        }

        root = nodesList.getFirst();
    }

    public void printTreeCodes() {
        printTreeCodes(root, "");
    }

    private void printTreeCodes(Node node, String binaryCode) {
        Node leftNode = node.getLeft();
        Node rightNode = node.getRight();

        if (leftNode == null && rightNode == null) {
            System.out.println(node.getCharacter() + ": " + binaryCode);
            return;
        }

        printTreeCodes(leftNode, binaryCode + "0");
        printTreeCodes(rightNode, binaryCode + "1");
    }
}
