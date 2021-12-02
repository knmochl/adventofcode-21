(ns advent.day2
  (:require [advent.util :refer [slurp-lines]]
            [clojure.string :as string]
            [clojure.edn :as edn]))

(def test-input
  ["forward 5"
   "down 5"
   "forward 8"
   "up 3"
   "down 8"
   "forward 2"])

(defn parse-command
  [command]
  (let [[direction amount] (string/split command #" ")]
    [(keyword direction) (edn/read-string amount)]))

(defn execute-command
  [[position depth] [direction amount]]
  (case direction
    :forward [(+ position amount) depth]
    :up [position (- depth amount)]
    :down [position (+ depth amount)]))

(defn execute-command-aim
  [[position depth aim] [direction amount]]
  (case direction
    :forward [(+ position amount) (+ depth (* aim amount)) aim]
    :up [position depth (- aim amount)]
    :down [position depth (+ aim amount)]))

(defn part1
  []
  (let [command-lines (slurp-lines "input2.txt")
        commands (map parse-command command-lines)]
    (apply * (reduce execute-command [0 0] commands))))

(defn part2
  []
  (let [commands (map parse-command (slurp-lines "input2.txt"))
        state (reduce execute-command-aim [0 0 0] commands)]
    (* (first state) (second state))))
