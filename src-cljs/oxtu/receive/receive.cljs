(ns oxtu.cljs.receive
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(enable-console-print!)

(defonce app-state-receive (atom {:text "Hello receive page!"}))

(defn texts []
  [:div
   [:h1 (:text @app-state-receive)]])

(reagent/render-component
 [texts]
 (. js/document (getElementById "app")))

(defn test-fn []
  (go (let [response (<! (http/get "https://api.github.com/users"
                                   {:with-credentials? false
                                    :query-params {"since" 135}}))]
        (prn (:status response))
        (prn (map :login (:body response))))))

(test-fn)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
