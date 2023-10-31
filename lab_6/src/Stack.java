public class Stack {
    private int maxSize;
    private int top;
    private int[] stackArray;

    public Stack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int value) {
        if (top < maxSize - 1) {
            stackArray[++top] = value;
            System.out.println("Добавлен элемент: " + value);
        } else {
            System.out.println("Стек полон. Невозможно добавить элемент " + value);
        }
    }

    public int pop() {
        if (top >= 0) {
            int poppedValue = stackArray[top--];
            System.out.println("Извлечен элемент: " + poppedValue);
            return poppedValue;
        } else {
            System.out.println("Стек пуст. Невозможно извлечь элемент.");
            return -1; // Возвращаем -1, чтобы указать, что стек пуст
        }
    }

    public int peek() {
        if (top >= 0) {
            return stackArray[top];
        } else {
            System.out.println("Стек пуст. Нет верхнего элемента.");
            return -1;
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public static void main(String[] args) {
        Stack stack = new Stack(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println("Верхний элемент стека: " + stack.peek());

        while (!stack.isEmpty()) {
            stack.pop();
        }
    }
}
