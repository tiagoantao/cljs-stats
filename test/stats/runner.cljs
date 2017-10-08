(ns stats.runner
  (:require [cljs.test :as test]
            [doo.runner :refer-macros [doo-all-tests doo-tests]]
            [stats.sample-test]
            [stats.stats-test]
            [stats.multi-test]
            [stats.test-test]
            [stats.regression-test]))

(doo-tests 'stats.sample-test
           'stats.stats-test
           'stats.multi-test
           'stats.test-test
           'stats.regression-test)
