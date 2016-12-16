#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZE_MEMORY 30000

int main() {
	char *memory = calloc(SIZE_MEMORY, sizeof(char));
	char *p = memory;

	(*memory)++;
	memory++;
	(*memory)++;
	(*memory)++;
	while (*memory) {
    memory++;
	(*memory)++;
	(*memory)++;
	memory--;
	(*memory)--;
	}
	memory++;
	memory++;
	(*memory)++;

	for (int i = 0; i < SIZE_MEMORY; i++, *p++) {
		if (*p) {
			printf("C%d: %d\n", i, *p);
		}
	}
	return 0;
}
