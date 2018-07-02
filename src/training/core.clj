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
