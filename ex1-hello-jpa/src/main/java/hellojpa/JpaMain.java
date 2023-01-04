package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Team team = new Team();
            team.setName("New Team");

            em.persist(team);

            Member member = new Member();
            member.setName("memberA");
            member.setAge(20);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
//            System.out.println(findMember instanceof Member);
//            Hibernate.initialize(findMember);

            System.out.println("Team Class" + findMember.getTeam().getClass());
            System.out.println("==========");
            findMember.getTeam().getName();
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(findMember));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
