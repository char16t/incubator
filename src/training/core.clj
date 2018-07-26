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
  "Problem: https://algoprog.ru/material/p2942
            https://algoprog.ru/material/p2943"
  [n]
  (rem (quot n 10) 10))

(defn three-digit-sum
  "Problem: https://algoprog.ru/material/p2944"
  [n]
  (let [a (rem n 10)
        b (rem (quot n 10) 10)
        c (rem (quot (quot n 10) 10) 10)]
    (+ a b c)))

(defn eclock
  "Problem: https://algoprog.ru/material/p2947"
  [min]
  (let [h (mod (quot min 60) 24)
        m (mod min 60)]
  [h m]))

(defn price
  "Problem: https://algoprog.ru/material/p2951"
  [a b n]
  (let [k (mod (* b n) 100)
        r (+ (* a n) (quot (* b n) 100))]
  [r k]))

(defn time-diff
  "Problem: https://algoprog.ru/material/p2952"
  [h1 m1 s1 h2 m2 s2]
  (- (+ (* h2 3600) (* m2 60) s2)
     (+ (* h1 3600) (* m1 60) s1)))

(defn mymax
  "Problem: https://algoprog.ru/material/p292"
  [a b]
  (max a b))

(defn max-pos
  "Problem: https://algoprog.ru/material/p293"
  [a b]
  (cond
    (> a b) 1
    (< a b) 2
    (= a b) 0))

(defn sign
  "Problem: https://algoprog.ru/material/p2959"
  [n]
  (cond
    (> n 0) 1
    (< n 0) -1
    (= n 0) 0))
    
(defn max3
  "Problem: https://algoprog.ru/material/p294"
  [& nums]
  (apply max nums))

(defn leap-year
  "Problem: https://algoprog.ru/material/p253"
  [year]
  (if (or (and (= (rem year 4) 0) (not= (rem year 100) 0))
          (= (rem year 400) 0))
    "YES"
    "NO"))

(defn evens
  "Problem: https://algoprog.ru/material/p333"
  [a b]
  (->> (range a b)
       (filter even?)))

(defn factorial
  "Problem: https://algoprog.ru/material/p351"
  [n]
  (if (= n 0)
    1
    (* n (factorial (- n 1)))))
    
(defn sq-sum
  "Problem: https://algoprog.ru/material/p315"
  [n]
  (->>
    (range 0 (+ n 1))
    (map #(* % %))
    (reduce +)))

(defn dividers
  "Problem: https://algoprog.ru/material/p340"
  [n]
  (->>
    (range 1 (inc n))
    (filter #(= (rem n %) 0))))

(defn has-zero
  "Problem: https://algoprog.ru/material/p347"
  [lst]
  (if (some #(= 0 %) lst)
    "YES"
    "NO"))

(defn sqr-list
  "Problem: https://algoprog.ru/material/p113"
  [coll]
  (->> coll
    (map #(* % %))))

(defn min-divider
  "Problem: https://algoprog.ru/material/p3058"
  [n]
  (->>
    (->>
      (range 2 (inc n))
      (filter #(= (rem n %) 0)))
    (first)))

(defn seq-len
  "Problem: https://algoprog.ru/material/p3064"
  [coll]
  (count (take-while (fn [x] (not= 0 x)) coll)))

(defn seq-sum
  "Problem: https://algoprog.ru/material/p3065"
  [coll]
  (->>
    (take-while (fn [x] (not= 0 x)) coll)
    (reduce +)))

(defn seq-count-even
  "Problem: https://algoprog.ru/material/p3067"
  [coll]
  (->>
    (take-while (fn [x] (not= 0 x)) coll)
    (filter even?)
    (count)))

(defn with-even-indexes
  "Problem: https://algoprog.ru/material/p63"
  [coll]
  (keep-indexed #(if (even? %1) %2) coll))

(defn filter-even
  "Problem: https://algoprog.ru/material/p64"
  [coll]
  (->> coll (filter even?)))

(defn count-increasing
  "Problem: https://algoprog.ru/material/p66"
  [coll]
  (->>
    (partition 2 1 coll)
    (map #(if (< (first %) (second %)) 1 0))
    (reduce +)))

(defn myreverse
  "Problem: https://clojuredocs.org/clojure.core/reverse"
  [coll]
  (reverse coll))

(defn cycle-move
  "Problem: https://algoprog.ru/material/p71"
  [coll]
  (conj (take (dec (count coll)) coll) (last coll)))

(defn is-palindrome
  "Problem: https://algoprog.ru/material/p108"
  [s]
  (if (= s (apply str (reverse s)))
    "yes"
    "no"))

(defn symmetrix-matrix
  "Problem: https://algoprog.ru/material/p355"
  [m]
  (if (= m (apply mapv vector m))
    "yes"
    "no"))

(defn count-words
  "Problem: https://algoprog.ru/material/p106"
  [text]
  (let [first-line (first (clojure.string/split-lines text))]
    (count (clojure.string/split first-line #"\s"))))

(defn is-digit
  "Problem: https://algoprog.ru/material/p102"
  [s]
  (if (Character/isDigit s)
    "yes"
    "no"))
    
(defn to-upper
  "Problem: https://algoprog.ru/material/p103"
  [s]
  (clojure.string/upper-case s))

(defn check-equation
  "Problem: https://algoprog.ru/material/p74"
  [a b c]
  (let [diff (Math/abs (- c (+ a b)))]
    (if (< diff 0.00001)
    "YES"
    "NO")))

(defn clock-angle
  "Problem: https://algoprog.ru/material/p3612"
  [h m s]
  (let [ha (/ 360 12)
        ma (/ 360 (* 12 60))
	sa (/ 360 (* 12 60 60))]
    (double
      (+
        (* ha h)
        (* ma m)
        (* sa s)))))
        
(defn morning-jogging-1
  "https://algoprog.ru/material/p596"
  [x y]
  (reduce
    (fn [a c] (if (>= a y) (reduced c) (+ a (* a 0.7))))
    x
    (range 1 100000)))
  
(defn sum-2
  "Problem: https://algoprog.ru/material/p120"
  [n]
  (first
    (reduce 
      (fn [a c] [(+ (first a)(/ 1 c)) (inc c)])
      [1 1]
      (range 1 (inc n)))))
 
(defn school-desks
  "Problem: https://algoprog.ru/material/p2946"
  [a b c]
  (int (Math/ceil
    (/ (+ a b c) 2))))

(defn- merge-sort-merge
  [[lhead & ltail :as left] [rhead & rtail :as right] result]
  (if (and (not-empty left) (not-empty right))
    (if (<= lhead rhead)
      (recur ltail right (conj result lhead))
      (recur left rtail (conj result rhead)))
    (concat result left right)))

(defn merge-sort
  [coll]
  (if (< (count coll) 2)
    coll
    (let [middle (int (/ (count coll) 2))
          left (subvec coll 0 middle)
          right (subvec coll middle)]
      (merge-sort-merge
        (merge-sort left)
        (merge-sort right)
        []))))

(defn next-even
  "https://algoprog.ru/material/p2945"
  [n]
  (int (* 2 (Math/ceil (/ (inc n) 2)))))

(defn lines-in-book
  "Problem: https://algoprog.ru/material/p506"
  [k n]
  [(int (Math/ceil (/ n k)))
   (rem n k)])

(defn longest-word
  "Problem: https://algoprog.ru/material/p107"
  [words]
  (let [lw
    (reduce
      (fn [longest word]
        (if (> (count word) (count longest))
          word
	  longest))
    words)]
    [lw (count lw)]))

(defn split-number
  "Problem: https://algoprog.ru/material/p792"
  [n]
  (->> (partition-all 3 (reverse (str n)))
    (map #(apply str (reverse %)))
    (reverse)
    (clojure.string/join ",")))

(defn two-same-letters
  "Problem: https://algoprog.ru/material/p109"
  [word]
  (->> (seq word)
    (reduce
      (fn [acc curr]
        (update-in acc [curr] (fnil inc 0)))
      {})
    (seq)
    (filter #(= (second %) 2))
    (ffirst)
    (str)))

(defn sandwich
  "Problem: https://algoprog.ru/material/p723"
  [enc]
  (->> (partition-all 2 (seq (take (dec (count enc)) enc)))
    (reduce
      (fn [acc curr]
        {:pre
          (apply str 
            (concat
              (get acc :pre)
              (str (first curr))))
         :post
          (apply str
            (concat
              (str (second curr))
              (get acc :post)))})
      {:pre "" :post ""})
    (seq)
    (map second)
    (apply concat)
    (apply str)))

(defn gcd
  "Problem: https://algoprog.ru/material/p199"
  [a b]
  (if (zero? b) a (recur b (mod a b))))

(defn fraction-reduction
  "Problem: https://algoprog.ru/material/p27"
  [a b]
  (let [gcd (gcd a b)]
    [(/ a gcd) (/ b gcd)]))

(defn digits
  "Problem: https://algoprog.ru/material/p1568"
  [coll]
  (let [c (frequencies coll)]
    [(get c 1 0)
     (get c 2 0)
     (get c 3 0)
     (get c 4 0)
     (get c 5 0)
     (get c 6 0)
     (get c 7 0)
     (get c 8 0)
     (get c 9 0)]))

(defn binary-strings
  "Problem: https://algoprog.ru/material/p80"
  [n]
  (def inner (fn [length prefix strings]
    (if (< (count prefix) length)
      (concat
        (apply (partial conj strings)
          (inner length (conj prefix 0) strings))
        (apply (partial conj strings)
          (inner length (conj prefix 1) strings)))
      [prefix])))
  (inner n [] []))

(defn binary-strings-with-ones
  "Problem: https://algoprog.ru/material/p84"
  [length ones]
  (filter
    #(= (get (frequencies %) 1 0) ones)
    (binary-strings length)))

(defn censorship
  "Problem: https://algoprog.ru/material/p426"
  [phrases]
  (filter
    (fn [phrase]
      (let [words (clojure.string/split phrase #" ")
            pure-words (map #(clojure.string/replace % #"\.|\!" {"." "" "!" ""}) words)
            censored (map #(> (count (frequencies %)) 3) pure-words)
            censored-count (count (filter #(= % true) censored))
            censored-limit (int (/ (count words) 2))]
        (> censored-count censored-limit)))
    phrases))

(defn count-smiles
  "Problem: https://algoprog.ru/material/p1629"
  [string]
  (count (re-seq #"(^|\s)((?::|;)(?:-*)?(?:\)+($|\s)|\(+($|\s)|\]+($|\s)|\[+($|\s)))" string)))

(defn ege
  "Problem: https://algoprog.ru/material/p1629"
  [task]
  (if (> (count (re-seq #"[6-9]{1}|\d{2,}" task)) 0)
    "NO"
    "YES"))

(defn fib-last-digit
  "Problem: https://algoprog.ru/material/p842"
  [n]
  (let [fib-seq ((fn rfib [a b] 
     (lazy-seq (cons a (rfib b (+ a b))))) 1 1)]
    (rem (nth fib-seq n) 10)))
