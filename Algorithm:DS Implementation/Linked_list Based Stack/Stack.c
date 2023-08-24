#include "Linked-List Based Stack.h"

int main(int argc, char** argv) {
    Node **a;
    createStack(a);
    push(a, 3);
    push(a, 4);
    printStack(a);

    int *k = (int*)malloc(sizeof(int));
    pop(a, k);
    printStack(a);
    return 0;
}