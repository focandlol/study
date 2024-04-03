package resttest.resttest;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> kkm(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        /*EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).kkm());
        entityModel.add(linTo.withRel("all-users"));

        return entityModel;*/

        UserResource userResource = new UserResource(user);
        userResource.add(linkTo(methodOn(this.getClass()).kkm()).withRel("all-users"));
        return userResource;
    }

}
