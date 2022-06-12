

public class Main {
    public static void main(String[] args) {
        int[][] m1 = new int[2][3];
        fillSeq(m1);
        Matrix M1 = new Matrix(m1);

        int[][] m2 = new int[3][4];
        fillSeq(m2);
        Matrix M2 = new Matrix(m2);


        System.out.println(M1);
        System.out.println();
        System.out.println(M2);
        System.out.println();
        try {
            Matrix M3 = M1.dot(M2);
            System.out.println(M3);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void fillSeq(int[][] M) {
        int k = 1;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                M[i][j] = k++;
            }
        }
    }
}
