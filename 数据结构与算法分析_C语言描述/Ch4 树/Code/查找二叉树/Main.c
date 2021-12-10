#include "SearchTree.h"

SearchTree construct() {
    SearchTree tree = (SearchTree)malloc(sizeof(struct TreeNode));
    tree->Element = 0;
    tree->Left = NULL;
    tree->Right = NULL;

    return tree;
}

void printTree(SearchTree T) {
    if(T->Left == NULL && T->Right == NULL) {
        printf("%d, ", T->Element);
        return ;
    }
    if(T->Left != NULL) 
        printTree(T->Left);
    printf("%d, ", T->Element);
    if(T->Right != NULL)
        printTree(T->Right);
}

int main() {
    SearchTree tree = construct();
    int i;
    for(i = 0; i < 10; i++) 
        Insert(i, tree);
    printTree(tree);
    return 0;
}