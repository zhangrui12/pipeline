package com.bolingcavalry.hellojib.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;

/**
 * @author liaohua1
 * @date 2020/4/30 15:33
 */
public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public void write(String fileName) throws IOException {
        StringBuilder role_permission_sql = new StringBuilder();
        role_permission_sql.append("INSERT INTO `info_r_role_permission` (`role_id`, `permission_id`) VALUES ");
        role_permission_sql.append("('1', '").append(1).append("');");
        role_permission_sql.append("\r\n");
        System.out.println(this.getClass().getClassLoader().getResource("file/download"));
        String filePath = this.getClass().getClassLoader().getResource("file/download").getFile() + "/" + fileName;
        logger.info(">>>>>>>> start to write content to file 111111 >>>>>>>>");
        writeFile(filePath, role_permission_sql.toString());
    }

    public void writeFile(String path, String sql) {
        createFile(path);
        BufferedWriter out = null;
        try {
            File writefile = new File(path);
            boolean a = writefile.createNewFile();
            out = new BufferedWriter(new FileWriter(writefile));
            logger.info(">>>>>>>> start to writeFile 222222 >>>>>>>>");
            out.write(sql);
            out.flush();
        } catch (Exception e) {
            logger.error(">>>>> write file error :{} >>>>>", e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(">>>>> out close fail >>>>>");
                }
            }
        }
    }

    public String readFileByLines(String file) {
        logger.info(">>>>>>>>>>>> 111111111111111 >>>>>>>>>>>>>>>");
        if (StringUtils.isEmpty(file)) {
            return null;
        }
        File f = new File(file);
        if (!f.exists()) {
            return null;
        }
        BufferedReader reader = null;
        StringBuffer ret = new StringBuffer();
        logger.info(">>>>>>>>>>>> 2222222222 >>>>>>>>>>>>>>>");
        try {
            reader = new BufferedReader(new FileReader(f));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                ret.append(tempString).append("\r\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return ret.toString();
    }

    public void createFile(String path){
        File f = new File(path);
        if (f.exists()) {
            return;
        }
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
