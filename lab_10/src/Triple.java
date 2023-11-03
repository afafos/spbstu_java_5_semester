public class Triple<T extends Comparable<T>> {
    private final T first;
    private final T second;
    private final T third;

    public Triple(T first, T second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public T getThird() {
        return third;
    }

    public T min() {
        if (!(first instanceof Comparable) || !(second instanceof Comparable) || !(third instanceof Comparable)) {
            throw new UnsupportedOperationException("Types are not comparable.");
        }
        return min(first, min(second, third));
    }

    public T max() {
        if (!(first instanceof Comparable) || !(second instanceof Comparable) || !(third instanceof Comparable)) {
            throw new UnsupportedOperationException("Types are not comparable.");
        }
        return max(first, max(second, third));
    }

    public double mean() {
        if (first instanceof Number && second instanceof Number && third instanceof Number) {
            double sum = ((Number) first).doubleValue() + ((Number) second).doubleValue() + ((Number) third).doubleValue();
            return sum / 3.0;
        } else {
            throw new UnsupportedOperationException("Mean operation is only supported for numbers.");
        }
    }

    private T min(T a, T b) {
        return a.compareTo(b) < 0 ? a : b;
    }

    private T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
}
