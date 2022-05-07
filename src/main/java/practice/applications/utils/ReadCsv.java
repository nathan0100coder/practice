package practice.applications.utils;

import lombok.extern.slf4j.Slf4j;
import practice.datastruc.obj.Dog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: nathan_wang
 * @Date: 2021/2/23 14:52
 * @Description: 反射统一处理np数据
 * @Version: 1.0
 */
@Slf4j
public class ReadCsv {
    public static void main(String[] args) {
        List<Object> results = read("D:\\tmp\\demo.csv", new Dog(),",");
        for (Object var:results){
            Dog dog = (Dog) var;
            System.out.println(dog);
        }
    }

    /**
     * 反射读取csv文件
     *
     * @param path  path
     * @param obj   obj
     * @param regex 正则
     * @return      ret
     */
    public static List<Object> read(String path, Object obj,String regex) {
        List<Object> list = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) {
               throw new RuntimeException("文件不存在!");
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "GBK"));
                //抬头栏
                String s = reader.readLine();
                System.out.println(s);
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] row = line.split(regex, -1);
                    // 分隔字符串（这里用到转义）,CSV大部分都是,或者|来分隔数据的，这里看情况来作决定，存储到List<Object>里
                    // 通过反射获取运行时类
                    Class clazz = obj.getClass();
                    // 创建运行时类的对象
                    Object infos = clazz.newInstance();
                    Field[] fs = infos.getClass().getDeclaredFields();
                    for (int i = 0; i < fs.length; i++) {
                        Field f = fs[i];
                        // 设置这些属性值是可以访问的
                        f.setAccessible(true);
                        String type = f.getType().toString();
                        if (type.endsWith("String")) {
                            // 给属性赋值
                            f.set(infos, row[i]);
                        }
                    }
                    list.add(infos);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 读取指定目录下的xxx.csv文件，并转化为java对象
     *
     * @param t            读取csv文件之后将要转换成的对象
     * @param readFilePath csv文件所在路径
     * @param charSet      编码格式
     * @return 实体对象列表
     */
//    public static <T> List<T> readCsvDataToObject(T t, String readFilePath, String charSet) {
//        List<T> resultList = new ArrayList<T>();
//        // 创建CSV读对象
//        CsvReader csvReader = null;
//        try {
//            csvReader = new CsvReader(readFilePath, ',', Charset.forName(charSet));
//            // 所有成员变量
//            Field[] fields = t.getClass().getDeclaredFields();
//            // 成员变量的值
//            Object entityMemberValue = "";
//            //读取csv文件列标题
//            csvReader.readHeaders();
//            while (csvReader.readRecord()) {
//                Object newInstance = t.getClass().newInstance();
//                // 读一整行
//                csvReader.getRawRecord();
//                for (int f = 0; f < fields.length; f++) {
//                    fields[f].setAccessible(true);
//                    String fieldName = fields[f].getName();
//                    entityMemberValue = getEntityMemberValue(entityMemberValue, fields, f, csvReader.get(fieldName));
//                    // 赋值
//                    PropertyUtils.setProperty(newInstance, fieldName, entityMemberValue);
//                }
//                resultList.add((T) newInstance);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (null != csvReader) {
//                csvReader.close();
//            }
//        }
//        return resultList;
//    }
    /**
     * 根据实体成员变量的类型得到成员变量的值
     *
     * @param realValue 要赋值的对象
     * @param fields    赋值对象所有的成员变量
     * @param f         具体赋值的第几个成员变量
     * @param value     将要写入的值
     * @return          转换之后的属性
     */
    private static Object getEntityMemberValue(Object realValue, Field[] fields, int f, String value) throws ParseException, ClassNotFoundException {
        String type = fields[f].getType().getName();
        switch (type) {
            case "char":
            case "java.lang.Character":
            case "java.lang.String":
                realValue = value;
                break;
            case "java.util.Date":
                realValue = "".equals(value) ? null : DateUtils.parseDate(value);
                break;
            case "java.lang.Integer":
                realValue = "".equals(value) ? null : Integer.valueOf(value);
                break;
            case "int":
            case "float":
            case "double":
            case "java.lang.Double":
            case "java.lang.Float":
            case "java.lang.Long":
            case "java.lang.Short":
            case "java.math.BigDecimal":
                realValue = "".equals(value) ? null : new BigDecimal(value);
                break;
            default:
                realValue = null;
                break;
        }
        return realValue;
    }

    /**
     * 使用csvjava通过反射导出csv文件(通用方法)
     *
     * @param objectData   需要导出的java对象数据
     * @param saveFilePath 导出文件所在路径
     * @param charSet      文件编码格式
     * @return 成功返回true 失败返回false
     */
//    public static <T> boolean generateCsv(List<T> objectData, String saveFilePath, String charSet) {
//        // 创建CSV写对象
//        CsvWriter csvWriter = null;
//        boolean flag = true;
//        try {
//            csvWriter = new CsvWriter(saveFilePath, ',', Charset.forName(charSet));
//            //获取数据属性信息并写入第一行
//            T firstData = objectData.get(0);
//            Field[] dataFields = firstData.getClass().getDeclaredFields();
//            for (Field field : dataFields) {
//                String fieldName = field.getName();
//                csvWriter.write(fieldName);
//            }
//            //第一行结束，换行
//            csvWriter.endRecord();
//            // 遍历集合数据，产生数据行
//            for (T data : objectData) {
//                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
//                for (Field field : dataFields) {
//                    if (field.toString().contains("static")) {
//                        continue;
//                    }
//                    String fieldName = field.getName();
//                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//                    Class dataClass = data.getClass();
//                    Method getMethod = dataClass.getMethod(getMethodName, new Class[]{});
//                    Object value = getMethod.invoke(data, new Object[]{});
//                    // 判断值的类型后进行强制类型转换
//                    String textValue;
//                    if (value instanceof Date) {
//                        Date date = (Date) value;
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                        textValue = sdf.format(date);
//                    } else {
//                        // 其它数据类型都当作字符串简单处理
//                        if (value == null) {
//                            value = "";
//                        }
//                        textValue = value.toString();
//                    }
//                    csvWriter.write(textValue);
//                }
//                //换行
//                csvWriter.endRecord();
//            }
//        } catch (Exception e) {
//            flag = false;
//            e.printStackTrace();
//        } finally {
//            //关闭
//            if (null != csvWriter) {
//                csvWriter.close();
//            }
//        }
//        return flag;
//    }

}
