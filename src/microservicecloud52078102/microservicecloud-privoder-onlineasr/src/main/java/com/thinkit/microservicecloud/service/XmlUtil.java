package com.thinkit.microservicecloud.service;

import org.apache.ibatis.javassist.bytecode.Descriptor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public class XmlUtil {



    public static String readXml(String xml){

        String result = "";
        String punctuation = "";
        Document doc = null;

            // 读取并解析XML文档
            // SAXReader就是一个管道，用一个流的方式，把xml文件读出来
            // SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档
            // Document document = reader.read(new File("User.hbm.xml"));
            // 下面的是通过解析xml字符串的
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElt = doc.getRootElement(); // 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称


        Iterator it = rootElt.elementIterator();
        while (it.hasNext()){
            Element element1 = (Element) it.next();

            System.out.println("子节点： " + element1.getName());
            System.out.println("子节点： " + element1.getText());

            if(element1.getName().equals("result")){
                Iterator rut = element1.elementIterator();
                while (rut.hasNext()){
                    Element element2 = (Element) rut.next();

                    System.out.println("二级子节点： " + element2.getName());
                    System.out.println("二级子节点： " + element2.getText());
                    if(element2.getName().equals("name")){

                        result = element2.getText();
                    }

                    if(element2.getName().equals("phoneme")){
                        punctuation = element2.getText().substring(element2.getText().length()-1,element2.getText().length());
                    }
                }
            }
        }


        return result+punctuation;
    }
}
