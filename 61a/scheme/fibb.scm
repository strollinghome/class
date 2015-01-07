(define (fibonacci n)
  (define (fib-tail n result prev_1 prev_2)
    (if (= n 2) result
        (fib-tail (- n 1) (+ result prev_1 prev_2) prev_2 result)))
    (if (< n 3) 1
        (fib-tail (- n 1) 3 1 1)))
   

(define (fib n)
  (if (< n 1) 1
    (+ (fib (- n 1)) (fib (- n 2)))))
