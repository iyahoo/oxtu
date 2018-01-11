(defproject oxtu "0.1.0-SNAPSHOT"
  :description "This is for oxtu"
  :url "https://oxtu.herokuapp.com/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.8.1"
  :plugins [[lein-cljsbuild "1.1.7"]]
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [hiccup "1.0.5"]
                 [reagent "0.7.0"]
                 [bidi "2.1.2"]]
  :uberjar-name "oxtu.jar"
  :profiles {:uberjar {:aot :all
                       :main oxtu.main}}
  :clean-targets ^{:protect false}
  [:target-path
   "resources/public/bundle.js" "resources/public/js/out"
   "resources/public/receive.js" "resources/public/js/out2"]

  :cljsbuild {:builds [{:source-paths ["src-cljs/oxtu/core"]
                        :compiler {:main "oxtu.cljs.core"
                                   :output-to "resources/public/bundle.js"
                                   :output-dir "resources/public/js/out"
                                   :asset-path "/js/out"
                                   :optimizations :none
                                   :pretty-print true}}
                       {:source-paths ["src-cljs/oxtu/receive"]
                        :compiler {:main "oxtu.cljs.receive"
                                   :output-to "resources/public/receive.js"
                                   :output-dir "resources/public/js/out2"
                                   :asset-path "/js/out2"
                                   :optimizations :none
                                   :pretty-print true}}]}
  :hooks [leiningen.cljsbuild])
