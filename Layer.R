Layer <- 
  setRefClass("Layer",
              fields=list(input="matrix", output = "matrix"),
              methods=list(
                forward_propagation = function() {
                  NA
                },
                backward_propagation = function() {
                  NA
                }
                )
              )

