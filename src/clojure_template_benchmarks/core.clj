(ns clojure-template-benchmarks.core
  (:use criterium.core
        tinsel.core)
  (:require [clabango.parser :refer [render render-file]]
            [stencil.core :as stencil]
            [hiccup.core :as hiccup]
            [me.raynes.laser :as laser :refer [defdocument]]
            [net.cgrand.enlive-html :as enlive]))

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
  (hiccup/html [:span {:class "foo"} bar]))

(defn list-hiccup [ceil]
  (hiccup/html [:ul (for [x (range 1 ceil)]
               [:li x])]))

(defn simple-hiccup-hint []
  (hiccup/html [:span {:class "foo"} ^String bar]))

(defn list-hiccup-hint [ceil]
  (hiccup/html [:ul (for [x (range 1 ceil)]
               [:li ^Number x])]))


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


(deftemplate simple-tinsel [[:span {:class "foo"}]] [] (has-class? "foo") (set-content bar))
(deftemplate list-tinsel [[:ul]] [ceil] (tag= :ul) (set-content (for [x (range 1 ceil)] [:li x])))

(defdocument simple-laser "<span class=\"foo\"></span>" []
  (laser/class= "foo") (laser/content bar))
(defdocument list-laser "<ul></ul>" [ceil]
  (laser/element= :ul) (laser/html-content
                        (for [x (range 1 ceil)]
                          (laser/node :li :content (str x)))))

(defdocument simple-laser-hinted "<span class=\"foo\"></span>" []
  (laser/class= "foo") (laser/content ^String bar))
(defdocument list-laser-hinted "<ul></ul>" [ceil]
  (laser/element= :ul) (laser/html-content
                        (for [x (range 1 ceil)]
                          (laser/node :li :content (str ^Number x)))))

(enlive/deftemplate simple-enlive-core "clojure_template_benchmarks/templates/simple.enlive" []
  [:span.foo] (enlive/content bar))
(enlive/deftemplate list-enlive-core "clojure_template_benchmarks/templates/list.enlive" [ceil]
  [:ul] (enlive/clone-for [x (range 1 ceil)]
                          (enlive/content (str x))))

(defn simple-enlive [] (apply str (simple-enlive-core)))
(defn list-enlive [ceil] (apply str (list-enlive-core ceil)))

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

  (println "\n\n ***** type-hinted hiccup benchmarks  ***** \n\n")
  (with-progress-reporting (quick-bench (simple-hiccup-hint)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-hiccup-hint 50)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-hiccup-hint 1000)))
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
  (println "\n --- \n")

  (println "\n\n ***** tinsel ***** \n\n")
  (with-progress-reporting (quick-bench (simple-tinsel)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-tinsel 50)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-tinsel 1000)))
  (println "\n --- \n")

  (println "\n\n ***** laser ***** \n\n")
  (with-progress-reporting (quick-bench (simple-laser)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-laser 50)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-laser 1000)))
  (println "\n --- \n")

  (println "\n\n ***** laser (hinted) ***** \n\n")
  (with-progress-reporting (quick-bench (simple-laser-hinted)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-laser-hinted 50)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-laser-hinted 1000)))
  (println "\n --- \n")
  
  (println "\n\n ***** enlive ***** \n\n")
  (with-progress-reporting (quick-bench (simple-enlive)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-enlive 50)))
  (println "\n --- \n")
  (with-progress-reporting (quick-bench (list-enlive 1000)))
  (println "\n --- \n")
  )