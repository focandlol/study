package collection.set.member;

import java.util.Objects;

public class MemberNoHashNoEquals {

    private String id;

    public MemberNoHashNoEquals(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                '}';
    }
}
