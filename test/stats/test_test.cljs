(ns stats.test-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [stats.test :as t]))


(deftest samples
  (testing "Testing all-pos"
    (is (= (t/all-pos 0 []) []))
    (is (= (t/all-pos 0 [1 2 2 4]) []))
    (is (= (t/all-pos 1 [1 2 2 4]) [0]))
    (is (= (t/all-pos 2 [1 2 2 4]) [1 2]))
    (is (= (t/all-pos 4 [1 2 2 4]) [3]))
    (is (nil? (t/all-pos 0 '(1 2 2 4))))
    ))


