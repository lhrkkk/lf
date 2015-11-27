(ns lf.dev
  (:require [lf.core :as app]
            [figwheel.client :as fw]))
(enable-console-print!)

(fw/start {:on-jsload app/mount-root
           :websocket-url "ws://localhost:3450/figwheel-ws"})

;; 解决了! 在这里运行而不要在index.html里面运行
(app/main)

;(.text (js/jQuery "#app") "ClojureScript Rocks!")
