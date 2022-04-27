# All program code is placed after the
# .text assembler directive
.text

# Declare main as a global function
.globl	main

j main

# code for add
add:
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# <x, int>
# <y, int>
# <i, int>
# Update the stack pointer
addi $sp $sp -0
# println
# Get x's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of x
lw $t1 0($t0)
# Get y's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -8
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of y
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
jr $ra

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
# Calling function add
# Save $ra to a register
move $t0 $ra
# Save $t0-9 registers
sw $t0 -12($sp)
# Evaluate parameters and save to stack
li $t1 3
sw $t1 -16($sp)
li $t1 4
sw $t1 -20($sp)
# Update the stack pointer
add $sp $sp -12
# Call the function
jal add
# Restore the stack pointer
add $sp $sp 12
# Restore the $t0-9 registers
lw $t0 -12($sp)
# Restore $ra
move $ra $t0
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t1 -4
# Add the stack pointer address to the offset.
add $t1 $t1 $sp
# Compute rhs for assignment =
li $t2 5
# complete assignment statement with store
sw $t2 0($t1)
# Get b's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -8
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Compute rhs for assignment =
li $t1 2
# complete assignment statement with store
sw $t1 0($t0)
# Calling function add
# Save $ra to a register
move $t0 $ra
# Save $t0-9 registers
sw $t0 -12($sp)
# Evaluate parameters and save to stack
# Get a's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t1 -4
# Add the stack pointer address to the offset.
add $t1 $t1 $sp
# Load the value of a
lw $t2 0($t1)
sw $t2 -16($sp)
# Get b's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t1 -8
# Add the stack pointer address to the offset.
add $t1 $t1 $sp
# Load the value of b
lw $t2 0($t1)
sw $t2 -20($sp)
# Update the stack pointer
add $sp $sp -12
# Call the function
jal add
# Restore the stack pointer
add $sp $sp 12
# Restore the $t0-9 registers
lw $t0 -12($sp)
# Restore $ra
move $ra $t0
# TODO: Exiting scope.
addi $sp $sp 0
li $v0 10
syscall

# All memory structures are placed after the
# .data assembler directive
.data

newline: .asciiz "\n"
datalabel0: .asciiz "This program prints 7 7"