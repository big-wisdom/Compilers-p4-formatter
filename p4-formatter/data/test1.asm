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
# TODO: Exiting scope.
li $v0 10
syscall

# All memory structures are placed after the
# .data assembler directive
.data

newline: .asciiz "\n"
datalabel0: .asciiz "Hello world"