(defproject clojure-template-benchmarks "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main clojure-template-benchmarks.core
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clabango "0.5"]
                 [criterium "0.4.1"] ; 0.3.1
                 [enlive "1.1.1"]
                 [hiccup "1.0.3"]
                 [stencil "0.3.2"]
                 [tinsel "0.4.0" :exclusions [hickory]]
                 [me.raynes/laser "1.1.1"]
                 [me.shenfeng/mustache "1.1"]])
