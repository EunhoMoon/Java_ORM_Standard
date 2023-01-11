package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
//            List<Member> memberList = em.createQuery("SELECT m FROM Member m WHERE m.name LIKE '%kim%'", Member.class)
//                    .getResultList();

//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Member> query = cb.createQuery(Member.class);
//
//            Root<Member> m = query.from(Member.class);
//            CriteriaQuery<Member> cg = query.select(m).where(cb.equal(m.get("name"), "kim"));
//            List<Member> resultList = em.createQuery(cg).getResultList();

//            Query nativeQuery = em.createNativeQuery("SELECT * FROM MEMBER WHERE name LIKE '%kim%'");
//            List resultList = nativeQuery.getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
