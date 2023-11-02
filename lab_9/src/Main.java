import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Main{
    public static void main(String[] args){
        try{
            String className = "Person";
            String methodName = "introduce";

            Class <?> clazz = Class.forName(className);

            Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
            Object instance = constructor.newInstance("Andrew", 20);

            Method method = clazz.getMethod(methodName);

            method.invoke(instance);

        }catch (ClassNotFoundException e){
            System.err.println("Class not found: " + e.getMessage());

        }catch (NoSuchMethodException e){
            System.err.println("Method not found: " + e.getMessage());

        }catch (Exception e){
            System.err.println("An error has occured: " + e.getMessage());

        }
    }
}