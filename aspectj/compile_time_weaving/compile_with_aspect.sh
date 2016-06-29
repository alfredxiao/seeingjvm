java -classpath ../lib/aspectjtools.jar:../lib/aspectjrt.jar  -Xmx64M org.aspectj.tools.ajc.Main -outjar app_with_aspect.jar -g -sourceroots ./src

# OR, alternatively use ajc
# ajc -cp ../lib/aspectjrt.jar:. -outjar app_with_aspect.jar -sourceroots .
