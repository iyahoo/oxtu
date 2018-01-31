(ns oxtu.cljs.react.tutorial
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(def squares (atom [nil nil nil nil nil nil nil nil nil]))

(defn square [& {:keys [value on-click]}]
  [:button.square {:onClick #(on-click)}
   value])

(defn handle-click [i]
  (swap! squares assoc i "X"))

(defn render-square [i]
  [square
   :value (@squares i)
   :on-click #(handle-click i)])

(defn board []
  (let [status "Next player: X"]
    [:div
     [:div.status (str @squares)]
     [:div.board-row
      [render-square 0]
      [render-square 1]
      [render-square 2]]
     [:div.board-row
      [render-square 3]
      [render-square 4]
      [render-square 5]]
     [:div.board-row
      [render-square 6]
      [render-square 7]
      [render-square 8]]]))

(defn game []
  [:div.game
   [:div.game-board
    [board]]
   [:div.game-info
    [:div
     ;; status
     ]
    [:ol
     ;; TODO
     ]]])

(reagent/render-component
 [game]
 (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
