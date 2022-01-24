cd ..
set JDK_HOME=C:\Program Files\Java\jdk1.7.0
set SERVLET_API=E:\projects\servlet\javax.servlet.jar
java -cp "%SERVLET_API%;lib\war.jar;lib\webserver.jar;lib\jasper.jar;%JDK_HOME%\lib\tools.jar" -Dtjws.webappdir=webapps -Dtjws.wardeploy.warname-as-context=yes Acme.Serve.Main -a aliases.properties -p 80 -l -c cgi-bin
