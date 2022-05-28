include("FCLayer.jl")

x = [0 0; 0 1; 1 0; 1 1]
y = [1.0; 0.0; 0.0; 1.0][:, :]

L1 = FCLayer(2, 3)
L2 = ActivationLayer(L1, tgh, tgh_prime)
L3 = FCLayer(3, 1)
L4 = ActivationLayer(L3, tgh, tgh_prime)
layers = [L1 L2 L3 L4]

fit(layers, x, y, 0.5, 1000)
out = predict(layers, x)
print(out)
