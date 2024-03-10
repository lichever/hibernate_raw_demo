import java.io.File;
import org.example.entity.manyToMany.noLinkTable.Course;
import org.example.entity.manyToMany.noLinkTable.Staff;
import org.example.entity.oneToMany.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ManyToManyWithoutLinkTableTest {
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

    Course c1 = new Course();
    Course c2 = new Course();

    c1.setCourseId("CS5610");
    c2.setCourseId("CS6620");
    c1.setName("Web Development");
    c2.setName("Web Security");

    Staff staff1 = new Staff();
    Staff staff2 = new Staff();
    staff1.setStId("A001");
    staff2.setStId("A002");
    staff1.setName("John");
    staff2.setName("Tom");

    c1.addStaff(staff1);
    c1.addStaff(staff2);
    c2.addStaff(staff1);
    session.save(staff1);
    session.save(staff2);
    session.save(c1);
    session.save(c2);

    //5.提交事务
    session.getTransaction().commit();
    //6.关闭session
    session.close();
  }


  //todo: 从另外一边删除试试
  @Test
  public void testDelete() {
    //3.改对象有crud操作
    Session session = sessionFactory.openSession();
    //4.开启事务
    session.beginTransaction();
    var c2 = session.get(Course.class, 2L); //must long

    System.out.println("=======================  c2 ============   " + c2);

    //可以写成 Course 的 utility 函数
    c2.getPeople().forEach(staff -> {
      System.out.println("staff = " + staff);
      staff.getCourses().remove(c2);
    });
    c2.getPeople().clear();

    if(c2 != null){
      session.delete(c2);
    }
    //5.提交事务
    session.getTransaction().commit();
    //6.关闭session
    session.close();
  }

}
