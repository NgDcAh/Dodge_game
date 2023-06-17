package Window;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Robot;

public class GameWindow {

	private JFrame frame;
	private Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
	private Security s= new Security();
	private Timer t= new Timer();
	private JLabel score = new JLabel("Score: ");
	private int myScore= 0;
	private JLabel diff = new JLabel("Difficulty: ");
	private JLabel gameover = new JLabel("Game Over!");
	private int selection = 0;	
	private JButton btnMenu = new JButton("Menu");
	private JButton btnPlay = new JButton("Play again");


	
	public GameWindow(){
        if(s.getFile().exists()) s.getFile().delete();
        initialize();
        mouseRobot();
        frame.setVisible(true);

    }
	public void startEasy(){
        initObject(200, 5, 100, 17);
        diff.setText("Difficulty: Easy");
        selection=1;
    }
    public void startMedium(){
        initObject(100,10,100,17);
        diff.setText("Difficulty: Medium");
        selection=2;
    }
    public void startHard(){
        initObject(50,15,100,17);
        diff.setText("Difficulty: Hard");
        selection=3;
    }

    private void mouseRobot(){
        new Thread(new Runnable() {
            public void run() {
                while (!s.getFile().exists()){
                    try{
                        Robot r =new Robot();
                        while(!s.getFile().exists()) {
                            int mouseX = MouseInfo.getPointerInfo().getLocation().x;
                            int mouseY = MouseInfo.getPointerInfo().getLocation().y;
                            r.mouseMove(MouseInfo.getPointerInfo().getLocation().x + 1, MouseInfo.getPointerInfo().getLocation().y + 1);
                            r.mouseMove(MouseInfo.getPointerInfo().getLocation().x - 1, MouseInfo.getPointerInfo().getLocation().y - 1);
                            t.delay(50);
                        }
                    }catch (AWTException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    private void initialize() {
		frame = new JFrame();
		frame.setBounds(100,100,(int)dim.getWidth()- 200,(int)dim.getHeight()- 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().setLayout(null);
        
        
        score.setForeground(Color.YELLOW);
        score.setFont(new Font("Tahoma", Font.PLAIN, 30));
        score.setHorizontalAlignment(SwingConstants.CENTER);        
        score.setBounds(0, 280,(int)dim.getWidth()- 200, 37);
        score.setEnabled(false);        
        frame.getContentPane().add(score);


        diff.setHorizontalAlignment(SwingConstants.CENTER);
        diff.setForeground(Color.GREEN);
        diff.setFont(new Font("Tahoma", Font.PLAIN, 30));
        diff.setBounds(0, 232, (int)dim.getWidth()- 200, 37);
        diff.setEnabled(false);
        frame.getContentPane().add(diff);
        
        gameover.setHorizontalAlignment(SwingConstants.CENTER);
        gameover.setForeground(Color.RED);
        gameover.setFont(new Font("Tahoma", Font.PLAIN, 50));
        gameover.setBounds(0, 102, (int)dim.getWidth()- 200, 61);
        gameover.hide();
        frame.getContentPane().add(gameover);

        
        btnPlay.setBounds((int)(Toolkit.getDefaultToolkit().getScreenSize().width / 2)-150, 384, 99, 23);
        
        frame.getContentPane().add(btnPlay);
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(selection == 1) new GameWindow().startEasy();
                if(selection == 2) new GameWindow().startMedium();
                if(selection == 3) new GameWindow().startHard();
                frame.dispose();

            }
        });
        
        btnMenu.setBounds((int)(Toolkit.getDefaultToolkit().getScreenSize().width / 2)-150, 431, 99, 23);

        frame.getContentPane().add(btnMenu);
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Menu().main(new String[]{});

            }
        });
        //Ket thuc game khi chuot ra khoi man hinh
        frame.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent e) {
                s.gameOver();
            }
        });

        btnMenu.hide();
        btnPlay.hide();


	}
	
    private void initObject(int delay, int speed, int time,int maxTime){
        new Thread(new Runnable() {
            public void run() {
                while(!s.getFile().exists()){
                    frame.getContentPane().add(new Object(frame, speed, time,maxTime).getObject());
                    t.delay(delay);
                    score.setText("Score: "+ myScore++);
                }
                t.delay(600);
                diff.setEnabled(true);
                score.setEnabled(true);
                gameover.show();
                btnMenu.show();
                btnPlay.show();


            }
        }).start();
    }
}
