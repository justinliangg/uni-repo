/*---------------------------------------------------------------------------
FILE: LinkedList.h
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 09/10/2021
COMMENTS: Reused from assignment 2.
----------------------------------------------------------------------------*/
#ifndef LINKEDLIST_H
#define LINKEDLIST_H

/*Includes*/
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

/*Typedef funcPtr*/
typedef void (*listFunc)(void* data);

/*Function Prototypes*/
LinkedList* createLinkedList();
void insertStart( LinkedList* list, void* entry );
void* removeStart( LinkedList* list );
void insertLast( LinkedList* list, void* entry );
void* removeLast( LinkedList* list );
void printLinkedList( LinkedList* list, listFunc funcPtr );
void freeLinkedList( LinkedList* list, listFunc funcPtr );

#endif
