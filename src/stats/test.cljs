(ns stats.test
  "Statistical Tests
  "
  {:author "Tiago Antao"}
  (:require [stats.stats :refer [std]]
            [stats.multi :refer [cov]])
  )

(defn pearson [v1 v2]
  (/ (cov v1 v2)
     (* (std v1) (std v2))))

(defn all-pos [v vec]
  (if (vector? vec)
    (let [first (.indexOf vec v)]
      (if (= -1 first)
        []
        (loop [pos (inc first) curr [first]]
          (if (= v (get vec pos))
            (recur (inc pos) (conj curr pos))
            curr))))))

(defn rank [v]
  (let [s (into [] (sort v))
        poses (all-pos v s)]))

(defn rank-sp
  "Spearman rank correlation"
  [v1 v2]
  (let [r1 (rank v1)
        r2 (rank v2)]
    (pearson r1 r2))
  )
