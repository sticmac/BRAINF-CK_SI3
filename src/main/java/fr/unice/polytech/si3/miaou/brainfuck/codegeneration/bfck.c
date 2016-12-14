#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZE_MEMORY 30000

int main() {
	char memory[SIZE_MEMORY] = {};	 //mémoire
	int i = 0;						 //pointeur en mémoire

	memory[i]++;
	i++;
	memory[i]++;
	memory[i]++;
	while (memory[i] != 0) {
    i++;
	memory[i]++;
	memory[i]++;
	i--;
	memory[i]--;
	}
	
	//dump
	for (i = 0; i < SIZE_MEMORY; i++) {
		if (memory[i] != 0) {
			printf("C%d: %d\n", i, memory[i]);
		}
	}
	return 0;
}
