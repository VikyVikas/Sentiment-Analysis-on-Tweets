package com.mycompany.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;

public class Connect
{
	 
	  static Connection getConnection() throws NamingException, SQLException {
		    InitialContext initCtx = createContext();
		    String jndiName = "HrDS";
		    ConnectionPoolDataSource dataSource = (ConnectionPoolDataSource) initCtx.lookup(jndiName);
		    PooledConnection pooledConnection = dataSource.getPooledConnection();
		    return pooledConnection.getConnection(); // Obtain connection from pool
		  }

		  private static InitialContext createContext() throws NamingException {
		    Properties env = new Properties();
		    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
		    env.put(Context.PROVIDER_URL, "jdbc:mysql://localhost:3306");
		    InitialContext context = new InitialContext(env);
		    return context;
		  }
}
