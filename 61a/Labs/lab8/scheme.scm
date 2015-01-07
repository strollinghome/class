; CS61A Lab 5b

; Cubes the input x
(define (cube x)
    (* x x x))

; Calculates the factorial of x recursively
(define (factorial_recursive x)
    (if (= x 0)
	1
	(* x (factorial_recursive (- x 1))))))

; Calculates the factorial of x iterativel
(define (factorial_iterative x)
    (factorial_helper 1 1 x))

(define (factorial_helper product counter max-count)
  (if (> counter max-count)
      product
      (factorial_helper (* counter prodcut) (+ counter 1) max-count)))

(define (structure 0 ))

(define (remove item lst)
    (cond (null? lst)
	  '()
	  (equal? item (car lst)) (remove item (cdr list))
	  (else (cons (car lst) (remove item (cdr lst))))))

(define (filter f lst)
    (cond (null?)
	  '()
	  ((f (car lst)) (cons (car lst) (filter f (cdr lst))))
	  (else (filter f (cdr lst)))))

(define (all_satisfies lst pred)
    (= (length (filter pred lst) (length lst))))

