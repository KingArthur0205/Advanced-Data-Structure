#include "LinkedList.h"

int Length(List L) {
	assert(L);

	int length = 0;
	Position P = L;
	while(P->Next != NULL) {
		++length;
		P = P->Next;
	}
	return length;
}

//A function that examines if a list is empty
int IsEmpty(List L) {
	//If the input list doesn't exist, the assert function will report a fault to the system.
	assert(L);
	return L->Next == NULL;
}

/*
 * Return tru if P is the last position in List L.
 * Prameter L is unused in this implementation
 */
int IsLast(Position P, List L) {
	assert(L && P);
	return P->Next == NULL;
}

/*
 *Return Position of X in L; NULL if not found
 */
Position Find(ElemType X, List L) {
	assert(L);
	
	Position P;
	P = L->Next;

	while(P != NULL && P->Element != X)
		P = P->Next;
	return P;
}

/*
 * Delete the first occurence of X in the List L.
 * If X is not found, do nothing.
 */
void Delete(ElemType X, List L) {
	//Find the element that is previous to X
	Position P = FindPrevious(X, L);

	//If P is the last elemetn in the list, meaning that X does not exist in L, do nothing.
	if(!IsLast(P, L)) {
		//Record the node that we want to delete
		Position TempCell = P->Next;
		P->Next = TempCell->Next;
		//Free the delted node.
		free(TempCell);
	}
}

/*
 * If X is not found, then the last element in the list is returned.
 * Assume a header
 */
Position FindPrevious(ElemType X, List L) {
	assert(L);
	Position P = L;

	while(P->Next != NULL && P->Next->Element != X)
		P = P->Next;
	return P;
}
