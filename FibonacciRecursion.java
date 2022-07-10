import java.math.BigInteger;
import java.util.HashMap;

public class FibonacciRecursion {
    
    // declaring and initializing several constants for recursion
    static final BigInteger baseCaseBoundary = BigInteger.ONE.add(BigInteger.TWO); // 3
    static final BigInteger baseCaseResult = BigInteger.ONE;
    static final BigInteger problemReductionOne = BigInteger.ONE;
    static final BigInteger problemReductionTwo = BigInteger.TWO;
    
    // create hashmap for memoization of fibonacci results
    static HashMap<BigInteger, BigInteger> fibResults = new HashMap<BigInteger, BigInteger>();


    public static BigInteger calculateFibonacci(BigInteger fibonacciNumber) {

        // Catch negative input
        if(fibonacciNumber.compareTo(new BigInteger("0")) == -1){
            return BigInteger.ZERO;
        };
    
        // Check if number is in hasmap. if yes, return the number from the hashmap.
        if (fibResults.get(fibonacciNumber) != null){
            return fibResults.get(fibonacciNumber);
        }

        // handling 0 as input value for fib calculation
        else if (fibonacciNumber.compareTo(new BigInteger("0")) == 0){
            return BigInteger.ZERO;
        }

        // recursion base case handling
        else if (fibonacciNumber.compareTo(baseCaseBoundary) == -1){
            fibResults.put(fibonacciNumber, baseCaseResult);
            return baseCaseResult;
        }

        // recursive call, reducing n by 1 and 2 to converge against the basecase. storing values within the hashmap for memoization.
        else {
           
            fibResults.put(fibonacciNumber, calculateFibonacci(fibonacciNumber.subtract(problemReductionOne)).add(calculateFibonacci(fibonacciNumber.subtract(problemReductionTwo))));
        }
    

        // returning the result grabbed from the hashmap.
        return fibResults.get(fibonacciNumber);

      };

}
