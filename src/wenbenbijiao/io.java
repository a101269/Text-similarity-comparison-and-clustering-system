/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wenbenbijiao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static wenbenbijiao.Wenbenbijiao.path;
import static wenbenbijiao.read_write.name;
import static wenbenbijiao.read_write.txt;
import static wenbenbijiao.read_write.txt;

/**
 *
 * @author a101269
 */
public class io {
    FileReader rfile;
    FileWriter wfile;
    BufferedReader bufreader;
    BufferedWriter bufwriter;
    
    public void read()
    {
        String s;
        String s2 = name.getText();
        try
        {
            File f = new File(path,s2);
            rfile=new FileReader(f);
            bufreader=new BufferedReader(rfile);
        }
        catch(IOException er){System.out.println(er);}
        try
        {
            while((s=bufreader.readLine())!=null)
            {txt.append(s+'\n');}
        }
        catch(IOException er){System.out.println(er);}
    }   
    
    public void write()
    {
        String s;
        String s2 = read_write.name.getText();
        try
        {
            File f2 = new File(path,s2);
            wfile=new FileWriter(f2);
            bufwriter=new BufferedWriter(wfile);
            String str =read_write.txt.getText();
            bufwriter.write(str,0,str.length());
            bufwriter.flush();
        }
        catch(IOException er){System.out.println(er);}
    }
    
}
