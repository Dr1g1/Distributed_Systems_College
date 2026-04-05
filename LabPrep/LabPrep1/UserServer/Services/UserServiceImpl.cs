using Google.Protobuf.WellKnownTypes;
using Grpc.Core;

namespace UserServer.Services
{
    public class UserServiceImpl : UserService.UserServiceBase
    {
        public override Task<User> GetUser(GetUserRequest request, ServerCallContext context)
        {
            if (!UserDB.DB.ContainsKey(request.Id))
                return Task.FromResult(new User());

            var dbUser = UserDB.DB[request.Id]; // ovim se vraca 

            var user = new User()
            {
                Id = dbUser.Id,
                Name = dbUser.Name,
                Surname = dbUser.Surname,
                Address = dbUser.Address
            };

            user.PhoneNumbers.AddRange(dbUser.PhoneNumbers);

            return Task.FromResult(user);
        }

        public override Task<UserListReply> GetAllUsers(Empty request, ServerCallContext context)
        {
            var users = new UserListReply();
            foreach(var dbUser in UserDB.DB.Values)
            {
                var user = new User()
                {
                    Id = dbUser.Id,
                    Name = dbUser.Name,
                    Surname = dbUser.Surname,
                    Address = dbUser.Address
                };
                user.PhoneNumbers.AddRange(dbUser.PhoneNumbers);
                users.Users.Add(user);
            }
            
            return Task.FromResult(users);
        }

        public override Task<AddReply> AddUser(AddUserRequest request, ServerCallContext context)
        {
            if (UserDB.DB.ContainsKey(Int32.Parse(request.User.Id)))
            {
                return Task.FromResult(new AddReply()
                {
                    Text = "User already exists!"
                });
            }

            UserDB.DB.Add(Int32.Parse(request.User.Id), request.User);
            return Task.FromResult(new AddReply()
            {
                Text = "User is added successfully!"
            });
        }

        public override Task<UpdateReply> UpdateUser(UpdateUserRequest request, ServerCallContext context)
        {
            if(!UserDB.DB.ContainsKey(Int32.Parse(request.User.Id)))
            {
                return Task.FromResult(new UpdateReply()
                {
                    Text = "This user doesn't exist!"
                });
            }
            var userDB = UserDB.DB[Int32.Parse(request.User.Id)];
            userDB.Name = request.User.Name;
            userDB.Surname = request.User.Surname;
            userDB.Address = request.User.Address;
            userDB.PhoneNumbers.AddRange(request.User.PhoneNumbers);
            return Task.FromResult(new UpdateReply()
            {
                Text = "User is updated successfully!"
            });
        }

        public override Task<Empty> DeleteUser(DeleteUserRequest request, ServerCallContext context)
        {
            if (!UserDB.DB.ContainsKey(Int32.Parse(request.Id)))
            {
                return Task.FromResult(new Empty());
            }
            UserDB.DB.Remove(Int32.Parse(request.Id));
            return Task.FromResult(new Empty());
        }

        public override Task<UserListReply> GetUsersInRange(GetUserInRangeRequest request, ServerCallContext context)
        {
            var users = UserDB.DB.OrderBy(u => u.Key).Where(u => u.Key >= request.FromId && u.Key <= request.ToId).Select(u => u.Value);

            var result = new UserListReply();
            result.Users.AddRange(users);
            return Task.FromResult(result);
        }

        public override async Task DeleteUsers(DeleteUsersRequest request, IServerStreamWriter<DeleteReply> responseStream, ServerCallContext context)
        {
            foreach(var uId in request.Ids)
            {
                if (!UserDB.DB.ContainsKey(Int32.Parse(uId)))
                    continue;
                UserDB.DB.Remove(Int32.Parse(uId));
                await responseStream.WriteAsync(new DeleteReply()
                {
                    Text = "User deleted successfully!"
                });
            }
        }
    }
}
