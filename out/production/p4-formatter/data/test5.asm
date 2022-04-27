# All program code is placed after the
# .text assembler directive
.text

# Declare main as a global function
.globl	main

j main
foo:
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -0
li $t0 7
# println
move $a0 $t0
li $v0 1
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 0
fum:
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <a, int>
# <println, null>
# <b, int>
# Update the stack pointer
addi $sp $sp -0
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Compute rhs for assignment =
li $t1 9
# complete assignment statement with store
sw $t1 0($t0)
# Get b's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -8
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Compute rhs for assignment =
li $t1 12
# complete assignment statement with store
sw $t1 0($t0)
# Get b's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -8
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of b
lw $t1 0($t0)
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of a
lw $t2 0($t0)
sub submit.MIPSResult@2353b3e6 submit.MIPSResult@2353b3e6 $t2
li $t0 4
add submit.MIPSResult@2353b3e6 submit.MIPSResult@2353b3e6 $t0
# println
move $a0 $t1
li $v0 1
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 0
main:
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -0
# println
la $a0 datalabel0
li $v0 4
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
datalabel0: .asciiz "This program prints 7 7 7"