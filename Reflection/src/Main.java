import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Test t = new Test();
        getConstructors(t);
        geMethod(t);
        getFiled(t);
        loadshowstrs();
        setValue();
    }

    /**
     * 获取构造函数
     * 
     * @param test
     */
    private static void getConstructors(Test test) {
        Class c = test.getClass();
        String classname = c.getName();
        try {
            Constructor[] theConstructors = c.getDeclaredConstructors();
            for (int i = 0; i < theConstructors.length; i++) {
                // 输出修饰域和方法名称
                int mod = theConstructors[i].getModifiers();
                System.out
                        .print(Modifier.toString(mod) + " " + classname + "(");
                // 获取指定构造方法的参数的集合
                Class[] parameterTypes = theConstructors[i].getParameterTypes();
                for (int j = 0; j < parameterTypes.length; j++) {
                    // 输出打印参数列表
                    System.out.print(parameterTypes[j].getName());
                    if (parameterTypes.length > j + 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println(")");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取反射类的方法
     */
    public static void geMethod(Test test) {

        Class c = test.getClass();
        String classname = c.getName();

        /*
         * Note: 方法getDeclaredMethods()只能获取到由当前类定义的所有方法，不能获取从父类继承的方法
         * 方法getMethods() 不仅能获取到当前类定义的public方法，也能得到从父类继承和已经实现接口的public方法
         * 请查阅开发文档对这两个方法的详细描述。
         */
        Method[] methods = c.getDeclaredMethods();
        // Method[] methods = c.getMethods();

        for (int i = 0; i < methods.length; i++) {

            // 打印输出方法的修饰域
            int mod = methods[i].getModifiers();
            System.out.print(Modifier.toString(mod) + " ");

            // 输出方法的返回类型
            System.out.print(methods[i].getReturnType().getName());

            // 获取输出的方法名
            System.out.print(" " + methods[i].getName() + "(");

            // 打印输出方法的参数列表
            Class[] parameterTypes = methods[i].getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                System.out.print(parameterTypes[j].getName());
                if (parameterTypes.length > j + 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");
        }
    }

    /**
     * 获取成员变量
     * 
     * @param test
     */
    public static void getFiled(Test test) {
        Class c = test.getClass(); // 获取Class类的对象的方法之一

        try {
            System.out.println("public & 非public 属性");
            Field[] fa = c.getDeclaredFields();
            for (int i = 0; i < fa.length; i++) {

                Class cl = fa[i].getType(); // 属性的类型

                int md = fa[i].getModifiers(); // 属性的修饰域

                Field f = c.getDeclaredField(fa[i].getName()); // 属性的值
                f.setAccessible(true); // Very Important
                Object value = (Object) f.get(new Test());

                if (value == null) {
                    System.out.println(Modifier.toString(md) + " " + cl + " : "
                            + fa[i].getName());
                } else {
                    System.out.println(Modifier.toString(md) + " " + cl + " : "
                            + fa[i].getName() + " = " + value.toString());
                }
            }

            System.out.println("public 属性");
            Field[] fb = c.getFields();
            for (int i = 0; i < fb.length; i++) {

                Class cl = fb[i].getType(); // 属性的类型

                int md = fb[i].getModifiers(); // 属性的修饰域

                Field f = c.getField(fb[i].getName()); // 属性的值
                f.setAccessible(true);
                Object value = (Object) f.get(new Test());

                // 判断属性是否被初始化
                if (value == null) {
                    System.out.println(Modifier.toString(md) + " " + cl + " : "
                            + fb[i].getName());
                } else {
                    System.out.println(Modifier.toString(md) + " " + cl + " : "
                            + fb[i].getName() + " = " + value.toString());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 类无法创建时，从类名开始，利用反射。
     */
    public static void loadshowstr() {
        try {
            Class c = Class.forName("Test");
            Constructor ct = c.getConstructor(null);
            Object obj = ct.newInstance(null);

            Class paramTypes[] = getParamTypes(c, "showstr");
            Method meth = c.getDeclaredMethod("showstr", paramTypes);
            meth.setAccessible(true);
            String[] str = new String[1];
            str[0] = "str";
            Object[] params = str;
            String s = (String) meth.invoke(obj, params);
            System.out.println("load " + s);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 直接创建对象，然后利用反射
     */
    public static void loadshowstrs() {
        try {
            Test t = new Test();
            Method meth = t.getClass().getDeclaredMethod("showstr",
                    String.class, String.class);
            meth.setAccessible(true);
            String s = (String) meth.invoke(t, "str1", "str2");
            System.out.println("load " + s);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Field 的get set 方法
     */
    public static void setValue() {
        try {
            Class c = Class.forName("Test");
            Constructor ct = c.getConstructor(int.class, String.class,
                    String.class);
            Object obj = ct.newInstance(0, "a", "b");

            Field f = c.getField("stra");
            f.setAccessible(true);
            String value1 = (String) f.get(obj);
            System.out.println("load " + value1);
            f.set(obj, "hehehe");

            Method meth = c.getDeclaredMethod("getStra", null);
            meth.setAccessible(true);
            String value2 = (String) meth.invoke(obj, null);
            System.out.println("load " + value2);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取参数类型，返回值保存在Class[]中
     */
    public static Class[] getParamTypes(Class cls, String mName) {
        Class[] cs = null;
        /*
         * Note: 由于我们一般通过反射机制调用的方法，是非public方法 所以在此处使用了getDeclaredMethods()方法
         */
        Method[] mtd = cls.getDeclaredMethods();
        for (int i = 0; i < mtd.length; i++) {
            if (!mtd[i].getName().equals(mName)) { // 不是我们需要的参数，则进入下一次循环
                continue;
            }
            cs = mtd[i].getParameterTypes();
        }
        return cs;
    }

}
