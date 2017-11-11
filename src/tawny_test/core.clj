(ns tawny-test.core
    (:require [clojure.set]
            [tawny.read :as r]
            [tawny.owl :as o]
            [tawny.lookup :as l]
            [tawny.fixture :as f])
  (:import (org.semanticweb.owlapi.model IRI OWLNamedObject OWLOntologyID)
           (org.semanticweb.owlapi.util SimpleIRIMapper)))

(defn get-ontology [iri owl-file]
  (tawny.owl/remove-ontology-maybe
   (OWLOntologyID. (IRI/create iri)))
  (.loadOntologyFromOntologyDocument
   (tawny.owl/owl-ontology-manager)
   (IRI/create (clojure.java.io/resource owl-file))))

(def testing-ontologies {"https://www.w3.org/TR/owl-guide/food" "food.owl"
                         "http://purl.obolibrary.org/obo/genepio.owl" "genepio.owl"})

(defn make-ontologies [ontomap]
  (map #(apply get-ontology %) (into [] ontomap)))
