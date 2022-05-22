import configparser
import numpy as np

parser = configparser.ConfigParser()
parser.read("config.ini")

n = parser.getint("Settings", "n")
w_n = parser.getint("Settings", "layer_n")
w_m = parser.getint("Settings", "layer_m")

res = np.random.random_sample((w_n, w_m))

np.savetxt("weights.csv", res, delimiter=",", fmt='%.10f')
