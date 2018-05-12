## Lexicon of instructions
### Arithmetic Integer Instructions 
#### ADD
Take the top two 16 bit singed integers from the stack, add
them together, then put the resulting 16 bit signed integer 
on the stack.
#### SUB
Take the top two 16 bit singed integers from the stack, subtract
the second from the first, then put the resulting 16 bit 
signed integer on the stack.
#### DIV
Take the top two 16 bit singed integers from the stack, divide
the first by the second, then put the resulting 16 bit 
signed integer on the stack.
#### MULT
Take the top two 16 bit singed integers from the stack, multiply
them together, then put the resulting 16 bit signed integer 
on the stack.
#### MOD
Take the top two 16 bit singed integers from the stack, modulo
the first by the second, then put the resulting 16 bit 
signed integer on the stack.
### Double Arithmetic Instructions
#### DADD
Take the top two 32 bit singed doubles from the stack, add
them together, then put the resulting 32 bit signed double 
on the stack.
#### DSUB
Take the top two 32 bit singed doubles from the stack, 
subtracts the second from the first, then put the resulting 
32 bit signed double on the stack.
#### DDIV
Take the top two 32 bit singed doubles from the stack, divide
the first by the second, then put the resulting 32 bit 
singed double on the stack.
#### DMULT
Take the top two 32 bit singed doubles from the stack, multiply
them together, then put the resulting 32 bit singed double 
on the stack.
### Logical Instructions
#### GREATER
Take the top two integers off the stack, put 1 on if the 
first is greater than the second, else put 0.
#### LESS 
Take the top two integers off the stack, put 1 on if the 
first is less than the second, else put 0.
#### EQL 
Take the top two integers off the stack, put 1 on if the 
first is equal to the second, else put 0.
#### AND 
Take the top two integers off the stack, put the bitwise and
of the them on the stack.
#### XOR
Take the top two integers off the stack, put the bitwise 
exclusive or of the them on the stack.
### Return Stack Instructions
#### RFETCH
Copy the top integer of the return stack to the data stack.
Do not remove the top element of the return stack
#### RFROM
Pop the top integer of the return stack and put it on the 
data stack. 
#### RPUSH
Pop the top integer from the data stack and push it on to 
the return stack.
#### RETURN
Move the instruction pointer to the top address of the return
stack.
### Data stack Instructions
#### DPUSH 
Push the next two bytes in instruction memory to the data stack.
Effectively makes the next two bytes be literal values.
#### ROLL
Takes the top integer of the stack as an argument, then moves the
item after that to the Nth place in the stack, where N is the
argument value.
#### DPOP
Remove the top item of the stack and do nothing with it.
### Memory operations 50-5F
#### JMP
Move the instruction pointer the integer that follows this
relative to its current position. 
#### CJMP 
Pop the top item of the stack. If it was not 0, as JMP, otherwise
move the instruction pointer past the following integer.
#### SUBJMP 
Put the current instruction pointer address, plus 3, on the
 return stack.
Then jump to address that follows this instruction.
#### FETCH
Pop the top address off the data stack, then push the integer
value found at that address to the data stack
#### STORE
Pop the top address off the data stack, then pop the top integer
value from the data stack. Store the integer value at the 
specified address.  
#### ALLOC
Allocate two bytes in the memory.
#### ALLOCSTRING 
Store the next token from the input stream in memory as a 
packed string.
#### ALLOCVAR

	//mode change 60-6F
	COMPILE, INTERP, 
	//output operations 70-7F
	NUMOUT, CR, SPACE, CHAROUT,
