package kkm.rest.restservice.controller;

import kkm.rest.restservice.bean.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class UserResource extends EntityModel<User> {

    public UserResource(User user, Link... links) {
        super(user, List.of(links));
    }
}
