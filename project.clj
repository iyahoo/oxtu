(defproject oxtu "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.8.1"
  :dependencies [[org.clojure/clojure "1.9.0-RC1"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [hiccup "1.0.5"]]
  :uberjar-name "oxtu.jar"
  :profiles
  {:uberjar {:aot :all
             :main oxtu.main}})
