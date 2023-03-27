package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em =  emf.createEntityManager(); // DB 작업은 em을 이용해서 한다.

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            // JPA는 모든 데이터 변경은 tx안에서 해결해야함
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("Hello");
//            em.persist(member);

            System.out.println("::::::::::select::::::::::::::");

            Member findMember = em.find(Member.class, 1L);
            System.out.println(findMember.toString());

            System.out.println("::::::::::Update::::::::::::::");
            findMember.setName("HelloJpa"); // jpa로 가져온 객체는 관리가 되면서 수정될 경우 update가 됨

            System.out.println("::::::::::select update::::::::::::::");

            Member updateMember = em.find(Member.class, 1L);
            System.out.println(updateMember.toString());

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }

    }
}