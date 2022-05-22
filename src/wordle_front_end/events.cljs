(ns wordle-front-end.events
  (:require
   [re-frame.core :as re-frame]
   [wordle-front-end.db :as db]
   [day8.re-frame.http-fx :as http-fx]
   [ajax.core :as ajax]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-fx
 ::http-cats
 (fn [{:keys [db]} _]
   {:db (assoc db :show-something? true)
    :http-xhrio {:method :get
                 :uri "https://catfact.ninja/fact"
                 :headers {:Access-Control-Allow-Origin "*"}
                 :timeout 8000
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [:good-http-result]
                 :on-failure [:bad-http-result]}}))

(re-frame/reg-event-db
 :good-http-result
 (fn [db fx]
   (let [fact (-> fx second :fact)]
     (assoc db :cat-fact fact))))

(re-frame/reg-event-db
 :bad-http-result
 (fn [db fx]
   (println "deu ruim o request")
   db))

(re-frame/reg-event-fx
 ::http-word-sample
 (fn [{:keys [db]} _]
   {:db db
    :http-xhrio {:method :get
                 :uri "http://localhost:8081/wordle/new"
                 :timeout 8000
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [:good-http-word-sample-result]
                 :on-failure [:bad-http-word-sample-result]}}))

(re-frame/reg-event-db
 :bad-http-word-sample-result
 (fn [db fx]
   (println (-> fx second))
   (assoc db :word "not loaded")))

(re-frame/reg-event-db
 :good-http-word-sample-result
 (fn [db fx]
   (println (-> fx second))
   (assoc db :word (-> fx second :word))))
