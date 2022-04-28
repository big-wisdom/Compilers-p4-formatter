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
# <return, null>
# Update the stack pointer
addi $sp $sp -0
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Compute rhs for assignment =
li $t1 3
# complete assignment statement with store
sw $t1 0($t0)
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
# Load the value of a
lw $t1 0($t0)
li $t0 4
slt $t1 $t1 $t0
subi $t1 $t1 1
bne $t1 $zero datalabel1
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -4
# println
la $a0 datalabel2
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 4
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of a
lw $t2 0($t0)
li $t0 4
subi $t2 $t2 1
bne $t2 $zero datalabel3
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -4
# println
la $a0 datalabel4
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 4
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of a
lw $t3 0($t0)
li $t0 4
subi $t3 $t3 1
bne $t3 $zero datalabel5
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -4
# println
la $a0 datalabel6
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 4
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of a
lw $t4 0($t0)
li $t0 3
subi $t4 $t4 1
bne $t4 $zero datalabel7
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -4
# println
la $a0 datalabel8
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 4
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of a
lw $t5 0($t0)
li $t0 3
subi $t5 $t5 1
bne $t5 $zero datalabel9
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -4
# println
la $a0 datalabel10
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 4
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of a
lw $t6 0($t0)
li $t0 4
subi $t6 $t6 1
bne $t6 $zero datalabel11
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -4
# println
la $a0 datalabel12
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 4
# TODO: Exiting scope.
addi $sp $sp 0
li $v0 10
syscall

# All memory structures are placed after the
# .data assembler directive
.data

newline: .asciiz "\n"
datalabel0: .asciiz "This program prints [1..5] correct."datalabel2: .asciiz "1 correct"datalabel4: .asciiz "2 not correct"datalabel6: .asciiz "2 not correct"datalabel8: .asciiz "3 correct"datalabel10: .asciiz "4 correct"datalabel12: .asciiz "5 not correct"