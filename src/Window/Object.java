package Window;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Object {

    public Object(JFrame windowFrame, int speed, int time, int maxTime){
        this.frame = windowFrame;
        this.frameW= frame.getWidth();
        this.frameH= frame.getHeight();
        this.speed= speed;
        this.time= time;
        this.maxTime= maxTime;
    }
    private JFrame frame;
    private  final int width= 50, height= 47, eWidth= 184, eHeight= 184;
    private int speed, time, maxTime;
    private int frameW ,frameH;
    private  JLabel object;
    private ImageIcon i = new ImageIcon("images/ech.gif");


    public Object(JFrame frame) {

    }
    private Timer timer= new Timer();
    private Security s= new Security();

    private int Begin(){
        return new Random().nextInt(frameH- height);
    }
    private void setupObject(){
        object = new JLabel();
        object.setBounds(0, Begin(), width, height);
        object.setIcon(i);
    }
    private void moveObject(){
        new Thread(new Runnable() {

            public void run() {
                int x= object.getX();
                int y= object.getY();
                int ctr= 1;
                while(x< frame.getWidth()- width && !s.getFile().exists()) {
                    timer.delay(time);
                    object.setLocation(x += speed, object.getY());
                    if(time>= maxTime && ctr++ %5 ==0) time -= time/2;
                }
                i= new ImageIcon("images/c.gif");
                object.setIcon(i);
                object.setBounds(x-(eWidth- width)/4, y-(eHeight- height)/4, eWidth, eHeight);
                timer.delay(600);
                object.hide();
                object= null;
            }
        }).start();
    }
    //Chạm vào vật thể
    private  void detectMouse(){
        object.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                s.gameOver();
            }
        });
    }
    public JLabel getObject(){
        setupObject();
        moveObject();
        detectMouse();
        return this.object;
    }
}
