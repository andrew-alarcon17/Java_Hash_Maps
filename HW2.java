import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class HW2 {

    // Prints all movies that occur in both lists.
    public static void intersection(List<String> list1, List<String> list2) {
        Set<String> hash1 = new HashSet<>(list1);
        Set<String> hash2 = new HashSet<>(list2);
        Set<String> intersect = new HashSet<>();
        //Sets have all unique values!

        for(String movie : hash1) {
            if (hash2.contains(movie)){ //Hash sets have constant time!
                intersect.add(movie);
            }
        }

        System.out.println(intersect.toString()); //print the intersecting movies

        /*
        for(String movie : list1) {
            if(list2.contains(movie)) {
                intersect.add(movie);
            }
        }
         */
    }

    // Prints all movies in the list that occur at least k times
    // (print the movie followed by the number of occurrences in parentheses).
    public static void frequent(List<String> list, int k) {
        Map<String, Integer> map = new HashMap<>();
        for(String movie : list) {
            if (map.containsKey(movie)) {
                //System.out.println("Found movie: " + movie + "...increment +1");
                map.put(movie, map.get(movie) + 1);
                if(map.get(movie) >= k) {
                    System.out.println(movie + " appears " + map.get(movie).toString() + " times");
                }
            } else {
                //System.out.println("Add new movie to map....");
                map.put(movie, 1);
            }
        }
    }

    // Prints all movies in the list, grouped by year.
    // All movies from the same year should be printed on the same line.
    // Earlier years should be listed first.
    public static void groupByYear(List<String> list) {
        Map<String, Set<String>> map = new TreeMap<>();
        Map<String, String> map2 = new TreeMap<>();

        /*We split all the entries of the file and put each entry into a new map.
        Each entry looks something like:
        {"KidA";"2000"}
        {"Figure8";"2000";
        {"OkComputer";"1997"}
        {"EitherOr";"1997"}
        {"Beggars";"2009"}
        */
        for(String movie : list){
            String[] array = movie.split(";");
            map2.put(array[0], array[1]);
        }

        for(Map.Entry<String, String> entry : map2.entrySet()) { //For the entries in map2
            if(!map.containsKey(entry.getValue())) {        // Check if the year is a key in the map.
                map.put(entry.getValue(), new TreeSet<>()); //If not, create a new pair and add the entry to the empty set
                //add movie to the treeset
            }                                              //If the year is already a key in the map, break out of the loop.
                Set<String> keys = map.get(entry.getValue());   //Use the map's get method to get the set associated with the year
                keys.add(entry.getKey());                       //Then use the set's add method to add the movie
                map.put(entry.getValue(), keys);
        }

        //System.out.println(map2.toString());
        System.out.println(map.toString());
    }

    // Returns a List of all movies in the specified file (assume there is one movie per line).
    public static List<String> getList(String filename) {
        List<String> list = new ArrayList<>();
        try (Scanner in = new Scanner(new FileReader(filename))) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> list1 = getList("1001_movies.txt");
        List<String> list2 = getList("rosenbaum.txt");
        List<String> list3 = getList("all_lists.txt");
        List<String> list4 = getList("sightsound_with_years.txt");


        System.out.println("***intersection***");
        intersection(list1, list2);

        System.out.println("***frequent***");
        frequent(list3, 2);

        System.out.println("***groupByYear***");
        groupByYear(list4);

    }
}
