to_categorical <- function(y) {
  y <- as.matrix(y)
  num_classes <- max(y)+1
  categorical <- matrix(0, nrow=nrow(y), ncol=num_classes)
  for (i in seq.int(0, num_classes - 1)) {
    categorical[i==y, i] <- 1
  }
  return(categorical)
}
