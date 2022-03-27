package util.lang;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyLangCollector implements Collector<String, Map<String, List<String>>, Map<String, List<String>>> {
    // 需要定义的两个内容
    // 1.类名
    private static final String CLASS_NAME = "ResManager";
    // 1.2 兼容合同自定义
    private static final String CLASS_NAME2 = "ResManagerUtils";

    // 2.方法
    private static final String METHOD_NAME = "loadKDString";

    // 2.2 兼容合同自定义
    private static final String METHOD_NAME2 = "getLocaleString";

    // 其他内容
    private static final String KEY_IMPORT = "import";

    private static final String KEY_END = ";";

    private static final Pattern NUM_REG = Pattern.compile("([^=]+)=");

    /**
     * 获取正则表达式
     *
     * @param javaName
     * @return
     */
    private static Pattern getReg(String javaName) {
        String regStr = CLASS_NAME + "\\s*\\.\\s*" + METHOD_NAME + "\\s*\\(\\s*\\\"([^\"]+)\\\"\\s*,\\s*\\\"" + javaName
                + "_([^\"]+)\\\"";
        return Pattern.compile(regStr);
    }

    /**
     * 获取正则表达式 兼容其他 class
     *
     * @param javaName
     * @return
     */
    private static Pattern getRegDd(String javaName) {
        String regStr = CLASS_NAME + "\\s*\\.\\s*" + METHOD_NAME + "\\s*\\(\\s*\\\"([^\"]+)\\\"\\s*,\\s*\\\"([^\"]*"
                + "_[^\"]+)\\\"";
        return Pattern.compile(regStr);
    }

    /**
     * 获取正则表达式 兼容其他 class + 合同自定义
     *
     * @param javaName
     * @return
     */
    private static Pattern getRegDd2(String javaName) {
        String regStr = CLASS_NAME2 + "\\s*\\.\\s*" + METHOD_NAME2 + "\\s*\\(\\s*\\\"([^\"]+)\\\"\\s*,\\s*\\\"([^\"]*"
                + "_[^\"]+)\\\"";
        return Pattern.compile(regStr);
    }

    @Override
    public Supplier<Map<String, List<String>>> supplier() {
        return () -> Maps.newHashMap();
    }

    @Override
    public BinaryOperator<Map<String, List<String>>> combiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    @Override
    public Function<Map<String, List<String>>, Map<String, List<String>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Sets.newHashSet();
    }

    /**
     * 主要方法
     *
     * @return 消化
     */
    @Override
    public BiConsumer<Map<String, List<String>>, String> accumulator() {
        return (map, path) -> {
            if (StringUtils.isBlank(path)) {
                return;
            }
            // 获取Java 名称
            String javaName = getName(path);
            Pattern reg = getRegDd(javaName);
            Pattern reg2 = getRegDd2(javaName);
            // 读取文件内容
            StringBuilder tempSB = new StringBuilder();
            // 非并行处理
            List<String> list = (List<String>) readPath(path)
                    // 合并成 一行
                    .map(item -> {
                        if (tempSB.length() > 0) {
                            return addReturn(tempSB, item);
                        }
                        return normalReturn(tempSB, item);
                    }).filter(StringUtils::isNotBlank)
                    // 正则匹配
                    .flatMap(item -> {
                        List<String> lists = new LinkedList<>();
                        boolean first = true;
                        Matcher matcher = reg.matcher(item);
                        boolean isError = addList(matcher, lists, first);
                        if (isError) {
                            if (addList(reg2.matcher(item), lists, first)) {
                                // 打印错误
                                String format = String.format("cls= %s, error= %s", javaName, item);
                                System.out.println(format);
                            }
                        }
                        return lists.stream();
                    })
                    // 排序
                    .sorted(Comparator.comparing(item -> {
                        Matcher matcher = NUM_REG.matcher(item);
                        matcher.find();
                        String number = matcher.group(1);
                        return number;
                    }))
                    // 去重
                    .distinct().collect(Collectors.toList());
            if (list.isEmpty()) {
                return;
            }
            map.put(javaName, list);
        };
    }

    private boolean addList(Matcher matcher, List<String> lists, boolean first) {
        while (true) {
            boolean find = matcher.find();
            if (find) {
                String val = matcher.group(1);
                String clsNum = matcher.group(2);
                lists.add(clsNum + "=" + val);
                first = false;
            } else if (first) {
                return true;
            } else {
                break;
            }
        }
        return false;
    }

    /**
     * 可以直接返回就直接 返回并清空 tempSB
     * 不就null 继续拼接
     *
     * @param tempSB
     * @param item
     * @return
     */
    private String addReturn(StringBuilder tempSB, String item) {
        tempSB.append(item.trim());
        int classIndex = item.lastIndexOf(CLASS_NAME);
        int endIndex = item.lastIndexOf(KEY_END);
        if (endIndex > classIndex) {
            String res = tempSB.toString();
            tempSB.setLength(0);
            return res;
        }
        return null;
    }

    /**
     * 判断需要返回的内容, 不能直接返回就添加到 tempSB
     *
     * @param tempSB 累加内容
     * @param item   原生
     * @return
     */
    private String normalReturn(StringBuilder tempSB, String item) {
        // 去掉 import 开头的内容
        if (item.startsWith(KEY_IMPORT)) {
            return null;
        }
        //
        int classIndex = item.lastIndexOf(CLASS_NAME);
        if (classIndex == -1) {
            return null;
        }
        int endIndex = item.lastIndexOf(KEY_END);
        if (endIndex > classIndex) {
            return item.trim();
        }
        // 这里需要 合并
        tempSB.append(item.trim());
        return null;
    }

    /**
     * 读取文件
     *
     * @param path
     * @return
     */
    private Stream<String> readPath(String path) {
        try {
            return Files.lines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取 java 类名
     *
     * @param path
     * @return
     */
    private String getName(String path) {
        int end = path.lastIndexOf(".");
        int start = path.lastIndexOf("/");
        if (start == -1) {
            start = path.lastIndexOf("\\");
        }
        return path.substring(start + 1, end);
    }
}
