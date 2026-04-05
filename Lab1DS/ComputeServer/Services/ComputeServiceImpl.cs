using System.Xml.Serialization;
using Google.Protobuf.WellKnownTypes;
using Grpc.Core;

namespace ComputeServer.Services
{
    public class ComputeServiceImpl : ComputeService.ComputeServiceBase
    {
        public static int acc = 1;
        public int paritycall = 0;

        public override Task<Empty> ComputeUnary(IntNum request, ServerCallContext context)
        {
            int temp = request.Num;
            
            if (paritycall == 0) 
            {
                temp *= acc;
            }
            else
            {
                temp += acc;
            }
            acc = temp;
            Console.WriteLine($"Accumulator: {acc}");
            paritycall ^= 1;
            return Task.FromResult(new Empty());
        }

        public override async Task ComputeStream(IAsyncStreamReader<IntNum> requestStream, IServerStreamWriter<IntNum> responseStream, ServerCallContext context)
        {
            int parity = 0;
            await foreach (var req in requestStream.ReadAllAsync())
            {
                int temp = req.Num;
                if(parity == 0)
                {
                    temp *= acc;
                }
                else
                {
                    temp += acc;
                }
                Console.WriteLine($"Accumulator: {acc}");
                parity ^= 1;
                
                await responseStream.WriteAsync(new IntNum { Num = temp });
            }
        }
    }
}
