(ns oxtu.cljs.react.tutorial
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defn square [& {:keys [value on-click]}]
  [:button.square {:onClick #(on-click)}
   value])

(defn board []
  (let [status "Next player: X"
        squares (atom [nil nil nil nil nil nil nil nil nil])
        handle-click (fn [i squares]
                       (swap! squares assoc i "X"))
        render-square (fn [i squares]
                        [square
                         :value (@squares i)
                         :on-click #(handle-click i squares)])]
    [:div
     [:div.status (str @squares)]
     [:div.board-row
      [render-square 0 squares]
      [render-square 1 squares]
      [render-square 2 squares]]
     [:div.board-row
      [render-square 3 squares]
      [render-square 4 squares]
      [render-square 5 squares]]
     [:div.board-row
      [render-square 6 squares]
      [render-square 7 squares]
      [render-square 8 squares]]]))

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
