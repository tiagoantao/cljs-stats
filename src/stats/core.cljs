(ns stats.core
  "Stats playground"
  {:author "Tiago Antao"}
  (:require [cljsjs.vega]
            [cljsjs.vega-lite]
            [cljsjs.d3]))

(enable-console-print!)

(def spec "
{
  \"$schema\": \"https://vega.github.io/schema/vega-lite/v2.json\",
  \"description\": \"Stock prices of 5 Tech Companies over Time with Averages.\",
  \"adata\": {\"url\": \"data/stocks.csv\"},
  \"layer\": [
    {
      \"mark\": \"line\",
      \"encoding\": {
        \"x\": {\"field\": \"date\", \"type\": \"temporal\"},
        \"y\": {\"field\": \"price\", \"type\": \"quantitative\"},
        \"color\": {\"field\": \"symbol\", \"type\": \"nominal\"}
      }
    },
    {
      \"mark\": \"rule\",
      \"encoding\": {
        \"y\": {
          \"field\": \"price\",
          \"type\": \"quantitative\",
          \"aggregate\": \"mean\"
        },
        \"size\": {\"value\": 2},
        \"color\": {\"field\": \"symbol\", \"type\": \"nominal\"}
      }
    }
  ]
}
")


(defn ^:export run
  [canvas-name]
  (set! (.-onload js/window)
        (let [jspec (.parse js/JSON spec)]
              (set! (.-width jspec) 300)
              (set! (.-heigth jspec) 300)
              (let [source (.stringify js/JSON jspec nil 2)
                    spec (.-spec (js/vl.compile jspec))
                    div (.html (.classed (js/d3.select "#vis-simple") "vega-embed" true) "")
                    ]
                (prn (js/vega.parse spec))
                (.-spec (js/vega.parse spec {} (fn [error chart]
                                                 (prn 3))))
                (prn 2)))))

