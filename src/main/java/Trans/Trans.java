package Trans;

import com.ibm.icu.text.Transliterator;

public class Trans {

    public final String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";

    public String translit(String str){
        Transliterator transliterator = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        return transliterator.transliterate(str.replace(' ','-'));
    }
}
