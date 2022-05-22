tgh <- function(x) {
  return(tanh(x))
}

tgh_prime <- function(x) {
  return(1 - tanh(x) ^2)
}


relu <- function(x) {
  return(ifelse(x>0,x,0.01*x))
}

relu_prime <- function(x) {
  return(ifelse(x>0,1,0.01))
}
