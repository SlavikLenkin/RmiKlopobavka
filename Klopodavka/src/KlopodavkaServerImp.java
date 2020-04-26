 import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class KlopodavkaServerImp implements KlopodavkaServer {

    public static final String TAG = "KlopodavkaServerImp";
    
    
    private static final String localhost = "127.0.0.1";
    private static final int port = 8777;
    
    private MyField myField;
    private KlopodavkaClient myGreenPlayer = null;
    private KlopodavkaClient myBluePlayer = null;
    private int numberClick=1;

    public KlopodavkaServerImp(){
        myField = new MyField(MyField.DEFAULT_COUNT);
    }

    @Override
    public boolean SetGreenPlayer() throws RemoteException, NotBoundException, MalformedURLException {
        if (myGreenPlayer!= null) return false;
        Registry registry = LocateRegistry.getRegistry(localhost, 8776);
        myGreenPlayer = (KlopodavkaClient) registry.lookup(GreenClientImp.TAG);
        System.out.println("Green player is connected!");
        if (myBluePlayer != null) {
            myGreenPlayer.startPlay(true);
            myBluePlayer.startPlay(false);
        }

        return true;
    }

    @Override
    public boolean SetBluePlayer() throws RemoteException, NotBoundException, MalformedURLException {
        if (myBluePlayer != null) return false;
        Registry registry = LocateRegistry.getRegistry(localhost, 8775);
        myBluePlayer = (KlopodavkaClient) registry.lookup(BlueClientImp.TAG);
        System.out.println("Blue player is connected!");
        if (myBluePlayer != null) {
            myGreenPlayer.startPlay(true);
            myBluePlayer.startPlay(false);
        }
        return true;
    }

    @Override
    public boolean clickFromPlayer(int x, int y, int turn) throws RemoteException {
        boolean isOkay = myField.setCellType(x, y, turn);
        if (isOkay) {
            if(turn == MyCell.GREEN) {
               if(numberClick==3){
               myGreenPlayer.setTurn(false);
               myBluePlayer.setTurn(true);
               numberClick=0;
               }
               else
               { 
                 myGreenPlayer.setTurn(true);
                 myBluePlayer.setTurn(false);
               }
               myGreenPlayer.updateCell(x, y, turn);
               myBluePlayer.updateCell(x, y, turn);
               numberClick++;
            } else if (turn == MyCell.BLUE) {
               if(numberClick==3){
               myGreenPlayer.setTurn(true);
               myBluePlayer.setTurn(false);
               numberClick=0;
               }
               else
               { myGreenPlayer.setTurn(false);
                 myBluePlayer.setTurn(true);
               }
                myGreenPlayer.updateCell(x, y, turn);
                myBluePlayer.updateCell(x, y, turn);
                numberClick++;
                
            }
        }
        
        return isOkay;
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        //Создание удаленного RMI Объекта.
        KlopodavkaServerImp server = new KlopodavkaServerImp();
        KlopodavkaServer stub = (KlopodavkaServer) UnicastRemoteObject.exportObject(server, port);
        System.out.println("Initializing " + TAG);

        //Регистрация удаленного RMI Объекта в реестре
        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind(TAG, stub);
        System.out.println("Starting " + TAG);
    }
}
