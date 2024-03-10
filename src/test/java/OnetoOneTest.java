import java.io.File;
import org.example.entity.Book;
import org.example.entity.oneToOne.Person;
import org.example.entity.oneToOne.SSN;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class OnetoOneTest {

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
    Person person = new Person();
    SSN ssn = new SSN();

    person.setName("John");

    ssn.setNumber("090342787");
    ssn.addPerson(person);

    session.save(ssn);
    //5.提交事务
    session.getTransaction().commit();
    //6.关闭session
    session.close();
  }


  /*
  和简单的 book test 相比
  这里 必须要 先get ssn ， 才能删除，
  因为这里有 级联关系了
   because Hibernate needs to manage the entity within a session before it can perform
  operations like deletion. The entity must be in a managed state for Hibernate to be able to track
  changes and relationship mappings properly.

   */
  @Test
  public void testDelete() {
    //3.改对象有crud操作
    Session session = sessionFactory.openSession();
    //4.开启事务
    session.beginTransaction();
    SSN ssn = session.get(SSN.class, 2);
    if(ssn != null){
      session.delete(ssn);
    }
    //5.提交事务
    session.getTransaction().commit();
    //6.关闭session
    session.close();
  }

}
