	.data
cstart:
	.word 10
nl:
	.asciiz "\n"

	.text
main:
	la	$t0, cstart			# step 1: Load counter
	lw	$s0, 0($t0)
loop:
	li	$v0, 1			# step 2: Print counter
	or	$a0, $s0, $zero
	syscall
	li	$v0, 4			# Print newline
	la	$a0, nl
	syscall

	bne	$s0, $zero, continue	# If counter != 0, go to continue
	li	$v0, 10			# exit
	syscall

continue:
	addi	$s0, $s0, -1		# step 4: decrement counter
	b	loop				# step 5: go to 2
