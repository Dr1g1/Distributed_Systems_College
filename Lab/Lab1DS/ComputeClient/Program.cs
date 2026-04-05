using ComputeClient;
using Google.Protobuf;
using Grpc.Core;
using Grpc.Net.Client;

try
{
    using var channel = GrpcChannel.ForAddress("http://localhost:5043");
    var client = new ComputeService.ComputeServiceClient(channel);
    string input;
    
    while (true)
    {
        Console.WriteLine("\n\tEnter one number -> n1");
        Console.WriteLine("\tEnter stream -> nn");
        Console.WriteLine("\t(Empty input is for stopping the program!)");
        input = Console.ReadLine();
        if (string.IsNullOrEmpty(input)) break;
        int number;

        if (input == "n1")
        {
            Console.WriteLine("Enter one integer number...");
            number = Int32.Parse(Console.ReadLine());
            client.ComputeUnary(new IntNum { Num = number });
            Console.WriteLine();
        }
        else if (input == "nn")
        {
            Console.WriteLine("Enter numbers (empty line for stop)...");
            using var call = client.ComputeStream();

            var retStream = Task.Run(async () =>
            {
                await foreach (var num in call.ResponseStream.ReadAllAsync())
                    Console.WriteLine($"Server: {num.Num}");
            });

            while (true)
            {
                string num1 = Console.ReadLine();
                if (string.IsNullOrEmpty(num1)) break;
                await call.RequestStream.WriteAsync(new IntNum { Num = Int32.Parse(num1) });
            }
            await call.RequestStream.CompleteAsync();
            await retStream;
        }
        else
        {
            continue;
        }
    }
}
catch (Exception e)
{
    Console.WriteLine(e.Message);
}