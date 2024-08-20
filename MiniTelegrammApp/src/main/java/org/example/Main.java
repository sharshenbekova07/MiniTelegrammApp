package org.example;

import org.example.entities.Message;
import org.example.entities.User;
import org.example.servisec.MessageManager;
import org.example.servisec.UserManager;

import java.util.Scanner;

public class Main {
    private static UserManager userManager = new UserManager();
    private static MessageManager messageManager = new MessageManager();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Mini App Telegram >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в консольное приложение Телеграмм!");

        while (true){
            System.out.print("Введите команду:  >>> ");
            String command = scanner.nextLine().trim();

            switch(command) {
                case "exit":
                    System.out.println( "Вы вышли из систему! \nСпасибо, что воспользовались нашей программой...");
                    return;
                case "help":
                    showHelp();
                    break;
                case "all_users":
                    showAllUsers();
                    break;
                case "login":
                    login(scanner);
                    break;
                case "register":
                    register(scanner);
                    break;
                case "search_user_by_login":
                    searchUserByLogin(scanner);
                    break;
                case "logout":
                    logout();
                    break;
                case "read_all_message":
                    readAllMessage(scanner);
                    break;
                case "send_message":
                    sendMessage(scanner);
                    break;
                case "mail_info":
                    showMailInfo();
                    break;
                case "info":
                    showUserInfo();
                    break;
                default:
                    System.out.println("Ошибка: Такой команды нет в системе \nВыполнение операции прервано.");
            }
        }

    }

    private static void showHelp() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<< Вывод списка всех команд системы >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (loggedInUser == null) {
            System.out.println("----Доступные команды для неавторизованных пользователей:----");
            System.out.println("exit - Выход из программы.");
            System.out.println("help - Показать это сообщение.");
            System.out.println("all_users - Показать всех пользователей в системе.");
            System.out.println("login - Вход пользователя.");
            System.out.println("register - Регистрация нового пользователя.");
        }else {
            System.out.println("----Доступные команды для авторизованных пользователей:----");
            System.out.println("exit - Выход из программы.");
            System.out.println("help - Показать это сообщение.");
            System.out.println("all_users - Показать всех пользователей в системе.");
            System.out.println("search_user_by_login - Поиск пользователя по логину.");
            System.out.println("logout - Выход текущего пользователя.");
            System.out.println("read_all_message - Прочитать все сообщения авторизованного пользователя.");
            System.out.println("send_message - Отправить сообщение пользователю по логину.");
            System.out.println("mail_info - Показать информацию о количестве полученных сообщений.");
            System.out.println("info - Показать информацию о текущем пользователе.");
        }

        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }


    private static void showAllUsers() {
        for (User user : userManager.getAllUsers()) {
            System.out.println(user);
        }
    }

    private static void login(Scanner scanner) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<< Аутентификация пользователя >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.print("Введите логин: ");
        String login = scanner.nextLine().trim();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine().trim();

        User user = userManager.authenticateUser(login,password);
        if (user != null) {
            loggedInUser = user;
            System.out.println("Добро пожаловать в систему: " + user.getLogin());
        } else {
            System.out.println("Неправильный логин или пароль.");
        }

        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private static void register(Scanner scanner) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< Регистрация пользователя в систему >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        System.out.print("Введите логин: ");
        String login = scanner.nextLine().trim();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine().trim();

        System.out.print("Подтвердите пароль: ");
        String confirmPassword = scanner.nextLine().trim();
        if (!password.equals(confirmPassword)) {
            System.out.println("Пароли не совпадают. Повторите попытку.");
            return;
        }

        System.out.print("Введите номер телефона: ");
        String phone = scanner.nextLine().trim();

        User user = userManager.registerUser(login, password, phone);
        if (user != null) {
            System.out.println("Пользователь успешно зарегистрирован.");
        } else {
            System.out.println("Регистрация не удалось. Возможно, пользователь уже существует.");
        }

        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private static void searchUserByLogin(Scanner scanner) {
        System.out.print("Введите логин для поиска: ");
        String login = scanner.nextLine().trim();

        User user = userManager.findUserByLogin(login);
        if (user != null) {
            System.out.println("Пользователь найден: " + user);
        } else {
            System.out.println("Пользователь не наййден.");
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private static void logout() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< Выход пользователя из системы  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        if (loggedInUser != null) {
            System.out.println("Вы вышли из системы: " + loggedInUser.getLogin() + "\n Всего доброго !");
            loggedInUser = null;
        } else {
            System.out.println("В настоящий момент никто не вошел в систему.");
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    }

    private static void readAllMessage(Scanner scanner) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< Вывод всех сообщений авторизированного пользователя >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        if (loggedInUser != null) {
            for  (Message message : messageManager.getMessageForUser(loggedInUser)) {
                System.out.println(message);
            }
        }else {
            System.out.println("Сначала нужно войти в систему.");
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    }

    private static void sendMessage(Scanner scanner) {
        if (loggedInUser != null) {
            System.out.print("Введите логин получателя: ");
            String recipientLogin = scanner.nextLine().trim();

            System.out.print("Введите сообщение: ");
            String messageText = scanner.nextLine().trim();

            User recipient = userManager.findUserByLogin(recipientLogin);
            if (recipient != null) {
                Message message = new Message(loggedInUser, recipient, messageText);
                messageManager.sendMessage(message);
                System.out.println("Сообщение отправлено.");
            }else  {
                System.out.println("Получатель не найден...");
            }
        }else {
            System.out.println("Сначала нужно войти в систему... ");
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    }

    private static void showMailInfo() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< Информация о количестве полученных сообщений. >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        if (loggedInUser != null) {
            int messageCount = messageManager.getMessageCountForUser(loggedInUser);
            int totalMessageCount = messageManager.getTotalMessageCount();
            System.out.println("Общее количество сообщений в системе: " + totalMessageCount);
            if(messageCount > 0) {
                System.out.println("У вас " + messageCount + " сообщений.");
            } else{
                System.out.println("У вас нет сообщений.");
            }
        }else {
            System.out.println("Сначала нужно войти в систему.");
        }

        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private static void showUserInfo() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< Вывод информации о авторизированном пользователе >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (loggedInUser != null) {
            System.out.println( " " +loggedInUser);
        } else {
            System.out.println("В настоящий момент никто не вошел в систему...");
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

}