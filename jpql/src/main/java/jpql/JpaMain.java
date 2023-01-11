package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {

            for (int i = 1; i <= 100; i++) {
                Member member = new Member();
                member.setName("member" + i);
                member.setAge(i);

                if (i % 2 == 0) {
                    Team team = new Team();
                    team.setName("team" + i);
                    em.persist(team);
                    member.changeTeam(team);
                }
                em.persist(member);
            }

//            List<Member> resultList = em.createQuery("select m from Member m  where m.name = :username", Member.class)
//                    .setParameter("username", "memberA")
//                    .getResultList();
//            List<MemberDTO> resultList1 = em.createQuery("select new  jpql.MemberDTO(m.name, m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//
//            for (MemberDTO memberDTO : resultList1) {
//                System.out.println("memberDTO = name : " + memberDTO.getName() +  " / age : " + memberDTO.getAge());
//            }

            List<Member> resultList = em.createQuery("SELECT m FROM Member m left JOIN m.team t ORDER BY m.age DESC", Member.class)
                    .setFirstResult(10)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("size = " + resultList.size());

            for (Member findMember : resultList) {
                System.out.println("findMember = " + findMember);
                System.out.println("team = " + findMember.getTeam());
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
