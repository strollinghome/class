main:
	addiu $a0, $0, 3

LfsrPalindrome:
	addiu $t0, $a0, 0
	addiu $t3, $0, 1
loop:
	lfsr $t1, $t0
	beq $t1, $a0, notfoundPal
	bitpal $t2, $t1
	beq $t2, $t3, foundPal
	addiu $t0, $t1, 0
	j loop

notfoundPal:
	addiu $v0, $a0, 0
	jr $ra

foundPal:
	addiu $v0, $t2, 0
	jr $ra
	
