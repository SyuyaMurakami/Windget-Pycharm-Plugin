package syuyamurakami.idea;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.patterns.PlatformPatterns;
import com.jetbrains.python.PyTokenTypes;
import java.io.*;
import java.util.ArrayList;


public class windgetCodeCompletion extends CompletionContributor {
    InputStream csvFile = this.getClass().getResourceAsStream("/META-INF/EnglishNameAfterAutoReformation.csv");
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    ArrayList<String[]> data = new ArrayList<>();

    public windgetCodeCompletion() {
        try {
            br = new BufferedReader(new InputStreamReader(csvFile,"utf-8"));
            while ((line=br.readLine())!=null){
                String[] attrName = line.split(csvSplitBy);
                data.add(attrName);
//                System.out.println(attrName[0]+attrName[1]+attrName[2]);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        extend(CompletionType.BASIC, PlatformPatterns.psiElement(PyTokenTypes.IDENTIFIER), new windgetCodeCompletionProvider(data));
    }

}
