package com.ty.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试类
 *
 * @ClassName Test
 * @Author tianye
 * @Date 2019/3/22 23:17
 * @Version 1.0
 */
public class Test {
    /**
     * @param urlPath     下载路径
     * @param downloadDir 下载存放目录
     * @return 返回下载文件
     */
    public static File downloadFile(String urlPath, String downloadDir) {
        File file = null;
        try {
            // 统一资源
            URL url = new URL(urlPath);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("POST");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();

            // 文件大小
            int fileLength = httpURLConnection.getContentLength();

            // 文件名
            String filePathUrl = httpURLConnection.getURL().getFile();
            String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);

            System.out.println("file length---->" + fileLength);

            URLConnection con = url.openConnection();

            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());

            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
                // 打印下载百分比
                // System.out.println("下载了-------> " + len * 100 / fileLength +
                // "%\n");
            }
            bin.close();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return file;
        }

    }

    /**
     * 将网络文件编码为base64
     *
     * @param fileUrl
     * @return
     * @throws Exception
     */
    private Map<String, String> encodeImageToBase64(String fileUrl) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        //打开链接
        HttpURLConnection conn = null;
        Map<String, String> map = new HashMap<>();
        try {
            URL url = new URL(fileUrl);
            conn = (HttpURLConnection) url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            // 设置字符编码
            conn.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            conn.connect();

            // 文件名
            String filePathUrl = conn.getURL().getFile();
            String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);
            String fileType = fileFullName.substring(fileFullName.lastIndexOf(".") + 1);
            map.put("type", fileType);

            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[conn.getContentLength()];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = inStream.read(buffer)) != -1) {
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            //关闭输入流
            inStream.close();
            byte[] data = outStream.toByteArray();
            //对字节数组Base64编码
            Base64.Encoder encoder = Base64.getEncoder();
            //返回Base64编码过的字节数组字符串
            map.put("base64", encoder.encodeToString(data));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.getEncoder().encodeToString(buffer);
    }


    public static void main(String[] args) {

        try {
            System.out.println(encodeBase64File("D:\\invoice_model_zh_CN.xlsx"));
        } catch (Exception e) {
            e.printStackTrace();
        }


//        BigDecimal bigDecimal = new BigDecimal("10");
//        BigDecimal bigDecimal1 = new BigDecimal("20");
//
//        NumberSumFunctionalInterface<BigDecimal> numberSum = BigDecimalTest::overrideAdd;
//
//        BigDecimal sum = numberSum.sum(bigDecimal, bigDecimal1);
//
//        System.out.println(sum);
//
//        NumberSumFunctionalInterface<Integer> integerSum = Integer::sum;
//
//        Integer integer = integerSum.sum(10, 20);
//
//        System.out.println(integer);

//        com.ty.functionalInterface.Test  i = (t) -> 2 * t;
//
//        com.ty.functionalInterface.Test  i1 = (t) -> 3 * t;
//
//        com.ty.functionalInterface.Test  i2 = (t) -> 4 * t;
//
//        Integer add = i.add(10);
//
//        List<String> list = Arrays.asList();
//
//        list.stream().forEach(System.out::println);
//
//
//        System.out.println(add);

//        System.out.println(UUID.randomUUID().toString());
//        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
//        System.out.println(UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
//        System.out.println(UUID.randomUUID().toString().replaceAll("-", "").length());
//
//        System.out.println(System.currentTimeMillis());
//
//        System.out.println("\"https://blog.csdn.net/king13127/article/details/78455959\"".length());
//        String s = "D:\\ProgramFile\\SogouInput\\readme.txt";
//        System.out.println(s.substring(s.lastIndexOf(File.separatorChar) + 1));
//        System.out.println(s.substring(s.lastIndexOf(File.separatorChar) + 1).substring(s.substring(s.lastIndexOf(File.separatorChar) + 1).lastIndexOf(".") + 1));
//        String valueAddService;
//        Byte t = 1;
//        switch (t) {
//            case 0:
//                System.out.println(0);
//                valueAddService = "world_ease";
//                break;
//            case 1:
//                System.out.println(1);
//                valueAddService = "origin_crt";
//                break;
//            case 2:
//                System.out.println(2);
//                valueAddService = "fumigation";
//                break;
//            default:
//                valueAddService = null;
//        }

//        String call = "{\"ask\":\"Success\",\"message\":\"Success\",\"data\":[{\"transit_warehouse_code\":\"wh002\",\"transit_warehouse_name\":\"深圳金积嘉仓\"},{\"transit_warehouse_code\":\"wh005\",\"transit_warehouse_name\":\"抚州仓\"},{\"transit_warehouse_code\":\"wh001\",\"transit_warehouse_name\":\"佛山仓\"},{\"transit_warehouse_code\":\"wh003\",\"transit_warehouse_name\":\"广州仓\"},{\"transit_warehouse_code\":\"wh004\",\"transit_warehouse_name\":\"测试仓\"}]}";
//
//        Map map = JSON.parseObject(call, Map.class);
//
//        List<Demo.DemoTest> data = (List<Demo.DemoTest>)map.get("data");
//
//        Demo.DemoTest demoTest = data.get(0);
//
//        System.out.println(demoTest.getTransitWarehouseCode());
//        System.out.println(demoTest.getTransitWarehouseName());

//        List<Byte> list = new ArrayList<>();
//        list.add(Byte.valueOf("1"));
//        list.add(Byte.valueOf("2"));
//        list.add(Byte.valueOf("3"));
//        list.add(Byte.valueOf("4"));
//        list.add(Byte.valueOf("2"));
//        list.add(Byte.valueOf("1"));
//        List<Byte> list1 = list.stream().filter(l -> l == 1 || l == 2).collect(Collectors.toList());
//
//        System.out.println("test");
//        list1.forEach(System.out::println);
//        Byte value = Byte.valueOf("-1");
//        System.out.println(value);

//        System.out.println(JSON.toJSONString("{}"));
//        System.out.println(valueAddService);


    }
}
