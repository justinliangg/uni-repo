#include "LinkedList.h"

/*Constructor*/
LinkedList* createLinkedList()
{
    LinkedList* list = (LinkedList*) malloc(sizeof(LinkedList)); 
    list->head = NULL;
    list->tail = NULL;
    list->length = 0;
    return list;
}

/*---------------------------------------------------------------------------*/
/*Mutators*/


/*insertStart*/

void insertStart(LinkedList* list, void* entry)
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



/*removeStart*/

void* removeStart(LinkedList* list)
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



/*insertLast*/

void insertLast(LinkedList* list, void* entry)
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



/*removeLast*/

void* removeLast(LinkedList* list)
{
    void* value;

    if(list->head == NULL)
    {
        value = NULL;
    }
    else if(list->head == list->tail) /*only one node in list*/
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
        
        /*iterate through list to find node*/
        /*by end of loop, prevNode is the node before the tail*/
        prevNode = NULL;
        currNode = list->head;
        while(currNode->next != NULL)
        {   
            prevNode = currNode;
            currNode = currNode->next;
        }

        /*Assign Node before tail node as new Tail node*/
        list->tail = prevNode; 
        prevNode->next = NULL;
        
        /*free current node*/
        free(currNode);
        currNode = NULL;

        /*reducing length*/
        (list->length)--;
    }

    return value;
}



/*getData*/
void* getData(LinkedList* list, int index)
{   
    void* data = NULL;
    int count = 0;
    ListNode* currNode = list->head; 
    /*interating through list and printing out value*/
    while(currNode != NULL)
    {   
        if(count == index)
        {
            data = currNode->data;
        }
        
        currNode = currNode->next;
        count++;
    }

    return data;
}
 
            

/*printLinkedList*/

void printLinkedList(LinkedList* list, listFunc funcPtr)
{   
    if(list->head == NULL)
    {
        printf("List is empty");
    }
    else
    {   
        /*interating through list and printing out value*/
        ListNode* currNode = list->head; 
        while(currNode != NULL)
        {
            (*funcPtr)(currNode->data);
            currNode = currNode->next;
        }
    }
}
     


/*freeLinkedList*/

void freeLinkedList(LinkedList* list, listFunc funcPtr)
{  
    ListNode* currNode = list->head;
    while(currNode != NULL)
    {
        /*Create a temp so we don't lose reference after freeing currNode*/
        ListNode* nextNode = currNode->next;

        (*funcPtr)(currNode->data);
        free(currNode); 
        currNode = nextNode;
    }

    /*freeing the whole list at the end*/
    free(list);
    list = NULL;
}
       


    


     

     
    


