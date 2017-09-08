(ns stats.stats
  "Common statistics"
  {:author "Tiago Antao"}
  (:require [stats.sample :refer [nobs Observations]])

)

(defn mean
  "Mean"
  [v] (/ (reduce + seq) (nobs v)))

(defn variance
  [v] (let [mean-v (mean v)] (mean (map #(js/Math.pow (% - mean-v) 2)))))

(defn std
  [v] (js/Math.sqrt (variance v)))

(comment
(defn median
  "Median"
  [v] (
  )
)
)

