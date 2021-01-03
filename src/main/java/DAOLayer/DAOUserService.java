package DAOLayer;

import EntityLayer.Users;

import java.util.List;

public interface DAOUserService {

    //Добавление нового пользователя
    void addNewUser(Users new_user);

    //Список всех пользователей
    List<Users> getAllUsers();

    //Информаций о пльзователе по id_user
    Users getUserByIdUser(int id_user);

    //Обновление информации о пользователе
    void updateUser(Users update_user);

    //Удаление пользователя по id_user
    void deleteUser(int id_user);

    //Получения значения роли пользователя. Пользователь/Администратор
    Users getRoleUser(String login_user, String password_user);

}
