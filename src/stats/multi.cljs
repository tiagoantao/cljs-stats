(ns stats.multi
  "Multivariate statistics
  "
  (:require [stats.stats :refer [mean]])
  {:author "Tiago Antao"}
  )

(defn cov [v1 v2]
  (let [mean-v1 (mean v1)
        mean-v2 (mean v2)]
    (/ (reduce + (map * (map (- v1 mean-v1))
                      (- v2 mean-v2)))
       (- n 1))
  ))

