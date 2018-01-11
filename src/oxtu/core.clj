;; -*- coding: utf-8 -*-
(ns oxtu.core
  (:require [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.flash :refer [wrap-flash]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.format :refer [wrap-restful-format]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :as res]
            [ring.util.request :refer [body-string]]
            [hiccup.core :as hc :refer [html]]
            [bidi.ring :refer [make-handler]]))

(defonce server (atom nil))

(defonce oxtu "オッ!!!!!!!!!!!!")

(defn oxtu-struct [str-htag]
  (let [htag (keyword str-htag)]
    [htag
     [:Marquee {:behavior "alternate" :direction "up" :height "50"} oxtu]]))

(defn make-response [strhtml]
  (-> strhtml
      res/response
      (res/header "Content-Type" "text/html")
      (res/charset "utf-8")))

(defn struct-to-htmlres [html]
  (-> html
      hc/html
      make-response))

(defn index [req]
  (-> (oxtu-struct "h1")
      struct-to-htmlres))

(defn h-tag [{:keys [route-params]}]
  (-> (oxtu-struct (:tag route-params))
      struct-to-htmlres))

(defn form-struct []
  [:html
   [:head
    [:link {:rel "stylesheet" :href "/css/style.css"}]]
   [:body
    [:div {:id "app"}
     [:script {:src "bundle.js" :type "text/javascript"}]]]])

(defn form [req]
  (-> (form-struct)
      struct-to-htmlres))

(defn receive-struct [req]
  [:html
   [:body
    [:div {:id "app"}
     [:script {:src "receive.js" :type "text/javascript"}]
     [:h3 (body-string req)]]]])

(defn receive [req]
  (-> (receive-struct req)
      (struct-to-htmlres)))

(def handler
  (make-handler ["/" {"" index
                      "index.html" index
                      "form.html" form
                      "receive.html" receive
                      [:tag] h-tag
                      [:tag "/"] h-tag}]))

(def app
  (-> handler
      wrap-restful-format
      wrap-session
      wrap-flash
      (wrap-resource "public")
      wrap-content-type))

(defn start-server [& {:keys [host port join?]
                       :or {host "localhost" port 3000 join? false}}]
  (let [port (cond (integer? port) port
                   (string? port) (Integer/parseInt port)
                   :else 3000)]
    (when-not @server
      (reset! server (run-jetty #'app {:host host :port port :join? join?})))))

(defn stop-server []
  (when @server
    (.stop @server)
    (reset! server nil)))

(defn restart-server []
  (when @server
    (stop-server)
    (start-server)))
