import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;

public class SimpleLambda {
    
    // a lambda function with one input and one output
    private static Function<Integer, Integer> doubleFunction = x -> 2*x;
    // this is equivalent to the following:
    // private int doubleFunction(int x) {
    //     return 2 * x;
    // }

    // if you want more inputs, you need to define an interface for it
    // the first few type parameters will be inputs, and the last type parameter is the output
    // Note, the one input function used previously is defined by Java by default
    @FunctionalInterface
    private static interface FunctionWithTwoInputs<X, Y, R> {
        R apply(X x, Y y);
    }

    private static FunctionWithTwoInputs<Integer, Integer, Integer> multiplyFunction = (x, y) -> x * y;
    // the above interface + function declaration is equivalent to the following:
    // private int multiplyFunction(int x, int y) {
    //     return x * y;
    // }


    public static void main(String[] args) {
        // to call lambda functions in Java, you need to use its apply method
        int doubledValue = doubleFunction.apply(2);
        System.out.println(String.format("doubled value: %d", doubledValue));

        int multipliedValue = multiplyFunction.apply(2, 3);
        System.out.println(String.format("multiplied value: %d", multipliedValue));

        // now some built in lambda's for list

        List<Integer> myList = Arrays.asList(5, 6, 7, -1, 3, 8, 4, 10, 2, 1);

        myList.stream()
        .filter(x -> x > 2) // keep if the value is greater than 2
        .sorted((x, y) -> x - y) // for any two values x, y in the list, place x in front of y if x - y < 0
        .map(x -> doubleFunction.apply(x)) // for all values in the sorted list, apply the double function
        .forEach(x -> System.out.println(x)); // print out every single elements of the doubled values in the stream
        // note that foreach is a terminal operation, we cannot modify anything on the stream after this
        
        // if you want the result to be kept in a new list
        List<Integer> result = myList.stream()
        .filter(x -> x > 2)
        .sorted((x, y) -> x - y)
        .map(x -> doubleFunction.apply(x))
        .collect(Collectors.toList());

        System.out.println(result);
    }
}
