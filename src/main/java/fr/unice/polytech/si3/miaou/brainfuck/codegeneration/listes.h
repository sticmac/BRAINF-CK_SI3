struct element {
  char value[10];
  struct element *next;
};

typedef struct element Element;
typedef Element *List;

List append_element(List lst, char v[]);

void print_list(List lst);
