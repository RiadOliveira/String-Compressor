package compression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HuffmanTree {
    private Node root = null;

    private class AuxiliarNode {
        public Node node;
        public Integer level;

        public AuxiliarNode(Node node, Integer level) {
            this.node = node;
            this.level = level;
        }
    }

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

    public String getCompressedStringBits(String receivedString) {
        String compressedString = "";

        for(int ind=0 ; ind<receivedString.length() ; ind++) {
            char iterationChar = receivedString.charAt(ind);
            compressedString += searchCharBinaryCode(iterationChar);
        }

        return compressedString;
    }

    private String searchCharBinaryCode(char character) {
        return searchCharBinaryCode(character, root, "");
    }

    private String searchCharBinaryCode(
        char character, Node node, String code
    ) {
        if(node == null) return null;
        if(node.getCharacter() == character) return code;

        String leftCode = searchCharBinaryCode(
            character, node.getLeft(), code + "0"
        );
        String rightCode = searchCharBinaryCode(
            character, node.getRight(), code + "1"
        );

        if(leftCode != null) return leftCode;
        if(rightCode != null) return rightCode;
        return null;
    }

    public void printTreeCodes() {
        System.out.println("Tabela de codificação:");
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

    public void showTree() {
        List<AuxiliarNode> auxiliarList = new ArrayList<>();
        fillAuxiliarList(root, 1, auxiliarList);
        sortAuxiliarList(auxiliarList);

        System.out.println("\nÁrvore de Huffman:");
        showAuxiliarList(auxiliarList);
        System.out.print("\n\n");
    }

    private void showAuxiliarList(List<AuxiliarNode> auxiliarList) {
        Integer iterationLevel = 1;

        while(!auxiliarList.isEmpty()) {
            AuxiliarNode iterationNode = auxiliarList.remove(0);

            if(iterationNode.level > iterationLevel) {
                iterationLevel = iterationNode.level;
                System.out.println();
            }

            Node.showNodeData(iterationNode.node);
            System.out.print("  ");
        }
    }

    private void sortAuxiliarList(List<AuxiliarNode> auxiliarList) {
        Collections.sort(auxiliarList, new Comparator<AuxiliarNode>() {
            public int compare(
                AuxiliarNode firstNode, AuxiliarNode secondNode
            ) {
                return firstNode.level.compareTo(secondNode.level);
            }
        });
    }

    private void fillAuxiliarList(
        Node node, Integer level, List<AuxiliarNode> auxiliarList
    ) {
        auxiliarList.add(new AuxiliarNode(node, level));
        if(node == null) return;

        Integer nextLevel = level + 1;
        fillAuxiliarList(node.getLeft(), nextLevel, auxiliarList);
        fillAuxiliarList(node.getRight(), nextLevel, auxiliarList);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
