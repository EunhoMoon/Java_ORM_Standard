package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // 등록
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("helloB");

//            em.persist(member);

            // 조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

            // 수정
//            findMember.setName("HelloJPA"); // 트랜잭션이 커밋되기 전에 변경된 값이 있는지 체크한 후 Update

            // 삭제
//            em.remove(findMember);

            // JPQL
            List<Member> result = em.createQuery("SELECT m FROM Member AS m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

//            for (Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member1 = new Member();
            member1.setName("member1");
            member1.changeTeam(team);
            Member member2 = new Member();
            member2.setName("member2");
            member2.changeTeam(team);
            em.persist(member1);
            em.persist(member2);

//            em.flush();
//            em.clear();
//            team.getMembers().add(member1);
//            team.getMembers().add(member2);

            Member findMember = em.find(Member.class, member1.getId());
            List<Member> members = findMember.getTeam().getMembers();
            for (Member m : members) {
                System.out.println("member=" + m.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
