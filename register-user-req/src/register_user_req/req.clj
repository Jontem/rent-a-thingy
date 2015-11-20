(ns register-user-req.req
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [register-user-req.core :refer :all]
            ))

(deftest create-and-find-test
  "N�r vi har skapat en registration med ett username ska vi kunna hitta det"
  (testing "create-and-find"
    (do
      (let [responses (create-and-find)]
          (is (= 200 (:status (:insert responses))))
          (is (= 200 (:status (:select responses))))))))

(deftest duplicate-insert-test
  "Om vi f�rs�ker att spara tv� registreringar med samma username ska vi f� ett 500-fel p� den andra."
  (testing "duplicate-insert"
    (let [responses (double-insert)]
      (is (= 200 (:status (:first responses))))
      (is (= 500 (:status (:second responses)))))))

(defn -main
  []
  (run-tests 'register-user-req.req))