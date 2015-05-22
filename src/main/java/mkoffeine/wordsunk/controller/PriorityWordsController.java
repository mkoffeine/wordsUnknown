package mkoffeine.wordsunk.controller;

import mkoffeine.wordsunk.controller.extra.LineInfo;
import mkoffeine.wordsunk.controller.extra.WordInfo;
import mkoffeine.wordsunk.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Michael on 10.05.2015.
 */
@Controller
public class PriorityWordsController {
    private static String PATH = "x:\\_Myhaylo\\idea\\00tb\\en_full-raw.dic";
    private String[] SUFFIXES = new String[]{"s", "ing", "es", "ly", "er", "ar", "ir", "y", "able", "e",
            "t", "ed", "en", "ist", "ful", "fy", "ian", "ize", "ible",
            "ness", "less", "ism", "hood", "ment"
            , "al", "ent", "nt", "sm", "m", "st", "an"
            , "ss", "ng", "n", "ble", "le", "r", "l"
            , "ul", "sm", "ood", "od", "d"
            , "on", "ion", "tion"};


    private WordInfo[] wordsArray = new WordInfo[0];

    public PriorityWordsController() {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = new String(encoded, StandardCharsets.UTF_8);
        String[] split = text.split("\n");
        List<WordInfo> list = new LinkedList<>();
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            int spaceIndex = s.indexOf(" ");
            String word = s.substring(0, spaceIndex);
            int pos = Integer.parseInt(s.substring(spaceIndex + 1).trim());
            WordInfo w = new WordInfo(word, i, pos);
            list.add(w);
        }
        wordsArray = list.toArray(wordsArray);
    }

    @RequestMapping(value = "/getFreq", method = RequestMethod.POST)
    public
    @ResponseBody
    String getFreq(@RequestParam("words") String words) {
        System.out.println("-------------------starting getHitsInText");

        StringBuilder result = new StringBuilder();
        String[] split = words.split("\n");

        SortedSet<LineInfo> lineSet = new TreeSet<>();
        for (int i = 0; i < split.length; i++) {
            String line = split[i];
            int spaceIndex = line.indexOf(" ");
            String word = line.substring(0, spaceIndex);
            Arrays.sort(wordsArray);
            int indexFound = Arrays.binarySearch(wordsArray, new WordInfo(word, 0, 0));
            int index = indexFound > -1 ? indexFound : -indexFound - 1;
            //---------
            int f = Integer.parseInt(line.substring(spaceIndex + 1).trim());
            LineInfo li = new LineInfo(word, wordsArray[index], f);


            //-----------more result
            if (index > -1 && index < wordsArray.length) {
                WordInfo info = wordsArray[index];
//                status = "w:  " + info.toString() + "\n\n";
                for (String suffix : SUFFIXES) {
                    // Plus suffux
                    String w = word + suffix;
                    index = Arrays.binarySearch(wordsArray, new WordInfo(w, 0, 0));
                    if (index > 0 && index < wordsArray.length) {
                        li.addWord(wordsArray[index]);
                    } else {
                        //status += "   --no-- " + word + "\n";
                    }

                    // MINUS suffux
                    if (word.endsWith(suffix)) {
                        w = word.substring(0, word.length() - suffix.length());
                        index = Arrays.binarySearch(wordsArray, new WordInfo(w, 0, 0));
                        if (index > 0 && index < wordsArray.length) {
                            li.addWord(wordsArray[index]);
                        }
                    }

                }

            }

            lineSet.add(li);
        }
        for (LineInfo li : lineSet) {
            result.append(li.toHtml()).append("<br>\n");
        }


        System.out.println("-------------------end getHitsInText");
        return result.toString();
    }

    public static void main(String[] args) {
        PriorityWordsController p = new PriorityWordsController();
        String s = p.getFreq("qwer 5\n" +
                "the 2\n" +
                "test 1\n" +
                "thes 1\n" +
                "these 1\n" +
                "sss 1\n" +
                "err 1\n" +
                "errs 1");

        System.out.println(s);
    }
}
