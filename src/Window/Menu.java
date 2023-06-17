package Window;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JPanel {
    private JFrame frame;
    private JLabel lblNewLabel= new JLabel("Don't touch the frog");
    private JLabel lblNewLabel_1= new JLabel("A game by Viet, Luong, An, Duc Anh");
    private JLabel lblDudeYourLever1= new JLabel("Choose your level: ");
    private JComboBox cbl= new JComboBox();
    private static Skin skin= new Skin();
    private final JLabel start = new JLabel("");
    private JLabel lblI= new JLabel("");
    private JPanel panel = new JPanel();
    private Timer timer= new Timer();
    private boolean isStarted= false;


    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    skin.changeSkin("Window");
                    Menu window= new Menu();
                    window.frame.setVisible(true);

                } catch (Exception e){
                    e.printStackTrace();

                }

            }
        });
    }
    public Menu(){
        initialaze();
    }
    private void initialaze(){
        frame= new JFrame("Dodge God");
        frame.setBounds(100, 100,450,300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10,11,414,53);
        frame.getContentPane().add(lblNewLabel);
        lblNewLabel.setForeground(Color.RED);


        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(10,64,414,14);
        frame.getContentPane().add(lblNewLabel_1);
        lblNewLabel_1.setForeground(Color.RED);
        
        lblDudeYourLever1.setHorizontalAlignment(SwingConstants.CENTER);
        lblDudeYourLever1.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
        lblDudeYourLever1.setBounds(10, 89, 414, 53);
        frame.getContentPane().add(lblDudeYourLever1);
        lblDudeYourLever1.setForeground(Color.RED);


        cbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cbl.setModel(new DefaultComboBoxModel(new String[]{"Easy [2 frogs/second]","Medium [10 frogs/second]", "Hard [20 frogs/second]"}));
        cbl.setBounds(63, 153, 315, 53);
        frame.getContentPane().add(cbl);

        start.setIcon(new ImageIcon("images/trigg.gif"));
        start.setHorizontalAlignment(SwingConstants.CENTER);
        start.setBounds(149, 215, 149, 39);
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cbl.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isStarted= true;
                        int x= start.getX();
                        int time= 100;
                        int maxTime= 17;
                        int ctr= 1;
                        while (start.getX()< frame.getWidth()){
                            start.setLocation(x+= 5, start.getY());
                            timer.delay(time);
                            if(time>= maxTime && ctr++ % 5 ==0) time -=time/2;
                        }
                        GameWindow gw= new GameWindow();
                        switch (cbl.getSelectedIndex()){
                            case 0:gw.startEasy(); break;
                            case 1:gw.startMedium(); break;
                            case 2:gw.startHard(); break;
                        }

                    }
                }).start();
            }
        });
        frame.getContentPane().add(start);

        lblI.setIcon(new ImageIcon("images/1.gif"));
        lblI.setBounds(10, 11, 424 ,249);
        frame.getContentPane().add(lblI);

        panel.setBorder(new BevelBorder(BevelBorder.RAISED,null,null,null,null));
        panel.setBounds(10,11,424,249);
        frame.getContentPane().add(panel);


    }

}
