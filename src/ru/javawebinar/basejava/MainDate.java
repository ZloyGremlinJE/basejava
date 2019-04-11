package ru.javawebinar.basejava;

import java.io.IOException;

public class MainDate {
    String name;
    Integer age;
    Boolean sex;
    String  mother;
    String father;

    public MainDate(String name) {
        this.name = name;
    }

    public MainDate(String name, Integer age) {
        this(name);
        this.age = age;
    }

    public MainDate(String name, Integer age, Boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public static void main(String[] args) throws IOException {
        short number = 9;
        char zero = '0';
        int nine = number + zero;
        System.out.println((char)nine);
        System.out.println((int)'9');
    }

//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String sourceFileName = reader.readLine();
//        String destinationFileName = reader.readLine();
//
//        InputStream fileInputStream = getInputStream(sourceFileName);
//        OutputStream fileOutputStream = getOutputStream(destinationFileName);
//
//        int count = 0;
//        while (fileInputStream.available() > 0) {
//            int data = fileInputStream.read();
//            fileOutputStream.write(data);
//            count++;
//        }

//        fileInputStream.close();
//        fileOutputStream.close();
//        System.out.println("Скопировано байт " + count);
//    }
//
//
//    public static InputStream getInputStream(String fileName) throws IOException {
//        return new FileInputStream(fileName);
//    }
//
//    public static OutputStream getOutputStream(String fileName) throws IOException {
//        return new FileOutputStream(fileName);
//    }


//    public static void main(String[] args) {
//
//
//    }
//        long start = System.currentTimeMillis();
//        Date date = new Date();
//        System.out.println(date);
//        System.out.println(System.currentTimeMillis() - start);
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
//        System.out.println(cal.getTime());
//
//        LocalDate ld = LocalDate.now();
//        LocalTime lt = LocalTime.now();
//        LocalDateTime ldt = LocalDateTime.of(ld, lt);
//        System.out.println(ldt);
//
//
//        SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");
//        System.out.println(sdf.format(date));
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YY/MM/dd");
//        System.out.println(dtf.format(ldt));
//        String s = "54214";
//        for (int i = 0; i<s.length(); i++){
//            System.out.println(Character.getNumericValue(s.charAt(i)));
//        }
//        SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy", Locale.US);
//        System.out.println(df.format(date));
//    }

//    public static HashMap<String, String> createMap() {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("1", "1");
//        map.put("2", "11");
//        map.put("3", "111");
//        map.put("4", "1111");
//        map.put("5", "11111");
//        map.put("6", "111");
//        map.put("7", "111111");
//        map.put("8", "1111");
//        map.put("9", "11");
//        map.put("10", "11111");
//        return map;
//    }
//
//    public static void removeTheFirstNameDuplicates(Map<String, String> map) {
//
//        Set<String> set = new HashSet<>();
//        set.addAll(map.values());
//
//        for (String s : set) {
//            if (Collections.frequency(map.values(), s) > 1) {
//                removeItemFromMapByValue(map, s);
//            }
//        }
//        System.out.println(map);
//
//
//    }
//
//    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
//        HashMap<String, String> copy = new HashMap<String, String>(map);
//        for (Map.Entry<String, String> pair : copy.entrySet()) {
//            if (pair.getValue().equals(value))
//                map.remove(pair.getKey());
//        }
//    }
//
//
//    public static void main(String[] args) {
//        removeTheFirstNameDuplicates(createMap());
//    }
}