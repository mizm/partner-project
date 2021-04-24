package shop.daegu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.daegu.domain.Group;
import shop.daegu.domain.Client;
import shop.daegu.dto.client.CleintGroupDto;
import shop.daegu.dto.client.ClientForm;
import shop.daegu.dto.client.ClientSearchCondition;
import shop.daegu.repository.Client.ClientRepository;
import shop.daegu.repository.GroupRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final GroupRepository groupRepository;

    @Transactional
    public Long add(ClientForm clientForm) {
        Long groupId = clientForm.getGroupId();
        Optional<Group> optGroup = groupRepository.findById(groupId);
        Group group = optGroup.orElseThrow(() -> new IllegalArgumentException("요청하신 그룹이 없습니다."));
        Client client = new Client(clientForm, group);
        Client saveClient = clientRepository.save(client);
        return saveClient.getId();
    }

    public List<CleintGroupDto> findAllBySearchCondition(ClientSearchCondition cond) {
        List<Client> clients = clientRepository.findAllByQuerydsl(cond);
        return clients.stream()
                .map(CleintGroupDto::new)
                .collect(Collectors.toList());
    }

    public CleintGroupDto findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 ID가 없습니다."));

        return new CleintGroupDto(client);
    }

    @Transactional
    public void update(ClientForm clientForm) {
        Long groupId = clientForm.getGroupId();
        Optional<Group> optGroup = groupRepository.findById(groupId);
        Group group = optGroup.orElseThrow(() -> new IllegalArgumentException("요청하신 그룹이 없습니다."));
        Client client = clientRepository.findById(clientForm.getId())
                .orElseThrow(() -> new IllegalArgumentException("요청하신 거래처가 없습니다."));
        client.change(clientForm,group);
    }

    @Transactional
    public void remove(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 거래처가 없습니다."));
        clientRepository.delete(client);
    }
}
