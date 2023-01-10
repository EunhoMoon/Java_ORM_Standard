package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

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

            member.getAddressHistory().add(new AddressEntity("old1", "street", "12345"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "12345"));

            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
//            List<Address> addressHistory = findMember.getAddressHistory();
//            Address oldAddr = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("new city", "street", "12345"));
//            addressHistory.add(oldAddr);
//            addressHistory.remove(new Address("old1", "street", "12345"));
//            addressHistory.add(new Address("old city", "street", "12345"));
//
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("비빔밥");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
