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
# <println, null>
# <i, int>
# <return, null>
# Update the stack pointer
addi $sp $sp -0
# Get i's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Compute rhs for assignment =
li $t1 0
# complete assignment statement with store
sw $t1 0($t0)
# println
la $a0 datalabel0
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
datalabel1:
# Get i's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of i
lw $t1 0($t0)
li $t0 10
slt $t1 $t1 $t0
subi $t1 $t1 1
bne $t1 $zero datalabel2
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -4
# println
# Get i's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 0
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of i
lw $t2 0($t0)
move $a0 $t2
li $v0 1
syscall
la $a0 newline
li $v0 4
syscall
# Get i's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 0
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Compute rhs for assignment =
# Get i's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t1 0
# Add the stack pointer address to the offset.
add $t1 $t1 $sp
# Load the value of i
lw $t2 0($t1)
li $t1 1
add $t2 $t2 $t1
# complete assignment statement with store
sw $t2 0($t0)
# TODO: Exiting scope.
addi $sp $sp 4
j datalabel1
datalabel2:
# TODO: Exiting scope.
addi $sp $sp 0
li $v0 10
syscall

# All memory structures are placed after the
# .data assembler directive
.data

newline: .asciiz "\n"
datalabel0: .asciiz "This program prints 0 through 9."
