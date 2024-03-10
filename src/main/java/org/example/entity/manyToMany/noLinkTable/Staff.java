package org.example.entity.manyToMany.noLinkTable;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
public class Staff {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // or another strategy
  @Column(name = "staff_pk")
  private Long id;  // Surrogate key

  @NaturalId
  private String stId;

  private String name;

  @ManyToMany
  @JoinTable(name = "course_staff",
      joinColumns = {@JoinColumn(name = "staff_pk")},
      inverseJoinColumns = {@JoinColumn(name = "course_pk")})
  private List<Course> courses;

  public Staff() {
    courses = new ArrayList<>();
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
    Staff staff = (Staff) o;
    return stId.equals(staff.stId) ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(stId);
  }


  @Override
  public String toString() {
    return "Staff{" +
        "id=" + id +
        ", stId='" + stId + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
