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

[name of word] : [instruction for word execution] ; 
[word source code]

Words that are given in the required word set do not have proper 
source code and instead have their definitions from the standard
in its place.
