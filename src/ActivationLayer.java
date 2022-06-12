public class ActivationLayer extends Layer {

    public ActivationLayer() {

    }

    public Matrix forward_propagation(Matrix input_data) throws Exception {
        this.input = input_data.copy();
        this.output = activation(this.input);
        return this.output;
    }

    public Matrix backward_propagation(Matrix output_error, double learning_rate) throws Exception {
        Matrix result = activation_prime(this.input);
        return result.dot(output_error);
    }

    public Matrix getOutput() {
        return this.output;
    }

    private Matrix activation(Matrix N) {
        Matrix result = N.copy();
        for (int i = 0; i < result.getNrow(); i++) {
            for (int j = 0; j < result.getNcol(); j++) {
                result.getM()[i][j] = Math.tanh(result.getM()[i][j]);
            }
        }
        return result;
    }

    private Matrix activation_prime(Matrix N) {
        Matrix result = N.copy();
        for (int i = 0; i < result.getNrow(); i++) {
            for (int j = 0; j < result.getNcol(); j++) {
                result.getM()[i][j] = 1 - Math.pow(Math.tanh(result.getM()[i][j]),2);
            }
        }
        return result;
    }
}
