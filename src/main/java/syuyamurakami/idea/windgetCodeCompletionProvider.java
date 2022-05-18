package syuyamurakami.idea;

import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
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
    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
        int size = this.size;
        ArrayList attr = this.attr;
        String[] rec;
        for (int i = 1;i<size;i++){
            rec = (String[]) attr.get(i);
            result.addElement(LookupElementBuilder.create("get"+rec[2]).withCaseSensitivity(true).withPresentableText(rec[0]+"->get"+rec[2]).withIcon(AllIcons.Nodes.Function));
        }
    }
}