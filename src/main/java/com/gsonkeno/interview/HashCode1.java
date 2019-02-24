package com.gsonkeno.interview;
/**
 * https://www.cnblogs.com/xrq730/p/4842028.html
 * @author gaosong
 * @since 2019-02-24
 */
public class HashCode1 {
    private String str0;
    private double dou0;
    private int       int0;

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof HashCode1)
        {
            HashCode1 hcc = (HashCode1)obj;
            if (hcc.str0.equals(this.str0) &&
                    hcc.dou0 == this.dou0 &&
                    hcc.int0 == this.int0)
            {
                return true;
            }
            return false;
        }
        return false;
    }

    public static void main(String[] args)
    {
        System.out.println(new HashCode1().hashCode());
        System.out.println(new HashCode1().hashCode());
        System.out.println(new HashCode1().hashCode());
        System.out.println(new HashCode1().hashCode());
        System.out.println(new HashCode1().hashCode());
        System.out.println(new HashCode1().hashCode());
    }
}
