package jpql;

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
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamA");
            em.persist(teamB);

            Member memberA = new Member();
            memberA.setName("memberA");
            memberA.setAge(20);
            memberA.setType(MemberType.USER);
            memberA.changeTeam(teamA);
            em.persist(memberA);

            Member memberB = new Member();
            memberB.setName("memberB");
            memberB.setAge(25);
            memberB.setType(MemberType.ADMIN);
            memberB.changeTeam(teamA);
            em.persist(memberB);

            Member memberC = new Member();
            memberC.setName("memberC");
            memberC.setAge(30);
            memberC.setType(MemberType.USER);
            memberC.changeTeam(teamB);
            em.persist(memberC);

            em.createQuery("update Member m set m.age = 40")
                    .executeUpdate();

            em.clear();

            Member findMember = em.find(Member.class, memberA.getId());
            int age = findMember.getAge();
            System.out.println("age = " + age);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
