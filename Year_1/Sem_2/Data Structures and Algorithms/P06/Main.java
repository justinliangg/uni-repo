public class Main
{
    public static void main(String [] args)
    {
        //Reading into graph 1;
        System.out.println("Graph 1: ");
        DSAGraph graph1 = new DSAGraph();
        FileIO.fileReadVertices("prac6_1.al",graph1);
        FileIO.fileReadEdges("prac6_1.al",graph1);
        graph1.display();


        //Reading into graph 2
        System.out.println("\nGraph 2: ");
        DSAGraph graph2 = new DSAGraph();
        FileIO.fileReadVertices("prac6_2.al",graph2);
        FileIO.fileReadEdges("prac6_2.al",graph2);
        graph2.display();
    }
}
