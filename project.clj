(defproject lf-re-frame "0.5.0"
  :dependencies [[org.clojure/clojure       "1.8.0"]
                 ;[org.clojure/clojurescript "1.7.145"]
                 [org.clojure/clojurescript "1.8.51"]
                 [org.clojure/core.async "0.2.374"]

                 [cryogen-core "0.1.40"]

                 [reagent "0.5.1"]
                 ;[re-frame "0.5.0"]
                 [re-frame "0.7.0"]
                 ;[re-com "0.6.1"]
                 [re-com "0.8.1"]
                 [cljs-ajax "0.5.1"]
                 [figwheel "0.2.6"]
                 [secretary "1.2.3"]
                 ;[markdown-clj "0.9.82"]
                 [markdown-clj "0.9.88"]
                 [cljs-http "0.1.40"]
                 ;[endophile "0.1.2"]
                 ;[hickory "0.5.1"]
                 [hickory "0.6.0"]
                 ;[prone "0.8.2"]
                 ;[cljsjs/jquery "1.9.1-0"]
                 ;[cljsjs/bootstrap "3.3.5-0"]
                 [prismatic/schema "1.0.3"]]

  :plugins [
            ;[lein-cljsbuild "1.1.1"]
            [lein-cljsbuild "1.1.3"]
            ;[lein-figwheel "0.5.0-2"]
            [lein-figwheel "0.5.2"]
            ]

  :hooks [leiningen.cljsbuild]

  :profiles {:dev {:cljsbuild
                   {:builds {:client {:source-paths ["devsrc"]
                                      :compiler     {:main lf.dev
                                                     :asset-path "js"
                                                     :optimizations :none
                                                     :source-map true
                                                     :source-map-timestamp true}}}}}
             :prod {:cljsbuild
                    {:builds {:client {:compiler    {:optimizations :advanced
                                                     :elide-asserts true
                                                     :pretty-print false}}}}}}

  :figwheel {:server-port     3450
             :server-ip       "0.0.0.0"
             ;:server-hostname ["localhost"]
             :repl            true}


  :clean-targets ^{:protect false} ["resources/public/js" "target"]

  :cljsbuild {:builds {:client {:source-paths ["src" ]
                                :compiler     {:output-dir "resources/public/js"
                                               :output-to  "resources/public/js/client.js"}}}})
