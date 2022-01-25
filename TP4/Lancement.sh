java -cp ../Tjws/lib/servlet .jar -d WEB-INF/classes src/com/iut/servlets/*
jar cf MonAppli.war WEB-INF
jar uf MonAppli.war index.html