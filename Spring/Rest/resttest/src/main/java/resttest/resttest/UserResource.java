package resttest.resttest;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.List;

public class UserResource extends EntityModel<User> {

    public UserResource(User user, Link... links) {
        super(user, List.of(links));
    }
}
