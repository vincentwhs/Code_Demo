Log4j��������Ҫ����ɹ��ɣ���־��¼��(Loggers)�������(Appenders)����־��ʽ����(Layout)��
1.Logger������Ҫ���û������Щ��־��¼��䣬������־��Ϣ���м�������
2.Appenders : ָ������־����ӡ������̨�����ļ���
3.Layout : ������־��Ϣ����ʾ��ʽ

A��off         ��ߵȼ������ڹر�������־��¼��
B��fatal       ָ��ÿ�����صĴ����¼����ᵼ��Ӧ�ó�����˳���
C��error      ָ����Ȼ���������¼�������Ȼ��Ӱ��ϵͳ�ļ������С�
D��warm     ���������Ǳ�ڵĴ������Ρ�
E��info         һ����ڴ����ȼ����ϣ�ǿ��Ӧ�ó��������ȫ�̡�
F��debug     һ������ϸ���ȼ����ϣ��Ե���Ӧ�ó���ǳ��а�����
G��all           ��͵ȼ������ڴ�������־��¼��
5�ֳ��ü�������ΪDEBUG��INFO��WARN��ERROR��FATAL
����ʹ��4���������ȼ��Ӹߵ��ͷֱ���:1.error 2.warn 3.info 4.debug


Log4j���ֳ��õ����Ŀ�ĵ�:
a��org.apache.log4j.ConsoleAppender������־��Ϣ���������̨��
b��org.apache.log4j.FileAppender������־��Ϣ�����һ���ļ���
c��org.apache.log4j.DailyRollingFileAppender������־��Ϣ�����һ����־�ļ�������ÿ�������һ���µ���־�ļ���
d��org.apache.log4j.RollingFileAppender������־��Ϣ�����һ����־�ļ�������ָ���ļ��ĳߴ磬���ļ���С�ﵽָ���ߴ�ʱ�����Զ����ļ�������ͬʱ����һ���µ��ļ���
e��org.apache.log4j.WriteAppender������־��Ϣ������ʽ���͵�����ָ���ط���
f��org.apache.log4j.jdbc.JDBCAppender��ͨ��JDBC����־��Ϣ��������ݿ��С�


��־��ʽ����Layout�����֣�
1.HTMLLayout : ��ʽ����־���ΪHTML�����ʽ��ͼ��
2.SimpleLayout : ��һ�ַǳ��򵥵ķ�ʽ��ʽ����־���������ӡ�������ݣ�����-��Ϣ
      ����INFO - info
3.PatternLayout : ����ָ����ת��ģʽ��ʽ����־������������û��ָ���κ�ת��ģʽ����ʹ��Ĭ�ϵ�ת��ģʽ��ʽ��
4.TTCCLayout : ������־������ʱ�䡢�̡߳����ȵ���Ϣ


Log4j������
����Log4j��������ָ����root Logger��������LoggerΪ�ĸ�����Ϊ��������ЩAppender,�Լ�Ϊ��ЩAppender����Layout���ȵȡ���Ϊ����������Logger����root Logger�ĺ�����������Ƕ��̳���root Logger�����ʡ���Щ����ͨ������ϵͳ���Եķ�������ʽ����ɣ�Ҳ�����ڳ����е���XXXConfigurator.configure()��������ʽ����ɡ������¼��ַ�ʽ������Log4j��
A�����÷����ļ��ͨ���������������ļ�������Ϣ������Log4jĬ�ϵĳ�ʼ�����̽��������á�
B�����÷����ļ��ͨ��Ӧ�÷��������ô����ļ������Ϣ������һ���ض���Servlet��������á�
C���ڳ����е���BasicConfigor.configure()������
D�����÷����ļ��ͨ��������PropertyConfigurator.configure(args[])����log4j.properties�ļ�������Log4j��

�����BasicConfigurator.configure()������PropertyConfigurator.config()�����ֱ���н��ܡ�
BasicConfigurator.configure()������
��ʹ�ü򵥵ķ�������Log4j���������������ɵ������ǣ�
1����Ĭ�ϵķ�ʽ����PatternLayout����p:
  PatternLayout p = new PatternLayout("%-4r[%t]%-5p%c%x-%m%n");
2����p����ConsoleAppender����a��Ŀ����System.out����׼����豸��
 ConsoleAppender a = new CpnsoleAppender(p,ConsoleAppender.SYSTEM_OUT);
3��Ϊroot Logger����һ��ConsoleAppender p;
 rootLogger.addAppender(p);
4����rootLogger��log level����ΪDUBUG����;
 rootLogger.setLevel(Level.DEBUG);

PropertyConfigurator.configure()������
��ʹ�������������Logger����ʱ��
static Logger logger = Logger.getLogger(mycalss.class);  
���û�е���BasicConfigurator.configure()��PropertyConfigurator.configure()
��DOMConfigurator.configure()������
Log4j���Զ�����CLASSPATH����Ϊlog4j.properties�������ļ���
����Ѵ������ļ���Ϊ�������֣�����my.properties,������Ȼ�������У�
���ᱨ��������ȷ��ʼ��Log4jϵͳ����ʾ����ʱ�����ڳ����м��ϣ�
PropertyConfigurator.configure("classes/my.properties");  ���⼴�ɽ����



4��Log4j��log4j.properties�������
  ��Ȼ���Բ��������ļ������ڳ�����ʵ�����ã������ַ���������ϵͳ��������Ȼ�ǲ���ȡ�ģ��ܲ��������ļ��ĵط�һ��һ��Ҫ�������ļ���
  Log4j֧���� �ָ�ʽ�������ļ���XML��ʽ��Java��property��ʽ��
��ϸ���ü����������.txt





/////////////////////////////////////////////////////////
2.2���ڴ�����ʹ��Log4j

1.�õ���¼��

ʹ��Log4j����һ�����ǻ�ȡ��־��¼���������¼�������������־��Ϣ�����﷨Ϊ��

public static Logger getLogger( String name)

ͨ��ָ�������ֻ�ü�¼���������Ҫ�Ļ�����Ϊ������ִ���һ���µļ�¼����Nameһ��ȡ��������֣����磺

static Logger logger = Logger.getLogger ( ServerWithLog4j.class.getName () )

2.��ȡ�����ļ�

���������־��¼��֮�󣬵ڶ���������Log4j���������﷨Ϊ��

BasicConfigurator.configure ()�� �Զ����ٵ�ʹ��ȱʡLog4j������  
PropertyConfigurator.configure ( String configFilename) ����ȡʹ��Java�������ļ���д�������ļ���  
DOMConfigurator.configure ( String filename ) ����ȡXML��ʽ�������ļ���

3.�����¼��Ϣ����ʽ����־��Ϣ��

����������Ҫ����ִ����ϣ����Ϳ������ɵ�ʹ�ò�ͬ���ȼ������־��¼�����뵽�����¼��־���κεط������﷨���£�

Logger.debug ( Object message ) ;  
Logger.info ( Object message ) ;  
Logger.warn ( Object message ) ;  
Logger.error ( Object message ) ;

2.3����־����

ÿ��Logger������һ����־����log level��������������־��Ϣ���������־����Ӹߵ��ͷ�Ϊ��
A��off ��ߵȼ������ڹر�������־��¼��
B��fatal ָ��ÿ�����صĴ����¼����ᵼ��Ӧ�ó�����˳���
C��error ָ����Ȼ���������¼�������Ȼ��Ӱ��ϵͳ�ļ������С�
D��warm ���������Ǳ�ڵĴ������Ρ�
E��info һ����ڴ����ȼ����ϣ�ǿ��Ӧ�ó��������ȫ�̡�
F��debug һ������ϸ���ȼ����ϣ��Ե���Ӧ�ó���ǳ��а�����
G��all ��͵ȼ������ڴ�������־��¼��

������Щ�����Ƕ�����org.apache.log4j.Level���С�Log4jֻ����ʹ��4���������ȼ��Ӹߵ��ͷֱ���error,warn,info��debug��ͨ��ʹ����־���𣬿��Կ���Ӧ�ó�������Ӧ������־��Ϣ����������磬���ʹ��b��info������Ӧ�ó��������е���info�������־��Ϣ(��debug)�����ᱻ��ӡ������









