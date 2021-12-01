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

(defn depth-sliding-reducer
  [[increases previous1 previous2 previous3] new-value]
  (if (> (+ new-value previous3 previous2) (+ previous1 previous2 previous3))
    [(inc increases) previous2 previous3 new-value]
    [increases previous2 previous3 new-value]))

(defn part1
  []
  (let [depths (mapv edn/read-string (slurp-lines "input1.txt"))]
    (first (reduce depth-reducer [0 (first depths)] (rest depths)))))

(defn part2
  []
  (let [depths (mapv edn/read-string (slurp-lines "input1.txt"))
        [num1 num2 num3] (take 3 depths)
        depths-remaining (drop 3 depths)]
    (first (reduce depth-sliding-reducer [0 num1 num2 num3] depths-remaining))))
