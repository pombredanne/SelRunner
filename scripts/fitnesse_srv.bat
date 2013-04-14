@ECHO OFF

REM ##
REM # start|stop|restart fitnesse server
REM #
REM # USAGE:
REM #   .\scripts\fitnesse_srv.bat start|stop|restart
REM #

set RUNTIME=..\tutorial_ci\runtime\fitnesse

set FITNESSE_ROOT=%cd%\test\fitnesse
set FITNESSE_CMD=java -Xmx200M -cp %RUNTIME%\lib\fitnesse-standalone.jar;%RUNTIME%\lib\* fitnesseMain.FitNesseMain -d %FITNESSE_ROOT% -p 10200 -e 0 -r FitNesseRoot -l %RUNTIME%/logs/
set PATH=%PATH%;%RUNTIME%\win_drivers\

REM echo "now run: %FITNESSE_CMD%"

%FITNESSE_CMD%
