package ine.wmd.Security;

import java.io.IOException;

import javax.security.auth.callback.*;

public class JaaSCallBackHandler implements CallbackHandler {

	private String name;
	private String password;
	
	public JaaSCallBackHandler(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof NameCallback) {
                    NameCallback nameCallback = (NameCallback) callbacks[i];
                    nameCallback.setName(name);
            } else if (callbacks[i] instanceof PasswordCallback) {
                    PasswordCallback passwordCallback = (PasswordCallback) callbacks[i];
                    passwordCallback.setPassword(password.toCharArray());
            } else {
                    throw new UnsupportedCallbackException(callbacks[i], "The submitted Callback is unsupported");
            }
    }
	}
	


}
