cd %~dp0
call java -javaagent:../demo-java-jacoco-lib/jacoco-0.7.9/lib/jacocoagent.jar -jar build/libs/demo-java-jacoco-1.0.0.jar
cd %~dp0