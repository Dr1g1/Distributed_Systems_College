using Google.Protobuf.WellKnownTypes;
using Grpc.Core;

namespace GrpcServer.Services
{
    public class ComputeServiceImpl : ComputeService.ComputeServiceBase
    {
        public int acc = 0;

        public override Task<Empty> ComputeAvg(IntNum request, ServerCallContext context)
        {
            int sum = 0;
            int i = 0;
            for(i = 0; i < request.Num; i++)
            {
                sum += i;
            }
            sum /= i;
            acc += sum;
            Console.WriteLine($"Accumulator: {acc}");
            return Task.FromResult(new Empty());
        }

        public override async Task ComputeStream(IAsyncStreamReader<IntNum> requestStream, IServerStreamWriter<IntNum> responseStream, ServerCallContext context)
        {
            int counter = 1;
            await foreach(var req in requestStream.ReadAllAsync())
            {
                //protobuf generisani objekti su readonly - ne mozemo direktno da im dodeljujemo vrednost
                int temp = 0;
                if(counter == 3)
                    temp = req.Num * acc;
                else
                    temp = req.Num - acc;

                counter++;
                await responseStream.WriteAsync(new IntNum { Num = temp });
            }
        }
    }
}
