(load "huffman.scm")

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
  

;; 1 DIVERSE
;; =========

;; a)

(define (p-cons x y)
  (lambda (proc) (proc x y)))

(define (p-car proc)
  (proc (lambda (x y) x)))

(define (p-cdr proc)
  (proc (lambda (x y) y)))

;; Test suite
;; ----------

(test-predicates "Tests for 1a"
                 (list
                  (procedure? (p-cons "foo" "bar"))
                  (eq? "foo" (p-car (p-cons "foo" "bar")))
                  (eq? "bar" (p-cdr (p-cons "foo" "bar")))
                  (eq? "foo" (p-car (p-cdr (p-cons "zoo" (p-cons "foo" "bar")))))))


;; b) 

;; Let-uttrykkene evalueres til forskjellige verdier, fordi foo er definert annerledes
;; i scopet til det andre let-uttrykket.

;; Test suite
;; ----------

(define foo 30)

(test-predicates "Tests for 1b"
                 (list
                  (eq? 80 ((lambda (x y)
                             (+ foo x y))
                           foo 20))
                  (eq? 40 ((lambda (foo)
                             ((lambda (x y)
                                (+ foo x y))
                              foo 20))
                           10))))


;; c)

(define a1 (list 1 2 3 4))
(define a2 (list + - * /))
(define a3 (list 5 6 7 8))
;; (map (lambda (x y z) (y x z))
;;     a1 a2 a3)

;; Resultatet blir (6 -4 21 1/2)
;; Map tar en prosedyre som første operand og så et vilkårlig antall lister.
;; La x, y, z være elementer fra alle lister på samme posisjon. Map sender x, y, z som operander
;; til lambda-prosedyren, som tolker y som en prosedyre, f.eks. +, og x,z som operandene.
;; Resultater fra operasjonene samles i en liste som returneres av map.

;; Se testene 2 og 3 for å se hvordan den anonyme prosedyren brukes uten map.

;; Test suite
;; ----------

(test-predicates "Tests for 1c"
                 (list
                  (equal? '(6 -4 21 1/2) (map (lambda (x y z) (y x z))
                                        a1 a2 a3))
                  (equal? 4 ((lambda (x y z) (y x z)) 2 + 2))
                  (equal? 1 ((lambda (x y z) (y x z)) 2 / 2))))



;; 2 HUFFMAN-KODING
;; ================

;; a)

(define (member? atom lst)
  (cond ((null? lst) #f)
        ((eq? atom (car lst)) #t)
        (else (member? atom (cdr lst)))))

;; Test suite
;; ----------

(test-predicates "Tests for 2a"
                 (list
                  (not (member? 'forrest '(lake river ocean)))
                  (member? 'river '(lake river ocean))))


;; b)
;; Vi bruker decode-1 fordi vi ønsker å kunne få tilgang til hele treet `tree` (konstant) og et
;; subtre `current-branch` som endrer seg ved hvert kall til decode-1.
;; Har vi ikke den interne prosedyren, blir det ikke mulig å gjøre den distinksjonen mellom trær.


;; c)

(define (decode bits tree)
  (let iter ((bits bits) (current-branch tree) (out '()))
    (if (null? bits)
        out
        (let ((next-branch
               (choose-branch (car bits) current-branch)))
          (if (leaf? next-branch)
              (iter (cdr bits) tree (append
                                     out 
                                     (list (symbol-leaf next-branch))))
              (iter (cdr bits) next-branch out))))))

;; Test suite
;; ----------

(test-predicates "Tests for 2c"
                 (list
                  (equal? (decode '(0 1 0 0 1 1 1 1 1 0) sample-tree)
                          '(ninjas fight ninjas by night))))


;; d) (decode sample-code sample-tree) -> (ninjas fight ninjas by night)


;; e) 

(define (encode symbol-list root)
  (define (encode-symbol symbol current-branch)
    (cond ((or (not (member? symbol (symbols current-branch)))
               (leaf? current-branch))
           '())
          ((member? symbol (symbols (left-branch current-branch)))
           (cons 0 (encode-symbol symbol (left-branch current-branch))))
          (else 
           (cons 1 (encode-symbol symbol (right-branch current-branch))))))
  
  (if (null? symbol-list)
      '()
      (append (encode-symbol (car symbol-list) root)
              (encode (cdr symbol-list) root))))

;; Test suite
;; ----------

(test-predicates "Tests for 2e"
                 (list
                  (equal? (encode '(ninjas fight ninjas by night) sample-tree)
                          sample-code)))


;; f)

(define (grow-huffman-tree pairs)
  (define (successive-merge tree)
    (if (= (length tree) 1)
        (car tree)
        (successive-merge (adjoin-set
                           (make-code-tree (car tree) (cadr tree))
                           (cddr tree)))))
  (successive-merge (make-leaf-set pairs)))


;; g)

(define ninja-symbols '((samurais 57) (ninjas 20) (fight 45) (night 12) (hide 3)
                                        (in 2) (ambush 2) (defeat 1) (the 5) (sword 4) (by 12)
                                        (assassin 1) (river 2) (forest 1) (wait 1) (poison 1)))

(define ninja-tree (grow-huffman-tree ninja-symbols))

;; Hvor mange bits bruker det på å kode følgende melding?
;; 43

;; Hva er den gjennomsnittlige lengden på hvert kodeord som brukes?
;; 81 / 16 = 5 1/16

;; Hva er det minste antall bits man ville trengt for å kode meldingen med en kode med fast lengde (fixed-length code) over det samme alfabetet? 
;; Det minste antallet bits er 68 hvis hvert enkodede symbol har lengde 4 bits.

(define message (list 'ninjas 'fight 'ninjas 'fight 'ninjas 'ninjas 'fight 'samurais
                      'samurais 'fight 'samurais 'fight 'ninjas 'ninjas 'fight 'by 'night))
(define bits (encode message ninja-tree))

;; Test suite
;; ----------

(test-predicates "Tests for 2g"
                 (list
                  (equal?
                   '(0 1 0 1 0 0 1 0 1 0 0 1 0 0 1 0 1 0 1 1 1 1 1 0 1 1 1 0 0 1 0 0 1 0 1 0 0 1 1 1 0 0 0)
                   bits)
                  (= 43 (length bits))))


;; h) 

(define (huffman-leaves tree)
  (cond ((null? tree) '())
        ((and (list? tree)
              (leaf? tree))
         (list (cdr tree)))
        ((list? tree) (append (huffman-leaves (left-branch tree))
                              (huffman-leaves (right-branch tree))))
        (else '())))

;; Test suite
;; ----------
                  
(test-predicates "Tests for 2h"
                 (list
                  (equal? '((ninjas 8) (fight 5) (night 1) (by 1))
                          (huffman-leaves sample-tree))))


;; i)

(define (sum-proc proc list)
  (define (sum-helper list count)
    (if (null? list)
        count
        (sum-helper (cdr list) (+ (proc (car list)) count))))
  (sum-helper list 0))

(define (sum list)
  (sum-proc (lambda (x) x) list))
     
(define (expected-code-length tree)
  (let ((list (huffman-leaves tree)))
    (define total (sum-proc (lambda (pair) (cadr pair)) list))
    
    (define (symbol-probability pair)
      (/ (cadr pair) total))
    
    (define (code-length pair)
      (length (encode pair tree)))
    
    (define (multiply pair)
      (* (symbol-probability pair) (code-length pair)))
    
    (sum (map multiply list))))

;; Test suite
;; ----------

(test-predicates "Tests for 2i"
                 (list
                  (= (+ 1 (/ 3 5)) (expected-code-length sample-tree))))

;; Hvilke kriterier må foreligge for at den faktiske gjennomsnittlige lengden på kodeordene i en melding tilsvarer den forventede gjennomsnittslengden slik den er definert over?
;; Frekvensen(vekten) av hvert ord i meldingen må være proposjonal med vekten ordet har i treet.

