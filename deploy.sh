./gradlew build -x test
docker stop daewoo-spring || true
docker build -t daewoo-spring-image . || true
docker run -d -p 33000:33000 --network daewoo --name daewoo-spring daewoo-spring-image || true
