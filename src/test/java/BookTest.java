import java.io.File;
import java.util.List;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookTest {

  private static SessionFactory sessionFactory;

  @BeforeAll
  public static void init() {
    //1.创建Configuration对象, 并读取hibernate.cfg.xml文件
    Configuration configuration = new Configuration().configure(
        new File("src/main/resources/hibernate.cfg.xml"));

    //2.获取SessionFactory对象，解析hibernate.cfg.xml文件生成基本的sql
    sessionFactory = configuration.buildSessionFactory(); //获取Session
  }


  @Test
  public void testSave() {
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


  @Test
  public void testGet() {
    //3.改对象有crud操作
    Session session = sessionFactory.openSession();
    //4.查询不需要 开启事务， 增删改 需要
//    session.beginTransaction();
    var book = session.get(Book.class, 1);
    System.out.println(book);
    //5.提交事务
//    session.getTransaction().commit();
    //6.关闭session
    session.close();
  }


  @Test
  public void testHQLGet() {
    //3.改对象有crud操作
    Session session = sessionFactory.openSession();
    //4.查询不需要 开启事务， 增删改 需要
//    session.beginTransaction();
    var arr =  session.createQuery("select b from Book b where b.title = 'Java'", Book.class);
    System.out.println(arr);
    //5.提交事务
//    session.getTransaction().commit();
    //6.关闭session
    session.close();
  }



  @Test
  public void testDelete() {
    //3.改对象有crud操作
    Session session = sessionFactory.openSession();
    //4.开启事务
    session.beginTransaction();
    Book b1 = new Book();
    b1.setId(2);
    session.delete(b1); //删除是 传 对象进去 不是id
    //5.提交事务
    session.getTransaction().commit();
    //6.关闭session
    session.close();
  }


}
