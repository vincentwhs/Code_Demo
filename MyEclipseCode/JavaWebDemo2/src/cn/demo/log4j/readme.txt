Log4j由三个重要的组成构成：日志记录器(Loggers)，输出端(Appenders)和日志格式化器(Layout)。
1.Logger：控制要启用或禁用哪些日志记录语句，并对日志信息进行级别限制
2.Appenders : 指定了日志将打印到控制台还是文件中
3.Layout : 控制日志信息的显示格式

A：off         最高等级，用于关闭所有日志记录。
B：fatal       指出每个严重的错误事件将会导致应用程序的退出。
C：error      指出虽然发生错误事件，但仍然不影响系统的继续运行。
D：warm     表明会出现潜在的错误情形。
E：info         一般和在粗粒度级别上，强调应用程序的运行全程。
F：debug     一般用于细粒度级别上，对调试应用程序非常有帮助。
G：all           最低等级，用于打开所有日志记录。
5种常用级别，依次为DEBUG、INFO、WARN、ERROR和FATAL
建议使用4个级别，优先级从高到低分别是:1.error 2.warn 3.info 4.debug


Log4j几种常用的输出目的地:
a：org.apache.log4j.ConsoleAppender：将日志信息输出到控制台。
b：org.apache.log4j.FileAppender：将日志信息输出到一个文件。
c：org.apache.log4j.DailyRollingFileAppender：将日志信息输出到一个日志文件，并且每天输出到一个新的日志文件。
d：org.apache.log4j.RollingFileAppender：将日志信息输出到一个日志文件，并且指定文件的尺寸，当文件大小达到指定尺寸时，会自动把文件改名，同时产生一个新的文件。
e：org.apache.log4j.WriteAppender：将日志信息以流格式发送到任意指定地方。
f：org.apache.log4j.jdbc.JDBCAppender：通过JDBC把日志信息输出到数据库中。


日志格式化器Layout有四种：
1.HTMLLayout : 格式化日志输出为HTML表格形式如图：
2.SimpleLayout : 以一种非常简单的方式格式化日志输出，它打印三项内容：级别-信息
      例：INFO - info
3.PatternLayout : 根据指定的转换模式格式化日志输出，或者如果没有指定任何转换模式，就使用默认的转化模式格式。
4.TTCCLayout : 包含日志产生的时间、线程、类别等等信息


Log4j的配置
配置Log4j环境就是指配置root Logger，包括把Logger为哪个级别，为它增加哪些Appender,以及为这些Appender设置Layout，等等。因为所有其他的Logger都是root Logger的后代，所以它们都继承了root Logger的性质。这些可以通过设置系统属性的方法来隐式地完成，也可以在程序中调用XXXConfigurator.configure()方法来显式地完成。有以下几种方式来配置Log4j。
A：配置放在文件里，通过环境变量传递文件名等信息，利用Log4j默认的初始化过程解析并配置。
B：配置放在文件里，通过应用服务器配置传递文件甸等信息，利用一个特定的Servlet来完成配置。
C：在程序中调用BasicConfigor.configure()方法。
D：配置放在文件里，通过命令行PropertyConfigurator.configure(args[])解析log4j.properties文件并配置Log4j。

下面对BasicConfigurator.configure()方法和PropertyConfigurator.config()方法分别进行介绍。
BasicConfigurator.configure()方法：
它使用简单的方法配置Log4j环境。这个方法完成的任务是：
1：用默认的方式创建PatternLayout对象p:
  PatternLayout p = new PatternLayout("%-4r[%t]%-5p%c%x-%m%n");
2：用p创建ConsoleAppender对象a，目标是System.out，标准输出设备：
 ConsoleAppender a = new CpnsoleAppender(p,ConsoleAppender.SYSTEM_OUT);
3：为root Logger增加一个ConsoleAppender p;
 rootLogger.addAppender(p);
4：把rootLogger的log level设置为DUBUG级别;
 rootLogger.setLevel(Level.DEBUG);

PropertyConfigurator.configure()方法：
当使用以下语句生成Logger对象时：
static Logger logger = Logger.getLogger(mycalss.class);  
如果没有调用BasicConfigurator.configure()，PropertyConfigurator.configure()
或DOMConfigurator.configure()方法，
Log4j会自动加载CLASSPATH下名为log4j.properties的配置文件。
如果把此配置文件改为其他名字，例如my.properties,程序虽然仍能运行，
但会报出不能正确初始化Log4j系统的提示。这时可以在程序中加上：
PropertyConfigurator.configure("classes/my.properties");  问题即可解决。



4、Log4j的log4j.properties配置详解
  虽然可以不用配置文件，而在程序中实现配置，但这种方法在如今的系统开发中显然是不可取的，能采用配置文件的地方一定一定要用配置文件。
  Log4j支持两 种格式的配置文件：XML格式和Java的property格式。
详细配置见：配置详解.txt





/////////////////////////////////////////////////////////
2.2、在代码中使用Log4j

1.得到记录器

使用Log4j，第一步就是获取日志记录器，这个记录器将负责控制日志信息。其语法为：

public static Logger getLogger( String name)

通过指定的名字获得记录器，如果必要的话，则为这个名字创建一个新的记录器。Name一般取本类的名字，比如：

static Logger logger = Logger.getLogger ( ServerWithLog4j.class.getName () )

2.读取配置文件

当获得了日志记录器之后，第二步将配置Log4j环境，其语法为：

BasicConfigurator.configure ()： 自动快速地使用缺省Log4j环境。  
PropertyConfigurator.configure ( String configFilename) ：读取使用Java的特性文件编写的配置文件。  
DOMConfigurator.configure ( String filename ) ：读取XML形式的配置文件。

3.插入记录信息（格式化日志信息）

当上两个必要步骤执行完毕，您就可以轻松地使用不同优先级别的日志记录语句插入到您想记录日志的任何地方，其语法如下：

Logger.debug ( Object message ) ;  
Logger.info ( Object message ) ;  
Logger.warn ( Object message ) ;  
Logger.error ( Object message ) ;

2.3、日志级别

每个Logger都被了一个日志级别（log level），用来控制日志信息的输出。日志级别从高到低分为：
A：off 最高等级，用于关闭所有日志记录。
B：fatal 指出每个严重的错误事件将会导致应用程序的退出。
C：error 指出虽然发生错误事件，但仍然不影响系统的继续运行。
D：warm 表明会出现潜在的错误情形。
E：info 一般和在粗粒度级别上，强调应用程序的运行全程。
F：debug 一般用于细粒度级别上，对调试应用程序非常有帮助。
G：all 最低等级，用于打开所有日志记录。

上面这些级别是定义在org.apache.log4j.Level类中。Log4j只建议使用4个级别，优先级从高到低分别是error,warn,info和debug。通过使用日志级别，可以控制应用程序中相应级别日志信息的输出。例如，如果使用b了info级别，则应用程序中所有低于info级别的日志信息(如debug)将不会被打印出来。









