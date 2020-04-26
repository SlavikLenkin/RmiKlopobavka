
import java.rmi.RemoteException;

public class KlopodavkaClientImp
        implements KlopodavkaClient{

    protected static final String localhost = "127.0.0.1";
    protected static final String RMI_HOSTNAME = "java.rmi.server.hostname";
    protected static final String SERVICE_PATH = "rmi://localhost/KlopodavkaServerImp";

    private KlopodavkaGameField myGameField = null;
    private MyField myField = null;
    protected KlopodavkaServer myServer = null;
    private int myType = MyCell.EMPTY;
    protected boolean myTurn = false;

    public KlopodavkaClientImp() throws RemoteException {
        super();
        myField = new MyField(MyField.DEFAULT_COUNT);
    }

    public void setGameField(KlopodavkaGameField aGameField) {
        myGameField = aGameField;
    }

    @Override
    public boolean click(int x, int y) throws RemoteException {
        if(!myTurn) return false;
        return myServer.clickFromPlayer(x, y, myType);
    }

    @Override
    public void startPlay(boolean Turn) throws RemoteException {
        myTurn = Turn;
        System.out.println("Start Play!");
        MainForm form = new MainForm(new KlopodavkaGameField(this));
        form.setVisible(true);
    }

    @Override
    public boolean updateCell(int X, int Y, int Type) throws RemoteException {
        boolean isOkay = myField.setCellType(X, Y, Type);
        myGameField.repaint();
        return isOkay;
    }

    @Override
    public void setTurn(boolean Turn) throws RemoteException {
       /* mField.setTableUnAvilable();
        mField.setTableUnchecked();*/
             myTurn = Turn;
    }

    @Override
    public MyField getField() {
        return myField;
    }

    public boolean isTurn() { return myTurn; }

    @Override
    public int getType() throws RemoteException {
        return myType;
    }
}
