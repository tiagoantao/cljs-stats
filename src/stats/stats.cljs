(ns stats.stats
  "Common statistics"
  {:author "Tiago Antao"}
  (:require [stats.sample :refer [nobs Observations Sample]])
)

(defn mean
  "Mean"
  [v] (/ (reduce + (seq v)) (nobs v)))
;This seq should be removed

(defn variance
  [v] (let [mean-v (mean v)]
        (mean (map #(.pow js/Math (- % mean-v) 2) v))))

(defn std
  [v] (js/Math.sqrt (variance v)))

(defn median
  "Median"
  [v] (let [srt (into [] (sort v) )
            half (/ (count v) 2)]
        (if (int? half)
          (/ (+ (nth srt (- half 1))
                (nth srt half)) 2)
          (nth srt half)
        ))
)
