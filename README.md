Решения задач с [algoprog.ru](https://algoprog.ru) и [informatics.mccme.ru](https://informatics.mccme.ru). Старые решения на Clojure
формально не проверялись, потому что Clojure нет среди доступных языков программирования. Решения на C и C++ формально проверены через
систему на [informatics.mccme.ru](https://informatics.mccme.ru) и прошли все тесты.

Проверить решения на Clojure можно так:

```
cd clojure
lein test
```

Тесты для решений на C и C++ не опубликованы, но собрать все такие программы можно так:
```
./build.sh
```

Исходные тесты задач не публикуются в этом репозитории. Их можно найти по ссылкам:

 * https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=<PROBLEM_ID>
 * https://algoprog.ru/material/p<PROBLEM_ID>
 
`PROBLEM_ID` содержится в имени файла с решением.
