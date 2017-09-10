(ns stats.stats-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [stats.sample :refer [Sample]]
            [stats.stats :as s]))


(deftest mean
  (testing "Mean"
    (is (= (s/mean (Sample. {:obs [1]})) 1))
    (is (= (s/mean '(1 2)) 1.5))
  ))


(deftest mean1
  (testing "Mean but divided by obs - 1"
    (is (= (s/mean1 '(1 2)) 3))
  ))


(deftest median
  (testing "Median"
    (is (= (s/median (Sample. {:obs [1 2 3 4 5 6]})) 3.5))
    (is (= (s/median '(1 2 3 4 5)) 3))
    ))

(deftest variance
  (testing "Variance"
    (is (= 2.5 (s/variance [1 2 3 4 5])))
    (is (= 0 (s/variance [1 1])))
    ))

(deftest pop-variance
  (testing "Population variance"
    (is (= 2 (s/pop-variance [1 2 3 4 5])))
    ))
