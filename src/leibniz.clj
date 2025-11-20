(ns leibniz)

(set! *unchecked-math* :warn-on-boxed)

(defn calc-pi-leibniz
  "Optimized Clojure solution"
  ^double
  [^long rounds]
  (let [^long end (+ 2 rounds)]
    (loop [^long i 2
           ^double x 1.0
           ^double pi 1.0]
      (if (== i end)
        (* 4.0 pi)
        (let [^double x-neg (- x)
              ^double denom (double (unchecked-dec (unchecked-multiply 2 i)))]
          (recur (unchecked-inc i) x-neg (+ pi (/ x-neg denom))))))))

(defn parse-long-trim [s]
  (Long/parseLong (re-find #"\A-?\d+" (str s))))

(def rounds (parse-long-trim (slurp "rounds.txt")))
(println (calc-pi-leibniz rounds))
