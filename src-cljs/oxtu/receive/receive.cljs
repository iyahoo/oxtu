(ns oxtu.cljs.receive
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce app-state-receive (atom {:text "Hello receive page!"}))

(defn texts []
  [:div
   [:h1 (:text @app-state-receive)]])

(reagent/render-component
 [texts]
 (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)



