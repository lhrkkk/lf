(ns lf.panels.task
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

(defn data-row
  [row first? last? col-widths mouse-over click-msg]
  (let [mouse-over-row? (identical? @mouse-over row)]
    [h-box
     :class "rc-div-table-row"
     :attr {:on-mouse-over (handler-fn (reset! mouse-over row))
            :on-mouse-out  (handler-fn (reset! mouse-over nil))}
     :children [[h-box
                 :width (:sort col-widths)
                 :gap "2px"
                 :align :center
                 :children [[row-button
                             :md-icon-name "zmdi zmdi-arrow-back zmdi-hc-rotate-90"
                             :mouse-over-row? mouse-over-row?
                             :tooltip "Move this task up"
                             :disabled? (and first? mouse-over-row?)
                             :on-click #(reset! click-msg (str "move row " (:id row) " up"))]
                            [row-button
                             :md-icon-name "zmdi zmdi-arrow-forward zmdi-hc-rotate-90"
                             :mouse-over-row? mouse-over-row?
                             :tooltip "Move this task down"
                             :disabled? (and last? mouse-over-row?)
                             :on-click #(reset! click-msg (str "move row " (:id row) " down"))]]]
                [label :label (:name row) :width (:name col-widths)]
                [label :label (:from row) :width (:from col-widths)]
                #_[label :label (:to row) :width (:to col-widths)]
                [h-box
                 :gap "2px"
                 :width (:actions col-widths)
                 :align :center
                 :children [#_[row-button
                               :md-icon-name "zmdi zmdi-copy"
                               :mouse-over-row? mouse-over-row?
                               :tooltip "Copy this line"
                               :on-click #(reset! click-msg (str "copy row " (:id row)))]
                            #_[row-button
                               :md-icon-name "zmdi zmdi-edit"
                               :mouse-over-row? mouse-over-row?
                               :tooltip "Edit this line"
                               :on-click #(reset! click-msg (str "edit row " (:id row)))]
                            [row-button
                             :md-icon-name "zmdi zmdi-delete"
                             :mouse-over-row? mouse-over-row?
                             :tooltip "Delete this task"
                             :on-click #(reset! click-msg (str "delete task " (:id row)))]]]]]))

;
(defn data-table
  [col-widths]
  (let [rows (subscribe [:tasks-rows])
        large-font (reagent/atom false)
        mouse-over (reagent/atom nil)
        click-msg (reagent/atom "")]
    (fn []
      [v-box
       :align :start
       :gap "10px"
       :children [#_[checkbox
                     :label "Large font-size (row-buttons inherit their font-size from their parent)"
                     :model large-font
                     :on-change #(reset! large-font %)]
                  [v-box
                   :class "rc-div-table"
                   :style {:font-size (when @large-font "24px")}
                   :children [[h-box
                               :class "rc-div-table-header"
                               :children [[label :label "Sort" :width (:sort col-widths)]
                                          [label :label "Name" :width (:name col-widths)]
                                          [label :label "From" :width (:from col-widths)]
                                          #_[label :label "To" :width (:to col-widths)]
                                          [label :label "Actions" :width (:actions col-widths)]]]
                              (for [[_ row first? last?] (enumerate (sort-by :sort (vals @rows)))]
                                ^{:key (:id row)} [data-row row first? last? col-widths mouse-over click-msg])]]
                  [h-box
                   :gap "5px"
                   :width "300px"
                   :children [[:span "clicked: "]
                              [:span.bold (str @click-msg)]]]]])))


(defn row-button-demo
  []
  (let [                                                    ;selected-icon (reagent/atom (:id (first icons)))
        col-widths {:sort "2.6em" :name "35em" :from "14em" :to "4em" :actions "4.5em"}
        rerows (subscribe [:tasks-rows])
        rows {"1" {:name "labkit.compute.gaussian.count_words_at_url('http://www.baidu.com')", :sort 0, :id "1", :to "", :from "2015-11-15T08:10:23+00:00"}, "2" {:name "labkit.compute.gaussian.count_words_at_url('http://www.baidu.com')", :sort 1, :id "2", :to "", :from "2015-11-15T08:10:26+00:00"}, "3" {:name "labkit.compute.gaussian.count_words_at_url('http://www.baidu.com')", :sort 2, :id "3", :to "", :from "2015-11-16T03:00:15+00:00"}, "4" {:name "labkit.compute.gaussian.count_words_at_url('http://www.baidu.com')", :sort 3, :id "4", :to "", :from "2015-11-16T03:00:17+00:00"}, "5" {:name "labkit.compute.gaussian.count_words_at_url('http://www.baidu.com')", :sort 4, :id "5", :to "", :from "2015-11-16T03:00:18+00:00"}}

        old-rows {"1" {:id "1" :sort 0 :name "Time range 1" :from "18:00" :to "22:30"}
                  "2" {:id "2" :sort 1 :name "Time range 2" :from "18:00" :to "22:30"}
                  ;"2" {:id "2" :sort 1 :name "Time range 2 with some extra text appended to the end." :from "18:00" :to "22:30"}
                  "3" {:id "3" :sort 2 :name "Time range 3" :from "06:00" :to "18:00"}}
        ]
    (fn []

      [v-box
       :gap "20px"
       :children [[data-table col-widths]
                  ;[:div (str rows)]
                  ;[:div (str @rerows)]
                  #_[gap :size "40px"]
                  #_[line]
                  #_[title :level :level3 :label "Row Button States"]
                  #_[:p "Row buttons have three distinct states."]
                  #_[example-icons selected-icon]
                  #_[v-box
                     :gap "8px"
                     :children [[h-box
                                 :gap "2px"
                                 :align :center
                                 :children [[label :label "States: ["]
                                            [row-button
                                             :md-icon-name @selected-icon
                                             :mouse-over-row? false
                                             :tooltip ":mouse-over-row? set to false (invisible)"
                                             :on-click #()]
                                            [row-button
                                             :md-icon-name @selected-icon
                                             :mouse-over-row? true
                                             :tooltip ":mouse-over-row? set to true (semi-visible)"
                                             :on-click #()]
                                            [row-button
                                             :md-icon-name @selected-icon
                                             :tooltip ":disabled? set to true"
                                             :disabled? true
                                             :on-click #()]
                                            [label :label "]"]]]]]]]


      ;[v-box
      ; :size     "auto"
      ; :gap      "10px"
      ; :children [[panel-title  "[row-button ... ]"
      ;             "src/re_com/buttons.cljs"
      ;             "src/re_demo/row_button.cljs"]
      ;
      ;            [h-box
      ;             :gap      "100px"
      ;             :children [[v-box
      ;                         :gap      "10px"
      ;                         :width    "450px"
      ;                         :children [[title2 "Notes"]
      ;                                    [status-text "Stable"]
      ;                                    [p "Designed for tables which have per-row buttons. To avoid visual clutter, they only appear on row mouse-over."]
      ;                                    [p "To understand, mouse-over the table in the demo.  Notice that buttons appear for each row, muted initially, but more strongly as the mouse is over them specifically."]
      ;                                    [p "Notice also that these buttons can have an optional explanatory tooltip."]
      ;                                    [p "Material design icons, and their names, can be " [material-design-hyperlink "found here"] "."]
      ;                                    [args-table row-button-args-desc]]]
      ;                        [v-box
      ;                         :gap      "10px"
      ;                         :children [[title2 "Demo"]
      ;                                    ]]]]]]

      )))


;; core holds a reference to panel, so need one level of indirection to get figwheel updates
(defn tasks-list
  []
  [row-button-demo])

;; -- View Components ---------------------------------------------------------

;
;(defn request-tasks-button
;  []
;  [:div {:class    "button-class"
;         :on-click #(dispatch [:request-queues])}  ;; get data from the server !!
;   "Get the tasks json!"])
;

(defn empty-button []
  (let [tasks (subscribe [:tasks])]
    (fn []
      [button
       :label (if (empty? @tasks) "Emptied" "Empty")
       :tooltip "Empty the tasks list"
       :tooltip-position :below-center
       :disabled? (empty? @tasks)
       :on-click #(dispatch [:empty-tasks])
       :class "btn-danger"]
      )
    )
  )

(defn refresh-button []
  (let []
    (fn []
      [button
       :label "Refresh"
       :tooltip "Refresh the tasks list"
       :tooltip-position :below-center
       ;:disabled?         (empty? @tasks)
       :on-click (fn []
                   (#(dispatch [:request-tasks]))
                   (#(dispatch [:request-workers]))
                   )
       :class "btn btn-primary"]
      )
    )
  )



(defn ajax-text []
  (let [queues (subscribe [:queues])
        db (subscribe [:db])
        tasks (subscribe [:tasks])
        task-rows (subscribe [:tasks-rows])
        ]
    (fn []
      [:div "hello"]
      [:div (str @queues)]
      [:div (str (:description (get @tasks 1)))
       [empty-button]
       ;[clock]
       ]
      ))
  )

;; -------------------------
;; Views

;
(defn workers-list
  []
  (let [workers (subscribe [:workers])]
    [:div
     (for [i @workers]
       [:li (str i)]
       )

     ]))

(defn task []
  [:div
   [:div.container
    [:h2 "Labit Task management"]
    [tasks-list]

    [v-box
     :padding "10px"
     :align :start
     :gap "10px"
     :children [[refresh-button]
                [empty-button]
                ;[clock]
                ]]
    [workers-list]

    ;[ajax-text]
    ;[(str (subscribe [:db]))]
    ]])




;
;
;(defn todo-input [{:keys [title on-save on-stop]}]
;  (let [val (atom title)
;        stop #(do (reset! val "")
;                  (if on-stop (on-stop)))
;        save #(let [v (-> @val str clojure.string/trim)]
;               (if-not (empty? v) (on-save v))
;               (stop))]
;    (fn [props]
;      [:input (merge props
;                     {:type "text"
;                      :value @val
;                      :on-blur save
;                      :on-change #(reset! val (-> % .-target .-value))
;                      :on-key-down #(case (.-which %)
;                                     13 (save)
;                                     27 (stop)
;                                     nil)})])))
;
;(def todo-edit (with-meta todo-input
;                          {:component-did-mount #(.focus (reagent/dom-node %))}))
;
;(defn stats-footer
;  []
;  (let [footer-stats (subscribe [:footer-stats])]
;    (fn []
;      (let [[active done filter] @footer-stats
;            props-for (fn [filter-kw txt]
;                        [:a {:class (if (= filter-kw filter) "selected")
;                             :href (str "#/" (name filter-kw))} txt])]
;        [:footer.footer
;         [:div
;          [:span.todo-count
;           [:strong active] " " (case active 1 "item" "items") " left"]
;          [:ul.filters
;           [:li (props-for :all "All")]
;           [:li (props-for :active "Active")]
;           [:li (props-for :done "Completed")]]
;          (when (pos? done)
;            [:button.clear-completed {:on-click #(dispatch [:clear-completed])}
;             "Clear completed"])]]))))
;
;(defn todo-item
;  []
;  (let [editing (atom false)]
;    (fn [{:keys [id done title]}]
;      [:li {:class (str (if done "completed ")
;                        (if @editing "editing"))}
;       [:div.view
;        [:input.toggle {:type "checkbox"
;                        :checked done
;                        :on-change #(dispatch [:toggle-done id])}]
;        [:label {:on-double-click #(reset! editing true)} title]
;        [:button.destroy {:on-click #(dispatch [:delete-todo id])}]]
;       (when @editing
;         [todo-edit {:class "edit"
;                     :title title
;                     :on-save #(dispatch [:save id %])
;                     :on-stop #(reset! editing false)}])])))
;
;(defn todo-list
;  [visible-todos]
;  [:ul.todo-list
;   (for [todo  @visible-todos]
;     ^{:key (:id todo)} [todo-item todo])])
;
;(defn todo-app
;  []
;  (let [todos           (subscribe [:todos])
;        visible-todos   (subscribe [:visible-todos])
;        completed-count (subscribe [:completed-count])]
;    (fn []
;      [:div
;       [:section.todoapp
;        [:header#header
;         [:h1 "todos"]
;         [todo-input {:class "new-todo"
;                      :placeholder "What needs to be done?"
;                      :on-save #(dispatch [:add-todo %])}]]
;        (when-not (empty? @todos)
;          [:div
;           [:section.main
;            [:input.toggle-all
;             {:type "checkbox"
;              :checked (pos? @completed-count)
;              :on-change #(dispatch [:complete-all-toggle])}]
;            [:label {:for "toggle-all"} "Mark all as complete"]
;            [todo-list visible-todos]]
;           [stats-footer]])]
;       [:footer.info
;        [:p "Double-click to edit a todo"]]])))
;;
;;
;;(defn panel2
;;  []
;;  (fn []
;;    [todo-app]))

