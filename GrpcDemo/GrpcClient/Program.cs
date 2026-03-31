// See https://aka.ms/new-console-template for more information
using System;
using Grpc.Net.Client;
using GrpcClient;
using System.Threading.Tasks;
using GrpcServer;
using Grpc.Core;

namespace MyApp
{
    public class Program
    {
        static async Task Main(string[] args)
        {
            //var input = new HelloRequest { Name = "Draga" };

            //var channel = GrpcChannel.ForAddress("http://localhost:5280");
            //var client = new Greeter.GreeterClient(channel);

            //var reply = await client.SayHelloAsync(input);

            //Console.WriteLine(reply.Message);

            var channel = GrpcChannel.ForAddress("http://localhost:5280");
            var customerClient = new Customer.CustomerClient(channel);

            var clientRequested = new CustomerLookupModel { UserId = 1 };

            var reply = await customerClient.GetCustomerInfoAsync(clientRequested);

            Console.WriteLine(reply.FirstName + " " + reply.LastName);

            Console.WriteLine();
            Console.WriteLine("New Customer List:");
            Console.WriteLine();

            using (var call = customerClient.GetNewCustomers(new NewCustomerRequest()))
            {
                while (await call.ResponseStream.MoveNext())
                {
                    var currentCustomer = call.ResponseStream.Current;

                    Console.WriteLine($"{currentCustomer.FirstName} {currentCustomer.LastName}: {currentCustomer.EmailAddress}");
                }
            }

            Console.ReadLine();
        }
    }
} 


