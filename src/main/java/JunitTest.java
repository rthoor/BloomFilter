import javafx.util.Pair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class JunitTest {

    @BeforeClass
    Main init(){
        //название и расширение файла из ресурсов для заполнения структуры
        String fileNameFromResources = "text.txt";
        Main main = new Main(fileNameFromResources);
        return main;
    }

    @Test
    void testAdd() {
        Main main = init();
        String wordOne = "hi";
        String wordTwo = "friends";
        String wordThree = "achievement";

        //этих слов изначально там нет (проверяем)
        Assert.assertEquals(main.maybeContainsWord(wordOne), main.wordsWithoutFilter.contains(wordOne));
        Assert.assertEquals(main.maybeContainsWord(wordTwo), main.wordsWithoutFilter.contains(wordTwo));
        Assert.assertEquals(main.maybeContainsWord(wordThree), main.wordsWithoutFilter.contains(wordThree));

        //добавили по-обычному (через метод листа)
        main.wordsWithoutFilter.add(wordOne);
        main.wordsWithoutFilter.add(wordTwo);
        main.wordsWithoutFilter.add(wordThree);

        //добавили с использованием фильтра Блума
        main.addWord(wordOne);
        main.addWord(wordTwo);
        main.addWord(wordThree);

        //сравниваем после добавления
        Assert.assertEquals(main.maybeContainsWord(wordOne), main.wordsWithoutFilter.contains(wordOne));
        Assert.assertEquals(main.maybeContainsWord(wordTwo), main.wordsWithoutFilter.contains(wordTwo));
        Assert.assertEquals(main.maybeContainsWord(wordThree), main.wordsWithoutFilter.contains(wordThree));
    }

    @Test
    void testRemove() {
        //делаем наоборот
        Main main = init();
        String wordOne = "hi";
        String wordTwo = "friends";
        String wordThree = "achievement";


        //добавили по-обычному (через метод листа)
        main.wordsWithoutFilter.add(wordOne);
        main.wordsWithoutFilter.add(wordTwo);
        main.wordsWithoutFilter.add(wordThree);

        //добавили с использованием фильтра Блума
        main.addWord(wordOne);
        main.addWord(wordTwo);
        main.addWord(wordThree);

        //эти слова изначально там есть (проверяем)
        Assert.assertEquals(main.maybeContainsWord(wordOne), main.wordsWithoutFilter.contains(wordOne));
        Assert.assertEquals(main.maybeContainsWord(wordTwo), main.wordsWithoutFilter.contains(wordTwo));
        Assert.assertEquals(main.maybeContainsWord(wordThree), main.wordsWithoutFilter.contains(wordThree));

        //удалили по-обычному (через метод листа)
        main.wordsWithoutFilter.remove(wordOne);
        main.wordsWithoutFilter.remove(wordTwo);
        main.wordsWithoutFilter.remove(wordThree);

        //удалили с использованием фильтра Блума
        main.removeWord(wordOne);
        main.removeWord(wordTwo);
        main.removeWord(wordThree);


        //сравниваем после удаления
        Assert.assertEquals(main.maybeContainsWord(wordOne), main.wordsWithoutFilter.contains(wordOne));
        Assert.assertEquals(main.maybeContainsWord(wordTwo), main.wordsWithoutFilter.contains(wordTwo));
        Assert.assertEquals(main.maybeContainsWord(wordThree), main.wordsWithoutFilter.contains(wordThree));
    }
}
