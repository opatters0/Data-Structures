import org.junit.Test;

public class Tester
{
    @Test
    public void main() {
        //first test
        String first = "99999";
        String second = "999";
        addLargeNumbers(first,second); // Should print 100,998

        //second test
        first = "89";
        second = "9876";
        addLargeNumbers(first,second); // Should print 9,965

        //third test
        first = "2147483647";
        second = "2147483647";
        addLargeNumbers(first,second); // Should print 4,294,967,294
    }

    /**
     * Adds large numbers
     * @Author Owen Patterson
     * @param s1 a string representing an integer.
     * @param s2 a string representing an integer.
     * @return StackInterface<Integer> The combined sum.
     *
     * This code is 24 lines long and fits on my monitor.
     * 1. Initializes three LinkedStacks, one for s1, one for s2, and one for the result of the addition.
     * 2. Iterates through each character in the strings, casting to integer, and pushes it onto the stack.
     * 3. Iterates while at least one of the stacks is empty.
     * 3a. Pops off the top of any !empty stack to avoid underflow
     * 3b. Adds the result along with any carry.
     * 3c. Gets tens and ones place values, pushing ones place and storing tens place
     * 4. Adds any carry left after the loop.
     * 5. Initializes a char array based on the length of the sum.
     * 6. Iterates through the resultStack contents storing in the charArray
     * 7. Prints array.
     **/
    public static void addLargeNumbers(String s1, String s2) {
        StackInterface<Integer> firstStack = new LinkedStack<Integer>();
        StackInterface<Integer> secondStack = new LinkedStack<Integer>();
        StackInterface<Integer> resultStack = new LinkedStack<Integer>();
        for (int i = 0; i < s1.length(); i++) {firstStack.push((s1.charAt(i) - '0'));}
        for (int i = 0; i < s2.length(); i++) {secondStack.push((s2.charAt(i) - '0'));}
        Integer tens = 0;
        while (!firstStack.isEmpty() || !secondStack.isEmpty()) {
            int i0 = 0; int i1 = 0;
            if (!firstStack.isEmpty()) { i0 = firstStack.pop(); }
            if (!secondStack.isEmpty()) { i1 = secondStack.pop(); }
            Integer additionResult = i0 + i1 + tens; Integer ones =  additionResult % 10;
            resultStack.push(ones); tens = additionResult / 10;
        }
        boolean extraPlace = false;
        if (tens != 0) { resultStack.push(tens); extraPlace = true;}
        int maxLength = s1.length(); int pos = 0;
        if (s1.length() < s2.length()) { maxLength = s2.length(); }
        if (extraPlace) { maxLength = maxLength + 1; }
        char[] array = new char[maxLength];
        while (!resultStack.isEmpty()) {
            array[pos] = resultStack.pop().toString().charAt(0); pos++;
        }
        System.out.print( s1 + " + " + s2 + " = ");
        System.out.println(array);
    }
}
