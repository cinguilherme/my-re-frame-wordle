(ns wordle-front-end.views
  (:require
   [re-frame.core :as re-frame]
   [wordle-front-end.subs :as subs]
   [wordle-front-end.events :as events]
   [wordle-front-end.ui :as ui]
   ))


(defn main-panel []
  (let [_ (re-frame/subscribe [::subs/name])
        word (re-frame/subscribe [::subs/word])
        new-word (re-frame/dispatch-sync [::events/http-word-sample])]
    [:div.center {:style {:margin "3em" :text-align "center"}}
     (ui/presenter "My Own Wordle")
     [:br]
     (ui/word-board @word)
     [:br]
     (ui/word-input #(re-frame/dispatch-sync [::events/http-cats]))
     [:br]
     (ui/cat-fact-ui)
     ]))
