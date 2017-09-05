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


(defn all-pos [v coll]
  (let [first (some #(= v %) coll)]
    (if (nil? first)
      (list)
      XXXX
     ) 
    ))

(defn rank [v]
  (let [s (sort v)]))


(defn rank-sp
  "Spearman rank correlation"
  [v1 v2]
  (let [r1 (rank v1)
        r2 (rank v2)]
    (pearson r1 r2))
  )
