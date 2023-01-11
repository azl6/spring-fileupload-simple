FROM maven:3.8.6

ENV UPLOADED_FILES_DIR=/home/azl6/files-uploaded

WORKDIR /app

COPY . /app

RUN mvn clean package

CMD java -jar target/fileupload-0.0.1-SNAPSHOT.jar