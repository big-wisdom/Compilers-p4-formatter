# All program code is placed after the
# .text assembler directive
.text

# Declare main as a global function
.globl	main

j main

# code for fib
fib:
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# <i, int>
# <return, int>
# Update the stack pointer
addi $sp $sp -0
# Get i's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of i
lw $t1 0($t0)
li $t0 0
sub $t1 $t1 $t0
bne $t1 $zero datalabel0
li $t0 1
sw $t0 -8($sp)
jr $ra
j datalabel1
datalabel0:
datalabel1:
# Get i's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t2 -4
# Add the stack pointer address to the offset.
add $t2 $t2 $sp
# Load the value of i
lw $t3 0($t2)
li $t2 1
sub $t3 $t3 $t2
bne $t3 $zero datalabel2
li $t2 1
sw $t2 -8($sp)
jr $ra
j datalabel3
datalabel2:
datalabel3:
# Calling function fib
# Save $ra to a register
move $t4 $ra
# Save $t0-9 registers
sw $t0 -12($sp)
sw $t1 -16($sp)
sw $t2 -20($sp)
sw $t3 -24($sp)
sw $t4 -28($sp)
# Evaluate parameters and save to stack
# Get i's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t5 -4
# Add the stack pointer address to the offset.
add $t5 $t5 $sp
# Load the value of i
lw $t6 0($t5)
li $t5 1
sub $t6 $t6 $t5
sw $t6 -32($sp)
# Update the stack pointer
add $sp $sp -28
# Call the function
jal fib
# Restore the stack pointer
add $sp $sp 28
# Restore the $t0-9 registers
lw $t0 -12($sp)
lw $t1 -16($sp)
lw $t2 -20($sp)
lw $t3 -24($sp)
lw $t4 -28($sp)
lw $t5 -32($sp)
# Restore $ra
move $ra $t4
# Get return value off stack
lw $t6 -36($sp)
# Calling function fib
# Save $ra to a register
move $t7 $ra
# Save $t0-9 registers
sw $t0 -12($sp)
sw $t1 -16($sp)
sw $t2 -20($sp)
sw $t3 -24($sp)
sw $t4 -28($sp)
sw $t5 -32($sp)
sw $t6 -36($sp)
sw $t7 -40($sp)
# Evaluate parameters and save to stack
# Get i's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t8 -4
# Add the stack pointer address to the offset.
add $t8 $t8 $sp
# Load the value of i
lw $t9 0($t8)
li $t8 2
sub $t9 $t9 $t8
sw $t9 -44($sp)
# Update the stack pointer
add $sp $sp -40
# Call the function
jal fib
# Restore the stack pointer
add $sp $sp 40
# Restore the $t0-9 registers
lw $t0 -12($sp)
lw $t1 -16($sp)
lw $t2 -20($sp)
lw $t3 -24($sp)
lw $t4 -28($sp)
lw $t5 -32($sp)
lw $t6 -36($sp)
lw $t7 -40($sp)
lw $t8 -44($sp)
# Restore $ra
move $ra $t7
# Get return value off stack
lw $t9 -48($sp)
add $t6 $t6 $t9
sw $t6 -8($sp)
jr $ra
# TODO: Exiting scope.
addi $sp $sp 0
jr $ra

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
la $a0 datalabel4
li $v0 4
syscall
la $a0 newline
li $v0 4
syscall
datalabel5:
# Get i's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t0 -4
# Add the stack pointer address to the offset.
add $t0 $t0 $sp
# Load the value of i
lw $t1 0($t0)
li $t0 12
slt $t1 $t1 $t0
subi $t1 $t1 1
bne $t1 $zero datalabel6
# TODO: Entering a new scope.
# TODO?: Symbols in symbol table:
# <println, null>
# Update the stack pointer
addi $sp $sp -4
# println
# Calling function fib
# Save $ra to a register
move $t0 $ra
# Save $t0-9 registers
sw $t0 -4($sp)
sw $t1 -8($sp)
# Evaluate parameters and save to stack
# Get i's offset from $sp from the symbol table and initialize a's address with it. We'll add $sp later
li $t2 0
# Add the stack pointer address to the offset.
add $t2 $t2 $sp
# Load the value of i
lw $t3 0($t2)
sw $t3 -12($sp)
# Update the stack pointer
add $sp $sp -8
# Call the function
jal fib
# Restore the stack pointer
add $sp $sp 8
# Restore the $t0-9 registers
lw $t0 -4($sp)
lw $t1 -8($sp)
# Restore $ra
move $ra $t0
# Get return value off stack
lw $t2 -16($sp)
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
j datalabel5
datalabel6:
# TODO: Exiting scope.
addi $sp $sp 0
li $v0 10
syscall

# All memory structures are placed after the
# .data assembler directive
.data

newline: .asciiz "\n"
datalabel4: .asciiz "This program prints the first 12 numbers of the Fibonacci sequence."
