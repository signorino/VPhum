import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.*
import ch.qos.logback.core.rolling.*
import ch.qos.logback.core.status.*

statusListener(OnConsoleStatusListener)
def env =  System.properties['spring.profiles.active'] ?: 'production'
def props = new Properties()
props.load(this.getClass().getClassLoader().getResourceAsStream("properties/application.properties"))

def config = new ConfigSlurper().parse(props)

def appenderList = []
def level = ERROR

def appName = config.app.name
def LOG_RECEIVER_DIR = '/logs/log_receiver/'+appName
jmxConfigurator()
if (env == 'production') {
    appenderList.add("ROLLING-ASYNC")
    level = WARN
} else if(env == 'integratetest') {
    appenderList.add("ROLLING-ASYNC")
    level = WARN
} else if(env == 'development') {
    appenderList.add("CONSOLE")
    appenderList.add("ROLLING-ASYNC")
    level = DEBUG
}
if (env == 'development') {
    appender("CONSOLE", ConsoleAppender) {
        encoder(PatternLayoutEncoder) {
            pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{35} - %msg %n"
            charset = Charset.forName("utf8")
        }
    }
}

if (env=='production' || env=='integratetest' || env=='development') {
    appender("ROLLING", RollingFileAppender) {
        encoder(PatternLayoutEncoder) { pattern = "[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%level] [%thread] [%logger{50}] >>> %msg%n" }
        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "${LOG_RECEIVER_DIR}/translator-%d{yyyy-MM-dd-HH}.zip"
            triggeringPolicy(SizeAndTimeBasedRollingPolicy) {
                maxFileSize = '10M'
                maxHistory = 30
            }
        }
    }
    appender("BizLogInfoFileAppender", RollingFileAppender) {
        encoder(PatternLayoutEncoder) { Pattern = "[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%level] [%thread] [%logger{50}] >>> %msg%n" }
        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "${LOG_RECEIVER_DIR}/vphum_%d{yyyyMMdd}.log"
            maxHistory = 30
        }
    }
    logger("com-vphum-biz-info", INFO, ["BizLogInfoFileAppender"], false)
    appender("BizLogErrFileAppender", RollingFileAppender) {
        encoder(PatternLayoutEncoder) { Pattern = "[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%level] [%thread] [%logger{50}] >>> %msg%n" }
        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "${LOG_RECEIVER_DIR}/vphum_%d{yyyyMMdd}.err"
            maxHistory = 30
        }
    }
    logger("com-vphum-biz-error", ERROR, ["BizLogErrFileAppender"], false)
}

root(level, appenderList)

