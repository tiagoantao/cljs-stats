(ns stats.sample-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [stats.sample :as s]))


(deftest deg2rad
  (testing "Basic samples"
    (prn (seq (s/Sample. {:hist {1 10 2 3}})))))
;    (is (<
;         (Math/abs
;          (- (tcore/deg2rad 180) 3.1415))
;         0.01))))


