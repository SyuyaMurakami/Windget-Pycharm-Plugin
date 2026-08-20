package syuyamurakami.idea;

import com.intellij.codeInsight.completion.PrefixMatcher;
import org.jetbrains.annotations.NotNull;

public class windgetContainsPrefixMatcher extends PrefixMatcher {

    public windgetContainsPrefixMatcher(String prefix) {
        super(prefix);
    }

    @Override
    public boolean prefixMatches(@NotNull String name) {
        return name.contains(getPrefix());
    }

    @Override
    public PrefixMatcher cloneWithPrefix(String prefix) {
        return new windgetContainsPrefixMatcher(prefix);
    }
}

