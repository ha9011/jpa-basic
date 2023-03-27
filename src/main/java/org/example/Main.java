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

            //1.  비영속성
            Member member = new Member();
            member.setId(2L);
            member.setName("Hello2");

            //2. 영속성 ( 아직 쿼리 실행은 아님, 트랜잭션이 커밋할떄 날라감)
            // 1차 캐시에 저장 ( map형태로 (id, id에 해당하는 member 객체)
            System.out.println("=== before ===");
            em.persist(member);
            System.out.println("=== after ===");

            //2-2 같은 트랜잭션에서 같은 pk  값을 조회 할 경우,
            // == 비교를 동일성 보장해줌 ( 같은 거 조회 할 경우 )

            //2-3 select시 1차 캐시에서 조회
            // 만약에 1차 캐시에 없을 경우, DB에서 직접 조회하고
            // 1차 캐시에 다시 저장
            // 그리고 반환
            // 그 후 다시 조회 할 경우, 1차 캐시에서 조회

            //3. 등록
            //영속 컨테스트는 '쓰기 지연 SQL 저장소'에 격납
            // flush 때 db로 가고
            // commit 때 저장

            //4 수정(변경 감지)
            // 수정이 되면(setXXX), 1차캐시에 있는 값을 스냅샷을 찍고
            // 스냅샷과 비교하여 차이가 있을 때, '쓰기 지연 SQL 저장소' 애 저장해 둠
            // 따라서  update 라는 메소드는 없음. 그 커밋시점에 적용

            // 5. 삭제 .remove
            // 커밋시점에 적용

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }

    }
}