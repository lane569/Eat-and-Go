package seb.niu.project.eatandgo;
/*----------����app----------*/


import android.app.Activity;
import android.app.Application;
import java.util.LinkedList;
 
 
 
//�ΨӺ޲z�Ҧ�Activity��������O
 
public class ActivityManager extends Application{
 
 
 
    private static ActivityManager instance;
 
    //�Ψ��x�sActivity��LinkedList
 
    private LinkedList<Activity> activityList = new LinkedList<Activity>();
 
    //�����O�L�k�ϥδ��q����k��Ҥ�
 
    private ActivityManager(){
 
        super();
 
    }
 
    //�ΨӨ��o�ߤ@��Ҫ��󪺤�k
 
    public static ActivityManager getInstance(){
 
        if (instance == null){
 
            instance = new ActivityManager();
 
        }
 
        return instance;
 
    }
 
    //�Ψӥ[�J�n�޲z��Activity����k
 
    public void addActivity(Activity activity){
 
        activityList.add(activity);
 
    }
 
    //�Ψ������Ҧ��Q�޲z��Activity����k
 
    public void closeAllActivity(){
 
        for (Activity activity : activityList){
 
            //fininsh()�NActivity���V��x�A���X�FActivity���|�A�귽�èS���Q
 
            //����A���|Ĳ�oonDestory()�A����Back��]�|�^���Activity
 
            activity.finish();
 
        }
 
        System.exit(0);
 
    }
 
}
