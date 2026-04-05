using Grpc.Core;
using Grpc.Net.Client;
using System.Transactions;
using UserClient;

using var channel = GrpcChannel.ForAddress("http://localhost:5169");
var client = new UserService.UserServiceClient(channel);

string unos;

do
{
    Console.WriteLine("\n\tGet user = g1");
    Console.WriteLine("\tGet all users = gn");
    Console.WriteLine("\tAdd user = a1");
    Console.WriteLine("\tUpdate user = u1");
    Console.WriteLine("\tDelete user = d1");
    Console.WriteLine("\n\tGet users in specified range = gr");
    Console.WriteLine("\tDelete users = dn");
    unos = Console.ReadLine();

    switch (unos)
    {
        case "g1":
            await GetUser();
            break;
        case "gn":
            await GetAllUsers();
            break;
        case "a1":
            await AddUser();
            break;
        case "u1":
            await UpdateUser();
            break;
        case "d1":
            await DeleteUser();
            break;
        case "gr":
            await GetUsersInRange();
            break;
        case "dn":
            await DeleteUsers();
            break;
        case "x": break;
        default: break;
    }

} while (unos != "x");


async Task GetUser()
{
    Console.WriteLine("Enter user's ID...");
    int userId = Int32.Parse(Console.ReadLine());

    try
    {
        var resp = await client.GetUserAsync(new GetUserRequest
        {
            Id = userId
        });
        Console.WriteLine($"{resp.Id} {resp.Name} {resp.Surname}");
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}
async Task GetAllUsers()
{
    try
    {
        var resp = await client.GetAllUsersAsync(new Google.Protobuf.WellKnownTypes.Empty());
        foreach(var u in resp.Users)
        {
            Console.WriteLine($"{u.Id} {u.Name} {u.Surname}");
        }
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}

async Task AddUser()
{
    Console.WriteLine("Enter user's ID...");
    string id = Console.ReadLine();
    Console.WriteLine("Enter user's name...");
    string name = Console.ReadLine();
    Console.WriteLine("Enter user's surname...");
    string surname = Console.ReadLine();
    Console.WriteLine("Enter user's address...");
    string address = Console.ReadLine();

    var user = new User()
    {
        Id = id,
        Name = name,
        Surname = surname,
        Address = address
    };

    Console.WriteLine("Enter phone numbers (empty line to stop)...");
    while(true)
    {
        string phone = Console.ReadLine();
        if (string.IsNullOrEmpty(phone)) break;
        user.PhoneNumbers.Add(new User.Types.PhoneNumber { Number = phone });
    }

    try
    {
        var resp = await client.AddUserAsync(new AddUserRequest { User = user });
        Console.WriteLine(resp.Text);
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}
async Task UpdateUser()
{
    Console.WriteLine("Enter ID of user to update...");
    string id = Console.ReadLine();
    Console.WriteLine("Enter new name...");
    string name = Console.ReadLine();
    Console.WriteLine("Enter new surname...");
    string surname = Console.ReadLine();
    Console.WriteLine("Enter new address...");
    string address = Console.ReadLine();

    var user = new User()
    {
        Id = id,
        Name = name,
        Surname = surname,
        Address = address
    };

    Console.WriteLine("Enter phone numbers (empty line to stop)...");
    while (true)
    {
        string phone = Console.ReadLine();
        if (string.IsNullOrEmpty(phone)) break;
        user.PhoneNumbers.Add(new User.Types.PhoneNumber { Number = phone });
    }

    try
    {
        var resp = await client.UpdateUserAsync(new UpdateUserRequest { User = user });
        Console.WriteLine(resp.Text);
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}
async Task DeleteUser()
{
    Console.WriteLine("Enter ID of user to delete...");
    string id = Console.ReadLine();

    try
    {
        await client.DeleteUserAsync(new DeleteUserRequest() { Id = id });
        Console.WriteLine("User deleted.");
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}
async Task GetUsersInRange()
{
    Console.WriteLine("Enter from ID...");
    int fromId = Int32.Parse(Console.ReadLine());
    Console.WriteLine("Enter to ID...");
    int toId = Int32.Parse(Console.ReadLine());

    try
    {
        var resp = await client.GetUsersInRangeAsync(new GetUserInRangeRequest() { FromId = fromId, ToId = toId });
        foreach(var u in resp.Users)
        {
            Console.WriteLine($"{u.Id} {u.Name} {u.Surname} {u.Address}");
            foreach (var phone in u.PhoneNumbers)
                Console.WriteLine($"\t{phone.Number}");
        }
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}
async Task DeleteUsers()
{
    Console.WriteLine("Enter IDs to delete (empty line to stop)...");
    var request = new DeleteUsersRequest();
    while(true)
    {
        string id = Console.ReadLine();
        if (string.IsNullOrEmpty(id))
            break;
        request.Ids.Add(id);
    }

    try
    {
        using var streamingCall = client.DeleteUsers(request);
        await foreach (var reply in streamingCall.ResponseStream.ReadAllAsync())
        {
            Console.WriteLine(reply.Text);
        }
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}


