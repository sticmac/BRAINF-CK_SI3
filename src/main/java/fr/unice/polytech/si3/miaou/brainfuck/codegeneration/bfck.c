#include <stdio.h>
#include <stdlib.h>
#include "stack.h"
#include "listes.h"

#define SIZE_MEMORY 300/*00*/
#define OFFSET 128

#define INCR 	memory[i]++;
#define DECR 	memory[i]--;
#define BACK 	if (memory[i] != 0) { j = pop_item(&s); }
#define IN 		getchar();
#define JUMP 	push_item(&s, j);
#define OUT 	printf("%c\n", memory[i]);
#define RIGHT 	i++;
#define LEFT 	i--;

int main() {
	char memory[SIZE_MEMORY] = {};	 //mémoire
	int i = 0;						 //pointeur en mémoire
	int j = 0;	 					 //pointeur d'instruction
	Stack s = NULL;					 //pile de jump
	List l = NULL;					 //liste d'instructions

	l = append_element(l, "INCR");
	j++;

	l = append_element(l, "INCR");
	j++;

	l = append_element(l, "JUMP");
	j++;

	l = append_element(l, "RIGHT");
	j++;

	l = append_element(l, "INCR");
	j++;

	l = append_element(l, "INCR");
	j++;

	l = append_element(l, "LEFT");
	j++;

	l = append_element(l, "DECR");
	j++;

	l = append_element(l, "BACK");
	j++;


	j = 0;
	for (Element *p = l; p; p = p->next) {
		j++;
		printf("%s\n", p-> value);
    }

	//dump
	for (i = 0; i < SIZE_MEMORY; i++) {
		if (memory[i] != 0) {
			printf("C%d: %d\n", i, memory[i]);
		}
	}
	return 0;
}
