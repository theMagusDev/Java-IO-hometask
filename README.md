# Частотный анализ. Пример работы с Java I/O. 

## Описание программы

Проект представляет собой консольное Java-приложение для анализа частотности букв в текстовых файлах. Пользователь вводит путь к файлу, в котором требуется подсчитать количество вхождений каждой буквы алфавита (как строчных, так и прописных). Если пользователь не указывает путь к файлу, используется текстовый файл с описанием языка Java, расположенный по умолчанию в `src/main/resources/java_description.txt`.

#### Принцип работы

1. **Запуск программы и выбор входного файла**:
    - Программа запрашивает у пользователя путь к файлу для анализа.
    - Если введена пустая строка, приложение использует файл по умолчанию, содержащий описание языка Java.
    - Программа проверяет, существует ли указанный файл, и, если его нет, повторно запрашивает путь у пользователя.

2. **Анализ частоты букв**:
    - После проверки существования файла программа вызывает метод `calculateLettersFrequency` из класса `FrequencyAnalyzer`.
    - Этот метод считывает содержимое файла, пропуская небуквенные символы, и подсчитывает количество вхождений каждой буквы, сохраняя данные в словаре `HashMap`.

3. **Вывод результата**:
    - Программа запрашивает у пользователя имя выходного файла для сохранения результатов.
    - Частотный словарь записывается в указанный файл в формате: `<буква>: <количество>`.
    - Если возникает ошибка при записи в файл, программа выводит соответствующее сообщение об ошибке.