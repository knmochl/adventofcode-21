(ns advent.day1
  (:require [advent.util :refer [slurp-lines]]
            [clojure.edn :as edn]))

(def test-input
  ["199"
   "200"
   "208"
   "210"
   "200"
   "207"
   "240"
   "269"
   "260"
   "263"])

(defn depth-reducer
  [[increases previous] new-value]
  (if (> new-value previous)
    [(inc increases) new-value]
    [increases new-value]))

(defn part1
  []
  (let [depths (mapv edn/read-string (slurp-lines "input1.txt"))]
    (first (reduce depth-reducer [0 (first depths)] (rest depths)))))
