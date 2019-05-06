package com.ty.test;

import java.util.List;

/**
 * TODO
 *
 * @ClassName Demo
 * @Author tianye
 * @Date 2019/4/28 17:18
 * @Version 1.0
 */
public class Demo {

    private String ask;

    private String message;

    private List<DemoTest> data;

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DemoTest> getData() {
        return data;
    }

    public void setData(List<DemoTest> data) {
        this.data = data;
    }

    public class DemoTest {
        private String transitWarehouseCode;
        private String transitWarehouseName;

        public String getTransitWarehouseCode() {
            return transitWarehouseCode;
        }

        public void setTransitWarehouseCode(String transitWarehouseCode) {
            this.transitWarehouseCode = transitWarehouseCode;
        }

        public String getTransitWarehouseName() {
            return transitWarehouseName;
        }

        public void setTransitWarehouseName(String transitWarehouseName) {
            this.transitWarehouseName = transitWarehouseName;
        }
    }

    public static void main(String[] args) {

//        try {
//            String file = Test.encodeBase64File("D:\\33333333.png");
//            System.out.println("size:"+file.length());
//            System.out.println(file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String s = "GC_G1149_UK";
        String[] s1 = s.split("_");

        System.out.println(s1[s1.length-1]);

    }
}
