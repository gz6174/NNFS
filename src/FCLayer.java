public class FCLayer extends Layer {

    Matrix weights;
    Matrix bias;

    public FCLayer(int input_size, int output_size) {
        this.weights = new Matrix(input_size, output_size);
        weights.rand();
        weights.add(-0.5);
        this.bias = new Matrix(1, output_size);
        bias.rand();
        bias.add(-0.5);
    }

    public Matrix forward_propagation(Matrix input_data) throws Exception {
        this.input = input_data.copy();
        this.output = this.input.dot(this.weights);
        this.output.add(this.bias);
        return this.output;
    }

    public Matrix backward_propagation(Matrix output_error, double learning_rate) throws Exception {
        Matrix input_error = output_error.dot(this.weights.t());
        Matrix weights_error = this.input.t().dot(output_error);
        weights_error.mul(-learning_rate);
        weights.add(weights_error);
        output_error.mul(-learning_rate);
        bias.add(output_error);
        return input_error;
    }

    public Matrix getOutput() {
        return this.output;
    }
}
