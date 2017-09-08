(ns stats.stats-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [stats.sample :refer [Sample]]
            [stats.stats :as s]))


(deftest mean
  (testing "Mean"
    (is (= (s/mean (Sample. {:obs [1]})) 1))
    (is (= (s/mean '(1 2) 1.5))))
  )


