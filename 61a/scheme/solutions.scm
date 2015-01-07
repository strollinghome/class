(define j
  (mu (c k)
      (if (< c n)
        (j (+ c 2) (- k 1))
        k)))

(define (f n)
  (define c n)
  (j 0 0))


