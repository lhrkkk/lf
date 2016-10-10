(ns lf.core
  (:require-macros
    [secretary.core :refer [defroute]]
    [lf.core :refer [key-to-panel]]
    [reagent.ratom :refer [reaction]])
  (:require
    [lf.routes :as routes]
    [lf.handlers]
    [lf.subs]
    ;[lf.views :refer [nav home about data trigger task get-started]]
    [lf.panels.nav :refer [nav]]
    [lf.panels.about :refer [about]]
    [lf.panels.home :refer [home]]
    [lf.panels.data :refer [data]]
    [lf.panels.analysis :refer [analysis]]
    [lf.panels.console :refer [console]]
    [lf.panels.get-started :refer [get-started]]
    [lf.panels.try1 :refer [try1]]

    [reagent.core :as reagent]
    [reagent.core :as r]
    [re-frame.core :refer [register-handler
                           path
                           register-sub
                           dispatch
                           dispatch-sync
                           subscribe]])
  )

;(enable-console-print!)

(defn generate-panel [content]
  [:div content]
  )

;; 根据panels生成panel-list,并生成route, nav, root-panel
(def panels {:Labkit [home]
             (keyword "Get Started") [get-started]
             ;databank, analysis, console

             :Databank [data]
             :Analysis [analysis]
             :Console   [console]
             :About [about]



             ;:new    [generate-panel "new"]
             ;:try1 [try1]

             })

;panel-list is str
(def panel-list
  (map name (keys panels)))

(defn root-panel
  []
  (let [active (subscribe [:active-panel])
        ]
    (fn []
      [:div
       [nav panel-list]
       [:div (@active panels)]
       ;[:div (str @(subscribe [:db]))]
       ;(key-to-panel :about)
       ;[shared-state2]
       ])))


;; -- Entry Point -------------------------------------------------------------

(defn mount-root []
  (reagent/render [root-panel]
                  (.getElementById js/document "app")))


(defn ^:export main
  []
  ;(routes/app-routes)
  (routes/app-routes-auto panel-list)
  (dispatch-sync [:initialize])
  (mount-root)
  )
