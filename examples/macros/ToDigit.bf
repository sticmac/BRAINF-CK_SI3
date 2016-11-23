## Simple macro TO_DIGIT example.
# Translates an ASCII digit to its represented value by substracting 48 to the current memory cell

DEFINE
TO_DIGIT
AS
------------------------------------------------ # Decrement 48 times
END

IN # Enter a value (digit in ASCII)
TO_DIGIT # Convert it to a digit in memory
RIGHT
IN
TO_DIGIT
