package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Team team1 = new Team();
            team1.setName("teamB");
            em.persist(team1);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setTeam(team);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("관리자3");
            member3.setTeam(team1);
            em.persist(member3);

            em.flush();
            em.clear();

            String query = "select m from Member m join fetch m.team";
            List<Member> resultList = em.createQuery(query, Member.class)
                    .getResultList();
            resultList.stream().map(m -> m.getTeam()).forEach(System.out::println);


//            select
//            member1_.username as col_0_0_
//                    from
//            Team team0_
//            inner join
//            Member member1_
//            on

//            select
//            members1_.username as col_0_0_
//                    from
//            Team team0_
//            inner join
//            Member members1_
//            on team0_.id=members1_.TEAM_ID

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
