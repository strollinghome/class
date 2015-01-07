(define (reverse-tail lst)
    (define (helper lst result)
      (cond
        ((null? lst) result)
        (else (helper (cdr lst) (cons (car lst) result)))))
    (helper lst '()))

(define (append-tail lst_l lst_r)
    (define (helper lst_l lst_r result)
      (cond
        ((null? lst_l) result)
        (else (helper (cdr lst_l) lst_r (cons (car lst_l) lst_r)))))
    (helper (reverse-tail lst_l) lst_r '()))


