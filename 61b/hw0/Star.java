public class Star {

    public static void main (String[] args) {
        int row = 0;
        int col = 0;
        int SIZE = 10;
        while (row < SIZE) {
            while (col < row) {
                System.out.print("*");
                col++;
            }
            System.out.println("*");
            col = 0;
            row++;
        }
    }
}
