(ns training.core-test
  (:require [clojure.test :refer :all]
            [training.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest apples-1-test
  (is (= (apples-1 3 14) 4)))

(deftest apples-2-test
  (is (= (apples-2 3 14) 2)))
