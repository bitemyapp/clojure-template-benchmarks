(ns clojure-template-benchmarks.core
  (:use criterium.core
        hiccup.core)
  (:require [clabango.parser :refer [render render-file]]
            [stencil.core :as stencil]))

(def bar (str "bar"))


(defn simple-str []
  (str "<span class=\"foo\">"
       bar
       "</span>"))

(defn list-str [ceil]
  (str "<ul>"
       (for [n (range 1 ceil)]
         (str "<li>" n "</li>"))
       "</ul>"))


(defn simple-hiccup []
  (html [:span {:class "foo"} bar]))

(defn list-hiccup [ceil]
  (html [:ul (for [x (range 1 ceil)]
               [:li x])]))


(defn simple-clabango-no-fd []
  (render (str "<span class=\"foo\">{{bar}}</span>") {:bar bar}))

(defn list-clabango-no-fd [ceil]
  (render "<ul>{% for item in items %}<li>{{item}}</li>{% endfor %}</ul>" {:items (range 1 ceil)}))


(defn simple-clabango []
  (render-file "clojure_template_benchmarks/templates/simple.html" {:bar bar}))

(defn list-clabango [ceil]
  (render-file "clojure_template_benchmarks/templates/list.html" {:items (range 1 ceil)}))


(defn simple-stencil-no-fd []
  (stencil/render-string "<span class=\"foo\">{{bar}}</span>" {:bar bar}))

(defn list-stencil-no-fd [ceil]
  (stencil/render-string "<ul>{{#items}}<li>{{.}}</li>{{/items}}</ul>" {:items (range 1 ceil)}))


(defn simple-stencil []
  (stencil/render-file "clojure_template_benchmarks/templates/simple.mustache" {:bar bar}))

(defn list-stencil [ceil]
  (stencil/render-file "clojure_template_benchmarks/templates/list.mustache" {:items (range 1 ceil)}))


(defn -main [& args]
  ;; (println (simple-hiccup))
  ;; (println (simple-clabango-no-fd))
  ;; (println (count (list-filler-hiccup)))
  ;; (println (count (list-filler-clabango-no-fd)))

  (println "\n\n ***** str benchmarks ***** \n\n")
  (with-progress-reporting (quick-bench (simple-str)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-str 50)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-str 1000)))
  (println "\n --- \n")
  
  (println "\n\n ***** hiccup benchmarks  ***** \n\n")
  (with-progress-reporting (quick-bench (simple-hiccup)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-hiccup 50)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-hiccup 1000)))
  (println "\n --- \n")

  (println "\n\n ***** clabango string ***** \n\n")
  (quick-bench (simple-clabango-no-fd))
  (println "\n --- \n")
  (quick-bench (list-clabango-no-fd 50))
  (println "\n --- \n")
  (quick-bench (list-clabango-no-fd 1000))
  (println "\n --- \n")

  (println "\n\n ***** clabango from file template ***** \n\n")
  (with-progress-reporting (quick-bench (simple-clabango)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-clabango 50)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-clabango 1000)))
  (println "\n --- \n")

  (println "\n\n ***** stencil string ***** \n\n")
  (with-progress-reporting (quick-bench (simple-stencil-no-fd)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-stencil-no-fd 50)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-stencil-no-fd 1000)))
  (println "\n --- \n")

  (println "\n\n ***** stencil file ***** \n\n")
  (with-progress-reporting (quick-bench (simple-stencil)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-stencil 50)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-stencil 1000)))
  (println "\n --- \n"))
