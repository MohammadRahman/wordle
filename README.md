# to compile the app to a new directory called out
```bash
javac -d out ./**/*.java
```
# to run the app since the compiled main class located in out dir so need to specify the location of main file
```bash
java -cp out wordle.WordleGame 
```
