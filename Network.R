Network <- setRefClass(
  "Network",
  fields=list(
    layers="list",
    loss="function",
    loss_prime="function"
  ),
  methods=list(
    add = function(layer) {
      layers <<- c(layers, layer)
    },
    use = function(loss, loss_prime) {
      loss <<- loss
      loss_prime <<- loss_prime
    },
    predict = function(input_data) {
      samples <- nrow(input_data)
      result <- matrix(NA, samples)
      for (i in seq.int(samples)) {
        output <- matrix(input_data[i,],1,ncol(input_data))
        for (layer in layers) {
          output <- layer$forward_propagation(output)
        }
        result[i] <- output
      }
      return(result)
    },
    fit = function(x_train, y_train, epochs, learning_rate) {
      samples <- nrow(x_train)
      for (i in seq.int(epochs)) {
        err <- 0
        
        for (j in seq.int(samples)) {
          output <- matrix(x_train[j, ], 1, ncol(x_train))
          
          for (layer in layers) {
            output <- layer$forward_propagation(output)
          } # endfor layers
          
          err <- err + loss(y_train[j, ], output)
          
          error <- loss_prime(y_train[j, ], output)
          
          for (layer in rev(layers)) {
            error <- layer$backward_propagation(error, learning_rate)
          } # endfor rev layers
        } # endfor j
        err <- err / samples
        if (i%%100==0) {
          cat(paste0("epoch ",i,"/",epochs,"\terror=",signif(err,8),"\n"))
        }
      } # endfor i
    } # endfun fit
  )
)
