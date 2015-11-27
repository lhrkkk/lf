(ns lf.panels.try1
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
            [reagent.core :as r]
            )
  )


(defn atom-input [value]
  [:input {:type      "text"
           :value     @value
           :on-change #(reset! value (-> % .-target .-value))}])

(def valss (r/atom "foo"))

(defn shared-state [val]
  (fn []
    [:div
     [:p "The value is now: " @val]
     [:p "Change it here: " [atom-input val]]]))

(defn shared-state2 []
  [:div
   [:p "The value is now: " @valss]
   [:p "Change it here: " [atom-input valss]]])


(defn try1 []
  [:div
   "hello"
   [shared-state valss]
   [shared-state2]
   ]

  )
