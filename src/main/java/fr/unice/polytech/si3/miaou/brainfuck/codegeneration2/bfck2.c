#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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


//Pile
struct elt {
  int value;
  struct element *next;
};

typedef struct elt elt;
typedef elt *Stack;

void push_item(Stack *stack, int n) {
  elt *new = malloc(sizeof(elt)); // create a new element

  if (!new) {
    fprintf(stderr, "allocation failed\n");
    exit(1);
  }
  new->value  = n;
  new->next   = *stack;
  *stack      = new;            // set the given stack to its new value
}

int pop_item(Stack *stack) {
  elt *old = *stack;

  if (old) {
    int old_value = old->value;

    *stack = (*stack)->next;
    free(old);
    return old_value;
  }
  fprintf(stderr, "pop on an empty stack!!!\n");
  exit(1);
}


//Liste
struct element {
  char value[10];
  struct element *next;
};

typedef struct element Element;
typedef Element *List;

List append_element(List lst, char v[]) {
  // Allouer un nouvel élément et l'initialiser
  Element *tmp = malloc(sizeof(Element));
  strcpy(tmp->value, v);
  tmp->next  = NULL;

  if (lst == NULL) // Le nouvel elément est la nouvelle liste
    return tmp;

  // lst n'était pas vide. Chercher son dernier élément
  Element *p;
  for (p=lst; p->next; p = p->next) {}

  // p désigne le dernier élément; lui faire pointer le nouvel élement
  p->next = tmp;

  // Renvoyer le début de la liste (l'ajout s'est fait plus loin)
  return lst;
}

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
