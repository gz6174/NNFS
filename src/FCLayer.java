public class FCLayer {

    Matrix weights;
    Matrix bias;
    Matrix input;
    Matrix output;

    public FCLayer(int input_size, int output_size) {
        this.weights = new Matrix(input_size, output_size);
        weights.rand();
        this.bias = new Matrix(1, output_size);
        bias.rand();

    }

}
