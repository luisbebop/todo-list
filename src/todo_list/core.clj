(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]))

(defn welcome
  "A ring handler to process all requests sent to the webapp"
  [request]
  (html [:h1 "Hello, Clojure world"]
      [:p "Welcome to my first Clojure app"]))

(defn goodbye
  "A song to wish you goodbye"
  [request]
  (html5 {:lang "en"}
         [:head (include-js "myscript.js") (include-css "mystyle.css")]
         [:body
          [:div [:h1 {:class "info" } "Walking back to happiness"]]
          [:div [:p "Walking back to happiness with you"]]
          [:div [:p "Said, Farewell to loneliness I knew"]]
          [:div [:p "Laid aside foolish pride"]]
          [:div [:p "Learnt the truth from tears I cried"]]]))

(defn request-info
  "View the information contained in the request, useful for debug"
  [request]
  {:status 200
   :body(pr-str request)
   :headers {}})

(defn hello
  "A simple personalized greeting showing the use of variable path elements"
  [request]
  (let [name (get-in request[:route-params :name])]
    {:status 200
     :body(str "Hello " name ". I got your name from the web url")
     :headers{}}))

(def operands {"+" + "-" - "*" * ":" /})

(defn calculator
  "A very simple calculator"
  [request]
  (let [a (Integer. (get-in request[:route-params :a]))
        b (Integer. (get-in request[:route-params :b]))
        op (get-in request[:route-params :op])
        f (get operands op)]
    (if f
      {:status 200
       :body (str (f a b))
       :headers{}}
      {:status 404
       :body "Sorry, unknown operator"
       :headers {}})))

(defroutes app
  (GET "/" [] welcome)
  (GET "/goodbye" [] goodbye)
  (GET "/request-info" [] request-info)
  (GET "/request-dump" [] handle-dump)
  (GET "/hello/:name" [] hello)
  (GET "/calculator/:a/:op/:b" [] calculator)
  (not-found "<h1>page not found</h1>"))

(defn -main
  "A very simple web server using Ring & Jetty"
  [port-number]
  (jetty/run-jetty app
   {:port(Integer. port-number)}))

(defn -dev-main
  "A very simple web server using Ring & Jetty"
  [port-number]
  (jetty/run-jetty (wrap-reload #'app)
   {:port(Integer. port-number)}))
