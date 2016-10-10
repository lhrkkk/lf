(ns lf.panels.about
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

;; trigger a dispatch every second
(defonce time-updater (js/setInterval
                        #(dispatch [:timer (js/Date.)]) 1000))


;; trigger a dispatch every second
;(defonce time-updater (js/setInterval
;                        #(dispatch [:timer (js/Date.)]) 1000))

(defn clock
  []
  (let [time-color (subscribe [:time-color])
        timer (subscribe [:timer])]

    (fn clock-render
      []
      (let [time-str (-> @timer
                         .toTimeString
                         (clojure.string/split " ")
                         first)
            style {:style {:color @time-color}}]
        [:div.example-clock style time-str]))))

(defn greeting
  [message]
  [:h1 message])

(defn color-input
  []
  (let [time-color (subscribe [:time-color])]

    (fn color-input-render
      []
      [:div.color-input
       "Time color: "
       [:input {:type      "text"
                :value     @time-color
                :on-change #(dispatch
                             [:time-color (-> % .-target .-value)])}]])))

(defn clock-panel
  []
  [:div
   [:div {:on-click #(dispatch [:set-active-panel :panel2])}
    "Here"]
   [greeting "Hello world, it is now"]
   [clock]
   [color-input]])



;; ------ about --------

(defn about []
  [:div
   [:div.container
    [h-box
     ;:align :bottom
     :children [[:h2 "About Labit"]
                ]]
    [:p {:class "lead"} "The Most Advanced Biopyhsics Computing, Analysis, and Algorithm Developement System"]
    [clock]
    ]])
