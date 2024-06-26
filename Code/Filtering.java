import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Filtering {
    public static List<String> dict = new ArrayList<>();


    /**
     * Used to calculate the standarddeviation of a string group and decide whether a string needs to be excluded
     * @param stringList
     * @return
     */

    public static List<String> standarddeviation(List<String> stringList) {
        List<String> resultString = new ArrayList<>();
        ArrayList<Double> entropy = new ArrayList<>();
        for (String s : stringList) {

            entropy.add(calculateEntropy(s));
        }
        double total = 0;
        for (Double d : entropy) {
            total += d;


        }
        double mean = total / stringList.size();
        double sd = 0;
        for (Double d : entropy) {
            sd += Math.pow((d - mean), 2);


        }
        double std = Math.sqrt(sd / stringList.size());
        for (int i = 0; i < stringList.size(); i++) {
            if (entropy.get(i) - mean <= 3 * std) {
                resultString.add(stringList.get(i));

            }
            else{System.out.println(stringList.get(i));}

        }

        return resultString;
    }

    /**
     * calculate the entropy of a string
     * @param name
     * @return
     */
    public static double calculateEntropy(String name) {
        HashMap<Character, Integer> occurence = new HashMap<>();
        name = name.substring(1, name.length() - 1);
        // System.out.println(this.name);
        for (int i = 0; i < name.length(); i++) {
            if (!occurence.containsKey(name.charAt(i))) {
                occurence.put(name.charAt(i), 1);
            } else {
                occurence.put(name.charAt(i), occurence.get(name.charAt(i)) + 1);
            }


        }
        double entropy = 0;
        for (int i = 0; i < name.length(); i++) {
            double temp = (((double) occurence.get(name.charAt(i))) / ((double) name.length()));
            temp = temp * (Math.log(temp) / Math.log(2));
            entropy += temp;


        }
        return -entropy;


    }

    /**
     * check the pattern of a string
     * @param stringArrayList
     * @return
     */
    public static ArrayList<String> patternFilter(List<String> stringArrayList) {
        ArrayList<String> result = new ArrayList<>();
        for (String s : stringArrayList) {
            boolean pattern = true;
            for (int i = 0; i < s.length() - 3; i++) {
                String sequence = s.substring(i, i + 4);
                if (!checkForpattern(sequence)) {
                    pattern = false;

                    break;
                }

            }
            if (pattern) result.add(s);


        }


        return result;
    }

    public static boolean checkForpattern(String s) {
        if ((s.charAt(0) == s.charAt(1)) && (s.charAt(2) == s.charAt(3)) && (s.charAt(0) == s.charAt(2))) return false;
        boolean flag = false;
        for (int i = 0; i < s.length() - 1; i++) {
            int a = (int) s.charAt(i);
            int b = (int) s.charAt(i + 1);
            if ((int) s.charAt(i) != ((int) s.charAt(i + 1)) + 1) {

                flag = true;
                break;
            }

        }
        if (!flag) return false;
        flag = false;
        for (int i = 0; i < s.length() - 1; i++) {
            if ((int) s.charAt(i) != ((int) s.charAt(i + 1)) - 1) {
                flag = true;
                break;
            }

        }
        return flag;


    }
    public static ArrayList<String> wordFilter(List<String> string) {
        ArrayList<String>result=new ArrayList<>();
        for(String s: string){
            boolean flag = false;
            for(String word: dict){

                if(s.toLowerCase().contains(word)){
//                    System.out.println(word);
//                 System.out.println(s);
                    flag=true;
                    break;

                }

            }
            if(!flag){
                result.add(s);
            }


        }


        return result;
    }
}
