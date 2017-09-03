(ns stats.stats
  "Common statistics"
  {:author "Tiago Antao"}
)

(defn mean
  "Mean"
  [seq] (/ (reduce + seq) (count seq)))

(comment
(defn median
  "Median"
  [col] (
  )
)
)
