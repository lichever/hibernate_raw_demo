package org.example.entity.oneToMany;


import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Student {
  @Id
  private int stuId;

  private String name;

  @ManyToOne
  @JoinColumn(name = "school_id_fk")
  private School school;


  //override equals and hashcode
  //小心循环引用
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return stuId == student.stuId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(stuId);
  }
}
