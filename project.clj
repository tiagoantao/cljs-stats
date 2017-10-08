(defproject stats "0.1.0-SNAPSHOT"
  :description "Stats playground"
  :url "http://github.com/tiagoantao/stats"
  :license {:name "GNU Affero General Public License (AGPL)"
            :url "https://www.gnu.org/licenses/agpl.html"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.908"]
                 [cljsjs/d3 "4.3.0-5"]
                 [cljsjs/vega "3.0.1-0"]
                 [cljsjs/vega-lite "2.0.0-beta.14-0"]]

  :plugins [[lein-figwheel "0.5.12"]
            [lein-codox "0.10.3"]
            [lein-cljsbuild "1.1.6" :exclusions [[org.clojure/clojure]]]
            [lein-doo "0.1.7"]]

  :cljsbuild
  {:builds {
            :dev
            {:source-paths ["src"]
             :figwheel {:on-jsload "stats.web/on-js-reload"}
             :compiler {:main stats.web
                        :asset-path "js/compiled/out"
                        :output-to "resources/public/js/compiled/stats.js"
                        :output-dir "resources/public/js/compiled/out"
                        :source-map-timestamp true
                        :preloads [devtools.preload]
                        :static-fns true
                        :pretty-print false
                        :optimizations :none}}

            :test
            {:source-paths ["src" "test"]
             :compiler {:output-to "out/testable.js"
                        :main stats.runner
                        :process-shim false
                        :optimizations :none}}
            }}

  :codox {
          :output-path "docs"
          :language :clojurescript
          :source-paths ["src"]
          :metadata {:doc/format :markdown}
          }
  
  :figwheel {
             :css-dirs ["resources/public/css"]
             :builds-to-start ["dev"]
             :validate-config false
             }

  :aliases {"test" ["doo" "phantom" "test" "once"]}

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.2"]
                                  [figwheel-sidecar "0.5.12"]
                                  [org.clojure/tools.nrepl "0.2.13"]
                                  [com.cemerick/piggieback "0.2.1"]]
                   :source-paths ["src" "dev"]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})
