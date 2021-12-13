#include "AVLTree.h"


void show(AVLTree T) {
    Position *queue = (Position*)malloc(1000 * sizeof(Position));
    int queueFront = 0;
    int queueEnd = 0;
    queue[queueEnd++] = T;
    while(queueEnd - queueFront) {
        int tempEnd = queueEnd;
        for(; queueFront < tempEnd; queueFront++) {
            Position tempNode = queue[queueFront];
            printf("%d ", tempNode->Element);
            if(tempNode -> Left)
                queue[queueEnd++] = tempNode -> Left;
            if(tempNode -> Right)
                queue[queueEnd++] = tempNode -> Right;
        }
        printf("\n");
    }
}

int main(int argc, char** argv) {
    Position node = (Position)malloc(sizeof(struct AVLNode));
    node->Element = 0;
    node->Left = NULL;
    node->Right = NULL;
    node->Height = 0;
    int i;
    for(i = 1; i < 20; i++) {
        node = Insert(i, node);
    }
    show(node);
    return 0;
}