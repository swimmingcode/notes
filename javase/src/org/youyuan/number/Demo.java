package org.youyuan.number;




import java.text.NumberFormat;

/**
 * @Describe
 * @Author youjiancheng
 * @Date 2021/1/25 16:05
 */
public class Demo {
    public static void main(String[] args) {
        NumberFormat nf = null;
        nf = NumberFormat.getInstance();
        //数字中间加了逗号，方便阅读
        System.out.println(nf.format(1000000));
        System.out.println(nf.format(10023.0122));
        /***************************************************/

    }
}
