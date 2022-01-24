#!/bin/bash

### BEGIN INIT INFO
# Provides:          tjwserv
# Required-Start:    $remote_fs $syslog $network
# Required-Stop:     $remote_fs $syslog
# Default-Start:     3 4 5
# Default-Stop:     0 1 2 6
# Short-Description: Start TJWS at boot time
# Description:       Enable service provided by TJWS.
### END INIT INFO

RETVAL=0;

JDK_HOME=/opt/java/ejre1.7.0_10
tjws=/home/pi/tjws
cp=$tjws/lib/javax.servlet.jar:$tjws/lib/war.jar:$tjws/lib/webserver.jar:$tjws/lib/jsp.jar:$tjws/lib/jasper.jar:$JDK_HOME/lib/tools.jar
log=/var/log/tjws

tjws_cmd= java -cp $cp -Dtjws.webappdir=$tjws/webapps -Dtjws.wardeploy.dynamically Acme.Serve.Main -nohup -a $tjws/aliases.properties -p 80 -l -c cgi-bin -d $log

start() {
echo "Starting TJWS"
$tjws_cmd
}

stop() {
echo "Stopping TJWS"
ps -ef | grep java | grep Acme.Serv |awk '{print $2}' | xargs kill >/dev/null 2>&1
}

restart() {
stop
start
}

case "$1" in
start)
  start
;;
stop)
  stop
;;
restart)
  restart
;;
*)

echo $"Usage: $0 {start|stop|restart}"
exit 1
esac

exit $RETVAL  