import java.io.*;
import java.util.PriorityQueue;
import java.util.HashMap;

public class HuffmanCoding {
    private static HashMap<Character, String> huffmanCodes = new HashMap<>();

    public static void main(String[] args) {
        String inputFile = "input_output_files/input.txt";
        String encodedFile = "input_output_files/encoded.bin";
        String decodedFile = "input_output_files/decoded.txt";

        int originalSize = encode(inputFile, encodedFile);
        int decodedSize = decode(encodedFile, decodedFile);
        int encodedSize = encodedFile.length() * 8;

        double compressionRatio = (double) encodedSize / originalSize;
        System.out.println("Original Size: " + originalSize + " bits");
        System.out.println("Encoded Size: " + encodedSize + " bits");
        System.out.println("Compression Ratio: " + compressionRatio);
    }

    private static int encode(String inputFile, String encodedFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             DataOutputStream dos = new DataOutputStream(new FileOutputStream(encodedFile))) {

            String text = br.readLine();
            int[] frequency = buildFrequencyArray(text);

            HuffmanNode root = buildHuffmanTree(frequency);
            generateHuffmanCodes(root, "");

            System.out.println("Huffman Tree:");
            printHuffmanTree(root, "");

            StringBuilder encodedText = new StringBuilder();
            for (char c : text.toCharArray()) {
                encodedText.append(huffmanCodes.get(c));
            }

            writeTree(root, dos);
            writeEncodedText(encodedText.toString(), dos);

            return text.length() * 8; // original size

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static int decode(String encodedFile, String decodedFile) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(encodedFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(decodedFile))) {

            HuffmanNode root = readTree(dis);
            String encodedText = readEncodedText(dis);

            StringBuilder decodedText = new StringBuilder();
            HuffmanNode current = root;

            for (char bit : encodedText.toCharArray()) {
                if (bit == '0') {
                    current = current.left;
                } else {
                    current = current.right;
                }

                if (current.left == null && current.right == null) {
                    decodedText.append(current.c);
                    current = root;
                }
            }

            bw.write(decodedText.toString());
            return decodedText.length();

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static int[] buildFrequencyArray(String text) {
        int[] frequency = new int[256];
        for (char c : text.toCharArray()) {
            frequency[c]++;
        }
        return frequency;
    }

    private static HuffmanNode buildHuffmanTree(int[] frequency) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (char i = 0; i < 256; i++) {
            if (frequency[i] > 0) {
                priorityQueue.add(new HuffmanNode(i, frequency[i], null, null));
            }
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            priorityQueue.add(new HuffmanNode('\0', left.data + right.data, left, right));
        }

        return priorityQueue.poll();
    }

    private static void generateHuffmanCodes(HuffmanNode root, String code) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                huffmanCodes.put(root.c, code);
            }

            generateHuffmanCodes(root.left, code + "0");
            generateHuffmanCodes(root.right, code + "1");
        }
    }

    private static void printHuffmanTree(HuffmanNode root, String code) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                System.out.println(root.c + ": " + code);
            } else {
                printHuffmanTree(root.left, code + "0");
                printHuffmanTree(root.right, code + "1");
            }
        }
    }

    private static void writeTree(HuffmanNode root, DataOutputStream dos) throws IOException {
        if (root != null) {
            if (root.left == null && root.right == null) {
                dos.writeBoolean(true);
                dos.writeChar(root.c);
            } else {
                dos.writeBoolean(false);
                writeTree(root.left, dos);
                writeTree(root.right, dos);
            }
        }
    }

    private static void writeEncodedText(String encodedText, DataOutputStream dos) throws IOException {
        int paddedLength = 8 - (encodedText.length() % 8);
        for (int i = 0; i < paddedLength; i++) {
            encodedText += "0";
        }

        for (int i = 0; i < encodedText.length(); i += 8) {
            String byteStr = encodedText.substring(i, i + 8);
            dos.writeByte((byte) Integer.parseInt(byteStr, 2));
        }
    }

    private static HuffmanNode readTree(DataInputStream dis) throws IOException {
        if (dis.readBoolean()) {
            return new HuffmanNode(dis.readChar(), 0, null, null);
        } else {
            HuffmanNode left = readTree(dis);
            HuffmanNode right = readTree(dis);
            return new HuffmanNode('\0', 0, left, right);
        }
    }

    private static String readEncodedText(DataInputStream dis) throws IOException {
        StringBuilder encodedText = new StringBuilder();
        while (dis.available() > 0) {
            byte b = dis.readByte();
            encodedText.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }
        return encodedText.toString();
    }
}
