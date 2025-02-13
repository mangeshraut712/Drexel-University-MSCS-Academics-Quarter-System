.data
x:	.word 0
y:	.word 0
z:	.word 0
nl:	.asciiz	"\n"

.text
main:
li	$v0, 5		# Read x
syscall
la	$t0, x
sw	$v0, 0($t0)
li	$v0, 5		# Read y
syscall
la	$t0, y
sw	$v0, 0($t0)

la	$t0, x		# z = x + y
lw	$t1, 0($t0)
lw	$t2, 4($t0)
add	$t3, $t1, $t2
la	$t0, z
sw	$t3, 0($t0)

li	$v0, 1		# Print z
lw	$a0, 0($t0)
syscall
li	$v0, 4
la	$a0, nl
syscall

la	$t0, x
lw	$t1, 0($t0)
lw	$t2, 4($t0)
sub	$t3, $t1, $t2
sw	$t3, 8($t0)

li	$v0, 1
lw	$a0, 8($t0)
syscall
li	$v0, 4
la	$a0, nl
syscall


li	$v0, 10		# Exit
syscall
