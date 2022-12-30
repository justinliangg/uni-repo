User Guide:

To run Simulation Mode: java gameofcatz -s [inputfile] [outputfile];
To run Interactive mode: java gameofcatz -i

Testing of Classes:
FileIO, gameofcatz, InteractiveMode, SimulationMode, Display,
Operations, and ModifyTarget can only be tested by using the 
scripts described in the traceability matrix.

The rest utilises test harnesses.


Descriptions of classes

DSAGraph: graph data structure, requires both DSAHashTable and DSALinkedList

DSALinkedList: abstract data type used to store data dynamically. 

DSAHashTable: hashes a key into an index for the value to be stored 

DSAHeap: data type used to store a DSAHashEntry object. Requires DSAHashEntry

FileIO.java: stores methods used for file input and output. Requires 

World: contains all the data structures required for the World. Requires DSAHashTable, DSALinkedList and DSAGraph

gameofcatz: contains the main method used to run the class: requires InteractiveMode and SimulationMode.

InteractiveMode: stores methods used for the Interactive Menu. requires World

SimulationMode: stores methods used to run simulation of the "World", 
it processes the data from the file input, generate and ranks routes. requires World

Display: stores methods used to display the graph and the World in interactive mode. requires World

Operations: stores all methods used to perform node and edge operations on the World. Requires World

ModifyTarget: stores methods used to change the start and target in interactive mode. Requires World

