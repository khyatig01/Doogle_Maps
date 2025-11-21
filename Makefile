test: 
	javac -cp .:junit5.jar graphTest.java 
	java -jar junit5.jar --class-path . --scan-classpath	

run: Frontend.class
	java -cp . --scan-classpath -n Frontend

Frontend.class: Frontend.java Backend.class CS400Graph.class GraphADT.class
	javac Frontend.java Backend.java CS400Graph.java GraphADT.java

clean:
	rm *.class
