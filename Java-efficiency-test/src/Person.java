import java.util.Objects;

class Person implements Comparable<Person> {
    static String[] nameBase = {
            "James", "Mary", "Robert", "Patricia", "John",
            "Jennifer", "Michael", "Linda", "David",
            "Elizabeth", "William", "Barbara", "Richard",
            "Susan", "Joseph", "Jessica", "Charles", "Sarah",
            "Thomas", "Karen", "Christopher", "Nancy",
            "Daniel", "Lisa", "Paul"
    };
    String name;
    int birthYear;
    public Person() {
        this.name = nameBase[ (int) Math.floor(Math.random() * 25) ];
        this.birthYear = ( (int) Math.floor( Math.random() * (2025 - 1950 + 1) ) ) + 1950;
    }

    public int getBirthYear() {return birthYear;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return this.birthYear == person.birthYear && Objects.equals(this.name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.birthYear);
    }

    @Override
    public int compareTo(Person secondPerson) {
        return this.getBirthYear() - secondPerson.getBirthYear();
    }
}