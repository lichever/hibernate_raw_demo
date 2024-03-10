package org.example.entity.manyToMany.noLinkTable;

import com.sun.jdi.ArrayReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
@Getter
@Setter
@Entity
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // or another strategy
  @Column(name = "course_pk")
  private Long id;  // Surrogate key

  @NaturalId
  private String courseId;

  private String name;

  @ManyToMany(mappedBy = "courses" ,cascade = CascadeType.ALL)
  private List<Staff> people;


  public Course() {
    people = new ArrayList<>();
  }


  //utility functions
  public void addStaff(Staff staff) {
    people.add(staff);
    staff.getCourses().add(this);
  }

  public void removeStaff(Staff staff) {
    people.remove(staff);
    staff.getCourses().remove(this);
  }



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
    Course c = (Course) o;
    return courseId.equals(c.courseId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId);
  }


  @Override
  public String toString() {
    return "Course{" +
        "id=" + id +
        ", courseId='" + courseId + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
