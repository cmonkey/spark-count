#lang racket

(define l '(1 2 3 4 5 6))

(map
  (lambda (x)
    (* x 2)
    ) l)

(filter
  (lambda (x)
    (> x 5)
    )
  (map (lambda (x)
         (* x 2)
         ) l)
  )

(define l2 (range 1 (add1 10) 1))

(apply + l2)

(define rdd1 (list '("a" 1)
                 '("a" 2)
                 '("b" 1)
                 '("b" 3)
                 ))

(define rdd2 (list '("a" 3)
                 '("a" 4)
                 '("b" 1)
                 '("b" 2)
                 ))

(set-union rdd1 rdd2)
