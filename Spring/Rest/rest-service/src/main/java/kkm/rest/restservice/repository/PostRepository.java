package kkm.rest.restservice.repository;

import kkm.rest.restservice.bean.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
