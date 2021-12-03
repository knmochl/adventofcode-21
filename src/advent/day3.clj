(ns advent.day3
  (:require [advent.util :refer [slurp-lines]]
            [clojure.string :as string]
            [clojure.edn :as edn]))

(def test-input
  ["00100"
   "11110"
   "10110"
   "10111"
   "10101"
   "01111"
   "00111"
   "11100"
   "10000"
   "11001"
   "00010"
   "01010"])

(defn get-entry
  [item n]
  (nth item n))

(defn get-sequence
  [items n]
  (map #(get-entry %1 n) items))

(defn most-common-digit
  [digits]
  (let [freqs (frequencies digits)]
    (if (< (freqs \0) (freqs \1))
      \1
      \0)))

(defn least-common-digit
  [digits]
  (let [freqs (frequencies digits)]
    (if (> (freqs \0) (freqs \1))
      \1
      \0)))

(defn calculate-digit
  [items f n]
  (f (get-sequence items n)))

(defn convert-binary
  [digits]
  (reduce #(+ (* 2 %1) %2)
          (map (comp edn/read-string str) digits)))

(defn part1
  []
  (let [values (slurp-lines "input3.txt")
        gamma (convert-binary (map (partial calculate-digit values most-common-digit) (range (count (first values)))))
        epsilon (convert-binary (map (partial calculate-digit values least-common-digit) (range (count (first values)))))]
    (* gamma epsilon)))
