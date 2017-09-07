(ns stats.dist
  "Probability distributions"
  {:author "Tiago Antao"}
)


(defprotocol Distribution
  "A continuous probability distribution"
  (sample [d] [d nobs] "Generates random values according to the distribution")
  ;support
  (CDF [d x] "Cumulative distribution function")
  ;quantile
  (mean [d] "Mean")
  (median [d] "Median")
  (mode [d] "Mode")
  (variance [d] "Variance")
  (skewness [d] "Skewness")
  (exkurtosis [d] "Excess kurtosis")
  (entropy [d] "Entropy")
  (MGF [d] "Moment-generating function")
  (CF [d] "Characteristic function")
  (fisher-inf [d] "Fisher-information"))


(defprotocol ContinuousDistribution
  "A continuous probability distribution"
  (PDF [d x] "Probability density function"))

(defprotocol DiscreteDistribution
  "A discrete probability distribution"
  (PMF [d x] "Probability mass function")
  (PGF [d x] "Probability-generating function"))

(defn erf [v] nil) ;XXX To be defined and moved

(defn factorial [n]
    (reduce * (range 1 (inc n)))) ;XXX factorial(!?!?) to be moved

(defn binomial-coef [n k] (/ (factorial n) (*
                                            (factorial k)
                                            (factorial (- n k)) ))) ;XXX to be moved

(defn sum [start end f]
  (reduce + #(f %)  (range start (+ 1 end))))

;Discrete Distributions

(defrecord Binomial [n p]
  Distribution
  (sample [d] (sample d 1))
  (sample [d nobs] nil) ; XXX
  (CDF [d k] (sum 0 (- k 1) #(* (binomial-coef (:n d) %)
                                (.pow js.Math (:p d) %)
                                (.pow js.Math ((:p d) %) (- (:n d) %)))))   ;assuming k integer
  (mean [d] (* n p))
  (median [d] nil) ;XXX
  (mode [d] nil) ;XXX
  (variance [d] (* n p (- 1 p)))
  (skewness [d] (/ (- 1 (* 2 p))
                   (.sqrt js.Math (* n p (- 1 p)))))
  (exkurtosis [d] nil) ;XXX
  (entropy [d] nil) ;XXX
  (MGF [d] nil) ;XXX
  (CF [d] nil) ;XXX
  (fisher-inf [d] nil)) ;XXX

(extend-protocol DiscreteDistribution
  Binomial
  (PMF [d k] (* (binomial-coef (:n d) k) (.pow js.Math (:p d) k)
                (.pow js.Math (- 1 (:p d)) (- (:n d) k))))
  (PGF [d z] (.pow js.Math (+ (- 1 (:p d)) (* (:p d) z)) (:n d)))
)

;Continuous Distributions


(defrecord Normal [mean variance]
  Distribution
  (sample [d] (sample d 1))
  (sample [d nobs] 
    (loop [x1 0 x2 0 r2 0]
      (if (and (> r2 0) (< r2 1))
        (let [f (.sqrt js.Math (/ (* -2 (.log js.Math r2)) r2))]
          (+ mean (* (.sqrt js.Math variance) f))
          (let [x1 (- (* 2 (.random js.Math) 1))
                x2 (- (* 2 (.random js.Math) 1))
                r2 (+ (* x1 x1) (* x2 x2))]
            (recur x1 x2 r2)
            )))))
  (CDF [d x] (/ (+ 1 (erf (/ (- x mean)
                             (* variance (.srqt js/Math 2)))))
                2))
  (mean [d] mean)
  (median [d] mean)
  (mode [d] mean)
  (variance [d] variance)
  (skewness [d] 0)
  (exkurtosis [d] 0)
  (entropy [d] nil) ;XXX
  (MGF [d] nil) ;XXX
  (CF [d] nil) ;XXX
  (fisher-inf [d] nil)) ;XXX


(extend-protocol ContinuousDistribution
  Normal
  (PDF [d x] (/ 1 (* (.sqrt js/Math (* 2 js/Math.PI variance))
                     (.pow js/Math js/Math.E
                           (- (/ (.pow js/Math (- x variance) 2)
                                 (* 2 variance variance))))))))
