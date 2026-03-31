
namespace UserServer
{
    public static class UserDB
    {
        public static Dictionary<int, User> DB { get; set; } = new Dictionary<int, User>();

        static UserDB() //staticki konstruktor - poziva se automatski jednom
        {
            DB.Add(1, new User
            {
                Id = "1",
                Name = "Draga",
                Surname = "Jovic",
                Address = "Ulica 1",
                PhoneNumbers =
                {
                    new User.Types.PhoneNumber { Number = "555-1234"},
                    new User.Types.PhoneNumber { Number = "444-9876"}
                }
            });
            DB.Add(2, new User
            {
                Id = "2",
                Name = "Mila",
                Surname = "Jovic",
                Address = "Ulica 1",
                PhoneNumbers =
                {
                    new User.Types.PhoneNumber {Number = "111-2222"}
                }
            });
        }
    }
}
