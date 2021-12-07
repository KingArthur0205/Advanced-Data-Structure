#include "SearchTree.h"

//释放整个二叉搜索树
SearchTree MakeEmpty(SearchTree T) {
    //若传入节点不为NULL，将其左右节点释放，最后释放本身
    if(T != NULL) {
        MakeEmpty(T->Left);
        MakeEmpty(T->Right);
        free(T);
    }
    return NULL;
}

// 查找二叉搜索树中X元素的位置，若找到返回该元素的地址。若未找到，返回NULl
Position Find(ElemType X, SearchTree T) {
    //base case: 若传入节点为NULL，返回NULL
    if(T == NULL)
        return NULL;
    
    //X可能在右子树上
    if(X > T->Element) {
        return Find(X, T->Right);
    } else if(X < T->Element) {
        return Find(X, T->Left);
    }
    //相等，返回T
    return T;
}

//返回二叉搜索树中值最小的节点的地址
//递归写法
Position FindMin(SearchTree T) {
    if(T == NULL)
        return NULL;

    //若节点没有左子树（当前节点为最小），返回当前节点地址
    if(T->Left == NULL)
        return T;
    return FindMin(T->Left);
}

//返回二叉搜索树中值最大的节点的地址
Position FindMax(SearchTree T) {
    if(T == NULL)
        return NULL;
    
    //当节点有右子树时，一直visit右子树
    while(T->Right != NULL)
        T = T->Right;
    return T;
}