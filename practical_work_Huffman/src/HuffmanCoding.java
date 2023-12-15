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

            StringBuilder textBuilder = new StringBuilder();
            String line;

            // Read the entire content of the file
            while ((line = br.readLine()) != null) {
                textBuilder.append(line).append('\n');
            }

            String text = textBuilder.toString().trim(); // Remove trailing newline

            int[] frequency = buildFrequencyArray(text);

            HuffmanNode root = buildHuffmanTree(frequency);
            generateHuffmanCodes(root, "");

            System.out.println("Huffman Tree:");
            printHuffmanTree(root, "");

            StringBuilder encodedText = new StringBuilder();
            for (char c : text.toCharArray()) {
                encodedText.append(huffmanCodes.get(c)); // получаем код символа
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
        try (DataInputStream dis = new DataInputStream(new FileInputStream(encodedFile)); // поток чтения из бин
             BufferedWriter bw = new BufferedWriter(new FileWriter(decodedFile))) { // поток записи в текстовый файл

            HuffmanNode root = readTree(dis);
            String encodedText = readEncodedText(dis);

            StringBuilder decodedText = new StringBuilder();
            HuffmanNode current = root;

            for (char bit : encodedText.toCharArray()) { // перебираем каждый бит
                if (bit == '0') {
                    current = current.left;
                } else {
                    current = current.right;
                }

                if (current.left == null && current.right == null) { // достигаем листа, добавляем символ в раскод. текст
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

    private static int[] buildFrequencyArray(String text) { // построение массива частот во сходной строке
        int[] frequency = new int[256]; // так как кодировка ASCII
        for (char c : text.toCharArray()) {
            frequency[c]++;
        }
        return frequency;
    }

    private static HuffmanNode buildHuffmanTree(int[] frequency) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (char i = 0; i < 256; i++) {
            if (frequency[i] > 0) { // если частота символа >0, то создаём новый узел и добавляется в очередь
                priorityQueue.add(new HuffmanNode(i, frequency[i], null, null));
            }
        }

        while (priorityQueue.size() > 1) { // пока не останется корень
            HuffmanNode left = priorityQueue.poll(); // извлекаем узел с наименьшей частотой
            HuffmanNode right = priorityQueue.poll();
            priorityQueue.add(new HuffmanNode('\0', left.data + right.data, left, right)); // фиктивный узел (сумма двух извлеченных)
        }

        return priorityQueue.poll();
    }

    private static void generateHuffmanCodes(HuffmanNode root, String code) {
        if (root != null) {
            if (root.left == null && root.right == null) { // если лист, то добавляем код символа
                huffmanCodes.put(root.c, code);
            }

            generateHuffmanCodes(root.left, code + "0"); // рекурсия для left и right
            generateHuffmanCodes(root.right, code + "1");
        }
    }

    private static void printHuffmanTree(HuffmanNode root, String code) {
        if (root != null) {
            if (root.left == null && root.right == null) { // если лист, то выводим символ и его код
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
                dos.writeBoolean(true); // запись в поток true для обозначения листа
                dos.writeChar(root.c); // запись символа root.c (символ листа)
            } else {
                dos.writeBoolean(false);
                writeTree(root.left, dos);
                writeTree(root.right, dos);
            }
        }
    }

    private static void writeEncodedText(String encodedText, DataOutputStream dos) throws IOException {
        int paddedLength = 8 - (encodedText.length() % 8); // расчёт "0", которые нужно добавить к заход.тексту до байта
        for (int i = 0; i < paddedLength; i++) { // добавляем, чтобы было целое число байт
            encodedText += "0";
        }

        for (int i = 0; i < encodedText.length(); i += 8) {
            String byteStr = encodedText.substring(i, i + 8); // получение подстроки 8 бит
            // преобразование строки бит в число в 2 с-ме счисл и запись этого числа в виде байта в выходной поток
            dos.writeByte((byte) Integer.parseInt(byteStr, 2));
        }
    }

    private static HuffmanNode readTree(DataInputStream dis) throws IOException {
        if (dis.readBoolean()) { // чтение булевого значения из входного потока
            return new HuffmanNode(dis.readChar(), 0, null, null); // создаем новый лист с символом, частота 0
        } else {
            HuffmanNode left = readTree(dis);
            HuffmanNode right = readTree(dis);
            return new HuffmanNode('\0', 0, left, right);
        }
    }

    private static String readEncodedText(DataInputStream dis) throws IOException {
        StringBuilder encodedText = new StringBuilder();
        while (dis.available() > 0) {
            byte b = dis.readByte(); // читаем байт
            // преобразование байта в стсроку бит с использованием toBinaryString, замена пробелов на 0
            encodedText.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }
        return encodedText.toString();
    }
}
