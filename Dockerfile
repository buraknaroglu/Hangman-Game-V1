FROM openjdk:8
ADD target/hangman-game.jar hangman-game.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "hangman-game.jar"]