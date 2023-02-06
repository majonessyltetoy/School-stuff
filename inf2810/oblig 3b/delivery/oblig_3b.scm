;; Innlevering 3b

(load "evaluator.scm")

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

;; 1 Bli kjent med evaluatoren
;; ---------------------------

;; a)

;; (foo 2 square) -> 0
;; Den lokale variabelen cond i prosedyren foo blir bundet til 2. Siden cond er lik 2, blir
;; cond-uttrykket evaluert til 0

;; (foo 4 square) -> 16
;; Den lokale variabelen cond i prosedyren foo blir bundet til 4. Siden cond ikke er lik 2, blir
;; else-delen av uttrykket evaluert. Else-delen består av et kall på en prosedyre bundet til else
;; cond (i dette tilfelle 4) som argument. Siden `else` er bundet til square, blir uttrykket
;; evaluert til 16.

;; (cond ((= cond 2) 0)
;;        (else (else 4))) -> 2
;; 
;; Siden cond er bundet til 3 i den globale omgivelsen, blir else-delen av cond-uttrykket evaluert.
;; Else-delen kaller på en prosedyre `else` som er bundet til en prosedyre som deler argumentet med 2.
;; Kallet på (else 4), gir derfor 2.

;; Prosedyren mc-eval sjekker først om ytterste uttrykket bl.a. er en special form, og etter det
;; om uttrykket er et prosedyrekall.
;; Siden uttrykket 
;;   (special-form?
;;     '(cond ((= cond 2) 0)
;;            (else (else 4))))
;; blir evaluert til #t, vil cond-uttrykket videre bli evaluert på en annen måte, og cond-delen vil bli bare
;; ansett som et syntaktisk element som ikke er bundet til noe som helst.
;; Videre vil uttrykket (special-form? '(= cond 2)) evaluere til #f, og da vil dette (= cond 2)-utrykket
;; bli tolket som et vanlig prosedyrekall. Videre vil cond-utrykket i (= cond 2) bli identifisert
;; som en variabel, og dens verdi vil bli hentet. Lignende oppførsel også gjelder for forekomster
;; av else.

;; 2 Primitiver/innebygde prosedyrer
;; ---------------------------------

;; a)

(define primitive-procedures
  (list (list 'car car)
        (list 'cdr cdr)
        (list 'cons cons)
        (list 'null? null?)
        (list 'not not)
        (list '+ +)
        (list '- -)
        (list '* *)
        (list '/ /)
        (list '= =)
        (list 'eq? eq?)
        (list 'equal? equal?)
        (list 'display
              (lambda (x) (display x) 'ok))
        (list 'newline
              (lambda () (newline) 'ok))
        ;; START
        (list '1+ (lambda (n) (+ n 1)))
        (list '1- (lambda (n) (- n 1)))
        ;; END
        ))

(set! the-global-environment (setup-environment))

(test-predicates "Tests for 2a"
                 (list
                  (eq? 3 (mc-eval '(1+ 2) the-global-environment))
                  (eq? 1 (mc-eval '(1- 2) the-global-environment))))

;; b)

(define (install-primitive! name proc)
  (set-car! (car the-global-environment) (cons name (caar the-global-environment)))
  (set-cdr! (car the-global-environment) (cons (list 'primitive proc) (cdar the-global-environment))))

(install-primitive! 'square (lambda (x) (* x x)))

(test-predicates "Tests for 2b"
                 (list
                  (eq? 4 (mc-eval '(square 2) the-global-environment))))

;; 3 Nye special forms og alternativ syntaks

;; a)

(define (special-form? exp)
  (cond ((quoted? exp) #t)
        ((assignment? exp) #t)
        ((definition? exp) #t)
        ((if? exp) #t)
        ((lambda? exp) #t)
        ((begin? exp) #t)
        ((cond? exp) #t)
        ;; START
        ((and? exp) #t)
        ((or? exp) #t)
        ;; END
        (else #f)))

(define (eval-special-form exp env)
  (cond ((quoted? exp) (text-of-quotation exp))
        ((assignment? exp) (eval-assignment exp env))
        ((definition? exp) (eval-definition exp env))
        ((if? exp) (eval-if exp env))
        ((lambda? exp)
         (make-procedure (lambda-parameters exp)
                         (lambda-body exp)
                         env))
        ((begin? exp)
         (eval-sequence (begin-actions exp) env))
        ((cond? exp) (mc-eval (cond->if exp) env))
        ;; START
        ((and? exp) (eval-and exp env))
        ((or? exp) (eval-or exp env))
        ;; END
        ))

;; AND
(define (and? exp)
    (and (tagged-list? exp 'and)
         (not (null? (and-operands exp)))))

(define (and-operands exp) (cdr exp))

(define (eval-and exp env)
    (define (check-exp rest last)
        (if (null? rest)
            (mc-eval last env)
            (if (eq? #f (mc-eval (car rest) env))
                #f
                (check-exp (cdr rest) (car rest)))))
    (check-exp (and-operands exp) #f))

;; OR
(define (or? exp)
    (and (tagged-list? exp 'or)
         (not (null? (or-operands exp)))))

(define (or-operands exp) (cdr exp))

(define (eval-or exp env)
    (define (check-exp rest)
        (if (null? rest)
            #f
            (let ((current (mc-eval (car rest) env)))
                (if (not (eq? #f current))
                    (mc-eval current env)
                    (check-exp (cdr rest))))))
    (check-exp (or-operands exp)))


(test-predicates "Tests for 3a"
                 (list
                  (eq? 1 (mc-eval '(and 1) the-global-environment))
                  (eq? #f (mc-eval '(and 1 #f) the-global-environment))
                  (eq? 1 (mc-eval '(or 1) the-global-environment))
                  (eq? 2 (mc-eval '(or #f 2 1) the-global-environment))
                  (eq? #f (mc-eval '(or #f #f #f) the-global-environment))))

;; b)

(define (if-predicates-statements exp)
  (define (create-list rest)
    (cond ((or (eq? 'if (car rest)) (eq? 'elsif (car rest)))
           (cons (list (cadr rest) (cadddr rest)) (create-list (cddddr rest))))
           ((eq? 'else (car rest))
            (list (list #t (cadr rest))))))
  (create-list exp))

(define (eval-if exp env)
  (define (check rest)
    (if (true? (mc-eval (caar rest) env))
        (mc-eval (cadar rest) env)
        (check (cdr rest))))
  
  (check (if-predicates-statements exp)))

(test-predicates "Tests for 3b"
                 (list
                  (equal?
                   '((<test1> <utfall1>) (<test2> <utfall2>) (#t <utfall3>))
                   (if-predicates-statements '(if <test1>
                                                  then <utfall1>
                                                  elsif <test2>
                                                  then  <utfall2>
                                                  else <utfall3>)))
                  (equal?
                   '((<test1> <utfall1>) (#t <utfall2>))
                   (if-predicates-statements '(if <test1>
                                                  then <utfall1>
                                                  else <utfall2>)))

                  (equal?
                   'yes
                   (eval-if '(if (eq? 2 2) then 'yes else 'no) the-global-environment))
                  (equal?
                   'no
                   (eval-if '(if (eq? 2 1) then 'yes else 'no) the-global-environment))
                  (equal?
                   "3 is 3"
                   (eval-if '(if (eq? 2 1) then 'yes elsif (eq? 3 3) then "3 is 3" else 'no) the-global-environment))))

;; c)

(define (special-form? exp)
  (cond ((quoted? exp) #t)
        ((assignment? exp) #t)
        ((definition? exp) #t)
        ((if? exp) #t)
        ((lambda? exp) #t)
        ((begin? exp) #t)
        ((cond? exp) #t)
        ;; START
        ((and? exp) #t)
        ((or? exp) #t)
        ((let? exp) #t)
        ;; END
        (else #f)))

(define (eval-special-form exp env)
  (cond ((quoted? exp) (text-of-quotation exp))
        ((assignment? exp) (eval-assignment exp env))
        ((definition? exp) (eval-definition exp env))
        ((if? exp) (eval-if exp env))
        ((lambda? exp)
         (make-procedure (lambda-parameters exp)
                         (lambda-body exp)
                         env))
        ((begin? exp)
         (eval-sequence (begin-actions exp) env))
        ((cond? exp) (mc-eval (cond->if exp) env))
        ;; START
        ((and? exp) (eval-and exp env))
        ((or? exp) (eval-or exp env))
        ((let? exp) (eval-let exp env))
        ;; END
        ))

(define (let? exp)
  (eq? (car exp) 'let))

(define (let-params exp)
  (map car (cadr exp)))

(define (let-values exp)
  (map cadr (cadr exp)))

(define (let-body exp) (cddr exp))

(define (eval-let exp env)
  (mc-eval (cons (make-lambda (let-params exp) (let-body exp)) (let-values exp)) env))

(test-predicates "Tests for 2c"
                 (list
                  (eq? 3 (eval-let '(let ((a 1) (b 2)) (+ a b)) the-global-environment))))

;; d)

(define (let-assignment proc exp)
  (define (create-list rest)
    (cond ((or (eq? 'let (car rest)) (eq? 'and (car rest)))
           (cons (proc rest) (create-list (cddddr rest))))
           (else'())))
  (create-list exp))

(define (let-params exp)
  (let-assignment (lambda (exp) (cadr exp)) exp))

(define (let-values exp)
  (let-assignment (lambda (exp) (cadddr exp)) exp))

(define (let-body exp)
  (define (find-body rest)
    (if (null? rest)
        #f
        (if (eq? 'in (car rest))
            (cdr rest)
            (find-body (cdr rest)))))
  (find-body exp))

(define testexpr '(let x = 2 and
        y = 3 in
     (display (cons x y))
     (+ x y)))

(test-predicates "Tests for 2d"
                 (list
                  (equal? '(x y) (let-params testexpr))
                  (equal? '(2 3) (let-values testexpr))
                  (equal? '((display (cons x y)) (+ x y)) (let-body testexpr))
                  (eq? 5 (mc-eval '(let x = 2 and y = 3 in (+ x y)) the-global-environment))))


;; e)

(define (special-form? exp)
  (cond ((quoted? exp) #t)
        ((assignment? exp) #t)
        ((definition? exp) #t)
        ((if? exp) #t)
        ((lambda? exp) #t)
        ((begin? exp) #t)
        ((cond? exp) #t)
        ((and? exp) #t)
        ((or? exp) #t)
        ((let? exp) #t)
        ;; START
        ((while? exp) #t)
        ;; END
        (else #f)))

(define (eval-special-form exp env)
  (cond ((quoted? exp) (text-of-quotation exp))
        ((assignment? exp) (eval-assignment exp env))
        ((definition? exp) (eval-definition exp env))
        ((if? exp) (eval-if exp env))
        ((lambda? exp)
         (make-procedure (lambda-parameters exp)
                         (lambda-body exp)
                         env))
        ((begin? exp)
         (eval-sequence (begin-actions exp) env))
        ((cond? exp) (mc-eval (cond->if exp) env))
        ((and? exp) (eval-and exp env))
        ((or? exp) (eval-or exp env))
        ((let? exp) (eval-let exp env))
        ;; START
        ((while? exp) (eval-while exp env))
        ;; END
        ))

(define (while? exp) (tagged-list? exp 'while))

(define (while-cond exp) (cadr exp))

(define (while-body exp) (cdddr exp))

(define (eval-while exp env)
  (define (while-loop)
    (if (mc-eval (while-cond exp) env)
        (begin (eval-sequence (while-body exp) env)
               (while-loop))
        'terminated))
  (while-loop))

;; Syntax

#; (while <cond> do <seq-of-expressions>)

;; Eks. telle fra 1 til 3

(mc-eval '(define n 1) the-global-environment)
(mc-eval '(while (not (eq? 4 n)) do (display n) (set! n (+ n 1))) the-global-environment)

;; Vise alle elementer i listen

(mc-eval '(define lst '(a b c x y z)) the-global-environment)
(mc-eval '(while (not (null? lst)) do
           (display (car lst))
           (display ", ")
           (set! lst (cdr lst)))
         the-global-environment)