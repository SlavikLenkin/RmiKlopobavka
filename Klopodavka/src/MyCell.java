public class MyCell {

    /** Cell's Type Enum*/
    public static final int NOTHING = -1;
    public static final int EMPTY = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
    public static final int KILL_GREEN = 3;
    public static final int KILL_BLUE=4;
    public static final int WALL=5;

    public static final MyCell NOT_CELL = new MyCell(NOTHING);

    /** Cell's Type */
    public int myType;
    public boolean isChecked;
    public boolean isAvailable;
    
    

    public MyCell(int Type) { 
        isChecked=false;
        isAvailable=false;
        myType = Type; }

    public int getType() { return myType; }

    public void setType(int Type) { myType = Type; }

}
