.text

.globl	main
main:

  addi $s1, $0,0
  add $s0,$0,$0
  addi $t0,$0,10
next:
  beq $s0, $t0, done
  add $s1, $s1, $s0
  addi $s0, $s0, 1
  j next
done:
  li $v0, 1
  move $a0, $s1
  syscall

  li	$v0, 10		# Exit
  syscall
