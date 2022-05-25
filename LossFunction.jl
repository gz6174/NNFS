function mse(y_true, y_pred)
    return mean((y_true - y_pred)^2)
end

function mse_prime(y_true, y_pred)
    return 2 * (y_pred - y_true) / length(y_true)
end
 
