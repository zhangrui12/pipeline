package com.bolingcavalry.hellojib;

import java.io.File;

/**
 * @author liaohua1
 * @date 2020/5/6 16:44
 */
public class Test {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String str = "xmx=20";
        String str1 = str.replace("xmx=", "");
        System.out.println(str1);
    }

    public static void writeFile() {

    }

    public static void writeFile1(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
