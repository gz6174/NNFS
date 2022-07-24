

public class Main {
    public static void main(String[] args) {
        double[][] x_train = new double[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[][] y_train = new double[][]{{0}, {1}, {1}, {0}};

        print(x_train);
        System.out.println();
        print(y_train);
        System.out.println();

        Matrix X = new Matrix(x_train);
        Matrix Y = new Matrix(y_train);

        Network network = new Network();
        network.add(new FCLayer(2,3));
        network.add(new ActivationLayer());
        network.add(new FCLayer(3,1));
        network.add(new ActivationLayer());

        try {
            network.fit(X, Y, 10, 0.1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void print(double[][] a) {
        for (double[] doubles : a) {
            for (double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }
}
