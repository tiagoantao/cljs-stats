(ns stats.multi
  "Multivariate statistics
  "
  {:author "Tiago Antao"}
  (:require [stats.stats :refer [mean]]
            [stats.sample :refer [nobs]])
  )

(defn- internal-cov [v1 v2 div]
  (let [mean-v1 (mean v1)
        mean-v2 (mean v2)]
    (/ (reduce + (map #(* %1 %2)
                      (map #(- % mean-v1) v1)
                      (map #(- % mean-v2) v2)))
       div)
    ))

(defn cov [v1 v2]
  (internal-cov v1 v2 (nobs v1)))

(defn sample-cov [v1 v2]
  (internal-cov v1 v2 (dec (nobs v1))))

(cov [1 2] [1 2])

(sample-cov [1 2] [1 2])
