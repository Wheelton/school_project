import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;
import java.util.*;



public class myGui extends JFrame  {
    public static JPanel top;
    public static JPanel right;
    public static JPanel bottom;
    public static JPanel left;
    public static JPanel center;



    myGui() { //główny GUI
         //Deklaracja paneli:
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("myGui");
        setSize(500,500);
        setVisible(true);

        //ustawiamy kolory dla paneli:
        top = new JPanel();
        top.setBackground(Color.YELLOW);


        right = new JPanel();
        right.setBackground(Color.RED);


        bottom = new JPanel();
        bottom.setBackground(Color.GREEN);


        left = new JPanel();
        left.setBackground(Color.BLUE);

        center = new JPanel();
        center.setBackground(Color.BLACK);

        //wyświetlamy left:
        getContentPane().add(BorderLayout.NORTH, top);
        getContentPane().add(BorderLayout.WEST, left);
        getContentPane().add(BorderLayout.SOUTH, bottom);
        getContentPane().add(BorderLayout.EAST, right);
        getContentPane().add(BorderLayout.CENTER, center);




        System.out.println("Witaj użytkowniku!!\nTo jest gra RepeatAfterComputer:\n" +
                "Głownym celem jest ustawić kolory tak jak ich ustawił komputer.\nAby grać, musisz przycisnąć strzałke" +
                ",\nwtedy kolor w centrum się zamieni z kolorem kierunku. \nPowodzenia!");

        Random rand = new Random();

        //Kroki komputera
      for (int i = 0; i<10; i++) //i<* -> ilość kroków(zmian)
        {
            int a = rand.nextInt(4);
            switch (a){
                case 0: Color c = center.getBackground();
                    center.setBackground(top.getBackground());
                    top.setBackground(c);
                    break;
                case 1:  c = center.getBackground();
                    center.setBackground(right.getBackground());
                    right.setBackground(c);
                    break;
                case 2: c = center.getBackground();
                    center.setBackground(bottom.getBackground());
                    bottom.setBackground(c);
                    break;
                case 3: c = center.getBackground();
                    center.setBackground(left.getBackground());
                    left.setBackground(c);
                    break;
            }

        }
      //Dodawanie kolorów komputera do tablicy

        Color[] arrKomp = new Color[5];
        Color[] arrGracz = new Color[5];
        for (int i = 0; i<=4; i++)
        {
            switch (i){
                case 0: arrKomp[i]=left.getBackground();
             //   System.out.println("Kolor left to "+ arrKomp[i]); //CHEAT!
                break;
                case 1: arrKomp[i]=right.getBackground();
              //  System.out.println("Kolor right to "+ arrKomp[i]); //CHEAT!
                break;
                case 2: arrKomp[i]=center.getBackground();
              //      System.out.println("Kolor center to "+ arrKomp[i]); //CHEAT!
                break;
                case 3: arrKomp[i]=bottom.getBackground();
              //      System.out.println("Kolor bottom to "+ arrKomp[i]); //CHEAT!
                break;
                case 4: arrKomp[i]=top.getBackground();
              //      System.out.println("Kolor top to "+ arrKomp[i]); //CHEAT!
                break;
            }

        }

        System.out.println("Masz 10 sekund by zapamiętać kolory:");
        //Timer
        int delay = 10000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //Ustawienie na wartości domyślne
                top.setBackground(Color.YELLOW);
                right.setBackground(Color.RED);
                bottom.setBackground(Color.GREEN);
                left.setBackground(Color.BLUE);
                center.setBackground(Color.BLACK);

                System.out.println("Zacznij!\n");

                //Wczytywanie strzałek do zmiany kolorów:
                addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {

                        super.keyPressed(e);

                        if (e.getKeyCode() == KeyEvent.VK_UP){
                            Color c = center.getBackground();
                            center.setBackground(top.getBackground());
                            top.setBackground(c);
                            addToArray(arrGracz);
                            System.out.print(checkArray(arrKomp,arrGracz));
                        }
                        if (e.getKeyCode() == KeyEvent.VK_LEFT){
                            Color c = center.getBackground();
                            center.setBackground(left.getBackground());
                            left.setBackground(c);
                            addToArray(arrGracz);
                            System.out.print(checkArray(arrKomp,arrGracz));
                        }
                        if (e.getKeyCode() == KeyEvent.VK_DOWN){
                            Color c = center.getBackground();
                            center.setBackground(bottom.getBackground());
                            bottom.setBackground(c);
                            addToArray(arrGracz);
                            System.out.print(checkArray(arrKomp,arrGracz));
                        }
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                            Color c = center.getBackground();
                            center.setBackground(right.getBackground());
                            right.setBackground(c);
                            addToArray(arrGracz);
                            System.out.print(checkArray(arrKomp,arrGracz));

                        }

                    }

                });
            }
        };
        Timer timer = new Timer(delay,taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }

    static void addToArray(Color[] arrGracz){
        for (int i = 0; i <= 4; i++)
        {
            switch (i)
            {
                case 0:
                    arrGracz[i] = left.getBackground();
                    break;
                case 1:
                    arrGracz[i] = right.getBackground();
                    break;
                case 2:
                    arrGracz[i] = center.getBackground();
                    break;
                case 3:
                    arrGracz[i] = bottom.getBackground();
                    break;
                case 4:
                    arrGracz[i] = top.getBackground();
                    break;

            }
        }
    }

     String  checkArray(Color[] arrKomp, Color[] arrGracz){//sprawdza całą tablicę kolorów
    String result="";
        int a=0;
        for (int i = 0; i < arrGracz.length; i++) {
            if (arrGracz[i]==arrKomp[i]) {
                a++;
            }
        }
        if (a==arrGracz.length)
        {
            result="Wygrałeś, program się zamknie po 5 sekundach";
            setEnabled(false);
            int delay=5000;
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                 setVisible(false);
                 System.exit(0);
                }
            };
            Timer timer = new Timer(delay,taskPerformer);
            timer.setRepeats(false);
            timer.start();
        }


        return result;
    }

    public static void main(String[] args) {
        myGui mG =  new myGui();

    }

}
