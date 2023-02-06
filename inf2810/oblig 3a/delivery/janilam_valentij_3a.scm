;; Jan Inge Lamo
;; Valentins Jonovs

(load "prekode3a.scm")

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

;; 1 Prosedyrer for bedre prosedyrer
;; ---------------------------------

;; a) og b)

(define mem
  (let ((procs (make-table)))
    (lambda (message proc)
      (define (memoize)
        (let ((cache (make-table)))
          (lambda vars
            (if (lookup vars cache)
                (lookup vars cache)
                (begin
                  (let ((result (apply proc vars)))
                    (insert! vars result cache)
                    result))))))
      
      (define (unmemoize)
        (lookup proc procs))
      
      (define (dispatch message)
        (cond ((eq? 'memoize message)
               (let ((mem-proc (memoize)))
                 (insert! mem-proc proc procs)
                 mem-proc))
              ((eq? 'unmemoize message)
               (unmemoize))))
      
      (dispatch message))))

(define zerop (mem 'memoize (lambda (a b) (display "computing \n") (+ a b))))
(zerop 1 2)
(zerop 1 2)
(zerop 0 1)
(zerop 0 1)
(zerop 1 2)

(set! zerop (mem 'unmemoize zerop))
(zerop 1 2)

;; c)

;; Prosedyren fib kaller på seg selv internt ved å kalle på fib. Dette symbolet peker
;; fortsatt på den umemoiserte versjonen, og kall på denne prosedyren bypasser vår
;; prosedyre som bruker cache for å returnere resultater.

;; d)

(define (resolve-arguments default-args args)
  (define resolved-args (make-table))
  
  (if (eq? '*table* (car default-args))
      (set! resolved-args default-args))
  
  (define (traverse-pairs remaining-pairs)
    (if (not (null? remaining-pairs))
        (begin
          (insert! (car remaining-pairs) (cadr remaining-pairs) resolved-args)
          (traverse-pairs (cddr remaining-pairs)))))
  
  (traverse-pairs args)
  
  resolved-args)

(define (greet . args)
  (define default-args (make-table))
  (insert! 'time "day" default-args)
  (insert! 'title "friend" default-args)
  
  (define named-args (resolve-arguments default-args args))
  
  (string-append "good " (lookup 'time named-args) " " (lookup 'title named-args)))

(test-predicates "Tests for 1d"
                 (list
                  (equal? (greet) "good day friend")
                  (equal? (greet 'time "evening") "good evening friend")
                  (equal? (greet 'title "sir" 'time "morning") "good morning sir")
                  (equal? (greet 'time "afternoon" 'title "dear") "good afternoon dear")))


;; 2 Strømmer
;; ----------

;; a)

(define (list-to-stream lst)
  (if (null? lst)
      the-empty-stream
      (cons-stream (car lst) (list-to-stream (cdr lst)))))

(define (stream-to-list stream . options)
  (define (ss-rec stream i)
    (cond ((= i 0) '())
          ((stream-null? stream) '())
          (else
           (cons (stream-car stream) (ss-rec (stream-cdr stream) (- i 1))))))
  (ss-rec stream (if (null? options) -1 (car options))))

(test-predicates "Tests for 2a"
                 (list
                  (equal? (stream-to-list (stream-interval 0 15) 10) '(0 1 2 3 4 5 6 7 8 9))
                  (equal? (stream-to-list (stream-interval 0 5)) '(0 1 2 3 4 5))
                  (equal? (stream-to-list (list-to-stream '(0 1 2 3 4))) '(0 1 2 3 4))))

;; b)

(define (or-list . lst)
  (if (null? lst)
      #f
      (if (car lst)
          #t
          (apply or-list (cdr lst)))))

(define (stream-map proc . argstreams)
  (if (or (null? argstreams)
          (apply or-list (map stream-null? argstreams)))
      the-empty-stream
      (cons-stream
       (apply proc (map stream-car argstreams))
       (apply stream-map (cons proc (map stream-cdr argstreams))))))

(let ((stream (cons-stream 2 (cons-stream 2 (cons-stream 1 the-empty-stream)))))
  (test-predicates "Tests for 2b"
                   (list
                    (equal? the-empty-stream (stream-map + the-empty-stream))
                    (equal? '(2 2 1) (stream-to-list (stream-map + stream)))
                    (equal? '(6 6 3) (stream-to-list (stream-map + stream stream stream)))
                    (equal? '(4 4 1) (stream-to-list (stream-map * stream stream))))))


;; c)

;; memq leter aktivt etter etter elementer som er medlem i listen (i dette tilfelle
;; strømmen). Hvis den modifiserte memq får en uendelig lang strøm vil den prøve å
;; lete gjennom hele strømmen og bruke dermed uendelig lang tid.


;; d)

(define (remove-duplicates stream)
  (if (stream-null? stream)
      the-empty-stream
      (cons-stream (stream-car stream)
                   (stream-filter (lambda (x) (not (eq? x (stream-car stream))))
                                  (stream-cdr stream)))))

(let ((stream (cons-stream 2 (cons-stream 2 (cons-stream 1 the-empty-stream)))))
  (test-predicates "Tests for 2d"
                   (list
                    (equal? 2 (stream-ref (remove-duplicates stream) 0))
                    (equal? 1 (stream-ref (remove-duplicates stream) 1))
                    (equal? the-empty-stream (stream-cdr (stream-cdr (remove-duplicates stream)))))))


;; e)

;; ? (define x
;;     (stream-map show
;;                 (stream-interval 0 10)))
;; -> 0

;; (stream-interval 0 10) returnerer en strøm hvor det første elementet generert av strømmen er 0.
;; (stream-map show (stream-interval 0 10)) tar prosedyren show og strømmen som argumenter, og
;; returnerer en ny strøm. Her stream-map henter første element fra den opprinnelige strømmen.
;; Dette elementet går via show prosedyren som skriver ut elementet, i dette tilfellet '0'.

;;
;; ?  (stream-ref x 5)
;; -> 1
;;    2
;;    3
;;    4
;;    5
;;    5

;; Den nye strømmen x genererer nye elementer via show prosedyren. Hver gang prosedyren blir kalt,
;; blir nye elementer skrevet ut. Utskrift av 1, 2, 3, 4, 5 er en sideeffekt (display) av å kalle på
;; show, og 5 er returverdien av stream-ref.

;;
;; ?  (stream-ref x 7)
;; -> 6
;;    7
;;    7

;; I dette kallet vil stream-ref begynne å traversere strømmen på nytt, men siden de første 5 elementene
;; er memoisert (pga forrige kallet), går de ikke via show lenger. De to neste elementene 6 og 7 er ikke
;; memoisert, og disse går via show prosedyren som skriver ut tallene. Siste '7' er returverdien.

;; f)

(define (mul-streams s1 s2)
  (stream-map * s1 s2))

(define factorials
  (cons-stream 1 (mul-streams nats
                              factorials)))

(test-predicates "Tests for 2f"
                 (list
                  (equal? 1 (stream-ref factorials 0))
                  (equal? 1 (stream-ref factorials 1))
                  (equal? 120 (stream-ref factorials 5))
                  (equal? 40320 (stream-ref factorials 8))))