/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file_string, choose Tools | Templates
 * and open the template in the editor.
 */
package wenbenbijiao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import static wenbenbijiao.wordarray.word;
import static wenbenbijiao.wordarray.subwordlength;
/**
 *
 * @author a101269
 */
public class juzhen {
    static int[][]everyfilelength=new int[9][1];
    static int[][]idnum=new int[9][1000];
    public void shengchengqi(String s1, String s2,int fi) throws Exception //fi为文本号
    { 
        Map<String, Integer> treeMap = new TreeMap<String, Integer>();// 构造一个treemap类，key是string类，value是integer类，用于存放单词数据
	StringTokenizer st ; 
	String file_string = ""; //用于保存整个文本的字符串        
        File f = new File(s1,s2);
        File f2 = new File("F:\\linuxtxt","b.txt");
        FileReader rfile=new FileReader(f);
        FileWriter wfile=new FileWriter(f2);
        BufferedReader bufreader=new BufferedReader(rfile);
        BufferedWriter bufwriter=new BufferedWriter(wfile);
        int[] num1=new int[1000];//保存各个文本的各单词数目，下角标：若单词与juzhen中Word保存的单词相同则列角标一致
        String inputword; 
        boolean bool=true;
        int j=0;
        inputword = bufreader.readLine();

	while(bool)
        { 
            file_string += inputword; 
            inputword = bufreader.readLine();
            if(inputword == null)
            {
                bool=false;
            }
	}//读出整篇文章，存入 file中。
	file_string= file_string.toLowerCase();//这里将所有的英文字母变成小写，如果不是这样，结果中大写字母开头的单词会排在最前面,取出文章中的单词，"," "." "!" " " 为各个单词的分界符。用StringTokenizer 方法将文档分词
	st = new StringTokenizer(file_string," ,.!?@#$%^&（）=_\"<>+-*/");//指定分隔符，
	while (st.hasMoreTokens()) //判断是否已到结尾
        {  
            String key = st.nextToken();//当st中还有词的时候，从 tokenizer中返回下一个token，把值赋予key
            everyfilelength[fi][0]+=1;
            int value;
            if (treeMap.get(key) != null) //单词不为空
            {
                value=treeMap.get(key); //返回指定键映射的值
                value++;
                treeMap.put(key, value); //单词，数目存入treeMap
            } //treeMap.get(key)方法的返回值是value（整型类，如果不是空值，那么value加一，然后将此时的key=单词，value=词频，写入treeMap中。
            else 
            { 
		treeMap.put(key, 1);
            }
            int i=0;
            while(i<subwordlength)
            {               
                if(word[i].equals(key))
                {
                    idnum[fi][i]=treeMap.get(key); 
                    break;
                }  
                else i++;
            }              
        }
        bufwriter.append(" ");
        bufwriter.flush();
        bufwriter.close();
        wfile.close();
    }  
}

