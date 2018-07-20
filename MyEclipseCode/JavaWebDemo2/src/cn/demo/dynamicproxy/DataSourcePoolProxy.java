package cn.demo.dynamicproxy;

import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

/*
 * 基于代理实现连接池
 */
public class DataSourcePoolProxy implements DataSource {
	//集合保存Connection
	private static LinkedList<Connection> list =  new LinkedList<Connection>();
	
	//初始化集合
	static{
		InputStream in = DataSourcePool.class.getClassLoader().getResourceAsStream("db.properties");
		Properties prop = new Properties();
		try{
			prop.load(in);
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			//注册驱动
			Class.forName(driver);
			//通过DriverManager获取一批Connection，存入集合
			for(int i=0;i<10;i++){
				Connection conn = DriverManager.getConnection(url, user, password);
				//加入集合
				list.add(conn);
			}
			
		}catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		if (list.size() > 0) {
			final Connection conn = list.removeFirst();
			
			/*
			 * 使用Mysql 5.0驱动
			 * Proxy类:
			 public static Object newProxyInstance(ClassLoader loader,
                                      Class<?>[] interfaces,
                                      InvocationHandler h)
                               throws IllegalArgumentException
              ClassLoader loader----该类ClassLoader
              Class<?>[] interfaces ----通过conn.getClass().getInterfaces()获取
              InvocationHandler h---应用程序调用interfaces中方法时，使用handler处理;需要自己实现
			 */
			Object object = Proxy.newProxyInstance(DataSourcePoolProxy.class.getClassLoader(),
					conn.getClass().getInterfaces(), 
					new InvocationHandler() {
						
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) 
								throws Throwable {
							//method:应用程序调用代理对象中的哪个方法
							//关心close方法
							if (method.getName().equals("close")) {
								//调用close方法,需要connecion还池
								System.out.println("动态代理，连接还池:" + conn);
								list.add(conn);
							}else {
								method.invoke(conn, args);
							}
							
							return null;
						}
					});
			
			//((Connection) object).
			return (Connection) object;
		}else {
			throw new RuntimeException();
		}
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
