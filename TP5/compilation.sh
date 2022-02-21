javac -Xlint:unchecked -cp WEB-INF/lib/*:WEB-INF/classes:../Tjws/lib/servlet.jar -d WEB-INF/classes src/com/iut/servlets/*






jar cf appli.war WEB-INF
jar uf appli.war index.html
jar uf appli.war css
jar uf appli.war js

cp appli.war ../Tjws/webapps/