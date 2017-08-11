(defproject stats "0.1.0-SNAPSHOT"
  :description "Stats playground"
  :url "http://github.com/tiagoantao/stats"
  :license {:name "GNU Affero General Public License (AGPL)"
            :url "https://www.gnu.org/licenses/agpl.html"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.854"]]

  :plugins [[lein-figwheel "0.5.10"]
            [lein-marginalia "0.9.0"]
            [lein-cljsbuild "1.1.6"] ;:exclusions [[org.clojure/clojure]]]
            [lein-doo "0.1.7"]]

  :cljsbuild
  {:builds {
            :dev
            {:source-paths ["src"]

             :figwheel {;:on-jsload "stats.core/on-js-reload"
                        }

             :compiler {:main stats.core
                        :asset-path "js/compiled/out"
                        :output-to "resources/public/js/compiled/stats.js"
                        :output-dir "resources/public/js/compiled/out"
                        :source-map-timestamp true
                        :preloads [devtools.preload]
                        :language-in :es5
                        :npm-deps {:vega-lite "2.0.0-beta.10"}
                        :pretty-print false
                        :optimizations :none

                        :static-fns true}}

                        ;:test
                        ;{:source-paths ["src" "test"]
                        ; :compiler {:output-to "out/testable.js"
                        ;            :main metis-web.runner
                        ;            :optimizations :none
                        ;            }}}}

            }}


  :figwheel {
             :css-dirs ["resources/public/css"] ;; watch and update CSS
             :validate-config false
             }

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.2"]
                                  [figwheel-sidecar "0.5.10"]
                                  [com.cemerick/piggieback "0.2.1"]]
                   :source-paths ["src" "dev"]
                   ;; for CIDER
                   ;; :plugins [[cider/cider-nrepl "0.12.0"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})