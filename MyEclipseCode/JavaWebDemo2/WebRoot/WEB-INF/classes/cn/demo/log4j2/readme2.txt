log4j2�����log4j 1.x������̥���ǵı仯����������Ƶ������ж��߳���10������log4j 1.x��logback�ĸ���������
�����õ��������־�����ڲ���ܹ��ĸ���������õȡ�����Ѿ�����log4j 1.x��ʹ��log4j2���Ƿǳ��򵥵ġ�


<?xml version="1.0" encoding="UTF-8"?>  
<Configuration status="WARN">  
    <Appenders>  
        <Console name="Console" target="SYSTEM_OUT">  
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />  
        </Console>  
    </Appenders>  
    <Loggers>  
        <Root level="error">  
            <AppenderRef ref="Console" />  
        </Root>  
    </Loggers>  
</Configuration>  
����������ӵ������ļ�log4j2.xml����ConfigurationΪ���ڵ㣬��һ��status���ԣ�������Ա�ʾlog4j2�������־��Ϣ��ӡ����
�����status��ΪTRACE��ִ�в��Դ��룬���Կ�������̨�д�ӡ��һЩlog4j���ز������װlogger�ȵ�����Ϣ��

������Appender���ã�Appender�������Ϊ��־�����Ŀ�ĵأ�����������һ������ΪConsole��Appender��Ҳ�������������̨��
Console�ڵ��е�PatternLayout�����������־ʱ�ĸ�ʽ��
%d{HH:mm:ss.SSS} ��ʾ����������ʱ��
%t �����ǰ�߳�����
%-5level �����־����-5��ʾ����벢�ҹ̶����5���ַ�������������ұ߲�0
%logger ���logger���ƣ���ΪRoot Loggerû�����ƣ�����û�����
%msg ��־�ı�
%n ����
�������õ�ռλ���У�
%F ������ڵ����ļ�������Client.java
%L ����к�
%M ������ڷ�����
%l  ���������ڵ�����, �������������������ļ���������
�����Logger�����ã�����ֻ������һ��Root Logger��


2 �Զ���Logger--------------------------------------------------------------
Logger logger = LogManager.getLogger("mylog");  
logger�ӻ�ȡRoot Logger��Ϊ���Ի��һ������Ϊmylog��Logger���������ļ���һ�����ú����ҵ���Root Logger������ִ�н����ͬ��
�����޸������ļ�
<Configuration status="WARN" monitorInterval="300">  
    <Appenders>  
        <Console name="Console" target="SYSTEM_OUT">  
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />  
        </Console>  
    </Appenders>  
    <Loggers>  
        <Logger name="mylog" level="trace" additivity="false">  
        <AppenderRef ref="Console" />  
    </Logger>  
        <Root level="error">  
            <AppenderRef ref="Console" />  
        </Root>  
    </Loggers>  
</Configuration>  
�ٴ�ִ�в��Դ��룬��һ��log4j2�ܸ��˵��ҵ�������Ϊmylog�����ã�����ʹ�������ð�level��Ϊtrace��ȫ������Ϣ������ˡ�
additivity="false"��ʾ�ڸ�logger���������־���������쵽����logger�����������Ϊtrue��������쵽Root Logger����ѭRoot Logger������Ҳ���һ�Ρ�
ע����ڵ�������һ��monitorInterval���ԣ�������ÿ��300�����¶�ȡ�����ļ������Բ�����Ӧ�õ�������޸����ã����Ǻܺ��õĹ��ܡ�



3 �Զ���Appender-----------------------------------------------------------------
�޸������ļ������һ���ļ����͵�Appender�����Ұ�mylog��AppenderRef��Ϊ�¼ӵ�Appender
<Configuration status="WARN" monitorInterval="300">  
    <Appenders>  
        <Console name="Console" target="SYSTEM_OUT">  
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />  
        </Console>  
        <File name="MyFile" fileName="D:/logs/app.log">  
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />  
        </File>  
    </Appenders>  
    <Loggers>  
        <Logger name="mylog" level="trace" additivity="true">  
            <AppenderRef ref="MyFile" />  
        </Logger>  
        <Root level="error">  
            <AppenderRef ref="Console" />  
        </Root>  
    </Loggers>  
</Configuration>  
ִ�в��鿴����̨��D:/logs/app.log��������



4 ʵ��������-------------------------------------------------------------------------
��������һ����ʱ����ļ���С������RollingRandomAccessFile Appender���������ǹ�����������ֻ�����ֳ������RollingFileAppender�кܴ������������
����������20-200%��
Rolling����˼�ǵ�����һ�������󣬾�������ԭ��־�ļ����ڱ��ݣ�����������һ���µ���־�ļ�������������ÿ������һ����־�ļ����������һ���ڵ���־�ļ�����Ѿ�����1G��
�ʹ������ɣ�������������һ�����ɡ�����log4j 1.xԭ���������޷�ʵ�֣���log4j2�оͺܼ��ˡ�
�����������
<Configuration status="WARN" monitorInterval="300">  
    <properties>  
        <property name="LOG_HOME">D:/logs</property>  
        <property name="FILE_NAME">mylog</property>  
    </properties>  
    <Appenders>  
        <Console name="Console" target="SYSTEM_OUT">  
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />  
        </Console>  
        <RollingRandomAccessFile name="MyFile"  
            fileName="${LOG_HOME}/${FILE_NAME}.log"  
            filePattern="${LOG_HOME}/$${date:yyyy-MM}/${FILE_NAME}-%d{yyyy-MM-dd HH-mm}-%i.log">  
            <PatternLayout  
                pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />  
            <Policies>  
                <TimeBasedTriggeringPolicy interval="1" />  
                <SizeBasedTriggeringPolicy size="10 MB" />  
            </Policies>  
            <DefaultRolloverStrategy max="20" />  
        </RollingRandomAccessFile>  
    </Appenders>  
  
    <Loggers>  
        <Logger name="mylog" level="trace" additivity="false">  
            <AppenderRef ref="MyFile" />  
        </Logger>  
        <Root level="error">  
            <AppenderRef ref="Console" />  
        </Root>  
    </Loggers>  
</Configuration>  
<properties>��������������������渴��
RollingRandomAccessFile�����ԣ�
fileName  ָ����ǰ��־�ļ���λ�ú��ļ�����
filePattern  ָ��������Rollingʱ���ļ���ת�ƺ�����������
SizeBasedTriggeringPolicy  ָ�����ļ��������sizeָ����ֵʱ������Rolling
DefaultRolloverStrategy  ָ����ౣ����ļ�����
TimeBasedTriggeringPolicy  ���������Ҫ��filePattern���ʹ�ã�ע��filePattern�����õ��ļ�������������
${FILE_NAME}-%d{yyyy-MM-dd HH-mm}-%i����С��ʱ��������mm�������ӣ�TimeBasedTriggeringPolicyָ����size��1��
�����������ÿ1��������һ�����ļ�������ĳ�%d{yyyy-MM-dd HH}����С����ΪСʱ����ÿһ��Сʱ����һ���ļ���



5 �Զ��������ļ�λ��------------------------------------------------------------------------
log4j2Ĭ����classpath�²��������ļ��������޸������ļ���λ�á��ڷ�web��Ŀ�У�
public static void main(String[] args) throws IOException {  
    File file = new File("D:/log4j2.xml");  
    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));  
    final ConfigurationSource source = new ConfigurationSource(in);  
    Configurator.initialize(null, source);        
    Logger logger = LogManager.getLogger("mylog");  
}  
�����web��Ŀ����web.xml�����
<context-param>  
    <param-name>log4jConfiguration</param-name>  
    <param-value>/WEB-INF/conf/log4j2.xml</param-value>  
</context-param>    
<listener>  
    <listener-class>org.apache.logging.log4j.web.Log4jServletContextListener</listener-class>  
</listener>  
������Щ��������ʵ��ʹ���ˣ���ƪ����һЩ�߼�Ӧ�ã��첽Appender��MongoDB Appender�ͻ���Filters�İ������������ͬ�ļ�������



Async/MongoDB/Flume Appender ����־���������ļ����
1. ����־���������ļ����
��Щ��ϰ�߰���־��Ϣ�����������ͬ���Ƶ��ļ��У���info.log��error.log��warn.log�ȣ���log4j2�п�ͨ������Filters��ʵ�֡�
�ٶ������ǰ�INFO�����¼������Ϣ�����info.log��WARN��ERROR�������Ϣ�����error.log��FATAL���������fatal.log�������ļ����£�
<Configuration status="WARN" monitorInterval="300">  
    <properties>  
        <property name="LOG_HOME">D:/logs</property>  
    </properties>  
    <Appenders>  
        <Console name="Console" target="SYSTEM_OUT">  
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />  
        </Console>  
  
        <RollingRandomAccessFile name="InfoFile"  
            fileName="${LOG_HOME}/info.log"  
            filePattern="${LOG_HOME}/$${date:yyyy-MM}/info-%d{yyyy-MM-dd}-%i.log">  
            <Filters>  
                <ThresholdFilter level="warn" onMatch="DENY" onMismatch="NEUTRAL" />  
                <ThresholdFilter level="trace" onMatch="ACCEPT" onMismatch="DENY" />  
            </Filters>  
            <PatternLayout pattern="%date{yyyy-MM-dd HH:mm:ss.SSS} %level [%thread][%file:%line] - %msg%n" />  
            <Policies>  
                <TimeBasedTriggeringPolicy />  
                <SizeBasedTriggeringPolicy size="10 MB" />  
            </Policies>  
            <DefaultRolloverStrategy max="20" />  
        </RollingRandomAccessFile>  
          
        <RollingRandomAccessFile name="ErrorFile"  
            fileName="${LOG_HOME}/error.log"  
            filePattern="${LOG_HOME}/$${date:yyyy-MM}/error-%d{yyyy-MM-dd}-%i.log">  
            <Filters>  
                <ThresholdFilter level="fatal" onMatch="DENY" onMismatch="NEUTRAL" />  
                <ThresholdFilter level="warn" onMatch="ACCEPT" onMismatch="DENY" />  
            </Filters>  
            <PatternLayout pattern="%date{yyyy-MM-dd HH:mm:ss.SSS} %level [%thread][%file:%line] - %msg%n" />  
            <Policies>  
                <TimeBasedTriggeringPolicy />  
                <SizeBasedTriggeringPolicy size="10 MB" />  
            </Policies>  
            <DefaultRolloverStrategy max="20" />  
        </RollingRandomAccessFile>  
          
        <RollingRandomAccessFile name="FatalFile"  
            fileName="${LOG_HOME}/fatal.log"  
            filePattern="${LOG_HOME}/$${date:yyyy-MM}/fatal-%d{yyyy-MM-dd}-%i.log">  
            <Filters>  
                <ThresholdFilter level="fatal" onMatch="ACCEPT" onMismatch="DENY" />  
            </Filters>  
            <PatternLayout pattern="%date{yyyy-MM-dd HH:mm:ss.SSS} %level [%thread][%file:%line] - %msg%n" />  
            <Policies>  
                <TimeBasedTriggeringPolicy />  
                <SizeBasedTriggeringPolicy size="10 MB" />  
            </Policies>  
            <DefaultRolloverStrategy max="20" />  
        </RollingRandomAccessFile>  
    </Appenders>  
  
    <Loggers>  
        <Root level="trace">  
            <AppenderRef ref="Console" />  
            <AppenderRef ref="InfoFile" />  
            <AppenderRef ref="ErrorFile" />  
            <AppenderRef ref="FatalFile" />  
        </Root>  
    </Loggers>  
</Configuration> 

2 �첽д��־
�����ļ��� 
<Configuration status="WARN" monitorInterval="300">  
    <properties>  
        <property name="LOG_HOME">D:/logs</property>  
        <property name="FILE_NAME">mylog</property>  
    </properties>  
  
    <Appenders>  
        <Console name="Console" target="SYSTEM_OUT">  
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />  
        </Console>  
        <RollingRandomAccessFile name="MyFile"  
            fileName="${LOG_HOME}/${FILE_NAME}.log"  
            filePattern="${LOG_HOME}/$${date:yyyy-MM}/${FILE_NAME}-%d{yyyy-MM-dd HH-mm}-%i.log">  
            <PatternLayout  
                pattern="%date{yyyy-MM-dd HH:mm:ss.SSS} %level [%thread][%file:%line] - %msg%n" />  
            <Policies>  
                <TimeBasedTriggeringPolicy interval="1" />  
                <SizeBasedTriggeringPolicy size="10 MB" />  
            </Policies>  
            <DefaultRolloverStrategy max="20" />  
        </RollingRandomAccessFile>  
        <Async name="Async">  
            <AppenderRef ref="MyFile" />  
        </Async>  
    </Appenders>  
  
    <Loggers>  
        <Logger name="asynclog" level="trace" additivity="false" >  
            <AppenderRef ref="Async" />  
        </Logger>  
        <Root level="error">  
            <AppenderRef ref="Console" />  
        </Root>  
    </Loggers>  
</Configuration>  

3 �����MongoDB
���������
<dependency>  
    <groupId>org.apache.logging.log4j</groupId>  
    <artifactId>log4j-nosql</artifactId>  
    <version>2.5</version>  
</dependency>  
<dependency>  
    <groupId>org.mongodb</groupId>  
    <artifactId>mongo-java-driver</artifactId>  
    <version>3.2.2</version>  
</dependency>  
�����ļ���
<Configuration status="WARN" monitorInterval="300">  
    <Appenders>  
        <Console name="Console" target="SYSTEM_OUT">  
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />  
        </Console>  
  
        <NoSql name="databaseAppender">  
            <MongoDb databaseName="test" collectionName="errorlog"  
                server="localhost" port="27017" />  
        </NoSql>  
    </Appenders>  
  
    <Loggers>  
        <Logger name="mongolog" level="trace" additivity="false">  
            <AppenderRef ref="databaseAppender" />  
        </Logger>  
        <Root level="error">  
            <AppenderRef ref="Console" />  
        </Root>  
    </Loggers>  
</Configuration>  

4 �����Flume
Flume����(flume-conf.properties)
agent1.sources=source1   
agent1.sinks=sink1   
agent1.channels=channel1   
  
agent1.sources.source1.type=avro  
agent1.sources.source1.channels=channel1  
agent1.sources.source1.bind=0.0.0.0  
agent1.sources.source1.port=41414  
  
agent1.sinks.sink1.type=file_roll   
agent1.sinks.sink1.sink.directory=D:/log  
agent1.sinks.sink1.channel=channel1  
agent1.sinks.sink1.sink.rollInterval=86400  
agent1.sinks.sink1.sink.batchSize=100  
agent1.sinks.sink1.sink.serializer=text  
agent1.sinks.sink1.sink.serializer.appendNewline = false  
  
agent1.channels.channel1.type=file   
agent1.channels.channel1.checkpointDir=D:/log/checkpoint   
agent1.channels.channel1.dataDirs=D:/log/data  


����Flume��ע�����Ի���Ϊwindows��
flume-ng.cmd agent --conf ../conf/ --conf-file ../conf/flume-conf.properties -name agent1  

���������
<dependency>  
    <groupId>org.apache.logging.log4j</groupId>  
    <artifactId>log4j-flume-ng</artifactId>  
    <version>2.5</version>  
</dependency>  

�����ļ���
<Configuration status="WARN" monitorInterval="300">  
    <Appenders>  
        <Flume name="eventLogger" compress="false">  
            <Agent host="127.0.0.1" port="41414" />  
            <RFC5424Layout enterpriseNumber="18060" includeMDC="true" appName="MyApp" />  
        </Flume>  
    </Appenders>  
    <Loggers>  
        <Root level="trace">  
            <AppenderRef ref="eventLogger" />  
        </Root>  
    </Loggers>  
</Configuration>  






















