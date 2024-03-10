import java.io.File;
import java.util.HashSet;
import org.example.entity.oneToMany.School;
import org.example.entity.oneToMany.Student;
import org.example.entity.oneToOne.SSN;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class OneToManyTest {

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

    School school = new School();
    school.setSchoolName("MIT");
    school.setStudents(new HashSet<>());


    Student student1 = new Student();
    Student student2 = new Student();
    student1.setStuId(1);
    student2.setStuId(2);
    student1.setName("John");
    student2.setName("Tom");

    school.addStudent(student1);
    school.addStudent(student2);

    session.save(school);
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
    var school = session.get(School.class, 0);
    if(school != null){
      session.delete(school);
    }
    //5.提交事务
    session.getTransaction().commit();
    //6.关闭session
    session.close();
  }

}
