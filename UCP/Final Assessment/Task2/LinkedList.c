/*---------------------------------------------------------------------------
FILE: LinkedList.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 09/10/2021
PURPOSE: generic LinkedList to dynamically store data
REQUIRES: NIL
COMMENTS: Reused from assignment 2
----------------------------------------------------------------------------*/
#include "LinkedList.h"



/*----------------------------------------------------------------------------
CONSTRUCTOR: createLinkedList
IMPORT: NIL
EXPORT: list(LinkedList*)
ASSERTION: allocate memory for a LinkedList, initialize its values and return
the LinkedList.
----------------------------------------------------------------------------*/

LinkedList* createLinkedList()
{
    LinkedList* list = (LinkedList*) malloc(sizeof(LinkedList)); 
    list->head = NULL;
    list->tail = NULL;
    list->length = 0;
    return list;
}




/*===========================================================================*/
/*MUTATORS*/

/*----------------------------------------------------------------------------
MUTATOR: insertStart
IMPORT: list(LinkedList*), entry(void*)
EXPORT: NIL
ASSERTION: insert entry into the front of the list
----------------------------------------------------------------------------*/

void insertStart( LinkedList* list, void* entry )
{   

    /*Make a new ListNode*/
    ListNode* newNode = (ListNode*) malloc(sizeof(ListNode));
    newNode->data = entry;
    newNode->next = NULL;

    /*Checking if list is empty*/
    if(list->head == NULL)
    {
        list->head = newNode;
        list->tail = newNode;
    }
    else
    {
        /*Point newNode to head first*/
        newNode->next = list->head;

        /*Reassign head as newNode*/
        (list->head) = newNode;
    }
    
    /*add to list count*/
    (list->length)++;
}




/*----------------------------------------------------------------------------
MUTATOR: removeStart
IMPORT: list(LinkedList*)
EXPORT: value(void*)
ASSERTION: remove ListNode from front of the list and return its value
----------------------------------------------------------------------------*/

void* removeStart( LinkedList* list )
{   
    void* value;

    if(list->head == NULL)
    {
        value = NULL;
    }
    else if(list->head == list->tail) /*only one node in list*/
    { 
        /*Retrieving value first*/
        value = (list->head)->data;
        
        /*remove head and tail*/
        free(list->head);
        list->head = NULL;
        list->tail = NULL;
        
        /*reducing length*/
        (list->length)--;
    }
    else
    {   
        ListNode* currNode;

        /*Retrieve Value first*/
        value = (list->head)->data;
        
        /*Remove head by pointing to nextNode*/
        currNode = list->head;
        list->head = (list->head)->next; /*change heads first*/
        
        /*free currNode*/
        free(currNode);
        currNode = NULL;
        
        /*reducing length*/
        (list->length)--;
    }

    return value;
}




/*----------------------------------------------------------------------------
MUTATOR: insertLast
IMPORT: list(LinkedList*), entry(void*)
EXPORT: NIL
ASSERTION: insert entry into the end of the list
----------------------------------------------------------------------------*/

void insertLast( LinkedList* list, void* entry )
{
    /*Make a new ListNode*/
    ListNode* newNode = (ListNode*) malloc(sizeof(ListNode));
    newNode->data = entry;
    newNode->next = NULL;

    /*Checking if list is empty*/
    if(list->head == NULL)
    {
        list->head = newNode;
        list->tail = newNode;
    }
    else
    {
        /*point tail next to newNode first*/
        list->tail->next = newNode;

        /*assign newNode as tail*/
        list->tail = newNode;
    }

    /*add to list count*/
    (list->length)++;
}




/*----------------------------------------------------------------------------
MUTATOR: removeLast
IMPORT: list(LinkedList*)
EXPORT: value(void*)
ASSERTION: remove ListNode from end of the list and return its value
----------------------------------------------------------------------------*/

void* removeLast( LinkedList* list )
{
    void* value;

    if( list->head == NULL )
    {
        value = NULL;
    }
    else if( list->head == list->tail ) /*only one node in list*/
    {   
        /*retrieving value first*/
        value = list->tail->data;
        
        /*remove head and tail*/ 
        free(list->tail);   
        list->head = NULL;
        list->tail = NULL;
         
        /*reducing length*/
        (list->length)--;
    }
    else
    {   
        ListNode* prevNode;
        ListNode* currNode;

        value = list->tail->data;
        
        /*iterate through list to find node before tail*/
        /*by end of loop, prevNode is the node before the tail*/
        prevNode = NULL;
        currNode = list->head;
        while( currNode->next != NULL )
        {   
            prevNode = currNode;
            currNode = currNode->next;
        }

        /*Assign Node before tail node as new Tail node*/
        list->tail = prevNode; 
        prevNode->next = NULL;
        
        /*free old tail node*/
        free(currNode);
        currNode = NULL;

        /*reducing length*/
        (list->length)--;
    }

    return value;
}




/*----------------------------------------------------------------------------
mutator: printlinkedlist
import: list(linkedlist*), funcptr(listfunc)
export: nil
assertion: print all the data inside in the list
----------------------------------------------------------------------------*/

void printLinkedList( LinkedList* list, listFunc funcPtr )
{   
    if( list->head == NULL )
    {
        printf("List is empty");
    }
    else
    {   
        /*iterating through list and printing out value*/
        ListNode* currNode = list->head; 
        while( currNode != NULL )
        {
            (*funcPtr)(currNode->data);
            currNode = currNode->next;
        }
    }
}




/*----------------------------------------------------------------------------
MUTATOR: freeLinkedList
IMPORT: list(LinkedList*), listFunc funcPtr
EXPORT: value(void*)
ASSERTION: free all ListNodes in list and the data in the list using funcPtr.
----------------------------------------------------------------------------*/

void freeLinkedList( LinkedList* list, listFunc funcPtr )
{  
    ListNode* currNode = list->head;
    while(currNode != NULL)
    {
        /*Create a reference to nextNode to avoid losing reference after 
        freeing currNode*/
        ListNode* nextNode = currNode->next;
        
        /*freeing currNode*/
        (*funcPtr)(currNode->data);
        free(currNode); 
        currNode = nextNode;
    }

    /*freeing the whole list at the end*/
    free(list);
    list = NULL;
}
       


    


     

     
    


