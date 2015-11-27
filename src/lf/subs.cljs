(ns lf.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub subscribe]]))


(register-sub
  :active-panel
  (fn
    [db _]                       ;; db is the app-db atom
    (reaction (:active-panel @db))))    ;; wrap the computation in a reaction
;

;; -- Subscription Handlers ---------------------------------------------------


(register-sub
  :timer
  (fn
    [db _]                       ;; db is the app-db atom
    (reaction (:timer @db))))    ;; wrap the computation in a reaction


(register-sub
  :time-color
  (fn
    [db _]
    (reaction (:time-color @db))))


(register-sub
  :queues
  (fn
    [db _]
    (reaction (:queues @db))))

(register-sub
  :tasks
  (fn
    [db _]
    (reaction (:tasks @db))))
;
(register-sub
  :workers
  (fn
    [db _]
    (reaction (:workers @db))))
;

(register-sub
  :tasks-rows
  (fn
    [db _]
    (let [tasks (reaction (:tasks @db))
          tasks-list (reaction (for [i (range (count @tasks))] {:name (:description (get @tasks i)) :sort i :id (str (inc i)) :to "" :from (:created_at (get @tasks i))}))
          task-map (reaction (into (sorted-map) (for [[i task] (map-indexed vector @tasks-list)] {(str (inc i)) task})))
          ]
      task-map
      )
    ))




(register-sub
  :db
  (fn
    [db _]
    (reaction @db)))





;
;
;;; -- Helpers -----------------------------------------------------------------
;
;
;(defn filter-fn-for
;      [showing-kw]
;      (case showing-kw
;            :active (complement :done)
;            :done   :done
;            :all    identity))
;
;
;(defn completed-count
;      "return the count of todos for which :done is true"
;      [todos]
;      (count (filter :done (vals todos))))
;
;
;;; -- Subscription handlers and registration  ---------------------------------
;
;(register-sub
;  :todos                ;; usage:  (subscribe [:todos])
;  (fn [db _]
;      (reaction (vals (:todos @db)))))
;
;(register-sub
;  :visible-todos
;  (fn [db _]
;      (reaction (let [filter-fn (filter-fn-for (:showing @db))
;                      todos     (vals (:todos @db))]
;                     (filter filter-fn todos)))))
;
;(register-sub
;  :completed-count
;  (fn [db _]
;      (reaction (completed-count (:todos @db)))))
;
;(register-sub
;  :footer-stats
;  (fn [db _]
;      (reaction
;        (let [todos (:todos @db)
;              completed-count (completed-count todos)
;              active-count    (- (count todos) completed-count)
;              showing         (:showing @db)]
;             [active-count completed-count showing]))))  ;; tuple
