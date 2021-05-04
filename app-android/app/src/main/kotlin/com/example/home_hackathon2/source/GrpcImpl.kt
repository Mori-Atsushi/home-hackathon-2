package com.example.home_hackathon2.source

import com.example.home_hackathon2.pb.App
import com.example.home_hackathon2.pb.AppServiceGrpc
import com.example.home_hackathon2.pb.AppServiceGrpcKt
import io.grpc.Metadata
import io.grpc.kotlin.ClientCalls
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GrpcImpl @Inject constructor(
    private val stub: AppServiceGrpcKt.AppServiceCoroutineStub,
) : Grpc {
    override suspend fun createUser(
        request: App.CreateUserRequest,
        meta: Metadata
    ): App.CreateUserResponse {
        return ClientCalls.unaryRpc(
            stub.channel,
            AppServiceGrpc.getCreateUserMethod(),
            request,
            stub.callOptions,
            meta
        )
    }

    override fun chatRoomEvent(
        requests: Flow<App.ChatRoomEventRequest>,
        meta: Metadata
    ): Flow<App.ChatRoomEventResponse> {
        return ClientCalls.bidiStreamingRpc(
            stub.channel,
            AppServiceGrpc.getChatRoomEventMethod(),
            requests,
            stub.callOptions,
            meta
        )
    }
}
