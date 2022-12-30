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

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
