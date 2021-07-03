package ch.zhaw.ads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuzzySearchServer implements CommandExecutor {


    private List<String> names;
    private Map<String, ArrayList<Integer>> trigramsToNames;


    @Override
    public String execute(String command) throws Exception {
        if (command.startsWith("FIND ")) {
            String searchName = command.replace("FIND ", "");
            String match = find(searchName);
            if (null == match) {
                return "No suitable person found.\n";
            }
            return match + "\n";
        } else {
            this.trigramsToNames = new HashMap<>();
            names = getNamesFromFile(command);
            for (int i = 0; i < names.size(); i++) {
                partitionNameAndAddToList(i, names.get(i));
            }
        }
        return null;
    }

        private String find(String searchName) {
        Map<String, Integer> nameCounter = new HashMap<>();
        if (names.contains(searchName)) {
            return searchName;
        }

        List<String> trigramsSearch = getTrigrams(searchName);
        for (String trigramSearch : trigramsSearch) {
            if (this.trigramsToNames.containsKey(trigramSearch)) {
                List<Integer> nameIndizes = this.trigramsToNames.get(trigramSearch);
                if (nameIndizes.isEmpty()) return "Nicht vorhanden";
                for (Integer index : nameIndizes) {
                    String name = names.get(index);
                    Integer count = 0;
                    if (nameCounter.containsKey(name)) {
                        count = nameCounter.get(name);
                    }
                    count+=1;
                    nameCounter.put(name, count);
                }
            }
        }

        String maxName = nameCounter.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

        return maxName;
    }

    private List<String> getNamesFromFile(String file) {
        List<String> names = new ArrayList<>();
        file = file.replace("\n", "");
        String[] data = file.split(";");
        List<String> values = new ArrayList<>();
        for (String part : data) {
            if (values.size() < 5) {
                values.add(part);
            } else {
                names.add(values.get(1));
                values.clear();
                values.add(part);
            }
        }
        return names;
    }

    private ArrayList<String> getTrigrams(String name) {

        ArrayList<String> trigrams = new ArrayList<>();
        for (int i = 0; i < name.length() && i+3<name.length(); i++) {
            trigrams.add(name.substring(i, i + 3).toUpperCase());
        }
        return trigrams;
    }

    private void partitionNameAndAddToList(Integer index, String name) {
        List<String> trigrams = getTrigrams(name);
        for (String trigram : trigrams) {
            if (!this.trigramsToNames.containsKey(trigram)) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(index);
                this.trigramsToNames.put(trigram, list);
            } else {
              this.trigramsToNames.get(trigram).add(index);
            }
        }
    }
}