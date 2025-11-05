# Java-efficiency-test

Small academic project (polished) that benchmarks the **efficiency of adding custom objects** to common Java collections (e.g., `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`).  
Originally developed for coursework; refined after evaluation.

## What it measures
- Time to **insert N elements** of a custom type into different collections

## Requirements
- **Java 17+** (any JDK vendor)

## How to Run 

### Option 1 - Run directly from source
```bash
javac EfficiencyTest.java
java EfficiencyTest
```
### Option 2 - Run the prebuilt JAR
Download the latest release:
[EfficiencyTest.jar (latest)](https://github.com/faafal/Java-efficiency-test/releases/download/v1.0.0/EfficiencyTest.jar)
#### Then run it:
```bash
java -jar EfficiencyTest.jar
```
### Option 3 — In an IDE
Open the repo in IntelliJ IDEA, Eclipse, or VS Code and run the EfficiencyTest class.
## Example output

| Data type | Collection Type | Number of instances | Creation time |
|------------|-----------------|---------------------|----------------|
| Boolean    | ArrayList       | 1,232,131           | 72 ns          |
| Double     | ArrayList       | 13,451,451          | 919 ns         |
| Person     | HashSet         | 124,161             | 21 ns          |
| Color      | TreeSet         | 4,567,241           | 432 ns         |





