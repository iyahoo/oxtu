(ns oxtu.cljs.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello world!"}))

(defn texts []
  [:div
   [:h1 (:text @app-state)]
   [:canvas {:id "c1" :width 140 :height 100}]
   [:h4 "↑↑↑四角じゃん！！↑↑↑"]
   [:h3 "オッ！！！！"]
   [:form {:action "/test" :method "post"}
    [:div
     [:label {:for "name"} "お名前:"]
     [:input {:type "text" :id "name" :name "user_name"}]]
    [:div
     [:label {:for "mail"} "E-mail:"]
     [:input {:type "email" :id "mail" :name "user_email"}]]
    [:div
     [:select {:name "kind"}
      [:option {:value "question"} "質問"]
      [:option {:value "ask"} "要望"]
      [:option {:value "others"} "その他"]]]
    [:div
     [:label {:for "msg"} "本文:"]
     [:textarea {:id "msg" :name "user_message"}]]
    [:div {:class "button"}
     [:button {:type "submit"} "送信"]]]])

(reagent/render-component
 [texts]
 (. js/document (getElementById "app")))

(defn write-rect []
  (let [canv (.getElementById js/document "c1")
        ctx (.getContext canv "2d")]    
    (.beginPath ctx)
    (. ctx fillRect 10 0 100 100)))

(write-rect)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
