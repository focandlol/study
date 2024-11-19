package focandlol.weather.repository;

import focandlol.weather.domain.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaMemoRepositoryTest {

    @Autowired
    private JpaMemoRepository jpaMemoRepository;

    @Test
    void insertMemoTest(){

        //given
        Memo secondMemo = new Memo(10, "jpaMemo");

        //when
        jpaMemoRepository.save(secondMemo);

        //then
        List<Memo> memoList = jpaMemoRepository.findAll();
        assertTrue(memoList.size() > 0);
    }

    @Test
    void findByIdTest(){
        //given
        Memo newMemo = new Memo(11, "jpa");

        //when
        Memo savedMemo = jpaMemoRepository.save(newMemo);
        System.out.println("savedMemo.getId() = " + savedMemo.getId());

        //then
        System.out.println("sf");
        Optional<Memo> result = jpaMemoRepository.findById(savedMemo.getId());
        assertEquals("jpa",result.get().getText());
    }
}