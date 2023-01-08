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
            Member member = new Member();

            member.setName("memberA");
            member.setHomeAddress(new Address("city1", "street", "12345"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("삽겹살");

            member.getAddressHistory().add(new Address("old1", "street", "12345"));
            member.getAddressHistory().add(new Address("old2", "street", "12345"));

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
