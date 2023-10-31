public class Main {
    public static void main(String[] args) {

        MyClass obj1 = new MyClass(10);
        MyClass obj2 = new MyClass(20);

        System.out.println("До обмена:");
        System.out.println("obj1: " + obj1.getValue());
        System.out.println("obj2: " + obj2.getValue());

        swapObjects(obj1, obj2);

        System.out.println("После обмена:");
        System.out.println("obj1: " + obj1.getValue());
        System.out.println("obj2: " + obj2.getValue());
    }

    public static void swapObjects(MyClass a, MyClass b) {
        int temp = a.getValue();
        a.setValue(b.getValue());
        b.setValue(temp);
    }
}

class MyClass {
    private int value;

    public MyClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
