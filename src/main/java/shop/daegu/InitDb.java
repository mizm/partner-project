package shop.daegu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.daegu.domain.Client;
import shop.daegu.domain.Group;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Group group1 = createGroup("group1", 0);
            Group group2 = createGroup("group2", 1);
            Group group3 = createGroup("group3", 2);
            Group group4 = createGroup("group4", 3);

            createClient(group1, "구미-농심공장", 0);
            createClient(group2, "다솔(윤민수)", 1);
            createClient(group3, "영진직업전문학교", 2);
            createClient(group4, "좋은날", 3);
            createClient(group1, "1)경주고", 4);
            createClient(group2, "1)신라고", 5);
            createClient(group3, "1)가정편의점", 6);
            createClient(group4, "1)경주대본관", 7);
        }

        private void createClient(Group group, String name, int ord) {
            Client client = new Client(name, name + "display", group, ord);
            em.persist(client);
        }

        private Group createGroup(String name, int ord) {
            Group group = new Group(name, ord);
            em.persist(group);
            return group;
        }

    }
}
