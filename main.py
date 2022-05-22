import numpy as np
import pandas as pd

train = pd.read_csv("sample/sample.csv", delimiter=",")

train_x, train_y = train.drop("label", axis=1), train["label"]

weights = pd.read_csv("sample/sample_weights.csv", delimiter=",", header=None)

test = np.dot(train_x.values, weights)[:, 0]
