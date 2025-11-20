(ns leibniz)

(set! *unchecked-math* :warn-on-boxed)

(defn calc-pi-leibniz
  ^double
  [^long rounds]
  (let [pairs (quot rounds 2)]
    (loop [i (long 0)
           pi (double 0.0)]
      (if (== i pairs)
        (let [pi (if (== (bit-and rounds 1) 1)
                   (+ pi (/ 1.0 (double (unchecked-add (unchecked-multiply 2 i) 1))))
                   pi)]
          (* 4.0 pi))
        (let [k (unchecked-multiply 4 i)
              denom1 (double (unchecked-inc k))
              denom2 (double (unchecked-add k 3))
              term (- (/ 1.0 denom1) (/ 1.0 denom2))]
          (recur (unchecked-inc i) (+ pi term)))))))

(defn parse-long-trim [s]
  (Long/parseLong (re-find #"\A-?\d+" (str s))))

(def rounds (parse-long-trim (slurp "rounds.txt")))
(println (calc-pi-leibniz rounds))
