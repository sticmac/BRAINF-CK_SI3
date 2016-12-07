## Simple macro TO_DIGIT example.
# Translates an ASCII digit to its represented value by substracting 48 to the current memory cell

DEFINE
MULTI_DECR
AS
- # Decrement 48 times
END

DEFINE
TO_DIGIT
AS
MULTI_DECR 48
END

IN # Enter a value (digit in ASCII)
# Convert it to a digit in memory
TO_DIGIT
RIGHT
IN
TO_DIGIT
