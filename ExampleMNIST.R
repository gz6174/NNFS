source("Network.R")
source("FCLayer.R")
source("ActivationLayer.R")
source("Activation.R")
source("LossFunction.R")
source("helpers.R")

library(data.table)
setDTthreads(12)

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

net$use(mse, mse_prime)

t <- proc.time()
net$fits(x_train, y_train, epochs=10, learning_rate=0.1, batch_size=1000)
t <- proc.time() - t
print(t[3])
#net.fits2(x_train, y_train, epochs=100, learning_rate=0.1, batch_size=1000)


out = net.predict(x_test[0:3])
print("\n")
print("predicted values : ")
print(out, end="\n")
print("true values : ")
print(y_test[0:3])
