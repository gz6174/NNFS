from Network import Network
from FCLayer import FCLayer
from ActivationLayer import ActivationLayer
from Activation import tanh, tanh_prime
from LossFunction import mse, mse_prime
from helpers import to_categorical

import numpy as np
import pandas as pd

train = pd.read_csv("mnist/mnist_train.csv", dtype="uint8")
x_train = np.array(train.drop("label", axis=1))
y_train = np.array(train["label"])
test = pd.read_csv("mnist/mnist_test.csv", dtype="uint8")
x_test = np.array(test.drop("label", axis=1))
y_test = np.array(test["label"])

x_train = x_train.reshape(x_train.shape[0], 1, 28 * 28)
x_train = x_train.astype('float32')
x_train /= 255
y_train = to_categorical(y_train)

x_test = x_test.reshape(x_test.shape[0], 1, 28 * 28)
x_test = x_test.astype('float32')
x_test /= 255
y_test = to_categorical(y_test)

# Network
net = Network()
net.add(FCLayer(28 * 28, 100))
net.add(ActivationLayer(tanh, tanh_prime))
net.add(FCLayer(100, 50))
net.add(ActivationLayer(tanh, tanh_prime))
net.add(FCLayer(50, 10))
net.add(ActivationLayer(tanh, tanh_prime))

net.use(mse, mse_prime)
net.fit(x_train, y_train, epochs=10, learning_rate=0.1)
#net.fits2(x_train, y_train, epochs=100, learning_rate=0.1, batch_size=1000)


out = net.predict(x_test[0:3])
print("\n")
print("predicted values : ")
print(out, end="\n")
print("true values : ")
print(y_test[0:3])

