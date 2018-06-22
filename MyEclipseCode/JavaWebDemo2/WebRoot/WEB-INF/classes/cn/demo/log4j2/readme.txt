2.输出源
CONSOLE（输出到控制台）、FILE（输出到文件）等。

3.布局方式
SimpleLayout：以简单的形式显示
HTMLLayout：以HTML表格显示
PatternLayout：自定义形式显示
在Log4J2中基本采用PatternLayout自定义日志布局。

自定义格式：
%t：线程名称
%p：日志级别
%c：日志消息所在类名
%m：消息内容
%M：输出执行方法
%d：发生时间，%d{yyyy-MM-dd HH:mm:ss,SSS}，输出类似：2011-10-18 22:10:28,921
%x: 输出和当前线程相关联的NDC(嵌套诊断环境),尤其用到像java servlets这样的多客户多线程的应用中。
%L：代码中的行数
%n：换行
<PatternLayout pattern="%d{HH:mm:ss.SSS} %-5level %class{36} %L %M -%msg%xEx%n"/>

c{precision}
logger{precision}
class{precision} Outputs the name of the logger that published the logging event.

Conversion Pattern			Logger Name					Result
%c{1}						org.apache.commons.Foo		Foo
%c{2}						org.apache.commons.Foo		commons.Foo
%c{10}						org.apache.commons.Foo		org.apache.commons.Foo
%c{-1}						org.apache.commons.Foo		apache.commons.Foo
%c{-2}						org.apache.commons.Foo		commons.Foo
%c{-10}						org.apache.commons.Foo		org.apache.commons.Foo
%c{1.}						org.apache.commons.Foo		o.a.c.Foo
%c{1.1.~.~}					org.apache.commons.test.Foo	o.a.~.~.Foo
%c{.}						org.apache.commons.test.Foo	....Foo


五、    配置使用
1.配置文件命名与存储位置（Log4J没有默认的配置文件）
系统选择configuration文件的优先级如下（放在src文件夹）：
classpath下名为 log4j-test.json 或者log4j-test.jsn文件
classpath下名为 log4j2-test.xml
classpath下名为 log4j.json 或者log4j.jsn文件
classpath下名为 log4j2.xml

很多人认为src就是classpath，java项目中Classpath路径到底指的是哪里？
1）   src不是classpath,WEB-INF/classes,lib才是classpath，WEB-INF/是资源目录, 客户端不能直接访问。
2）   WEB-INF/classes目录存放src目录java文件编译之后的class文件，xml、properties等资源配置文件，这是一个定位资源的入口。
3）   lib和classes同属classpath，两者的访问优先级为: lib>classes


//////////////////////////////////////////////////////////////////////
log4j2.xml文件结构
<?xml version="1.0" encoding="UTF-8"?>;
<Configuration>
  <Properties>
    <Property name="name1">value</property>
    <Property name="name2" value="value2"/>
  </Properties>
  <Filter type="type" ... />
  <Appenders>
    <Appender type="type" name="name">
      <Filter type="type" ... />
    </Appender>
    ...
  </Appenders>
  <Loggers>
    <Logger name="name1">
      <Filter type="type" ... />
    </Logger>
    ...
    <Root level="level">
      <AppenderRef ref="name"/>
    </Root>
  </Loggers>
</Configuration>
///////////////////////////////////////////////////////////////////////////



log4j2有默认的配置，如果要替换配置，只需要在classpath根目录下放置log4j2.xml。
log4j 2.0与以往的1.x有一个明显的不同，其配置文件只能采用.xml, .json或者 .jsn。
在默认情况下，系统选择configuration文件的优先级如下：（classpath为src文件夹）
classpath下名为 log4j-test.json 或者log4j-test.jsn文件
classpath下名为 log4j2-test.xml
classpath下名为 log4j.json 或者log4j.jsn文件
classpath下名为 log4j2.xml
如果本地要测试，可以把log4j2-test.xml放到classpath，而正式环境使用log4j2.xml，
则在打包部署的时候不要打包log4j2-test.xml即可。


下面是其缺省配置：
//////////////////////////////////////////////////////////////////////
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
  <Appenders>
    <Console name="Console" target="SYSTEM_OUT">
      <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
    </Console>
  </Appenders>
  <Loggers>
    <Root level="error">
      <AppenderRef ref="Console"/>
    </Root>
  </Loggers>
</Configuration>
////////////////////////////////////////////////////////////////////////
下面将对上面的配置文件进行一一讲解。
(见Bar和MyApp和log4j2.xml)


三、Appenders
ConsoleAppender-------------------------------------------------------------------
将使用 System.out 或 System.err输出到控制台。
可以有如下参数
name：Appender的名字
target：SYSTEM_OUT 或 SYSTEM_ERR，默认是SYSTEM_OUT
layout：如何格式化，如果没有默认是%m%n
典型的ConsoleAppender如下
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="warn" name="MyApp" packages="">
  <Appenders>
    <Console name="STDOUT" target="SYSTEM_OUT">
      <PatternLayout pattern="%m%n"/>
    </Console>
  </Appenders>
  <Loggers>
    <Root level="error">
      <AppenderRef ref="STDOUT"/>
    </Root>
  </Loggers>
</Configuration>


　　

RollingFileAppender-----------------------------------------------------------------------------
顾名思义，日志文件回滚，也就是删除最旧的日志文件，默认是3个文件。可以通过DefaultRolloverStrategy设置max参数为多个
例子如下:
<Appenders>
    <RollingFile name="RollingFile" fileName="logs/app.log"
                 filePattern="logs/$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.log.gz">
      <PatternLayout>
        <Pattern>%d %p %c{1.} [%t] %m%n</Pattern>
      </PatternLayout>
      <Policies>
        <TimeBasedTriggeringPolicy />
        <SizeBasedTriggeringPolicy size="250 MB"/>
      </Policies>
      <DefaultRolloverStrategy max="20"/>
    </RollingFile>
</Appenders>
现在说说TimeBasedTriggeringPolicy和SizeBasedTriggeringPolicy的作用。
第一个是基于时间的rollover，第二个是基于大小的rollover。第二个很容易理解，如果大小大于某个阈值，上面是50MB的时候就会滚动。
TimeBasedTriggeringPolicy中有其中一个参数是interval，表示多久滚动一次。默认是1 hour。modulate=true用来调整时间：
比如现在是早上3am，interval是4，那么第一次滚动是在4am，接着是8am，12am...而不是7am



四、Layouts-----------------------------------------------------------------------------
这里只描述最常见的PatternLayout！更多看官方文档http://logging.apache.org/log4j/2.x/manual/layouts.html
<RollingFile name="RollingFileError" fileName="${sys:user.home}/logs/error.log"
                     filePattern="${sys:user.home}/logs/$${date:yyyy-MM}/error-%d{yyyy-MM-dd}-%i.log">
            <ThresholdFilter level="ERROR"/>
    <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
    <Policies>
        <TimeBasedTriggeringPolicy/>
        <SizeBasedTriggeringPolicy size="50 MB"/>
    </Policies>
     <DefaultRolloverStrategy max="20"/>
</RollingFile>

上面的%是什么含义，还有哪些呢？其实最主要的参数还是%d, %p, %l, %m, %n, %X。下面的图是摘取网上的。
见图----PatternLayout参数说明.png

%X用来获取MDC记录，这些记录从哪来的？我们可以使用org.apache.logging.log4j.ThreadContext将需要记录的值put进去。
（我发现slf的MDC.java的put方法对log4j2不可用，因为底层依赖的是log4j1）
xml中使用%X{aaa}取出来：
<console name="Console" target="SYSTEM_OUT">
    <PatternLayout pattern="%X{user_id} %X{request_uri} [%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
</console>
对应ThreadContext的文档在这里https://logging.apache.org/log4j/2.x/manual/thread-context.html

五、Filters--------------------------------------------------------------------
Filters决定日志事件能否被输出。过滤条件有三个值：ACCEPT(接受), DENY(拒绝) or NEUTRAL(中立).
ACCEP和DENY比较好理解就是接受和拒绝的意思，在使用单个过滤器的时候，一般就是使用这两个值。但是在组合过滤器中，
如果用接受ACCEPT的话，日志信息就会直接写入日志文件,后续的过滤器不再进行过滤。所以，在组合过滤器中，接受使用NEUTRAL（中立），
被第一个过滤器接受的日志信息，会继续用后面的过滤器进行过滤，只有符合所有过滤器条件的日志信息，才会被最终写入日志文件。

ThresholdFilter
有几个参数：
level：将被过滤的级别。
onMatch:默认值是NEUTRAL
onMismatch：默认是DENY
如果LogEvent 中的 Log Level 大于 ThresholdFilter 中配置的 Log Level，那么返回 onMatch 的值， 否则返回 onMismatch 的值，
例如 : 如果ThresholdFilter 配置的 Log Level 是 ERROR ， LogEvent 的Log Level 是 DEBUG。 那么 onMismatch 的值将被返回，
 因为 ERROR 小于DEBUG。如果是Accept，将自己被接受，而不经过下一个过滤器
下面的例子可以这样理解：如果是INFO级别及其以上，将经过通过第一个过滤，进入第二个，否则是onMismatch：拒绝进入。对于第二个，如果是大于等于WARN(WARN/ERROR/ERROR)，
那么将返回onMatch，也就是拒绝，如果是其他情况(也就是INFO)，将是中立情况，因为后面没有其他过滤器，则被接受。最后的结果就只剩下INFO级别的日志。
也就符合了RollingFileInfo只记录Info级别的规则。
<RollingFile name="RollingFileInfo" fileName="${sys:user.home}/logs/info.log"
             filePattern="${sys:user.home}/logs/$${date:yyyy-MM}/info-%d{yyyy-MM-dd}-%i.log">
    <!--控制台只输出level及以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->        
    <Filters>
        <ThresholdFilter level="INFO"/>
        <ThresholdFilter level="WARN" onMatch="DENY" onMismatch="NEUTRAL"/>
    </Filters>
    <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
    <Policies>
        <TimeBasedTriggeringPolicy interval="24" modulate="true"/>
        <SizeBasedTriggeringPolicy size="50 MB"/>
    </Policies>
    <DefaultRolloverStrategy max="20"/>
</RollingFile>


六、Lookups--------------------------------------------------------------------------
提供另外一种方式添加某些特殊的值到日志中。
Date Lookup
与其他lookup不同，它不是通过key去查找值，而是通过SimpleDateFormat验证格式是否有效，然后记录当前时间
<RollingFile name="Rolling-${map:type}" fileName="${filename}" 
	filePattern="target/rolling1/test1-$${date:MM-dd-yyyy}.%i.log.gz">
  <PatternLayout>
    <pattern>%d %p %c{1.} [%t] %m%n</pattern>
  </PatternLayout>
  <SizeBasedTriggeringPolicy size="500" />
</RollingFile>


Context Map Lookup: 如记录loginId
<File name="Application" fileName="application.log">
  <PatternLayout>
    <pattern>%d %p %c{1.} [%t] $${ctx:loginId} %m%n</pattern>
  </PatternLayout>
</File>
这个的结果和前面的MDC是一样的，即 %X{loginId}


Environment Lookup：记录系统环境变量
比如可以获取如/etc/profile中的变量值
<File name="Application" fileName="application.log">
  <PatternLayout>
    <pattern>%d %p %c{1.} [%t] $${env:USER} %m%n</pattern>
  </PatternLayout>
</File>


System Properties Lookup
可以获取Java中的系统属性值。
<Appenders>
  <File name="ApplicationLog" fileName="${sys:logPath}/app.log"/>
</Appenders>
和系统属性值有什么区别呢？其实就是System.getProperties();和System.getenv();的区别。
其实Environment是获取环境变量，而System Properties获取的更多是与Java相关的值






























