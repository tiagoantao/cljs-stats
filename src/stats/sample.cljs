(ns stats.sample
  "Samples.

  Samples can be implemented in many different ways: normally lazy sequences but
  for example histograms also.

  Here we abstract that away.

  Note that it is possible to annotate a type with another
  (usually for efficiency purposes), for example:


  "
  {:author "Tiago Antao"}
)

(defprotocol Observations
  (nobs [s] (count (:obs (:data s)))))

(extend-protocol Observations
  PersistentVector
  (nobs [s] (count s))
  List
  (nobs [s] (count s)))

(defrecord Sample [data]
  Observations
  )

(extend-protocol ISeqable
  Sample
  (-seq [o] (condp some (keys (:data o))
              #{:hist}  ((fn find [seq]
                       (if (empty? seq)
                         (list)
                         (concat (repeat (second (first seq))
                                         (ffirst seq))
                                 (find (rest seq))))) (:hist (:data o)))

              #{:obs} (seq (:obs (:data o))))))

