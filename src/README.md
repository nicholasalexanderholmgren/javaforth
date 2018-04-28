# Java Forth


# Dictionary Memory Format

Each word ocupies a region in memory.

The first byte of this region is how long the word is, in total.

The second byte and onward contain each letter of the name of the word.

The last byte contains a pointer to a Java object that defines how the word executes.
