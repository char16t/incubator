# Monorepository with useless programs

Directory structure:

 * [/adoc](/adoc) - AsciiDoc sandbox. It is a best format for large documentation
 * [/algoprog.ru](/algoprog.ru) - Problem solving from [algoprog.ru](https://algoprog.ru) and [informatics.mccme.ru](https://informatics.mccme.ru) sites in C++
 * [/capnproto](/capnproto) - Experiments with Cap'n Proto. Cap'n Proto is an insanely fast data interchange format and capability-based RPC system.
 * [/clojure](/clojure) - Problem solving from [algoprog.ru](https://algoprog.ru) and [informatics.mccme.ru](https://informatics.mccme.ru) sites in Clojure
 * [/comb](/comb) - Some useful combinatorics algorithms implemented in Python
 * [/cpp](/cpp) - External and internal web-services on C++ and Cap'n'Proto
 * [/crypto](/crypto) - Cryptography
 * [/kotlin](/kotlin) - Algorithms on graphs implemented on Kotlin
 * [/java](/java) - Data structures, algorithms written on pure java from scratch + actors
 * [/scala](/scala) - Functional programming practice on pure Scala and using Cats library (and some other)
 * [/ml](/ml) - Machine Learning
 * [/sql](/sql) - State machine, tree-structures, versioning, discontinuous ranges intersection and other non-trivial implementations on SQL
 * [/typelevel-ecosystem](/typelevel-ecosystem) - Web-services on Scala wrtten in functional style
 * [/windows](/windows) - Automation for Windows

---

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

Исходные тексты задач не публикуются в этом репозитории. Их можно найти по ссылкам:

 * https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=<PROBLEM_ID>
 * https://algoprog.ru/material/p<PROBLEM_ID>
 
`PROBLEM_ID` содержится в имени файла с решением.

---

**Список решённых задач**:

Приведены ссылки на исходные тексты задач и ссылка на решение, которое хранится в этом репозитории.

 * Уровень 1
   - Уровень 1Б
     + 1Б: Задачи на вещественные числа
       * [a + b = c](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=74) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p74.c))
       * [Часы - 1](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=3612) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p3612.c))
       * [Утренняя пробежка - 1](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=596) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p596.c))
       * [Утренняя пробежка - 2](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=597) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p597.c))
       * [Диета](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=595) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p595.c))
       * [1/0!+1/1!+1/2!+...](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=120) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p120.c))
   - Уровень 1В
     + 1В: Продвинутые задачи на арифметические операции: в них запрещается пользоваться if'ами и циклами
       * [Парты](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=2946) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p2946.c))
       * [Следующее четное](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=2945) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p2945.c))
       * [Строки в книге](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=506) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p506.c))
       * [Ваня наблюдает за лягушкой](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=534) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p534.c))
     + 1В: Продвинутые задачи на условный оператор: в них запрещено пользоваться циклами и массивами
       * [Ход ладьи](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=297) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p297.c))
       * [Слон](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=255) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p255.c))
       * [Шоколадка](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=258) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p258.c))
       * [Электричка](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=38) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p38.cpp))
       * [Мороженое](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=264) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p264.cpp))
       * [Уравнение](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=235) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p235.cpp))
       * [Коровы](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=303) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p303.cpp))
     + 1В: Продвинутые задачи на циклы: в них запрещается пользоваться массивами
       * [Максимальный элемент массива](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=227) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p227.cpp))
       * [Номер максимального элемента массива](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=228) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p228.cpp))
       * [Количество элементов, равных максимуму](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=3072) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p3072.cpp))
       * [Максимальное число идущих подряд равных элементов](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=3077) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p3077.cpp))
       * [ГНЧЭ-1](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=1430) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p1430.cpp))
     + 1В: Продвинутые задачи на массивы
       * [Шеренга](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=1456) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p1456.cpp))
       * [Числа Фибоначи](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=201) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p201.cpp))
       * [Цифры](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=1568) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p1568.cpp))
     + 1В: Продвинутые задачи на вещественные числа
       * [Первая цифра после точки](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=3609) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p3609.cpp))
       * [a + b = c](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=74) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p74.c))
       * [Диета](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=595) ([**C**](https://github.com/char16t/training/blob/master/algoprog.ru/p595.c))
     + 1В: Продвинутые задачи на строки
       * [Самое длинное слово](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=107) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p107.cpp))
   - Уровень 1Г
     + 1Г: Дополнительные задачи на разные темы - 1
       * [Электронные часы - 2](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=3469) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p3469.cpp))
       * [Улитка](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=3477) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p3477.cpp))
       * [Конец уроков](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=3472) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p3472.cpp))
       * [Дележ яблок - 3](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=2954) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p2954.cpp))
       * [Фибоначчиева последовательность](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=1370)  ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p1370.cpp))
     + 1Г: Дополнительные задачи на разные темы - 2
       * [Симметричная последовательность](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=507)  ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p507.cpp))
       * [Метро](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=511) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p511.cpp))
     + 1Г: Дополнительные задачи на разные темы - 3
       * [IP-адрес](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=1435) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p1435.cpp))
 * Уровень 2
   - Уровень 2А
     + 2А: Задачи на НОД
       * [НОД](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=199) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p199.cpp))
       * [Сокращение дроби](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=27) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p27.cpp))
       * [Отрезок](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=1838) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p1838.cpp))
     + 2А: \*Задачи на рекурсивный перебор
       * [Двоичные строки заданной длины](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=80) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p80.cpp))
       * [Все двоичные строки длины n, содержащие ровно k единиц](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=84) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p84.cpp))
       * [Все перестановки заданной длины](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=85) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p85.cpp))
       * [Разбиение на невозрастающие слагаемые, лексикографический порядок](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=89) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p89.cpp))
       * [Разбиение на невозрастающие слагаемые, обратный порядок](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=90) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p90.cpp))
       * [Разбиение на неубывающие слагаемые, лексикографический порядок](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=91) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p91.cpp))
     + 2А: Задачи на квадратичные сортировки
       * [Сортировка выбором максимума](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=230) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p230.cpp))
       * [Библиотечный метод](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=1436) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p1436.cpp))
       * [Пузырьковая сортировка: количество обменов](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=1411) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p1411.cpp))
       * [Скидки](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=1099) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p1099.cpp))
       * [Обувной магазин](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=39) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p39.cpp))
     + 2А: Задачи "на технику"
       * [ЕГЭ](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=848) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p848.cpp))
   - Уровень 2Б
     + 2Б: Простые задачи на ДП
       * [Последняя цифра числа Фибоначчи](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=842) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p842.cpp))
       * [Простая последовательность](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=843) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p843.cpp))
       * [Взрывоопасность](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=913) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p913.cpp))
       * [Без трех единиц](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=912) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p912.cpp))
       * [Взрывоопасность-2](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=914) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p914.cpp))
       * [Платная лестница](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=915) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p915.cpp))
       * [Биномиальные коэффициенты](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=206) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p206.cpp))
       * [Cамый дешевый путь](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=944) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p944.cpp))
     + 2Б: Задачи на стек, очередь и дек
       * [Простой стек](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=54) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p54.cpp))
       * [Очередь с защитой от ошибок](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=58) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p58.cpp))
       * [Дек с защитой от ошибок](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=61) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p61.cpp))
       * [Игра в пьяницу](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=50) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p50.cpp))
     + 2Б: Задачи на множители
       * [Проверка на простоту](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=310) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p310.cpp))
       * [Разложение на простые множители](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=623) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p623.cpp))
       * [Простое число](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=973) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p973.cpp))
     + 2Б: Задачи на простую жадность
       * [Создание архива](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=1576) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p1576.cpp))
       * [Решение задач](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=2826) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p2826.cpp))
       * [Путешествие](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=113075) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p113075.cpp))
   - Уровень 2В
     + 2В: Продвинутые задачи на НОД
       * [Шестеренки](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=1422) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p1422.cpp))
       * [Кинотеатр](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=404) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p404.cpp))
     + 2В: Продвинутые задачи на квадратичные сортировки И на стек-очередь-дек
       * [Минимальное число](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=720) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p720.cpp))
       * [Правильная скобочная последовательность](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=51) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p51.cpp))
     + 2В: Продвинутые задачи на ДП
       * [Плавные числа](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=2998) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p2998.cpp))
       * [Ход коня](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=2999) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p2999.cpp))
       * [Калькулятор](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=2963) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p2963.cpp))
       * [0-1 рюкзак: наибольший вес](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=1119) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p1119.cpp))
     + 2В: Дополнительные задачи на разные темы
       * [Постфиксная запись](https://informatics.mccme.ru/moodle/mod/statements/view3.php?chapterid=52) ([**C++**](https://github.com/char16t/training/blob/master/algoprog.ru/p52.cpp))
