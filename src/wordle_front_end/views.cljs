(ns wordle-front-end.views
  (:require
   [re-frame.core :as re-frame]
   [wordle-front-end.subs :as subs]
   [wordle-front-end.ui :as ui]
   ))


(defn main-panel []
  (let [_ (re-frame/subscribe [::subs/name])]
    [:div.center {:style {:margin "3em" :text-align "center"}}
     (ui/presenter "My Own Wordle")
     [:br]
     (ui/word-board "avocado")
     [:br]
     (ui/word-input)
     ]))
