using LinearAlgebra

mutable struct FCLayer
    input
    weights
    bias
    FCLayer(x, y) = new(nothing, rand(x, y), rand(1, y))
end

mutable struct ActivationLayer
    input
    output
    activation
    activation_prime
    ActivationLayer(f, g) = new(nothing, nothing, f, g)
end


function forward_propagation(L::FCLayer, input_data::Matrix)
    input_data * L.weights .+ L.bias
end

function forward_propagation(L::ActivationLayer, input_data)
    L.input = input_data
    L.output = L.activation(L.input)
    return L.output
end

function backward_propagation(L:FCLayer, output_error::Matrix, learning_rate)
    input_error = output_error * L.weights'
    weights_error = L.input' * output_error
    L.weights = L.weights - learning_rate * weights_error
    L.bias = L.bias - learning_rate * output_error
    return input_error
end

function backward_propagation(L::ActivationLayer, output_error::Matrix)
    return L.activation_prime(L.input) * output_error
end
