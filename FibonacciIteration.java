import java.math.BigInteger;
import java.util.HashMap;

public class FibonacciIteration{

    // declaration of several own constants
    private final static BigInteger bigIntZero = BigInteger.ZERO;
    private final static BigInteger bigIntOne = BigInteger.ONE;
    private final static BigInteger bigIntTwo = BigInteger.TWO;
    private final static BigInteger bigIntThree = bigIntOne.add(bigIntTwo);

    // declaration of status values to keep track of input type.
    private static boolean negativeInput = false;
    private static boolean evenInput = false;

    // initialize hashmap with first values. used for memoization to handle big inputs.
    private static HashMap<BigInteger, BigInteger> fibResults = new HashMap<BigInteger, BigInteger>(){{
                put(bigIntZero, bigIntZero);
                put(bigIntOne, bigIntOne);
                put(bigIntTwo, bigIntOne);
            }};


    public static BigInteger calculateFibonacci(BigInteger fibonacciNumber){

        // check if input is less than zero and even
        if (fibonacciNumber.compareTo(bigIntZero) < 0){

            // toggle boolean to true
            negativeInput = !negativeInput;

            // toogle even input to true if value is even
            if (fibonacciNumber.mod(bigIntTwo).compareTo(bigIntZero) == 0){
                evenInput = !evenInput;
            };

            // turn n into positive value to calculate fibonacci values as usual.
            fibonacciNumber = fibonacciNumber.multiply(new BigInteger("-1"));
        };

        // iterate and use hashmap / memoization to calculate the fibonacci values.
        for (BigInteger i = bigIntThree; i.compareTo(fibonacciNumber) <= 0; i = i.add(BigInteger.ONE)){
            
            // grab the fibonacci values n -1 and n - 2
            BigInteger firstValue = fibResults.get(i.subtract(bigIntOne));
            BigInteger secondValue = fibResults.get(i.subtract(bigIntTwo));

            // store calculated new value here
            BigInteger newValue = (firstValue).add(secondValue);

            // put new value into the hashmap
            fibResults.put(i, newValue);

            // removes the entry with key value "current value of i - 3" to keep the memory usage low.
            // hasmap only contains three values all the time.
            fibResults.remove(i.subtract(bigIntThree));
        }

        // special case: if input 'n' was negative and even, return negative fibonacci number.
        if (negativeInput && evenInput){
            negativeInput = false;
            evenInput = false;

            return fibResults.get(fibonacciNumber).multiply(new BigInteger("-1"));
        }
        
        // since both booleans are static variables, they are resetted here
        negativeInput = false;
        evenInput = false;

        return fibResults.get(fibonacciNumber);
      
    };

    public static void main(String[] args) {
        System.out.println(FibonacciIteration.calculateFibonacci(new BigInteger("5")));
        System.out.println("keys: " + fibResults.keySet()+ " | values: " + fibResults.values());
    }
}