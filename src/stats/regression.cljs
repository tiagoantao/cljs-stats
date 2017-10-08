(ns stats.regression
  "Regression analysis
  "
  {:author "Tiago Antao"}
  (:require [stats.stats :refer [mean std]]
            [stats.test :refer [pearson]])
  )

(defn sse
  "Sum of squared errors of prediction"
  [y ye]
  (reduce + (map #(.pow js/Math (- %1 %2) 2) y ye ))
  )

(defn linear [x y]
  (let [mx (mean x)
        my (mean y)
        r (pearson x y)
        b (/ (reduce + (map #(* (- %1 mx) (- %2 my)) x y))
             (reduce + (map #(.pow js/Math (- % mx) 2) x) ))
        a (- my (* b mx))
        mx2 (mean (map #(* % %) x))
        my2 (mean (map #(* % %) y))
        mxy (mean (map #(* %1 %2) x y))
        rxy (/ (- mxy (* mx my))
               (.sqrt js/Math (* (- mx2 (* mx mx))
                                 (- my2 (* my my)))))
        sse (sse y (map #(+ (*) b) x))]
    {:a a :b b :sse sse :rxy rxy :R2 (* r r)}
  ))

(prn 1111 (pearson [0 1] [0 1]))
(prn 1111 (linear [0 1 2 3] [0 1.1 2 4]))

