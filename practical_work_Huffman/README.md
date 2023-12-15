# Huffman Coding

Huffman Coding is a Java implementation of the Huffman coding algorithm. 
This program allows you to encode and decode text files using Huffman coding, providing information about compression ratios.

## Overview

- `CompressionInfo.java`: Defines a class to store information about compressed data, including the compressed data itself, original file name, recovery map, original length, and data offset.

- `DataIOManager.java`: Manages input and output operations, including reading and writing archive files, reading normal files, and testing compression results.

- `Huffman.java`: Implements the Huffman compression algorithm. It includes methods for encoding and decoding data, building Huffman trees, and managing code mappings.

- `Leaf.java`: Represents a leaf node in the Huffman tree, containing a symbol and its corresponding prefix code.

- `Main.java`: Contains the main application logic for encoding, decoding, and providing information about archive files.

- `Node.java`: Represents a node in the Huffman tree, with frequency information and references to left and right child nodes.

## Usage

To use the Huffman compression algorithm, you can run the Main class with the following commands:

1. **To encode a file**: ```java Main encode <file_path>```
2. **To decode an archive**: ```java Main decode <archive_path>```
3. **To get information about an archive**: ```java Main inform <archive_path>```

## Results

The Huffman compression algorithm has been implemented successfully, providing compression and decompression of data. 

- **Compression Ratio**: The compression ratio is calculated as the percentage of the original file length to the compressed file length. The ratio is printed during the testing phase.

- **Code Map**: The code map represents the mapping of symbols to their Huffman codes. This information is displayed during the testing phase.

- **Archive Information**: The inform command provides detailed information about the compressed archive, including original file name, length, and code mappings.

