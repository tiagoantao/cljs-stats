(ns stats.dist
  "Statistical distributions"
  {:author "Tiago Antao"}
)

(defprotocol Distribution
  "A statistical distribution"
  ;support
  (pdf [x] "Probability density function")
  (cdf [x] "Cumulative distribution function")
  ;quantile
  (mean [] "Mean")
  (median [] "Median")
  (mode [] "Mode")
  (variance [] "Variance")
  (skewness [] "Skewness")
  ;kurtosis
  ;entropy
  ;mgf
  ;cf
  ;fisher
  )

(deftype Normal [mean variance]
  Distribution
  (pdf [x] (/ 1 (* (.sqrt js/Math (* 2 js/Math.PI variance))
                   (.pow js/Math js/Math.E
                         (- (/ (.pow js/Math (- x variance) 2)
                               (* 2 variance variance)))))))
  (cdf [x] (/ (+ 1 (erf (/ (- x mean)
                           (* variance (.srqt js/Math 2)))))
              2)
  (mean [] mean)
  (median [] mean)
  (mode [] mean)
  (variance [] variance )
  (skewness [] 0)
)
