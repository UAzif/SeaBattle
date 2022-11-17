import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGraf {
    private int id;
    static int counter;
    JFrame frame;
    JPanel panel1;
    JPanel panel2;
    JButton buttonForSet = new JButton();
    JButton clickButton;
    JLabel label;
    public JButton[][] setButtons = new JButton[10][10];
    public JButton[][] clickButtons = new JButton[10][10];
    public int[][] intArr;

    GameGraf(int x, int y, Game game, GameLogic gL, int id) {
        this.id = id;

        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBounds(x, y, 340, 750);
        frame.setVisible(true);

        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setVisible(true);
        panel1.setBackground(Color.LIGHT_GRAY);
        panel1.setBounds(5, 5, 305, 350);
        frame.add(panel1);
        label = new JLabel();
        label.setBounds(50, 310, 200, 25);
        label.setVisible(false);
        panel1.add(label);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttonForSet = new JButton();
                buttonForSet.setBounds(j * 30 + 2, i * 30 + 2, 30, 30);
                buttonForSet.setBackground(Color.WHITE);
                buttonForSet.setVisible(true);
                setButtons[i][j] = buttonForSet;
                int finalI = i;
                int finalJ = j;
                setButtons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gL.filling(setButtons, finalI, finalJ);
                    }
                });
                panel1.add(buttonForSet);
            }
        }
        JButton readyButton = new JButton("Старт!");
        readyButton.setBounds(100, 310, 100, 25);
        readyButton.setVisible(true);
        panel1.add(readyButton);
        readyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                intArr = new int[10][10];
                intArr = gL.maSS(setButtons);
                game.getArr(intArr, id);
                readyButton.setVisible(false);
                label.setVisible(true);
                game.startOfGame(counter,id);
                gL.blockButton(setButtons);
            }
        });
        panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setVisible(true);
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setBounds(5, 360, 310, 310);
        frame.add(panel2);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                clickButton = new JButton();
                clickButton.setBounds(j * 30 + 2, i * 30 + 2, 30, 30);
                clickButton.setVisible(true);
                clickButtons[i][j] = clickButton;
                int finalI = i;
                int finalJ = j;
                clickButtons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        label.setText("");
                        game.pr(clickButtons, finalI, finalJ, id, gL);
                    }
                });
                panel2.add(clickButton);
                panel2.repaint();
            }
        }
        frame.repaint();
    }
}

