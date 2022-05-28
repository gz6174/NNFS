include("FCLayer.jl")

using CSV
using DataFrames

train = CSV.read("mnist/mnist_train.csv", DataFrame)
test = CSV.read("mnist/mnist_test.csv", DataFrame)

train = Matrix(train)
test = Matrix(test)

y_train = train[:,1][:, :]
x_train = train[:,2:end] ./255

y_test = test[:,1][:, :]
x_test = test[:,2:end] ./255

train = nothing
test = nothing

function to_categorical(y::Matrix)
    num_classes = findmax(y)[1] + 1
    categorical = zeros(Int8, size(y)[1], num_classes)
    categorical = y.==1
    for i in 2:num_classes
        categorical = hcat(categorical, y.==i)
    end
    return Matrix{Int64}(categorical)
end

y_train = to_categorical(y_train)
y_test = to_categorical(y_test)

L1 = FCLayer(28*28, 100)
L2 = ActivationLayer(L1, tgh, tgh_prime)
L3 = FCLayer(100, 50)
L4 = ActivationLayer(L3, tgh, tgh_prime)
L5 = FCLayer(50, 10)
L6 = ActivationLayer(L5, tgh, tgh_prime)

net = [L1 L2 L3 L4 L5 L6]

fit(net, x_train, y_train, 0.1, 10)
y_pred = predict(net, x_train)
mse(y_train, y_pred)
mse_prime(y_train, y_pred)
