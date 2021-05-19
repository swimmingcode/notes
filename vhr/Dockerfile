#FROM java:8

COPY *.jar /vhr.jar

EXPOSE 80

#ENTRYPOINT ["java","-jar","/app.jar"]
#在docker run时运行，如果 Dockerfile 中如果存在多个 CMD 指令，仅最后一个生效。
CMD ["--server.port=80"]