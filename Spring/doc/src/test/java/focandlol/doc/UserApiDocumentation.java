package focandlol.doc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class UserApiDocumentation {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetUser() throws Exception {
    mockMvc.perform(get("/{id}", 1L)) // 경로와 경로 변수 지정
        .andExpect(status().isOk())
        .andDo(document("get-user",
            pathParameters( // 경로 변수 문서화
                parameterWithName("id").description("The ID of the user to retrieve")
            ),
            responseFields( // 응답 필드 문서화
                fieldWithPath("id").description("The unique ID of the user"),
                fieldWithPath("phoneNumber").description("The phoneNumber of the user"),
                fieldWithPath("email").description("The email of the user"),
                fieldWithPath("account").description("The account of the user"),
                fieldWithPath("createdAt").description("The date the user was created"),
                fieldWithPath("updatedAt").description("The date the user was updated")
            )
        ));
  }
}
