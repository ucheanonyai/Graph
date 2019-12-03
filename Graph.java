import java.io.*;
import java.util.*;


////////////////////////////////////////////////////////////////
class Edge
{

    /// To complete
    public int [][]edges;
    public Vertex next;
    public Vertex previous;
    public int srcVert;
    public int destVert;
    public int distance;
    
    public Edge(int sv, int dv, int d) // constructor
    {
        srcVert = sv;
        destVert = dv;
        distance = d;
    }
    
    
    public void setEdgeWeight(int v1,int v2,int weight){
        edges[v1][v2]=weight;
    }
 
}  // end class Edge


////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////


class Vertex
{
    public int label;
    public boolean visited;
    
    public int i,j; // coor
   /// To complete
    public Vertex(int label){
        this.label=label;
        visited=false;
    }
    
    public String toString(){
        String ex=String.valueOf(label);
        return ex;
    }
    
    public int getLabel(){
        return label;
    }
    
    public void setLabel(int set){
        label=set;
    }
    
    public void display(){
        System.out.println(label);
    }
}  // end class Vertex


////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////


class Graph
{
    public Vertex nodes[]; // list of vertices
    private int adjMat[][];      // adjacency matrix
    public int nVertex;          // Number of vertices/nodes
    public int nx;
    public int ny;
    public Vertex tempArray[][];
    int i=0;
    private String name;
    public int currentVert;
    public int nTree;
    Heap thePQ=new Heap();

   
    ////////////// To Complete
    public Graph(){
        nx = 0;
        ny = 0;
        
    }
    
    public Graph(String filename){
    
        this.name=filename;
        String[]temp1=new String[145]; //contains x-axis
        String[]temp2=new String[145];//contains y-axis
        String[]weight=new String[145];//contains weights
       
    
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(this.name);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            

            while((line = bufferedReader.readLine()) != null) {
               
                
                String[]field=new String[3];
                field=line.split(" ");
                if(field.length < 2)
                    continue;
                temp1[i]=field[0];
                temp2[i]=field[1];
                
                if(i>0)
                weight[i]=field[2];
                
                //System.out.println(line);
                i++;
              
            }
            nx=Integer.parseInt(temp1[0]);
            ny=Integer.parseInt(temp2[0]);
         //   Graph fileGraph=new Graph(Integer.parseInt(temp1[0]), Integer.parseInt(temp1[0]));
            nVertex=nx*ny;
            adjMat=new int[nVertex][nVertex];
            nodes=new Vertex[nVertex];
            this.nx=nx;
            this.ny=ny;
        
            for(int i=0;i<nVertex;i++)
            for(int j=0;j<nVertex;j++){
                adjMat[i][j]=0;
            }
        
        for(int i=0;i<nVertex;i++) {
            nodes[i]=new Vertex(i);
            nodes[i].i = i % nx;
            nodes[i].j = i / nx;
        }
        
//        for(int i=0;i<ny;i++)
//            for(int j=0;j<nx;j++){
//                
//                nodes[i].i=ny;
//                nodes[i].j=nx;                              
//            }
        
        for(int z=1;z<i;z++){
             //   System.err.println(i);
             setEdgeWeight(Integer.parseInt(temp1[z]),Integer.parseInt(temp2[z]),Integer.parseInt(weight[z]));
        }
            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                this.name + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '"  + name + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
    }
    
    public void initalMat (){
        adjMat = new int[nVertex][nVertex];
        nodes = new Vertex[nVertex];
        
        
        for(int i=0;i<nVertex;i++) {
            nodes[i]=new Vertex(i);
            nodes[i].i = i % nx;
            nodes[i].j = i / nx;
        }
    }
    
    public Graph(int nx,int ny){
        nVertex=nx*ny;
        adjMat=new int[nVertex][nVertex];
        nodes=new Vertex[nVertex];
        this.nx=nx;
        this.ny=ny;
        
        for(int i=0;i<nVertex;i++)
            for(int j=0;j<nVertex;j++){
                adjMat[i][j]=0;
            }
        
        for(int i=0;i<nVertex;i++) {
            nodes[i]=new Vertex(i);
            nodes[i].i = i % nx;
            nodes[i].j = i / nx;
        }
    } 
    
    public void getGrid(){
        tempArray=new Vertex[nx][ny];
        int z=0;
        for(int i=0;i<ny;i++){
            for(int j=0;j<nx;j++){
                if(z<nodes.length){
                tempArray[j][i]=nodes[z];
                        z++;
                }
                else break;
            }
        }
    }
    public Vertex[][] form2DGrid(){
       
        this.getGrid();
        
        for(int i=0;i<ny;i++){
            for(int j=0;j<nx;j++){
                
                if(j+1<nx)
                addEdge(tempArray[j][i].getLabel(),tempArray[j+1][i].getLabel());
                
                if(i+1<ny)
                    addEdge(tempArray[j][i].getLabel(), tempArray[j][i+1].getLabel());
                
            }
            }
        
        return tempArray;
    }
    
    public Vertex[][] form2DGrid(int weight){
       
        this.getGrid();
        
        for(int i=0;i<ny;i++){
            for(int j=0;j<nx;j++){
                
                if(j+1<nx)
                setEdgeWeight(tempArray[j][i].getLabel(),tempArray[j+1][i].getLabel(),weight);
                
                if(i+1<ny)
                    setEdgeWeight(tempArray[j][i].getLabel(), tempArray[j][i+1].getLabel(),weight);
                
            }
            }
        
        return tempArray;
    }
    
    
    
    public void addEdge(int v1, int v2) { 
        adjMat[v1][v2] = 1;
        adjMat[v2][v1] = 1;
    }
    
    public void setEdgeWeight(int v1,int v2,int weight){
        adjMat[v1][v2] = weight;
        adjMat[v2][v1] = weight;
    }
    
    public int getEdgeWeight(int v1,int v2){
        return adjMat[v1][v2];
    }
    
    public void displayInfoGraph(){
        int n=0;
        this.getGrid();
        
////        for(int i=nx-1;i>=0;i--){
////            for(int j=0;j<ny;j++){
////                System.out.print(this.tempArray[i][j].toString()+"  ");
////                
////            }
////            System.out.println("");
//                      
//        }

        for(int x = 0; x< nVertex; x++) {
            for(int y = x+1; y< nVertex; y++) {
                if(adjMat[x][y]>0) {
                      System.out.println("("+x%nx+","+x/nx+")<-->("+y%nx+","+y/nx+")   "+ adjMat[x][y]);
                      n += adjMat[x][y];
                }
            }
        }
        
//        for(int i=0;i<ny;i++){
//            for(int j=0;j<nx;j++){
//          //    if(adjMat[tempArray[i][j].getLabel()][tempArray[i][j+1].getLabel()]==1){ //  CHECK IF ADJACENCY MATRIX IS ONE OR ZERO
//                if(j+1<nx && adjMat[tempArray[j][i].getLabel()][tempArray[j+1][i].getLabel()]>0){
//                    System.out.println("("+j+","+i+")<-->("+(j+1)+","+(i)+")   "+getEdgeWeight(tempArray[j+1][i].getLabel(),tempArray[j][i].getLabel() ));
//                    n+=getEdgeWeight(tempArray[j][i].getLabel(),tempArray[j+1][i].getLabel() );
//                }
//               // addEdge(tempArray[i][j].getLabel(),tempArray[i][j+1].getLabel());
//                
//                if(i+1<ny && adjMat[tempArray[j][i].getLabel()][tempArray[j][i+1].getLabel()]>0){
//                     System.out.println("("+j+","+i+")<-->("+(j)+","+(i+1)+")   "+getEdgeWeight(tempArray[j][i].getLabel(),tempArray[j][i+1].getLabel() ));
//                    n+=getEdgeWeight(tempArray[j][i].getLabel(),tempArray[j][i+1].getLabel() );
//                }
//      //          }   
//            }
//            }
        
        System.out.println("Total weight: "+n);
    }
    
    public void displayAdjMat(){
        System.out.println("");
        System.out.println("Matrix:");
        for(int i=0;i<nVertex;i++){
            for (int j = 0; j < nVertex; j++) {
                System.out.print(adjMat[i][j]+" ");
                
            }
            System.out.println("");
        }
    }


    public int getnVertex(){
        return nVertex;
    }
    
    public int getNx(){
        return nx;
    }
    
    public int getNy(){
        return ny;
    }
    
    public Graph dfs(int start){
        // make an empty graph
        Graph dfsPath=new Graph();
        
        dfsPath.nx = this.nx;       
        dfsPath.ny = ny;       
        dfsPath.nVertex=nx*ny;
        dfsPath.initalMat();
                
        
        Stack theStack=new Stack(nVertex);
        nodes[start].visited=true;
        dfsPath.nodes[start] = nodes[start];
    //    nodes[start].display();
        theStack.push(start);
        
        while(!theStack.isEmpty()){
            int pk = (int) theStack.peek();
            int v=getAdjUnvisitedNode((int) theStack.peek());
           
            if(v==-1)
                theStack.pop();
            
            else{
                nodes[v].visited=true;
                dfsPath.nodes[v] = nodes[v]; /// add vertex
                dfsPath.setEdgeWeight(v, pk, this.adjMat[v][pk]); // add edge
                
              
                
 //               nodes[v].display();
                theStack.push(v);
            }
        }
        for(int i=0;i<nodes.length;i++) nodes[i].visited=false;
                
        return dfsPath;
       }
    
//    public void addVertex(char c) {
//        if (N >= maxSize) { 
//            System.out.println("Graph full"); 
//            return;
//        }
//        nodes[N++] = new Vertex(c); }
    
    public int getAdjUnvisitedNode(int v){
        for(int i=0;i<nodes.length;i++) 
            if(adjMat[v][i]>0 && nodes[i].visited==false)
                return i;
        
        return -1;
    }
    
    public Graph bfs(int start){
        Queue theQueue=new Queue(nVertex);
        
        Graph dfsPath=new Graph();
        
        dfsPath.nx = this.nx;       
        dfsPath.ny = ny;       
        dfsPath.nVertex=nx*ny;
        dfsPath.initalMat();
        
       // breadth-first search
       // begin at vertex start
        nodes[start].visited = true; // mark it
        dfsPath.nodes[start] = nodes[start];

        theQueue.enqueue(start); // enqueue at tail
           int v2;
        while( !theQueue.isEmpty() ) // until queue empty,
        {
            int v1 = (int)theQueue.dequeue(); // dequeue vertex at head
            // until it has no unvisited neighbors
            while( (v2=getAdjUnvisitedNode(v1)) != -1 )
                { // get one,
                    nodes[v2].visited= true; // mark it
                    dfsPath.nodes[v2] = nodes[v2]; /// add vertex
                    dfsPath.setEdgeWeight(v1, v2,adjMat[v1][v2]); // add edge

                theQueue.enqueue(v2); // enqueue it
                } // end while(unvisited neighbors)
        } // end while(queue not empty)
            // queue is empty, so we’re done
        for(int j=0; j<nVertex; j++) // reset flags
        nodes[j].visited= false;
    
        return dfsPath;
    } // end bfs()
    
    public Graph mstw() // minimum spanning tree
        {
        Graph temp=new Graph();
        temp.nx = this.nx;       
        temp.ny = ny;       
        temp.nVertex=nx*ny;
        temp.initalMat();
        
        currentVert = 3; // start at 3
        while(nTree < nVertex-1) // while not all verts in tree
        { // put currentVert in tree
        nodes[currentVert].visited = true;
        //nTree++;
        // insert edges adjacent to currentVert into PQ
            for(int j=0; j<nVertex; j++) // for each vertex,
                {
                if(j==currentVert) // skip if it’s us
                continue;
                if(nodes[j].visited) // skip if in the tree
                continue;
               int distance = adjMat[currentVert][j];
                 if( distance == 0) // skip if no edge
                    continue;
                putInPQ(j, distance); // put it in PQ (maybe)
                    }
             
            if(thePQ.size()==0) // no vertices in PQ?
            {
                System.out.println("GRAPH NOT CONNECTED");
                break;
                }
                // remove edge with minimum distance, from PQ
        Edge theEdge = thePQ.removeMin();
        int sourceVert = theEdge.srcVert;
        currentVert = theEdge.destVert;
        // display edge from source to current
        if(!nodes[currentVert].visited) // skip if in the tree
        {

            temp.setEdgeWeight(sourceVert, currentVert, adjMat[sourceVert][currentVert]);
        nTree++;
        }
      } // end while(not all verts in tree)
        // mst is complete
       
        //// generate tempArray
        temp.getGrid();
        
        
       for(int j=0; j<nVertex; j++) // unmark vertices
            nodes[j].visited = false;
       
       return temp;
         } // end mstw
    
    
    public void putInPQ(int newVert, int newDist){
// is there another edge with the same destination vertex?
        
        int queueIndex = thePQ.find(newVert);
        if(queueIndex != -1) // got edge’s index
        {
            Edge tempEdge = thePQ.peekN(queueIndex); // get edge
            int oldDist = tempEdge.distance;
            if(oldDist > newDist) // if new edge shorter,
        {
        
        thePQ.removeN(queueIndex); // remove old edge
        Edge theEdge =new Edge(currentVert, newVert, newDist);
        thePQ.insert(theEdge); // insert new edge
        }
        // else no action; just leave the old vertex there
        } // end if
        else // no edge with same destination vertex
        { // so insert new one
        Edge theEdge = new Edge(currentVert, newVert, newDist);
        thePQ.insert(theEdge);
        }
        } // end putInPQ()
    
    

    

    //// Plot the Graph using the StdDraw.java library
    public void plot(String color){

        if (color.equals("BLUE"))
            StdDraw.setPenColor(StdDraw.BLUE);  // change pen color
        else if (color.equals("GRAY"))
            StdDraw.setPenColor(StdDraw.GRAY);  // change pen color
        else if (color.equals("RED"))
            StdDraw.setPenColor(StdDraw.RED);  // change pen color

        for (int i=0;i<nVertex;i++)
            for (int j=0;j<=i;j++)
                if(adjMat[i][j]!=0){
                    StdDraw.setPenRadius(adjMat[i][j]*adjMat[i][j]*0.0025);
                    StdDraw.filledCircle(nodes[i].i,nodes[i].j,0.25);  // plot node
                    StdDraw.filledCircle(nodes[j].i,nodes[j].j,0.25);  // plot node
                    StdDraw.line(nodes[i].i,nodes[i].j,nodes[j].i,nodes[j].j); //plot edges
                }

    }


    

}  // end class Graph

