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

(deftest eclock-1-test
  (is (= (eclock 150) [2 30])))
  
(deftest eclock-2-test
  (is (= (eclock 1441) [0 1])))
  
(deftest price-1-test
  (is (= (price 10 15 2) [20 30])))

(deftest price-2-test
  (is (= (price 2 50 4) [10 0])))
  
(deftest time-diff-1-test
  (is (= (time-diff 1 1 1 2 2 2) 3661))) 

(deftest time-diff-2-test
  (is (= (time-diff 1 2 30 1 3 20) 50)))

(deftest mymax-test
  (is (= (mymax 1 2) 2)))

(deftest max-pos-1-test
  (is (= (max-pos 1 2) 2)))

(deftest max-pos-2-test
  (is (= (max-pos 2 2) 0)))
  
(deftest sign-1-test
  (is (= (sign 2) 1)))
  
(deftest sign-2-test
  (is (= (sign -1) -1)))
  
(deftest sign-3-test
  (is (= (sign 0) 0)))

(deftest max3-test
  (is (= (max3 1 2 3) 3)))

(deftest leap-year-1-test
  (is (= (leap-year 2007) "NO")))

(deftest leap-year-1-test
  (is (= (leap-year 2000) "YES")))

(deftest evens-test
  (is (= (evens 2 5) [2 4])))
  
(deftest factorial-test
  (is (= (factorial 3) 6)))

(deftest sq-sum-test
  (is (= (sq-sum 2) 5)))

(deftest dividers-test
  (is (= (dividers 6) [1 2 3 6])))

(deftest has-zero-1-test
  (is (= (has-zero [1 2 3 4]) "NO")))
  
(deftest has-zero-2-test
  (is (= (has-zero [1 2 0 4]) "YES")))

(deftest sqr-list-test
  (is (= (sqr-list [1 2 3 4]) [1 4 9 16])))

(deftest min-divider-test
  (is (= (min-divider 15) 3)))
