public class App {
    public static void main(String[] args) {

        Map map = new HashMap(10);
        map.put(1,1);
        map.put(2,3);
        map.put(3,4);
        map.put(4,5);
        map.put(5,6);
        System.out.println(" value of key(2): " + map.get(2) + " size of map:"+  map.size());
    }
}
