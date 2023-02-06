;;#lang r5rs

#|
 * Innlevering 1a
 * Author: Jan Inge Lamo
 * Filename: oblig1a_janilam.scm
 * Date: 29.01.16
|#



;; Oppgave 1


;; a) Evaluates to 30


;; b) Lisp expect the first atom after an open parenthesis to be a procedure.
;;    However '5' is not a procedure or even a valid procedure name.


;; c) Same error as (b), this time someone has mistaken lisp for an infix
;;    notation language. The operator must come first, then the operands.


;; d) bar is assigned the value 21. In the line under bar is evaluated, it'll
;;    return it's value, namely 21.


;; e) bar is treated as an ordinary number, the expression evaluates to 10


;; f) We still treat bar like 21, thus the math problem become:
;;    (21*3*4*1)/21 = 3*4 = 12



;; Oppgave 2


;; a) The first expression evaluate to "piff!" because the 'or' operator
;;    evaluates each expression in turn, and if it is true it'll return the
;;    value of the true expression. Strings and numbers are true, they evaluate
;;    to themselves.
;;
;;    The second expression returns #f because the first expression 'and' check
;;    is false. Once 'and' find a false expression it returns the value of the
;;    expression, in this case false.
;;
;;    The third expression evaluate to "poff!", the predicate is true, so the
;;    'if' operator evaluates the next expression, "poff!" evaluates to "poff!".
;;
;;    These three operators are all special form because they do not evaluate
;;    the inner parenthesis in a nested expression. Even though all three of
;;    them were written erroneous, none of them ran into errors because the
;;    conditions to evaluate them never actualized.
;;
;;    It should be noted that there are many special form procedures.
;;    For instance 'cond' is as special form as 'if'.


;; b)
(define (sign-if n)
    (if (> n 0)
        1
        (if (< n 0)
            -1
            0)))

(define (sign-cond n)
    (cond ((> n 0) 1)
          ((< n 0) -1)
          (else 0)))


;; c)
(define (sign-and-or n)
  (or (and (positive? n) 1)
      (and (negative? n) -1)
      0))



;; Oppgave 3


;; a)
(define (add1 n)
  (+ n 1))
(define (sub1 n)
  (- n 1))


;; b)
(define (plus x y)
    (cond ((zero? y) x)
          ((positive? y) (plus (add1 x) (sub1 y)))
          (else (plus (sub1 x) (add1 y)))))


;; c)
(define (plus-recursive x y)
  (cond ((zero? y) x)
        ((positive? y) (add1 (plus x (sub1 y))))
        (else (sub1 (plus x (add1 y))))))

;; The procedure 'plus' in (b) is iterative while (c) is recursive.
;; The difference between the two is that while the iterative changes both its
;; parameters on every recursion, ordinary recursion only changes one parameter,
;; calculating the answer after it's satisfied with the parameter, compared to
;; iterative which is done with the parameter and the answer at the same time.


;; d)
(define (power-close-to b n)
  (define (power-iter e)
    (if (> (expt b e) n)
        e
        (power-iter (+ 1 e))))
  (power-iter 1))

;; When we move the iterative procedure inside the primary procedure, then we
;; can remove duplicate parameters. Since 'power-iter' is inside the scope of
;; 'power-close-to' will it have access to the b and n parameter. We remove the
;; unnecessary parameters from the 'power-iter'.


;; e)
(define (fib n)
  (define (fib-iter a b count)
    (if (= count 0)
        b
        (fib-iter (+ a b) a (- count 1))))
  (fib-iter 1 0 n))

;; We don't have to simplify the internal definition, all the parameters are
;; in good use with no excess computation. However we could define 'fib-iter'
;; using 'let', so that we could remove the '(fib-iter 1 0 n)' initiation line.
;; We would get something like this:

(define (fib-improved n)
  (let fib-iter ((a 1) (b 0) (count n))
    (if (= count 0)
        b
        (fib-iter (+ a b) a (- count 1)))))

;; This is arguably better and is probably something we will learn about later
;; in the course. However it doesn't answer the exercise question ("is it
;; possible to simplify the internal definiton"). While it is the proper method.
;; It is probably not the correct answer in this exercise.
