
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MyMap_Tests {
    public String generateClassName(String name)
    {
        if(getClass().toString().contains("."))
        {
            return getClass().toString().substring(6,getClass().toString().lastIndexOf(".")+1)+name;
        }
        return name;
    }

    public ArrayList<String> allowedImports = new ArrayList<>();

    @Before
    public void setup()
    {
        allowedImports.add("java.util.ArrayList");
    }

    @Test(timeout = 250)
    public void checkImports() throws Exception{
        String className = "MyMap";
        String fileName = "src/"+generateClassName(className).replaceAll("\\.","/")+".java";
        boolean allowedOnly = true;
        ArrayList<String> invalidImport = new ArrayList<>();
        try
        {

            File file = new File(fileName);
            Scanner fromFile = new Scanner(file);
            while(fromFile.hasNextLine())
            {
                String line = fromFile.nextLine().trim();
                if(line.contains("import"))
                {
                    boolean good = false;
                    for(String allowed: allowedImports)
                    {
                        if(line.matches("\\s*import\\s+"+allowed+"\\s*;\s*(//\\.*)?"))
                            good=true;
                    }
                    if(!good)
                    {
                        allowedOnly=false;
                        invalidImport.add(line);
                    }
                }

            }
        }
        catch(Exception e)
        {

            Assert.assertTrue("Missing File: "+className+".java",false);
            allowedOnly = false;
        }
        System.out.println(className);
        Assert.assertTrue("Invalid imports: "+invalidImport,allowedOnly);
    }
    // add
    @Test(timeout = 250)
    public void test1() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("MyMap"));
        MapInterface<Integer,String> map = (MapInterface<Integer,String>) classRef.getConstructor().newInstance();

        try
        {
            Assert.assertNull(map.put(1,"Jane"));
            Assert.assertNull(map.put(2,"Ted"));
            Assert.assertNull(map.put(3,"Billy"));
            Assert.assertNull(map.put(4,"Jane"));
            Assert.assertEquals("Jane",map.put(4,"Petra"));
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    @Test(timeout = 250)
    public void test2() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("MyMap"));
        MapInterface<Character,Long> map = (MapInterface<Character,Long>) classRef.getConstructor().newInstance();

        try
        {
            Assert.assertNull(map.put('A',45L));
            Assert.assertNull(map.put('B',17L));
            Assert.assertNull(map.put('C',16L));
            Assert.assertNull(map.put('D',45L));
            Assert.assertNull(map.put('E',13L));
            Assert.assertEquals((Long)17L, map.put('B',99L));
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    // containsKey / ContainsVale
    @Test(timeout = 250)
    public void test3() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("MyMap"));
        MapInterface<Integer,String> map = (MapInterface<Integer,String>) classRef.getConstructor().newInstance();

        try
        {
            map.put(1,"Tina");
            map.put(6,"Ann");
            map.put(3,"James");
            map.put(7,"Paula");
            map.put(8,"Marry");
            map.put(1,"Petra");

            Assert.assertTrue(map.containsKey(1));
            Assert.assertFalse(map.containsKey(2));
            Assert.assertTrue(map.containsKey(3));
            Assert.assertFalse(map.containsKey(4));
            Assert.assertFalse(map.containsKey(5));
            Assert.assertTrue(map.containsKey(6));
            Assert.assertTrue(map.containsKey(7));
            Assert.assertTrue(map.containsKey(8));

            Assert.assertFalse(map.containsValue("Tina"));
            Assert.assertTrue(map.containsValue("Ann"));
            Assert.assertTrue(map.containsValue("James"));
            Assert.assertTrue(map.containsValue("Paula"));
            Assert.assertTrue(map.containsValue("Marry"));
            Assert.assertTrue(map.containsValue("Petra"));
            Assert.assertFalse(map.containsValue("Bilbo"));
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    @Test(timeout = 250)
    public void test4() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("MyMap"));
        MapInterface<Character,Long> map = (MapInterface<Character,Long>) classRef.getConstructor().newInstance();

        try
        {
            map.put('A',1L);
            map.put('C',2L);
            map.put('D',3L);
            map.put('F',4L);
            map.put('F',5L);
            map.remove('D');


            Assert.assertTrue(map.containsKey('A'));
            Assert.assertFalse(map.containsKey('B'));
            Assert.assertTrue(map.containsKey('C'));
            Assert.assertFalse(map.containsKey('D'));
            Assert.assertFalse(map.containsKey('E'));
            Assert.assertTrue(map.containsKey('F'));

            Assert.assertTrue(map.containsValue(1L));
            Assert.assertTrue(map.containsValue(2L));
            Assert.assertFalse(map.containsValue(3L));
            Assert.assertFalse(map.containsValue(4L));
            Assert.assertTrue(map.containsValue(5L));
            Assert.assertFalse(map.containsValue(6L));

        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    // clear / isEmpty
    @Test(timeout = 250)
    public void test5() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("MyMap"));
        MapInterface<Integer,String> map = (MapInterface<Integer,String>) classRef.getConstructor().newInstance();

        try
        {
            Assert.assertTrue(map.isEmpty());
            map.put(1,"Tina");
            map.put(6,"Ann");
            map.put(3,"James");

            Assert.assertTrue(map.containsKey(1));
            Assert.assertTrue(map.containsKey(3));
            Assert.assertTrue(map.containsKey(6));
            Assert.assertTrue(map.containsValue("Tina"));
            Assert.assertTrue(map.containsValue("Ann"));
            Assert.assertTrue(map.containsValue("James"));
            Assert.assertEquals(3,map.size());

            Assert.assertFalse(map.isEmpty());
            map.clear();

            Assert.assertFalse(map.containsKey(1));
            Assert.assertFalse(map.containsKey(3));
            Assert.assertFalse(map.containsKey(6));
            Assert.assertFalse(map.containsValue("Tina"));
            Assert.assertFalse(map.containsValue("Ann"));
            Assert.assertFalse(map.containsValue("James"));
            Assert.assertEquals(0,map.size());
            Assert.assertTrue(map.isEmpty());
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    @Test(timeout = 250)
    public void test6() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("MyMap"));
        MapInterface<Character,Long> map = (MapInterface<Character,Long>) classRef.getConstructor().newInstance();

        try
        {
            Assert.assertTrue(map.isEmpty());
            map.put('A',1L);
            map.put('C',2L);
            map.put('D',3L);
            map.put('Z',3L);
            Assert.assertFalse(map.isEmpty());

            Assert.assertTrue(map.containsKey('A'));
            Assert.assertTrue(map.containsKey('C'));
            Assert.assertTrue(map.containsKey('D'));
            Assert.assertTrue(map.containsKey('Z'));
            Assert.assertTrue(map.containsValue(1L));
            Assert.assertTrue(map.containsValue(2L));
            Assert.assertTrue(map.containsValue(3L));
            Assert.assertEquals(4,map.size());

            map.clear();
            Assert.assertTrue(map.isEmpty());
            Assert.assertFalse(map.containsKey('A'));
            Assert.assertFalse(map.containsKey('C'));
            Assert.assertFalse(map.containsKey('D'));
            Assert.assertFalse(map.containsKey('Z'));
            Assert.assertFalse(map.containsValue(1L));
            Assert.assertFalse(map.containsValue(2L));
            Assert.assertFalse(map.containsValue(3L));
            Assert.assertEquals(0,map.size());
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    // get / remove
    @Test(timeout = 250)
    public void test7() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("MyMap"));
        MapInterface<Integer,String> map = (MapInterface<Integer,String>) classRef.getConstructor().newInstance();

        try
        {
            Assert.assertNull(map.get(1));
            Assert.assertNull(map.get(3));
            Assert.assertNull(map.get(4));
            Assert.assertNull(map.get(2));

            map.put(1,"A");
            map.put(3,"B");
            map.put(4,"C");
            Assert.assertEquals(3,map.size());

            Assert.assertEquals(map.get(1),"A");
            Assert.assertEquals(map.get(3),"B");
            Assert.assertEquals(map.get(4),"C");
            Assert.assertNull(map.get(2));

            Assert.assertNull(map.remove(5));
            Assert.assertEquals(map.remove(1),"A");
            Assert.assertEquals(map.remove(4),"C");

            Assert.assertNull(map.get(1));
            Assert.assertEquals(map.get(3),"B");
            Assert.assertNull(map.get(4));
            Assert.assertNull(map.get(2));
            Assert.assertEquals(1,map.size());
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    @Test(timeout = 250)
    public void test8() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("MyMap"));
        MapInterface<Character,Long> map = (MapInterface<Character,Long>) classRef.getConstructor().newInstance();

        try
        {
            Assert.assertNull(map.get('a'));
            Assert.assertNull(map.get('b'));
            Assert.assertNull(map.get('c'));
            Assert.assertNull(map.get('d'));
            Assert.assertNull(map.get('e'));

            map.put('a',1L);
            map.put('b',2L);
            map.put('c',3L);
            map.put('d',4L);
            map.put('e',5L);

            Assert.assertEquals(5,map.size());

            Assert.assertEquals(map.get('a'),(Long)1L);
            Assert.assertEquals(map.get('b'),(Long)2L);
            Assert.assertEquals(map.get('c'),(Long)3L);
            Assert.assertEquals(map.get('d'),(Long)4L);
            Assert.assertEquals(map.get('e'),(Long)5L);


            Assert.assertNull(map.remove('f'));
            Assert.assertEquals(map.remove('a'),(Long)1L);
            Assert.assertEquals(map.remove('c'),(Long)3L);

            Assert.assertNull(map.get('a'));
            Assert.assertEquals(map.get('b'),(Long)2L);
            Assert.assertNull(map.get('c'));
            Assert.assertEquals(map.get('d'),(Long)4L);
            Assert.assertEquals(map.get('e'),(Long)5L);
            Assert.assertEquals(3,map.size());
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    //entrySet / values / entrySet
    // get / remove
    @Test(timeout = 250)
    public void test9() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("MyMap"));
        MapInterface<Integer,String> map = (MapInterface<Integer,String>) classRef.getConstructor().newInstance();

        ArrayList<MapEnt<Integer,String>> entries = new ArrayList<MapEnt<Integer,String>>();
        entries.add(new MapEnt<>(1,"C"));
        entries.add(new MapEnt<>(3,"B"));
        entries.add(new MapEnt<>(4,"C"));
        entries.add(new MapEnt<>(6,"M"));

        ArrayList<Integer> keys = new ArrayList<>();
        keys.add(1);
        keys.add(3);
        keys.add(4);
        keys.add(6);

        ArrayList<String> Values = new ArrayList<>();
        keys.add(1);
        keys.add(3);
        keys.add(4);
        keys.add(6);

        try
        {
            map.put(1,"C");
            map.put(3,"B");
            map.put(4,"C");
            map.put(6,"M");
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    @Test(timeout = 250)
    public void test10() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("MyMap"));
        MapInterface<Character,Long> map = (MapInterface<Character,Long>) classRef.getConstructor().newInstance();

        try
        {
            map.put('a',7L);
            map.put('b',8L);
            map.put('c',3L);
            map.put('d',14L);
            map.put('e',55L);
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }
}
