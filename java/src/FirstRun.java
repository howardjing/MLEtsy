public class FirstRun {
    public static void main(String[] args) {
        User testUser = new User("data/training.txt");
        RandomItems testRandomItems = new RandomItems("data/randomizedTesting.txt");
    
        Tester test = new Tester(testUser, testRandomItems);
        //System.out.println("Dict: " + test.allTagsDict.toString());
        test.printPreferences();
    
    }
}