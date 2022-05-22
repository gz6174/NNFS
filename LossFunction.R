mse <- function(y_true, y_pred) {
  return(mean((y_true - y_pred)^2))
}


mse_prime <- function(y_true, y_pred) {
  return(2 * (y_pred - y_true) / length(y_true))
}
