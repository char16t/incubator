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

(deftest next-prev-test
  (is (= (next-prev 179)
         "The next number for the number 179 is 180.\nThe previous number for the number 179 is 178.")))

(deftest hypotenuse-test
  (is (= (hypotenuse 3 4) 5.0)))        

(deftest last-digit-test
  (is (= (last-digit 179) 9)))

(deftest dozens-test
  (is (= (dozens 42) 4)))

(deftest dozens-test
  (is (= (dozens 179) 7)))  

(deftest three-digit-sum-test
  (is (= (three-digit-sum 179) 17)))  
