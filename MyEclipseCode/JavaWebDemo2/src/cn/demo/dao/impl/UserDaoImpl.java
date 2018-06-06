package cn.demo.dao.impl;

import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.demo.dao.UserDao;
import cn.demo.domain.User;
import cn.demo.utils.XmlUtils;

public class UserDaoImpl implements UserDao {

	/* (non-Javadoc)
	 * @see cn.demo.dao.impl.UserDao#add(cn.demo.domain.User)
	 */
	@Override
	public void add(User user){
		try {
			Document doc = XmlUtils.getDocument();
			
			Element root = doc.getRootElement();
			
			Element user_tag = root.addElement("user");
			user_tag.setAttributeValue("id", user.getId());
			user_tag.setAttributeValue("username", user.getUsername());
			user_tag.setAttributeValue("password", user.getPassword());
			user_tag.setAttributeValue("email", user.getEmail());
			user_tag.setAttributeValue("birthday", user.getBirthday() == null ? "" : user.getBirthday().toLocaleString());
			user_tag.setAttributeValue("nickname", user.getNickname());
			
			XmlUtils.write2Xml(doc);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.demo.dao.impl.UserDao#find(java.lang.String, java.lang.String)
	 */
	@Override
	public User find(String username, String password){
		
		try {
			Document document = XmlUtils.getDocument();
			
			Element element = (Element) document.selectSingleNode("//user[@username='"+username+"' and @password='"+password+"']");
			if (element == null) {
				return null;
			}
			
			User user = new User();
			
			String date = element.attributeValue("birthday");
			if (date == null || date.equals("")) {
				user.setBirthday(null);
			}else {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				user.setBirthday(df.parse(date));
			}
			
			user.setEmail(element.attributeValue("email"));
			user.setId(element.attributeValue("id"));
			user.setUsername(element.attributeValue("name"));
			user.setPassword(element.attributeValue("password"));
			user.setNickname(element.attributeValue("nickname"));
			
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see cn.demo.dao.impl.UserDao#find(java.lang.String)
	 */
	@Override
	public boolean find(String username){
		
		Document document;
		try {
			document = XmlUtils.getDocument();

			Element e = (Element) document.selectSingleNode("//user[@username='"+username+"']");
			
			if (e != null) {
				return true;
			}
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		
		return false;
	}
	
	
}
