public class DaoFactory
{
    public UserDao NaverUserDao()
    {
        ConnectionMaker connectionMaker = new NaverConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }

}
