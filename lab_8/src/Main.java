import java.lang.reflect.*;
public class Main {
    public static void printClassInfo(Class<?> clazz) {

        System.out.println("Class Name: " + clazz.getName());

        // Вывод модификаторов доступа
        int modifiers = clazz.getModifiers();
        System.out.println("Modifiers: " + Modifier.toString(modifiers));

        // Вывод суперкласса
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            System.out.println("Superclass: " + superClass.getName());
        }

        // Вывод интерфейсов
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            System.out.print("Implemented Interfaces: ");
            for (Class<?> iface : interfaces) {
                System.out.print(iface.getName() + ", ");
            }
            System.out.println();
        }

        // Вывод полей
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length > 0) {
            System.out.println("Fields:");
            for (Field field : fields) {
                System.out.println("  " + field.getName() + " : " + field.getType().getName());
            }
        }

        // Вывод методов
        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length > 0) {
            System.out.println("Methods:");
            for (Method method : methods) {
                System.out.print("  " + Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getName() + " " + method.getName() + "(");
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    System.out.print(parameterTypes[i].getName());
                    if (i < parameterTypes.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println(")");
            }
        }
    }

    public static void main(String[] args) {

        Class<?> clazz = Person.class;
        printClassInfo(clazz);
    }
}


