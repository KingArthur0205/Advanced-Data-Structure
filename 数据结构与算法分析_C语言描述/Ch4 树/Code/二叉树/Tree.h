typedef int ELemType;
typedef struct TreeNode *PtrToNode;
typedef PtrToNode Tree;

struct TreeNode {
    ElemType Element;
    Tree Left;
    Tree Right;
};