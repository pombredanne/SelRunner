#!/bin/bash 


##
# start|stop|restart fitnesse server
#
# USAGE:
#   ./scripts/fitnesse_srv.sh start|stop|restart
#

RUNTIME="../tutorial_ci/runtime/fitnesse"

FITNESSE_ROOT="$PWD/test/fitnesse"
FITNESSE_CMD="java -Xmx200M -DSELENIUM_BROWSER=ChromeRunner -DSELENIUM_SERVER=http://localhost:4444/wd/hub -cp $RUNTIME/lib/fitnesse.jar:$RUNTIME/lib/* fitnesseMain.FitNesseMain -d $FITNESSE_ROOT -p 10200 -e 0 -r FitNesseRoot -l $RUNTIME/logs/"
export PATH=$PATH:$RUNTIME/drivers/

case $1 in
    "start" )
        echo "start fitnesse server"
        nohup $FITNESSE_CMD > /tmp/nohup.out 2> /tmp/nohup.out &
        ;;
    "stop" )
        echo "stop fitnesse server"
        kill `ps aux | grep "fitnesseMain.FitNesseMain" | grep -v grep | awk '{print $2}'` > /dev/null
        ;;
    "restart" )
        echo "restart fitnesse server"
        kill `ps aux | grep "fitnesseMain.FitNesseMain" | grep -v grep | awk '{print $2}'` > /dev/null 
        nohup $FITNESSE_CMD > /tmp/nohup.out 2> /tmp/nohup.out &
        ;;
    *)
        echo "need start|stop|restart"
        exit 1
esac