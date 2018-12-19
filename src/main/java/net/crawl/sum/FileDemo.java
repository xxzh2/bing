// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileDemo.java

package net.crawl.sum;

import java.net.MalformedURLException;
import java.net.URL;

// Referenced classes of package net.suman:
//            Localization

public class FileDemo {

    public FileDemo() {
    }

    public static void main(String args[]) {
        try {
            URL url = new URL("http://s.cn.bing.net/az/hprichbg/rb/JianPavilionUSTC_ZH-CN14581650358_1366x768.jpg");
            String fl = "C:\\Users\\Larn\\Desktop\\JianPavilionUSTC_ZH-CN14581650358_1366x768.jpg";
            Localization fc = new Localization(fl, url);
            fc.localize();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
