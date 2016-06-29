javac -cp ../lib/javassist-3.20.0-GA.jar:../lib/tools.jar *.java
jar -cmf manifest.txt loggingagent.jar *.class
cp loggingagent.jar ../lib
