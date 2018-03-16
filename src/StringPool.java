/**
 * Created by Administrator on 2018/3/15.
 */
public class StringPool {
    public static void main(String[] args) {
        String sl=new StringBuilder("go").append("od").toString();
        System.out.println(sl.intern()==sl);
        String s2=new StringBuilder("ja").append("va").toString();
        System.out.println(s2.intern()==s2);


    }
}
