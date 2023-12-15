import java.util.HashMap;

public class CompressionInfo {
    String compressedData;
    String originalFileName;
    HashMap<String, Byte> recoveryMap;
    int originalLength;
    int dataOffset;
}
