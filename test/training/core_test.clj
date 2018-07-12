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

(deftest seq-len-test
  (is (= (seq-len [1 7 9 0 5]) 3)))

(deftest seq-sum-test
  (is (= (seq-sum [1 7 9 0 5]) 17)))

(deftest seq-count-even-test
  (is (= (seq-count-even [2 1 4 0 6 5 2]) 2)))

(deftest with-even-indexes-test
  (is (= (with-even-indexes [4 5 3 4 2 3]) [4 3 2])))

(deftest filter-even-test
  (is (= (filter-even [1 2 3 4 5]) [2 4])))

(deftest count-increasing-test
  (is (= (count-increasing [1 2 3 4 5]) 4)))

(deftest myreverse-test
  (is (= (myreverse [4 5 3 4 2 3]) [3 2 4 3 5 4])))

(deftest cycle-move-test
  (is (= (cycle-move [4 5 3 4 2 3]) [3 4 5 3 4 2])))

(deftest is-palindrome-test
  (is (= (is-palindrome "abba") "yes")))

(deftest symmetrix-matrix-1-test
  (is (= (symmetrix-matrix [[0 1 2] [1 5 3] [2 3 4]]) "yes")))
  
(deftest symmetrix-matrix-2-test
  (is (= (symmetrix-matrix [[0 0 0] [0 0 0] [1 0 0]]) "no")))

(deftest count-words-test
  (is (= (count-words "In the town where I was born") 7)))

(def is-digit-1-test
  (is (= (is-digit \2) "yes")))
  
(def is-digit-2-test
  (is (= (is-digit \c) "no")))

(def to-upper-test
  (is (= (to-upper "c") "C")))

(deftest check-equation-1-test
  (is (= (check-equation 2 3 7) "NO")))

(deftest check-equation-2-test
  (is (= (check-equation 0.1 0.2 0.3) "YES")))

(deftest clock-angle-test
  (is (= (clock-angle 1 2 6) 31.05)))

(deftest morning-jogging-1-1-test
  (is (= (morning-jogging-1 10 30) 4)))

(deftest morning-jogging-1-2-test
  (is (= (morning-jogging-1 10 50) 5)))

(deftest sum-2-test
  (is (= (sum-2 1) 2)))

(deftest school-desks-test
  (is (= (school-desks 20 21 22) 32)))

(deftest merge-sort-1-test
  (is (= (merge-sort [4 3 2 1]) [1 2 3 4])))
  
(deftest merge-sort-2-test
  (is (= (merge-sort []) [])))
