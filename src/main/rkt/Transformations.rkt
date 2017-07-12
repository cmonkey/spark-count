#lang racket

(define l '(1 2 3))

(define (my-map l)
  (map (lambda (x ) (list x x x)) l)
  )

(my-map l)
(apply append (my-map l))
