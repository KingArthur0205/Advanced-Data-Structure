#include "BTree.h"

//创建一个新的BTree
BTree Init() {
    BTree T = (BTree)malloc(sizeof(BTNode));
    if(T == NULL)
        exit(-1);

    T->keyNum = 0;
    T->isLeaf = 1;
    return T;
}

//根据一个数组创建一个B树
void BTee_Create(BTree *T, ElemType *data, int length) {
    assert(T);
    *T = Init();

    int i;
    for(i = 0; i < length; i++) {
        BTree_Insert(T, data[i]);
    }
}

int isFull(BTree T) {
    if(T->keyNum < ORDER)
        return 0;
    return 1;
}

void BTree_Split(BTree* T) {}

void BTree_Insert(BTree* T, ElemType data) {
    assert(T);

    struct BTNode *nodePtr = *T;
    //若B树节点满，开始分裂
    if(isFull(*T)) {
        BTree_Split(T);
        BTree_Insert(T, data);
        return ;
    }
    //若当前节点不是叶子节点，进入下一层
    if(!nodePtr->isLeaf) {}
    //若当前节点为叶子节点，且节点未满，进行插入
    if(nodePtr->isLeaf && isFull(*T)) {}
}