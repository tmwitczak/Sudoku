public class PascalTriangle {

    int[][] triangle;

    public void compute(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("Input must be a non-negative number.");
        }

        triangle = new int[n][];
        for (int i = 0; i < n; i++) {
            triangle[i] = new int[i + 1];
        }
        for (int i = 0; i < n; i++) {
            triangle[i][0] = 1;
            triangle[i][i] = 1;

            for (int j = 0; j < i - 1; j++) {
                triangle[i][j + 1] = triangle[i - 1][j] + triangle[i - 1][j + 1];
            }
        }

        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(triangle[i][j] + " ");
            }
            System.out.println();
        }*/
    }

    public int[][] getTriangle() {
        return triangle;
    }
}
