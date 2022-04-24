# All program code is placed after the
# .text assembler directive
.text

# Declare main as a global function
.globl	main

j main
main:
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# <main, null>
# Update the stack pointer
# println
la $a0 datalabel0
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
li $t0 7
# println
move $a0 $t0
li $v0 1
syscall
la $a0 newline
li $v0 4
syscall
li $t1 3
# println
move $a0 $t1
li $v0 1
syscall
la $a0 newline
li $v0 4
syscall
li $t2 14
# println
move $a0 $t2
li $v0 1
syscall
la $a0 newline
li $v0 4
syscall
li $t3 7
# println
move $a0 $t3
li $v0 1
syscall
la $a0 newline
li $v0 4
syscall
li $t4 7
# println
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
li $v0 10
syscall

# All memory structures are placed after the
# .data assembler directive
.data

newline: .asciiz "\n"
datalabel0: .asciiz "This program prints 7 7 7 7 7 (separated by newlines)"