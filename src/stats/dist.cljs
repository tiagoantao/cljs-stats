(ns stats.dist
  "Statistical distributions"
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
  (PGF [x] "Probability-generating function"))

(defn erf [v] nil) ;XXX To be defined and moved

(defn factorial [n]
    (reduce * (range 1 (inc n)))) ;XXX factorial(!?!?) to be moved

(defn binomial-coef [n k] (/ (factorial n) (*
                                            (factorial k)
                                            (factorial (- n k)) ))) ;XXX to be moved

(defn sum [start end f]
  (reduce + #(f %)  (range start (+ 1 end))))

;Discrete Distributions

(deftype Binomial [n p]
  Distribution
  DiscreteDistribution
  (sample ([d] (random d 1)) nil)
  (PMF [d k] (* (binomial-coef n k) (js.Math/pow p k) (js.Math/pow (- 1 p) (- n k))))
  (CDF [d k] (sum 0 (- k 1) #(* (binomial-coef n %)
                                (js.Math/pow p %)
                                (js.Math/pow (p %) (- n %)))))   ;assuming k integer
  (mean [d] (* n p))
  (median [d] nil) ;XXX
  (mode [d] nil) ;XXX
  (variance [d] (* n p (- 1 p)))
  (skewness [d] (/ (- 1 (* 2 p))
                   (js.Math/sqrt (* n p (- 1 p)))))
  (exkurtosis [d] nil) ;XXX
  (entropy [d] nil) ;XXX
  (MGF [d] nil) ;XXX
  (CF [d] nil) ;XXX
  (fisher-inf [d] nil)) ;XXX



;Continuous Distributions


(deftype Normal [mean variance]
  Distribution
  ContinuousDistribution
  (sample ([d] (random d 1)) ([d nobs]  ([d nobs]
                                         (loop [x1 0 x2 0 r2 0]
                                           (if (and (> r2 0) (< r2 1))
                                             (let [f (js.Math./sqrt (/ (* -2 (js.Math/log r2)) r2))]
                                               (+ mean (* (js.Math/sqrt variance) f))
                                               (let [x1 (- (* 2 (js.Math/random) 1))
                                                     x2 (- (* 2 (js.Math/random) 1))
                                                     r2 (+ (* x1 x1) (* x2 x2))]
                                                 (recur x1 x2 r2)
                                                 )))))))
  (PDF [d x] (/ 1 (* (.sqrt js/Math (* 2 js/Math.PI variance))
                     (.pow js/Math js/Math.E
                           (- (/ (.pow js/Math (- x variance) 2)
                                 (* 2 variance variance)))))))
  (CDF [d x] (/ (+ 1 (erf (/ (- x mean)
                             (* variance (.srqt js/Math 2))))))
    2)
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
 
