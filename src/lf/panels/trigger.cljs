(ns lf.panels.trigger
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



(defn start-server-button []
  (let []
    (fn []
      [button
       :label "Start server"
       :tooltip "Start server to deal with the tasks"
       :tooltip-position :below-center
       ;:disabled?         (empty? @tasks)
       :on-click #(dispatch [:start-server])
       :class "btn btn-primary"]
      )
    )
  )


(defn start-worker-button []
  (let []
    (fn []
      [button
       :label "Start a worker"
       :tooltip "Start a worker to do the compute"
       :tooltip-position :below-center
       ;:disabled?         (empty? @tasks)
       :on-click #(dispatch [:start-worker])
       :class "btn btn-primary"]
      )
    )
  )


(defn trigger []
  [:div
   [:div.container
    [:h2 "Triggers for call labkit service' functions"]
    [v-box
     :padding "10px"
     :align :start
     :gap "10px"
     :children [[start-server-button]
                [start-worker-button]
                ;  [clock]
                ]]

    ]])
