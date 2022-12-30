#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include <stdio.h>
#include <stdlib.h>

/*Declaration of LinkedList and Nodes*/
typedef struct LinkedList
{
    struct ListNode* head;
    struct ListNode* tail;
    int length;
} LinkedList;

typedef struct ListNode
{
    void* data;
    struct ListNode* next;
} ListNode;

/*TypeDef funcPtr*/
typedef void (*listFunc)(void* data);

/*Function Prototypes*/
LinkedList* createLinkedList();
void insertStart(LinkedList* list, void* entry);
void* removeStart(LinkedList* list);
void insertLast(LinkedList* list, void* entry);
void* removeLast(LinkedList* list);
void* getData(LinkedList* list, int index);
void printLinkedList(LinkedList* list, listFunc funcPtr);
void freeLinkedList(LinkedList* list, listFunc funcPtr);

#endif
