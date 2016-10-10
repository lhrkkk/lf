(ns lf.panels.home
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

;; -------------------------
;; Views

(defn Home-old []
  [:div
   [:div.container-fluid
    [:header {:class "jumbotron subhead", :id "overview"}
     [:div {:class "container"}
      [:h1 "Welcom to Labkit"]
      [:p {:class "lead"} "The Labkit Developement System"]]]
    [:div.container
     [:image {:src "images/labkit.jpg"}]
     ;[:h4 "The world's best biopyhsics research platform"]
     ]
    ]])


(defn home []

  [:div
   [:main {:class "bs-masthead", :id "content", :role "main"}
    [:div {:class "container"}
     [:h1 "Welcom to Labkit"]
     [:p {:class "lead"} "\n      Welcom to the Labkit Developement System"
      ;[:a {:href "http://www.rabbitmq.com/tutorials/amqp-concepts.html"} "AMQP 0.9.1 model"]
      ]

     [:p
      ;[:a {:class "btn btn-primary btn-lg", :on-click #(dispatch [:set-active-panel (keyword "Get Started")])} "Get Started! »"]
      [:a {:class "btn btn-primary btn-lg", :href "#/Get Started" } "Get Started! »"]
      ]
     [:ul {:class "bs-masthead-links"}
      [:li
       [:a {:href "http://labkit.readthedocs.org/en/latest/"} "Read doc guides »"]
       ;[:a {:href "file:///Users/lhr/_env/sites/91/labkit/docs/_build/html/index.html"} "Read doc guides »"]
       ]
      [:li
       [:a {:href "https://groups.google.com/forum/?fromgroups#!forum/labkit"} "Join the Mailing List »"]]
      [:li
       [:a {:href "http://github.com/lhrkkk/labkit"} "Contribute »"]]]]
    (for [i (range 35)] [:br])
    ]

   #_[:div {:class "labkit-home-img"}
      [:img {:src "images/labkit.png"}]]]

  )
