package Window;

import javax.swing.*;

public class Skin {
    public void changeSkin(String skin){
        try{
            for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
                if(info.getName().equals(skin)) UIManager.setLookAndFeel(info.getClassName());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
