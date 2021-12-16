#include "BTree.h"

int main(int argc, char** argv) {
    BTree T = Init();
    T->keyNum = 3;
    printf("%d", isFull(T));
    return 0;
}