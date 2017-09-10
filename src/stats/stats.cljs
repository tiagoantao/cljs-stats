(ns stats.stats
  "Common statistics"
  {:author "Tiago Antao"}
  (:require [stats.sample :refer [nobs Observations Sample]])
)

(defn mean
  "Mean"
  [v] (/ (reduce + (seq v)) (nobs v)))
;This seq should be removed

(defn mean1
  "Mean but divided by obs - 1"
  [v] (/ (reduce + (seq v)) (dec (nobs v))))
;This seq should be removed

(defn variance
  [v] (let [mean-v (mean v)]
        (mean1 (map #(.pow js/Math (- % mean-v) 2) v))))

(defn pop-variance
  [v] (let [mean-v (mean v)]
        (mean (map #(.pow js/Math (- % mean-v) 2) v))))

(defn std
  [v] (js/Math.sqrt (variance v)))

(defn pop-std
  [v] (js/Math.sqrt (pop-variance v)))

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
