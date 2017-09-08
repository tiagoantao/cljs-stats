(ns stats.stats
  "Common statistics"
  {:author "Tiago Antao"}
  (:require [stats.sample :refer [nobs Observations]])
)

(defn mean
  "Mean"
  [v] (/ (reduce + v) (nobs v)))

(defn variance
  [v] (let [mean-v (mean v)] (mean (map #(js/Math.pow (% - mean-v) 2)))))

(defn std
  [v] (js/Math.sqrt (variance v)))

(defn median
  "Median"
  [v] (let [srt (into [] (sort v) )
            half (/ (count v) 2)]
        (if (int? half)
          (/ (+ (nth srt (- half 0.5))
                (nth srt (+ half 0.5))) 2))
        (nth srt half)
        )
)

(median [1 2 3 4 5])
