to_categorical <- function(y) {
  y <- as.matrix(y)
  num_classes <- max(y)
  categorical <- matrix(0, nrow=nrow(y), ncol=num_classes)
  for (i in seq.int(num_classes)) {
    categorical[i==y, i] <- 1
  }
  return(categorical)
}
