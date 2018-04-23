package org.tis.tools.asf.module.generate.tools;

import com.sun.javafx.PlatformUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/12
 **/
public class FileUtils {

    private static File directory = new File("");
    private static String prjDir = directory.getAbsolutePath();
    private static String tempDir = prjDir + "/src/main/resources/temp";

    private static String generatePath() {
       return formatPath(prjDir + "/src/main/resources/temp");
    }



    /**
     * 格式化文件路径
     * @param path
     * @return
     */
    public static String formatPath(String path) {
        if (PlatformUtil.isLinux()) {
            return path.replaceAll("/+|\\\\+","/");
        } else {
            return path.replaceAll("/+|\\\\+","\\\\");
        }
    }


    public static String package2Path(String packageName) {
        if (!packageName.contains(".")) {
            return packageName;
        } else {
            return packageName.replace(".", File.separator);
        }
    }

    /**
     * 创建文件
     *
     * @param fileName    文件名称
     * @param filecontent 文件内容
     * @return 是否创建成功，成功则返回true
     */
    public static void createFile(String fileName, String filecontent, String filePath) throws IOException {
        //文件路径+名称+文件类型
        String filenameTemp = generatePath() + File.separator + filePath + File.separator + fileName;
        File file = new File(filenameTemp);
        //如果文件不存在，则创建新的文件
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            System.out.println("success create file, the file is " + filenameTemp);
            //创建文件成功后，写入内容到文件里
            writeFileContent(filenameTemp, filecontent);
        } else {
            System.err.println("filed create existed file, the file is " + filenameTemp);
        }
    }

    /**
     * 向文件中写入内容
     *
     * @param filePath 文件路径与名称
     * @param newstr   写入的内容
     * @return
     * @throws IOException
     */
    public static Boolean writeFileContent(String filePath, String newstr) throws IOException {
        Boolean bool = false;
        String filein = newstr + "\r\n";//新写入的行，换行
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filePath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            //文件原有内容
//            for (int i = 0; (temp = br.readLine()) != null; i++) {
//                buffer.append(temp);
//                // 行与行之间的分隔符 相当于“\n”
//                buffer = buffer.append(System.getProperty("line.separator"));
//            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }

    /**
     * 删除文件
     *
     * @return
     */
//    public static boolean delFile(String fileName) {
//        Boolean bool = false;
//        filenameTemp = path + fileName + ".txt";
//        File file = new File(filenameTemp);
//        try {
//            if (file.exists()) {
//                file.delete();
//                bool = true;
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        return bool;
//    }

    public static void main(String[] args) {
//        String s = formatPath(tempDir);
//        boolean s1 = new File(s).exists();
//        System.out.println(s1);
//        System.out.println(formatPath(tempDir));
        String s2 = package2Path("12.222.222");
        System.out.println(s2);
//        createFile("Test.java", "111111111111", s);

    }




}