## Recursive macro expansion example
# Increments the first cell 2 times, then decrement it once

DEFINE
NYAN
AS
DECR
END

DEFINE
MIAOU
AS
INCR
INCR
# Call the previously-defined NYAN macro in this one
NYAN
END

MIAOU
