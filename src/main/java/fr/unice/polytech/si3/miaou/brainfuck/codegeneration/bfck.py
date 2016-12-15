#!/usr/bin/python
#coding: latin-1

size_memory = 30000

memory = [0] * size_memory
i = 0

memory[i] += 1
memory[i] += 1

while not memory[i] == 0:
	i += 1
	memory[i] += 1
	memory[i] += 1
	i -= 1
	memory[i] -= 1

i += 1
i += 1
memory[i] = ord(input())
print(chr(memory[i]), end="")

for j in range(0, size_memory):
	if memory[j] != 0:
		print("C", j,": ", memory[j], sep="")
