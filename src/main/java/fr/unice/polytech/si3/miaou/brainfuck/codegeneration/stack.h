typedef struct element Element;
typedef Element *Stack;

void push_item(Stack *stack, int n);

int pop_item(Stack *stack);

void print_stack(Stack stack);
