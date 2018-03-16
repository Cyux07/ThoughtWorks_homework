package ThoughtWorks;

import ThoughtWorks.tools.Checking;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

/**启动位置, 接收任意长度参数*/
public class Main {
    /**
     * @param fileNames 文件名参数，可以是绝对路径或根目录相对路径*/
    public static void main(String[] fileNames) {

        File raw = null;
        if (fileNames.length <= 0) {
            try {
                raw = new File(Main.class.getResource("raw.txt").toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            test(raw);
        } else {
            int i = 0;
            while (i < fileNames.length) {
                try {
                    raw = new File(Main.class.getResource(fileNames[i]).toURI());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    raw = new File(fileNames[i]);
                }
                test(raw);
                i++;
            }
        }
    }

    /**调用相关类对文本数据进行测试*/
    private static void test(File raw) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(raw);
            WatchCenter watchCenter = new WatchCenter();
            watchCenter.readRecord(scanner);

            System.out.println(watchCenter.getInfo(2));
            System.out.println(watchCenter.getInfo(4));
            System.out.println(watchCenter.getInfo(100));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (scanner != null)
                scanner.close();
        }
    }
}
