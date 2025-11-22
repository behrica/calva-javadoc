(ns test.calva-javadoc-test

  (:require [calva-javadoc :refer [to-class-instance-form view-javadoc-in-flare]]))



; test cases:
(assert (= "java.util.UUID/randomUUID" (to-class-instance-form '(java.util.UUID/randomUUID))))
(def uuid (java.util.UUID/randomUUID))
(assert (= "java.util.UUID/.toString" (to-class-instance-form '(.toString uuid))))
(assert (= "java.util.UUID/.toString" (to-class-instance-form '(.toString ^UUID uuid))))
(assert (= "java.lang.String/.charAt" (to-class-instance-form '(String/.charAt))))
(assert (= "java.util.UUID/.toString" (to-class-instance-form '(.toString (java.util.UUID/randomUUID)))))
(assert (= "java.util.UUID/.getLeastSignificantBits" (to-class-instance-form '(.getLeastSignificantBits uuid))))
(assert (= "sun.util.calendar.ZoneInfo/.setRawOffset" (to-class-instance-form '(.setRawOffset tz))))


(def flare (calva-javadoc/view-javadoc-in-flare '(.getLeastSignificantBits uuid)))
(assert (= 'flare/html (:tag flare)))
