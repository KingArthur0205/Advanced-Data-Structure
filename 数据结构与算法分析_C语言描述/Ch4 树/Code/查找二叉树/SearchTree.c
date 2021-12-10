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

//在二叉搜索树中插入元素X
SearchTree Insert(ElemType X, SearchTree T) {
    //应在当前位置插入元素X
    if(T == NULL) {
        T = (SearchTree)malloc(sizeof(struct TreeNode));
        //若空间不足，退出
        if(T == NULL) {
            exit(-1);
        } else {
            T->Element = X;
            T->Right = T->Left = NULL;
            return T;
        }
    }
    //X比根节点值小，应被插入左子树
    if(T->Element > X)
        T->Left = Insert(X, T->Left);
    //X比根节点值大，应被插入右子树
    else if(T->Element < X)
        T->Right = Insert(X, T->Right);
    //若X值与根节点值相同，不进行操作
    return T;
}

SearchTree Delete(ElemType X, SearchTree T) {
    Position TmpCell;
    //若T不存在，退出
    if(T == NULL)
        exit(0);
    //要删除元素可能在T的左子树中
    else if(T->Element > X)
        T->Left = Delete(X, T->Left);
    //要删除元素可能在T的右子树中
    else if(T->Element < X)
        T->Right = Delete(X, T->Right);
    //当前T的值等于X且左右子树都不为NULL
    else if(T->Left && T->Right) {
        //找到右子树中最小值作为当前结点值的替换
        TmpCell = FindMin(T->Right);
        T->Element = TmpCell->Element;
        //删除右子树中最小值
        T->Right = Delete(T->Element, T->Right);
    } 
    //当前T的值等于X且只存在一/零子树
    else {
        //因为之后T的值会改变，因此用TmpCell记录
        TmpCell = T;
        //若只有右子树
        if(T->Left == NULL)
            T = T->Right;
        //若只有左子树
        else if(T->Right == NULL)
            T = T->Left;
        //释放空间
        free(TmpCell);
    }
    return T;
}