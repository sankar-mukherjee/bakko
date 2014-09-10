package Android.Bug;

import java.io.File;
import java.util.ArrayList;

/***
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sankar
 */
public class tts3 {

    String ort;
    ArrayList<Integer> pon;
    static String[] PHONEME = {"a", "a0", "a1", "a10", "i", "i0", "e", "e0", "u", "u0",
        "o", "o0", "e1", "e10", "k", "k1", "g", "g1", "n2", "c",
        "c1", "j", "j1", "t0", "t10", "d0", "d10", "t", "t1", "d",
        "d1", "n", "p", "p1", "b", "b1", "m", "r", "l", "s1",
        "s", "h", "r0", "r10", "ng0", "y", "w", ",", "/", "?",
        ";", "#", "&", "."};
    int Phoneme_length = 54;
    String[] ARRANGED_PH = new String[Phoneme_length];
    int[] STATE = new int[Phoneme_length];

    public void Initialization() {

        int no_of_phonemes = 0, index, length, len;
        for (length = 3; length > 0; length--) {
            for (index = 0; index < Phoneme_length; index++) {
                len = PHONEME[index].length();
                if (len == length) {
                    ARRANGED_PH[no_of_phonemes] = PHONEME[index];
                    STATE[no_of_phonemes] = index;
                    no_of_phonemes = no_of_phonemes + 1;
                }
            }
        }

    }

    public void Pon(String word) {
        try{
        deleteExistingSD();
        //        File myFile = new File("out");
        //        myFile.delete();
        Initialization();
        pon = Conversion(word);
        synthesis s = new synthesis();
        int a = s.Token_Genaration(pon);
        s.Process_token(a);
        s.make_wave();
        }catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void deleteExistingSD() {
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/download");
        dir.mkdirs();
        File file = new File(dir, "out");
        file.delete();
    }

    public ArrayList<Integer> Conversion(String word) {
        int found = 0, count = 0, lnx = 0;
        int ln1 = word.length();
        int[] Phword = new int[ln1];
        for (int c = 0; c <= ln1 - 1; c++) {
            Phword[c] = 100;
        }
        int no_of_phon = 0;
        int pos1;
        while (ln1 > 0) {
            found = 0;
            count = 0;
            while ((count < Phoneme_length) && (found == 0)) {
                pos1 = word.indexOf(ARRANGED_PH[count]);
                if (pos1 == 0) {
                    found = 1;
                    Phword[no_of_phon] = STATE[count];
                    no_of_phon = no_of_phon + 1;
                    word = word.replaceFirst(ARRANGED_PH[count], "");
                    lnx = lnx + 1;
                    /**********************************/
                }
                count = count + 1;
            }
            /***********************************/
            ln1 = word.length();
        }

        ArrayList<Integer> al = new ArrayList<Integer>();

        for (int i = 0; i <= lnx - 1; i++) {
            al.add(i, Phword[i]);
        }
        return (al);
    }
}
