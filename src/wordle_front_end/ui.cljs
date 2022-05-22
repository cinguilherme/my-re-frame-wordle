(ns wordle-front-end.ui)

(defn presenter [title]
  [:div.container
   [:h1 title]])

(defn- letter-box [letter]
  [:div.flex
   {:style {:border "1px" :displays "contents" :padding "5px 25px 5px 25px"}}
   [:h2.border.border-gray letter]])

(defn- ->sequence-letters-div [word]
  (->> word
       vec
       (mapv #(str %))
       (mapv letter-box)))

(def conj-acumulate (fn [acc nex] (conj acc nex)))
(defn- reduce-div [letter-box-col]
  (reduce
   conj-acumulate
   [:div.flex {:style {:display "flex" :place-content "space-evenly"}}]
   letter-box-col))
(defn- word->letter-boxes [word]
  (->> word
       ->sequence-letters-div
       reduce-div))

(defn word-board [word]
  [:div.container {:style {:width "30%" :displays "contents"}}
   (word->letter-boxes word)
   [:h2 word]])

(defn word-input [callback-on-inp]
  [:div.container 
   [:lable "input"]
   [:input {:type "text"}]
   [:button.btn-primary 
    {:style {:width "100px" :height "30px"}
     :on-click #(do (println "btn-clicked")
                    #_(callback-on-inp))}
    "Enter!"]])

(comment

  (->> "abacate"
       ->sequence-letters-div
       reduce-div))