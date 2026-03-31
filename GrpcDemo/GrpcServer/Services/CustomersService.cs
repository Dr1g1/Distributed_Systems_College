using Grpc.Core;

namespace GrpcServer.Services
{
    public class CustomersService : Customer.CustomerBase
    {
        private readonly ILogger<CustomersService> _logger;

        public CustomersService(ILogger<CustomersService> logger)
        {
            _logger = logger;
        }

        public override Task<CustomerModel> GetCustomerInfo(CustomerLookupModel request, ServerCallContext context)
        {
            CustomerModel output = new CustomerModel();
            
            if(request.UserId == 1)
            {
                output.FirstName = "Draga";
                output.LastName = "Jovic";
            }
            else if (request.UserId == 2)
            {
                output.FirstName = "Mila";
                output.LastName = "Jovic";
            }
            else
            {
                output.FirstName = "Mina";
                output.LastName = "Krasic";
            }

            return Task.FromResult(output);
        }

        public override async Task GetNewCustomers(NewCustomerRequest request, IServerStreamWriter<CustomerModel> responseStream, ServerCallContext context)
        {
            List<CustomerModel> customers = new List<CustomerModel>
            {
                new CustomerModel
                {
                    FirstName = "Tim",
                    LastName = "Burton",
                    EmailAddress = "nesto@gmail.com",
                    Age = 41,
                    IsAlive = true,
                },
                new CustomerModel
                {
                    FirstName = "Lena",
                    LastName = "Stojiljkovic",
                    EmailAddress = "nesto1@gmail.com",
                    Age = 22,
                    IsAlive = true,
                },
                new CustomerModel
                {
                    FirstName = "Uros",
                    LastName = "Stefanovic",
                    EmailAddress = "nesto22@gmail.com",
                    Age = 22,
                    IsAlive = true,
                }
            };

            foreach(var c in customers)
            {
                await Task.Delay(1000);
                await responseStream.WriteAsync(c);
            }
        }
    }
}
