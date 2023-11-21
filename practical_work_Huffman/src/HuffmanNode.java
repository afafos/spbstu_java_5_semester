class HuffmanNode implements Comparable<HuffmanNode> {
    int data;
    char c;
    HuffmanNode left, right;

    public HuffmanNode(char c, int data, HuffmanNode left, HuffmanNode right) {
        this.c = c;
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.data - o.data;
    }
}
