(ns stats.sample-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [stats.sample :as s]))


(deftest samples
  (testing "Basic sample testing"
    (is (=
         (seq (s/Sample. {:obs [1 1 2 2 2]}))
         '(1 1 2 2 2))))
  (testing "Basic sample testing - histogram representation"
    (is (=
         (seq (s/Sample. {:hist {1 2 2 3}}))
         '(1 1 2 2 2))))
  (testing "nobs works for vectors and lists"
    (is (= (s/nobs [1 2 3]) 3))
    (is (= (s/nobs '(1 2 3)) 3)))

  )


