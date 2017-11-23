(ns oxtu.core
  (:require [ring.middleware.session :as mids :refer [wrap-session]]
            [ring.middleware.flash :as midf :refer [wrap-flash]]
            [ring.adapter.jetty :as s]
            [ring.util.response :as res]
            [hiccup.core :as hc]
            [bidi.bidi :as bd]
            [bidi.ring :as br :refer [make-handler]]))

(defonce server (atom nil))

(defonce oxtu "オッ!!!!!!!!!!!!")

(defn handler-oxtu [hnum]
  (let [htag (keyword hnum)]
    (as-> oxtu oxtu
      [:Marquee {:behavior "alternate" :direction "up" :height "50"} oxtu]
      [htag oxtu])))

(defn struct-to-htmlres [html]
  (-> html
      hc/html
      res/response
      (res/header "Content-Type" "text/html")))

(defn index-handler [req]
  (-> (handler-oxtu "h1")
      struct-to-htmlres))

(defn h-num-handler [{:keys [route-params]}]
  (-> (handler-oxtu (:tag route-params))
      struct-to-htmlres))

(def handler
  (br/make-handler ["/" {"" index-handler
                         "index.html" index-handler
                         [:tag "/"] h-num-handler}]))

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


