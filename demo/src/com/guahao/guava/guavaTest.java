package com.guahao.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Xu Jianglin
 * @create 2019-02-15 17:42
 */
public class guavaTest {

    //find key by vale and find value by key
    @Test
    public void test7(){


    }
    @Test
    public void test8(){
        HashMultimap<String, String>  multimap = HashMultimap.create();
        multimap.put("car","Toyota");
        multimap.put("car","Benz");
        multimap.put("car","Benz");
        multimap.put("book","java");
        System.out.println(multimap);
/*        System.out.println(multimap.get("car"));
        System.out.println(multimap.get("car").contains("Benz"));
        System.out.println(multimap.get("car").getClass());
        System.out.println(multimap.get("car1"));*/
    }
    //一对多
    @Test
    public void test6(){
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("car","Toyota");
        multimap.put("car","Benz");
        multimap.put("car","Benz");
        multimap.put("book","java");
        System.out.println(multimap);
/*        System.out.println(multimap.get("car"));
        System.out.println(multimap.get("car").contains("Benz"));
        System.out.println(multimap.get("car").getClass());
        System.out.println(multimap.get("car1").contains("a"));*/
    }

    @Test
    public void test5(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");

        List<String> readOnlyList = Collections.unmodifiableList(list);

        list.add("c");

        System.out.println(list.size());
        System.out.println(readOnlyList.size());//改变源集合,返回的readonlyList也变了,不合要求
        System.out.println(readOnlyList);

        System.out.println("------------------------------");

        ImmutableList<String> immutableList = ImmutableList.of("a", "b");

        //immutableList.add("c"); //UnsupportedOperationException

        System.out.println(immutableList);

        ImmutableList<String> immutableList2 = ImmutableList.copyOf(list);
        list.add("cc");
        System.out.println(immutableList2.size() + "::::" + list.size());//改变源集合不会改变immutableList2
    }

    @Test
    public void test4(){
        HashMultiset set = HashMultiset.create();
        set.add("a");
        set.add("a");
        set.add("a");
        set.add("b");
        System.out.println(set);
        System.out.println(set.size());
        System.out.println(set.contains("a"));
        System.out.println(set.count("a"));
        System.out.println(Arrays.toString(set.toArray()));
        set.setCount("a", 10);
        System.out.println(set);
        System.out.println(set.size());
        System.out.println(Arrays.toString(set.toArray()));
        set.stream().distinct().forEach(System.out::println);

    }

    @Test
    public void test3(){
        List<Integer> list = Ints.asList(1, 2, 3);
        System.out.println(Ints.join(",",2,3,4));

        int[] newA = Ints.concat(new int[]{1, 2}, new int[]{2, 3, 4});
        System.out.println(Arrays.toString(newA));

        System.out.println(Ints.max(newA));
        System.out.println(Ints.min(newA));

        System.out.println(Ints.contains(newA,3));

        int[] arr = Ints.toArray(list);

    }


    @Test
    public void test2(){
        CharMatcher diMatcher = CharMatcher.DIGIT;
        CharMatcher anyMatcher = CharMatcher.ANY;

        String s = "ab1c24b5b6bb6";
        System.out.println(diMatcher.retainFrom(s));
        System.out.println(diMatcher.removeFrom(s));
        System.out.println(diMatcher.replaceFrom(s,"HELLO"));
        System.out.println(diMatcher.apply('1'));
        System.out.println(diMatcher.indexIn(s));
        System.out.println(diMatcher.collapseFrom(s,'*'));//敏感信息脱敏

        System.out.println("--------------------");
        System.out.println(anyMatcher.inRange('u','z').or(CharMatcher.is('n')).replaceFrom("xujianglin","*"));


    }


    @Test
    public void test1(){
//        Joiner joiner = Joiner.on(",").skipNulls();
        Joiner joiner = Joiner.on(",").useForNull("I am null");
        System.out.println(joiner.join(Lists.newArrayList("a",null,"b","c")));

        System.out.println("-------------------");

//        Splitter splitter = Splitter.on(",");
//        Splitter splitter = Splitter.on(",").trimResults();
        Splitter splitter = Splitter.on(",").omitEmptyStrings().trimResults();
        Iterable<String> resul = splitter.split("a,b,c,, , 99 ,");
        for (String s:resul) {
            System.out.println(s);
        }



    }

    public static void main(String[] args) {
        String rules = "alert tcp  $EXTERNAL_NET any -> $HOME_NET $HTTP_PORTS (msg:\"jboss JMXInvokerServlet RCE\";  content:\"invoker/JMXInvokerServlet\";nocase;  sid:103050; classtype:web-application-attack; rev:2; )";
//        String rules = "alert tcp $EXTERNAL_NET any -> any  $HTTP_PORTS (msg:\"php_pass\"; content:\"$password = \";  sid:102002; )";
//        Pattern pattern = Pattern.compile("\\(msg:\"(.*?\";)");
//        Pattern pattern = Pattern.compile("\\(msg:\"(.*)?\";");
        Pattern pattern = Pattern.compile("\\(msg:\"(.*)?\";(.*)?\";");
        Matcher matcher = pattern.matcher(rules);


        matcher.find();
        System.out.println(matcher.group());
        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
//        System.out.println(matcher.group(2));

//        System.out.println(matcher.group(3));

    }



}
