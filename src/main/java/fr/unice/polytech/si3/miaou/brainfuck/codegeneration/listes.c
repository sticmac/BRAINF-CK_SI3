#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "listes.h"

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

void print_list(List lst) {
  printf("{");
  while (lst) {
    printf("%s", lst->value);
    lst = lst->next;
    if (lst) printf(", ");
  }
  printf("}\n");
}

int main() {
	return 0;
}
