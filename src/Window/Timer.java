package Window;

public class Timer {
    public void delay(int ms){
        try{
            Thread.sleep(ms);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
