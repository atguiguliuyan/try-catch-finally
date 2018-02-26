/**
 * Created by Administrator on 2018/2/26.
 */
public class TryCatchFinally {
    /**
     * 通过字节码，我们发现，在try语句的return块中，return 返回的引用变量（t 是引用类型）并不是try语句外定义的引用变量t
     * ，而是系统重新定义了一个局部引用t’，这个引用指向了引用t对应的值，也就是try ，即使在finally语句中把引用t指向了值finally，
     * 因为return的返回引用已经不是t ，所以引用t的对应的值和try语句中的返回值无关了。
     * @return
     */
    public static String test1(){
        String t="";
        try{
            t="try";
            return t;
        }catch (Exception e){
            t="catch";
            return t;
        }finally{
            t="finally";
        }
    }

    /**
     * 我们发现try语句中的return语句给忽略。可能jvm认为一个方法里面有两个return语句并没有太大的意义，
     * 所以try中的return语句给忽略了，直接起作用的是finally中的return语句，所以这次返回的是finally。
     * @return
     */
    public static String test2(){
        String t="";
        try{
            t="try";
            return t;
        }catch (Exception e){
            t="catch";
            return t;
        }finally{
            t="finally";
            return t;
        }
    }

    /**
     * 这里面try语句里面会抛出 java.lang.NumberFormatException，所以程序会先执行catch语句中的逻辑，t赋值为catch，在执行return之前，会把返回值保存到一个临时变量里面t '，
     * 执行finally的逻辑，t赋值为finally，但是返回值和t'，所以变量t的值和返回值已经没有关系了，返回的是catch
     * @return
     */
    public static String test3(){
        String t="";
        try{
            t="try";
            Integer.parseInt(null);
            return t;
        }catch (Exception e){
            t="catch";
            return t;
        }finally{
            t="finally";

        }
    }

    /**
     * 由于try语句里面抛出异常，程序转入catch语句块，catch语句在执行return语句之前执行finally，而finally语句有return,则直接执行finally的语句值，返回finally
     * @return
     */
    public static String test4(){
        String t="";
        try{
            t="try";
            Integer.parseInt(null);
            return t;
        }catch (Exception e){
            t="catch";
            return t;
        }finally{
            t="finally";
            return t;
        }
    }

    /**
     * 这个例子在catch语句块添加了Integer.parser(null)语句，强制抛出了一个异常。然后finally语句块里面没有return语句。继续分析一下，
     * 由于try语句抛出异常，程序进入catch语句块，catch语句块又抛出一个异常，
     * 说明catch语句要退出，则执行finally语句块，对t进行赋值。然后catch语句块里面抛出异常。结果是抛出java.lang.NumberFormatException异常
     * @return
     */
    public static String test5(){
        String t="";
        try{
            t="try";
            Integer.parseInt(null);
            return t;
        }catch (Exception e){
            t="catch";
            Integer.parseInt(null);
            return t;
        }finally{
            t="finally";

        }
    }

    /**
     * 这个例子和上面例子中唯一不同的是，这个例子里面finally 语句里面有return语句块。
     * try catch中运行的逻辑和上面例子一样，当catch语句块里面抛出异常之后，进入finally语句快，然后返回t。
     * 则程序忽略catch语句块里面抛出的异常信息，直接返回t对应的值 也就是finally。方法不会抛出异常
     * @return
     */
    public static String test6(){
        String t="";
        try{
            t="try";
            Integer.parseInt(null);
            return t;
        }catch (Exception e){
            t="catch";
            Integer.parseInt(null);
            return t;
        }finally{
            t="finally";
            return t;
        }
    }
    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
        System.out.println(test5());
        System.out.println(test6());
    }
}
