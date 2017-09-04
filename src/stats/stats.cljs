(ns stats.stats
  "Common statistics"
  {:author "Tiago Antao"}
)

(defn mean
  "Mean"
  [v] (/ (reduce + seq) (nobs v)))

(comment
(defn median
  "Median"
  [v] (
  )
)
)

(defn var
  [v] (let [mean-v (mean v)] (mean (map #(js/Math.pow (% - mean-v) 2)))))
