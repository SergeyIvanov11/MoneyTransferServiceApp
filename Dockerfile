FROM openjdk:17-alpine
EXPOSE 5500
ADD target/MoneyTransferServiceApp-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
