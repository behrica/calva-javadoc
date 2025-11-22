(ns calva-javadoc 
  (:require
   [clojure.string :as str]
   [clojure.java.doc.api]
   [clojure.repl.deps]))

(defn to-class-instance-form [form]
       ;;; their are probaly more cases
    ;(println :form form)
    (cond (symbol? form) (.getName (class (eval form)))
          :else
          (let [f (first form)]
            ;(println :f f)
            ;(println "(str f)" (str f))
            (if (.startsWith (str f)  ".")
              (str  (.getName (class (eval (second form)))) "/" f)
              (let [splits (str/split (str f) (re-pattern "/"))
                    _ (println :splits splits)
                    evaled (eval (symbol (first splits)))
                    _ (println :evaled evaled)
                    class-name (.getName evaled)]
                (format "%s/%s" class-name (second splits)))))))


(defn view-javadoc-in-flare [my-form]
  (let [class-instance-form-str (to-class-instance-form my-form)
        jdoc-info  (clojure.java.doc.api/javadoc-data-fn class-instance-form-str nil)
        javadoc-html
        (if (str/includes? (str class-instance-form-str) "/")
          (str/join "\n"
                    (map
                     (fn [method-doc] (:method-description-html method-doc))
                     (-> jdoc-info :selected-method)))
          (:class-description-html jdoc-info)
          
          )]
    (println (format "Fetched javadoc for %s" class-instance-form-str))
    ;(println :class-instance-form-str class-instance-form-str)

    (tagged-literal 'flare/html {:html javadoc-html
                                 :key "javadoc"
                                 :title "javadoc"})))


(comment 
  (do
    (clojure.repl.deps/add-lib 'io.github.clojure/java.doc {:git/tag "v0.1.2" :git/sha "fc518b1"})
    (require '[clojure.java.doc.api])

    

    (let [class-instance-form-str (to-class-instance-form (java.util.UUID/randomUUID))
          jdoc-info  (clojure.java.doc.api/javadoc-data-fn class-instance-form-str nil)]
      (println (format "Fetched javadoc for %s" class-instance-form-str))
      (println :jdoc-info jdoc-info)
      

      (tagged-literal 'flare/html {:html (format "%s"
                                                  ;(:class-description-html jdoc-info)
                                                 (str/join "\n"
                                                           (map
                                                            (fn [method-doc] (:method-description-html method-doc))
                                                            (-> jdoc-info :selected-method)))),
                                   :key "javadoc"
                                   :title "javadoc"})))
  )
   
(comment 
  (clojure.java.doc.api/javadoc-data-fn  
   "sun.util.calendar.ZoneInfo/.setRawOffset" nil)
  ;;=> Execution error (FileNotFoundException) at sun.net.www.protocol.http.HttpURLConnection/getInputStream0 (HttpURLConnection.java:2024).
  ;;   https://docs.oracle.com/en/java/javase/21/docs/api/java.base/sun/util/calendar/ZoneInfo.html
  ;;   
  )
