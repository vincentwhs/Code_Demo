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
 * ���ڴ���ʵ�����ӳ�
 */
public class DataSourcePoolProxy implements DataSource {
	//���ϱ���Connection
	private static LinkedList<Connection> list =  new LinkedList<Connection>();
	
	//��ʼ������
	static{
		InputStream in = DataSourcePool.class.getClassLoader().getResourceAsStream("db.properties");
		Properties prop = new Properties();
		try{
			prop.load(in);
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			//ע������
			Class.forName(driver);
			//ͨ��DriverManager��ȡһ��Connection�����뼯��
			for(int i=0;i<10;i++){
				Connection conn = DriverManager.getConnection(url, user, password);
				//���뼯��
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
			 * ʹ��Mysql 5.0����
			 * Proxy��:
			 public static Object newProxyInstance(ClassLoader loader,
                                      Class<?>[] interfaces,
                                      InvocationHandler h)
                               throws IllegalArgumentException
              ClassLoader loader----����ClassLoader
              Class<?>[] interfaces ----ͨ��conn.getClass().getInterfaces()��ȡ
              InvocationHandler h---Ӧ�ó������interfaces�з���ʱ��ʹ��handler����;��Ҫ�Լ�ʵ��
			 */
			Object object = Proxy.newProxyInstance(DataSourcePoolProxy.class.getClassLoader(),
					conn.getClass().getInterfaces(), 
					new InvocationHandler() {
						
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) 
								throws Throwable {
							//method:Ӧ�ó�����ô�������е��ĸ�����
							//����close����
							if (method.getName().equals("close")) {
								//����close����,��Ҫconnecion����
								System.out.println("��̬�������ӻ���:" + conn);
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
