package mkoffeine.wordsunk.controller.extra;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Michael on 16.05.2015.
 */
public class LineInfo implements Comparable<LineInfo> {
    private String text;//typed
    private WordInfo mainWord;
    private int hitsInText;
    private SortedSet<WordInfo> foundWords = new TreeSet<>(new WordInfo.FreqDescComparator());

    public LineInfo(String text, WordInfo mainWord, int hitsInText) {
        this.hitsInText = hitsInText;
        this.text = text;
        this.mainWord = mainWord;
    }

    private int getMaxFreq() {
        if (foundWords.size() == 0) {
            return mainWord.getFreq();
        }
        return Math.max(mainWord.getFreq(), foundWords.last().getFreq());
//        return Math.min(mainWord.getFreq(), foundWords.first().getFreq());
    }

    public LineInfo() {
    }

    public int getHitsInText() {
        return hitsInText;
    }

    public void setHitsInText(int hitsInText) {
        this.hitsInText = hitsInText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SortedSet<WordInfo> getFoundWords() {
        return foundWords;
    }

    public void addWord(WordInfo wi) {
        foundWords.add(wi);
    }

    public WordInfo getMainWord() {
        return mainWord;
    }

    public void setMainWord(WordInfo mainWord) {
        this.mainWord = mainWord;
    }

    @Override
    public int compareTo(LineInfo o) {
        if (this == o) return 0;
        if (this.hitsInText == o.hitsInText) {
            if (o.getMaxFreq() == this.getMaxFreq()) {
                return o.getMainWord().compareTo(this.getMainWord());
            }
            else {
                return o.getMaxFreq() - this.getMaxFreq();
//                todo fix sorting
//                return this.getMaxFreq() - o.getMaxFreq();
            }
        }
        return o.hitsInText - this.hitsInText;
    }

    public String toHtml() {
        String html = "" + text + ": " + getHitsInText() + " ---" +
                mainWord.getWord() + "--: " + getPositionHtml(getMainWord().getPosition()) + " - ";
        for (WordInfo wi : foundWords) {
            html += "--" + wi.getWord() + " " + getPositionHtml(wi.getPosition());
        }

        return html;
    }

    private String getPositionHtml(int f) {
        if (f < 5000) {
            return "<b>" + f + "</b>";
        }
        else if (f < 25000) {
            return "<b>" + f / 1000 + "</b>" + "k";
        } else {
            return "" + f / 1000 + "k";
        }
    }

    @Override
    public String toString() {
        return "LineInfo{" +
                "text='" + text + '\'' +
                ", mainWord=" + mainWord +
                ", hitsInText=" + hitsInText +
                ", foundWords=" + foundWords +
                '}';
    }
}
