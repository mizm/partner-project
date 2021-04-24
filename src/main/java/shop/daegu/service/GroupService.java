package shop.daegu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.daegu.domain.Group;
import shop.daegu.dto.group.GroupForm;
import shop.daegu.repository.GroupRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    @Transactional
    public Group add(GroupForm form) {
        Group group = new Group(form);
        return groupRepository.save(group);
    }

    @Transactional
    public void delete(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 거래처가 없습니다."));;
        groupRepository.delete(group);
    }
}
