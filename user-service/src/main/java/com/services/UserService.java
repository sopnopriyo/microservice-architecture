package com.services;

import com.entities.User;
import com.entities.value_objects.Department;
import com.entities.value_objects.ResponseTemplateVO;
import com.repos.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final RestTemplate restTemplate;

    @Autowired
    public UserService(UserRepository repository,
                       RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }


    public User save(User user) {
        return this.repository.save(user);
    }

    public User getById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public ResponseTemplateVO getUserWithDepartment(String id) {
        User user = this.getById(Long.valueOf(id));

        //Department department = restTemplate.getForObject("http://department-service/departments/" + user.getDepartmentId(), Department.class);

        return new ResponseTemplateVO(user, null);
    }

    public User getByEmail(String email) {
        return this.repository.findByEmail(email);
    }
}
