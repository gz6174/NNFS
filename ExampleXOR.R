source("Network.R")
source("FCLayer.R")
source("ActivationLayer.R")
source("Activation.R")
source("LossFunction.R")

# training data
x_train <- matrix(rbind(c(0,0),c(0,1),c(1,0),c(1,1)),4,2)
y_train <- matrix(c(0,1,1,0), 4, 1)

# network
net = Network()
net$add(FCLayer(2, 3))
net$add(ActivationLayer(relu, relu_prime))
net$add(FCLayer(3, 1))
net$add(ActivationLayer(relu, relu_prime))

# train
net$use(mse, mse_prime)
net$fit(x_train, y_train, epochs=1000, learning_rate=0.1)

# test
out <- net$predict(x_train)
print(out)
