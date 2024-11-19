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
class JdbcMemoRepositoryTest {

    @Autowired
    JdbcMemoRepository repository;
    @Autowired
    private JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest(){

        //given
        Memo newMemo = new Memo(2, "insertMemoTest");

        //when
        repository.save(newMemo);

        //then
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(),"insertMemoTest");

    }

    @Test
    void findAllMemoTest(){
        List<Memo> memoList = repository.findAll();
        System.out.println("memoList = " + memoList);

        assertNotNull(memoList);
    }

}