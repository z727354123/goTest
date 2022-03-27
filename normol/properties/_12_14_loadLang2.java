package util.lang;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能描述
 *
 * @since 2020-12-14
 */
public class _12_14_loadLang2 {
    static ArrayList<String> files = new ArrayList<String>();

    public static void main(String[] args) {
        // 目录
        String dir = "D:\\workspace\\main_hlcrs\\hlcrs\\erp\\hr\\hlcrs\\hr-hlcrs-formplugin";
        // 文件
        String fileStr = "D:\\workspace\\main_hlcrs\\hlcrs\\erp\\hr\\hlcrs\\hr-hlcrs-formplugin\\src\\main\\java\\resources\\hr-hlcrs-formplugin_zh_CN.properties";

        // 获取所有java 文件
        File file = new File(dir);
        listAll(file);


        // 开始解析
        final Map<String, List<String>> res = files.stream().collect(new MyLangCollector());

        // 获取所有
        Set<String> allSet = readPath(fileStr).filter(StringUtils::isNotBlank).collect(Collectors.toSet());


        for (Map.Entry<String, List<String>> entry : res.entrySet()) {
            List<String> list = entry.getValue();

            // System.out.println(list.size() + "-------------------华丽分割线----------------------" +entry.getKey());
            // list.forEach(System.out::println);
        }
        // 打印
        HashSet<String> haveRemove = new HashSet<>();
        for (Map.Entry<String, List<String>> entry : res.entrySet()) {
            List<String> list = entry.getValue();
            for (String item : list) {
                boolean remove = allSet.remove(item);
                if (!remove && !haveRemove.contains(item)) {
                    String str = String.format("移除失败 cls=%s, str = %s", entry.getKey(), item);
                    System.out.println(str);
                }
                haveRemove.add(item);
            }
        }

        for (String item : allSet) {
            String str = String.format("多余的 str = %s",  item);
            System.out.println(str);
        }

    }


    /**
     * 读取文件
     *
     * @param path
     * @return
     */
    private static Stream<String> readPath(String path) {
        try {
            return Files.lines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void listAll(File file) {
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                // 文件
                final String fileName = tempList[i].toString();
                if (fileName.endsWith(".java")) {
                    // java文件
                    // System.out.println(fileName);
                    files.add(fileName);
                }
            }
            if (tempList[i].isDirectory()) {
                // 文件夹
                listAll(tempList[i]);
            }
        }
    }

}
