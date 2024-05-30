package generic.test.ex5.map2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MemberRepository {
    private Map<String, Member> memberMap = new HashMap<>();
    public void save(Member member) {
        // 코드 작성
        memberMap.put(member.getId(),member);
    }
    public void remove(String id) {
        // 코드 작성
        memberMap.remove(id);
    }
    public Member findById(String id) {
        // 코드 작성
        return memberMap.get(id);
    }
    public Member findByName(String name) {
        // 코드 작성
        Set<Map.Entry<String, Member>> entries = memberMap.entrySet();
        for (Map.Entry<String, Member> entry : entries) {
            if (entry.getValue().getName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
