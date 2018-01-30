import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.mccarl.account.api.Application;
import ru.mccarl.account.api.entity.Account;
import ru.mccarl.account.api.repository.AccountRepository;

/**
 * Created by vrudometkin on 29/01/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@ContextConfiguration
public class JTest extends Specifications{

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private AccountRepository accountRepository;

    @Before
    public void setup() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Account account = new Account();
        account.setName("test");
        Mockito.when(accountRepository.findOne(Mockito.anyString())).thenReturn(account);
    }

    @Test
    public void getAccount() throws Exception{
        Account account = new Account();
        account.setName("test");
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
