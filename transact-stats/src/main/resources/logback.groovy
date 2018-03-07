//
// Built on Tue Sep 26 22:05:31 CEST 2017 by logback-translator
// For more information on configuration files in Groovy
// please see http://logback.qos.ch/manual/groovy.html

// For assistance related to this tool or configuration files
// in general, please contact the logback user mailing list at
//    http://qos.ch/mailman/listinfo/logback-user

// For professional support please see
//   http://www.qos.ch/shop/products/professionalSupport

import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.ERROR
import static ch.qos.logback.classic.Level.WARN

def LOG_HOME = System.getenv("STATSLOGS")
def ARCH_HOME = "${LOG_HOME}/arch"

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{yyyy-MM-dd HH:mm:ss} >> %-3relative [%thread] %-5level %logger{36} - %msg%n"
    }
}

appender("FILE", RollingFileAppender) {
    file = "${LOG_HOME}/app.log"
    encoder(PatternLayoutEncoder) {
        pattern = "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger - %msg%n"
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${ARCH_HOME}/app.%d{yyyy-MM-dd}.%i.log"
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) { maxFileSize = "10MB" }
    }
}

logger("ch.qos.logback", ERROR, ["CONSOLE"], false)
logger("org.springframework", WARN, ["FILE", "CONSOLE"], false)
logger("com.technicaltests.n26", DEBUG, ["FILE", "CONSOLE"], false)
root(WARN, ["FILE", "CONSOLE"])
