package catalog;

import java.util.List;
import java.util.Random;

public final class Names {
    private final static List<String> maleNames = List.of("James", "John", "Robert", "Michael", "William", "David", "Richard", "Joseph", "Thomas", "Charles", "Christopher", "Daniel", "Matthew", "Anthony", "Mark", "Donald", "Steven", "Paul", "Andrew", "Joshua", "Kevin", "Brian", "George", "Edward", "Ronald", "Timothy", "Jason", "Jeffrey", "Ryan", "Jacob", "Gary", "Nicholas", "Eric", "Jonathan", "Stephen", "Larry", "Justin", "Scott", "Brandon", "Benjamin", "Samuel", "Gregory", "Frank", "Alexander", "Raymond", "Patrick", "Jack", "Dennis", "Jerry", "Tyler", "Aaron", "Adam", "Nathan", "Zachary", "Kyle", "Ethan", "Arthur", "Carl", "Albert", "Henry", "Joe", "Fred", "Harry", "Peter", "Willie", "Logan", "Austin", "Alan", "Jesse", "Christian", "Sean", "Ralph", "Roy", "Jordan", "Billy", "Bruce", "Bryan", "Noah", "Dylan", "Eugene", "Wayne", "Louis", "Philip", "Vincent", "Russell", "Bobby", "Johnny", "Bradley", "Curtis", "Chad", "Keith", "Shawn", "Walter", "Harold", "Dean", "Craig", "Derek", "Phillip", "Tony", "Randy", "Terry");
    private final static List<String> femaleNames = List.of("Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer", "Maria", "Susan", "Margaret", "Dorothy", "Lisa", "Nancy", "Karen", "Betty", "Helen", "Sandra", "Donna", "Carol", "Ruth", "Sharon", "Michelle", "Laura", "Sarah", "Kimberly", "Deborah", "Jessica", "Shirley", "Cynthia", "Angela", "Melissa", "Brenda", "Amy", "Anna", "Rebecca", "Virginia", "Kathleen", "Pamela", "Martha", "Debra", "Amanda", "Stephanie", "Carolyn", "Christine", "Marie", "Janet", "Catherine", "Frances", "Ann", "Joyce", "Diane", "Alice", "Julie", "Heather", "Teresa", "Doris", "Gloria", "Evelyn", "Jean", "Cheryl", "Mildred", "Katherine", "Joan", "Ashley", "Judith", "Rose", "Janice", "Kelly", "Nicole", "Judy", "Christina", "Kathy", "Theresa", "Beverly", "Denise", "Tammy", "Irene", "Jane", "Lori", "Rachel", "Marilyn", "Andrea", "Kathryn", "Louise", "Sara", "Anne", "Jacqueline", "Wanda", "Bonnie", "Julia", "Ruby", "Lois", "Tina", "Phyllis", "Norma", "Paula", "Diana", "Annie", "Lillian", "Emily", "Robin", "Peggy", "Crystal");

    public static String getRandomMaleName(){
        Random random = new Random();
        return maleNames.get(random.nextInt(maleNames.size()));
    }

    public static String getRandomFemaleName(){
        Random random = new Random();
        return femaleNames.get(random.nextInt(femaleNames.size()));
    }
}
