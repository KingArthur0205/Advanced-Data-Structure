#include <stdio.h>
#include <stdlib.h>

#define TRUE 1
#define FALSE 0
typedef int bool;
 
// Definition of a Node
typedef struct NodeT {
    int data;
    struct NodeT *next;
} Node;

bool createStack(Node **stack) {
    stack = NULL;
    return TRUE;
}

bool push(Node **stack, int data) {
    // Allocate a new element
    Node *element = (Node*)malloc(sizeof(Node));
    if (!element)
        return FALSE;
    
    // Set fields in the node
    element->data = data;
    element->next = *stack;
    *stack = element;

    return TRUE;
}

// Use the data parameter to store the deleted element value.
bool pop(Node **stack, int *data) {
    Node *element;
    // There are no elements in the stack
    if (!(element = *stack)) {
        return FALSE;
    }

    *data = element->data;
    *stack = element->next;
    free(element);
    return TRUE;
}

bool deleteStack(Node **stack) {
    Node *element = *stack;
    while (element) {
        Node *deleteElem = element;
        element = element->next;
        free(deleteElem);
    }
    // Set the stack pointer to NULL
    *stack = NULL;
    return TRUE;
}

void printStack(Node **stack) {
    Node *element = *stack;
    if (!element)
        printf("There are no elements in the stack.\n");

    int elemIndex = 0;
    while (element) {
        printf("Node: %d Value: %d\n", elemIndex++, element->data);
        element = element->next;
    }
}