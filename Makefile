build: property-api-build event-collector-api-build event-outbox-processor-build event-stats-processor-build
	docker-compose build

property-api-build:
	./property-api/gradlew build -p ./property-api

event-collector-api-build:
	./event-collector-api/gradlew build -p ./event-collector-api

event-outbox-processor-build:
	./event-outbox-processor/gradlew shadowJar -p ./event-outbox-processor
	./event-outbox-processor/gradlew copyJarToKC -p ./event-outbox-processor

event-stats-processor-build:
	./event-stats-processor/gradlew build -p ./event-stats-processor

run:
	docker-compose up -d