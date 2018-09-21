/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wenbenbijiao;

import java.io.*;
import java.util.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import static wenbenbijiao.juzhen.idnum;
import static wenbenbijiao.TF_IDF.tfidf;
import static wenbenbijiao.juzhen.everyfilelength;
import static wenbenbijiao.wordarray.subwordlength;
import static wenbenbijiao.K_means.data;
/**
 *
 * @author a101269
 */
public class Wenbenbijiao {
    static String[] s = new String[9];//文件名
    static fenci fen=new fenci();
    static danciquanji quan=new danciquanji();
    static wordarray bing=new wordarray();
    static juzhen ju=new juzhen();  
    static TF_IDF ti=new TF_IDF(); 
    static Cosine sim=new Cosine(); 
    static  K_means k=new K_means();
    static int filenum;
    static String path="F:\\linuxtxt";
    /** 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        int i;

        Scanner sc = new Scanner(System.in);
        
        System.out.println("请输入进行统计的文本数目(最大9个，文件在/home/liu/linuxtxt下：");
        int filenum = sc.nextInt();
        for(i=0;i<filenum;i++)
        {   int j=i+1;
            System.out.println("请输入文件"+j+"的文件名：");
            s[i] = sc.next();    
        }
        
        for(i=0;i<filenum;i++)   
        {
            int j=i+1;
            System.out.println("文本"+j+"词频统计结果：");
            fen.fenciqi(path,s[i]);  
        }
        
        quan.quanjiqi();
        bing.bingjiqi();
        
        for(i=0;i<filenum;i++)   
        { 
            int j=i+1;
            System.out.print("文本"+j+":");
            ju.shengchengqi(path,s[i],i);
            for(j=0;j<subwordlength;j++)
            {
            System.out.print(idnum[i][j]+ " ");
            }  
            System.out.println();
        } 

        
        System.out.println("文本相似度比较模块，请输入要比较的两个文件号");
        int f1,f2;
        int[] xulie1=new int[1000];
        int[] xulie2=new int[1000];
        f1 = sc.nextInt(); 
        f2 = sc.nextInt();
        for(i=0;i<subwordlength;i++)
        {
            xulie1[i]=idnum[f1-1][i];
            xulie2[i]=idnum[f2-1][i];
        }
        sim.cosine(subwordlength,xulie1,xulie2);
        System.out.println(sim.degree);
        
        k.pretreatment(filenum);//预处理
        k.set_kernal(data,1,3,5);//设置初始核心
        k.k_means();
        k.show_category();
        
        System.out.println("是否进行文件读写操作（1 进行，0 退出）");
        f1 = sc.nextInt(); 
        switch(f1)
        {
            case 0:System.exit(0);
            case 1: new read_write().setVisible(true);
        }  
    }
}

