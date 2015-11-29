(defproject todo-list "0.1.0-SNAPSHOT"
  :description "A simple webapp using Ring, Compojure and Hiccup"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring "1.4.0"]
                 [compojure "1.3.4"]
                 [hiccup "1.0.5"]]
  :min-lein-version "2.0.0"
  :uberjar-name "todo-list.jar"
  :main todo-list.core
  :profiles {:dev {:main todo-list.core/-dev-main}})
