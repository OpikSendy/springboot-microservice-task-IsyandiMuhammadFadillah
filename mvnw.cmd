@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script
@REM ----------------------------------------------------------------------------

@if "%DEBUG%" == "" @echo off
@setlocal

set MAVEN_CMD_LINE_ARGS=%*
set MAVEN_PROJECT_BASEDIR=%~dp0
if "%MAVEN_PROJECT_BASEDIR:~-1%"=="\" set MAVEN_PROJECT_BASEDIR=%MAVEN_PROJECT_BASEDIR:~0,-1%

if exist "%MAVEN_PROJECT_BASEDIR%\.mvn\wrapper\maven-wrapper.properties" goto okBaseDir
:errorBaseDir
echo Error: MAVEN_PROJECT_BASEDIR is not valid.
goto end

:okBaseDir
set WRAPPER_JAR="%MAVEN_PROJECT_BASEDIR%\.mvn\wrapper\maven-wrapper.jar"
set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

if exist %WRAPPER_JAR% goto run

echo Downloading Maven Wrapper...
powershell -Command "[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; (New-Object System.Net.WebClient).DownloadFile('https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar', '%MAVEN_PROJECT_BASEDIR%\.mvn\wrapper\maven-wrapper.jar')"

:run
java "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECT_BASEDIR%" -classpath %WRAPPER_JAR% %WRAPPER_LAUNCHER% %MAVEN_CMD_LINE_ARGS%
if ERRORLEVEL 1 goto error
goto end

:error
set ERRORLEVEL=1

:end
@endlocal
