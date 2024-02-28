package org.example;

import java.io.File;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

  public static void main(String[] args) {
    //1.创建Configuration对象, 并读取hibernate.cfg.xml文件
    Configuration configuration = new
        Configuration().configure(new File("src/main/resources/hibernate.cfg.xml"));

    //2.获取SessionFactory对象，解析hibernate.cfg.xml文件生成基本的sql
    SessionFactory sessionFactory = configuration.buildSessionFactory(); //获取Session

    //3.改对象有crud操作
    Session session = sessionFactory.openSession();

    //4.开启事务
    session.beginTransaction();
    Book b1 = new Book();
    b1.setTitle("Java");
    b1.setAuthor("Tom");
    session.save(b1);

    //5.提交事务
    session.getTransaction().commit();
    //6.关闭session
    session.close();



  }
}