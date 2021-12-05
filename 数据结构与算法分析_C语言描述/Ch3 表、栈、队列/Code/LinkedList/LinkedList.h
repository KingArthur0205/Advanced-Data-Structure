#include <assert.h>
#include <stdlib.h>
#include <stdio.h>


struct Node;
typedef int ElemType;
typedef struct Node *PtrToNode; //A pointer that points to an object of struct Node
typedef PtrToNode List; //Rename PtrToNode List
typedef PtrToNode Position; //Rename PtrToNode position

int Length(List L); //Get the length of List L
void DeleteAll(ElemType X, List L); //Delete all of the nodes with value X

int IsEmpty(List L); //A function evaluates if a list is empty;
int IsLast(Position P, List L); //A function examines if Position P is the last element in the List. Assume P exists.
Position Find(ElemType X, List L); //Return Position of X in L; NULL if not found
void Delete(ElemType X, List L); //Delete Element X in the List L. If X is not in L, do nothing.
Position FindPrevious(ElemType X, List L); //Find the element that is previous to X
void Insert(ElemType X, List L, Position P);
void DeleteList(List L); //Delete all of the elements in a list but keep the header.
Position Header(List L); //Get the header of the list.
Position First(List L); //Get the first element of the list. If it is NULL, return NULL
ElemType Retrieve(Position P); //Get the value of the element at Position P

struct Node {
	ElemType Element; //Store Data
	Position Next; //The position of the next node.
};
