(ns advent.day2
  (:require [advent.util :refer [slurp-lines]]
            [clojre.string :as string]
            [clojure.edn :as edn]))

(def test-input
  ["forward 5"
   "down 5"
   "forward 8"
   "up 3"
   "down 8"
   "forward 2"])
