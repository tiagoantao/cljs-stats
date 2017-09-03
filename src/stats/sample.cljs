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

(defprotocol Sample
)

(defrecord Observations [coll]
   Sample
)

(extend-protocol ISeqable
  Observations
  (-seq [o] (seq (:coll o))))

(def aa (Observations. [1 2 3]))


(defrecord Histogram [hist]
  Sample
  )

(extend-protocol ISeqable
  Histogram
  (-seq [o] ((fn find [seq]
               (if (empty? seq)
                 (list)
                 (concat (repeat (second (first seq)) (first (first seq))) (find (rest seq))))) (:hist o))))


(def bb (Histogram. {1 10 2 5 10 1}))

(seq bb)
