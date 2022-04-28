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
la $a0 datalabel3
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 4
j datalabel2
datalabel1:
datalabel2:
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of a
lw $t2 0($t0)
li $t0 4
slt $t2 $t0 $t2
subi $t2 $t2 1
bne $t2 $zero datalabel4
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
j datalabel5
datalabel4:
datalabel5:
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of a
lw $t3 0($t0)
li $t0 4
slt $t3 $t0 $t3
subi $t3 $t3 1
bne $t3 $zero datalabel7
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -4
# println
la $a0 datalabel9
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 4
j datalabel8
datalabel7:
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
datalabel8:
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of a
lw $t4 0($t0)
li $t0 3
slt $t4 $t0 $t4
bne $t4 $zero datalabel11
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -4
# println
la $a0 datalabel13
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 4
j datalabel12
datalabel11:
datalabel12:
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of a
lw $t5 0($t0)
li $t0 3
sub $t5 $t5 $t0
bne $t5 $zero datalabel14
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -4
# println
la $a0 datalabel16
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 4
j datalabel15
datalabel14:
datalabel15:
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of a
lw $t6 0($t0)
li $t0 4
slt $t6 $t6 $t0
bne $t6 $zero datalabel17
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -4
# println
la $a0 datalabel19
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 4
j datalabel18
datalabel17:
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -4
# println
la $a0 datalabel20
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# TODO: Exiting scope.
addi $sp $sp 4
datalabel18:
# TODO: Exiting scope.
addi $sp $sp 0
li $v0 10
syscall

# All memory structures are placed after the
# .data assembler directive
.data

newline: .asciiz "\n"
datalabel0: .asciiz "This program prints [1..5] correct."
datalabel3: .asciiz "1 correct"
datalabel6: .asciiz "2 not correct"
datalabel9: .asciiz "2 not correct"
datalabel10: .asciiz "2 correct"
datalabel13: .asciiz "3 correct"
datalabel16: .asciiz "4 correct"
datalabel19: .asciiz "5 not correct"
datalabel20: .asciiz "5 correct"
