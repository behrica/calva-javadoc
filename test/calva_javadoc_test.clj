(ns test.calva-javadoc-test
  
  (:require [calva-javadoc :refer [to-class-instance-form view-javadoc-in-flare]]))



; test cases:
(assert (= "java.util.UUID/randomUUID" (to-class-instance-form '(java.util.UUID/randomUUID))))
(def uuid (java.util.UUID/randomUUID))
(assert (= "java.util.UUID/.toString" (to-class-instance-form '(.toString uuid))))
(assert (= "java.lang.String/.charAt" (to-class-instance-form '(String/.charAt))))
(assert (= "java.util.UUID/.toString" (to-class-instance-form '(.toString (java.util.UUID/randomUUID)))))

(view-javadoc-in-flare '(java.util.UUID/randomUUID))



(java.util.UUID/randomUUID)