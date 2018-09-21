/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wenbenbijiao;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;
import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;
import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;
import static wenbenbijiao.juzhen.idnum;
import static wenbenbijiao.wordarray.subwordlength;
/**
 *
 * @author a101269
 */

public class K_means {
    public static Vector<double[]> data = new Vector<>();  //存储经过处理后的每一个样本的各个属性值和所属分类
    public static Vector<double[]> init_kernal = new Vector<>();//存储每次迭代产生的聚类核心的每个属性值的均值

    public static void pretreatment(int fnum) {   //数据预处理，将原始数据中的每一个属性值提取出来存放到Vector<double[]>  data中
        int i = 0;
        String t;
        while (i < fnum)
        {//取出indata中的每一行值
            double[] tem = new double[subwordlength+1];
            for (int j = 0; j < subwordlength; j++)
            {
                tem[j] = idnum[i][j];//将每一个的样本的各属性值转换后依次存入到double[]数组中
            }
            tem[fnum] = 0;//tem的第五个值表示所属类别，1.0表示第一类，2.0表示第二类，3.0表示第三类，初始化为0不属于任何类
            data.add(tem);//将每一个样本加入到data中
            i++;
        }
    }

    public static Vector<double[]> set_kernal(Vector<double[]> data, int a, int b, int c) {//设置初始的聚类核心，a，b，c分别表示一个类的核心在data中的编号
        init_kernal.add(data.get(a));
        init_kernal.add(data.get(b));
        init_kernal.add(data.get(c));
        return init_kernal;

    }

    public static int choose(double[] data, double[] a, double[] b, double[] c) {//判断一个样本属于哪一个类，返回值1表示第一类，2表示第二类，3表示第三类
        double ta, tb, tc;
        ta=0;
        tb=0;
        tc=0;
        //ta，tb，tc分别表示一个样本点到三个聚类核心的欧式距离的平方
        for(int i=0;i<subwordlength;i++)
        {
            ta+=(data[i] - a[i]) * (data[i] - a[i]);
        }
        for(int i=0;i<subwordlength;i++)
        {
            tb+=(data[i] - b[i]) * (data[i] - b[i]);
        }   
        for(int i=0;i<subwordlength;i++)
        {
            tc+=(data[i] - c[i]) * (data[i] - c[i]);
        }
        if (ta == Math.min(Math.min(ta, tb), tc))   //如果到第一类的距离最小返回1
            return 1;
        else if (tb == Math.min(Math.min(ta, tb), tc))//如果到第二类的距离最小返回2
            return 2;
        else if (tc == Math.min(Math.min(ta, tb), tc))//如果到第三类的距离最小返回3
            return 3;
        return 0;
    }


    public static Vector<double[]> onestep(Vector<double[]> data, Vector<double[]> kernal) {//函数执行一次表示kmeans的一次迭代
        Vector<double[]> newkernal = new Vector<>();//用于存放一次迭代后新的类的核心的各属性值
        int i = 0;
        double[] a = kernal.get(0); //a赋值为当前第一个类的核心
        double[] b = kernal.get(1); //b赋值为当前第二类的核心
        double[] c = kernal.get(2); //c赋值为当前第三类的核心
        double[] temp;

        while (i < data.size()) {//取出data中的每一个样本存放在temp中
            temp = data.get(i);
            temp[4] = (double) choose(temp, a, b, c);//调用choose函数判断当前样本属于哪一个类
            i++;
        }


        double[] suma = new double[9];
        int al = 0;//表示当前第一类的样本个数
        double[] sumb = new double[9];
        int bl = 0;//当前第二类的样本个数
        double[] sumc = new double[9];
        int cl = 0;//当前第三类的样本个数
        i = 0;
        while (i < data.size()) {
            double[] t = data.get(i);
            if (t[4] == 1) {     //如果当前样本属于第一类
                for (int j = 0; j < 9; j++) {
                    suma[j] = suma[j] + t[j];
                }
                al++;             //该类的样本个数加一
            } else if (t[4] == 2) {
                for (int j = 0; j < 9; j++) {
                    sumb[j] = sumb[j] + t[j];

                }
                bl++;
            } else if (t[4] == 3) {
                for (int j = 0; j < 9; j++) {
                    sumc[j] = sumc[j] + t[j];

                }
                cl++;
            }
            i++;//指向下一个样本继续循环
        }

        for (int j = 0; j < 9; j++) {//计算出本次迭代后的每个类的核心的坐标
            suma[j] = suma[j] / al;
            sumb[j] = sumb[j] / bl;
            sumc[j] = sumc[j] / cl;

        }
        //将新的类的核心添加入到newkernal中
        newkernal.add(suma);
        newkernal.add(sumb);
        newkernal.add(sumc);
        return newkernal;//返回本次迭代后的新的类的核心
    }


    public static void k_means() {//k_means算法的实现

        while (true) {
            boolean con = true;
            Vector<double[]> t = onestep(data, init_kernal);//将 data和当前的init_kernal传入onestep函数进行一次迭代，返回值为迭代后的类的核心

            //判断本次迭代后返回的类的核心是不是和迭代之前的类的核心相同，如果不相同con被设为false，继续迭代。
            for (int i = 0; i < t.size(); i++) {
                for (int j = 0; j < 9; j++) {
                    if (t.get(i)[j] != init_kernal.get(i)[j])
                        con = false;
                }
            }
            if (con)//如果con为true说明本次迭代的核心和迭代之前的核心相同，说明聚类完成，退出循环
                break;
            else
                init_kernal = t;//如果本次迭代返回的新的核心和迭代之前的不同，则当前核心设置为返回的新的核心，继续循环迭代
        }

    }


    public static void show_category() {//打印出所有的样本的属性和所属类别
        for (int i = 0; i < data.size(); i++)
        {
            System.out.print((i + 1) + "  ");
            System.out.print(data.get(i)[4] + "  ");
            
            System.out.println();
        }

    }
}





    

