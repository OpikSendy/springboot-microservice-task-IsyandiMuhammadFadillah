#!/bin/sh
# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup shell script for Linux/Unix/Docker
# ----------------------------------------------------------------------------

PRG="$0"
while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

SAVED="`pwd`"
CDPATH=""
cd "`dirname "$PRG"`/.." >/dev/null
exec_dir="`pwd`"
cd "$SAVED" >/dev/null

JAVACMD="java"
WRAPPER_JAR="$exec_dir/.mvn/wrapper/maven-wrapper.jar"
WRAPPER_LAUNCHER="org.apache.maven.wrapper.MavenWrapperMain"

exec "$JAVACMD" "-Dmaven.multiModuleProjectDirectory=$exec_dir" -classpath "$WRAPPER_JAR" "$WRAPPER_LAUNCHER" "$@"
