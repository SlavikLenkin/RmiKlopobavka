public class MyField {

    /** Default count of row and column */
    public static final int DEFAULT_COUNT = 10;//19
    public static final int baseGreen = 1;
    public static final int baseBlue = 8;
     
   
    public int endGame=1;
    public int myCount;
    public MyCell[][] myCells;

    public MyField(int Count) {
        myCount = Count;
        myCells = new MyCell[myCount][myCount];
       
        for (int i = 0; i < myCount; i++)
            for (int j = 0; j < myCount; j++){
                myCells[i][j] = new MyCell(MyCell.EMPTY);
               
            }
        myCells[baseGreen][baseGreen]=new MyCell(MyCell.GREEN);
        myCells[baseBlue][baseBlue]=new MyCell(MyCell.BLUE);
       
         for (int i = 0; i < myCount; i++){
              myCells[0][i] = new MyCell(MyCell.WALL);
              myCells[i][0] = new MyCell(MyCell.WALL);
              myCells[myCount-1][i] = new MyCell(MyCell.WALL);
              myCells[i][myCount-1] = new MyCell(MyCell.WALL);
         }
         
        
    }
    

    public int getCount() {
        return myCount;
    }

    public int getCellType(int X, int Y) {
        if (X < 0 || X >= myCount || Y < 0 || Y >= myCount) return MyCell.NOT_CELL.getType();
        return myCells[X][Y].getType();
    }
    public boolean setCellTypeStart(int X, int Y, int Type) {
        myCells[X][Y].setType(Type);
        return true;
    }
    public void checkCells(int x, int y,int Type) {
		  if (x < 0 || x >= myCount || y < 0 || y >= myCount || myCells[x][y].isChecked ||myCells[x][y].getType()==MyCell.WALL)
			return;
                if(Type==MyCell.GREEN){
		if ((myCells[x][y].getType()) == MyCell.EMPTY || myCells[x][y].getType()== MyCell.BLUE && ! (x == baseBlue && y == baseBlue)) {	 // isn't enemy's base
			myCells[x][y].isChecked = true;
			myCells[x][y].isAvailable = true;
			endGame++;
		} else if (myCells[x][y].getType() == MyCell.GREEN || myCells[x][y].getType() == MyCell.KILL_GREEN) {
		myCells[x][y].isChecked = true;

			checkCells(x-1,y-1,Type);
			checkCells(x-1,y,Type);
			checkCells(x-1,y+1,Type);
			checkCells(x,y-1,Type);
			checkCells(x,y+1,Type);
			checkCells(x+1,y-1,Type);
			checkCells(x+1,y,Type);
			checkCells(x+1,y+1,Type);
		}
                }
                if(Type==MyCell.BLUE){
                    if ((myCells[x][y].getType()) == MyCell.EMPTY || myCells[x][y].getType()== MyCell.GREEN && ! (x == baseGreen && y == baseGreen)) {	 // isn't enemy's base
			myCells[x][y].isChecked = true;
			myCells[x][y].isAvailable = true;
			endGame++;
		} else if (myCells[x][y].getType() == MyCell.BLUE || myCells[x][y].getType() == MyCell.KILL_BLUE) {
		myCells[x][y].isChecked = true;

			checkCells(x-1,y-1,Type);
			checkCells(x-1,y,Type);
			checkCells(x-1,y+1,Type);
			checkCells(x,y-1,Type);
			checkCells(x,y+1,Type);
			checkCells(x+1,y-1,Type);
			checkCells(x+1,y,Type);
			checkCells(x+1,y+1,Type);
		}
                }
	}
    public void setTableUnchecked() {
		for (int i = 0; i < myCount; i++)
			for (int j = 0; j < myCount; j++)
				myCells[i][j].isChecked = false;
                
               }
    public void setTableUnAvilable(){
        for (int i = 0; i <myCount; i++)
                 for (int j = 0; j < myCount; j++)
                    myCells[i][j].isAvailable = false;
    }
      public boolean setCellType(int X, int Y, int Type) {
          if (endGame == 0){
              System.out.print("End Game\n");
              return true;
          }
           setTableUnchecked();
           setTableUnAvilable();
           endGame=0;
           if( Type== MyCell.GREEN)
                 checkCells(baseGreen,baseGreen,MyCell.GREEN);
             if( Type== MyCell.BLUE)
                  checkCells(baseBlue,baseBlue,MyCell.BLUE);
        if (X < 0 || X >= myCount || Y < 0 || Y >= myCount) return false;
        if (myCells[X][Y].getType() == MyCell.WALL) return false;
        if (myCells[X][Y].getType() == Type) return false;
        if (myCells[X][Y].getType() == MyCell.KILL_BLUE) return false;
        if (myCells[X][Y].getType() == MyCell.KILL_GREEN) return false;
        if (myCells[X][Y].isAvailable) {
        if(Type == MyCell.BLUE)
        {
            if (myCells[X][Y].getType() == MyCell.EMPTY)
                     myCells[X][Y].setType(Type);
                 else{
                     
                     myCells[X][Y].setType(MyCell.KILL_BLUE);
                 }
                 myCells[X][Y].isAvailable = false;
                 myCells[X][Y].isChecked = false;
                 checkCells(X,Y,Type);
                 System.out.print("BLUE "+endGame+"\n");
                 if (endGame == 0)
                     System.out.print("End Game");
                 return true;
            
        }
       if(Type == MyCell.GREEN)
        {  if (myCells[X][Y].getType() == MyCell.EMPTY)
                    myCells[X][Y].setType(Type);
                else{
                    
                    myCells[X][Y].setType(MyCell.KILL_GREEN);
                }
                myCells[X][Y].isAvailable = false;
                myCells[X][Y].isChecked = false;
		checkCells(X,Y,Type);
                 System.out.print("GREEN "+ endGame+"\n");
                if (endGame == 0)
                     System.out.print("End Game");
                return true;
            }
        }
       
        return false;
        }
  
}
