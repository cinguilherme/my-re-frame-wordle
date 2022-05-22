(ns wordle-front-end.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::cat-fact
 (fn [db]
   (:cat-fact db)))

(re-frame/reg-sub
 ::word
 (fn [db]
   (:word db)))
