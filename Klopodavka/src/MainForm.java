import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {

    private static final int DEFAULT_WIDTH = MyField.DEFAULT_COUNT * KlopodavkaGameField.CELL_SIZE+30;
    private static final int DEFAULT_HEIGHT = DEFAULT_WIDTH +30;
    private static final int DEFAULT_MARGIN = 50;

    public MainForm(KlopodavkaGameField GameField)
    {
        /* Form's settings */
        setTitle("Klopodvka Game");
        setBounds(DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(GameField, BorderLayout.CENTER);

        setVisible(true);
    }
}
