(ns stats.regression-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [stats.regression :as r]))


(deftest regression
  (testing "Sum of squared errors of prediction"
    (is (= (r/sse [1] [1]) 0))
    (is (= (r/sse [1] [2]) 1))
    (is (= (r/sse [1] [3]) 4))
    (is (= (r/sse [1 2] [3 2]) 4))
    (is (= (r/sse [1 2] [3 3]) 5))
    )
  )


