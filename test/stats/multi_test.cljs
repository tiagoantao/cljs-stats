(ns stats.multi-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [stats.multi :as m]))


(deftest multiple-variables
  (testing "Covariance"
    (is (= (m/cov [1 2] [1 2]) 0.25))
    )
  (testing "Sample Covariance"
    (is (= (m/sample-cov [1 2] [1 2]) 0.5))
    )
  )


