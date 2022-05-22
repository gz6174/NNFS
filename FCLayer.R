source("Layer.R")

FCLayer <- setRefClass(
  "FCLayer",
  fields = list(input_size="numeric", 
                output_size="numeric", 
                weights="matrix",
                bias="matrix",
                input_error="matrix",
                weights_error="matrix"
  ),
  contains = "Layer",
  methods = list(
    initialize = function(input_size, output_size) {
      input_size <<- input_size
      output_size <<- output_size
      weights <<-  matrix(runif(input_size * output_size, -0.5, 0.5),input_size, output_size)
      bias <<- matrix(runif(output_size, -0.5, 0.5), 1, output_size)
    },
    forward_propagation = function(input_data) {
      input <<- input_data
      output <<- input %*% weights + rep(bias, each=nrow(input))
      return(output)
    },
    backward_propagation = function(output_error, learning_rate) {
      input_error <<- output_error %*% t(weights)
      weights_error <<- t(input) %*% output_error
      weights <<- weights - learning_rate * weights_error
      bias <<- bias - learning_rate * output_error
      return(input_error)
    }
  )
)
