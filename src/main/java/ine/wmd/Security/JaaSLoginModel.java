package ine.wmd.Security;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class JaaSLoginModel implements LoginModule {

	private CallbackHandler callbackHandler;
	private Boolean succeeded=false;
	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		return succeeded;
	}

	@Override
	public void initialize(Subject arg0, CallbackHandler callbackHandler, Map<String, ?> arg2, Map<String, ?> arg3) {
		this.callbackHandler = callbackHandler;
		succeeded=false;
	}

	@Override
	public boolean login() throws LoginException {
		if (callbackHandler == null) {
			throw new LoginException("Oops, callbackHandler is null!");
		}

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("name:");
		callbacks[1] = new PasswordCallback("password:", false);
		
		try {
			callbackHandler.handle(callbacks);
			System.out.println("  " +callbacks[0]);
		} catch (IOException e) {
			throw new LoginException("IOException calling handle on callbackHandler");
		} catch (UnsupportedCallbackException e) {
			throw new LoginException("UnsupportedCallbackException calling handle on callbackHandler");
		}

		NameCallback nameCallback = (NameCallback) callbacks[0];
		PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];

		String name = nameCallback.getName();
		String password = new String(passwordCallback.getPassword());

		// don't ever do this in a real application!
		if ("test".equals(name) && "test".equals(password)) {
			succeeded = true;
			return succeeded;
		} else {
			succeeded = false;
			throw new FailedLoginException("Login failed! You may not log in.");
		}
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

}
