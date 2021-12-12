#include <stdlib.h>
#include <stdio.h>

struct AVLNode;
typedef struct AVLNode *Position;
typedef Position AVLTree;
typedef int ElemType;

AVLTree MakeEmpty(AVLTree T);
Position FInd(ElemType X, AVLTree T);
Position FindMin(AVLTree T);
Position FindMax(AVLTree T);
AVLTree Insert(ElemType X, AVLTree T);
AVLTree Delete(ElemType X, AVLTree T);
ElemType Retrieve(Position P);

struct AVLNode {
    ElemType Element;
    int Height;
    AVLTree Left;
    AVLTree Right;
};

// 获得当前节点的高度
static int Height(Position P) {
    if(P == NULL)
        return -1;
    return P->Height;
}