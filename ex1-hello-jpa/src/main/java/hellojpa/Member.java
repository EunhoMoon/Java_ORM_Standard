package hellojpa;

import javax.persistence.*;
import java.util.Date;

/**
 * 요구사항 추가
 * - 회원은 일반 회원과 관리자로 구분
 * - 회원 가입일과 수정일이 존재
 * - 회원을 설명할 수 있는 필드가 있어야 한다. 해당 필드는 길이 제한 X
 */
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(unique = true, length = 10)
    private String name;
    private int age;
//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
