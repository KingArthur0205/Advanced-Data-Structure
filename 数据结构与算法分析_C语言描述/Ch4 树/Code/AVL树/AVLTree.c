#include "AVLTree.h"
int MAX(int a, int b);
static AVLTree SingleRotateWithLeft(Position P);
static AVLTree DoubleRotateWithLeft(Position P);
static AVLTree SingleRotateWithRight(Position P);
static AVLTree DoubleRotateWithRight(Position P);

AVLTree MakeEmpty(AVLTree T) {
    if(T != NULL) {
        MakeEmpty(T->Left);
        MakeEmpty(T->Right);
        free(T);
    }
    return NULL;
}

//在树T中寻找元素X
Position Find(ElemType X, AVLTree T) {
    //树中不存在X
    if(T == NULL)
        return NULL;
    //X可能在左子树
    if (T->Element > X)
        return Find(X, T->Left);
    //X可能在右子树
    else if (T->Element < X)
        return Find(X, T->Right);
    //当前T节点元素等于X
    return T;
}

Position FindMin(AVLTree T) {
    if(T == NULL)
        return NULL;

    if(T->Left == NULL)
        return T;
    return FindMin(T->Left);
}

Position FindMax(AVLTree T) {
    if(T == NULL)
        return NULL;

    if(T->Right == NULL)
        return T;
    return T->Right;
}

/* 在AVL树中插入元素X
 * 递归结束条件：若传入树为空（当前节点不在树中），创建该节点
 * 1.若X小于当前节点值，T->Left = Insert(X, T->Left)。判断T的左右子树高度差，若等于2，进行判断：
        a.若X小于T->Left->Element，进行单旋（情形1）
        b.若X大于T->Left->Element,进行双旋（情形2）
    2.若X大于当前节点，T->Right = Insert(X, T->Right)。判断T的左右子树高度差，若等于2，进行判断：
        a.若X大于T->Right->ELement,进行单旋（情形4）
        b.若X小于T->Right->Element,进行双旋（情形3）
    3.若X已在树中，不做操作
    4.取出左右子树高度的较大值+1，设为当前节点的高度（新创建节点左右子树高度为-1，因此其高度为0）
**/
AVLTree Insert(ElemType X, AVLTree T) {
    if(T == NULL) {
        T = (AVLTree)malloc(sizeof(struct AVLNode));
        //创建不成功
        if(T == NULL)
            exit(-1);
        //创建成功，初始化T
        else {
            T->Element = X;
            T->Height = 0;
            T->Right = T->Left = 0;
        }
    } 
    // X存在于/应被插入左子树
    else if(X < T->Element) {
        //将X插入左子树
        T->Left = Insert(X, T->Left);
        //若插入后树不平衡，进行旋转
        if(Height(T->Left) - Height(T->Right) == 2) {
            //情形1
            if(X < T->Left->Element) 
                T = SingleRotateWithLeft(T);
            //情形2
            else if(X > T->Left->Element) 
                T = DoubleRotateWithLeft(T);
        }
    } 
    // X存在于/或应被插入右子树
    else if(X > T->Element) {
        //将X插入右子树
        T->Right = Insert(X, T->Right);
        //若树不平衡，进行旋转
        if(Height(T->Right) - Height(T->Left) == 2) {
            //情形4
            if(X > T->Right->Element)
                T = SingleRotateWithRight(T);
            //情形3
            else if(X < T->Right->Element)
                T = DoubleRotateWithRight(T);
        }
    }
    //当前节点高度取左右子树较大值+1
    T->Height = MAX(Height(T->Left), Height(T->Right)) + 1;
    return T;
}


//情形1：P2为根节点，P1为P2左孩子（旋转后的根节点）
static Position SingleRotateWithLeft(Position P2) {
    Position P1;

    P1 = P2->Left;
    //将P1的右子树变成P2的左子树
    P2->Left = P1->Right;
    //将P2变为P1的右子树
    P1->Right = P2;

    //更新P1和P2高度
    P2->Height = MAX(Height(P2->Left), Height(P2->Right)) + 1;
    P1->Height = MAX(Height(P1->Left), P2->Height) + 1;

    //返回新的根节点P1
    return P1;
}

//情形4：P2为根节点，P1为根节点右孩子（旋转后的根节点）
static Position SingleRotateWithRight(Position P2) {
    Position P1;

    P1 = P2->Right;
    //将P1的左孩子设为P2的右孩子
    P2->Right = P1->Left;
    //将P1的左孩子设为P2
    P1->Left = P2;

    //更新P1和P2高度
    P2->Height = MAX(Height(P2->Left), Height(P2->Right)) + 1;
    P1->Height = MAX(Height(P1->Left), P2->Height) + 1;

    //返回新的根节点P1
    return P1;
}

static Position DoubleRotateWithLeft(Position P3) {
    P3->Left = SingleRotateWithRight(P3->Left);
    return SingleRotateWithLeft(P3);
}

static Position DoubleRotateWithRight(Position P3) {
    P3->Right = SingleRotateWithLeft(P3->Right);
    return SingleRotateWithRight(P3);
}

int MAX(int a, int b) {
    return a > b ? a : b;
}