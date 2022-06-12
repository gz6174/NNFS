import java.util.LinkedList;
import java.util.List;

public class Network {
    private List<Layer> layers;

    public Network() {
        layers = new LinkedList<>();
    }

    public void add(Layer layer) {
        this.layers.add(layer);
    }

    public Matrix predict(Matrix input_data, int resultNcol) throws Exception {
        int samples = input_data.getNrow();
        Matrix result = new Matrix(samples, resultNcol);
        for (int i = 0; i < samples; i++) {
            Matrix output = new Matrix(input_data.getM()[i], 1);
            for (Layer layer : layers) {
                output = layer.forward_propagation(output);
            }
            for (int j = 0; j < resultNcol; j++) {
                result.getM()[i][j] = output.getM()[0][j];
            }
        }
        return result;
    }

    public void fit(Matrix x_train, Matrix y_train, int epochs, double learning_rate) throws Exception {
        int samples = x_train.getNrow();

        for (int i = 0; i < epochs; i++) {
            double err = 0;
            for (int j = 0; j < samples; j++) {
                Matrix output = new Matrix(x_train.getM()[j],1);
                for (Layer layer : layers) {
                    output = layer.forward_propagation(output);
                }
                err += mse(new Matrix(y_train.getM()[j],1), output);

                Matrix error = mse_prime(new Matrix(y_train.getM()[j], 1), output);
                for (int k = layers.size()-1; k >= 0; k--) {
                    error = layers.get(k).backward_propagation(error, learning_rate);
                }
            }
            err /= samples;
            System.out.println("Epoch "+i+", error: "+err);
        }
    }

    private double mse(Matrix y_true, Matrix y_pred) {
        double result = 0;
        for (int i = 0; i < y_true.getNrow(); i++) {
            result += Math.pow(y_true.getM()[i][0] - y_pred.getM()[i][0], 2);
        }
        result = result / y_true.getNrow();
        return result;
    }

    private Matrix mse_prime(Matrix y_true, Matrix y_pred) throws Exception {
        Matrix result = y_true.copy();
        result.sub(y_pred);
        result.mul(2);
        result.mul(1.0/y_true.getNrow());
        return result;
    }
}
