(ns training.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn apples-1
  "Problem: https://algoprog.ru/material/p2938"
  [n k]
  (quot k n))

(defn apples-2
  "Problem: https://algoprog.ru/material/p2939"
  [n k]
  (rem k n)) 

(defn next-prev
  "Problem: https://algoprog.ru/material/p2937"
  [n]
  (let [curr (str n)
        next (str (inc n))
        prev (str (dec n))]
    (print-str
      (apply str (concat
        "The next number for the number " curr " is " next ".\n"
        "The previous number for the number " curr " is " prev ".")))))

(defn hypotenuse
  "Problem: https://algoprog.ru/material/p2936"
  [a b]
  (Math/sqrt (+ (* a a) (* b b))))

(defn last-digit
  "Problem: https://algoprog.ru/material/p2941"
  [n]
  (rem n 10))

(defn dozens
  "Problem: https://algoprog.ru/material/p2942"
  [n]
  (quot n 10))
