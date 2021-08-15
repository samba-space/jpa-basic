import hellojpa.Member;
import hellojpa.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {


            Member member = new Member();
            member.setUsername("hello");
            System.out.println("member = " + member.getId());
            if(Member.class == member.getClass()){

            }
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("========");
            System.out.println("member = " + member.getId());
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember = " + member.getUsername());

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
