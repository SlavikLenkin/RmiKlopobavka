import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class KlopodavkaGameField extends JPanel {

    public static final int CELL_SIZE = 35;//50
    public static final int FIELD_SIZE =CELL_SIZE * MyField.DEFAULT_COUNT;
    public static final int baseGreen=1;
    public static final int baseBlue=7;
  
   
    private KlopodavkaClientImp myClient;
   
    

    public KlopodavkaGameField(KlopodavkaClientImp Client) {
        setVisible(false);
        myClient = Client;
        Client.setGameField(this);
        repaint();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x = e.getX() / CELL_SIZE;
                int y = e.getY() / CELL_SIZE;
                System.out.println("Click on " + x + " " + y);
                try {
                    myClient.click(x, y);
                } catch (RemoteException E) {
                    System.out.println(E.getMessage());
                }
                repaint();
            }
        });
      
        setVisible(true);
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Рисуем линии, что представляют из себя сетку.
        
       
        for (int i = 0; i <= myClient.getField().getCount(); i++) {
            g.drawLine(0, i * CELL_SIZE, FIELD_SIZE, i * CELL_SIZE);
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, FIELD_SIZE);
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5.0f)); 
        Font myFont = new Font ("Courier New", 1, 25);
        g.setFont(myFont);
        
        
        for (int i = 0; i < myClient.getField().getCount(); i++) {
            for (int j = 0; j < myClient.getField().getCount(); j++) {
                if (myClient.getField().getCellType(i, j) != MyCell.EMPTY) {
                    if (myClient.getField().getCellType(i,j) == MyCell.GREEN) {
                        g.setColor(Color.GREEN);
                        g.drawLine((i * CELL_SIZE), (j * CELL_SIZE), (i + 1) * CELL_SIZE, (j + 1) * CELL_SIZE);
                        g.drawLine((i + 1) * CELL_SIZE, (j * CELL_SIZE), (i * CELL_SIZE), (j + 1) * CELL_SIZE);
                        }
                    if (myClient.getField().getCellType(i,j) == MyCell.KILL_BLUE) {
                        g.setColor(Color.GREEN);
                        g.drawLine((i * CELL_SIZE), (j * CELL_SIZE), (i + 1) * CELL_SIZE, (j + 1) * CELL_SIZE);
                        g.drawLine((i + 1) * CELL_SIZE, (j * CELL_SIZE), (i * CELL_SIZE), (j + 1) * CELL_SIZE);
                        g.setColor(Color.BLUE);
                        g.drawLine((i * CELL_SIZE)+CELL_SIZE/2, (j * CELL_SIZE), (i + 1) * CELL_SIZE-CELL_SIZE/2, (j + 1) * CELL_SIZE);
                        
                        
                    }
                    if (myClient.getField().getCellType(i,j) == MyCell.KILL_GREEN) {
                        g.setColor(Color.BLUE);
                        g.drawLine((i * CELL_SIZE), (j * CELL_SIZE), (i + 1) * CELL_SIZE, (j + 1) * CELL_SIZE);
                        g.drawLine((i + 1) * CELL_SIZE, (j * CELL_SIZE), (i * CELL_SIZE), (j + 1) * CELL_SIZE);
                        
                        g.setColor(Color.GREEN);
                        g.drawLine((i * CELL_SIZE)+CELL_SIZE/2, (j * CELL_SIZE), (i + 1) * CELL_SIZE-CELL_SIZE/2, (j + 1) * CELL_SIZE);
                        
                        
                    }
                    if (myClient.getField().getCellType(i, j) == MyCell.BLUE) {
                        g.setColor(Color.BLUE);
                        g.drawLine((i * CELL_SIZE), (j * CELL_SIZE), (i + 1) * CELL_SIZE, (j + 1) * CELL_SIZE);
                        g.drawLine((i + 1) * CELL_SIZE, (j * CELL_SIZE), (i * CELL_SIZE), (j + 1) * CELL_SIZE);
                        
                    }
                    if (myClient.getField().getCellType(i,j) == MyCell.WALL) {
                        g.setColor(Color.BLACK);
                        g.drawLine((i * CELL_SIZE), (j * CELL_SIZE), (i + 1) * CELL_SIZE, (j + 1) * CELL_SIZE);
                        g.drawLine((i + 1) * CELL_SIZE, (j * CELL_SIZE), (i * CELL_SIZE), (j + 1) * CELL_SIZE);
                    }
                    if(myClient.getField().endGame==0)
                    g.drawString("Game Over", (myClient.getField().getCount()-2)*CELL_SIZE/2, myClient.getField().getCount()*CELL_SIZE/2);
                }
            }
        }

        //TODO Draw game over
    }
}

