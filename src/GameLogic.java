import javax.swing.*;
import java.awt.*;

public class GameLogic {
// создает массив чисел из массива закрашенных клеток
    public static int[][] maSS(JButton[][] buttons) {
        int[][] mass = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (buttons[i][j].getBackground().equals(Color.cyan)) {
                    mass[i][j] = 1;
                }
            }
        }
        return mass;
    }
//выдает в лейбле результат  нажатия на кнопку
    public String check(int[][] array, int x, int y) {
        if (x == 0) {
            if ((y == 0) && (array[x + 1][y] == 0 && array[x][y + 1] == 0)) {
                return "Утопил!!! Делайте следующий ход";
            } else if ((y > 0 && y < 9) && (array[x][y - 1] == 0 && array[x + 1][y] == 0 && array[x][y + 1] == 0)) {
                return "Утопил!!! Делайте следующий ход";
            } else if ((y == 9) && (array[x][y - 1] == 0 && array[x + 1][y] == 0)) {
                return "Утопил!!! Делайте следующий ход";
            } else {
                return "Попал! Делайте следующий ход";
            }
        }
        if ((x > 0) && (x < 9)) {
            if ((y == 0) && (array[x + 1][y] == 0 && array[x - 1][y] == 0 && array[x][y + 1] == 0)) {
                return "Утопил!!! Делайте следующий ход";
            } else if ((y > 0 && y < 9) && (array[x][y - 1] == 0 && array[x - 1][y] == 0 && array[x + 1][y] == 0 && array[x][y + 1] == 0)) {
                return "Утопил!!! Делайте следующий ход";
            } else if ((y == 9) && (array[x][y - 1] == 0 && array[x - 1][y] == 0) && (array[x + 1][y] == 0)) {
                return "Утопил!!! Делайте следующий ход";
            } else {
                return "Попал! Делайте следующий ход";
            }
        }
        if (x == 9) {
            if ((y == 0) && (array[x - 1][y] == 0 && array[x][y + 1] == 0)) {
                return "Утопил!!! Делайте следующий ход";
            } else if ((y > 0 && y < 9) && (array[x][y - 1] == 0 && array[x - 1][y] == 0 && array[x][y + 1] == 0)) {
                return "Утопил!!! Делайте следующий ход";
            } else if ((y == 9) && (array[x][y - 1] == 0 && array[x - 1][y] == 0)) {
                return "Утопил!!! Делайте следующий ход";
            } else {
                return "Попал! Делайте следующий ход";
            }
        }
        return "мимо";
    }
//блокирует кнопки
    public void blockButton(JButton[][] buttons) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
//разблокирует массив кнопок
    public void unBlockButton(JButton[][] buttons) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setEnabled(true);
            }
        }
    }
//закрашивает выбранные клетки цветом,
// если выбрано не правильно при повторном нажатии возвращает прежний цвет
    public void filling(JButton[][] buttons, int i, int j) {
        int sum = 0;
        if (!buttons[i][j].getBackground().equals(Color.cyan)) {
            buttons[i][j].setBackground(Color.cyan);
        }
        else {
            buttons[i][j].setBackground(Color.WHITE);
        }
        int[][] arr = maSS(buttons);
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                if (arr[i][j] == 1) {
                    sum = sum + arr[i][j];
                    if (sum == 20) {
                        blockButton(buttons);
                    }
                }
            }
        }
    }
}



