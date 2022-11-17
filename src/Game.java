import javax.swing.*;
import java.awt.*;

public class Game {
    GameLogic gL = new GameLogic();

    public int[][] intArr1;
    public int[][] intArr2;

    GameGraf gg1;
    GameGraf gg2;

    Game() {
        gg1 = new GameGraf(20, 50, this, gL, 1);
        gg1.frame.setTitle("Морской бой. Первый игрок");

        gg2 = new GameGraf(350, 50, this, gL, 2);
        gg2.frame.setTitle("Морской бой. Второй игрок");

        gL.blockButton(gg1.clickButtons);
        gL.blockButton(gg2.clickButtons);

        intArr1 = GameLogic.maSS(gg1.setButtons);
        intArr2 = GameLogic.maSS(gg1.setButtons);
    }

    public void startOfGame(int counter, int id) {
        int a = (int) (Math.random() * 2 + 4) - 3;
        if (counter == 1) {
            if (id == 1) {
                gg1.label.setText("Подождем");
            } else {
                gg2.label.setText("Подождем");
            }
        } else {
            if (a == 1) {
                gg1.label.setText("Вы начинаете. Делайте первый ход");
                gg2.label.setText("Первый ход выпал 1 игроку");
                gL.unBlockButton(gg1.clickButtons);
            } else {
                gg2.label.setText("Вы начинаете. Делайте первый ход");
                gg1.label.setText("Первый ход выпал 2 игроку");
                gL.unBlockButton(gg2.clickButtons);
            }
        }
    }

        // выбирает нужный массив в зависимости id доски
        int[][] changeArr ( int id){
            int[][] arr;
            if (id == 1) {
                arr = intArr2;
            } else {
                arr = intArr1;
            }
            return arr;
        }

        //созданный массив передаем доске
        public void getArr ( int[][] arr, int id){
            if (id == 1) {
                intArr1 = arr;
            } else {
                intArr2 = arr;
            }
            //return arr;
        }

        //определяем с какой доски нажата кнопка
        public JButton[][] checkSetButts ( int id){
            JButton[][] buttons;
            if (id == 1) {
                buttons = gg2.setButtons;
            } else {
                buttons = gg1.setButtons;
            }
            return buttons;
        }

        //меняем массивы с элементами местами-выбираем массив другой доски
        public JButton[][] checkBut1 ( int id){
            JButton[][] buttons;
            if (id == 1) {
                buttons = gg1.clickButtons;
            } else {
                buttons = gg2.clickButtons;
            }
            return buttons;
        }

        public void pr (JButton[][]buttons,int i, int j, int id, GameLogic gL){
            int[][] intArr1;
            intArr1 = changeArr(id);
            if (intArr1[i][j] == 0) {
                JButton[][] tempBut1;
                tempBut1 = checkBut1(id);
                tempBut1[i][j].setBackground(Color.pink);
                if (id == 1) {
                    gg1.label.setText("Мимо! Ход передается другому игроку");
                    gg2.label.setText("Ваш ход");
                    gL.blockButton(gg1.clickButtons);
                    gL.unBlockButton(gg2.clickButtons);
                } else {
                    gg2.label.setText("Мимо! Ход передается другому игроку");
                    gg1.label.setText("Ваш ход");
                    gL.blockButton(gg2.clickButtons);
                    gL.unBlockButton(gg1.clickButtons);
                }
            } else {
                JButton[][] tempButArr;
                tempButArr = checkSetButts(id);
                tempButArr[i][j].setBackground(Color.RED);
                buttons[i][j].setBackground(Color.RED);
                buttons = checkSetButts(id);

                buttons[i][j].setBackground(Color.RED);
                if (id == 1) {
                    gg1.label.setText(gL.check(intArr1, i, j));
                } else {
                    gg2.label.setText(gL.check(intArr1, i, j));
                }
                intArr1[i][j] = 0;
                int k = endOfTheGame(intArr1);
                if (k == 0) {
                    gL.blockButton(gg2.clickButtons);
                    gL.blockButton(gg1.clickButtons);
                    if (id == 1) {
                        gg1.label.setText("Поздравляю!!! Вы выиграли");
                        gg2.label.setText("Жаль,но на этот раз вы проиграли ");
                    } else {
                        gg2.label.setText("Поздравляю!!! Вы выиграли");
                        gg1.label.setText("Жаль,но на этот раз вы проиграли");
                    }
                }
            }
        }

        //проверка конца игры
        public int endOfTheGame ( int[][] arr){
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (arr[i][j] == 1) {
                        sum = sum + arr[i][j];
                    }
                }
            }
            return sum;
        }
    }

