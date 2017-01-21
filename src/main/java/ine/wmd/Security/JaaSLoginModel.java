package ine.wmd.Security;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class JaaSLoginModel implements LoginModule {

	
	    private CallbackHandler callbackHandler;
	    private boolean succeeded = false;
	    
	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	  public void initialize(Subject subject,
              CallbackHandler callbackHandler,
                    Map<java.lang.String, ?> sharedState,
                    Map<java.lang.String, ?> options) {


   this.callbackHandler = callbackHandler;
   succeeded = false;

 
	}

	@Override
	public boolean login() throws LoginException {
		
		   if (callbackHandler == null) {
               throw new LoginException("Oops, callbackHandler is null!");
       }

       Callback[] callbacks = new Callback[2];
       callbacks[0] = new NameCallback("login");
       callbacks[1] = new PasswordCallback("pswd", false);

       try {
               callbackHandler.handle(callbacks);
       } catch (IOException e) {
               throw new LoginException("IOException calling handle on callbackHandler");
       } catch (UnsupportedCallbackException e) {
               throw new LoginException("UnsupportedCallbackException calling handle on callbackHandler");
       }

       NameCallback loginCallback = (NameCallback) callbacks[0];
       PasswordCallback pswdCallback = (PasswordCallback) callbacks[1];

       String login = loginCallback.getName();
       String password = new String(pswdCallback.getPassword());

 //don't ever do this in a real application!
       if ("ghani".equals(login) && "ghani".equals(password)) {
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
