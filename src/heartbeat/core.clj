(ns heartbeat.core
  (:gen-class)
  (:require (quil [core :as q]
                  [middleware :as m]))
  (:import (processing.opengl PShader)))


(defn setup-heart []
  (q/smooth)
  (q/frame-rate 24)
  {:heart (q/load-shape (clojure.java.io/resource "Heart.obj"))
   :beat-shader (q/load-shader (.toString (clojure.java.io/resource "heart-pump.frag"))
                               (.toString (clojure.java.io/resource "heart-pump.vert")))})

(defn draw-heart [state]
  (q/background 200)
  (q/ambient-light 30 30 30)
  (q/directional-light 255 255 255 1 1 0)
  (q/translate (/ (q/width) 2) (/ (q/height) 2) 0)
  (q/scale 2 -2 2)
  (.set (:beat-shader state) "TIME_FROM_INIT" (q/millis))
  (q/shader (:beat-shader state))
  (q/shape (:heart state)))

(defn -main []
  (q/defsketch heart3d
               :title "3D heart"
               :size [800 600]
               :setup setup-heart
               :draw draw-heart
               :middleware [m/fun-mode m/navigation-3d]
               :renderer :opengl))