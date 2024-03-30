package kkm.rest.restservice.repository;

import kkm.rest.restservice.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
