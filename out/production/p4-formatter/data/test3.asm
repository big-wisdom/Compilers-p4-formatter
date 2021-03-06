# All program code is placed after the
# .text assembler directive
.text

# Declare main as a global function
.globl	main

j main

# code for main
main:
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <a, int>
# <println, null>
# <b, int>
# Update the stack pointer
addi $sp $sp -0
# println
la $a0 datalabel0
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Compute rhs for assignment =
li $t1 3
# complete assignment statement with store
sw $t1 0($t0)
# Get b's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -8
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Compute rhs for assignment =
li $t1 4
# complete assignment statement with store
sw $t1 0($t0)
# println
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of a
lw $t1 0($t0)
# Get b's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -8
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of b
lw $t2 0($t0)
add $t1 $t1 $t2
move $a0 $t1
li $v0 1
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 0
li $v0 10
syscall

# All memory structures are placed after the
# .data assembler directive
.data

newline: .asciiz "\n"
datalabel0: .asciiz "This program prints the number 7"