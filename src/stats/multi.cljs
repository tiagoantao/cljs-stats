(ns stats.multi
  "Multivariate statistics
  "
  {:author "Tiago Antao"}
  (:require [stats.stats :refer [mean]])
  )

(defn cov [v1 v2]
  (let [mean-v1 (mean v1)
        mean-v2 (mean v2)]
    (/ (reduce + (map * (map (- v1 mean-v1))
                      (- v2 mean-v2)))
       (dec (nobs v1)))
  ))

