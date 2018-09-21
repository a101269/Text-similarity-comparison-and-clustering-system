/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wenbenbijiao;

/**
 *
 * @author a101269
 */
public class Cosine {
    float degree;
    public void cosine(int cloumn,int[] vector1,int[] vector2 )
    {
	float vector1Modulo=0;
	float vector2Modulo=0;
	float v1andv2=0;
	for(int i=0;i<cloumn;i++)
	{
            vector1Modulo += vector1[i]*vector1[i];  
            vector2Modulo += vector2[i]*vector2[i];   
            v1andv2+=vector1[i]*vector2[i];
	}
        vector1Modulo = (float) Math.sqrt(vector1Modulo);  
	vector2Modulo = (float) Math.sqrt(vector2Modulo);  
        degree = v1andv2/((vector1Modulo*vector2Modulo+1));
    }
}