package compression;

import java.util.Scanner;

public class StringCompressor {
    private static Scanner scanner = new Scanner(System.in);

    public static void startCompressionMenu() {
        showCompressionMenu();

        while(scanner.nextInt() != 0) {
            handleCompressionOperation();
            showCompressionMenu();
        }
    }

    private static void showCompressionMenu() {
        System.out.println("Selecione uma das opções abaixo:");
        System.out.println("[1] Comprimir texto");
        System.out.println("[0] Sair");
        System.out.println();
        System.out.print("Opção: ");
    }

    private static void handleCompressionOperation() {
        System.out.print("Insira o texto para ser comprimido: ");
        String receivedString = scanner.next();
        System.out.println();

        showStringAndItsBits(receivedString);
        Node stringNodes[] = getNodesFromString(receivedString);
    }

    private static void showStringAndItsBits(String receivedString) {
        System.out.println("String recebida: " + receivedString);

        int bitsQuantity = 0;
        for(int ind=0 ; ind<receivedString.length() ; ind++) {
            char iterationChar = receivedString.charAt(ind);
            String binaryString = Integer.toBinaryString(iterationChar);
            bitsQuantity += binaryString.length();

            System.out.print("Caractere: " + iterationChar + " - ");
            System.out.print("Bits: " + binaryString);
            System.out.println();
        }

        System.out.println("Quantidade total de bits: " + bitsQuantity);
    }

    private static Node[] getNodesFromString(String receivedString) {
        Node stringNodes[] = new Node[receivedString.length()];
        int stringLength = receivedString.length();

        for(int stringIndex=0 ; stringIndex<stringLength ; stringIndex++) {
            char iterationChar = receivedString.charAt(stringIndex);

            int nodesIndex = 0;
            while(
                stringNodes[nodesIndex] != null &&
                stringNodes[nodesIndex].getCharacter() != iterationChar
            ) {
                nodesIndex++;
            }

            if(stringNodes[nodesIndex] == null) {
                stringNodes[nodesIndex] = new Node(iterationChar);
            } else stringNodes[nodesIndex].updateFrequency();
        }

        return stringNodes;
    }

    private static void executeCompression() {

    }
}
