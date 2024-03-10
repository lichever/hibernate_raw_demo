package org.example.entity.oneToMany;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class School {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "school_name")
  private String schoolName;

  @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
  private Set<Student> students;

  //双向的
  //如果用set  一定要 重写 set 里面元素的  equals 和 hashcode
  public void addStudent(Student s){
    s.setSchool(this);
    students.add(s);
  }

  public void removeStudent(Student s){
    students.remove(s);
    s.setSchool(null);
  }

}
