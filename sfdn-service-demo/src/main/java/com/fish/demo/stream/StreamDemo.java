package com.fish.demo.stream;

import com.fish.demo.bean.Book;
import com.fish.demo.bean.Point;
import com.fish.demo.enums.Topic;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Year;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.fish.demo.enums.Topic.*;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.*;

/**
 * @author hh
 * @description
 * @date 2020/11/15  16:13
 */
public class StreamDemo {

    /**
     * @description 对Integer实例集合中的每一个元素应用某个变换生成一个Point，然后找出距离原点最远的点与原点之间的距离
     * @param
     * @return void
     * @date 2020/11/15 16:45
     * @author hh
     */
    @Test
    public void demo1(){
        List<Integer> intList = Arrays.asList(1,2,3,4,5);
        OptionalDouble maxDistance = intList.stream()
                .map(i -> new Point(i % 3, i/3))
                .mapToDouble(p -> p.distance(0, 0))
                .max();
        System.out.println(maxDistance.getAsDouble());
    }

    /**
     * @description 并行方式处理集合元素
     * @param
     * @return void
     * @date 2020/11/15 16:53
     * @author hh
     */
    @Test
    public void demo2(){
        List<Integer> intList = Arrays.asList(1,2,3,4,5);
        OptionalDouble maxDistance = intList.parallelStream()
                .map(i -> new Point(i % 3, i /3))
                .mapToDouble(p -> p.distance(0,0))
                .max();
    }

    /**
     * @description 按照与原点之间的距离排序
     * @param
     * @return void
     * @date 2020/11/15 17:13
     * @author hh
     */
    @Test
    public void demo3(){
        List<Integer> intList = Arrays.asList(1,2,3,4,5);
        intList.stream()
                .map(i -> new Point(i % 3, i /3))
//                .sorted(comparing(p -> p.distance(0, 0)))
                .sorted(Comparator.comparing(p -> p.distance(0, 0)))
                .forEach(p -> System.out.printf("(%f, %f)", p.getHorizontal(), p.getVertical()));
    }

    public static <T, U extends Comparable<U>>
                Comparator<T> comparing(Function<T, U> keyComparer){
        return Comparator.comparing(keyComparer::apply);
    }

   public void demo4(){
       List<Integer> intList = Arrays.asList(1,2,3,4,5);
       int sum = intList.stream().mapToInt(Integer::intValue).sum();
   }

    @FunctionalInterface
    public interface CustomFuctionInterface {
        String printStr(String str1, String str2);
    }

    @Test
    public void demo5(){
        CustomFuctionInterface customFuctionInterface = (str1, str2) -> "hello " + str1 + str2;
        String printStr = customFuctionInterface.printStr("A&", "B");
        System.out.println("printStr = " + printStr);
    }

    @FunctionalInterface
    public interface CustomFuctionInterface1 {
        void doSomething();
    }

    //假设现在某个类的某个方法形参为CustomFuctionInterface1
    public static void execute(CustomFuctionInterface1 interface1) {
        interface1.doSomething();
    }

    @Test
    public void test3() {
        //传统的调用方法
        execute(new CustomFuctionInterface1() {
            @Override
            public void doSomething() {
                System.out.println("doSomething...");
            }
        });
        //通过Lambda表达式改进
        execute(() -> System.out.println("doSomething..."));
    }

    @Test
    public void demo6() throws FileNotFoundException {
        Book nails = new Book("Chinese Fingernail Image",
                Arrays.asList("Li", "Fu", "Li"),
                new int[]{256}, Year.of(2014), 25.2, MEDICINE);
        Book dragon = new Book("Compilers: Priciples, Techniques and Tools",
                Arrays.asList("Aho", "Lam", "Sethi", "Ullman"),
                new int[]{1009}, Year.of(2006), 23.6, COMPUTING);
        Book voss = new Book("Voss",
                Arrays.asList("Patrick White"),
                new int[]{478}, Year.of(1957), 19.8, FICTION);
        List<Book> library = Arrays.asList(nails, dragon, voss);

        //只包含计算机图书的流
        Stream<Book> computingBooks = library.stream()
                .filter(b -> b.getTopic() == COMPUTING);

        //图书标题的流
        Stream<String> bookTitles = library.stream()
                .map(Book::getTitle);

        //Book的流，根据标题排序
        Stream<Book> booksSortedByTitle = library.stream()
                .sorted(Comparator.comparing(Book::getTitle));

        //使用这个排序流创建一个作者流，根据图书标题排序，并且去除重复的
        Stream<String> authorsInBoolTitleOrder = library.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .flatMap(book -> book.getAuthors().stream())
                .distinct();

        //以标题的字母顺序生成前100个图书的流
        Stream<Book> readingList = library.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .limit(100);

        //除去前100个图书的流
        Stream<Book> remainderList = library.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .skip(100);

        //图书馆中最早出版的图书
        Optional<Book> oldest = library.stream()
                .min(Comparator.comparing(Book::getPubDate));

        //图书馆中图书的标题集合
        Set<String> titles = library.stream()
                .map(Book::getTitle)
                .collect(Collectors.toSet());

        //调试,peek可以对处于管道中间位置的流元素执行处理
        List multipleAuthoredHostories = library.stream()
                .filter(b -> b.getTopic() == Book.Topic.HISTORY)
                .peek(b -> System.out.println(b.getTitle()))
                .filter(b -> b.getAuthors().size() > 1)
                .collect(toList());

        //不创建一个分类map，并将每个主题映射到与该主题对应的图书列表中
        Map<Topic, List<Book>> booksByTopic = new HashMap<>();
        library.parallelStream()
                .peek(b -> {
                    Topic topic = b.getTopic();
                    List<Book> currentBooksForTopic = booksByTopic.get(topic);
                    if (currentBooksForTopic == null){
                        currentBooksForTopic = new ArrayList<>();
                    }
                    currentBooksForTopic.add(b);
                    booksByTopic.put(topic, currentBooksForTopic);
                })
                .anyMatch(b -> false);

        //历史书是否可以放到书架上面（净高19厘米）
        boolean withinShelfHeight = library.stream()
                .filter(b -> b.getTopic() == HISTORY)
                .allMatch(b -> b.getHeight() < 19);
        //搜索操作
        Optional<Book> anyBook = library.stream()
                .filter(b -> b.getAuthors().contains("Herman"))
                .findAny();
        //从书本中找到包含字符串"findFist"的第一行
        BufferedReader br = new BufferedReader(new
                FileReader("Mastering.tex"));
        Optional<String> line = br.lines()
                .filter(s -> s.contains("findFirsh"))
                .findFirst();
        //统计 IntSummaryStatistics是一个拥有5个属性的值对象：average,count,max.min,sum
        IntSummaryStatistics pageCountStatistics = library.stream()
                .mapToInt(b -> IntStream.of(b.getPageCounts()).sum())
                .summaryStatistics();

        //收集器模式的示例
        //根据主题对图书进行分类的Map
        Map<Topic, List<Book>> booksByTopicMap = library.stream()
                .collect(groupingBy(Book::getTopic));
        //从图书标题映射到最新版发布日期的有序Map
        Map<String, Year> titleToPubDate = library.stream()
                .collect(toMap(Book::getTitle,
                                Book::getPubDate,
                                BinaryOperator.maxBy(naturalOrder())));
        //将图书划分为小说（对应true）与非小说（对应false）的Map
        Map<Topic, Optional<Book>> mostAuthorsByTopic = library.stream()
                .collect(groupingBy(Book::getTopic,
                        maxBy(comparing(b -> b.getAuthors().size()))));
        //将每个主题关联到该主题总的卷数上
        Map<Topic,Integer> volumeCountByTopic = library.stream()
                .collect(groupingBy(Book::getTopic,
                        summingInt(b -> b.getPageCounts().length)));
        //拥有最多图书的主题
        Optional<Topic> mostPopularTopic = library.stream()
                .collect(groupingBy(Book::getTopic, counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        //将每个主题关联到该主题下所有图书标题拼接成的字符串上
        Map<Topic, String> concatenatedTitlesByTopic = library.stream()
                .collect(groupingBy(Book::getTopic,
                        mapping(Book::getTitle, joining(";"))));
    }
}
