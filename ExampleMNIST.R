source("Network.R")
source("FCLayer.R")
source("ActivationLayer.R")
source("Activation.R")
source("LossFunction.R")
source("helpers.R")

library(data.table)
setDTthreads(8)

train <- fread("mnist/mnist_train.csv")
test <- fread("mnist/mnist_test.csv")
x_train <- train[,-"label"]
y_train <- train[,"label"]
x_test <- test[,-"label"]
y_test <- test[,"label"]

x_train <- as.matrix(x_train / 255)
y_train <- to_categorical(y_train)
x_test <- as.matrix(x_test / 255)
y_test <- to_categorical(y_test)

rm(train, test)

net = Network()
net$add(FCLayer(28 * 28, 100))
net$add(ActivationLayer(tgh, tgh_prime))
net$add(FCLayer(100, 50))
net$add(ActivationLayer(tgh, tgh_prime))
net$add(FCLayer(50, 10))
net$add(ActivationLayer(tgh, tgh_prime))
