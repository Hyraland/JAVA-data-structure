import java.util.List;
import java.util.*;

public class MyTrieSet implements TrieSet61B{
    HashMap<Character, MyTrieSet> next;
    boolean hasnext;

    public MyTrieSet(){
        next = new HashMap<>();
        hasnext = false;
    };

    /** Clears all items out of Trie */
    public void clear(){
        this.next = new HashMap<>();
        this.hasnext = false;
    };

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key){
        MyTrieSet curoot;
        HashMap<Character, MyTrieSet> curnext = next;
        for(char c: key.toCharArray()){
            if (curnext.containsKey(c)){
                curoot = curnext.get(c);
                curnext = curoot.next;
            }
            else {
                return false;
            }
        }
        return true;
    };


    /** Inserts string KEY into Trie */
    public void add(String key){
        if (key == null || key.length()==0){
            return;
        }
        MyTrieSet curoot = this;
        curoot.hasnext = true;
        HashMap<Character, MyTrieSet> curnext = this.next;
        for(char c: key.toCharArray()){
            if (curnext.containsKey(c)){
                curoot = curnext.get(c);
                curoot.hasnext = true;
                curnext = curoot.next;
            }
            else {
                curoot = new MyTrieSet();
                curoot.hasnext = true;
                curnext.put(c, curoot);
                curnext = curoot.next;
            }
        }
        if(curnext.size()==0) {
            curoot.hasnext = false;
        }
    };

    private List<String> getwords(MyTrieSet tri){
        List<String> words = new ArrayList<>();
        List<String> subwords;

        if(tri.hasnext == false){
            words.add("");
            return words;
        }

        for (char tr: tri.next.keySet()){
            subwords = getwords(tri.next.get(tr));
            for (String swd: subwords){
                    words.add(tr + swd);
            }
            words.add("");
        }
        return words;
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix){
        List<String> prefstr;
        List<String> words = new ArrayList<>();;
        if (prefix == null || prefix.length()==0){
            return getwords(this);
        }
        MyTrieSet curoot = this;
        HashMap<Character, MyTrieSet> curnext = next;
        for(char c: prefix.toCharArray()){
            if (curnext.containsKey(c)){
                curoot = curnext.get(c);
                curnext = curoot.next;
            }
            else {
                return null;
            }
        }
        prefstr = getwords(curoot);
        prefstr.removeIf(item -> item == null || "".equals(item));
        for (String pst: prefstr){
            words.add(prefix + pst);
        }
        return words;
    };

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key){
        throw new UnsupportedOperationException();
    };
}
