import java.io.File;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    String helpString = "Arguments format: <command> <file path>, where command is encode, decode, inform";

    Huffman h = new Huffman();

    String[] nargs = new String[args.length];
    System.arraycopy(args, 0, nargs, 0, args.length);

    String[] commands = new String[] { "encode", "decode", "inform" };

    if (args.length == 0 || !(Arrays.asList(commands).contains(args[0]))) {
      nargs = new String[args.length + 1];
      System.arraycopy(args, 0, nargs, 1, args.length);
      nargs[0] = "encode";
    }

    switch (nargs[0]) {
      case "decode" -> {
        if (nargs.length <= 1) {
          throw new IllegalArgumentException("Provide path to archive file");
        }

        CompressionInfo compressionInfo = DataIOManager.readArchive(args[1]);

        byte[] result = h.decode(compressionInfo.compressedData, compressionInfo.recoveryMap);

        DataIOManager.writeFile(result, compressionInfo.originalFileName);
      }
      case "encode" -> {
        if (nargs.length <= 1) {
          throw new IllegalArgumentException("Provide path to a file");
        }

        byte[] data = DataIOManager.readFile(nargs[1]);
        byte[] result = h.encode(data);
        File f = new File(nargs[1]);
        String originalFileName = f.getName();
        String outputFilePath = originalFileName.substring(0, originalFileName.lastIndexOf('.')) + ".arc";

        DataIOManager.writeArchive(result, h.codeMap, originalFileName, outputFilePath);
      }
      case "inform" -> {
        if (nargs.length <= 1) {
          throw new IllegalArgumentException("Provide path to archive file");
        }

        CompressionInfo compressionInfo = DataIOManager.readArchive(args[1]);
        byte[] result = h.decode(compressionInfo.compressedData, compressionInfo.recoveryMap);

        DataIOManager.test(compressionInfo, result);
      }
      default -> throw new IllegalArgumentException(helpString);
    }
  }
}
