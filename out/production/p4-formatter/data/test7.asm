# All program code is placed after the
# .text assembler directive
.text

# Declare main as a global function
.globl	main

j main

# code for identity
identity:
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# <x, int>
# <return, int>
# Update the stack pointer
addi $sp $sp -0
# Get x's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of x
lw $t1 0($t0)
sw $t1 -8($sp)
jr $ra
# TODO: Exiting scope.
addi $sp $sp 0
jr $ra

# code for add
add:
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# <x, int>
# <y, int>
# <return, int>
# Update the stack pointer
addi $sp $sp -0
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
sw $t1 -12($sp)
jr $ra
# TODO: Exiting scope.
addi $sp $sp 0
jr $ra

# code for main
main:
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# <return, null>
# Update the stack pointer
addi $sp $sp -0
# println
la $a0 datalabel0
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
# println
# Calling function identity
# Save $ra to a register
move $t0 $ra
# Save $t0-9 registers
sw $t0 -4($sp)
# Evaluate parameters and save to stack
li $t1 7
sw $t1 -8($sp)
# Update the stack pointer
add $sp $sp -4
# Call the function
jal identity
# Restore the stack pointer
add $sp $sp 4
# Restore the $t0-9 registers
lw $t0 -4($sp)
# Restore $ra
move $ra $t0
# Get return value off stack
lw $t1 -12($sp)
move $a0 $t1
li $v0 1
syscall
la $a0 newline
li $v0 4
syscall
# println
# Calling function add
# Save $ra to a register
move $t0 $ra
# Save $t0-9 registers
sw $t0 -4($sp)
# Evaluate parameters and save to stack
li $t1 3
sw $t1 -8($sp)
li $t1 4
sw $t1 -12($sp)
# Update the stack pointer
add $sp $sp -4
# Call the function
jal add
# Restore the stack pointer
add $sp $sp 4
# Restore the $t0-9 registers
lw $t0 -4($sp)
# Restore $ra
move $ra $t0
# Get return value off stack
lw $t1 -16($sp)
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
datalabel0: .asciiz "This program prints 7 7"