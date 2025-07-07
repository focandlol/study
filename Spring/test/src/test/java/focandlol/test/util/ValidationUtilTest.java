package focandlol.test.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidationUtilTest {

  @Test
  @DisplayName("이메일 테스트 성공")
  void isEmail_test1(){
    //given
    String email = "test@gmail.com";

    //when
    boolean result = ValidationUtil.isEmail(email);

    //then
    assertTrue(result);
  }

  @Test
  @DisplayName("이메일 테스트 실패")
  void isEmail_test2(){
    //given
    String email = "test.com";

    //when
    boolean result = ValidationUtil.isEmail(email);

    //then
    assertFalse(result);
  }

}