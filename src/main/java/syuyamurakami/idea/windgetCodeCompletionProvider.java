package syuyamurakami.idea;

import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.PlainPrefixMatcher;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.StandardPatterns;
import com.intellij.util.ProcessingContext;
import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.NotNull;
import java.lang.String;
import java.util.ArrayList;

public class windgetCodeCompletionProvider extends CompletionProvider<CompletionParameters> {
    ArrayList attr;
    int size;
    windgetCodeCompletionProvider(ArrayList data){
        this.attr = data;
        this.size = data.size();
    }
    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters,
                                  ProcessingContext context,
                                  @NotNull CompletionResultSet result) {
        result.restartCompletionOnPrefixChange(StandardPatterns.string().startsWith("g"));
        String rawPrefix = result.getPrefixMatcher().getPrefix();
        String chinesePart = extractTrailingChinese(rawPrefix);
        CompletionResultSet getBranch = result.withPrefixMatcher(rawPrefix);

        int size = this.size;
        ArrayList attr = this.attr;
        String[] rec;

        if (rawPrefix.startsWith("get")) {
            if (!chinesePart.isEmpty()) {
                CompletionResultSet cnBranch = result.withPrefixMatcher(new windgetContainsPrefixMatcher(chinesePart));
                for (int i = 1; i < size; i++) {
                    rec = (String[]) attr.get(i);
                    cnBranch.addElement(
                            LookupElementBuilder.create(rec[2])
                                    .withLookupString(rec[0])
                                    .withPresentableText(rec[0] + " -> get" + rec[2])
                                    .withIcon(AllIcons.Nodes.Function)
                    );
                }
            } else {
                for (int i = 1; i < size; i++) {
                    rec = (String[]) attr.get(i);
                    getBranch.addElement(
                            LookupElementBuilder.create("get" + rec[2])
                                    .withCaseSensitivity(true)
                                    .withPresentableText(rec[0] + " -> get" + rec[2])
                                    .withIcon(AllIcons.Nodes.Function)
                    );
                }
            }
        }
    }

    private static String extractTrailingChinese(String s) {
        int i = s.length();
        while (i > 0 && isCNS(s.charAt(i - 1))) {
            i--;
        }
        return s.substring(i);
    }

    private static boolean isCNS(char c) {
        return Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN;
    }
}
