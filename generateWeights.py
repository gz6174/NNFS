import configparser
import numpy as np

parser = configparser.ConfigParser()
parser.read("config.ini")

input_n = parser.getint("Settings", "input_n")
layer_1 = parser.getint("Settings", "layer_1")
layer_2 = parser.getint("Settings", "layer_2")
output_n = parser.getint("Settings", "output_n")

w1 = np.random.random_sample((input_n, layer_1))
b1 = np.random.random_sample((1, layer_1))
w2 = np.random.random_sample((layer_1, layer_2))
b2 = np.random.random_sample((1, layer_2))
w3 = np.random.random_sample((layer_2, output_n))
b3 = np.random.random_sample((1, output_n))

np.savetxt("weights_1.csv", w1, delimiter=",", fmt='%.10f')
np.savetxt("weights_2.csv", w2, delimiter=",", fmt='%.10f')
np.savetxt("weights_3.csv", w3, delimiter=",", fmt='%.10f')
np.savetxt("bias_1.csv", b1, delimiter=",", fmt='%.10f')
np.savetxt("bias_2.csv", b2, delimiter=",", fmt='%.10f')
np.savetxt("bias_3.csv", b3, delimiter=",", fmt='%.10f')
