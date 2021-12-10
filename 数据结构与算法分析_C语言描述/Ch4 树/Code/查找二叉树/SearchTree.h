#include <stdlib.h>
#include <stdio.h>

struct TreeNode;
typedef int ElemType;
typedef struct TreeNode *Position;
typedef Position SearchTree;

SearchTree MakeEmpty(SearchTree T); //将二叉搜索树清空
Position Find(ElemType X, SearchTree T); //在二叉搜索树中找到值为X的节点，返回其地址
Position FindMin(SearchTree T); //返回二叉搜索树中值最小的节点的地址
Position FindMax(SearchTree T); //返回二叉搜索树中值最大的节点的地址
SearchTree Insert(ElemType X, SearchTree T); //在二叉搜索树T中插入元素X
SeartchTree Delete(ElemType X, SearchTree T); //在二叉搜索树T中删除元素X

struct TreeNode {
    ElemType Element;
    SearchTree Left;
    SearchTree Right;
};