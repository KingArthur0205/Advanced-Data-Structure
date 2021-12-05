#include "LinkedList.h"

void show(List L) {
	int length = Length(L);
	Position P = L;
	while(P->Next != NULL) {
		printf("%d ", P->Next->Element);
	       P = P->Next;	
	}
}

int main() {
	List L = (List)malloc(sizeof(struct Node));
	L->Element = 0;
	L->Next = NULL;
	int i;
	List ptr = L;
	for(i = 0; i < 10; i++) {
		struct Node* tempNode = (struct Node*)malloc(sizeof(struct Node));
		tempNode->Element = i;
		tempNode->Next = NULL;
		ptr->Next = tempNode;
		ptr = tempNode;
	}
	show(L);
	printf("%p", Find(3, L));
	return 0;
}
