# All program code is placed after the
# .text assembler directive
.text

# Declare main as a global function
.globl	main

j main
la $t0 datalabel0
# All memory structures are placed after the
# .data assembler directive
.data

