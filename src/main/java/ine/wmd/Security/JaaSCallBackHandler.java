package ine.wmd.Security;

import java.io.IOException;

import javax.security.auth.callback.*;

import javax.security.auth.login.*;


public class JaaSCallBackHandler implements Callback, CallbackHandler {

	private String login;
	private String pswd;
	
	public JaaSCallBackHandler(String login, String pswd) {

		this.login = login;
		this.pswd = pswd;
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		
		
		for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof NameCallback) {
                    NameCallback loginCallback = (NameCallback) callbacks[i];
                    
                    loginCallback.setName(login);
                    
            } else if (callbacks[i] instanceof PasswordCallback) {
                    PasswordCallback passwordCallback = (PasswordCallback) callbacks[i];
                    
                    passwordCallback.setPassword(pswd.toCharArray());
            } else {
                    throw new UnsupportedCallbackException(callbacks[i], "The submitted Callback is unsupported");
            }
    }
}
			
			
	}
	
