package org.example.entity.oneToOne;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/*

this is parent side

 */

@Data
@Entity
@Table(name = "ssn")
public class SSN {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  // has leading zero, so use string
  private String number;


  //  表示ssn的实体在SSN表
  @OneToOne(
      mappedBy = "ssn",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private Person person;

  //add, remove 操作在 parent 这一边
  // one to one 还可以 2边 都cascade，不用这些utility 方法
  // 但 to many 就需要了， 因为 从 many 那一侧 加 one 这一侧 级联容易出问题
  // 比如 删除一个 many表的一个玩意，结果 主表的 row 都没了。。。
  // 在test 的时候 就只需 save 主表的object就行了，从表的会自动save了
  // 注意： 这里的 add remove 要双向操作 不然关联不上
  public void addPerson(Person p){
    p.setSsn(this);
    person = p;
  }

  public void removePerson(){
    if(person != null){
      person.setSsn(null);
      person = null;
    }

  }

}
