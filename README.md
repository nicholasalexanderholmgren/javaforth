# Java Forth


## Word Lookup

Words are stored through a variety of mechanisms.

The address where a word can be found is remembered by its 
relevant lookup table in the ForthMachine. Currently there
are lookup tables for compiled words and for variables.

If a word is found in the variable lookup table, then the 
interpreter will leave its memory address on the stack and 
resume pulling from the input stream.

If a word is found in the compiled words lookup table, AKA 
the Forth Dictionary, then it will begin execution of the
instructions stored at that address.

## Dict file format

Words stored in a .dict file should take the following format:

<name of word> : [instruction for word execution] ; <word source code>

Words that are given in the required word set do not have proper 
source code and instead have their definitions from the standard
in its place.

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
#### DPUSHC  
#### ROLL 
#### DPOP
### Memory operations 50-5F
#### CJMP 
#### JMP
#### SUBJMP 
#### FETCH 
#### STORE 
#### ALLOC
#### ALLOCSTRING 
#### ALLOCVAR
	//mode change 60-6F
	COMPILE, INTERP, 
	//output operations 70-7F
	NUMOUT, CR, SPACE, CHAROUT,
