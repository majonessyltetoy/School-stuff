;; Jan Inge Lamo
;; Valentins Jonovs

;; X Tests
;; -------

(define (assert predicate)
  (eq? #t predicate))

(define (test-predicates suite-name predicates)
  (define (advance-to-next-predicate predicates)
    (display "- OK\n")
    (test-predicates-helper (cdr predicates)))

  (define (display-fail)
    (display "- FAIL\n")
    (display "Some tests failed\n\n"))
    
  (define (test-predicates-helper predicates)
    (if (null? predicates)
        (display "All tests passed\n\n")
        (begin 
          (if (assert (car predicates))
            (advance-to-next-predicate predicates)
            (display-fail)))))
  
  (display "Running test suite: ")
  (display (string-append suite-name "\n"))
  (test-predicates-helper predicates))

;; 1  Innkapsling, lokal tilstand og omgivelsesmodellen
;; ====================================================

;; a)

(define (make-counter)
  (let ((count 0))
    (lambda ()
      (set! count (+ count 1))
      count)))

(define count 42)
(define c1 (make-counter))
(define c2 (make-counter))

(test-predicates "Tests for 1a"
                 (list
                  (equal? 1 (c1))
                  (equal? 2 (c1))
                  (equal? 3 (c1))
                  (equal? 42 count)
                  (equal? 1 (c2))))

;; b) Se fil 1b.png

;; 2  Innkapsling, lokal tilstand, og message passing
;; ==================================================

;; a)

(define (make-stack stack)
  (lambda (arg . elements)
    (cond ((eq? arg 'push!)
           (let push-loop ()
             (if (not (null? elements))
                 (begin
                   (set! stack (cons (car elements) stack))
                   (set! elements (cdr elements))
                   (push-loop)))))
          ((eq? arg 'pop!)
           (if (not (null? stack))
               (set! stack (cdr stack))))
          ((eq? arg 'stack) stack)
          (else "Error! Unexpected input"))))

(define s1 (make-stack (list 'foo 'bar)))
(define s2 (make-stack '()))

(test-predicates "Tests for 2a"
                 (list
                  (begin (s1 'pop!) #t)
                  (equal? '(bar) (s1 'stack))
                  (begin (s2 'pop!) #t)
                  (begin (s2 'push! 1 2 3 4) #t)
                  (equal? '(4 3 2 1) (s2 'stack))))

;; b)

(define (push! stack-proc . args)
  (apply stack-proc 'push! args))

(define (pop! stack-proc)
  (stack-proc 'pop!))

(define (stack stack-proc)
  (stack-proc 'stack))

;; 3  Strukturdeling og sirkulære lister
;; =====================================

;; a)

;; Etter define

;; +---+---+    +---+---+    +---+---+    +---+---+    +---+---+
;; |   |   |--->|   |   |--->|   |   |--->|   |   |--->|   | \ |
;; +---+---+    +---+---+    +---+---+    +---+---+    +---+---+
;;   |            |            |            |            |      
;;   v            v            v            v            v      
;; +---+        +---+        +---+        +---+        +---+    
;; | a |        | b |        | c |        | d |        | e |    
;; +---+        +---+        +---+        +---+        +---+   

;; Eter set-cdr!

;;                 +-----------------------------+               
;;                 v                             |               
;;  +---+---+    +---+---+    +---+---+    +---+---+   
;;  |   |   |--->|   |   |--->|   |   |--->|   |   |   
;;  +---+---+    +---+---+    +---+---+    +---+---+   
;;    |            |            |            |              
;;    v            v            v            v                
;;  +-+-+        +-+-+        +-+-+        +-+-+        
;;  | a |        | b |        | c |        | d |        
;;  +---+        +---+        +---+        +---+         

;; set-cdr! peker om det som fortsetter etter d (pair celle 2) i (d e) til å b (pair celle 1).

;; b)

;;Før set-car!

;;  +---+---+    +---+---+    +---+---+
;;  |   |   |--->|   |   |--->|   | \ |
;;  +---+---+    +---+---+    +---+---+
;;    |            |            |      
;;    v            v            v      
;;  +-------+    +-------+    +-------+  
;;  | bring |    | a     |    | towel |  
;;  +-------+    +-------+    +-------+  

;; Etter  set-car!

;;    +------------+                   
;;    |            v                   
;;  +---+---+    +---+---+    +-------+
;;  |   |   |--->|   |   |--->|   | \ |
;;  +---+---+    +---+---+    +---+---+
;;                 |            |      
;;                 v            v      
;;               +-------+    +-------+  
;;               | a     |    | towel |  
;;               +-------+    +-------+  

;; set-car! peker om hode til den første cons cellen til cdr'en til den første cons cellen.

;; c)

;; Floyd's cycle-finding algorithm
(define (cycle? lst)
  (if (null? lst)
      #f
      (let cycle?-loop ((slow lst)
                        (fast (cdr lst)))
        (cond ((null? fast) #f)
              ((eq? slow fast) #t)
              ((null? (cdr fast)) #f)
              (else (cycle?-loop (cdr slow) (cddr fast)))))))

(define bar (list 'a 'b 'c 'd 'e))
(set-cdr! (cdddr bar) (cdr bar))
 
(define bah (list 'bring 'a 'towel))
(set-car! bah (cdr bah))
(set-car! (car bah) 42)

(test-predicates "Tests for 2a"
                 (list
                  (not (cycle? '(hey ho)))
                  (not (cycle? '(la la la)))
                  (not (cycle? bah))
                  (cycle? bar)))

;; d)

;; En liste er definert ved at cdr av den siste cellen er en tom liste.
;; Siden sikulære lister ikke slutter med en tom liste, er de ikke per definisjon lister.

;; e)

;; make-ring object
(define (make-ring lst)
  
  ;; Private functions
  
  (define (list->ring lst)
    (let iterator ((loop-lst lst))
      (cond ((null? lst) '())
            ((null? (cdr loop-lst))
             (begin
               (set-cdr! loop-lst lst)
               lst))
            (else (iterator (cdr loop-lst))))))
  
  (define (ring->list lst)
    (let iterator ((slow lst) (fast (cdr lst)) (out-lst '()))
      (if (eq? fast slow)
          (begin
            (set! out-lst (cons (car slow) out-lst))
            (reverse out-lst))
          (iterator (cdr slow) (cddr fast) (cons (car slow) out-lst)))))
  
  ;; Enviorment variables
  (let ((lst-length (length lst))
        (lst (list->ring lst)))
    
    ;; Object body
    (lambda (arg . elements)
      (cond ((eq? arg 'top)
             (if (not (null? lst))
                 (car lst)))
            ((eq? arg 'l-r)
             (if (not (null? lst))
                 (set! lst (cdr lst))))
            ((eq? arg 'r-r)
             (if (not (null? lst))
                 (set! lst (let r-r-loop ((ring lst)
                                          (count (- lst-length 1)))
                             (if (eq? 0 count)
                                 ring
                                 (r-r-loop (cdr ring) (- count 1)))))))
            ((eq? arg 'ins)
             (if (eq? lst-length 0)
                 (begin
                   (set! lst-length 1)
                   (set! lst (list->ring (cons (car elements) '()))))
                 (begin
                   (set! lst-length (+ lst-length 1))
                   (set! lst (list->ring (cons (car elements) (ring->list lst)))))))
            ((eq? arg 'del)
             (if (>= 1 lst-length)
                 (begin
                   (set! lst-length 0)
                   (set! lst '()))
                 (begin
                   (set! lst-length (- lst-length 1))
                   (set! lst (list->ring (cdr (ring->list lst)))))))
            (else "Error! Unexpected input")))))


(define (top ring-proc)
  (ring-proc 'top))

(define (right-rotate! ring-proc)
  (begin
    (ring-proc 'r-r)
    (ring-proc 'top)))

(define (left-rotate! ring-proc)
  (begin
    (ring-proc 'l-r)
    (ring-proc 'top)))

(define (delete! ring-proc)
  (begin
    (ring-proc 'del)
    (ring-proc 'top)))

(define (insert! ring-proc . args)
  (begin
    (apply ring-proc 'ins args)
    (ring-proc 'top)))

(define r1 (make-ring '(1 2 3 4)))
(define r2 (make-ring '(a b c d)))

(test-predicates "Tests for 2e"
                 (list
                  (eq? 1 (top r1))
                  (eq? 'a (top r2))
                  (eq? 4 (right-rotate! r1) )
                  (eq? 1  (left-rotate! r1))
                  (eq? 2 (left-rotate! r1))
                  (eq? 3 (delete! r1) )
                  (eq? 4 (left-rotate! r1))
                  (eq? 1 (left-rotate! r1))
                  (eq? 3 (left-rotate! r1))))

;; f)

;; insert! og delete! har tidskompleksitet O(2n) siden de kaller ring->list og
;; list->ring, og begge de to funksjonene har kompleksitet O(n).
;;
;; left-rotate! har konstant tidskompleksitet O(1), men right-rotate! har O(n)
;; tidskompleksitet siden den må gå gjennom listen for å finne det siste elementet.
;;
;; Alle prosedyrene har konstant minnebruk siden de er hale rekursive.


