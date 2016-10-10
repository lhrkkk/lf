(ns lf.panels.nav
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



;<footer class="bs-footer" role="contentinfo">
;<div class="container">
;<p>This website was developed by the
;&nbsp;<a href='http://github.com/clojurewerkz'>ClojureWerkz Team</a>.</p>
;<p>Follow us on Twitter:
;&nbsp;<a href='http://twitter.com/clojurewerkz'>ClojureWerkz</a>,&nbsp;<a href='http://twitter.com/michaelklishin'>Michael Klishin</a>,&nbsp;<a href='http://twitter.com/ifesdjeen'>Alex P</a></p>
;</div>
;</footer>


;(defn footer [ link name]
;
;      [:footer {:class "bs-footer", :role "contentinfo"}
;              [:div {:class "container"}
;               [:p "This website was developed by the"
;                [:a {:href link} name] "."]
;               [:p "Follow us on Twitter:"
;                [:a {:href "http://twitter.com/clojurewerkz"} "ClojureWerkz"] ", "
;                [:a {:href "http://twitter.com/michaelklishin"} "Michael Klishin"] ", "
;                [:a {:href "http://twitter.com/ifesdjeen"} "Alex P"]]]])



;; =======================

(defn nav-old [page-list]
  "page-map: all page-content pair map
   page: the active page"
  [:div {:class "navbar navbar-inverse navbar-fixed-top"}
   [:div {:class "navbar-inner"}
    [:div {:class "container"}
     [:button {:type "button", :class "btn btn-navbar", :data-toggle "collapse", :data-target ".nav-collapse"}
      [:span {:class "icon-bar"}]
      [:span {:class "icon-bar"}]
      [:span {:class "icon-bar"}]]
     [:a {:class "brand", :href (str "#/" (first page-list))} (first page-list)]
     [:div {:class "nav-collapse collapse"}
      [:ul {:class "nav"}
       (for [name (rest page-list)]
         [:li {:class "active"} [:a {:href (str "#/" name)} name]])
       ]
      ]]]]
  )

(defn nav [page-list]
  "page-map: all page-content pair map
     page: the active page"

  [:header {:class "navbar navbar-inverse navbar-fixed-top bs-docs-nav", :role "banner"}
   [:div {:class "container"}
    [:div {:class "navbar-header"}
     [:button {:class "navbar-toggle", :type "button", :data-toggle "collapse", :data-target ".bs-navbar-collapse"}
      [:span {:class "sr-only"} "Toggle navigation"]
      [:span {:class "icon-bar"}]
      [:span {:class "icon-bar"}]
      [:span {:class "icon-bar"}]]
     [:a {:class "navbar-brand", :href (str "#/" (first page-list))} (first page-list)]
     ]
    [:nav {:class "collapse navbar-collapse bs-navbar-collapse", :role "navigation"}
     [:ul {:class "nav navbar-nav"}
      (for [name (rest page-list)]
        [:li {:class ""} [:a {:href (str "#/" name)} name]])
      ]]]])

