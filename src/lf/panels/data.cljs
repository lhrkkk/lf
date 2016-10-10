(ns lf.panels.data
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [subscribe dispatch]]
            [re-com.core :refer [h-box v-box box gap line row-button button label checkbox horizontal-bar-tabs vertical-bar-tabs title p]
             :refer-macros [handler-fn]]
            [re-com.buttons :refer [row-button-args-desc]]
            [re-com.util :refer [enumerate]]
            [reagent.core :as reagent]
            [re-com.core :as re-com]
            [reagent.core :as reagent :refer [atom]]
            [ajax.core :as ajax :refer [GET POST]]
            [re-frame.core :refer [subscribe dispatch register-handler]]

            ))



(defn data []
  [:div
   [:div.container
    [:h2 "Databank query, display, download"]
    ;[tasks-list]
    ;[request-tasks-button]
    ;[ajax-text]

    ]])
