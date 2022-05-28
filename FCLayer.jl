using LinearAlgebra
using Statistics
include("Activation.jl")
include("LossFunction.jl")

mutable struct FCLayer
    input
    output
    weights
    bias
    FCLayer(x, y) = new(nothing, nothing, rand(x, y).-0.5, rand(1, y).-0.5)
end

mutable struct ActivationLayer
    input
    output
    activation
    activation_prime
    ActivationLayer(L, f, g) = new(L.output, nothing, f, g)
end

function forward_propagation(L::FCLayer, input_data::Matrix)
    L.input = input_data
    return input_data * L.weights .+ L.bias
end

function forward_propagation(L::ActivationLayer, input_data::Matrix)
    L.input = input_data
    L.output = L.activation(L.input)
    return L.output
end

function backward_propagation(L::FCLayer, output_error::Matrix, learning_rate)
    input_error = output_error * L.weights'
    weights_error = L.input' * output_error
    L.weights = L.weights - learning_rate * weights_error
    L.bias = L.bias .- learning_rate * output_error
    return input_error
end

function backward_propagation(L::ActivationLayer, output_error::Matrix, learning_rate)
    return L.activation_prime(L.input) .* output_error
end

function fit(layers::Matrix,
    x::Matrix, y::Matrix,
    learning_rate::Float64,
    epochs = 1000)
    samples = size(x)[1]
    for i in 1:epochs
        e = 0
        for j in 1:samples
            output = copy(x[j,:])[:, :]
            for L in layers
                output = forward_propagation(L, output)
            end
            output_error = mse_prime(y, output)
            for L in reverse(layers)
                output_error = backward_propagation(L, output_error, learning_rate)
            end
        end
        print("Iter: ",string(i),", ",mse(y, output),'\n')
    end
end

function predict(layers::Matrix, x::Matrix)
    output = copy(x)
    for L in layers
        output = forward_propagation(L, output)
    end
    return output
end
