#|
 * Innlevering 1b
 * Author: Jan Inge Lamo
 * Filename: oblig1b_janilam.scm
 * Date: 22.02.16
 *
 * Note: This delivery is actually same as the first delivery, I read some
 *       things and misunderstood what tail recursion means, fortunately it
 *       was false, and my first assumption was correct.
 |#


#lang r5rs

;; 1 Par og lister

;; a)
;;
;; _______
;; |  |  |
;; -------
;;  |   |
;; 47   11


;; b)
;;
;; _______
;; |  |()|
;; -------
;;  |
;; 47


;; c)
;;
;; _______    _______
;; |  |  | -> |  |()|
;; -------    -------
;;  |          |
;; 47         11


;; d)
;;
;; _______    _______
;; |  |  | -> |  |()|
;; -------    -------
;;  |          |
;; 47         _______    _______
;;            |  |  | -> |  |()|
;;            -------    -------
;;             |          |
;;            11         12


;; e)
;;
;; _______    _______    _______    _______
;; |  |  | -> |  |  | -> |  |  | -> |  |()|
;; -------    -------    -------    -------
;;  |          |          |          |
;;  |          1          2          3 
;;  |
;; _______    _______    _______
;; |  |  | -> |  |  | -> |  |()|
;; -------    -------    -------
;;  |           |          |
;;  1           2          3


;; f)
;;
;; (car (cdr (cdr '(1 2 3 4))))


;; g)
;;
;; (car (car (cdr '((1 2) (3 4)))))


;; h)
;;
;; (car (car (cdr (cdr '((1) (2) (3) (4))))))


;; i)
;;
;; cons:
;;        (cons (cons 1 (cons 2 '())) (cons (cons 3 (cons 4 '())) '()))
;;
;; list:
;;        (list (list 1 2) (list 3 4))




;; 2 Rekursjon over lister og høyereordens prosedyrer

;; a)
(define (length2 lst)
  (let iter ((lst lst) (counter 0))
    (if (null? lst)
        counter
        (iter (cdr lst) (+ counter 1)))))


;; b)
(define (rev-list lst)
  (let iter ((in lst) (out '()))
    (if (null? in)
        out
        (iter (cdr in)
              (cons (car in) out)))))


;; c)
;;
;; This implementation of 'ditch' is not tail recursive. This is because of the
;; 'cons' that has to add together each element after when the procedure is done
;; parsing through the list. Which will result in O(n) space complexity, however
;; if all the objects are the number we want to ditch, then space complexity is
;; constant O(1). The function has linear O(n) time complexity anyway, since we
;; have to go through the entire list.
(define (ditch n lst)
  (cond ((null? lst) '())
        ((= n (car lst))
         (ditch n (cdr lst)))
        (else (cons (car lst) 
                    (ditch n (cdr lst))))))


;; d)
(define (nth n lst)
  (if (zero? n)
      (car lst)
      (nth (- n 1) (cdr lst))))


;; e)
(define (where n lst)
  (let iter ((x 0) (lst lst))
    (cond ((null? lst) #f)
          ((= n (car lst)) x)
          (else (iter (+ x 1) (cdr lst))))))


;; f)
(define (map2 proc lst1 lst2)
  (if (or (null? lst1) (null? lst2))
      '()
      (cons (proc (car lst1) (car lst2))
            (map2 proc (cdr lst1) (cdr lst2)))))

;; How will we ever become anything if we never reach for the stars?
;; This map function take one or more lists and applies 'proc' to every corresp-
;; onding element in all the lists and returns a list with the result.
(define (map3 proc . lsts)
  (define (get-elements proc lst) ;; get the car or cdr of every list
    (if (null? lst)
        '()
        (cons (proc (car lst))
              (get-elements proc (cdr lst)))))
  (let map3-loop ((lst lsts)) ;; we need a loop because map3 is a variadic
    (if (let loop ((lst lst)) ;; function
          (cond ((null? lst) #f)
                ((null? (car lst)) #t) ;; one of the lists is empty!
                (else (loop (cdr lst)))))
        '()
        (cons (apply proc
                     (get-elements car lst))
              (map3-loop (get-elements cdr lst))))))


;; g)
(map2 (lambda (x y)
        (/ (+ x y) 2))
      '(1 2 3 4)
      '(3 4 5))

(map2 (lambda (x y)
        (and (even? x)
             (even? y)))
      '(1 2 3 4)
      '(3 4 5))


;; h)
(define (both? proc)
  (lambda (x y)
    (and (proc x)
         (proc y))))


;; i)
(define (self proc)
  (lambda (x)
    (proc x x)))

