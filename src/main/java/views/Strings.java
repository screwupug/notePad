package views;

public enum Strings {
    MAIN_MENU
            (
                    """
                           READ - прочитать одну заметку
                           CREATE - создать заметку
                           DELETE - удалить заметку
                           UPDATE - изменить заметку
                           LIST - посмотреть все записки
                           EXIT - выход из программы
                        """
            ),
    UPDATE_MENU
            (
                    """
                            HEADER - изменить название
                            BODY - изменить текст
                            BACK - назад в главное меню
                            """
            );


    private final String text;

    Strings(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
    }
