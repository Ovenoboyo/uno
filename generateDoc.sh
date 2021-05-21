rm -rf ./docs
./gradlew javadoc --no-watch-fs && mv app/build/docs/javadoc ./docs
