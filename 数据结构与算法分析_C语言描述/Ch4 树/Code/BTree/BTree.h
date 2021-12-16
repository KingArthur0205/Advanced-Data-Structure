#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#define ORDER 3

typedef int ElemType;
struct BTNode;
typedef struct BTNode *BTree;

struct BTNode {
    int keyNum;
    ElemType keys[ORDER - 1];
    struct BTNode* children[ORDER];
    int isLeaf;
} BTNode;

BTree Init();
void BTree_Create(BTree *, ElemType *, int); //通过一个数组创建B树
void BTree_Insert(BTree *, ElemType); //在B树中插入一个元素
int isFull(BTree);
void BTree_Split(BTree *);