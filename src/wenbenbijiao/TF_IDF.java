/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wenbenbijiao;
import static wenbenbijiao.juzhen.idnum;
import static wenbenbijiao.Wenbenbijiao.filenum;
import static wenbenbijiao.juzhen.everyfilelength;
import static wenbenbijiao.wordarray.subwordlength;
/**
 *
 * @author a101269
 */
public class TF_IDF {
    static double[][] tfidf=new double[9][1000];
    public void  tf_idf(int fi){
        int i=0,j=0,n=0;
        float tf[]=new float[1000];
        double idf[]=new double[1000];
        for(i=0;i<subwordlength;i++)
        {
            if(idnum[fi][i]!=0)
            {
                tf[i]=idnum[fi][i]/everyfilelength[fi][0];
                for(j=0;j<filenum;j++)
                {
                    if(idnum[j][i]!=0)
                        n++;
                }
                idf[i]=Math.log((filenum/(n+1)));
            }
            tfidf[fi][i]=tf[i]*idf[i];
        }
    }   
}
