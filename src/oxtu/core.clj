;; -*- coding: utf-8 -*-
(ns oxtu.core
  (:require [ring.middleware.session :as mids :refer [wrap-session]]
            [ring.middleware.flash :as midf :refer [wrap-flash]]
            [ring.adapter.jetty :as s]
            [ring.util.response :as res]
            [hiccup.core :as hc]
            [bidi.ring :as br :refer [make-handler]]))

(defonce server (atom nil))

(defonce oxtu "オッ!!!!!!!!!!!!")

(defonce form-html "resources/public/form.html")

(defn oxtu-struct [htag]
  (let [htag (keyword htag)]
    (as-> oxtu oxtu
      [:Marquee {:behavior "alternate" :direction "up" :height "50"} oxtu]
      [htag oxtu])))

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
  [:script {:src "bundle.js" :type "text/javascript"}])

(defn form [req]
  (-> (form-struct)
      struct-to-htmlres))

(def handler
  (br/make-handler ["/" {"" index
                         "index.html" index
                         "form.html" form
                         [:tag] h-tag
                         [:tag "/"] h-tag}]))

(def app
  (-> handler
      mids/wrap-session
      midf/wrap-flash))

(defn start-server [& {:keys [host port join?]
                       :or {host "localhost" port 3000 join? false}}]
  (let [port (if (string? port) (Integer/parseInt port) port)]
    (when-not @server
      (reset! server (s/run-jetty #'app {:host host :port port :join? join?})))))

(defn stop-server []
  (when @server
    (.stop @server)
    (reset! server nil)))

(defn restart-server []
  (when @server
    (stop-server)
    (start-server)))
