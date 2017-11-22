(ns hello-world.core
  (:require [ring.adapter.jetty :as s]
            [hiccup.core :as hc]))

(defonce server (atom nil))

(defn view [req]
  (-> [:h1 [:Marquee {:behavior "alternate" :direction "up" :height "50"} "オッ!!!!!!!!!!!!!!!"]]
      hc/html))

(defn app [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (view req)})

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
