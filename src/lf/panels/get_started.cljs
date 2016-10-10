(ns lf.panels.get-started
  (:require-macros [cljs.core.async.macros :refer [go]])
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
    ;[markdown.core :refer :all]
            [re-frame.core :refer [subscribe dispatch register-handler]]
            [markdown.core :as md]
            [hickory.core :refer [as-hiccup parse, parse-fragment]]

            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            ;[cljsjs.jquery :refer [$]]
            ;[cryogen-core.toc :refer [generate-toc]]
            ))



;(:use markdown.core)
;(:use hickory.core)


;(def st "labkit\n======\n\nlabkit for the calculation and analysis of bio-physical design platform.\nUser-submitted tasks automatically in parallel.\n\nlabkit also offers a number of comprehensive practical Python library ,\nincluding\n\n-   general (please refer to the general lib ), the framework and public\n    utility library\n-   compute\n-   ensemble\n\nQuick start\n===========\n\ninstall\n-------\n\nsimplely install labkit by\n\n> sh install.sh\n\nThis will install labkit and it's frontend.\n\nPreparation\n-----------\n\nyou need a linux cluster to run labkit\n\nin the cluster front you need beanstalkd and mongodb running\n\nin the cluster front server: labkit front\n\nin the cluster node : labkit worker\n\nand all above can be configured at the first time, and then you can only\nuse labkit push to submit your task. The wonderful journey is now\nstarted!\n\nConcept\n-------\n\nlabkit use yaml config file to describe algorithm, and when your config\nfile is written, then simplely push it to the server, the cluster will\ndo all other things for you and return the answer.\n\nwhen develop labkit, you can also easily add new modules and functions,\nand call them in the yaml config file. all your new module can also be\ndeal with correctly and automatically. also all labkit's origin module\nare running by this way. you can easily contribute labkit so it can grow\nup and become more useful.\n\nworkflow\n--------\n\ncompose a task.yml file, and the module\\_settings, then use\n`` ` labkit push ``\\` to push a task.\n\nlabkit will run the task in the cluster automatically in parallel, and\nfinally get the answer.\n\ntask.yml grammer\n----------------\n\nthe extension of yaml config file is .yml and the default config file\nname is task.yml if you run labkit push without any other arguments.\ntask.yml in current folder will be push.\n\n    list:\n\n     -   item1\n     -   item2\n\n    map:\n\n     key: value\n\n    rule:\n\n    :   -   \n\n            a list : // undefined key without single value will be ignored\n\n            :   -   a list item is a single task and will be\n                    evaluated sequentially.\n                    -   second item\n\n                    dict: function arguments, or assign a variable repeat: 100\n                    until: condition\n\nLearning more about labkit\n==========================\n\nlabkit is simple and easy use! the advanced usage is also simple and\npowerful! It's just what you needed! The next step on your labkit\njourney is the [full user guide](guide/index.html), where you can learn\nindepth about how to use labkit and develop your own algorithm.\n")

;
;
;(defn load-md-old [title]
;      (go (let [txt (<! (http/get (join ["/md/" title ".md"])))
;                field (.getElementById js/document "app")]
;               (set! (.-innerHTML field) (md->html (:body txt))))))
;
;(defn load-md [title]
;      (go (let [txt (<! (http/get (join ["/md/" title ".md"])))
;                field (.getElementById js/document "app")]
;               (set! (.-innerHTML field) (md->html (:body txt))))))

;(log/console)
;(.log js/console (<! (http/get (join ["/md/readme.md"]))))
;(.log js/console (http/get (join ["/md/readme.md"])))

(go (def txt (:body (<! (http/get "http://127.0.0.1:3450/md/readme.md")))
      ))



(def jquery (js* "$"))


(jquery
  (fn []
       (.toc (jquery "#toc"))
      ))

;$('#toc').toc()



(defn get-started []
      (let [st "labkit\n======\n\nlabkit for the calculation and analysis of bio-physical design platform.\nUser-submitted tasks automatically in parallel.\n\nlabkit also offers a number of comprehensive practical Python library ,\nincluding\n\n-   general (please refer to the general lib ), the framework and public\n    utility library\n-   compute\n-   ensemble\n\nQuick start\n===========\n\ninstall\n-------\n\nsimplely install labkit by\n\n> sh install.sh\n\nThis will install labkit and it's frontend.\n\nPreparation\n-----------\n\nyou need a linux cluster to run labkit\n\nin the cluster front you need beanstalkd and mongodb running\n\nin the cluster front server: labkit front\n\nin the cluster node : labkit worker\n\nand all above can be configured at the first time, and then you can only\nuse labkit push to submit your task. The wonderful journey is now\nstarted!\n\nConcept\n-------\n\nlabkit use yaml config file to describe algorithm, and when your config\nfile is written, then simplely push it to the server, the cluster will\ndo all other things for you and return the answer.\n\nwhen develop labkit, you can also easily add new modules and functions,\nand call them in the yaml config file. all your new module can also be\ndeal with correctly and automatically. also all labkit's origin module\nare running by this way. you can easily contribute labkit so it can grow\nup and become more useful.\n\nworkflow\n--------\n\ncompose a task.yml file, and the module\\_settings, then use\n`` ` labkit push ``\\` to push a task.\n\nlabkit will run the task in the cluster automatically in parallel, and\nfinally get the answer.\n\ntask.yml grammer\n----------------\n\nthe extension of yaml config file is .yml and the default config file\nname is task.yml if you run labkit push without any other arguments.\ntask.yml in current folder will be push.\n\n    list:\n\n     -   item1\n     -   item2\n\n    map:\n\n     key: value\n\n    rule:\n\n    :   -   \n\n            a list : // undefined key without single value will be ignored\n\n            :   -   a list item is a single task and will be\n                    evaluated sequentially.\n                    -   second item\n\n                    dict: function arguments, or assign a variable repeat: 100\n                    until: condition\n\nLearning more about labkit\n==========================\n\nlabkit is simple and easy use! the advanced usage is also simple and\npowerful! It's just what you needed! The next step on your labkit\njourney is the [full user guide](guide/index.html), where you can learn\nindepth about how to use labkit and develop your own algorithm.\n"
            ;txt (<! (http/get (join ["/md/" title ".md"])))
            ;txt (:body  (http/get "/md/readme.md"))
            ;txt (:body (<! (http/get "http://127.0.0.1:3450/md/readme.md")))
            ;txt "哈哈哈"
            ;
            ]
           (fn []
               [:div
                [:div.container
                 ;(md-to-html-string "# This is a test\nsome code follows\n```clojure\n(defn foo [])\n```")
                 ;"tutorial is here "
                 ;st
                 ;(as-hiccup (parse (md/md->html " ### hahah\n")))
                 ;(as-hiccup (http/get (join ["/md/" title ".md"])))

                 ;(str (get-in (as-hiccup (parse (md/md->html st))) [:html :body]))
                 ;(str (-> (as-hiccup (parse (md/md->html st))) last last ))

                 ;(str txt)

                 ;(map as-hiccup (parse-fragment (md/md->html st)))

                 [:div {:class "container"}
                  [:div {:class "row"}
                   [:div {:class "col-md-3 bs-docs-sidebar", :id "side-navigation"}
                    [:div {:class "bs-sidebar hidden-print affix-top", :role "complementary"}
                     [:ul {:id "toc", :class "nav bs-sidenav"}]]]
                   [:div {:class "col-md-9"}
                    ;"{{ content }}\n"
                    ;(generate-toc (md/md->html txt))
                    (map as-hiccup (parse-fragment (md/md->html txt)))
                    [:h2 "Tell Us What You Think!"]
                    [:p "Please take a moment to tell us what you think about this guide on Twitter or the "
                     [:a {:href "https://groups.google.com/forum/#!forum/clojure-rabbitmq"} "Clojure RabbitMQ mailing list"]]
                    [:p "Let us know what was unclear or what has not been covered. Maybe you do not like the guide style or grammar or discover spelling mistakes. Reader feedback is key to making the documentation better."]]]]








                 ;(md/md->html st)
                 ;(as-hiccup (parse (set! (.-innerHTML field) (md/md->html st))))
                 ]]))

      )
