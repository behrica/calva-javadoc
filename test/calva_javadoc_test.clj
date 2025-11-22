(ns test.calva-javadoc-test
  (:require [clojure.test :refer [deftest is]]
            [calva-javadoc :refer [to-class-instance-form view-javadoc-in-flare]]))

(deftest to-class-instance-form-tests
  (is (= "java.util.UUID/randomUUID" (to-class-instance-form '(java.util.UUID/randomUUID))))
  (is (= "java.util.UUID/.toString" (to-class-instance-form '(.toString uuid))))
  (is (= "java.util.UUID/.toString" (to-class-instance-form '(.toString ^UUID uuid))))
  (is (= "java.lang.String/.charAt" (to-class-instance-form '(String/.charAt))))
  (is (= "java.util.UUID/.toString" (to-class-instance-form '(.toString (java.util.UUID/randomUUID)))))
  (is (= "java.util.UUID/.getLeastSignificantBits" (to-class-instance-form '(.getLeastSignificantBits uuid))))
  (is (= "sun.util.calendar.ZoneInfo/.setRawOffset" (to-class-instance-form '(.setRawOffset tz)))))


(deftest view-javadoc-in-flare-test
  (let [flare (view-javadoc-in-flare '(.getLeastSignificantBits uuid))]
    (is (= 'flare/html (:tag flare)))))


(comment
  (def uuid (java.util.UUID/randomUUID))
  uuid
 (.toString uuid)
  
  )