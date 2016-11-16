# This is a Hello world in Brainf*ck 

++++++++++ # An array is initialized using loop
[
	>+++++++>++++++++++>+++>+<<<<-  # This line is indented, it helps to read it.
]

# At the end of the loop, memory contains :
# C1 : 70
# C2 : 100
# C3 : 30
# C4 : 10

>++.	 # (70  + 2  = 72  = H) & print
>+.	 # (100 + 1  = 101 = e) & print
+++++++. # (101 + 7  = 108 = l) & print
OUT#Print 'l' a second time
+++.	 # (108 + 3  = 111 = o) & print

	# This is a space (30 + 2)
	>++.

# The next line was a bad idea
# >>>+++++<<<<<<  
# Another bad idea
# RIGHT
<<+++++++++++++++. # (72 + 15 = 87 = W) & print 

	#C2 already contains 'o', we are just printing the content	
	RIGHT	
	OUT

+++. 		   # 'o' + 3 = 'r'
------.		   # 'r' - 6 = 'l'
--------.	   # 'l' - 8 = 'd'
>+.		   # (32 + 1 = 33 = '!')

#Just print a new line
>.
