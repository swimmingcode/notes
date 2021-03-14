package org.youyuan.clone;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/10 15:19
 */
public class Husband implements Cloneable {
    private int id;
    private Wife wife;

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Husband(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {//myeclipse自动生成的
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {//myeclipse自动生成的
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Husband other = (Husband) obj;
        if (id != other.id)
            return false;
        return true;
    }

    /**
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        Wife wife = new Wife(1,"jin");
        Husband husband = new Husband(1);
        Husband husband2 = null;
        husband.setWife(wife);
        husband2 = (Husband) husband.clone();
        System.out.println("husband class same="+(husband.getClass()==husband2.getClass()));//true
        System.out.println("husband object same="+(husband==husband2));//false
        System.out.println("husband object equals="+(husband.equals(husband)));//true
        System.out.println("wife class same="+(husband.getWife().getClass()==husband2.getWife().getClass()));//true
        System.out.println("wife object same="+(husband.getWife()==husband2.getWife()));//true
        System.out.println("wife object equals="+(husband.getWife().equals(husband.getWife())));//true
    }
}
