# Create an Image
FROM openjdk:17.0.1
ARG project_name
ENV project_name_env=$project_name
EXPOSE 5000
RUN echo "project_name: $project_name"
COPY /$project_name/build/libs/$project_name.jar $project_name.jar
ENTRYPOINT exec java -jar $(echo {$project_name_env}).jar


