#include "AVLTree.h"

int main(int argc, char** argv) {
    struct AVLNode node;
    node.Element = 3;
    node.Height = 1;
    node.Left = NULL;
    node.Right = NULL;
    printf("%d", Height((&node)->Left));
    return 0;
}