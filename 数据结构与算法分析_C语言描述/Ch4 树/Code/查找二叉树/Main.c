#include "SearchTree.h"

SearchTree construct() {
    SearchTree tree = (SearchTree)malloc(sizeof(struct TreeNode));
    tree->Element = 0;
    tree->Left = NULL;
    tree->Right = NULL;

    return tree;
}

int main() {
    SearchTree tree = construct();
    printf("%d %d", FindMin(tree)->Element, FindMax(tree)->Element);
    return 0;
}