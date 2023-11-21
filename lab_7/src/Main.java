public class Main {
    public static void main(String[] args) {

        MyClass<Integer> obj1 = new MyClass<>(10);
        MyClass<Integer> obj2 = new MyClass<>(20);

        System.out.println("До обмена:");
        System.out.println("obj1: " + obj1.getValue());
        System.out.println("obj2: " + obj2.getValue());

        swapObjects(obj1, obj2);

        System.out.println("После обмена:");
        System.out.println("obj1: " + obj1.getValue());
        System.out.println("obj2: " + obj2.getValue());
    }

    public static <T> void swapObjects(MyClass<T> a, MyClass<T> b) {
        T temp = a.getValue();
        a.setValue(b.getValue());
        b.setValue(temp);
    }
}

class MyClass<T> {
    private T value;

    public MyClass(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
