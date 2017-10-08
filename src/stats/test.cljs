(ns stats.test
  "Statistical Tests
  "
  {:author "Tiago Antao"}
  (:require [stats.sample :refer [nobs]]
            [stats.stats :refer [mean std variance]]
            [stats.multi :refer [cov]])
  )

(defn t-test-one [v miu]
  (let [m (mean v)
        s (std v)]
    (/ (- m miu) (/ s (.sqrt js/Math (nobs v))))))

(defn t-test [v1 v2]
  (let [m1 (mean v1)
        m2 (mean v2)
        sp (.sqrt js/Math (/
                           (+ (* (- (nobs v1) 1) (variance v1))
                              (* (- (nobs v1) 1) (variance v1)))
                           (+ (nobs v1) (nobs v2) -2)))]
    (/
     (- (mean v1) (mean v2))
     (* sp (.sqrt js/Math (+ (/ 1 (nobs v1) (nobs v2) )))))
   ))


(defn t-test-welch [v1 v2])

(defn t-test-pair [v1 v2]
  (let [m1 (mean v1)
        m2 (mean v2)
        n (nobs v1) ;= to v2
        dif1 (map #(% - m1) v1)
        dif2 (map #(% - m2) v2)]
    (* (- m1 m2)
       (.sqrt js/Math (/ (* n (dec n))
                         (reduce +
                                 (map #(.pow js/Math (- %1 %2) 2)
                                 dif1 dif2)))))
    ))
  
;correlations 

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
        poses (map #(all-pos % s) v)]
    (map #(inc (mean %)) poses)
    ))

(rank [1 0])

(defn rank-sp
  "Spearman rank correlation"
  [v1 v2]
  (let [r1 (rank v1)
        r2 (rank v2)]
    (pearson r1 r2))
  )


;Categorical comparison: chi-square
