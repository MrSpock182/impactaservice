package br.com.studiotrek.impactaservice.login;

import br.com.studiotrek.impactaservice.login.model.Login;
import br.com.studiotrek.impactaservice.login.regra.LoginRegra;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestLogin {

    @Test
    public void testLogin() {
        try{
            LoginRegra loginRegra = new LoginRegra(new Login());
            Login login = loginRegra.request("1410264", "1693");
            assertNotNull(login);
        } catch (IllegalAccessException ex) {
            assertTrue(false);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

}
