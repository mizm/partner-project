package shop.daegu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import shop.daegu.domain.Client;
import shop.daegu.domain.Group;
import shop.daegu.dto.client.ClientForm;
import shop.daegu.dto.client.ClientSearchCondition;
import shop.daegu.repository.Client.ClientRepository;
import shop.daegu.repository.GroupRepository;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ClientServiceTest {

    @Autowired ClientService clientService;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    EntityManager em;

    private Long groupId;
    @BeforeEach
    void beforEach() {
        Group group1 = new Group("test",1);
        Group group2 = new Group("test1",2);
        Group group3 = new Group("test2",3);
        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        groupId = group3.getId();
        Client client1 = new Client("client1", "dc1",group1, 1);
        Client client2 = new Client("client2","dc1",group2, 2);
        Client client3 = new Client("client3","dc1",group3, 3);
        Client client4 = new Client("client4","dc1",group1, 3);
        Client client5 = new Client("client5","dc1",group2, 3);
        Client client6 = new Client("client6","dc1",group3, 3);
        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);
        clientRepository.save(client4);
        clientRepository.save(client5);
        clientRepository.save(client6);
    }

    @Test
    @Commit
    void addTest() {
        //given
        ClientForm clientForm = new ClientForm();
        clientForm.setName("테스트");
        clientForm.setOrd(1);
        clientForm.setGroupId(groupId);

        //when
        Long client_id = clientService.add(clientForm);

        //then
        Client findClient = clientRepository.findById(client_id).get();

        assertThat(findClient.getName()).isEqualTo("테스트");
        assertThat(findClient.getOrd()).isEqualTo(1);
        assertThat(findClient.getGroup().getName()).isEqualTo("test");
    }
    
    @Test
    @Commit
    void findTest() {
        ClientSearchCondition cond = new ClientSearchCondition();
        cond.setClientName("client1");
        List<Client> result = clientRepository.findAllByQuerydsl(cond);
        assertThat(result.size()).isEqualTo(1);
        cond.setGroupName("test1");
        cond.setClientName(null);
        result = clientRepository.findAllByQuerydsl(cond);
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void updateTest() {
        //given
        List<Client> clients = clientRepository.findAll();
        ClientForm clientForm = new ClientForm();
        clientForm.setName("변경테스트");
        clientForm.setOrd(199);
        clientForm.setGroupId(groupId);
        clientForm.setId(clients.get(0).getId());

        //when
        clientService.update(clientForm);
        em.flush();
        em.clear();
        //then
        Client client = clientRepository.findById(clients.get(0).getId()).get();
        assertThat(client.getOrd()).isEqualTo(199);
        assertThat(client.getGroup().getId()).isEqualTo(groupId);
        assertThat(client.getName()).isEqualTo("변경테스트");
    }
}