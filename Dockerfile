
######
## Stage 2: Build application with Maven
######
FROM gradle:jdk11 as build
WORKDIR /app/server
ADD server /app/server
ADD shared /app/shared
RUN gradle build



######
## Stage 3: Package into Tomcat and create start script
## and expose port and define command to start
######
FROM tomcat:8.5.43-jdk11-openjdk

WORKDIR /app

# Create a group and user "marathon"
RUN addgroup --system app && adduser --system --ingroup app --home /home/app app

COPY --from=build /app/server/build/libs/serialization*.war /usr/local/tomcat/webapps/serialization.war

RUN echo "sleep 3; cd /usr/local/tomcat/bin; ./catalina.sh jpda start; tail -f /usr/local/tomcat/logs/catalina.out" >> /app/server.sh
RUN chmod 755 /app/server.sh

# Tell docker that all future commands should run as the "marathon" user
RUN chown -R app:app /app /usr/local/tomcat /home/app
USER app

ENV CATALINA_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=7199 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"

# Tomcat
EXPOSE 8080
# Debug
EXPOSE 8000
# JMX
EXPOSE 7199

#HEALTHCHECK CMD curl --silent --fail http://localhost:8080/marathon/monitoring | grep true || exit 1
CMD /app/server.sh