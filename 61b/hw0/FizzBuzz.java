public class FizzBuzz {

    public static void main (String[] args) {
        int start = 1;
        int end = 20;
        while (start <= end) {
            if (start % 3 == 0 && start % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (start % 5 == 0) {
                System.out.println("Buzz");
            } else if (start % 3 == 0) {
                System.out.println("Fizz");
            } else {
                System.out.println(start);
            }
            start++;
        }
    }
}
