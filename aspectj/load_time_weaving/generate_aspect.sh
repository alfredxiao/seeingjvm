javac app/*.java

java -classpath ../lib/aspectjtools.jar:../lib/aspectjrt.jar:.  -Xmx64M org.aspectj.tools.ajc.Main -outjar logging_aspect.jar -outxml Logging.aj

# OR, alternatively use ajc
# ajc -cp ../lib/aspectjrt.jar:. -outjar logging_aspect.jar -outxml Logging.aj
