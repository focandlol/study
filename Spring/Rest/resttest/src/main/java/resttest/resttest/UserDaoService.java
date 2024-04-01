package resttest.resttest;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList();

    private static int userCount = 3;

    static{
        users.add(new User(1,"kkm1",new Date()));
        users.add(new User(2,"kkm2",new Date()));
        users.add(new User(3,"kkm3",new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(++userCount);
        }

        if(user.getJoinDate() == null){
            user.setJoinDate(new Date());
        }
        users.add(user);

        return user;

    }

    public User findOne(int id){
        for (User user : users) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id){
        for (User user : users) {
            if(user.getId() == id){
                users.remove(user);
                return user;
            }
        }
        return null;
    }
}
