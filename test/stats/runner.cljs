(ns stats.runner
  (:require [cljs.test :as test]
            [doo.runner :refer-macros [doo-all-tests doo-tests]]
            [stats.sample-test]
            [stats.test-test]))

(doo-tests 'stats.sample-test
           'stats.test-test)
