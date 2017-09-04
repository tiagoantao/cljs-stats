(ns stats.test
  "Statistical Tests
  "
  (:require [stats.stats :refer std]
            [stats.multi :refer cov])
  {:author "Tiago Antao"}
  )

(defn pearson [v1 v2]
  (/ (cov v1 v2)
     (* (std v1) (std v2))))
