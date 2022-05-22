source("Layer.R")

ActivationLayer <- setRefClass(
  "ActivationLayer",
  fields = list(input="matrix",
                output="matrix",
                activation="function",
                activation_prime="function"
  ),
  contains = "Layer",
  methods = list(
    initialize = function(act, act_prime) {
      activation <<- act
      activation_prime <<- act_prime
    },
    forward_propagation = function(input_data) {
      input <<- input_data
      output <<- activation(input)
      return(output)
    },
    backward_propagation = function(output_error, learning_rate) {
      return(activation_prime(input) * output_error)
    }
  )
)
