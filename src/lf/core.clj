(ns lf.core)


(defmacro key-to-panel [title]
  `[~(symbol (name title))]
  )

