package sudoku;

import java.util.ListResourceBundle;

public class AuthorsBundle extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            {"programmer1", "Jakub Plich"},
            {"programmer2", "Tomasz Witczak"},
    };
}
