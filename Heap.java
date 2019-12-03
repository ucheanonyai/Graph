
class Heap
{

// array in sorted order, from max at 0 to min at size-1
private final int SIZE = 20;
private Edge[] queArray;
private int size;
// -------------------------------------------------------------
public Heap() // constructor
{
queArray = new Edge[SIZE];
size = 0;
}
// -------------------------------------------------------------
public void insert(Edge item) // insert item in sorted order
{
int j;
for(j=0; j<size; j++) // find place to insert
if( item.distance >= queArray[j].distance )
break;
for(int k=size-1; k>=j; k--) // move items up
queArray[k+1] = queArray[k];
queArray[j] = item; // insert item
size++;
}
// -------------------------------------------------------------
public Edge removeMin() // remove minimum item
{ return queArray[--size]; }

// -------------------------------------------------------------
public void removeN(int n) // remove item at n
{
for(int j=n; j<size-1; j++) // move items down
queArray[j] = queArray[j+1];
size--;
}
// -------------------------------------------------------------
public Edge peekMin() // peek at minimum item
{ return queArray[size-1]; }
// -------------------------------------------------------------
public int size() // return number of items
{ return size; }
// -------------------------------------------------------------
public boolean isEmpty2() // true if queue is empty
{ return (size==0); }
// -------------------------------------------------------------
public Edge peekN(int n) // peek at item n
{ return queArray[n]; }
// -------------------------------------------------------------
public int find(int findDex) // find item with specified
{ // destVert value
for(int j=0; j<size; j++)
if(queArray[j].destVert == findDex)
return j;
return -1;
}






   private Vertex[] heapArray;
   private int maxSize;           // size of array
   private int currentSize;       // number of Vertexs in array
// -------------------------------------------------------------
   public Heap(int mx)            // constructor
      {
      maxSize = mx;
      currentSize = 0;
      heapArray = new Vertex[maxSize];  // create array
      }
// -------------------------------------------------------------
   public boolean isEmpty()
      { return currentSize==0; }
// -------------------------------------------------------------
   public boolean insert(int Label)
      {
      if(currentSize==maxSize)
         return false;
      Vertex newVertex = new Vertex(Label);
      heapArray[currentSize] = newVertex;
      trickleUp(currentSize++);
      return true;
      }  // end insert()
// -------------------------------------------------------------
   public void trickleUp(int index)
      {
      int parent = (index-1) / 2;
      Vertex bottom = heapArray[index];

      while( index > 0 &&
             heapArray[parent].getLabel()< bottom.getLabel() )
         {
         heapArray[index] = heapArray[parent];  // move it down
         index = parent;
         parent = (parent-1) / 2;
         }  // end while
      heapArray[index] = bottom;
      }  // end trickleUp()
// -------------------------------------------------------------
   public Vertex remove()           // delete item with max Label
      {                           // (assumes non-empty list)
      Vertex root = heapArray[0];
      heapArray[0] = heapArray[--currentSize];
      trickleDown(0);
      return root;
      }  // end remove()
// -------------------------------------------------------------
   public void trickleDown(int index)
      {
      int largerChild;
      Vertex top = heapArray[index];       // save root
      while(index < currentSize/2)       // while Vertex has at
         {                               //    least one child,
         int leftChild = 2*index+1;
         int rightChild = leftChild+1;
                                         // find larger child
         if(rightChild < currentSize &&  // (rightChild exists?)
                             heapArray[leftChild].getLabel() <
                             heapArray[rightChild].getLabel())
            largerChild = rightChild;
         else
            largerChild = leftChild;
                                         // top >= largerChild?
         if( top.getLabel() >= heapArray[largerChild].getLabel() )
            break;
                                         // shift child up
         heapArray[index] = heapArray[largerChild];
         index = largerChild;            // go down
         }  // end while
      heapArray[index] = top;            // root to index
      }  // end trickleDown()
// -------------------------------------------------------------
   public boolean change(int index, int newValue)
      {
      if(index<0 || index>=currentSize)
         return false;
      int oldValue = heapArray[index].getLabel(); // remember old
      heapArray[index].setLabel(newValue);  // change to new

      if(oldValue < newValue)             // if raised,
         trickleUp(index);                // trickle it up
      else                                // if lowered,
         trickleDown(index);              // trickle it down
      return true;
      }  // end change()
// -------------------------------------------------------------
   public void displayHeap()
      {
      System.out.print("heapArray: ");    // array format
      for(int m=0; m<currentSize; m++)
         if(heapArray[m] != null)
            System.out.print( heapArray[m].getLabel() + " ");
         else
            System.out.print( "-- ");
      System.out.println();
                                          // heap format
      int nBlanks = 32;
      int itemsPerRow = 1;
      int column = 0;
      int j = 0;                          // current item
      String dots = "...............................";
      System.out.println(dots+dots);      // dotted top line

      while(currentSize > 0)              // for each heap item
         {
         if(column == 0)                  // first item in row?
            for(int k=0; k<nBlanks; k++)  // preceding blanks
               System.out.print(' ');
                                          // display item
         System.out.print(heapArray[j].getLabel());

         if(++j == currentSize)           // done?
            break;

         if(++column==itemsPerRow)        // end of row?
            {
            nBlanks /= 2;                 // half the blanks
            itemsPerRow *= 2;             // twice the items
            column = 0;                   // start over on
            System.out.println();         //    new row
            }
         else                             // next item on row
            for(int k=0; k<nBlanks*2-2; k++)
               System.out.print(' ');     // interim blanks
         }  // end for
      System.out.println("\n"+dots+dots); // dotted bottom line
      }  // end displayHeap()
// -------------------------------------------------------------
   }  // end class Heap

// -------------------------------------------------------------

   
    

